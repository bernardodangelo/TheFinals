package com.bernardo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class season9 extends JavaPlugin implements Listener {
    private Random random = new Random();
    private String message1 = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "]" +
            ChatColor.RED + " Regras do Servidor: " +
            ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "docs.google.com/document/d/1TCZrqdJc6_h1r1O-T2q5v0l2jnFeOTE2ZuuHEi3PIOs/edit?usp=sharing";

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
            if (random.nextDouble() <= 0.05) {
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
        }.runTaskTimer(this, 0L, 77 * 60 * 20L); // 87 * 60 = 129min
    }

    private void startTimer2() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(createComponent2());
                }
            }
        }.runTaskTimer(this, 0L, 60 * 60 * 20L); // 40 * 60 = 60min
    }

    private TextComponent createComponent1() {
        TextComponent component = new TextComponent(message1);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "docs.google.com/document/d/1TCZrqdJc6_h1r1O-T2q5v0l2jnFeOTE2ZuuHEi3PIOs/edit?usp=sharing"));
        return component;
    }

    private TextComponent createComponent2() {
        TextComponent component = new TextComponent(message2);
        component.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/kZQKjBe"));
        return component;
    }

    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.MUSHROOM_STEW) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0));
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item != null && item.getType() == Material.CHERRY_LOG && item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null && itemMeta.getDisplayName().equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "Abertura da SEASON 9")) {
                    event.setCancelled(true);
                }
            }
        }
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("item1")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("season9.admin")) {
                    ItemStack item = new ItemStack(Material.CHERRY_LOG);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Abertura da SEASON 9");
                    List<String> lore = new ArrayList<>();
                    lore.add(ChatColor.AQUA + "Item colecionável");
                    lore.add(ChatColor.GRAY + "Este item foi dado para todos os players");
                    lore.add(ChatColor.GRAY + "que estiveram presentes na abertura do");
                    lore.add(ChatColor.GRAY + "server (15/07/2023).");
                    itemMeta.setLore(lore);
                    item.setItemMeta(itemMeta);

                    PlayerInventory inventory = player.getInventory();
                    inventory.addItem(item);

                    player.sendMessage(ChatColor.GREEN + "Você recebeu um item colecionável: " + ChatColor.YELLOW + ChatColor.BOLD + "Abertura da SEASON 9");
                } else {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando só pode ser executado por jogadores.");
            }
            return true;
        }
        return false;
    }
}