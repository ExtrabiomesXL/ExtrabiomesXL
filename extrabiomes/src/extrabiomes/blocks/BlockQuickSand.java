/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockQuickSand extends Block {

	public BlockQuickSand(int id) {
		super(id, 1, Material.sand);
		setHardness(4.0F);
		setStepSound(Block.soundSandFootstep);
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		checkNotNull(itemList).add(new ItemStack(this));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y,
			int z, Entity entity)
	{
		checkNotNull(entity).setInWeb();
	}

}
