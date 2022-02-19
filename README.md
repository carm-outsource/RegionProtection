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

本插件由 [重庆溢鹏多赛科技有限公司](https://ypchongqing.com) 请求本人开发，经过授权后开源。

## 插件依赖

- **[必须]** 插件本体基于 [Spigot-API](https://hub.spigotmc.org/stash/projects/SPIGOT) 、 [BukkitAPI](http://bukkit.org/) 实现。
- **[自带]** 插件功能基于 [EasyPlugin](https://github.com/CarmJos/EasyPlugin) 实现。
- **[推荐]** 授权功能基于 [LuckPerms](https://luckperms.net/) 实现。
 
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

## 开源协议

本项目源码采用 [GNU General Public License v3.0](https://opensource.org/licenses/GPL-3.0) 开源协议。

<details>
<summary>关于 GPL 协议</summary>

> GNU General Public Licence (GPL) 有可能是开源界最常用的许可模式。GPL 保证了所有开发者的权利，同时为使用者提供了足够的复制，分发，修改的权利：
>
> #### 可自由复制
> 你可以将软件复制到你的电脑，你客户的电脑，或者任何地方。复制份数没有任何限制。
> #### 可自由分发
> 在你的网站提供下载，拷贝到U盘送人，或者将源代码打印出来从窗户扔出去（环保起见，请别这样做）。
> #### 可以用来盈利
> 你可以在分发软件的时候收费，但你必须在收费前向你的客户提供该软件的 GNU GPL 许可协议，以便让他们知道，他们可以从别的渠道免费得到这份软件，以及你收费的理由。
> #### 可自由修改
> 如果你想添加或删除某个功能，没问题，如果你想在别的项目中使用部分代码，也没问题，唯一的要求是，使用了这段代码的项目也必须使用 GPL 协议。
>
> 需要注意的是，分发的时候，需要明确提供源代码和二进制文件，另外，用于某些程序的某些协议有一些问题和限制，你可以看一下 @PierreJoye 写的 Practical Guide to GPL Compliance 一文。使用 GPL 协议，你必须在源代码代码中包含相应信息，以及协议本身。
>
> *以上文字来自 [五种开源协议GPL,LGPL,BSD,MIT,Apache](https://www.oschina.net/question/54100_9455) 。*
</details>
