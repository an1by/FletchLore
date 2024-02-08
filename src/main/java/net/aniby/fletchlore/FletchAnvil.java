package net.aniby.fletchlore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class FletchAnvil {
    public static final HashMap<Player, Inventory> map = new HashMap<>();

    public static Inventory createInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.ANVIL);
        map.put(player, inventory);
        return inventory;
    }
}
