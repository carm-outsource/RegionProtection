package cc.carm.plugin.regionprotection;

import cc.carm.lib.easyplugin.EasyPlugin;
import cc.carm.lib.easyplugin.i18n.EasyPluginMessageProvider;
import cc.carm.plugin.regionprotection.command.RegionProtectCommands;
import cc.carm.plugin.regionprotection.configuration.PluginConfig;
import cc.carm.plugin.regionprotection.configuration.values.DataBlockLocation;
import cc.carm.plugin.regionprotection.configuration.values.ProtectedRegion;
import cc.carm.plugin.regionprotection.listener.RegionListener;
import cc.carm.plugin.regionprotection.manager.ConfigManager;
import cc.carm.plugin.regionprotection.manager.PlayerManager;
import cc.carm.plugin.regionprotection.manager.RegionManager;

public class Main extends EasyPlugin {

	public Main() {
		super(new EasyPluginMessageProvider.zh_CN());
	}

	private static Main instance;
	protected static ConfigManager configManager;
	protected static RegionManager regionManager;
	protected static PlayerManager playerManager;

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

		log("加载区域管理器...");
		Main.regionManager = new RegionManager();
		if (!Main.regionManager.initConfiguration()) {
			error("区域配置文件初始化失败，请检查文件权限。");
			return false;
		}

		log("加载玩家管理器...");
		Main.playerManager = new PlayerManager();

		log("注册监听器...");
		regListener(new RegionListener());

		log("注册指令...");
		registerCommand("RegionProtection", new RegionProtectCommands());

		//测试
		RegionProtection.getRegionManager().setRegion("test", new ProtectedRegion(
				"world",
				new DataBlockLocation(-16, 50, -16),
				new DataBlockLocation(16, 60, 16)
		));

		return true;
	}

	@Override
	public boolean isDebugging() {
		return PluginConfig.DEBUG.get();
	}

	public static Main getInstance() {
		return instance;
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
