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
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = CreateSNTE.MODID) //replace your_mod_id
public class FoodEvent {
    public static List<Player> saturated = new ArrayList<>();

    @SubscribeEvent
    public static void itemUsed(LivingEntityUseItemEvent.Stop event) {
        LivingEntity entity = event.getEntity();
        ItemStack item = event.getItem();

        if (entity instanceof Player player && item.getItem() == Items.CREATIVE_DONUT.get()) {
            LogManager.getLogger().warn("Player hunger: {}", player.getFoodData().getFoodLevel());
            if(saturated.contains(player)) {
                LogManager.getLogger().warn("Turned off");
                saturated.remove(player);
            } else {
                LogManager.getLogger().warn("Turned on");
                saturated.add(player);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            LogManager.getLogger().warn("tick {}, {}", saturated.contains(player) ? "yes" : "no", player.getFoodData().getFoodLevel());
            if(saturated.contains(player) && player.getFoodData().getFoodLevel() < 20) {
                player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 20, 255, true, true));
            }
        }
    }
}