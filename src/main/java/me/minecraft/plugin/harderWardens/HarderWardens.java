package me.minecraft.plugin.harderWardens;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class HarderWardens extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "HarderWardens >> Plugin has been enabled!");
        this.getServer().getPluginManager().registerEvents(this, this);
        this.saveDefaultConfig();

        FileConfiguration config = this.getConfig();
        config.addDefault("warden_difficulty", "NORMAL");
    }

    @EventHandler
    public void wardenSpawnEvent(EntitySpawnEvent e) {

        String warden_difficulty = this.getConfig().getString("warden_difficulty", "NORMAL");
        if (e.getEntity() instanceof LivingEntity ent) {
            if (ent.getType() == EntityType.WARDEN) {
                if (warden_difficulty.equals("EASY")) { // 250HP (125 hearts) on easy difficulty
                    ent.setCustomName("TEMPORARY NAME EASY"); // Warden's name on easy difficulty is
                    ent.setCustomNameVisible(false);
                    ent.setPersistent(ent.isCustomNameVisible());
                    ent.setMaxHealth(250);
                    ent.setHealth(250);
                } else if (warden_difficulty.equals("NORMAL")) { // 500HP (250 hearts) on normal difficulty
                    ent.setCustomName("TEMPORARY NAME NORMAL"); // Warden's name on normal difficulty is
                    ent.setCustomNameVisible(false);
                    ent.setPersistent(ent.isCustomNameVisible());
                    ent.setMaxHealth(500);
                    ent.setHealth(500);
                } else if (warden_difficulty.equals("HARD")) { // 1000HP (500 hearts) on hard difficulty
                    ent.setCustomName("TEMPORARY NAME HARD"); // Warden's name on hard difficulty is
                    ent.setCustomNameVisible(false);
                    ent.setPersistent(ent.isCustomNameVisible());
                    ent.setMaxHealth(1000);
                    ent.setHealth(1000);
                } else { // if difficulty not set correctly, default difficulty (normal) will be used
                    ent.setCustomName("TEMPORARY NAME NORMAL");
                    ent.setCustomNameVisible(false);
                    ent.setPersistent(ent.isCustomNameVisible());
                    ent.setMaxHealth(500);
                    ent.setHealth(500);
                }
            }
        }
    }
    @EventHandler
    public void wardenDeathEvent(EntityDeathEvent e) {

        LivingEntity ent = e.getEntity();
        Random ran = new Random();
        int num = ran.nextInt(100);

        String warden_difficulty = this.getConfig().getString("warden_difficulty", "NORMAL");
        if (ent.getType() == EntityType.WARDEN) {
            e.getDrops().clear();
            if (warden_difficulty.equals("EASY")) { // Loot from wardens on easy difficulty (common drops / uncommon drops)

            } else if (warden_difficulty.equals("NORMAL")) { //  Loot from wardens on normal difficulty (uncommon drops / rare drops)

            } else if (warden_difficulty.equals("HARD")) { // Loot from wardens on normal difficulty (rare drops)

            } else { // if difficulty not set correctly, default difficulty (normal) will be used

            }
        }
    }
    @EventHandler
    public void wardenAttackEvent(EntityDamageByEntityEvent e) {
        String warden_difficulty = this.getConfig().getString("warden_difficulty", "NORMAL");
        if (e.getDamager() instanceof Warden) {
            if (warden_difficulty.equals("EASY")) { // 0.5x damage on easy difficulty
                double originalDamage = e.getDamage();
                double newDamage = originalDamage * 0.5;
                e.setDamage(newDamage);
            } else if (warden_difficulty.equals("NORMAL")) { // 2.2x damage on normal difficulty
                double originalDamage = e.getDamage();
                double newDamage = originalDamage * 2.2;
                e.setDamage(newDamage);
            } else if (warden_difficulty.equals("HARD")) { // 3.5x damage on hard difficulty
                double originalDamage = e.getDamage();
                double newDamage = originalDamage * 3.5;
                e.setDamage(newDamage);
            } else { // if difficulty not set correctly, default difficulty (normal) will be used
                double originalDamage = e.getDamage();
                double newDamage = originalDamage * 2.2;
                e.setDamage(newDamage);
            }
        }
    }
}
