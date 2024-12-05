package me.minecraft.plugin.harderWardens;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
    }

    @EventHandler
    public void wardenSpawnEvent(EntitySpawnEvent e) {

        if (e.getEntity() instanceof LivingEntity ent) {
            if (ent.getType() == EntityType.WARDEN) {
                ent.setCustomName("The Reaper");
                ent.setCustomNameVisible(false);
                ent.setPersistent(ent.isCustomNameVisible());
                ent.setMaxHealth(1000);
                ent.setHealth(1000);
            }
        }
    }
    @EventHandler
    public void wardenDeathEvent(EntityDeathEvent e) {

        LivingEntity ent = e.getEntity();
        Random ran = new Random();
        int num = ran.nextInt(100);

        ItemStack diamond_chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD);

        if (ent.getType() == EntityType.WARDEN) {
            e.getDrops().clear();
            if (num >= 80) {
                e.getDrops().add(new ItemStack(Material.TOTEM_OF_UNDYING));
            } else if (num >= 60 && num < 80) {
                e.getDrops().add(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 3));
            } else if (num >= 40 && num < 60) {
                e.getDrops().add(new ItemStack(Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE));
                e.getDrops().add(new ItemStack(Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE));
                diamond_chestplate.addEnchantment(Enchantment.PROTECTION, 4);
                diamond_chestplate.addEnchantment(Enchantment.UNBREAKING, 3);
                e.getDrops().add(new ItemStack(diamond_chestplate));
            } else if (num >= 20 && num < 40) {
                e.getDrops().add(new ItemStack(Material.OMINOUS_TRIAL_KEY, 2));
                diamond_sword.addEnchantment(Enchantment.SHARPNESS, 4);
                diamond_sword.addEnchantment(Enchantment.UNBREAKING, 3);
                e.getDrops().add(new ItemStack(diamond_sword));
            } else if (num < 20) {
                e.getDrops().add(new ItemStack(Material.DIAMOND, 6));
                e.getDrops().add(new ItemStack(Material.NAME_TAG));
                e.getDrops().add(new ItemStack(Material.COOKED_BEEF, 8));
            }
        }
    }
    @EventHandler
    public void wardenAttackEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Warden) {
            double originalDamage = e.getDamage();
            double newDamage = originalDamage * 2.5;
            e.setDamage(newDamage);
        }
    }
}
