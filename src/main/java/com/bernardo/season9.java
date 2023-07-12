package com.bernardo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Random;

public final class season9 extends JavaPlugin implements Listener {
    private Random random = new Random();
    private String message1 = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "]" +
            ChatColor.RED + " Regras do Servidor: " +
            ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "discord.gg/kZQKjBe";

    private String message2 = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "]" + ChatColor.AQUA + " Entre no Discord da Live: " +
            ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "discord.gg/kZQKjBe";

    @Override
    public void onEnable() {
        getLogger().info("Season 9 Plugin Iniciado.");
        getServer().getPluginManager().registerEvents(this, this);

        startTimer1();
        startTimer2();
    }

    @Override
    public void onDisable() {
        getLogger().info("Season 9 Plugin Finalizado.");
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.PIG && event.getSpawnReason() == SpawnReason.NATURAL) {
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

    private void startTimer1() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(createComponent1());
                }
            }
        }.runTaskTimer(this, 0L, 129 * 60 * 20L); // 129 * 60 = 129min
    }

    private void startTimer2() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(createComponent2());
                }
            }
        }.runTaskTimer(this, 0L, 60 * 60 * 20L); // 60 * 60 = 60min
    }

    private TextComponent createComponent1() {
        TextComponent component = new TextComponent(message1);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/kZQKjBe"));
        return component;
    }

    private TextComponent createComponent2() {
        TextComponent component = new TextComponent(message2);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/kZQKjBe"));
        return component;
    }
}