/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.ModelBase;
import net.minecraft.src.RenderLiving;

import org.lwjgl.opengl.GL11;


public class RenderScarecrow extends RenderLiving {
	public RenderScarecrow(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1) {
		renderScarecrow((EntityScarecrow) entity, d, d1, d2, f, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d, double d1,
			double d2, float f, float f1) {
		renderScarecrow((EntityScarecrow) entityliving, d, d1, d2, f, f1);
	}

	@Override
	protected void preRenderCallback(EntityLiving entityliving, float f) {
		preRenderScale((EntityScarecrow) entityliving, f);
	}

	protected void preRenderScale(EntityScarecrow entity, float f) {
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	public void renderScarecrow(EntityScarecrow entityScarecrow, double d,
			double d1, double d2, float f, float f1) {
		super.doRenderLiving(entityScarecrow, d, d1, d2, f, f1);
	}
}