package cc.carm.plugin.regionprotection.model;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SerializableAs("cc.carm.config.DataBlockLocation")
public class DataBlockLocation implements ConfigurationSerializable {

    private int x;
    private int y;
    private int z;

    public DataBlockLocation(Location location) {
        this(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public DataBlockLocation(final int x, final int y, final int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static DataBlockLocation deserialize(Map<String, Object> args) {
        return new DataBlockLocation(NumberConversions.toInt(args.get("x")),
                NumberConversions.toInt(args.get("y")),
                NumberConversions.toInt(args.get("z")));
    }

    public static DataBlockLocation deserializeText(String s) {
        if (s == null || !s.contains(";")) return null;
        String[] args = StringUtils.split(s, ";");
        if (args.length != 3) return null;
        try {
            int x = NumberConversions.toInt(args[0]);
            int y = NumberConversions.toInt(args[1]);
            int z = NumberConversions.toInt(args[2]);
            return new DataBlockLocation(x, y, z);
        } catch (Exception ex) {
            return null;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Location getBukkitLocation(World world) {
        return new Location(world, getX(), getY(), getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataBlockLocation location = (DataBlockLocation) o;
        return x == location.x && y == location.y && z == location.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();

        data.put("x", this.x);
        data.put("y", this.y);
        data.put("z", this.z);

        return data;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }

    public String serializeToText() {
        return getX() + ";" + getY() + ";" + getZ();
    }
}