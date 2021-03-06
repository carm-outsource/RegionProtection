package cc.carm.plugin.regionprotection.model;

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
    @NotNull DataXZLocation pos1;
    @NotNull DataXZLocation pos2;

    public ProtectedRegion(@Nullable String world, @NotNull DataXZLocation pos1, @NotNull DataXZLocation pos2) {
        this.world = world;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public ProtectedRegion(@NotNull DataXZLocation pos1, @NotNull DataXZLocation pos2) {
        this(null, pos1, pos2);
    }

    public static ProtectedRegion deserialize(Map<String, Object> args) {
        String world = args.containsKey("world") ? (String) args.get("world") : null;
        if (args.containsKey("pos1") && args.containsKey("pos2")) {
            return new ProtectedRegion(world, (DataXZLocation) args.get("pos1"), (DataXZLocation) args.get("pos2"));
        }
        return null;
    }

    private static boolean isNumberInRange(double source, double numberA, double numberB) {
        return Math.min(numberA, numberB) <= source && Math.max(numberA, numberB) >= source;
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

    public @NotNull DataXZLocation getPos1() {
        return pos1;
    }

    public void setPos1(@NotNull DataXZLocation pos1) {
        this.pos1 = pos1;
    }

    public @NotNull DataXZLocation getPos2() {
        return pos2;
    }

    public void setPos2(@NotNull DataXZLocation pos2) {
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

    public String serializeToText() {
        return world + "@(" + pos1.serializeToText() + "|" + pos2.serializeToText() + ")";
    }

    @Override
    public String toString() {
        return serializeToText();
    }
}
