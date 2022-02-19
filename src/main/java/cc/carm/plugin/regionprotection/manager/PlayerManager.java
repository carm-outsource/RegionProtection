package cc.carm.plugin.regionprotection.manager;

import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import cc.carm.plugin.regionprotection.model.SelectingRegion;
import cc.carm.plugin.regionprotection.util.LuckPermsUtils;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    public Map<UUID, Integer> triedTimes = new HashMap<>();
    public Map<UUID, Long> checkInterval = new HashMap<>();

    public Map<UUID, SelectingRegion> selectingData = new HashMap<>();

    public boolean isPermitted(Player player) {
        return player.hasPermission("RegionProtection.admin")
                || player.hasPermission(PluginConfig.PERMISSION.get())
                || getPlayMinutes(player) >= PluginConfig.PLAY_TIME.get();
    }

    public double getPlayMinutes(Player player) {
        return (double) player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 1200;
    }

    public Map<UUID, SelectingRegion> getSelectingData() {
        return selectingData;
    }

    public boolean isSelecting(Player player) {
        return getSelectingData().containsKey(player.getUniqueId());
    }

    public SelectingRegion getSelectingRegion(Player player) {
        return getSelectingData().get(player.getUniqueId());
    }

    public SelectingRegion startSelecting(Player player) {
        SelectingRegion region = new SelectingRegion(player.getWorld().getName());
        this.selectingData.put(player.getUniqueId(), region);
        return region;
    }

    public void endSelecting(Player player) {
        this.selectingData.remove(player.getUniqueId());
    }


    public void addTriedTimes(UUID uuid) {
        if (triedTimes.containsKey(uuid)) {
            triedTimes.put(uuid, triedTimes.get(uuid) + 1);
        } else {
            triedTimes.put(uuid, 1);
        }
    }

    public void resetTriedTimes(UUID uuid) {
        triedTimes.remove(uuid);
    }

    public boolean isTriedTimesExceeded(UUID uuid) {
        return triedTimes.containsKey(uuid) && triedTimes.get(uuid) > 1;
    }

    public boolean shouldCheck(UUID uuid) {
        int checkTicks = PluginConfig.CHECK_INTERVAL.get();
        if (checkTicks <= 0) return true;
        else {
            return !checkInterval.containsKey(uuid) || System.currentTimeMillis() - checkInterval.get(uuid) >= (50L * checkTicks); // 0.25s | 5ticks
        }
    }

    public void updateCheckTime(UUID uuid) {
        checkInterval.put(uuid, System.currentTimeMillis());
    }

    public void clear(UUID uuid) {
        triedTimes.remove(uuid);
        checkInterval.remove(uuid);
        selectingData.remove(uuid);
    }


    public void addPermission(Player player) {
        if (LuckPermsUtils.hasLuckPerms()) LuckPermsUtils.addPermission(player, PluginConfig.PERMISSION.get());
    }

    public void removePermission(Player player) {
        if (LuckPermsUtils.hasLuckPerms()) LuckPermsUtils.removePermission(player, PluginConfig.PERMISSION.get());
    }

}
