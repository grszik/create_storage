package dev.grosik.create_snt_extra.init;

import dev.grosik.create_snt_extra.CreateSNTE;
import dev.grosik.create_snt_extra.base.FluidContainer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Fluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, CreateSNTE.MODID);
    public static final DeferredRegister<net.minecraft.world.level.material.Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.Keys.FLUIDS, CreateSNTE.MODID);

    public static final FluidContainer CAKE_BATTER = new FluidContainer("cake_batter", FluidType.Properties.create().canSwim(false).canDrown(true).canPushEntity(true).supportsBoating(false).viscosity(2), () -> FluidContainer.createExtension(new FluidContainer.ClientExtensions(CreateSNTE.MODID, "cake_batter").overlay(null).tint(0xFF7C2B).still("apple_syrup_still").flowing("apple_syrup_flow").overlay("apple_syrup_still")), BlockBehaviour.Properties.copy(Blocks.WATER));
}
