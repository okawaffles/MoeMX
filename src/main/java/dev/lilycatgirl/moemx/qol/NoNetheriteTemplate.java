package dev.lilycatgirl.moemx.qol;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class NoNetheriteTemplate {

    public static void registerRecipes(JavaPlugin plugin) {
        // tool keys
        NamespacedKey swordKey = new NamespacedKey(plugin, "sword");
        NamespacedKey axeKey = new NamespacedKey(plugin, "axe");
        NamespacedKey shovelKey = new NamespacedKey(plugin, "shovel");
        NamespacedKey pickaxeKey = new NamespacedKey(plugin, "pickaxe");
        NamespacedKey hoeKey = new NamespacedKey(plugin, "hoe");

        // armor keys
        NamespacedKey helmetKey = new NamespacedKey(plugin, "helmet");
        NamespacedKey chestplateKey = new NamespacedKey(plugin, "chestplate");
        NamespacedKey leggingsKey = new NamespacedKey(plugin, "leggings");
        NamespacedKey bootsKey = new NamespacedKey(plugin, "boots");

        // create recipes
        // tools
        ShapelessRecipe sword = new ShapelessRecipe(swordKey, new ItemStack(Material.NETHERITE_SWORD));
        sword.addIngredient(1, Material.DIAMOND_SWORD);
        sword.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe axe = new ShapelessRecipe(axeKey, new ItemStack(Material.NETHERITE_AXE));
        axe.addIngredient(1, Material.DIAMOND_AXE);
        axe.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe shovel = new ShapelessRecipe(shovelKey, new ItemStack(Material.NETHERITE_SHOVEL));
        shovel.addIngredient(1, Material.DIAMOND_SHOVEL);
        shovel.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe pickaxe = new ShapelessRecipe(pickaxeKey, new ItemStack(Material.NETHERITE_PICKAXE));
        pickaxe.addIngredient(1, Material.DIAMOND_PICKAXE);
        pickaxe.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe hoe = new ShapelessRecipe(hoeKey, new ItemStack(Material.NETHERITE_HOE));
        hoe.addIngredient(1, Material.DIAMOND_HOE);
        hoe.addIngredient(1, Material.NETHERITE_INGOT);

        // Armor
        ShapelessRecipe helmet = new ShapelessRecipe(helmetKey, new ItemStack(Material.NETHERITE_HELMET));
        helmet.addIngredient(1, Material.DIAMOND_HELMET);
        helmet.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe chestplate = new ShapelessRecipe(chestplateKey, new ItemStack(Material.NETHERITE_CHESTPLATE));
        chestplate.addIngredient(1, Material.DIAMOND_CHESTPLATE);
        chestplate.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe leggings = new ShapelessRecipe(leggingsKey, new ItemStack(Material.NETHERITE_LEGGINGS));
        leggings.addIngredient(1, Material.DIAMOND_LEGGINGS);
        leggings.addIngredient(1, Material.NETHERITE_INGOT);

        ShapelessRecipe boots = new ShapelessRecipe(bootsKey, new ItemStack(Material.NETHERITE_BOOTS));
        boots.addIngredient(1, Material.DIAMOND_BOOTS);
        boots.addIngredient(1, Material.NETHERITE_INGOT);

        // register recipes
        // tools
        Bukkit.addRecipe(sword);
        Bukkit.addRecipe(axe);
        Bukkit.addRecipe(pickaxe);
        Bukkit.addRecipe(hoe);
        Bukkit.addRecipe(shovel);
        // armor
        Bukkit.addRecipe(helmet);
        Bukkit.addRecipe(chestplate);
        Bukkit.addRecipe(leggings);
        Bukkit.addRecipe(boots);
    }
}
