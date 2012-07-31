
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.config.ConfigureBlocks;

public class BlockCrackedSand extends Block implements ITextureProvider
{

	private static boolean	canGrow;

	private static boolean	restrictGrowthToBiome;

	public BlockCrackedSand(int id) {
		super(id, 0, Material.rock);
		setHardness(1.2F);
		setStepSound(Block.soundStoneFootstep);
		if (ConfigureBlocks.crackedSandCanGrow) setTickRandomly(true);

		canGrow = ConfigureBlocks.crackedSandCanGrow;
		restrictGrowthToBiome = ConfigureBlocks.crackedSandGrowthRestrictedToWasteland;

		TerrainGenManager.blockWasteland = this;
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, World world,
			int x, int y, int z)
	{
		return true;
	}

	private void changeNeighbor(World world, int x, int y, int z) {
		if (world.getBlockLightValue(x, y + 1, z) < 9) return;

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
		else if (id == sand.blockID) {
			world.setBlockWithNotify(x, y, z, sandStone.blockID);			
		}
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

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
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
					&& world.getBiomeGenForCoords(x, z) != BiomeManager.wasteland)
				return;
			if (world.getBlockLightValue(x, y + 1, z) < 15) return;
			for (int i = 0; i < 4; ++i) {
				final int x1 = x + rand.nextInt(3) - 1;
				final int y1 = y + rand.nextInt(5) - 3;
				final int z1 = z + rand.nextInt(3) - 1;
				if (!restrictGrowthToBiome
						|| world.getBiomeGenForCoords(x1, z1) == BiomeManager.wasteland)
					changeNeighbor(world, x1, y1, z1);
			}
		}
	}
}
