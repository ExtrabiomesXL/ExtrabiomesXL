/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.crackedsand;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import extrabiomes.api.BiomeManager;

public class BlockCrackedSand extends Block {

	private static boolean	canGrow;
	private static boolean	restrictGrowthToBiome;

	public BlockCrackedSand(int id, boolean canGrow,
			boolean restrictGrowthToBiome)
	{
		super(id, 0, Material.rock);
		setHardness(1.2F);
		setStepSound(Block.soundStoneFootstep);

		BlockCrackedSand.canGrow = canGrow;
		if (canGrow) setTickRandomly(true);
		BlockCrackedSand.restrictGrowthToBiome = restrictGrowthToBiome;

		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	private void changeNeighbor(World world, int x, int y, int z) {
		if (world.getBlockLightValue(x, y + 1, z) < 15) return;

		decayBlock(world, x, y, z);
	}

	private void decayBlock(World world, int x, int y, int z) {
		final int id = world.getBlockId(x, y, z);

		if (id == tilledField.blockID) {
			final int metadata = world.getBlockMetadata(x, y, z);
			if (metadata != 0)
				world.setBlockMetadataWithNotify(x, y, z, metadata - 1);
			else
				world.setBlockWithNotify(x, y, z, dirt.blockID);
		} else if (id == grass.blockID)
			world.setBlockWithNotify(x, y, z, dirt.blockID);
		else if (id == dirt.blockID)
			world.setBlockWithNotify(x, y, z, sand.blockID);
		else if (id == sand.blockID)
			world.setBlockWithNotify(x, y, z, sandStone.blockID);
		else if (id == sandStone.blockID) {
			final int metadata = world.getBlockMetadata(x, y, z);
			if (metadata != 0)
				world.setBlockMetadataWithNotify(x, y, z, 0);
			else
				world.setBlockWithNotify(x, y, z, blockID);
		} else if (id == waterStill.blockID)
			if (isThreeSidesCrackedSand(world, x, y, z))
				world.setBlockAndMetadataWithNotify(x, y, z,
						waterMoving.blockID, 7);
	}

	private boolean isThreeSidesCrackedSand(World world, int x, int y,
			int z)
	{
		int count = 0;
		for (int xTest = x - 1; xTest < x + 2; xTest += 2)
			for (int zTest = z - 1; zTest < z + 2; zTest += 2)
				if (world.getBlockId(xTest, y, zTest) == blockID)
					count++;
		return count >= 3;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!canGrow) return;
		if (!world.isRemote) {
			if (restrictGrowthToBiome
					&& world.getBiomeGenForCoords(x, z) != BiomeManager.wasteland
							.get()) return;
			if (world.getBlockLightValue(x, y + 1, z) < 15) return;
			for (int i = 0; i < 4; ++i) {
				final int x1 = x + rand.nextInt(3) - rand.nextInt(3);
				final int y1 = y + rand.nextInt(5) - rand.nextInt(5);
				final int z1 = z + rand.nextInt(3) - rand.nextInt(3);
				if (!restrictGrowthToBiome
						|| world.getBiomeGenForCoords(x1, z1) == BiomeManager.wasteland
								.get())
					changeNeighbor(world, x1, y1, z1);
			}
		}
	}
}
