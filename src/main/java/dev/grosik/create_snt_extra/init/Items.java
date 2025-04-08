package dev.grosik.create_snt_extra.init;

import dev.grosik.create_snt_extra.CreateSNTE;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CreateSNTE.MODID);

    public static final RegistryObject<Item> ICE_CREAM = registerItem("ice_cream", new Item.Properties()
            .stacksTo(16)
            .food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.2f)
                    .build())
            .rarity(Rarity.COMMON));
    public static final RegistryObject<Item> ICE_CREAM_TOP = registerItem("ice_cream_top", new Item.Properties()
            .stacksTo(16)
            .rarity(Rarity.COMMON));
    public static final RegistryObject<Item> ICE_CONE = registerItem("ice_cone", new Item.Properties()
            .stacksTo(16)
            .rarity(Rarity.COMMON));

    public static final RegistryObject<Item> CREATIVE_DONUT = registerItem("creative_donut", new Item.Properties()
            .stacksTo(1)
            .food(new FoodProperties.Builder()
                    .nutrition(3)
                    .saturationMod(0.2f)
                    .build())
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
