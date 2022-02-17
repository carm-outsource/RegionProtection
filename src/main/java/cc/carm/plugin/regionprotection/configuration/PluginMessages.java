package cc.carm.plugin.regionprotection.configuration;

import cc.carm.lib.easyplugin.configuration.language.EasyMessageList;
import cc.carm.lib.easyplugin.configuration.language.MessagesRoot;


public class PluginMessages extends MessagesRoot {

	public static final EasyMessageList NO_BUCKET = new EasyMessageList(
			new String[]{"&f抱歉，您暂时未满足在区域 &c%(region_name) &f中放置流体的条件。"},
			new String[]{"%(region_name)"}
	);

	public static final EasyMessageList NOT_PERMITTED = new EasyMessageList(
			new String[]{"&f抱歉，您暂时未满足进入区域 &c%(region_name) &f的条件。"},
			new String[]{"%(region_name)"}
	);

}
