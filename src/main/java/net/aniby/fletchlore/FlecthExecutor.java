package net.aniby.fletchlore;

import net.kyori.adventure.inventory.Book;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;

public class FlecthExecutor {
    public static HashMap<Player, AnvilInventory> map = new HashMap<>();

    @EventHandler
    public void interact(PlayerInteractEvent event) {

    }

    @EventHandler
    public void da(PrepareAnvilEvent event) {
        event.getInventory();
    }

    public static void da(Player player, JavaPlugin plugin) {
        AnvilInventory inventory = (AnvilInventory) Bukkit.createInventory(player, InventoryType.ANVIL);
//        inventory.
    }
}
