package dev.grosik.create_storage.init;

import dev.grosik.create_storage.CreateStorage;
import dev.grosik.create_storage.networkunit.NetworkUnitBlockEntity;
import dev.grosik.create_storage.storagecable.StorageCableBlockEntity;
import dev.grosik.create_storage.storageunit.StorageUnitBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CreateStorage.MODID);

    public static final RegistryObject<BlockEntityType<NetworkUnitBlockEntity>> NETWORK_UNIT =
            BLOCK_ENTITIES.register("network_unit",
                    () -> BlockEntityType.Builder.of(NetworkUnitBlockEntity::new, Blocks.NETWORK_UNIT.get()).build(null));

    public static final RegistryObject<BlockEntityType<StorageUnitBlockEntity>> STORAGE_UNIT =
            BLOCK_ENTITIES.register("storage_unit",
                    () -> BlockEntityType.Builder.of(StorageUnitBlockEntity::new, Blocks.STORAGE_UNIT.get()).build(null));

    public static final RegistryObject<BlockEntityType<StorageCableBlockEntity>> STORAGE_CABLE =
            BLOCK_ENTITIES.register("storage_cable",
                    () -> BlockEntityType.Builder.of(StorageCableBlockEntity::new, Blocks.STORAGE_CABLE.get()).build(null));
}
