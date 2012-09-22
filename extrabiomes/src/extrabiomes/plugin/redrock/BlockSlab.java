/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

import static extrabiomes.plugin.redrock.BlockType.RED_COBBLE;
import static extrabiomes.plugin.redrock.BlockType.RED_ROCK;

import java.util.List;
import java.util.Random;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.utility.BlockCustomHalfSlab;

public class BlockSlab extends BlockCustomHalfSlab {

	public static final String[]	stepTypes	= new String[] {
			"redRock", "redCobble", "redRockBrick" };

	final boolean					isDoubleSlab;

	public BlockSlab(int blockID, boolean isDoubleSlab) {
		super(blockID, isDoubleSlab, Material.rock);
		setHardness(2.0F);
		setResistance(2.0F);
		setStepSound(soundStoneFootstep);
		this.isDoubleSlab = isDoubleSlab;

		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabBlock);
		if (!isDoubleSlab) setLightOpacity(0);
	}

	@Override
	protected ItemStack createStackedBlock(int metadata) {
		return new ItemStack(RedRock.getHalfSlab().blockID, 2,
				metadata & 0x7);
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return getBlockTextureFromSideAndMetadata(par1, 0);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		metadata &= 0x7;

		if (metadata == RED_ROCK.metadata()) {
			if (side <= 1)
				return 14;
			else
				return 13;
		} else if (metadata == RED_COBBLE.metadata()) return 2;
		return 11;
	}

	@Override
	public String getFullSlabName(int metadata) {
		if (metadata < 0 || metadata >= stepTypes.length) metadata = 0;

		return super.getBlockName() + "." + stepTypes[metadata];
	}

	@Override
	public int getRenderType() {
		if (isDoubleSlab) return 0;
		return RedRock.getRenderId();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int blockID, CreativeTabs creativeTabs,
			List list)
	{
		if (blockID != RedRock.getDoubleSlab().blockID)
			for (int i = 0; i < stepTypes.length; ++i)
				list.add(new ItemStack(blockID, 1, i));
	}

	@Override
	public int idDropped(int metadata, Random rand, int unused) {
		return RedRock.getHalfSlab().blockID;
	}
}
