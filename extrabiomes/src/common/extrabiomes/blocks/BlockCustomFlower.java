/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockCustomFlower extends Block {

	public static final int	metaAutumnShrub	= 0;
	public static final int	metaHydrangea	= 1;
	public static final int	metaOrange		= 2;
	public static final int	metaPurple		= 3;
	public static final int	metaTinyCactus	= 4;
	public static final int	metaRoot		= 5;
	public static final int	metaToadstool	= 6;
	public static final int	metaWhite		= 7;

	public BlockCustomFlower(final int id) {
		super(id, Material.plants);
		blockIndexInTexture = 32;
		setTickRandomly(true);
		final float var4 = 0.2F;
		setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4,
				var4 * 3.0F, 0.5F + var4);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);

		MinecraftForge.addGrassPlant(this, metaAutumnShrub, 2);
		MinecraftForge.addGrassPlant(this, metaHydrangea, 2);
		MinecraftForge.addGrassPlant(this, metaOrange, 5);
		MinecraftForge.addGrassPlant(this, metaPurple, 5);
		MinecraftForge.addGrassPlant(this, metaWhite, 5);
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, metaAutumnShrub));
		itemList.add(new ItemStack(this, 1, metaHydrangea));
		itemList.add(new ItemStack(this, 1, metaOrange));
		itemList.add(new ItemStack(this, 1, metaPurple));
		itemList.add(new ItemStack(this, 1, metaTinyCactus));
		itemList.add(new ItemStack(this, 1, metaRoot));
		itemList.add(new ItemStack(this, 1, metaToadstool));
		itemList.add(new ItemStack(this, 1, metaWhite));
	}

	@Override
	public boolean canBlockStay(final World world, final int x,
			final int y, final int z)
	{
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world
				.canBlockSeeTheSky(x, y, z))
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x,
			final int y, final int z)
	{
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x,
						y - 1, z));
	}

	protected boolean canThisPlantGrowOnThisBlockID(final int id) {
		return id == Block.grass.blockID || id == Block.dirt.blockID
				|| id == Block.tilledField.blockID;
	}

	protected final void checkFlowerChange(final World world,
			final int x, final int y, final int z)
	{
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	protected int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(final int side,
			final int md)
	{
		return super.getBlockTextureFromSideAndMetadata(side, md) + md;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(
			final World world, final int x, final int y, final int z)
	{
		return null;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final int id)
	{
		super.onNeighborBlockChange(world, x, y, z, id);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand)
	{
		checkFlowerChange(world, x, y, z);
	}
}
