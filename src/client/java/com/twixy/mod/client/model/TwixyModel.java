package com.twixy.mod.client.model;

import com.twixy.mod.TwixyMod;
import com.twixy.mod.entity.TwixyEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class TwixyModel extends EntityModel<TwixyEntity> {

    public static final EntityModelLayer LAYER_LOCATION =
            new EntityModelLayer(Identifier.of(TwixyMod.MOD_ID, "twixy"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart bone;
    private final ModelPart tail1;
    private final ModelPart bone2;
    private final ModelPart backLegL;
    private final ModelPart bone3;
    private final ModelPart backLegR;
    private final ModelPart bone4;
    private final ModelPart frontLegL;
    private final ModelPart bone5;
    private final ModelPart frontLegR;
    private final ModelPart bone6;

    public TwixyModel(ModelPart root) {
        super(RenderLayer::getEntityCutoutNoCull);

        this.body = root.getChild("body");
        this.head = body.getChild("head");
        this.bone = head.getChild("bone");
        this.tail1 = body.getChild("tail1");
        this.bone2 = tail1.getChild("bone2");
        this.backLegL = body.getChild("backLegL");
        this.bone3 = backLegL.getChild("bone3");
        this.backLegR = body.getChild("backLegR");
        this.bone4 = backLegR.getChild("bone4");
        this.frontLegL = body.getChild("frontLegL");
        this.bone5 = frontLegL.getChild("bone5");
        this.frontLegR = body.getChild("frontLegR");
        this.bone6 = frontLegR.getChild("bone6");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData root = modelData.getRoot();

        ModelPartData body = root.addChild(
                "body",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, 17.0F, 1.0F)
        );

        body.addChild(
                "body_r1",
                ModelPartBuilder.create()
                        .uv(0, 0)
                        .cuboid(-4.0F, -8.0F, -3.0F, 8.0F, 16.0F, 8.0F),
                ModelTransform.rotation(1.5708F, 0.0F, 0.0F)
        );

        ModelPartData head = body.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, 24)
                        .cuboid(-2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F)
                        .uv(32, 29)
                        .cuboid(-1.5F, -0.0156F, -4.0F, 3.0F, 2.0F, 2.0F),
                ModelTransform.pivot(0.0F, -2.0F, -10.0F)
        );

        head.addChild(
                "head_r1",
                ModelPartBuilder.create()
                        .uv(14, 33)
                        .cuboid(-2.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F),
                ModelTransform.rotation(-0.2182F, 0.0F, 0.0F)
        );

        head.addChild(
                "head_r2",
                ModelPartBuilder.create()
                        .uv(8, 33)
                        .cuboid(1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 2.0F),
                ModelTransform.rotation(-0.1745F, 0.0F, 0.0F)
        );

        head.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 1.0F));

        ModelPartData tail1 = body.addChild(
                "tail1",
                ModelPartBuilder.create(),
                ModelTransform.pivot(0.0F, -2.0F, 7.0F)
        );

        tail1.addChild(
                "tail1_r1",
                ModelPartBuilder.create()
                        .uv(0, 33)
                        .cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 19.0F, 1.0F),
                ModelTransform.rotation(1.5708F, 0.0F, 0.0F)
        );

        tail1.addChild("bone2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData backLegL = body.addChild(
                "backLegL",
                ModelPartBuilder.create()
                        .uv(32, 13)
                        .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F),
                ModelTransform.pivot(-1.1F, 1.0F, 6.0F)
        );
        backLegL.addChild("bone3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData backLegR = body.addChild(
                "backLegR",
                ModelPartBuilder.create()
                        .uv(32, 21)
                        .cuboid(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F),
                ModelTransform.pivot(1.1F, 1.0F, 6.0F)
        );
        backLegR.addChild("bone4", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData frontLegL = body.addChild(
                "frontLegL",
                ModelPartBuilder.create()
                        .uv(20, 24)
                        .cuboid(-2.0F, -0.2F, -1.0F, 3.0F, 10.0F, 3.0F),
                ModelTransform.pivot(-1.2F, -3.0F, -5.0F)
        );
        frontLegL.addChild("bone5", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData frontLegR = body.addChild(
                "frontLegR",
                ModelPartBuilder.create()
                        .uv(32, 0)
                        .cuboid(-1.0F, -0.2F, -1.0F, 3.0F, 10.0F, 3.0F),
                ModelTransform.pivot(1.2F, -3.0F, -5.0F)
        );
        frontLegR.addChild("bone6", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(TwixyEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {
        // Restore the Blockbench pose before applying the walking animation.
        head.yaw = netHeadYaw * 0.017453292F;
        head.pitch = headPitch * 0.017453292F;

        tail1.pitch = 0.0F;
        backLegL.pitch = 0.0F;
        backLegR.pitch = 0.0F;
        frontLegL.pitch = 0.0F;
        frontLegR.pitch = 0.0F;
        bone3.pitch = 0.0F;
        bone6.pitch = 0.0F;

        if (limbSwingAmount <= 0.001F) {
            return;
        }

        float time = limbSwing % 10.0F;
        if (time < 0.0F) {
            time += 10.0F;
        }

        float t = time / 10.0F;

        tail1.pitch = keyframe3(t, 0.0F, -7.5F, 0.45833F, 0.0001F);
        backLegL.pitch = keyframe3(t, 0.0F, -17.5F, 0.5F, 17.5F);
        backLegR.pitch = keyframe3(t, 0.0F, 17.5F, 0.5F, -15.0F);
        frontLegL.pitch = keyframe3(t, 0.0F, -15.0F, 0.5F, 10.0001F);
        frontLegR.pitch = keyframe3(t, 0.0F, 17.5F, 0.5F, -12.5F);
        bone3.pitch = keyframe3(t, 0.0F, 2.5F, 0.5F, 0.0F);
        bone6.pitch = keyframe3(t, 0.0F, 15.0F, 0.5F, 0.0F);
    }

    private static float keyframe3(float t, float start, float middle, float middleTime,
                                   float end) {
        if (t <= 0.25F) {
            return lerp(t / 0.25F, start, middle);
        }
        return lerp((t - 0.25F) / (middleTime - 0.25F), middle, end);
    }

    private static float lerp(float t, float a, float b) {
        return a + (b - a) * Math.max(0.0F, Math.min(1.0F, t));
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay, int color) {
        body.render(matrices, vertices, light, overlay, color);
    }
}
