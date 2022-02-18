package cc.carm.plugin.regionprotection.command;

import cc.carm.plugin.regionprotection.RegionProtection;
import cc.carm.plugin.regionprotection.configuration.PluginMessages;
import cc.carm.plugin.regionprotection.manager.PlayerManager;
import cc.carm.plugin.regionprotection.model.ProtectedRegion;
import cc.carm.plugin.regionprotection.model.SelectingRegion;
import cc.carm.plugin.regionprotection.util.LuckPermsUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RegionProtectCommands implements CommandExecutor, TabCompleter {

    public boolean help(CommandSender sender) {
        PluginMessages.Command.Usage.HEAD.send(sender);
        PluginMessages.Command.Usage.COMMON.send(sender);
        if (LuckPermsUtils.hasLuckPerms()) PluginMessages.Command.Usage.PERMITTING.send(sender);
        if (sender instanceof Player) PluginMessages.Command.Usage.SELECTION.send(sender);
        return true;
    }

    public boolean onlyPlayer(CommandSender sender) {
        sender.sendMessage("后台不可以执行该指令。");
        return true;
    }

    public boolean noLuckPerms(CommandSender sender) {
        sender.sendMessage("本服务器未安装LuckPerms，无法使用该指令。");
        return true;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String alias, @NotNull String[] args) {
        PlayerManager manager = RegionProtection.getPlayerManager();

        if (args.length == 1) {
            String aim = args[0];
            if (aim.equalsIgnoreCase("reload")) {
                try {
                    sender.sendMessage("正在重载配置文件...");
                    long s1 = System.currentTimeMillis();
                    RegionProtection.getPluginConfigManager().reload();
                    sender.sendMessage("重载完成！耗时 " + (System.currentTimeMillis() - s1) + " 毫秒。");
                } catch (Exception e) {
                    sender.sendMessage("重载失败，请检查配置文件是否有误。");
                    e.printStackTrace();
                }
                return true;
            } else if (aim.equalsIgnoreCase("list")) {
                Map<String, ProtectedRegion> regions = RegionProtection.getRegionManager().getRegions();
                if (regions.isEmpty()) {
                    PluginMessages.Command.List.NOTHING.send(sender);
                } else {
                    PluginMessages.Command.List.HEAD.send(sender);
                    regions.forEach((name, region) -> PluginMessages.Command.List.VALUE
                            .send(sender, new Object[]{name, region.toString()})
                    );
                }
                return true;
            } else if (aim.equalsIgnoreCase("info")) {
                if (!(sender instanceof Player)) return onlyPlayer(sender);
                Player player = (Player) sender;
                SelectingRegion region = manager.getSelectingRegion(player);

                if (region == null) {
                    PluginMessages.SelectMode.NOT_SELECTING.send(player);
                    return true;
                }

                PluginMessages.SelectMode.SELECTED_INFO.send(player, new Object[]{
                        region.getWorld(),
                        (region.getPos1() == null ? "未选择" : region.getPos1().toString()),
                        (region.getPos2() == null ? "未选择" : region.getPos2().toString())
                });

                return true;
            } else if (aim.equalsIgnoreCase("select")) {
                if (!(sender instanceof Player)) return onlyPlayer(sender);
                Player player = (Player) sender;
                if (manager.isSelecting(player)) {
                    manager.endSelecting(player);
                    PluginMessages.SelectMode.DISABLING.send(player);
                } else {
                    manager.startSelecting(player);
                    PluginMessages.SelectMode.ENABLING.send(player);
                }
                return true;
            } else return help(sender);
        } else if (args.length == 2) {
            String aim = args[0];
            if (aim.equalsIgnoreCase("allow") || aim.equalsIgnoreCase("deny")) {
                if (!LuckPermsUtils.hasLuckPerms()) return noLuckPerms(sender);

                Player player = Bukkit.getPlayer(args[1]);
                if (player == null) {
                    PluginMessages.NOT_ONLINE.send(sender, new Object[]{args[1]});
                    return true;
                }

                if (aim.equalsIgnoreCase("allow")) {
                    if (manager.isPermitted(player)) {
                        PluginMessages.Command.Permitting.ALREADY_HAVE.send(sender, new Object[]{player.getName()});
                    } else {
                        manager.addPermission(player);
                        PluginMessages.Command.Permitting.ALLOW.send(sender, new Object[]{player.getName()});
                    }
                } else {
                    if (!manager.isPermitted(player)) {
                        PluginMessages.Command.Permitting.NOT_HAVE.send(sender, new Object[]{player.getName()});
                    } else {
                        manager.removePermission(player);
                        PluginMessages.Command.Permitting.DENY.send(sender, new Object[]{player.getName()});
                    }
                }

                return true;
            } else if (aim.equalsIgnoreCase("set")) {
                if (!(sender instanceof Player)) return onlyPlayer(sender);
                Player player = (Player) sender;

                if (!manager.isSelecting(player)) {
                    PluginMessages.SelectMode.NOT_SELECTING.send(player);
                    return true;
                }

                String regionName = args[1];
                if (RegionProtection.getRegionManager().getRegions().containsKey(regionName)) {
                    PluginMessages.Command.Set.ALREADY_EXIST.send(player, new Object[]{regionName});
                    return true;
                }

                SelectingRegion region = manager.getSelectingRegion(player);
                if (region.getPos1() == null || region.getPos2() == null) {
                    PluginMessages.SelectMode.NO_SELECTED_REGION.send(player);
                    return true;
                }

                ProtectedRegion protectedRegion = new ProtectedRegion(region.getWorld(), region.getPos1(), region.getPos2());
                RegionProtection.getRegionManager().setRegion(regionName, protectedRegion);

                PluginMessages.Command.Set.SUCCESS.send(player, new Object[]{regionName, protectedRegion.toString()});
                return true;
            } else if (aim.equalsIgnoreCase("remove")) {
                if (!(sender instanceof Player)) return onlyPlayer(sender);
                String regionName = args[1];
                if (!RegionProtection.getRegionManager().getRegions().containsKey(regionName)) {
                    PluginMessages.Command.Remove.NOT_EXIST.send(sender, new Object[]{regionName});
                    return true;
                }
                RegionProtection.getRegionManager().removeRegion(regionName);
                PluginMessages.Command.Remove.SUCCESS.send(sender, new Object[]{regionName});
                return true;
            } else return help(sender);
        } else return help(sender);

    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                      @NotNull String alias, @NotNull String[] args) {
        List<String> allCompletes = new ArrayList<>();
        switch (args.length) {
            case 1: {
                allCompletes.add("list");
                if (LuckPermsUtils.hasLuckPerms()) {
                    allCompletes.add("allow");
                    allCompletes.add("deny");
                }
                if (sender instanceof Player) {
                    allCompletes.add("info");
                    allCompletes.add("select");
                    allCompletes.add("set");
                    allCompletes.add("remove");
                }
                allCompletes.add("reload");
                break;
            }
            case 2: {
                String aim = args[0];
                if (LuckPermsUtils.hasLuckPerms() &&
                        (aim.equalsIgnoreCase("allow") || aim.equalsIgnoreCase("deny"))) {
                    allCompletes = Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList());
                } else if (sender instanceof Player && aim.equals("remove")) {
                    allCompletes = new ArrayList<>(RegionProtection.getRegionManager().getRegions().keySet());
                }
                break;
            }
        }
        return allCompletes.stream()
                .filter(s -> StringUtil.startsWithIgnoreCase(s, args[args.length - 1]))
                .limit(10).collect(Collectors.toList());
    }
}
