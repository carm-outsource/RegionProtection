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

    public static final ConfigValue<Integer> PLAY_TIME = new ConfigValue<>(
            "play-time", Integer.class, 1440
    );

    public static final ConfigValue<Integer> CHECK_INTERVAL = new ConfigValue<>(
            "check-interval", Integer.class, 5
    );


    public static class Sounds {

        public static ConfigSound NOT_PERMITTED = new ConfigSound(
                "sounds.not-permitted", Sound.ENTITY_VILLAGER_NO
        );

    }
}
