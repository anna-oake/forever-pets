package cat.anya.foreverpets;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public final class ForeverPetsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) ->
            !ForeverPets.protectIfEligible(entity)
        );
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, source, amount) ->
            !ForeverPets.protectIfEligible(entity)
        );
        ServerEntityEvents.ENTITY_LOAD.register((entity, level) ->
            ForeverPets.protectIfEligible(entity)
        );
    }
}
