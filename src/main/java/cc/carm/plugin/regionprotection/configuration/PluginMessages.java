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

    public static final EasyMessageList NOT_ONLINE = new EasyMessageList(
            new String[]{"&7玩家 &c%(player) &7并不在线。"},
            new String[]{"%(player)"}
    );

    public static class Command {
        public static class Usage {
            public static final EasyMessageList HEAD = new EasyMessageList(
                    "&c&l区域保护插件 &f使用帮助"
            );

            public static final EasyMessageList COMMON = new EasyMessageList(
                    "&8#&f list", "&8-&7 列出所有保护区域。"
            );

            public static final EasyMessageList SELECTION = new EasyMessageList(
                    "&8#&f select",
                    "&8-&7 开启选点模式，开启后可以手持金锄头进行选区。",
                    "&8#&f info",
                    "&8-&7 查看当前选区信息。",
                    "&8#&f set &c<区域名>",
                    "&8-&7 以当前选区设定一块区域为保护区域。",
                    "&8#&f remove &c<区域名>",
                    "&8-&7 移除指定名称的保护区域。"
            );

            public static final EasyMessageList PERMITTING = new EasyMessageList(
                    "&8#&f allow &c<玩家名>",
                    "&8-&7 给某位玩家添加配置文件中的权限。 ",
                    "&8#&f deny &c<玩家名>",
                    "&8-&7 给某位玩家移除配置文件中的权限。 "
            );

        }

        public static class List {
            public static final EasyMessageList HEAD = new EasyMessageList(
                    "&c&l区域保护插件 &f保护区域列表"
            );

            public static final EasyMessageList VALUE = new EasyMessageList(
                    new String[]{"&8# &c%(region_name)", "&8- &7位置 &f%(region_location)"},
                    new String[]{"%(region_name)", "%(region_location)"}
            );
            public static final EasyMessageList NOTHING = new EasyMessageList(
                    "&f当前还没有设置任何保护区域。"
            );

        }


        public static class Permitting {
            public static final EasyMessageList ALREADY_HAVE = new EasyMessageList(
                    new String[]{"&7玩家 &c%(player) &7已有授权。"},
                    new String[]{"%(player)"}
            );
            public static final EasyMessageList NOT_HAVE = new EasyMessageList(
                    new String[]{"&7玩家 &c%(player) &7还没有被授权。"},
                    new String[]{"%(player)"}
            );

            public static final EasyMessageList ALLOW = new EasyMessageList(
                    new String[]{"&7成功为玩家 &c%(player) &7添加授权。"},
                    new String[]{"%(player)"}
            );

            public static final EasyMessageList DENY = new EasyMessageList(
                    new String[]{"&f成功为玩家 &c%(player) &f添加授权。"},
                    new String[]{"%(player)"}
            );
        }

        public static class Set {
            public static final EasyMessageList ALREADY_EXIST = new EasyMessageList(
                    new String[]{"&f已经存在名为 &c%(region_name) &f的保护区域,若需修改请先移除同名区域。"},
                    new String[]{"%(region_name)"}
            );


            public static final EasyMessageList SUCCESS = new EasyMessageList(
                    new String[]{"&f成功添加名为 &c%(region_name) &f的保护区域，范围为 &c%(region_location) &f。"},
                    new String[]{"%(region_name)", "%(region_location)"}
            );

        }

        public static class Remove {
            public static final EasyMessageList NOT_EXIST = new EasyMessageList(
                    new String[]{"&f不存在名为 &c%(region_name) &f的保护区域。"},
                    new String[]{"%(region_name)"}
            );
            public static final EasyMessageList SUCCESS = new EasyMessageList(
                    new String[]{"&f成功移除名为 &c%(region_name) &f的保护区域。"},
                    new String[]{"%(region_name)"}
            );
        }
    }

    public static class SelectMode {
        public static final EasyMessageList ENABLING = new EasyMessageList(
                "&f成功为您启用区域选择模式，请在创造模式下手持金锄头左键/右键方块选择区域。");
        public static final EasyMessageList DISABLING = new EasyMessageList(
                "&f已为您关闭区域选择模式。");

        public static final EasyMessageList NO_SELECTED_REGION = new EasyMessageList(
                "&f您还未选择一个完整的区域，请先选择一个区域。");

        public static final EasyMessageList NOT_SELECTING = new EasyMessageList(
                "&f您当前并未处于区域选择模式，请先进入区域选择模式。"
        );

        public static final EasyMessageList DIFFERENT_WORLD = new EasyMessageList(
                "&f您选择的方块与已选择的其他点不在同一个世界，请重新选择！"
        );

        public static final EasyMessageList SELECTED_INFO = new EasyMessageList(
                new String[]{
                        "&8# &f当前已选择区域信息：",
                        "&8-&7 所在世界&f %(world)",
                        "&8-&7 点1&f %(pos1)",
                        "&8-&7 点2&f %(pos2)"
                },
                new String[]{"%(world)", "%(pos1)", "%(pos2)"}
        );

        public static final EasyMessageList SELECTED = new EasyMessageList(
                new String[]{
                        "&f成功选择点 &c%(id) &7-> &c%(location)"
                },
                new String[]{"%(id)", "%(location)"}
        );
    }

}
