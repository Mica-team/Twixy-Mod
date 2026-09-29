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
    new EntityModelLayer(
        Identifier.of(TwixyMod.MOD_ID, "twixy"),
        "main"
            );

    private final ModelPart body;
    private final ModelPart belly;
    private final ModelPart head;
    private final ModelPart backLegL;
    private final ModelPart backLegR;
    private final ModelPart frontLegL;
    private final ModelPart frontLegR;
    private final ModelPart tail;

    public TwixyModel(ModelPart root) {
        super(RenderLayer::getEntityCutoutNoCull);

        this.body = root.getChild("body");
        this.belly = body.getChild("belly");
        this.head = body.getChild("head");
        this.backLegL = body.getChild("backLegL");
        this.backLegR = body.getChild("backLegR");
        this.frontLegL = body.getChild("frontLegL");
        this.frontLegR = body.getChild("frontLegR");
        this.tail = root.getChild("Tail");
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
                "belly",
                ModelPartBuilder.create()
                        .uv(15, -3)
                        // Slightly smaller body; legs and tail are unchanged.
                        .cuboid(-4.0F, -8.0F, -2.0F, 8.0F, 16.0F, 9.0F, new Dilation(-0.25F)),
                ModelTransform.of(0.0F, 0.0F, 0.0F,
                        1.5708F, 0.0F, 0.0F)
        );

        ModelPartData head = body.addChild(
                "head",
                ModelPartBuilder.create()
                        .uv(0, -1)
                        // Slightly smaller head while keeping its position.
                        .cuboid(-1.5F, -3.0F, -4.0F, 5.0F, 5.0F, 6.0F, new Dilation(-0.25F))
                        .uv(0, 23)
                        .cuboid(-0.5F, -1.0156F, -5.0F, 3.0F, 3.0F, 3.0F, new Dilation(-0.25F)),
                ModelTransform.pivot(-1.0F, -3.0F, -10.0F)
        );

        head.addChild(
                "head_r1",
                ModelPartBuilder.create()
                        .uv(6, 9)
                        .cuboid(2.0F, -13.0F, -10.0F, 1.0F, 2.0F, 3.0F)
                        .uv(0, 9)
                        .cuboid(-1.0F, -13.0F, -10.0F, 1.0F, 2.0F, 3.0F),
                ModelTransform.of(
                        0.0F, 9.0F, 9.0F,
                        0.0436F, 0.0F, 0.0F
                )
        );

        body.addChild(
                "backLegL",
                ModelPartBuilder.create()
                        .uv(7, 12)
                        .cuboid(-1.0F, -6.0F, -2.0F, 3.0F, 12.0F, 3.0F),
                ModelTransform.pivot(1.1F, 1.0F, 6.0F)
        );

        body.addChild(
                "backLegR",
                ModelPartBuilder.create()
                        .uv(6, 12)
                        .cuboid(-2.0F, -6.0F, -2.0F, 3.0F, 12.0F, 3.0F),
                ModelTransform.pivot(-1.1F, 1.0F, 6.0F)
        );

        body.addChild(
                "frontLegL",
                ModelPartBuilder.create()
                        .uv(38, -2)
                        .cuboid(-1.0F, -4.2F, -1.0F, 4.0F, 14.0F, 4.0F),
                ModelTransform.pivot(1.2F, -3.0F, -5.0F)
        );

        body.addChild(
                "frontLegR",
                ModelPartBuilder.create()
                        .uv(37, -2)
                        .cuboid(-3.0F, -4.2F, -1.0F, 4.0F, 14.0F, 4.0F),
                ModelTransform.pivot(-1.2F, -3.0F, -5.0F)
        );

        root.addChild(
                "Tail",
                ModelPartBuilder.create()
                        .uv(-10, -10)
                        .cuboid(-1.0F, -2.0F, -4.0F, 2.0F, 2.0F, 12.0F),
                ModelTransform.pivot(0.0F, 13.0F, 13.0F)
        );

        // Match the new 128x128 Twixy texture.
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void setAngles(TwixyEntity entity, float limbSwing, float limbSwingAmount,
                          float ageInTicks, float netHeadYaw, float headPitch) {

        head.yaw = netHeadYaw * 0.017453292F;
        head.pitch = headPitch * 0.017453292F;

        frontLegL.pitch = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        frontLegR.pitch = (float) Math.cos(limbSwing * 0.6662F + Math.PI) * 1.4F * limbSwingAmount;
        backLegL.pitch = (float) Math.cos(limbSwing * 0.6662F + Math.PI) * 1.4F * limbSwingAmount;
        backLegR.pitch = (float) Math.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices,
                       int light, int overlay, int color) {

        body.render(matrices, vertices, light, overlay, color);
        tail.render(matrices, vertices, light, overlay, color);
    }
}
