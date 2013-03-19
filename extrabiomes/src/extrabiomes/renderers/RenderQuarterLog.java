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

public class RenderQuarterLog implements ISimpleBlockRenderingHandler {

	@Override
	public int getRenderId() {
		return 0;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata,
			int modelID, RenderBlocks renderer)
	{
		final Tessellator var4 = Tessellator.instance;

		if (renderer.useInventoryTint) {
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
		renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(0, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(1, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(2, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(3, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(4, metadata));
		var4.draw();

		var4.startDrawingQuads();
		var4.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(5, metadata));
		var4.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y,
			int z, Block block, int modelId, RenderBlocks renderer)
	{
		final int metadata = world.getBlockMetadata(x, y, z);
		final int orientation = metadata & 12;

		if (orientation == 4) {
			renderer.uvRotateEast = 1;
			renderer.uvRotateWest = 1;
			renderer.uvRotateTop = 1;
			renderer.uvRotateBottom = 1;
		} else if (orientation == 8) {
			renderer.uvRotateSouth = 1;
			renderer.uvRotateNorth = 2;
		}

		final boolean didRender = renderer.renderStandardBlock(block,
				x, y, z);
		renderer.uvRotateSouth = 0;
		renderer.uvRotateEast = 0;
		renderer.uvRotateWest = 0;
		renderer.uvRotateNorth = 0;
		renderer.uvRotateTop = 0;
		renderer.uvRotateBottom = 0;
		return didRender;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

}
