package cc.sakurua.itemcommands;

import cc.sakurua.itemcommands.listeners.ItemInteract;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;

public final class ItemCommands extends JavaPlugin {

    public static HashMap<String, HashMap<String, String>> menu = new HashMap<>();

    @Override
    public void onEnable() {
        // 加载配置文件
        loadConfig();
        // 注册事件
        getServer().getPluginManager().registerEvents(new ItemInteract(), this);
        getLogger().info("加载了" + menu.size() + "个物品");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        // 自动检测是否存在ItemCommands/config.yml
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        ConfigurationSection configSection = config.getConfigurationSection("items");
        Set<String> items;

        if (configSection != null) {
            items = configSection.getKeys(false);
            for (String item : items) {
                HashMap<String, String> fields = new HashMap<>();
                ConfigurationSection menuItem = config.getConfigurationSection("items." + item);
                if (menuItem != null) {
                    fields.put("type", menuItem.getString("type"));
                    fields.put("name", menuItem.getString("name"));
                    fields.put("commands", menuItem.getString("commands"));
                }
                menu.put(item, fields);
            }
        }
    }
}
