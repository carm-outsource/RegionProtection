package cc.carm.plugin.regionprotection;

import cc.carm.plugin.regionprotection.manager.ConfigManager;
import cc.carm.plugin.regionprotection.manager.RegionManager;

public class RegionProtectionAPI {

	public static ConfigManager getPluginConfigManager() {
		return Main.getConfigManager();
	}

	public static RegionManager getRegionManager() {
		return Main.getRegionManager();
	}


}
