package dev.grosik.create_storage;

import dev.grosik.create_storage.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateStorage.MODID)
public class CreateStorage {
    public static final String MODID = "create_storage";

    public CreateStorage() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Items.ITEMS.register(bus);
        CreativeTab.TABS.register(bus);
        Blocks.BLOCKS.register(bus);
        BlockEntityTypes.BLOCK_ENTITIES.register(bus);
        Fluids.FLUID_TYPES.register(bus);
        Fluids.FLUIDS.register(bus);
    }
}
