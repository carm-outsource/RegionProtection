package cc.carm.plugin.regionprotection.manager;

import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

	public Map<UUID, Integer> triedTimes = new HashMap<>();

	public Map<UUID, Long> checkInterval = new HashMap<>();

	public boolean isPermitted(Player player) {
		return player.hasPermission("RegionProtection.admin")
				|| player.hasPermission(PluginConfig.PERMISSION.get())
				|| getPlayMinutes(player) >= PluginConfig.PLAY_TIME.get();
	}

	public long getPlayMinutes(Player player) {
		return player.getStatistic(Statistic.PLAY_ONE_MINUTE);
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
		return triedTimes.containsKey(uuid) && triedTimes.get(uuid) >= 5;
	}

	public boolean shouldCheck(UUID uuid) {
		return !checkInterval.containsKey(uuid) || System.currentTimeMillis() - checkInterval.get(uuid) >= 500; // 0.5s
	}

	public void updateCheckTime(UUID uuid) {
		checkInterval.put(uuid, System.currentTimeMillis());
	}

	public void clear(UUID uuid) {
		triedTimes.remove(uuid);
		checkInterval.remove(uuid);
	}


}
