package net.aniby.fletchlore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class FletchAnvil {
    public static final HashMap<Player, AnvilInventory> map = new HashMap<>();
    public static AnvilInventory createinventory(Player player){
        AnvilInventory inventory = (AnvilInventory) Bukkit.createInventory(null, InventoryType.ANVIL);
        map.put(player, inventory);
        return inventory;
    }

 }
