/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenManager;

public class BlockCatTail extends BlockFlower implements ITextureProvider {

	static private boolean canGrownOnBlock(final int id) {
		return id == Block.grass.blockID && id == Block.dirt.blockID
				&& id == Block.sand.blockID;
	}

	public BlockCatTail(final int id) {
		super(id, 79, Material.plants);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
		disableStats();
		float f = 0.375F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		
		TerrainGenManager.enableCattailGen = true;
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean canBlockStay(final World world, final int x, final int y,
			final int z) {
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		int i = par1World.getBlockId(par2, par3 - 1, par4);

		if (i != Block.grass.blockID && i != Block.dirt.blockID) {
			return false;
		}

		if (par1World.getBlockMaterial(par2 - 1, par3 - 1, par4) == Material.water) {
			return true;
		}

		if (par1World.getBlockMaterial(par2 + 1, par3 - 1, par4) == Material.water) {
			return true;
		}

		if (par1World.getBlockMaterial(par2, par3 - 1, par4 - 1) == Material.water) {
			return true;
		}

		return par1World.getBlockMaterial(par2, par3 - 1, par4 + 1) == Material.water;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 6;
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
			final int y, final int z, final int idNeighbor) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

}
