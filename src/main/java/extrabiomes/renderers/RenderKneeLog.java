/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderKneeLog implements ISimpleBlockRenderingHandler
{
    
    @Override
    public int getRenderId()
    {
        return 0;
    }
    
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
        final Tessellator var4 = Tessellator.instance;
        
        if (renderer.useInventoryTint)
        {
            final int renderColor = block.getRenderColor(metadata);
            final float red = (renderColor >> 16 & 255) / 255.0F;
            final float green = (renderColor >> 8 & 255) / 255.0F;
            final float blue = (renderColor & 255) / 255.0F;
            GL11.glColor4f(red, green, blue, 1.0F);
        }
        
        block.setBlockBoundsForItemRender();
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        var4.startDrawingQuads();
        var4.setNormal(0.0F, -1.0F, 0.0F);
        renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, 0));
        var4.draw();
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        final int metadata = world.getBlockMetadata(x, y, z);
        
        switch (metadata)
        {
            case 0:
                renderer.uvRotateNorth = 1;
                renderer.uvRotateEast = 1;
                break;
            case 1:
                renderer.uvRotateSouth = 1;
                renderer.uvRotateEast = 1;
                break;
            case 2:
                renderer.uvRotateSouth = 1;
                renderer.uvRotateWest = 1;
                break;
            case 3:
                renderer.uvRotateNorth = 1;
                renderer.uvRotateWest = 1;
                break;
            case 4:
                renderer.uvRotateTop = 1;
                break;
            case 5:
                renderer.uvRotateTop = 1;
                break;
            case 6:
                renderer.uvRotateBottom = 1;
                break;
            case 7:
                renderer.uvRotateBottom = 1;
                renderer.uvRotateEast = 2;
                break;
            case 8:
                renderer.uvRotateWest = 2;
                break;
            case 9:
                break;
            case 10:
                break;
            case 11:
                break;
        }
        
        //renderer.flipTexture = true;
        
        //renderer.uvRotateTop = metadata & 3;
        
        //if (orientation == 4) {
        //	renderer.uvRotateEast = 1;
        //	renderer.uvRotateWest = 1;
        //	renderer.uvRotateTop = 1;
        //	renderer.uvRotateBottom = 1;
        //} else if (orientation == 8) {
        //	renderer.uvRotateSouth = 1;
        //	renderer.uvRotateNorth = 2;
        //}
        
        final boolean didRender = renderer.renderStandardBlock(block, x, y, z);
        //renderer.render
        renderer.uvRotateSouth = 0;
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateNorth = 0;
        renderer.uvRotateTop = 0;
        renderer.uvRotateBottom = 0;
        renderer.flipTexture = false;
        return didRender;
    }
    
    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }
}
