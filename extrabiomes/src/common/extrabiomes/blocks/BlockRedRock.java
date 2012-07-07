/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenManager;

public class BlockRedRock extends Block implements ITextureProvider {

	public static final int	metaRedRock			= 0;
	public static final int	metaRedCobble		= 1;
	public static final int	metaRedRockBrick	= 2;

	public BlockRedRock(final int id) {
		super(id, 2, Material.rock);
		setHardness(1.5F);
		setResistance(2.0F);

		TerrainGenManager.blockMountainRidge = this;
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, metaRedRock));
		itemList.add(new ItemStack(this, 1, metaRedCobble));
		itemList.add(new ItemStack(this, 1, metaRedRockBrick));
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, World world,
			int x, int y, int z)
	{
		return true;
	}

	@Override
	protected int damageDropped(int metadata) {
		return metadata == metaRedRockBrick ? metaRedRockBrick
				: metaRedCobble;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return metadata == metaRedRockBrick ? 11
				: metadata == metaRedCobble ? 12 : super
						.getBlockTextureFromSideAndMetadata(side,
								metadata);
	}

	@Override
	public float getExplosionResistance(Entity par1Entity) {
		// TODO Auto-generated method stub
		return super.getExplosionResistance(par1Entity);
	}

	@Override
	public float getHardness(int meta) {
		return meta == metaRedCobble ? 2.0F : super.getHardness(meta);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

}
