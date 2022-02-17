package cc.carm.plugin.regionprotection.listener;

import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import cc.carm.plugin.regionprotection.configuration.PluginMessages;
import cc.carm.plugin.regionprotection.configuration.values.ProtectedRegion;
import cc.carm.plugin.regionprotection.manager.PlayerManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Map;

import static cc.carm.plugin.regionprotection.RegionProtectionAPI.getRegionManager;
import static cc.carm.plugin.regionprotection.manager.PlayerManager.isPermitted;
import static cc.carm.plugin.regionprotection.manager.PlayerManager.updateCheckTime;

public class RegionListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerMoveEvent event) {
		if (event.isCancelled()) return; // 事件被其他插件取消

		Location toLocation = event.getTo();
		if (toLocation == null) return; // 无处可去 ~

		Player player = event.getPlayer();
		updateCheckTime(player.getUniqueId());
		if (isPermitted(player)) return; // 玩家已经通过授权或已满足条件

		Map.Entry<String, ProtectedRegion> regionIn = getRegionManager().getFirstRegionIn(toLocation);
		if (regionIn == null) return; // 不在任何区域内

		String regionName = regionIn.getKey();
		ProtectedRegion region = regionIn.getValue();

		event.setCancelled(true);

		PluginConfig.Sounds.NOT_PERMITTED.play(player);
		PluginMessages.NOT_PERMITTED.send(player, new Object[]{regionIn.getKey()});
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onMove(PlayerBucketEmptyEvent event) {
		if (event.isCancelled()) return; // 事件被其他插件取消

		Location toLocation = event.getBlock().getLocation();
		Player player = event.getPlayer();
		if (isPermitted(player)) return; // 玩家已经通过授权或已满足条件

		Map.Entry<String, ProtectedRegion> regionIn = getRegionManager().getFirstRegionIn(toLocation);
		if (regionIn != null) {
			event.setCancelled(true);
			PluginConfig.Sounds.NOT_PERMITTED.play(player);
			PluginMessages.NO_BUCKET.send(player, new Object[]{regionIn.getKey()});
		}
	}


	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		PlayerManager.clear(event.getPlayer().getUniqueId());
	}


}
