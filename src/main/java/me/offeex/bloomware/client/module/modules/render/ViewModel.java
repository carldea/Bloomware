package me.offeex.bloomware.client.module.modules.render;

import me.offeex.bloomware.client.module.Module;
import me.offeex.bloomware.client.setting.Setting;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;

public class ViewModel extends Module {
    public final Setting<Number> scaleX = register("ScaleX", 1.0, 0.1, 5.0, 0.1);
    public final Setting<Number> scaleY = register("ScaleY", 1.0, 0.1, 5.0, 0.1);
    public final Setting<Number> scaleZ = register("ScaleZ", 1.0, 0.1, 5.0, 0.1);
    public final Setting<Number> positionX = register("PositionX", 0.0, -3.0, 3.0, 0.1);
    public final Setting<Number> positionY = register("PositionY", 0.0, -3.0, 3.0, 0.1);
    public final Setting<Number> positionZ = register("PositionZ", 0.0, -3.0, 3.0, 0.1);
    public final Setting<Number> rotationX = register("RotationX", 0.0, -180.0, 180.0, 1.0);
    public final Setting<Number> rotationY = register("RotationY", 0.0, -180.0, 180.0, 1.0);
    public final Setting<Number> rotationZ = register("RotationZ", 0.0, -180.0, 180.0, 1.0);

    public ViewModel() {
        super("ViewModel", "Changes your hand renderer", Category.RENDER, false);
    }

    public void changeRenderer(MatrixStack stack) {
        if (!this.isEnabled()) {
            return;
        }
        stack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(rotationX.getValue().intValue()));
        stack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotationY.getValue().intValue()));
        stack.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(rotationZ.getValue().intValue()));
        stack.scale(scaleX.getValue().floatValue(), scaleY.getValue().floatValue(), scaleZ.getValue().floatValue());
        stack.translate(positionX.getValue().doubleValue(), positionY.getValue().doubleValue(), positionZ.getValue().doubleValue());
    }
}
