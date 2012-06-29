
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.EnumCreatureType;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.config.ConfigureBlocks;
import forge.ITextureProvider;

public class BlockCrackedSand extends Block implements ITextureProvider
{
	private static boolean	canGrow;
	private static boolean	restrictGrowthToBiome;

	public BlockCrackedSand(int i) {
		super(i, 0, Material.STONE);
		c(1.2F);
		a(Block.h);

		if (ConfigureBlocks.crackedSandCanGrow) a(true);

		canGrow = ConfigureBlocks.crackedSandCanGrow;
		restrictGrowthToBiome = ConfigureBlocks.crackedSandGrowthRestrictedToWasteland;
		TerrainGenManager.blockWasteland = this;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void a(World world, int i, int j, int k, Random random) {
		if (!canGrow) return;

		if (!world.isStatic) {
			if (restrictGrowthToBiome
					&& world.getBiome(i, k) != BiomeManager.wasteland)
				return;

			if (world.getLightLevel(i, j + 1, k) < 15) return;

			for (int l = 0; l < 4; l++) {
				final int i1 = i + random.nextInt(3) - 1;
				final int j1 = j + random.nextInt(5) - 3;
				final int k1 = k + random.nextInt(3) - 1;

				if (!restrictGrowthToBiome
						|| world.getBiome(i1, k1) == BiomeManager.wasteland)
					changeNeighbor(world, i1, j1, k1);
			}
		}
	}

	@Override
	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this));
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType enumcreaturetype,
			World world, int i, int j, int k)
	{
		return true;
	}

	private void changeNeighbor(World world, int i, int j, int k) {
		if (world.getLightLevel(i, j + 1, k) < 9) return;

		final int l = world.getTypeId(i, j, k);

		if (l == SAND.id)
			world.setRawTypeId(i, j, k, id);
		else if (l == SANDSTONE.id) {
			final int i1 = world.getData(i, j, k);

			if (i1 != 0)
				world.setData(i, j, k, 0);
			else
				world.setTypeId(i, j, k, SAND.id);
		} else if (l == DIRT.id)
			world.setRawTypeId(i, j, k, SAND.id);
		else if (l == GRASS.id || l == SOIL.id)
			world.setRawTypeId(i, j, k, DIRT.id);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}
}
