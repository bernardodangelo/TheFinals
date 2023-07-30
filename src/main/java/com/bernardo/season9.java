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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Arrays;
import java.util.Random;

public final class season9 extends JavaPlugin implements Listener {
    private Random random = new Random();
    private String message1 = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "]" +
            ChatColor.RED + " Regras do Servidor: " +
            ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "docs.google.com/document/d/1TCZrqdJc6_h1r1O-T2q5v0l2jnFeOTE2ZuuHEi3PIOs/edit?usp=sharing";

    private String message2 = ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "]" + ChatColor.AQUA + " Entre no Discord do Servidor: " +
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
            if (random.nextDouble() <= 0.01) {
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
        }.runTaskTimer(this, 0L, 77 * 60 * 20L); // 77 * 60 = 129min
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
    public void onPlayerInteractAbertura(PlayerInteractEvent event) {
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

    @EventHandler
    public void onPlayerInteractPlace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item != null && item.getType() == Material.RED_WOOL && item.hasItemMeta()) {
                ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta != null && itemMeta.getDisplayName().equals(ChatColor.RED + "" + ChatColor.BOLD + "r/Place 2023")) {
                    event.setCancelled(true);
                }
            }
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("regras")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "] " + ChatColor.RED + "Regras do Servidor: " + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "docs.google.com/document/d/1TCZrqdJc6_h1r1O-T2q5v0l2jnFeOTE2ZuuHEi3PIOs/edit?usp=sharing");
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando só pode ser executado por jogadores.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("loja")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "Como criar uma loja no servidor: " + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "https://www.twitch.tv/excambaw/clip/CleverTiredSkirretHassaanChop-UpCCxcMlxSffw_Cg");
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando só pode ser executado por jogadores.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("terreno")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "Como proteger terreno no servidor: " + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "https://www.twitch.tv/excambaw/clip/ZanyPatientBeaverDoggo-vdbvmQRk5mYc8Uxd?filter=clips&range=7d");
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando só pode ser executado por jogadores.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("voice")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GRAY + "[" + ChatColor.YELLOW + ChatColor.BOLD + "SEASON 9" + ChatColor.GRAY + "] " + ChatColor.YELLOW + "Como usar o chat de voz: " + ChatColor.LIGHT_PURPLE + ChatColor.UNDERLINE + "https://www.twitch.tv/excambaw/clip/DaintySpeedyLatteTakeNRG--yk3P0AsJ2GR_lDg?filter=clips&range=7d&sort=time");
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando só pode ser executado por jogadores.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("item1")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("season9.item1")) {
                    ItemStack item = new ItemStack(Material.CHERRY_LOG);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "Abertura da SEASON 9");
                    itemMeta.setLore(Arrays.asList(ChatColor.GRAY + "Este é um item customizado", ChatColor.GRAY + "Ele foi obtido durante a abertura do servidor (15/07/2023)."));
                    item.setItemMeta(itemMeta);

                    player.getInventory().addItem(item);
                    player.sendMessage(ChatColor.GREEN + "Você recebeu o item especial da abertura da SEASON 9.");
                } else {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para executar este comando.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Este comando só pode ser executado por jogadores.");
            }
            return true;
        } else if (command.getName().equalsIgnoreCase("item2")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("season9.item2")) {
                    ItemStack item = new ItemStack(Material.RED_WOOL);
                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "r/Place 2023");
                    itemMeta.setLore(Arrays.asList(ChatColor.AQUA + "Item Colecionável", ChatColor.GRAY + "Item dado aos jogadores que ajudaram ativamente na", ChatColor.GRAY + "construção das artes no r/Place 2023 (25/07/2023)."));
                    item.setItemMeta(itemMeta);

                    player.getInventory().addItem(item);
                    player.sendMessage(ChatColor.GREEN + "Você recebeu o item especial do r/Place 2023.");
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


    @EventHandler
    public void rightClickSign(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType() == Material.OAK_SIGN || event.getClickedBlock().getType() == Material.OAK_WALL_SIGN) {
                event.setCancelled(true);
            }
        }
    }
}