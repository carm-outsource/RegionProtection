package cc.carm.plugin.regionprotection.listener;

import cc.carm.plugin.regionprotection.Main;
import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import cc.carm.plugin.regionprotection.configuration.PluginMessages;
import cc.carm.plugin.regionprotection.model.ProtectedRegion;
import cc.carm.plugin.regionprotection.util.LocationMathUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.util.Vector;

import java.util.Map;

import static cc.carm.plugin.regionprotection.RegionProtection.getPlayerManager;
import static cc.carm.plugin.regionprotection.RegionProtection.getRegionManager;

public class RegionListener implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.isCancelled() || event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN) return; // 事件被其他插件取消
        Main.debugging("Checking teleport event for " + event.getPlayer().getName());

        Location toLocation = event.getTo();
        if (toLocation == null) return; // 无处可去 ~

        Player player = event.getPlayer();
        if (getPlayerManager().isPermitted(player)) return; // 玩家已经通过授权或已满足条件

        Map.Entry<String, ProtectedRegion> regionIn = getRegionManager().getFirstRegionIn(toLocation);
        if (regionIn != null) {
            event.setCancelled(true); //阻止传送
            PluginConfig.Sounds.NOT_PERMITTED.play(player);
            PluginMessages.NOT_PERMITTED.send(player, new Object[]{regionIn.getKey()});
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (event.isCancelled()) return; // 事件被其他插件取消

        Location toLocation = event.getTo();
        if (toLocation == null) return; // 无处可去 ~

        Player player = event.getPlayer();
        if (!getPlayerManager().shouldCheck(player.getUniqueId())) return; // 还在检查冷却中
        getPlayerManager().updateCheckTime(player.getUniqueId());
        if (getPlayerManager().isPermitted(player)) return; // 玩家已经通过授权或已满足条件

        Map.Entry<String, ProtectedRegion> regionIn = getRegionManager().getFirstRegionIn(toLocation);
        if (regionIn == null) {
            getPlayerManager().resetTriedTimes(player.getUniqueId());
            return; // 不在任何区域内
        }

        String regionName = regionIn.getKey();
        ProtectedRegion region = regionIn.getValue();

        PluginConfig.Sounds.NOT_PERMITTED.play(player);
        PluginMessages.NOT_PERMITTED.send(player, new Object[]{regionName});

        LocationMathUtils.withMinDistance(event.getTo(), region, (xz, line) -> {
            if (getPlayerManager().isTriedTimesExceeded(player.getUniqueId())) {
                // 尝试了很多次也没把他弹出去，直接传送出去

                Location location = LocationMathUtils.getLocationAdded(event.getTo(), xz, line, 2);
                player.teleport(location, PlayerTeleportEvent.TeleportCause.PLUGIN);

                Main.debugging("Teleporting " + player.getName() + " to " + location);

            } else {
                // 尝试把玩家弹出区域
                Vector v = xz ? new Vector(2 * (line > 0 ? 1 : -1), 0.5, 0) : new Vector(0, 0.5, 2 * (line > 0 ? 1 : -1));
                player.setVelocity(v);
                player.setFallDistance(-500L); // 防止摔落上海
                getPlayerManager().addTriedTimes(player.getUniqueId()); // 添加一次尝试次数
                Main.debugging("Add velocity " + v + " to " + player.getName());
            }

        });


    }

    @EventHandler
    public void onBukkitEmpty(PlayerBucketEmptyEvent event) {
        if (event.isCancelled()) return; // 事件被其他插件取消
        Main.debugging("Checking bucket empty event for " + event.getPlayer().getName());
        Location toLocation = event.getBlock().getLocation();
        Player player = event.getPlayer();
        if (getPlayerManager().isPermitted(player)) {
            Main.debugging("Player " + event.getPlayer().getName() + " has been permitted.");
            return; // 玩家已经通过授权或已满足条件
        }

        Map.Entry<String, ProtectedRegion> regionIn = getRegionManager().getFirstRegionIn(toLocation);
        if (regionIn == null) {
            Main.debugging("Player " + event.getPlayer().getName() + " not in any regions.");
            return;
        }

        event.setCancelled(true);
        PluginConfig.Sounds.NOT_PERMITTED.play(player);
        PluginMessages.NO_BUCKET.send(player, new Object[]{regionIn.getKey()});
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Main.debugging("Checking quit event for " + event.getPlayer().getName());
        getPlayerManager().clear(event.getPlayer().getUniqueId());
    }


}
