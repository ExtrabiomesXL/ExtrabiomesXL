package extrabiomes.renderers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import extrabiomes.blocks.BlockMiniLog;
import extrabiomes.lib.Blocks;

public class RenderMiniLog implements ISimpleBlockRenderingHandler
{
    
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
        //var4.startDrawingQuads();
        //var4.setNormal(0.0F, -1.0F, 0.0F);
        //renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, metadata));
        //var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 1.0F, 0.0F);
        renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, -1.0F);
        renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.1875D, block.getIcon(2, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(0.0F, 0.0F, 1.0F);
        renderer.renderFaceZPos(block, 0.0D, 0.0D, -0.1875D, block.getIcon(3, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(-1.0F, 0.0F, 0.0F);
        renderer.renderFaceXNeg(block, 0.1875D, 0.0D, 0.0D, block.getIcon(4, 0));
        var4.draw();
        
        var4.startDrawingQuads();
        var4.setNormal(1.0F, 0.0F, 0.0F);
        renderer.renderFaceXPos(block, -0.1875D, 0.0D, 0.0D, block.getIcon(5, 0));
        var4.draw();
        
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
    }
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        
        //renderer.enableAO = false;
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        
        renderer.aoBrightnessXYNN = 255;
        renderer.aoBrightnessXYNP = 255;
        renderer.aoBrightnessXYPN = 255;
        renderer.aoBrightnessXYPP = 255;
        
        int color = block.colorMultiplier(renderer.blockAccess, x, y, z);
        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
        tessellator.setColorOpaque_F(red, green, blue);
        
        int orientation = world.getBlockMetadata(x, y, z) & 12;
        switch (orientation)
        {
            case 4:
                renderEastWestCenter(x, y, z, block, renderer);
                
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y, z + 1))
                    renderNorthNub(x, y, z, block, renderer);
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y, z - 1))
                    renderSouthNub(x, y, z, block, renderer);
                
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y + 1, z))
                    renderUpNub(x, y, z, block, renderer);
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y - 1, z))
                    renderDownNub(x, y, z, block, renderer);
                
                break;
            case 8:
                renderNorthSouthCenter(x, y, z, block, renderer);
                
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x - 1, y, z))
                    renderEastNub(x, y, z, block, renderer);
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x + 1, y, z))
                    renderWestNub(x, y, z, block, renderer);
                
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y + 1, z))
                    renderUpNub(x, y, z, block, renderer);
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y - 1, z))
                    renderDownNub(x, y, z, block, renderer);
                
                break;
            default:
                renderUpDownCenter(x, y, z, block, renderer);
                
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y, z + 1))
                    renderNorthNub(x, y, z, block, renderer);
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x, y, z - 1))
                    renderSouthNub(x, y, z, block, renderer);
                
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x - 1, y, z))
                    renderEastNub(x, y, z, block, renderer);
                if (((BlockMiniLog) Blocks.BLOCK_LOG_SAKURA_GROVE.get()).canConnect(world, x + 1, y, z))
                    renderWestNub(x, y, z, block, renderer);
                
                break;
        }
        
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateTop = 0;
        renderer.uvRotateBottom = 0;
        renderer.uvRotateSouth = 0;
        renderer.uvRotateNorth = 0;
        
        return true;
    }
    
    private void renderNorthNub(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateTop = 0; //Posy
        renderer.uvRotateBottom = 0; //NegY
        renderer.uvRotateSouth = 1; //PosX
        renderer.uvRotateNorth = 2; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.1875D, 0.1875D, 0.8125D, 0.8125D, 0.8125D, 1.0D);
        
        // Ends
        renderer.renderFaceZPos(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceXNeg(block, x, y, z, icnSide);
        renderer.renderFaceXPos(block, x, y, z, icnSide);
        renderer.renderFaceYPos(block, x, y, z, icnSide);
        renderer.renderFaceYNeg(block, x, y, z, icnSide);
    }
    
    private void renderSouthNub(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateTop = 0; //Posy
        renderer.uvRotateBottom = 0; //NegY
        renderer.uvRotateSouth = 1; //PosX
        renderer.uvRotateNorth = 1; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.1875D, 0.1875D, 0.0D, 0.8125D, 0.8125D, 0.1875D);
        
        // Ends
        renderer.renderFaceZNeg(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceXPos(block, x, y, z, icnSide);
        renderer.renderFaceXNeg(block, x, y, z, icnSide);
        renderer.renderFaceYPos(block, x, y, z, icnSide);
        renderer.renderFaceYNeg(block, x, y, z, icnSide);
    }
    
    private void renderEastNub(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 1;
        renderer.uvRotateWest = 1;
        renderer.uvRotateTop = 1; //Posy
        renderer.uvRotateBottom = 1; //NegY
        renderer.uvRotateSouth = 0; //PosX
        renderer.uvRotateNorth = 0; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.0D, 0.1875D, 0.1875D, 0.1875D, 0.8125D, 0.8125D);
        
        // Ends
        renderer.renderFaceXNeg(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceZPos(block, x, y, z, icnSide);
        renderer.renderFaceZNeg(block, x, y, z, icnSide);
        renderer.renderFaceYPos(block, x, y, z, icnSide);
        renderer.renderFaceYNeg(block, x, y, z, icnSide);
    }
    
    private void renderWestNub(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 1;
        renderer.uvRotateWest = 1;
        renderer.uvRotateTop = 1; //Posy
        renderer.uvRotateBottom = 1; //NegY
        renderer.uvRotateSouth = 0; //PosX
        renderer.uvRotateNorth = 0; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.8125D, 0.1875D, 0.1875D, 1.0D, 0.8125D, 0.8125D);
        
        // Ends
        renderer.renderFaceXPos(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceZNeg(block, x, y, z, icnSide);
        renderer.renderFaceZPos(block, x, y, z, icnSide);
        renderer.renderFaceYPos(block, x, y, z, icnSide);
        renderer.renderFaceYNeg(block, x, y, z, icnSide);
    }
    
    private void renderUpNub(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateTop = 0; //Posy
        renderer.uvRotateBottom = 0; //NegY
        renderer.uvRotateSouth = 0; //PosX
        renderer.uvRotateNorth = 0; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.1875D, 0.8125D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
        
        // Ends
        renderer.renderFaceYPos(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceZNeg(block, x, y, z, icnSide);
        renderer.renderFaceZPos(block, x, y, z, icnSide);
        renderer.renderFaceXPos(block, x, y, z, icnSide);
        renderer.renderFaceXNeg(block, x, y, z, icnSide);
        
    }
    
    private void renderDownNub(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateTop = 0; //Posy
        renderer.uvRotateBottom = 0; //NegY
        renderer.uvRotateSouth = 0; //PosX
        renderer.uvRotateNorth = 0; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.1875D, 0.8125D);
        
        // Ends
        renderer.renderFaceYNeg(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceZNeg(block, x, y, z, icnSide);
        renderer.renderFaceZPos(block, x, y, z, icnSide);
        renderer.renderFaceXPos(block, x, y, z, icnSide);
        renderer.renderFaceXNeg(block, x, y, z, icnSide);
    }
    
    private void renderUpDownCenter(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 0;
        renderer.uvRotateWest = 0;
        renderer.uvRotateTop = 0; //Posy
        renderer.uvRotateBottom = 0; //NegY
        renderer.uvRotateSouth = 0; //PosX
        renderer.uvRotateNorth = 0; //NegX
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // RenderBounds
        renderer.setRenderBounds(0.1875D, 0.0D, 0.1875D, 0.8125D, 1.0D, 0.8125D);
        
        // Ends
        renderer.renderFaceYNeg(block, x, y, z, icnEnd);
        renderer.renderFaceYPos(block, x, y, z, icnEnd);
        
        // sides
        renderer.renderFaceZNeg(block, x, y, z, icnSide);
        renderer.renderFaceZPos(block, x, y, z, icnSide);
        renderer.renderFaceXPos(block, x, y, z, icnSide);
        renderer.renderFaceXNeg(block, x, y, z, icnSide);
    }
    
    private void renderNorthSouthCenter(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateSouth = 1;
        renderer.uvRotateNorth = 1;
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.1875D, 0.1875D, 0.0D, 0.8125D, 0.8125D, 1.0D);
        
        // Ends
        renderer.renderFaceZNeg(block, x, y, z, icnEnd);
        renderer.renderFaceZPos(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceXPos(block, x, y, z, icnSide);
        renderer.renderFaceXNeg(block, x, y, z, icnSide);
        renderer.renderFaceYPos(block, x, y, z, icnSide);
        renderer.renderFaceYNeg(block, x, y, z, icnSide);
    }
    
    private void renderEastWestCenter(int x, int y, int z, Block block, RenderBlocks renderer)
    {
        renderer.uvRotateEast = 1;
        renderer.uvRotateWest = 1;
        renderer.uvRotateTop = 1;
        renderer.uvRotateBottom = 1;
        
        IIcon icnEnd = block.getIcon(1, 0);
        IIcon icnSide = block.getIcon(2, 0);
        
        // Set the render area
        renderer.setRenderBounds(0.0D, 0.1875D, 0.1875D, 1.0D, 0.8125D, 0.8125D);
        
        // Ends
        renderer.renderFaceXPos(block, x, y, z, icnEnd);
        renderer.renderFaceXNeg(block, x, y, z, icnEnd);
        
        // Sides
        renderer.renderFaceZNeg(block, x, y, z, icnSide);
        renderer.renderFaceZPos(block, x, y, z, icnSide);
        renderer.renderFaceYPos(block, x, y, z, icnSide);
        renderer.renderFaceYNeg(block, x, y, z, icnSide);
    }
    
    @Override
    public boolean shouldRender3DInInventory(int modelId)
    {
        return true;
    }
    
    @Override
    public int getRenderId()
    {
        return 0;
    }
    
}
