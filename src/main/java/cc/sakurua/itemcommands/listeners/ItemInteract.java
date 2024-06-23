package cc.sakurua.itemcommands.listeners;

import cc.sakurua.itemcommands.ItemCommands;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class ItemInteract implements Listener {

    @EventHandler
    public void onPlayerRightClickItem(PlayerInteractEvent event){
        Player player = event.getPlayer();
        for (Entry<String, HashMap<String, String>> entry : ItemCommands.menu.entrySet()) {
            HashMap<String, String> value = entry.getValue();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            if (itemInMainHand.getType().equals(Material.getMaterial(value.get("type")))) {
                if (itemInMainHand.getItemMeta().hasDisplayName()) {
                    String name = ChatColor.translateAlternateColorCodes('&', value.get("name"));
                    if (itemInMainHand.getItemMeta().getDisplayName().equals(name)) {
                        if (event.getAction().isRightClick()) {
                            player.performCommand(value.get("commands"));
                        }
                    }
                }
            }
        }
    }
}
