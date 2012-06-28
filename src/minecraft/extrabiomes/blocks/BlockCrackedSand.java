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

public class BlockCrackedSand extends Block implements ITextureProvider {

	private static boolean canGrow;

	private static boolean restrictGrowthToBiome;
	public BlockCrackedSand(int id) {
		super(id, 0, Material.rock);
		setHardness(1.2F);
		setStepSound(Block.soundStoneFootstep);
		if (ConfigureBlocks.crackedSandCanGrow)
			this.setTickRandomly(true);

		canGrow = ConfigureBlocks.crackedSandCanGrow;
		restrictGrowthToBiome = ConfigureBlocks.crackedSandGrowthRestrictedToWasteland;

		TerrainGenManager.blockWasteland = this;
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x,
			int y, int z) {
		return true;
	}

	private void changeNeighbor(World world, int x, int y, int z) {
		if (world.getBlockLightValue(x, y + 1, z) < 9)
			return;

		final int id = world.getBlockId(x, y, z);
		if (id == sand.blockID)
			world.setBlock(x, y, z, blockID);
		else if (id == sandStone.blockID) {
			final int metadata = world.getBlockMetadata(x, y, z);
			if (metadata != 0)
				world.setBlockMetadataWithNotify(x, y, z, 0);
			else
				world.setBlockWithNotify(x, y, z, sand.blockID);
		} else if (id == dirt.blockID)
			world.setBlock(x, y, z, sand.blockID);
		else if (id == grass.blockID || id == tilledField.blockID)
			world.setBlock(x, y, z, dirt.blockID);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!canGrow)
			return;
		if (!world.isRemote) {
			if (restrictGrowthToBiome
					&& world.getBiomeGenForCoords(x, z) != BiomeManager.wasteland)
				return;
			if (world.getBlockLightValue(x, y + 1, z) < 15)
				return;
			for (int i = 0; i < 4; ++i) {
				int x1 = x + rand.nextInt(3) - 1;
				int y1 = y + rand.nextInt(5) - 3;
				int z1 = z + rand.nextInt(3) - 1;
				if (!restrictGrowthToBiome
						|| world.getBiomeGenForCoords(x1, z1) == BiomeManager.wasteland)
					changeNeighbor(world, x1, y1, z1);
			}
		}
	}
}
