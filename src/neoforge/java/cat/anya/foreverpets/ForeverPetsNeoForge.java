package cat.anya.foreverpets;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@Mod(ForeverPets.MOD_ID)
public final class ForeverPetsNeoForge {
    public ForeverPetsNeoForge() {
        NeoForge.EVENT_BUS.addListener(this::onEntityJoin);
        NeoForge.EVENT_BUS.addListener(this::onIncomingDamage);
        NeoForge.EVENT_BUS.addListener(this::onDeath);
    }

    private void onEntityJoin(EntityJoinLevelEvent event) {
        if (!event.getLevel().isClientSide()) {
            ForeverPets.protectIfEligible(event.getEntity());
        }
    }

    private void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (ForeverPets.protectIfEligible(event.getEntity())) {
            event.setCanceled(true);
        }
    }

    private void onDeath(LivingDeathEvent event) {
        if (ForeverPets.protectIfEligible(event.getEntity())) {
            event.setCanceled(true);
        }
    }
}
