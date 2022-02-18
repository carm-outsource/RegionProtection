```text

 _____            _               _____           _            _   _             
|  __ \          (_)             |  __ \         | |          | | (_)            
| |__) |___  __ _ _  ___  _ __   | |__) | __ ___ | |_ ___  ___| |_ _  ___  _ __  
|  _  // _ \/ _` | |/ _ \| '_ \  |  ___/ '__/ _ \| __/ _ \/ __| __| |/ _ \| '_ \ 
| | \ \  __/ (_| | | (_) | | | | | |   | | | (_) | ||  __/ (__| |_| | (_) | | | |
|_|  \_\___|\__, |_|\___/|_| |_| |_|   |_|  \___/ \__\___|\___|\__|_|\___/|_| |_|
             __/ |                                                               
            |___/                                                                
```

# RegionProtection

[![workflow](https://github.com/CarmJos/RegionProtection/actions/workflows/maven.yml/badge.svg?branch=master)](https://github.com/CarmJos/RegionProtection/actions/workflows/maven.yml)
![Support](https://img.shields.io/badge/Minecraft-Java%201.13--Latest-green)
![](https://visitor-badge.glitch.me/badge?page_id=RegionProtection.readme)

区域保护插件，将不符合条件的玩家弹出区域，基于EasyPlugin实现。

## 插件依赖

- **[必须]** 插件本体基于 [Spigot-API](https://hub.spigotmc.org/stash/projects/SPIGOT) 、 [BukkitAPI](http://bukkit.org/) 实现。
- **[自带]** 插件功能基于 [EasyPlugin](https://github.com/CarmJos/EasyPlugin) 实现。
-

详细依赖列表可见 [Dependencies](https://github.com/CarmJos/RegionProtection/network/dependencies) 。

## 插件指令

指令主指令为 /RegionProtection

```text
# list
@ 管理指令 (RegionProtection.admin)
- 列出所有保护区域。

# allow <玩家名>
@ 管理指令 (RegionProtection.admin)
- 给某位玩家添加配置文件中的权限。 
- 该指令通过 LuckPerms 实现，须安装 LuckPerms 插件。

# deny <玩家名>
@ 管理指令 (RegionProtection.admin)
- 给某位玩家移除配置文件中的权限。 
- 该指令通过 LuckPerms 实现，须安装 LuckPerms 插件。

# select
@ 非后台管理指令 (RegionProtection.admin)
- 开启选点模式，开启后可以手持金锄头进行选区。

# info
@ 非后台管理指令 (RegionProtection.admin)
- 查看当前选区信息。

# set <区域名>
@ 非后台管理指令 (RegionProtection.admin)
- 以当前选区设定一块区域为保护区域。

# remove <区域名>
@ 非后台管理指令 (RegionProtection.admin)
- 移除指定名称的保护区域。

# reload
@ 管理指令 (RegionProtection.admin)
- 重载插件配置文件。
```

## 插件权限

```text

# RegionProtection.admin
- 建筑魔杖的管理权限。

```

## 如何选区

- 输入 `/rp select` 开启选点模式
- **创造模式**下手持**金锄头** 对准方块按下鼠标 **左键**/**右键** 进行选点
- 选点完成后输入 `/rp set <区域名>` 设定区域
- 设定结束后，再次输入 `/rp select` 关闭选点模式，金锄头将恢复原版功能。

## 配置文件

### 插件配置文件 ([config.yml](src/main/resources/config.yml))

详见源文件。

### 消息配置文件 ([messages.yml](src/main/java/cc/carm/plugin/regionprotection/configuration/PluginMessages.java))

详见代码源文件，将在首次启动时生成配置。

## 支持与捐赠

若您觉得本插件做的不错，您可以捐赠支持我！

<img height=25% width=25% src="https://raw.githubusercontent.com/CarmJos/CarmJos/main/img/donate-code.jpg"  alt=""/>