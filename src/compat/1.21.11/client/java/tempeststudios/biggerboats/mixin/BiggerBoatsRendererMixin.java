package tempeststudios.biggerboats.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.client.renderer.entity.state.BoatRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tempeststudios.biggerboats.BiggerBoatsGeometry;

import java.util.Map;
import java.util.WeakHashMap;

@Mixin(AbstractBoatRenderer.class)
public abstract class BiggerBoatsRendererMixin {
    @Unique
    private static final Map<BoatRenderState, Integer> BIGGERBOATS_PASSENGER_COUNTS = new WeakHashMap<>();

    @Inject(
            method = "extractRenderState(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/client/renderer/entity/state/EntityRenderState;F)V",
            at = @At("TAIL"))
    private void biggerboats$capturePassengerCount(
            Entity entity,
            EntityRenderState state,
            float tickDelta,
            CallbackInfo ci) {
        if (state instanceof BoatRenderState boatState) {
            BIGGERBOATS_PASSENGER_COUNTS.put(boatState, entity.getPassengers().size());
        }
    }

    @Inject(
            method = "submit(Lnet/minecraft/client/renderer/entity/state/BoatRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V",
                    ordinal = 0,
                    shift = At.Shift.AFTER))
    private void biggerboats$stretchBoat(
            BoatRenderState state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState cameraState,
            CallbackInfo ci) {
        float lengthScale = BiggerBoatsGeometry.boatLengthScale(BIGGERBOATS_PASSENGER_COUNTS.getOrDefault(state, 0));
        if (lengthScale != 1.0F) {
            poseStack.scale(1.0F, 1.0F, lengthScale);
        }
    }
}
