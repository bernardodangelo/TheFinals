package com.bernardo;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class season9 extends JavaPlugin implements Listener {
    private Random random = new Random();

    @Override
    public void onEnable() {
        getLogger().info("Season 9 Plugin Iniciado.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Season 9 Plugin Finalizado.");
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.PIG) {
            if (random.nextDouble() <= 0.005) {
                event.getEntity().setCustomName("Technoblade");
                event.getEntity().setCustomNameVisible(true);
            }
        }
    }
    @EventHandler
    public void onCreatureSpawnNatural(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.PIG && event.getSpawnReason() == SpawnReason.NATURAL) {
            if (random.nextDouble() <= 0.005) {
                event.getEntity().setCustomName("Technoblade");
                event.getEntity().setCustomNameVisible(true);
            }
        }
    }
}

