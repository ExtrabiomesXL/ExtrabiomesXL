/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

import org.lwjgl.opengl.GL11;

public class RenderScarecrow extends RenderLiving {
	public RenderScarecrow(ModelBase modelbase, float f) {
		super(modelbase, f);
	}

	@Override
	public void doRender(Entity entity, double d, double d1, double d2,
			float f, float f1)
	{
		renderScarecrow((EntityScarecrow) entity, d, d1, d2, f, f1);
	}

	@Override
	public void doRenderLiving(EntityLiving entityliving, double d,
			double d1, double d2, float f, float f1)
	{
		renderScarecrow((EntityScarecrow) entityliving, d, d1, d2, f,
				f1);
	}

	@Override
	protected void preRenderCallback(EntityLiving entityliving, float f)
	{
		preRenderScale((EntityScarecrow) entityliving, f);
	}

	protected void preRenderScale(EntityScarecrow entity, float f) {
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	public void renderScarecrow(EntityScarecrow entityScarecrow,
			double d, double d1, double d2, float f, float f1)
	{
		super.doRenderLiving(entityScarecrow, d, d1, d2, f, f1);
	}
}