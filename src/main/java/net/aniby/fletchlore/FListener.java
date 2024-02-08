package net.aniby.fletchlore;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

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
            player.openInventory(FletchAnvil.createInventory(player));
        }
    }

    @EventHandler
    void onDrag(InventoryDragEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory.getType() != InventoryType.ANVIL)
            return;

        Player player = (Player) event.getWhoClicked();

        if (event.getResult() != Event.Result.ALLOW)
            return;

        if (FletchAnvil.map.get(player) != inventory)
            return;

        Map<Integer, ItemStack> items = event.getNewItems();
        if (items.containsKey(1) || items.containsKey(0)) {
            ItemStack result = updateResult(inventory);
            inventory.setItem(2, result);
        } else if (items.containsKey(2)) {
            player.closeInventory(InventoryCloseEvent.Reason.PLUGIN);
            FletchAnvil.map.remove(player);
        }
    }

    ItemStack updateResult(Inventory inventory) {
        ItemStack second = inventory.getItem(1);
        if (second == null || second.getType() != Material.WRITTEN_BOOK)
            return null;
        BookMeta secondMeta = (BookMeta) second.getItemMeta();
        if (secondMeta.getPageCount() != 1)
            return null;

        ItemStack first = inventory.getItem(0);
        if (first == null)
            return null;
        ItemStack result = first.clone();

        ItemMeta firstMeta = first.getItemMeta();
        firstMeta.lore(List.of(secondMeta.page(0)));
        result.setItemMeta(firstMeta);
        return result;
    }
}
