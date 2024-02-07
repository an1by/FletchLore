package net.aniby.fletchlore;

import net.kyori.adventure.inventory.Book;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FListener implements Listener {
    private final FletchLore plugin;

    public FListener(FletchLore plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();


        PlayerInventory inventory = player.getInventory();
        ItemStack mainHandItem = inventory.getItemInMainHand();

        if (block != null && block.getType() == Material.FLETCHING_TABLE && !mainHandItem.getType().isAir()) {
            AnvilInventory anvil_inventory = FletchAnvil.createinventory(player);
            player.openInventory(anvil_inventory);
        }
    }

    @EventHandler
    void onDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();

        if (!(inventory instanceof AnvilInventory anvil))
            return;

        Player player = (Player) event.getWhoClicked();

        if (event.getResult() != Event.Result.ALLOW)
            return;

        if (FletchAnvil.map.get(player) != anvil) {
            return;
        }

        if (event.getNewItems().containsKey(1) || event.getNewItems().containsKey(0)) {
            ItemStack result = updateresult(anvil);
            anvil.setResult(result);
        }
        else if (event.getNewItems().containsKey(2)) {
            player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
            FletchAnvil.map.remove(player);
        }

    }

    ItemStack updateresult(AnvilInventory inventory) {
        ItemStack second = inventory.getSecondItem();
        if (second == null || second.getType() != Material.WRITTEN_BOOK) {
            return null;
        }

        BookMeta second_meta = (BookMeta) second.getItemMeta();
        if (second_meta.getPageCount() != 1) {
            return null;
        }

        ItemStack first = inventory.getFirstItem();
        if (first == null) {
            return null;
        }

        ItemStack result = first.clone();
        ItemMeta first_meta = first.getItemMeta();
        first_meta.lore(List.of(second_meta.page(0)));
        result.setItemMeta(first_meta);
        return result;
    }
}
