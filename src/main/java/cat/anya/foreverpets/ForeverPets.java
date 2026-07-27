package cat.anya.foreverpets;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
/*? if neoforge {*/
/*? if >=1.21.11 {*/
import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.parrot.Parrot;
/*?} else {*/
/*import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;*/
/*?}*/
/*?} else {*/
/*? if >=26.1 {*/
/*import net.minecraft.world.entity.animal.feline.Cat;
import net.minecraft.world.entity.animal.parrot.Parrot;*/
/*?} else {*/
/*import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Parrot;*/
/*?}*/
/*?}*/
/*? if >=1.21.5 {*/
import net.minecraft.world.entity.animal.wolf.Wolf;
/*?} else {*/
/*import net.minecraft.world.entity.animal.Wolf;*/
/*?}*/

public final class ForeverPets {
    public static final String MOD_ID = "forever_pets";

    private ForeverPets() {
    }

    public static boolean protectIfEligible(Entity entity) {
        if (!(entity instanceof TamableAnimal tamableAnimal)) {
            return false;
        }

        if (!(entity instanceof Cat || entity instanceof Wolf || entity instanceof Parrot)) {
            return false;
        }

        if (!tamableAnimal.isTame() || !hasOwner(tamableAnimal)) {
            return false;
        }

        tamableAnimal.setInvulnerable(true);
        tamableAnimal.setHealth(tamableAnimal.getMaxHealth());
        return true;
    }

    private static boolean hasOwner(TamableAnimal pet) {
        /*? if >=1.21.5 {*/
        return pet.getOwnerReference() != null;
        /*?} else {*/
        /*return pet.getOwnerUUID() != null;*/
        /*?}*/
    }
}
