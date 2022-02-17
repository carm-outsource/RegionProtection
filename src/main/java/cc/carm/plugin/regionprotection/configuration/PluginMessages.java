package cc.carm.plugin.regionprotection.configuration;

import cc.carm.lib.easyplugin.configuration.language.EasyMessageList;
import cc.carm.lib.easyplugin.configuration.language.MessagesRoot;
import cc.carm.lib.easyplugin.configuration.language.MessagesSection;


public class PluginMessages extends MessagesRoot {

	public static final EasyMessageList NO_PERMISSION = new EasyMessageList(
			"&f抱歉，您还没有学会该魔法的使用，请您学习后再试！"
	);

	public static final EasyMessageList POSITION_SELECTED = new EasyMessageList(
			new String[]{"&f您成功选择 &e点#%(index)&8(%(location)) &f。"},
			new String[]{"%(index)", "%(location)"}
	);

	public static final EasyMessageList POSITION_CLEARED = new EasyMessageList(
			"&f已为您清除之前的选区。"
	);

	public static final EasyMessageList NOT_TRUSTED_BLOCK = new EasyMessageList(
			"&f您没有所选方块的操作权限。"
	);

	public static final EasyMessageList NOT_ALLOWED_BLOCK = new EasyMessageList(
			"&f您选择的物品不是一个方块或不可以用于魔杖使用。"
	);

	@MessagesSection("place-wand")
	public static class PlaceWand {

		public static final EasyMessageList PUT_BLOCKS = new EasyMessageList("",
				"&f请您先 &eShift+鼠标右键 &f放入一些方块后再试！"
		);

		public static final EasyMessageList NO_ENOUGH_BLOCKS = new EasyMessageList(
				new String[]{"&c&l施法失败！&f您仍需要 &e%(need) &f个方块用于建造，请 &eShift+右键空气 &f放入足够的方块！"},
				new String[]{"%(need)"}
		);

		public static final EasyMessageList NO_SELECT_BLOCK_INV = new EasyMessageList(
				"&f背包中没有您选定的方块，请放入指定方块后再试。"
		);

		public static final EasyMessageList NO_SELECT_BLOCK = new EasyMessageList(
				"&f容器中没有您选定的方块，请放入指定方块后再试。"
		);

		public static final EasyMessageList NO_REMAIN_BLOCK = new EasyMessageList(
				"&f魔杖内已经没有剩余的方块了！"
		);

		public static final EasyMessageList TAKE_SUCCESS = new EasyMessageList(
				new String[]{"&f成功从容器中取出 &e%(amount) &f个方块放入魔杖，魔杖当前有 &e%(remain) &f个方块。"},
				new String[]{"%(amount)", "%(remain)"}
		);

		public static final EasyMessageList TAKE_SUCCESS_INV = new EasyMessageList(
				new String[]{"&f成功从背包中取出 &e%(amount) &f个方块放入魔杖，魔杖当前有 &e%(remain) &f个方块。"},
				new String[]{"%(amount)", "%(remain)"}
		);

		public static final EasyMessageList PUT_SUCCESS = new EasyMessageList(
				new String[]{"&f成功将魔杖中的 &e%(amount) &f个方块放入容器，魔杖剩余 &e%(remain) &f个方块。"},
				new String[]{"%(amount)", "%(remain)"}
		);

		public static final EasyMessageList PICK_SUCCESS = new EasyMessageList(
				new String[]{"&f成功将魔杖中的 &e%(amount) &f个方块放入背包，魔杖剩余 &e%(remain) &f个方块。"},
				new String[]{"%(amount)", "%(remain)"}
		);

		public static final EasyMessageList PLACE_SUCCESS = new EasyMessageList(
				new String[]{"&a&l施法成功！&f共有 &e%(changes) &f个方块被放置，魔杖内剩余 &e%(remain) &f个方块。"},
				new String[]{"%(changes)", "%(remain)"}
		);

		public static final EasyMessageList NO_CHANGES = new EasyMessageList(
				"&f没有任何可被替换的方块，因此没有发生任何变化。",
				"&7注意：放置魔杖只会作用于空气格！"
		);

		public static final EasyMessageList NO_SPACE = new EasyMessageList(
				"&f您没有足够的背包空间取出物品！"
		);
	}

	@MessagesSection("remove-wand")
	public static class RemoveWand {

		public static final EasyMessageList SELECT_BLOCK = new EasyMessageList("",
				"&f请您至少选择一个方块类型后再操作！"
		);

		public static final EasyMessageList REMOVE_SUCCESS = new EasyMessageList(
				new String[]{"&a&l施法成功！&f共有 &e%(changes) &f个方块被移除。"},
				new String[]{"%(changes)"}
		);
		public static final EasyMessageList NO_CHANGES = new EasyMessageList(
				"&f没有任何可被替换的方块，因此没有发生任何变化。"
		);

	}

	public static class Results {

		public static final EasyMessageList SUCCESS = new EasyMessageList(
				"&a&l操作成功！"
		);

		public static final EasyMessageList DIFFERENT_WORLD = new EasyMessageList(
				"&c&l无法施法！&f您选择的两个点不在同个世界！"
		);

		public static final EasyMessageList DIFFERENT_RESIDENCE = new EasyMessageList(
				"&c&l无法施法！&f您选择的两个点不在同个领地！"
		);

		public static final EasyMessageList NOT_TRUSTED = new EasyMessageList(
				"&c&l无法施法！&f您在选择的区域中没有建筑权限！"
		);

		public static final EasyMessageList OVER_LIMIT = new EasyMessageList(
				"&c&l无法施法！&f选取的方块数量超过了单次操作的上限！"
		);

	}


}
