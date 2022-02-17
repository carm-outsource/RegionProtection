package cc.carm.plugin.regionprotection;

import cc.carm.lib.easyplugin.EasyPlugin;
import cc.carm.lib.easyplugin.i18n.EasyPluginMessageProvider;
import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import cc.carm.plugin.regionprotection.manager.ConfigManager;
import cc.carm.plugin.regionprotection.manager.RegionManager;

public class Main extends EasyPlugin {

	public Main() {
		super(new EasyPluginMessageProvider.zh_CN());
	}

	private static Main instance;
	private static ConfigManager configManager;
	private static RegionManager regionManager;

	@Override
	protected void load() {
		instance = this;
	}

	@Override
	public boolean initialize() {
		log("加载插件配置文件...");
		Main.configManager = new ConfigManager();
		if (!Main.configManager.initConfig()) {
			error("插件配置文件初始化失败，请检查文件权限。");
			return false;
		}

		log("加载区域配置文件...");
		Main.regionManager = new RegionManager();
		if (!Main.regionManager.initConfiguration()) {
			error("区域配置文件初始化失败，请检查文件权限。");
			return false;
		}


		return true;
	}


	@Override
	public boolean isDebugging() {
		return PluginConfig.DEBUG.get();
	}

	public static Main getInstance() {
		return instance;
	}

	public static ConfigManager getConfigManager() {
		return configManager;
	}

	public static void info(String... messages) {
		getInstance().log(messages);
	}

	public static void severe(String... messages) {
		getInstance().error(messages);
	}

	public static void debugging(String... messages) {
		getInstance().debug(messages);
	}

}