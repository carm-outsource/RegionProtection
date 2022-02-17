package cc.carm.plugin.regionprotection.configuration;

import cc.carm.lib.easyplugin.configuration.impl.ConfigSound;
import cc.carm.lib.easyplugin.configuration.values.ConfigValue;
import org.bukkit.Sound;

public class PluginConfig {

	public static final ConfigValue<Boolean> DEBUG = new ConfigValue<>(
			"debug", Boolean.class, false
	);

	public static final ConfigValue<String> PERMISSION = new ConfigValue<>(
			"permission", String.class, "RegionProtection.override"
	);

	public static final ConfigValue<Long> PLAY_TIME = new ConfigValue<>(
			"play-time", Long.class, 1440L
	);


	public static class Sounds {

		public static ConfigSound NOT_PERMITTED = new ConfigSound(
				"sounds.not-permitted", Sound.ENTITY_VILLAGER_NO
		);

	}
}
