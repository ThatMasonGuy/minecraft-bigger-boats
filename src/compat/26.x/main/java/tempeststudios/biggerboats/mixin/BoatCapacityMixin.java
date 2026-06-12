package tempeststudios.biggerboats.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tempeststudios.biggerboats.BiggerBoatsGeometry;

@Mixin(AbstractBoat.class)
public abstract class BoatCapacityMixin {
    @Shadow
    protected abstract double rideHeight(EntityDimensions dimensions);

    @Inject(method = "getMaxPassengers", at = @At("HEAD"), cancellable = true)
    private void biggerboats$getMaxPassengers(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(BiggerBoatsGeometry.MAX_PASSENGERS);
    }

    @Inject(method = "getPassengerAttachmentPoint", at = @At("HEAD"), cancellable = true)
    private void biggerboats$getPassengerAttachmentPoint(
            Entity passenger,
            EntityDimensions dimensions,
            float scale,
            CallbackInfoReturnable<Vec3> cir) {
        AbstractBoat boat = (AbstractBoat) (Object) this;
        int passengerCount = boat.getPassengers().size();
        int passengerIndex = boat.getPassengers().indexOf(passenger);
        float seatOffset = BiggerBoatsGeometry.seatOffset(passengerIndex, passengerCount);
        if (passenger instanceof Animal) {
            seatOffset += 0.2F;
        }

        cir.setReturnValue(new Vec3(0.0D, this.rideHeight(dimensions), seatOffset)
                .yRot(-boat.getYRot() * ((float) Math.PI / 180.0F)));
    }
}
