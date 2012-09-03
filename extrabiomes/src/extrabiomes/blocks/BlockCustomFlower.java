/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Random;

import extrabiomes.CommonProxy;
import extrabiomes.Extrabiomes;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockCustomFlower extends Block {

	public static final int	metaAutumnShrub	= 0;
	public static final int	metaHydrangea	= 1;
	public static final int	metaOrange		= 2;
	public static final int	metaPurple		= 3;
	public static final int	metaTinyCactus	= 4;
	public static final int	metaRoot		= 5;
	public static final int	metaToadstool	= 6;
	public static final int	metaWhite		= 7;

	public BlockCustomFlower(int id) {
		super(id, Material.plants);
		blockIndexInTexture = 32;
		setTickRandomly(true);
		final float var4 = 0.2F;
		setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4,
				var4 * 3.0F, 0.5F + var4);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);

		final CommonProxy proxy = Extrabiomes.proxy;
		CommonProxy.addGrassPlant(this, metaAutumnShrub, 2);
		CommonProxy.addGrassPlant(this, metaHydrangea, 2);
		CommonProxy.addGrassPlant(this, metaOrange, 5);
		CommonProxy.addGrassPlant(this, metaPurple, 5);
		CommonProxy.addGrassPlant(this, metaWhite, 5);

		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		checkNotNull(itemList).add(
				new ItemStack(this, 1, metaAutumnShrub));
		itemList.add(new ItemStack(this, 1, metaHydrangea));
		itemList.add(new ItemStack(this, 1, metaOrange));
		itemList.add(new ItemStack(this, 1, metaPurple));
		itemList.add(new ItemStack(this, 1, metaTinyCactus));
		itemList.add(new ItemStack(this, 1, metaRoot));
		itemList.add(new ItemStack(this, 1, metaToadstool));
		itemList.add(new ItemStack(this, 1, metaWhite));
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return (checkNotNull(world).getFullBlockLightValue(x, y, z) >= 8 || world
				.canBlockSeeTheSky(x, y, z))
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(checkNotNull(world)
						.getBlockId(x, y - 1, z));
	}

	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return id == Block.grass.blockID || id == Block.dirt.blockID
				|| id == Block.tilledField.blockID;
	}

	protected void checkFlowerChange(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, checkNotNull(world)
					.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	protected int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int md) {
		return super.getBlockTextureFromSideAndMetadata(side, md) + md;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
		return null;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int id)
	{
		super.onNeighborBlockChange(world, x, y, z, id);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		checkFlowerChange(world, x, y, z);
	}
}
