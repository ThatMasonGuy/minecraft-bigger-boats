package tempeststudios.biggerboats.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tempeststudios.biggerboats.BiggerBoatsGeometry;

@Mixin(Boat.class)
public abstract class BoatCapacityMixin {
    @Shadow
    private float deltaRotation;

    @Shadow
    protected abstract void clampRotation(Entity entity);

    @Inject(method = "getMaxPassengers", at = @At("HEAD"), cancellable = true)
    private void biggerboats$getMaxPassengers(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(BiggerBoatsGeometry.MAX_PASSENGERS);
    }

    @Inject(method = "positionRider", at = @At("HEAD"), cancellable = true)
    private void biggerboats$positionRider(Entity passenger, Entity.MoveFunction moveFunction, CallbackInfo ci) {
        Boat boat = (Boat) (Object) this;
        if (!boat.hasPassenger(passenger)) {
            return;
        }

        int passengerCount = boat.getPassengers().size();
        int passengerIndex = boat.getPassengers().indexOf(passenger);
        float seatOffset = BiggerBoatsGeometry.seatOffset(passengerIndex, passengerCount);
        if (passenger instanceof Animal) {
            seatOffset += 0.2F;
        }

        float ridingOffset = (float) ((boat.isRemoved()
                ? 0.009999999776482582D
                : boat.getPassengersRidingOffset()) + passenger.getMyRidingOffset());
        Vec3 offset = new Vec3(seatOffset, 0.0D, 0.0D)
                .yRot(-boat.getYRot() * ((float) Math.PI / 180.0F) - ((float) Math.PI / 2.0F));

        moveFunction.accept(
                passenger,
                boat.getX() + offset.x,
                boat.getY() + ridingOffset,
                boat.getZ() + offset.z);
        passenger.setYRot(passenger.getYRot() + this.deltaRotation);
        passenger.setYHeadRot(passenger.getYHeadRot() + this.deltaRotation);
        this.clampRotation(passenger);
        ci.cancel();
    }
}
