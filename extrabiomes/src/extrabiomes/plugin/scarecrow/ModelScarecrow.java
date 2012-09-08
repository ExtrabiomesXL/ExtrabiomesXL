/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.scarecrow;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;

public class ModelScarecrow extends ModelBase {
	private final ModelRenderer	leg;
	private final ModelRenderer	body;
	private final ModelRenderer	head;
	private final ModelRenderer	arms;

	public ModelScarecrow() {
		textureWidth = 128;
		textureHeight = 64;

		leg = new ModelRenderer(this, 0, 16);
		leg.addBox(-1F, -29F, -1F, 2, 30, 2);
		leg.setRotationPoint(0F, 23F, 0F);
		leg.setTextureSize(128, 64);
		leg.mirror = true;
		setRotation(leg, 0F, 0F, 0F);
		body = new ModelRenderer(this, 0, 0);
		body.addBox(-4F, -4F, -4F, 8, 8, 8);
		body.setRotationPoint(0F, 5F, 0F);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		head = new ModelRenderer(this, 0, 52);
		head.addBox(-3F, -6F, -3F, 6, 6, 6);
		head.setRotationPoint(0F, 1F, 0F);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		arms = new ModelRenderer(this, 32, 0);
		arms.addBox(-15F, -1F, -1F, 31, 2, 2);
		arms.setRotationPoint(0F, 3F, 0F);
		arms.setTextureSize(128, 64);
		arms.mirror = true;
		setRotation(arms, 0F, 0F, 0.10F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2,
			float f3, float f4, float f5)
	{
		leg.render(f5);
		body.render(f5);
		head.render(f5);
		arms.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y,
			float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}