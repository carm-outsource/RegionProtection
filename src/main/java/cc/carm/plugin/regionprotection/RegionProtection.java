package cc.carm.plugin.regionprotection;

import cc.carm.plugin.regionprotection.manager.ConfigManager;
import cc.carm.plugin.regionprotection.manager.PlayerManager;
import cc.carm.plugin.regionprotection.manager.RegionManager;

/**
 * 接口入口类，便于其他插件访问调用相关接口。
 */
public class RegionProtection {

    public static ConfigManager getPluginConfigManager() {
        return Main.configManager;
    }

    public static RegionManager getRegionManager() {
        return Main.regionManager;
    }

    public static PlayerManager getPlayerManager() {
        return Main.playerManager;
    }

}
