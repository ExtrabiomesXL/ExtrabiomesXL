/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;

import extrabiomes.api.TerrainGenManager;

import net.minecraft.src.Block;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockRedRock extends Block {

	public static final int	metaRedRock			= 0;
	public static final int	metaRedCobble		= 1;
	public static final int	metaRedRockBrick	= 2;

	public BlockRedRock(int id) {
		super(id, 2, Material.rock);
		setHardness(1.5F);
		setResistance(2.0F);

		TerrainGenManager.blockMountainRidge = this;
		setTextureFile("/extrabiomes/extrabiomes.png");
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		checkNotNull(itemList).add(new ItemStack(this, 1, metaRedRock));
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
	public float getBlockHardness(World world, int x, int y, int z) {
		final int meta = world.getBlockMetadata(x, y, z);
		return meta == metaRedCobble ? 2.0F : blockHardness;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return metadata == metaRedRockBrick ? 11
				: metadata == metaRedCobble ? 12 : super
						.getBlockTextureFromSideAndMetadata(side,
								metadata);
	}
}
