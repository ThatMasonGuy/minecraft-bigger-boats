package tempeststudios.biggerboats.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tempeststudios.biggerboats.BiggerBoatsGeometry;

@Mixin(BoatRenderer.class)
public abstract class BiggerBoatsRendererMixin {
    @Inject(
            method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER))
    private void biggerboats$stretchBoat(
            Boat boat,
            float yaw,
            float tickDelta,
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int light,
            CallbackInfo ci) {
        float lengthScale = BiggerBoatsGeometry.boatLengthScale(boat.getPassengers().size());
        if (lengthScale != 1.0F) {
            poseStack.scale(1.0F, 1.0F, lengthScale);
        }
    }
}
