package cc.carm.plugin.regionprotection.configuration.values;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("cc.carm.config.ProtectedRegion")
public class ProtectedRegion implements ConfigurationSerializable {

	@Nullable String world;
	@NotNull DataBlockLocation pos1;
	@NotNull DataBlockLocation pos2;

	public ProtectedRegion(@Nullable String world, @NotNull DataBlockLocation pos1, @NotNull DataBlockLocation pos2) {
		this.world = world;
		this.pos1 = pos1;
		this.pos2 = pos2;
	}

	public ProtectedRegion(@NotNull DataBlockLocation pos1, @NotNull DataBlockLocation pos2) {
		this(null, pos1, pos2);
	}

	public @Nullable String getWorld() {
		return world;
	}

	public void setWorld(@Nullable String world) {
		this.world = world;
	}

	public void setWorld(@Nullable World world) {
		setWorld(world == null ? null : world.getName());
	}

	public @NotNull DataBlockLocation getPos1() {
		return pos1;
	}

	public void setPos1(@NotNull DataBlockLocation pos1) {
		this.pos1 = pos1;
	}

	public @NotNull DataBlockLocation getPos2() {
		return pos2;
	}

	public void setPos2(@NotNull DataBlockLocation pos2) {
		this.pos2 = pos2;
	}

	public double getCenterX() {
		return (pos1.getX() + pos2.getX()) / 2D;
	}

	public double getCenterZ() {
		return (pos1.getZ() + pos2.getZ()) / 2D;
	}

	public boolean isInArea(Location location) {
		return (getWorld() == null || (location.getWorld() != null && location.getWorld().getName().equalsIgnoreCase(getWorld())))
				&& isNumberInRange((int) location.getX(), getPos1().getX(), getPos2().getX())
				&& isNumberInRange((int) location.getY(), getPos1().getY(), getPos2().getY())
				&& isNumberInRange((int) location.getZ(), getPos1().getZ(), getPos2().getZ());
	}

	public boolean isInArea(Player player) {
		return isInArea(player.getLocation());
	}

	@Override
	public @NotNull Map<String, Object> serialize() {
		Map<String, Object> data = new HashMap<>();
		if (world != null) data.put("world", getWorld());
		data.put("pos1", getPos1());
		data.put("pos2", getPos2());

		return data;
	}

	public static ProtectedRegion deserialize(Map<String, Object> args) {
		String world = args.containsKey("world") ? (String) args.get("world") : null;
		if (args.containsKey("pos1") && args.containsKey("pos2")) {
			return new ProtectedRegion(world, (DataBlockLocation) args.get("pos1"), (DataBlockLocation) args.get("pos2"));
		}
		return null;
	}

	public String serializeToText() {
		return world + "@(" + pos1.serializeToText() + "|" + pos2.serializeToText() + ")";
	}

	public static ProtectedRegion deserializeText(String s) {
		if (s == null || !s.contains("|") || !s.contains(";")) return null;
		String[] args = StringUtils.split(s, "|");
		if (args.length != 2) return null;
		try {
			DataBlockLocation pos1 = DataBlockLocation.deserializeText(args[0]);
			DataBlockLocation pos2 = DataBlockLocation.deserializeText(args[1]);
			return new ProtectedRegion(pos1, pos2);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public String toString() {
		return serializeToText();
	}

	private static boolean isNumberInRange(double source, double numberA, double numberB) {
		return Math.min(numberA, numberB) <= source && Math.max(numberA, numberB) >= source;
	}
}
