package cc.carm.plugin.regionprotection.manager;

import cc.carm.lib.easyplugin.configuration.file.FileConfig;
import cc.carm.plugin.regionprotection.Main;
import cc.carm.plugin.regionprotection.configuration.values.ProtectedRegion;
import com.google.common.collect.ImmutableMap;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RegionManager {


	private FileConfig regionsConfiguration;
	Map<String, ProtectedRegion> regions = new HashMap<>();

	public boolean initConfiguration() {
		try {
			regionsConfiguration = new FileConfig(Main.getInstance(), "regions.yml");
			loadRegions();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public void loadRegions() {
		ConfigurationSection section = getRegionsConfiguration().getConfig().getConfigurationSection("regions");
		if (section == null) return;
		Map<String, ProtectedRegion> regions = new HashMap<>();

		for (String regionName : section.getKeys(false)) {
			ProtectedRegion region = section.getSerializable(regionName, ProtectedRegion.class);
			if (region != null) regions.put(regionName, region);
		}

		this.regions = regions;
	}


	@Unmodifiable
	public @NotNull Map<String, ProtectedRegion> getRegions() {
		return ImmutableMap.copyOf(this.regions);
	}

	public void setRegion(@NotNull String name, @NotNull ProtectedRegion area) {
		regions.put(name, area);
		getRegionsConfiguration().getConfig().set("regions." + name, area);
		saveConfig();
	}

	public void removeRegion(@NotNull String name) {
		regions.remove(name);
		getRegionsConfiguration().getConfig().set("regions." + name, null);
	}

	@Unmodifiable
	public @NotNull Set<Map.Entry<String, ProtectedRegion>> getRegionsIn(@NotNull Location location) {
		return getRegions().entrySet().stream()
				.filter(entry -> entry.getValue().isInArea(location))
				.collect(Collectors.toUnmodifiableSet());
	}

	public @Nullable Map.Entry<String, ProtectedRegion> getFirstRegionIn(@NotNull Location location) {
		return getRegionsIn(location).stream().findFirst().orElse(null);
	}

	public FileConfig getRegionsConfiguration() {
		return regionsConfiguration;
	}

	public void saveConfig() {
		try {
			getRegionsConfiguration().save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
