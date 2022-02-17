package cc.carm.plugin.regionprotection.listener;

import cc.carm.plugin.regionprotection.Main;
import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import cc.carm.plugin.regionprotection.configuration.PluginMessages;
import cc.carm.plugin.regionprotection.configuration.values.ProtectedRegion;
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
		if (event.isCancelled()) return; // 事件被其他插件取消

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

		event.setCancelled(true);
		if (getPlayerManager().isTriedTimesExceeded(player.getUniqueId())) {
			// 尝试了很多次也没把他弹出去，直接传送出去

			LocationMathUtils.withMinDistance(event.getTo(), region, (xz, line) -> {
				Location location = LocationMathUtils.getLocationAdded(event.getTo(), xz, line, 2);
				player.teleport(location);

				Main.debugging("Teleporting " + player.getName() + " to " + location);
			});

		} else {
			// 尝试把玩家弹出区域
			Vector v = new Vector(
					toLocation.getX() - region.getCenterX(),
					1,
					toLocation.getZ() - region.getCenterZ()
			);
			player.setVelocity(v.normalize());
			getPlayerManager().addTriedTimes(player.getUniqueId()); // 添加一次尝试次数
			Main.debugging("Add velocity " + v + " to " + player.getName());
		}


	}

	@EventHandler
	public void onBukkitEmpty(PlayerBucketEmptyEvent event) {
		if (event.isCancelled()) return; // 事件被其他插件取消

		Location toLocation = event.getBlock().getLocation();
		Player player = event.getPlayer();
		if (getPlayerManager().isPermitted(player)) return; // 玩家已经通过授权或已满足条件

		Map.Entry<String, ProtectedRegion> regionIn = getRegionManager().getFirstRegionIn(toLocation);
		if (regionIn != null) {
			event.setCancelled(true);
			PluginConfig.Sounds.NOT_PERMITTED.play(player);
			PluginMessages.NO_BUCKET.send(player, new Object[]{regionIn.getKey()});
		}
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		getPlayerManager().clear(event.getPlayer().getUniqueId());
	}


}
