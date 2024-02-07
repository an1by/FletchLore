package net.aniby.fletchlore;

import net.kyori.adventure.inventory.Book;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FListener implements Listener {
    private final FletchLore plugin;
    public FListener(FletchLore plugin) {
        this.plugin = plugin;
    }

    List<Player> changeMap = new ArrayList<>();

    @EventHandler
    void onInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        if (block != null && block.getType() == Material.FLETCHING_TABLE && !mainHandItem.getType().isAir()) {
            ItemStack item = new ItemStack(Material.WRITABLE_BOOK);
            changeMap.add(player);
            player.openBook(item);
        }
    }

    static void createInventory()

    public static HashMap<Player, AnvilInventory> map = new HashMap<>();

    @EventHandler
    public void da(PrepareAnvilEvent event) {
        event.getInventory();
    }

    public static void da(Player player, JavaPlugin plugin) {
        AnvilInventory inventory = (AnvilInventory) Bukkit.createInventory(player, InventoryType.ANVIL);
//        inventory.
    }
}
