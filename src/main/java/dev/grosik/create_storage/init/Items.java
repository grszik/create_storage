package dev.grosik.create_storage.init;

import dev.grosik.create_storage.CreateStorage;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CreateStorage.MODID);

    public static final RegistryObject<Item> ICE_CREAM = registerItem("ice_cream_top", new Item.Properties()
            .stacksTo(16)
            .rarity(Rarity.COMMON));

    public static RegistryObject<Item> registerItem(String name, Item.Properties properties) {
        return CreativeTab.addToTab(ITEMS.register(name, () -> new Item(properties)));
    }
    public static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block, String name) {
        return CreativeTab.addToTabBlocks(ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(Rarity.COMMON))));
    }
    public static RegistryObject<Item> registerBlockItem(RegistryObject<Block> block, String name, Item.Properties properties) {
        return CreativeTab.addToTabBlocks(ITEMS.register(name, () -> new BlockItem(block.get(), properties)));
    }
}
