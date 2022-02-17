package cc.carm.plugin.regionprotection.manager;

import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class PlayerManager {

	public boolean isPermitted(Player player) {
		return player.hasPermission(PluginConfig.PERMISSION.get()) ||
				getPlayMinutes(player) >= PluginConfig.PLAY_TIME.get();
	}

	public long getPlayMinutes(Player player) {
		return player.getStatistic(Statistic.PLAY_ONE_MINUTE);
	}

	public void rejectPlayer() {

	}

}
