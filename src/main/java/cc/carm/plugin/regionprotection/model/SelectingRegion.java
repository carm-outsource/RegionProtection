package cc.carm.plugin.regionprotection.model;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectingRegion {

    @NotNull String world;
    @Nullable DataXZLocation pos1;
    @Nullable DataXZLocation pos2;

    public SelectingRegion(@NotNull String world, @Nullable DataXZLocation pos1, @Nullable DataXZLocation pos2) {
        this.world = world;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public SelectingRegion(@NotNull String world) {
        this(world, null, null);
    }

    public @NotNull String getWorld() {
        return world;
    }

    public void setWorld(@NotNull String world) {
        this.world = world;
    }

    public void setWorld(@NotNull World world) {
        setWorld(world.getName());
    }

    public @Nullable DataXZLocation getPos1() {
        return pos1;
    }

    public void setPos1(@NotNull DataXZLocation pos1) {
        this.pos1 = pos1;
    }

    public @Nullable DataXZLocation getPos2() {
        return pos2;
    }

    public void setPos2(@NotNull DataXZLocation pos2) {
        this.pos2 = pos2;
    }

    public String serializeToText() {
        return world + "@(" + (pos1 == null ? "?" : pos1.serializeToText()) + "|" + (pos2 == null ? "?" : pos2.serializeToText()) + ")";
    }

    @Override
    public String toString() {
        return serializeToText();
    }
}
