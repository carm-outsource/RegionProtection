package cc.carm.plugin.regionprotection.util;

import cc.carm.plugin.regionprotection.configuration.values.ProtectedRegion;
import org.bukkit.Location;

import java.util.function.BiConsumer;

public class LocationMathUtils {

	/**
	 * @param point  Player's point
	 * @param region Region
	 * @param after  Boolean (true = x, false = z)  Integer (value)
	 */
	public static void withMinDistance(Location point, ProtectedRegion region,
									   BiConsumer<Boolean, Integer> after) {
		double x = point.getX();
		double z = point.getZ();
		int x1 = region.getPos1().getX();
		int x2 = region.getPos2().getX();
		int z1 = region.getPos1().getZ();
		int z2 = region.getPos2().getZ();

		double d1 = Math.abs(x - x1);
		double d2 = Math.abs(x - x2);
		double d3 = Math.abs(z - z1);
		double d4 = Math.abs(z - z2);

		double min = Math.min(Math.min(d1, d2), Math.min(d3, d4));

		if (min == d1) {
			after.accept(true, x1);
		} else if (min == d2) {
			after.accept(true, x2);
		} else if (min == d3) {
			after.accept(false, z1);
		} else {
			after.accept(false, z2);
		}

	}

	public static int getCoordinateAdded(int distance, int line) {
		if (line < 0) {
			return line - distance;
		} else {
			return line + distance;
		}
	}

	public static Location getLocationAdded(Location base, boolean xz, int line, int distance) {
		int afterCoordinate = getCoordinateAdded(distance, line);

		Location location = base.clone();
		if (xz) {
			location.setX(afterCoordinate);
		} else {
			location.setZ(afterCoordinate);
		}
		
		if (location.getWorld() == null) {
			return location;
		}

		double y = location.getWorld().getHighestBlockYAt(location.getBlockX(), location.getBlockZ());
		location.setY(y);

		return location;
	}


}
