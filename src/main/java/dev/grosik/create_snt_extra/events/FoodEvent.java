package dev.grosik.create_snt_extra.events;

import dev.grosik.create_snt_extra.CreateSNTE;
import dev.grosik.create_snt_extra.init.Items;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CreateSNTE.MODID) //replace your_mod_id
public class FoodEvent {

    @SubscribeEvent
    public static void onFoodEaten(LivingEntityUseItemEvent.Finish event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = event.getItem();

        if (entity instanceof Player player && item.getItem() == Items.CREATIVE_DONUT.get()) {
            if (player.hasEffect(new MobEffectInstance(MobEffects.SATURATION).getEffect())) {
                player.removeEffect(new MobEffectInstance(MobEffects.SATURATION).getEffect());
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION));
            }
        }
    }
}