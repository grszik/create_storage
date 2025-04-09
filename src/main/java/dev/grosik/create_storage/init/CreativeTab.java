package dev.grosik.create_storage.init;

import dev.grosik.create_storage.CreateStorage;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class CreativeTab {
    public static final List<RegistryObject<? extends Item>> TAB_ITEMS = new ArrayList<>();
    public static final List<RegistryObject<? extends Item>> BLOCK_TAB_ITEMS = new ArrayList<>();
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateStorage.MODID);

    public static RegistryObject<CreativeModeTab> TAB = TABS.register("create_storage",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.create_storage.creativetab"))
                    .icon(Items.ICE_CREAM.get()::getDefaultInstance)
                    .displayItems((displayParameters, output) -> {
                        TAB_ITEMS.forEach(itemLike -> output.accept(itemLike.get()));
                    })
                    .build());

    public static <T extends Item> RegistryObject<T> addToTab(RegistryObject<T> itemLike) {
        TAB_ITEMS.add(itemLike);
        return itemLike;
    }
    public static <T extends Item> RegistryObject<T> addToTabBlocks(RegistryObject<T> itemLike) {
        TAB_ITEMS.add(itemLike);
        return itemLike;
    }
}
