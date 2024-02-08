package net.aniby.fletchlore;

import org.bukkit.plugin.java.JavaPlugin;

public final class FletchLore extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new FListener(this), this);

    }

    @Override
    public void onDisable() {

    }
}
