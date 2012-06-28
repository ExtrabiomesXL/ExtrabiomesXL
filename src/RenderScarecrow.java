package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderScarecrow extends RenderLiving
{
	public RenderScarecrow(ModelBase modelbase, float f)
	{
		super(modelbase, f);
	}

	public void renderScarecrow(EntityScarecrow entityScarecrow, double d, double d1, double d2, float f, float f1)
	{
		super.doRenderLiving(entityScarecrow, d, d1, d2, f, f1);
	}

	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
	{
		renderScarecrow((EntityScarecrow) entityliving, d, d1, d2, f, f1);
	}

	public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
	{
		renderScarecrow((EntityScarecrow) entity, d, d1, d2, f, f1);
	}

	protected void preRenderScale(EntityScarecrow entity, float f)
	{
		GL11.glScalef(1.25F, 1.25F, 1.25F);
	}

	protected void preRenderCallback(EntityLiving entityliving, float f)
	{
		preRenderScale((EntityScarecrow) entityliving, f);
	}
}