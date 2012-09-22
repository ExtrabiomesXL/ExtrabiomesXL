
package extrabiomes.utility;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.RenderBlocks;
import net.minecraft.src.Tessellator;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import extrabiomes.Extrabiomes;

public class SlabRenderer implements ISimpleBlockRenderingHandler {

	private final int	renderID;

	public SlabRenderer() {
		renderID = Extrabiomes.proxy.getNextAvailableRenderId();
	}

	@Override
	public int getRenderId() {
		return renderID;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata,
			int modelID, RenderBlocks renderer)
	{
		if (renderer.useInventoryTint) {
			final int color = block.getRenderColor(metadata);

			final float red = (color >> 16 & 255) / 255.0F;
			final float green = (color >> 8 & 255) / 255.0F;
			final float blue = (color & 255) / 255.0F;
			GL11.glColor4f(red * 1.0F, green * 1.0F, blue * 1.0F, 1.0F);
		}

		block.setBlockBoundsForItemRender();
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderBottomFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(0, metadata));
		Tessellator.instance.draw();

		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderTopFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(1, metadata));
		Tessellator.instance.draw();

		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderEastFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(2, metadata));
		Tessellator.instance.draw();

		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderWestFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(3, metadata));
		Tessellator.instance.draw();

		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderNorthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(4, metadata));
		Tessellator.instance.draw();

		Tessellator.instance.startDrawingQuads();
		Tessellator.instance.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderSouthFace(block, 0.0D, 0.0D, 0.0D,
				block.getBlockTextureFromSideAndMetadata(5, metadata));
		Tessellator.instance.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	public boolean renderWithAmbientOcclusion(int x, int y, int z,
			Block block, RenderBlocks renderer, float red, float green,
			float blue)
	{
		renderer.enableAO = true;
		boolean rendered = false;
		float brightnessTopLeft = renderer.lightValueOwn;
		float brightnessBottomLeft = renderer.lightValueOwn;
		float brightnessBottomRight = renderer.lightValueOwn;
		float brightnessTopRight = renderer.lightValueOwn;
		boolean mixColorFace0 = true;
		final boolean mixColorFace1 = true;
		boolean mixColorFace2 = true;
		boolean mixColorFace3 = true;
		boolean miscColorFace4 = true;
		boolean mixColorFace5 = true;
		renderer.lightValueOwn = block.getAmbientOcclusionLightValue(
				renderer.blockAccess, x, y, z);
		renderer.aoLightValueXNeg = block
				.getAmbientOcclusionLightValue(renderer.blockAccess,
						x - 1, y, z);
		renderer.aoLightValueYNeg = block
				.getAmbientOcclusionLightValue(renderer.blockAccess, x,
						y - 1, z);
		renderer.aoLightValueZNeg = block
				.getAmbientOcclusionLightValue(renderer.blockAccess, x,
						y, z - 1);
		renderer.aoLightValueXPos = block
				.getAmbientOcclusionLightValue(renderer.blockAccess,
						x + 1, y, z);
		renderer.aoLightValueYPos = block
				.getAmbientOcclusionLightValue(renderer.blockAccess, x,
						y + 1, z);
		renderer.aoLightValueZPos = block
				.getAmbientOcclusionLightValue(renderer.blockAccess, x,
						y, z + 1);
		block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		final int mixedBrightnessXNeg = block
				.getMixedBrightnessForBlock(renderer.blockAccess,
						x - 1, y, z);
		final int mixedBrightnessYNeg = block
				.getMixedBrightnessForBlock(renderer.blockAccess, x,
						y - 1, z);
		final int mixedBrightnessZNeg = block
				.getMixedBrightnessForBlock(renderer.blockAccess, x, y,
						z - 1);
		final int mixedBrightnessXPos = block
				.getMixedBrightnessForBlock(renderer.blockAccess,
						x + 1, y, z);
		final int mixedBrightnessYPos = block
				.getMixedBrightnessForBlock(renderer.blockAccess, x,
						y + 1, z);
		final int mixedBrightnessZPos = block
				.getMixedBrightnessForBlock(renderer.blockAccess, x, y,
						z + 1);

		final Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(983055);
		renderer.aoGrassXYZPPC = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x + 1, y + 1, z)];
		renderer.aoGrassXYZPNC = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x + 1, y - 1, z)];
		renderer.aoGrassXYZPCP = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x + 1, y, z + 1)];
		renderer.aoGrassXYZPCN = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x + 1, y, z - 1)];
		renderer.aoGrassXYZNPC = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x - 1, y + 1, z)];
		renderer.aoGrassXYZNNC = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x - 1, y - 1, z)];
		renderer.aoGrassXYZNCN = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x - 1, y, z - 1)];
		renderer.aoGrassXYZNCP = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x - 1, y, z + 1)];
		renderer.aoGrassXYZCPP = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x, y + 1, z + 1)];
		renderer.aoGrassXYZCPN = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x, y + 1, z - 1)];
		renderer.aoGrassXYZCNP = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x, y - 1, z + 1)];
		renderer.aoGrassXYZCNN = Block.canBlockGrass[renderer.blockAccess
				.getBlockId(x, y - 1, z - 1)];

		if (renderer.overrideBlockTexture >= 0) {
			mixColorFace5 = false;
			miscColorFace4 = false;
			mixColorFace3 = false;
			mixColorFace2 = false;
			mixColorFace0 = false;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y - 1, z, 0))
		{
			if (renderer.aoType > 0) {
				// if (block.minY <= 0.0D)
				// {
				--y;
				// }

				renderer.aoBrightnessXYNN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoBrightnessYZNN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoBrightnessYZNP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoBrightnessXYPN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x + 1, y, z);
				renderer.aoLightValueScratchXYNN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoLightValueScratchYZNN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoLightValueScratchYZNP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoLightValueScratchXYPN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x + 1, y, z);

				if (!renderer.aoGrassXYZCNN && !renderer.aoGrassXYZNNC)
				{
					renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXYNN;
					renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXYNN;
				} else {
					renderer.aoLightValueScratchXYZNNN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y,
									z - 1);
					renderer.aoBrightnessXYZNNN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y,
									z - 1);
				}

				if (!renderer.aoGrassXYZCNP && !renderer.aoGrassXYZNNC)
				{
					renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXYNN;
					renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXYNN;
				} else {
					renderer.aoLightValueScratchXYZNNP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y,
									z + 1);
					renderer.aoBrightnessXYZNNP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y,
									z + 1);
				}

				if (!renderer.aoGrassXYZCNN && !renderer.aoGrassXYZPNC)
				{
					renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXYPN;
					renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXYPN;
				} else {
					renderer.aoLightValueScratchXYZPNN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y,
									z - 1);
					renderer.aoBrightnessXYZPNN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y,
									z - 1);
				}

				if (!renderer.aoGrassXYZCNP && !renderer.aoGrassXYZPNC)
				{
					renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXYPN;
					renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXYPN;
				} else {
					renderer.aoLightValueScratchXYZPNP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y,
									z + 1);
					renderer.aoBrightnessXYZPNP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y,
									z + 1);
				}

				// if (block.minY <= 0.0D)
				// {
				++y;
				// }

				brightnessTopLeft = (renderer.aoLightValueScratchXYZNNP
						+ renderer.aoLightValueScratchXYNN
						+ renderer.aoLightValueScratchYZNP + renderer.aoLightValueYNeg) / 4.0F;
				brightnessTopRight = (renderer.aoLightValueScratchYZNP
						+ renderer.aoLightValueYNeg
						+ renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXYPN) / 4.0F;
				brightnessBottomRight = (renderer.aoLightValueYNeg
						+ renderer.aoLightValueScratchYZNN
						+ renderer.aoLightValueScratchXYPN + renderer.aoLightValueScratchXYZPNN) / 4.0F;
				brightnessBottomLeft = (renderer.aoLightValueScratchXYNN
						+ renderer.aoLightValueScratchXYZNNN
						+ renderer.aoLightValueYNeg + renderer.aoLightValueScratchYZNN) / 4.0F;
				renderer.brightnessTopLeft = renderer.getAoBrightness(
						renderer.aoBrightnessXYZNNP,
						renderer.aoBrightnessXYNN,
						renderer.aoBrightnessYZNP, mixedBrightnessYNeg);
				renderer.brightnessTopRight = renderer.getAoBrightness(
						renderer.aoBrightnessYZNP,
						renderer.aoBrightnessXYZPNP,
						renderer.aoBrightnessXYPN, mixedBrightnessYNeg);
				renderer.brightnessBottomRight = renderer
						.getAoBrightness(renderer.aoBrightnessYZNN,
								renderer.aoBrightnessXYPN,
								renderer.aoBrightnessXYZPNN,
								mixedBrightnessYNeg);
				renderer.brightnessBottomLeft = renderer
						.getAoBrightness(renderer.aoBrightnessXYNN,
								renderer.aoBrightnessXYZNNN,
								renderer.aoBrightnessYZNN,
								mixedBrightnessYNeg);
			} else {
				brightnessTopRight = renderer.aoLightValueYNeg;
				brightnessBottomRight = renderer.aoLightValueYNeg;
				brightnessBottomLeft = renderer.aoLightValueYNeg;
				brightnessTopLeft = renderer.aoLightValueYNeg;
				renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = renderer.aoBrightnessXYNN;
			}

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (mixColorFace0 ? red
					: 1.0F) * 0.5F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (mixColorFace0 ? green
					: 1.0F) * 0.5F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (mixColorFace0 ? blue
					: 1.0F) * 0.5F;
			renderer.colorRedTopLeft *= brightnessTopLeft;
			renderer.colorGreenTopLeft *= brightnessTopLeft;
			renderer.colorBlueTopLeft *= brightnessTopLeft;
			renderer.colorRedBottomLeft *= brightnessBottomLeft;
			renderer.colorGreenBottomLeft *= brightnessBottomLeft;
			renderer.colorBlueBottomLeft *= brightnessBottomLeft;
			renderer.colorRedBottomRight *= brightnessBottomRight;
			renderer.colorGreenBottomRight *= brightnessBottomRight;
			renderer.colorBlueBottomRight *= brightnessBottomRight;
			renderer.colorRedTopRight *= brightnessTopRight;
			renderer.colorGreenTopRight *= brightnessTopRight;
			renderer.colorBlueTopRight *= brightnessTopRight;
			renderer.renderBottomFace(block, x, y, z, block
					.getBlockTexture(renderer.blockAccess, x, y, z, 0));
			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y + 1, z, 1))
		{
			if (renderer.aoType > 0) {
				// if (block.maxY >= 1.0D)
				// {
				++y;
				// }

				renderer.aoBrightnessXYNP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoBrightnessXYPP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x + 1, y, z);
				renderer.aoBrightnessYZPN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoBrightnessYZPP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoLightValueScratchXYNP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoLightValueScratchXYPP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x + 1, y, z);
				renderer.aoLightValueScratchYZPN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoLightValueScratchYZPP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z + 1);

				if (!renderer.aoGrassXYZCPN && !renderer.aoGrassXYZNPC)
				{
					renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXYNP;
					renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXYNP;
				} else {
					renderer.aoLightValueScratchXYZNPN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y,
									z - 1);
					renderer.aoBrightnessXYZNPN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y,
									z - 1);
				}

				if (!renderer.aoGrassXYZCPN && !renderer.aoGrassXYZPPC)
				{
					renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXYPP;
					renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXYPP;
				} else {
					renderer.aoLightValueScratchXYZPPN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y,
									z - 1);
					renderer.aoBrightnessXYZPPN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y,
									z - 1);
				}

				if (!renderer.aoGrassXYZCPP && !renderer.aoGrassXYZNPC)
				{
					renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXYNP;
					renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXYNP;
				} else {
					renderer.aoLightValueScratchXYZNPP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y,
									z + 1);
					renderer.aoBrightnessXYZNPP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y,
									z + 1);
				}

				if (!renderer.aoGrassXYZCPP && !renderer.aoGrassXYZPPC)
				{
					renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXYPP;
					renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXYPP;
				} else {
					renderer.aoLightValueScratchXYZPPP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y,
									z + 1);
					renderer.aoBrightnessXYZPPP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y,
									z + 1);
				}

				// if (block.maxY >= 1.0D)
				// {
				--y;
				// }

				brightnessTopRight = (renderer.aoLightValueScratchXYZNPP
						+ renderer.aoLightValueScratchXYNP
						+ renderer.aoLightValueScratchYZPP + renderer.aoLightValueYPos) / 4.0F;
				brightnessTopLeft = (renderer.aoLightValueScratchYZPP
						+ renderer.aoLightValueYPos
						+ renderer.aoLightValueScratchXYZPPP + renderer.aoLightValueScratchXYPP) / 4.0F;
				brightnessBottomLeft = (renderer.aoLightValueYPos
						+ renderer.aoLightValueScratchYZPN
						+ renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPN) / 4.0F;
				brightnessBottomRight = (renderer.aoLightValueScratchXYNP
						+ renderer.aoLightValueScratchXYZNPN
						+ renderer.aoLightValueYPos + renderer.aoLightValueScratchYZPN) / 4.0F;
				renderer.brightnessTopRight = renderer.getAoBrightness(
						renderer.aoBrightnessXYZNPP,
						renderer.aoBrightnessXYNP,
						renderer.aoBrightnessYZPP, mixedBrightnessYPos);
				renderer.brightnessTopLeft = renderer.getAoBrightness(
						renderer.aoBrightnessYZPP,
						renderer.aoBrightnessXYZPPP,
						renderer.aoBrightnessXYPP, mixedBrightnessYPos);
				renderer.brightnessBottomLeft = renderer
						.getAoBrightness(renderer.aoBrightnessYZPN,
								renderer.aoBrightnessXYPP,
								renderer.aoBrightnessXYZPPN,
								mixedBrightnessYPos);
				renderer.brightnessBottomRight = renderer
						.getAoBrightness(renderer.aoBrightnessXYNP,
								renderer.aoBrightnessXYZNPN,
								renderer.aoBrightnessYZPN,
								mixedBrightnessYPos);
			} else {
				brightnessTopRight = renderer.aoLightValueYPos;
				brightnessBottomRight = renderer.aoLightValueYPos;
				brightnessBottomLeft = renderer.aoLightValueYPos;
				brightnessTopLeft = renderer.aoLightValueYPos;
				renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = mixedBrightnessYPos;
			}

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = mixColorFace1 ? red
					: 1.0F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = mixColorFace1 ? green
					: 1.0F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = mixColorFace1 ? blue
					: 1.0F;
			renderer.colorRedTopLeft *= brightnessTopLeft;
			renderer.colorGreenTopLeft *= brightnessTopLeft;
			renderer.colorBlueTopLeft *= brightnessTopLeft;
			renderer.colorRedBottomLeft *= brightnessBottomLeft;
			renderer.colorGreenBottomLeft *= brightnessBottomLeft;
			renderer.colorBlueBottomLeft *= brightnessBottomLeft;
			renderer.colorRedBottomRight *= brightnessBottomRight;
			renderer.colorGreenBottomRight *= brightnessBottomRight;
			renderer.colorBlueBottomRight *= brightnessBottomRight;
			renderer.colorRedTopRight *= brightnessTopRight;
			renderer.colorGreenTopRight *= brightnessTopRight;
			renderer.colorBlueTopRight *= brightnessTopRight;
			renderer.renderTopFace(block, x, y, z, block
					.getBlockTexture(renderer.blockAccess, x, y, z, 1));
			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y, z - 1, 2))
		{
			if (renderer.aoType > 0) {
				if (block.minZ <= 0.0D) --z;

				renderer.aoLightValueScratchXZNN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoLightValueScratchYZNN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoLightValueScratchYZPN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y + 1, z);
				renderer.aoLightValueScratchXZPN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x + 1, y, z);
				renderer.aoBrightnessXZNN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoBrightnessYZNN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoBrightnessYZPN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y + 1, z);
				renderer.aoBrightnessXZPN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x + 1, y, z);

				if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZCNN)
				{
					renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
					renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
				} else {
					renderer.aoLightValueScratchXYZNNN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y - 1,
									z);
					renderer.aoBrightnessXYZNNN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y - 1,
									z);
				}

				if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZCPN)
				{
					renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
					renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
				} else {
					renderer.aoLightValueScratchXYZNPN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y + 1,
									z);
					renderer.aoBrightnessXYZNPN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y + 1,
									z);
				}

				if (!renderer.aoGrassXYZPCN && !renderer.aoGrassXYZCNN)
				{
					renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
					renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
				} else {
					renderer.aoLightValueScratchXYZPNN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y - 1,
									z);
					renderer.aoBrightnessXYZPNN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y - 1,
									z);
				}

				if (!renderer.aoGrassXYZPCN && !renderer.aoGrassXYZCPN)
				{
					renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
					renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
				} else {
					renderer.aoLightValueScratchXYZPPN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y + 1,
									z);
					renderer.aoBrightnessXYZPPN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y + 1,
									z);
				}

				if (block.minZ <= 0.0D) ++z;

				brightnessTopLeft = (renderer.aoLightValueScratchXZNN
						+ renderer.aoLightValueScratchXYZNPN
						+ renderer.aoLightValueZNeg + renderer.aoLightValueScratchYZPN) / 4.0F;
				brightnessBottomLeft = (renderer.aoLightValueZNeg
						+ renderer.aoLightValueScratchYZPN
						+ renderer.aoLightValueScratchXZPN + renderer.aoLightValueScratchXYZPPN) / 4.0F;
				brightnessBottomRight = (renderer.aoLightValueScratchYZNN
						+ renderer.aoLightValueZNeg
						+ renderer.aoLightValueScratchXYZPNN + renderer.aoLightValueScratchXZPN) / 4.0F;
				brightnessTopRight = (renderer.aoLightValueScratchXYZNNN
						+ renderer.aoLightValueScratchXZNN
						+ renderer.aoLightValueScratchYZNN + renderer.aoLightValueZNeg) / 4.0F;
				renderer.brightnessTopLeft = renderer.getAoBrightness(
						renderer.aoBrightnessXZNN,
						renderer.aoBrightnessXYZNPN,
						renderer.aoBrightnessYZPN, mixedBrightnessZNeg);
				renderer.brightnessBottomLeft = renderer
						.getAoBrightness(renderer.aoBrightnessYZPN,
								renderer.aoBrightnessXZPN,
								renderer.aoBrightnessXYZPPN,
								mixedBrightnessZNeg);
				renderer.brightnessBottomRight = renderer
						.getAoBrightness(renderer.aoBrightnessYZNN,
								renderer.aoBrightnessXYZPNN,
								renderer.aoBrightnessXZPN,
								mixedBrightnessZNeg);
				renderer.brightnessTopRight = renderer.getAoBrightness(
						renderer.aoBrightnessXYZNNN,
						renderer.aoBrightnessXZNN,
						renderer.aoBrightnessYZNN, mixedBrightnessZNeg);
			} else {
				brightnessTopRight = renderer.aoLightValueZNeg;
				brightnessBottomRight = renderer.aoLightValueZNeg;
				brightnessBottomLeft = renderer.aoLightValueZNeg;
				brightnessTopLeft = renderer.aoLightValueZNeg;
				renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = mixedBrightnessZNeg;
			}

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (mixColorFace2 ? red
					: 1.0F) * 0.8F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (mixColorFace2 ? green
					: 1.0F) * 0.8F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (mixColorFace2 ? blue
					: 1.0F) * 0.8F;
			renderer.colorRedTopLeft *= brightnessTopLeft;
			renderer.colorGreenTopLeft *= brightnessTopLeft;
			renderer.colorBlueTopLeft *= brightnessTopLeft;
			renderer.colorRedBottomLeft *= brightnessBottomLeft;
			renderer.colorGreenBottomLeft *= brightnessBottomLeft;
			renderer.colorBlueBottomLeft *= brightnessBottomLeft;
			renderer.colorRedBottomRight *= brightnessBottomRight;
			renderer.colorGreenBottomRight *= brightnessBottomRight;
			renderer.colorBlueBottomRight *= brightnessBottomRight;
			renderer.colorRedTopRight *= brightnessTopRight;
			renderer.colorGreenTopRight *= brightnessTopRight;
			renderer.colorBlueTopRight *= brightnessTopRight;
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 2);
			renderer.renderEastFace(block, x, y, z, texture);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y, z + 1, 3))
		{
			if (renderer.aoType > 0) {
				if (block.maxZ >= 1.0D) ++z;

				renderer.aoLightValueScratchXZNP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoLightValueScratchXZPP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x + 1, y, z);
				renderer.aoLightValueScratchYZNP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoLightValueScratchYZPP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y + 1, z);
				renderer.aoBrightnessXZNP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x - 1, y, z);
				renderer.aoBrightnessXZPP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x + 1, y, z);
				renderer.aoBrightnessYZNP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoBrightnessYZPP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y + 1, z);

				if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZCNP)
				{
					renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
					renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
				} else {
					renderer.aoLightValueScratchXYZNNP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y - 1,
									z);
					renderer.aoBrightnessXYZNNP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y - 1,
									z);
				}

				if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZCPP)
				{
					renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
					renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
				} else {
					renderer.aoLightValueScratchXYZNPP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x - 1, y + 1,
									z);
					renderer.aoBrightnessXYZNPP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x - 1, y + 1,
									z);
				}

				if (!renderer.aoGrassXYZPCP && !renderer.aoGrassXYZCNP)
				{
					renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
					renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
				} else {
					renderer.aoLightValueScratchXYZPNP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y - 1,
									z);
					renderer.aoBrightnessXYZPNP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y - 1,
									z);
				}

				if (!renderer.aoGrassXYZPCP && !renderer.aoGrassXYZCPP)
				{
					renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
					renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
				} else {
					renderer.aoLightValueScratchXYZPPP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x + 1, y + 1,
									z);
					renderer.aoBrightnessXYZPPP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x + 1, y + 1,
									z);
				}

				if (block.maxZ >= 1.0D) --z;

				brightnessTopLeft = (renderer.aoLightValueScratchXZNP
						+ renderer.aoLightValueScratchXYZNPP
						+ renderer.aoLightValueZPos + renderer.aoLightValueScratchYZPP) / 4.0F;
				brightnessTopRight = (renderer.aoLightValueZPos
						+ renderer.aoLightValueScratchYZPP
						+ renderer.aoLightValueScratchXZPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
				brightnessBottomRight = (renderer.aoLightValueScratchYZNP
						+ renderer.aoLightValueZPos
						+ renderer.aoLightValueScratchXYZPNP + renderer.aoLightValueScratchXZPP) / 4.0F;
				brightnessBottomLeft = (renderer.aoLightValueScratchXYZNNP
						+ renderer.aoLightValueScratchXZNP
						+ renderer.aoLightValueScratchYZNP + renderer.aoLightValueZPos) / 4.0F;
				renderer.brightnessTopLeft = renderer.getAoBrightness(
						renderer.aoBrightnessXZNP,
						renderer.aoBrightnessXYZNPP,
						renderer.aoBrightnessYZPP, mixedBrightnessZPos);
				renderer.brightnessTopRight = renderer.getAoBrightness(
						renderer.aoBrightnessYZPP,
						renderer.aoBrightnessXZPP,
						renderer.aoBrightnessXYZPPP,
						mixedBrightnessZPos);
				renderer.brightnessBottomRight = renderer
						.getAoBrightness(renderer.aoBrightnessYZNP,
								renderer.aoBrightnessXYZPNP,
								renderer.aoBrightnessXZPP,
								mixedBrightnessZPos);
				renderer.brightnessBottomLeft = renderer
						.getAoBrightness(renderer.aoBrightnessXYZNNP,
								renderer.aoBrightnessXZNP,
								renderer.aoBrightnessYZNP,
								mixedBrightnessZPos);
			} else {
				brightnessTopRight = renderer.aoLightValueZPos;
				brightnessBottomRight = renderer.aoLightValueZPos;
				brightnessBottomLeft = renderer.aoLightValueZPos;
				brightnessTopLeft = renderer.aoLightValueZPos;
				renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = mixedBrightnessZPos;
			}

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (mixColorFace3 ? red
					: 1.0F) * 0.8F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (mixColorFace3 ? green
					: 1.0F) * 0.8F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (mixColorFace3 ? blue
					: 1.0F) * 0.8F;
			renderer.colorRedTopLeft *= brightnessTopLeft;
			renderer.colorGreenTopLeft *= brightnessTopLeft;
			renderer.colorBlueTopLeft *= brightnessTopLeft;
			renderer.colorRedBottomLeft *= brightnessBottomLeft;
			renderer.colorGreenBottomLeft *= brightnessBottomLeft;
			renderer.colorBlueBottomLeft *= brightnessBottomLeft;
			renderer.colorRedBottomRight *= brightnessBottomRight;
			renderer.colorGreenBottomRight *= brightnessBottomRight;
			renderer.colorBlueBottomRight *= brightnessBottomRight;
			renderer.colorRedTopRight *= brightnessTopRight;
			renderer.colorGreenTopRight *= brightnessTopRight;
			renderer.colorBlueTopRight *= brightnessTopRight;
			block.getBlockTexture(renderer.blockAccess, x, y, z, 3);
			renderer.renderWestFace(block, x, y, z, block
					.getBlockTexture(renderer.blockAccess, x, y, z, 3));

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess,
						x - 1, y, z, 4))
		{
			if (renderer.aoType > 0) {
				if (block.minX <= 0.0D) --x;

				renderer.aoLightValueScratchXYNN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoLightValueScratchXZNN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoLightValueScratchXZNP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoLightValueScratchXYNP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y + 1, z);
				renderer.aoBrightnessXYNN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoBrightnessXZNN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoBrightnessXZNP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoBrightnessXYNP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y + 1, z);

				if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZNNC)
				{
					renderer.aoLightValueScratchXYZNNN = renderer.aoLightValueScratchXZNN;
					renderer.aoBrightnessXYZNNN = renderer.aoBrightnessXZNN;
				} else {
					renderer.aoLightValueScratchXYZNNN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y - 1,
									z - 1);
					renderer.aoBrightnessXYZNNN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y - 1,
									z - 1);
				}

				if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZNNC)
				{
					renderer.aoLightValueScratchXYZNNP = renderer.aoLightValueScratchXZNP;
					renderer.aoBrightnessXYZNNP = renderer.aoBrightnessXZNP;
				} else {
					renderer.aoLightValueScratchXYZNNP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y - 1,
									z + 1);
					renderer.aoBrightnessXYZNNP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y - 1,
									z + 1);
				}

				if (!renderer.aoGrassXYZNCN && !renderer.aoGrassXYZNPC)
				{
					renderer.aoLightValueScratchXYZNPN = renderer.aoLightValueScratchXZNN;
					renderer.aoBrightnessXYZNPN = renderer.aoBrightnessXZNN;
				} else {
					renderer.aoLightValueScratchXYZNPN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y + 1,
									z - 1);
					renderer.aoBrightnessXYZNPN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y + 1,
									z - 1);
				}

				if (!renderer.aoGrassXYZNCP && !renderer.aoGrassXYZNPC)
				{
					renderer.aoLightValueScratchXYZNPP = renderer.aoLightValueScratchXZNP;
					renderer.aoBrightnessXYZNPP = renderer.aoBrightnessXZNP;
				} else {
					renderer.aoLightValueScratchXYZNPP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y + 1,
									z + 1);
					renderer.aoBrightnessXYZNPP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y + 1,
									z + 1);
				}

				if (block.minX <= 0.0D) ++x;

				brightnessTopRight = (renderer.aoLightValueScratchXYNN
						+ renderer.aoLightValueScratchXYZNNP
						+ renderer.aoLightValueXNeg + renderer.aoLightValueScratchXZNP) / 4.0F;
				brightnessTopLeft = (renderer.aoLightValueXNeg
						+ renderer.aoLightValueScratchXZNP
						+ renderer.aoLightValueScratchXYNP + renderer.aoLightValueScratchXYZNPP) / 4.0F;
				brightnessBottomLeft = (renderer.aoLightValueScratchXZNN
						+ renderer.aoLightValueXNeg
						+ renderer.aoLightValueScratchXYZNPN + renderer.aoLightValueScratchXYNP) / 4.0F;
				brightnessBottomRight = (renderer.aoLightValueScratchXYZNNN
						+ renderer.aoLightValueScratchXYNN
						+ renderer.aoLightValueScratchXZNN + renderer.aoLightValueXNeg) / 4.0F;
				renderer.brightnessTopRight = renderer.getAoBrightness(
						renderer.aoBrightnessXYNN,
						renderer.aoBrightnessXYZNNP,
						renderer.aoBrightnessXZNP, mixedBrightnessXNeg);
				renderer.brightnessTopLeft = renderer.getAoBrightness(
						renderer.aoBrightnessXZNP,
						renderer.aoBrightnessXYNP,
						renderer.aoBrightnessXYZNPP,
						mixedBrightnessXNeg);
				renderer.brightnessBottomLeft = renderer
						.getAoBrightness(renderer.aoBrightnessXZNN,
								renderer.aoBrightnessXYZNPN,
								renderer.aoBrightnessXYNP,
								mixedBrightnessXNeg);
				renderer.brightnessBottomRight = renderer
						.getAoBrightness(renderer.aoBrightnessXYZNNN,
								renderer.aoBrightnessXYNN,
								renderer.aoBrightnessXZNN,
								mixedBrightnessXNeg);
			} else {
				brightnessTopRight = renderer.aoLightValueXNeg;
				brightnessBottomRight = renderer.aoLightValueXNeg;
				brightnessBottomLeft = renderer.aoLightValueXNeg;
				brightnessTopLeft = renderer.aoLightValueXNeg;
				renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = mixedBrightnessXNeg;
			}

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (miscColorFace4 ? red
					: 1.0F) * 0.6F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (miscColorFace4 ? green
					: 1.0F) * 0.6F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (miscColorFace4 ? blue
					: 1.0F) * 0.6F;
			renderer.colorRedTopLeft *= brightnessTopLeft;
			renderer.colorGreenTopLeft *= brightnessTopLeft;
			renderer.colorBlueTopLeft *= brightnessTopLeft;
			renderer.colorRedBottomLeft *= brightnessBottomLeft;
			renderer.colorGreenBottomLeft *= brightnessBottomLeft;
			renderer.colorBlueBottomLeft *= brightnessBottomLeft;
			renderer.colorRedBottomRight *= brightnessBottomRight;
			renderer.colorGreenBottomRight *= brightnessBottomRight;
			renderer.colorBlueBottomRight *= brightnessBottomRight;
			renderer.colorRedTopRight *= brightnessTopRight;
			renderer.colorGreenTopRight *= brightnessTopRight;
			renderer.colorBlueTopRight *= brightnessTopRight;
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 4);
			renderer.renderNorthFace(block, x, y, z, texture);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess,
						x + 1, y, z, 5))
		{
			if (renderer.aoType > 0) {
				if (block.maxX >= 1.0D) ++x;

				renderer.aoLightValueScratchXYPN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoLightValueScratchXZPN = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoLightValueScratchXZPP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoLightValueScratchXYPP = block
						.getAmbientOcclusionLightValue(
								renderer.blockAccess, x, y + 1, z);
				renderer.aoBrightnessXYPN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y - 1, z);
				renderer.aoBrightnessXZPN = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z - 1);
				renderer.aoBrightnessXZPP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y, z + 1);
				renderer.aoBrightnessXYPP = block
						.getMixedBrightnessForBlock(
								renderer.blockAccess, x, y + 1, z);

				if (!renderer.aoGrassXYZPNC && !renderer.aoGrassXYZPCN)
				{
					renderer.aoLightValueScratchXYZPNN = renderer.aoLightValueScratchXZPN;
					renderer.aoBrightnessXYZPNN = renderer.aoBrightnessXZPN;
				} else {
					renderer.aoLightValueScratchXYZPNN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y - 1,
									z - 1);
					renderer.aoBrightnessXYZPNN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y - 1,
									z - 1);
				}

				if (!renderer.aoGrassXYZPNC && !renderer.aoGrassXYZPCP)
				{
					renderer.aoLightValueScratchXYZPNP = renderer.aoLightValueScratchXZPP;
					renderer.aoBrightnessXYZPNP = renderer.aoBrightnessXZPP;
				} else {
					renderer.aoLightValueScratchXYZPNP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y - 1,
									z + 1);
					renderer.aoBrightnessXYZPNP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y - 1,
									z + 1);
				}

				if (!renderer.aoGrassXYZPPC && !renderer.aoGrassXYZPCN)
				{
					renderer.aoLightValueScratchXYZPPN = renderer.aoLightValueScratchXZPN;
					renderer.aoBrightnessXYZPPN = renderer.aoBrightnessXZPN;
				} else {
					renderer.aoLightValueScratchXYZPPN = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y + 1,
									z - 1);
					renderer.aoBrightnessXYZPPN = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y + 1,
									z - 1);
				}

				if (!renderer.aoGrassXYZPPC && !renderer.aoGrassXYZPCP)
				{
					renderer.aoLightValueScratchXYZPPP = renderer.aoLightValueScratchXZPP;
					renderer.aoBrightnessXYZPPP = renderer.aoBrightnessXZPP;
				} else {
					renderer.aoLightValueScratchXYZPPP = block
							.getAmbientOcclusionLightValue(
									renderer.blockAccess, x, y + 1,
									z + 1);
					renderer.aoBrightnessXYZPPP = block
							.getMixedBrightnessForBlock(
									renderer.blockAccess, x, y + 1,
									z + 1);
				}

				if (block.maxX >= 1.0D) --x;

				brightnessTopLeft = (renderer.aoLightValueScratchXYPN
						+ renderer.aoLightValueScratchXYZPNP
						+ renderer.aoLightValueXPos + renderer.aoLightValueScratchXZPP) / 4.0F;
				brightnessTopRight = (renderer.aoLightValueXPos
						+ renderer.aoLightValueScratchXZPP
						+ renderer.aoLightValueScratchXYPP + renderer.aoLightValueScratchXYZPPP) / 4.0F;
				brightnessBottomRight = (renderer.aoLightValueScratchXZPN
						+ renderer.aoLightValueXPos
						+ renderer.aoLightValueScratchXYZPPN + renderer.aoLightValueScratchXYPP) / 4.0F;
				brightnessBottomLeft = (renderer.aoLightValueScratchXYZPNN
						+ renderer.aoLightValueScratchXYPN
						+ renderer.aoLightValueScratchXZPN + renderer.aoLightValueXPos) / 4.0F;
				renderer.brightnessTopLeft = renderer.getAoBrightness(
						renderer.aoBrightnessXYPN,
						renderer.aoBrightnessXYZPNP,
						renderer.aoBrightnessXZPP, mixedBrightnessXPos);
				renderer.brightnessTopRight = renderer.getAoBrightness(
						renderer.aoBrightnessXZPP,
						renderer.aoBrightnessXYPP,
						renderer.aoBrightnessXYZPPP,
						mixedBrightnessXPos);
				renderer.brightnessBottomRight = renderer
						.getAoBrightness(renderer.aoBrightnessXZPN,
								renderer.aoBrightnessXYZPPN,
								renderer.aoBrightnessXYPP,
								mixedBrightnessXPos);
				renderer.brightnessBottomLeft = renderer
						.getAoBrightness(renderer.aoBrightnessXYZPNN,
								renderer.aoBrightnessXYPN,
								renderer.aoBrightnessXZPN,
								mixedBrightnessXPos);
			} else {
				brightnessTopRight = renderer.aoLightValueXPos;
				brightnessBottomRight = renderer.aoLightValueXPos;
				brightnessBottomLeft = renderer.aoLightValueXPos;
				brightnessTopLeft = renderer.aoLightValueXPos;
				renderer.brightnessTopLeft = renderer.brightnessBottomLeft = renderer.brightnessBottomRight = renderer.brightnessTopRight = mixedBrightnessXPos;
			}

			renderer.colorRedTopLeft = renderer.colorRedBottomLeft = renderer.colorRedBottomRight = renderer.colorRedTopRight = (mixColorFace5 ? red
					: 1.0F) * 0.6F;
			renderer.colorGreenTopLeft = renderer.colorGreenBottomLeft = renderer.colorGreenBottomRight = renderer.colorGreenTopRight = (mixColorFace5 ? green
					: 1.0F) * 0.6F;
			renderer.colorBlueTopLeft = renderer.colorBlueBottomLeft = renderer.colorBlueBottomRight = renderer.colorBlueTopRight = (mixColorFace5 ? blue
					: 1.0F) * 0.6F;
			renderer.colorRedTopLeft *= brightnessTopLeft;
			renderer.colorGreenTopLeft *= brightnessTopLeft;
			renderer.colorBlueTopLeft *= brightnessTopLeft;
			renderer.colorRedBottomLeft *= brightnessBottomLeft;
			renderer.colorGreenBottomLeft *= brightnessBottomLeft;
			renderer.colorBlueBottomLeft *= brightnessBottomLeft;
			renderer.colorRedBottomRight *= brightnessBottomRight;
			renderer.colorGreenBottomRight *= brightnessBottomRight;
			renderer.colorBlueBottomRight *= brightnessBottomRight;
			renderer.colorRedTopRight *= brightnessTopRight;
			renderer.colorGreenTopRight *= brightnessTopRight;
			renderer.colorBlueTopRight *= brightnessTopRight;
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 5);
			renderer.renderSouthFace(block, x, y, z, texture);

			rendered = true;
		}

		renderer.enableAO = false;
		return rendered;
	}

	public boolean renderWithColorMultiplier(IBlockAccess world, int x,
			int y, int z, Block block, RenderBlocks renderer,
			float red, float green, float blue)
	{
		renderer.enableAO = false;
		final Tessellator tessellator = Tessellator.instance;
		boolean rendered = false;
		final float bottomMultiplier = 0.5F;
		final float multiplierNS = 0.8F;
		final float multiplierEW = 0.6F;
		final float topRed = red;
		final float topGreen = green;
		final float topBlue = blue;
		float bottomRed = bottomMultiplier;
		float redNS = multiplierNS;
		float redEW = multiplierEW;
		float bottomGreen = bottomMultiplier;
		float greenNS = multiplierNS;
		float greenEW = multiplierEW;
		float bottomBlue = bottomMultiplier;
		float blueNS = multiplierNS;
		float blueEW = multiplierEW;

		bottomRed = bottomMultiplier * red;
		redNS = multiplierNS * red;
		redEW = multiplierEW * red;
		bottomGreen = bottomMultiplier * green;
		greenNS = multiplierNS * green;
		greenEW = multiplierEW * green;
		bottomBlue = bottomMultiplier * blue;
		blueNS = multiplierNS * blue;
		blueEW = multiplierEW * blue;

		final int brightness = block.getMixedBrightnessForBlock(
				renderer.blockAccess, x, y, z);

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y - 1, z, 0))
		{
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y - 1, z));
			tessellator.setColorOpaque_F(bottomRed, bottomGreen,
					bottomBlue);
			renderer.renderBottomFace(block, x, y, z, block
					.getBlockTexture(renderer.blockAccess, x, y, z, 0));
			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y + 1, z, 1))
		{
			tessellator.setBrightness(block.getMixedBrightnessForBlock(
					renderer.blockAccess, x, y + 1, z));
			tessellator.setColorOpaque_F(topRed, topGreen, topBlue);
			renderer.renderTopFace(block, x, y, z, block
					.getBlockTexture(renderer.blockAccess, x, y, z, 1));
			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y, z - 1, 2))
		{
			tessellator.setBrightness(block.minZ > 0.0D ? brightness
					: block.getMixedBrightnessForBlock(
							renderer.blockAccess, x, y, z - 1));
			tessellator.setColorOpaque_F(redNS, greenNS, blueNS);
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 2);
			renderer.renderEastFace(block, x, y, z, texture);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess, x,
						y, z + 1, 3))
		{
			tessellator.setBrightness(block.maxZ < 1.0D ? brightness
					: block.getMixedBrightnessForBlock(
							renderer.blockAccess, x, y, z + 1));
			tessellator.setColorOpaque_F(redNS, greenNS, blueNS);
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 3);
			renderer.renderWestFace(block, x, y, z, texture);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess,
						x - 1, y, z, 4))
		{
			tessellator.setBrightness(block.minX > 0.0D ? brightness
					: block.getMixedBrightnessForBlock(
							renderer.blockAccess, x - 1, y, z));
			tessellator.setColorOpaque_F(redEW, greenEW, blueEW);
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 4);
			renderer.renderNorthFace(block, x, y, z, texture);

			rendered = true;
		}

		if (renderer.renderAllFaces
				|| block.shouldSideBeRendered(renderer.blockAccess,
						x + 1, y, z, 5))
		{
			tessellator.setBrightness(block.maxX < 1.0D ? brightness
					: block.getMixedBrightnessForBlock(
							renderer.blockAccess, x + 1, y, z));
			tessellator.setColorOpaque_F(redEW, greenEW, blueEW);
			final int texture = block.getBlockTexture(
					renderer.blockAccess, x, y, z, 5);
			renderer.renderSouthFace(block, x, y, z, texture);

			rendered = true;
		}

		return rendered;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y,
			int z, Block block, int modelId, RenderBlocks renderer)
	{
		final int color = block.colorMultiplier(world, x, y, z);
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable) {
			final float newRed = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
			final float newGreen = (red * 30.0F + green * 70.0F) / 100.0F;
			final float newBlue = (red * 30.0F + blue * 70.0F) / 100.0F;
			red = newRed;
			green = newGreen;
			blue = newBlue;
		}

		return Minecraft.isAmbientOcclusionEnabled()
				&& Block.lightValue[block.blockID] == 0 ? renderWithAmbientOcclusion(
				x, y, z, block, renderer, red, green, blue)
				: renderWithColorMultiplier(world, x, y, z, block,
						renderer, red, green, blue);
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

}
