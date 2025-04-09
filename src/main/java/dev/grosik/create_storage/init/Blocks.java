package dev.grosik.create_storage.init;

import dev.grosik.create_storage.CreateStorage;
import dev.grosik.create_storage.networkunit.NetworkUnitBlock;
import dev.grosik.create_storage.storagecable.StorageCableBlock;
import dev.grosik.create_storage.storageunit.StorageUnitBlock;
import dev.grosik.create_storage.storageunit.StorageUnitBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.world.level.block.Blocks.STONE;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateStorage.MODID);
    public static List<RegistryObject<Item>> BLOCK_ITEMS = new ArrayList<>();

    public static final RegistryObject<Block> NETWORK_UNIT = BLOCKS.register("network_unit", () -> new NetworkUnitBlock(BlockBehaviour.Properties.copy(STONE).mapColor(MapColor.METAL).strength(1.5f)));
    public static final RegistryObject<Item> NETWORK_ITEM = Items.registerBlockItem(NETWORK_UNIT, "network_unit_block");

    public static final RegistryObject<Block> STORAGE_UNIT = BLOCKS.register("storage_unit", () -> new StorageUnitBlock(BlockBehaviour.Properties.copy(STONE).mapColor(MapColor.METAL).strength(1.5f)));
    public static final RegistryObject<Item> STORAGE_ITEM = Items.registerBlockItem(STORAGE_UNIT, "storage_unit_block");

    public static final RegistryObject<Block> STORAGE_CABLE = BLOCKS.register("storage_cable", () -> new StorageCableBlock(BlockBehaviour.Properties.copy(STONE).mapColor(MapColor.METAL).strength(1.5f)));
    public static final RegistryObject<Item> STORAGE_CABLE_ITEM = Items.registerBlockItem(STORAGE_CABLE, "storage_cable");

    public static RegistryObject<Block> registerBlock(String name, MapColor color) {
        return registerBlock(name, BlockBehaviour.Properties.copy(STONE).mapColor(color).strength(1.5f));
    }
    public static RegistryObject<Block> registerBlock(String name, BlockBehaviour.Properties properties) {
        RegistryObject<Block> block = registerBlockOnly(name, properties);
        BLOCK_ITEMS.add(Items.registerBlockItem(block, name));
        return block;
    }

    public static RegistryObject<Block> registerBlockOnly(String name, BlockBehaviour.Properties properties) {
        return BLOCKS.register(name, () -> new Block(properties));
    }
    public static RegistryObject<Block> registerAxisBlock(String name, MapColor color) {
        RegistryObject<Block> block = BLOCKS.register(name, () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(color).strength(1.5f)));
        BLOCK_ITEMS.add(Items.registerBlockItem(block, name));
        return block;
    }
    public static RegistryObject<Block> registerItemOnly(String name, RegistryObject<Block> block) {
        BLOCK_ITEMS.add(Items.registerBlockItem(block, name));
        return block;
    }
}