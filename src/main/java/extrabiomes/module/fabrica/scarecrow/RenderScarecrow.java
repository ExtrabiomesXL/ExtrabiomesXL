/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.scarecrow;

import org.lwjgl.opengl.GL11;

import extrabiomes.Extrabiomes;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScarecrow extends RenderLiving<EntityScarecrow>
{
    private static final ResourceLocation model_resource = new ResourceLocation(Extrabiomes.RESOURCE_PATH, "textures/models/scarecrow.png");
    
    public RenderScarecrow(RenderManager renderManager, ModelBase modelbase, float f)
    {
        super(renderManager, modelbase, f);
    }
    

    protected void preRenderScale(EntityScarecrow entity, float f)
    {
        GL11.glScalef(1.25F, 1.25F, 1.25F);
    }
    
    public void renderScarecrow(EntityScarecrow entityScarecrow,
            double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entityScarecrow, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(EntityScarecrow entity)
    {
        return model_resource;
        //return new ResourceLocation(Extrabiomes.TEXTURE_PATH + "scarecrow");
    }
}