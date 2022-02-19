package cc.carm.plugin.regionprotection.model;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SerializableAs("cc.carm.config.DataXZLocation")
public class DataXZLocation implements ConfigurationSerializable {

    private int x;
    private int z;

    public DataXZLocation(Location location) {
        this(location.getBlockX(), location.getBlockZ());
    }

    public DataXZLocation(final int x, final int z) {
        this.x = x;
        this.z = z;
    }

    public static DataXZLocation deserialize(Map<String, Object> args) {
        return new DataXZLocation(
                NumberConversions.toInt(args.get("x")),
                NumberConversions.toInt(args.get("z"))
        );
    }

    public static DataXZLocation deserializeText(String s) {
        if (s == null || !s.contains(";")) return null;
        String[] args = StringUtils.split(s, ";");
        if (args.length != 2) return null;
        try {
            int x = NumberConversions.toInt(args[0]);
            int z = NumberConversions.toInt(args[2]);
            return new DataXZLocation(x, z);
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataXZLocation location = (DataXZLocation) o;
        return x == location.x && z == location.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, z);
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        Map<String, Object> data = new HashMap<>();
        data.put("x", this.x);
        data.put("z", this.z);
        return data;
    }

    @Override
    public String toString() {
        return x + ", " + z;
    }

    public String serializeToText() {
        return getX() + ";" + getZ();
    }
}