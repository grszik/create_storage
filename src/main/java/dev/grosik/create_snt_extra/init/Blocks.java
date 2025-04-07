package dev.grosik.create_snt_extra.init;

import dev.grosik.create_snt_extra.CreateSNTE;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class Blocks {
    public static final BlockSetType chocolate = new BlockSetType("chocolate");
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CreateSNTE.MODID);
    public static List<RegistryObject<Item>> BLOCK_ITEMS = new ArrayList<>();


    public static RegistryObject<Block> registerBlock(String name, MapColor color) {
        return registerBlock(name, BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.STONE).mapColor(color).strength(1.5f));
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
