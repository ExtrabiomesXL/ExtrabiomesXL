package extrabiomes;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenBlock;

public class WorldGenGrass extends WorldGenerator {
	private MetaBlock blockToPlace;

	public WorldGenGrass(MetaBlock blockToPlace) {
		this.blockToPlace = blockToPlace;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		final BlockControl bc = BlockControl.INSTANCE;

		for (int i = 0; ((i = world.getBlockId(x, y, z)) == 0 || bc.isLeaves(i))
				&& y > 0; y--) {
		}

		for (int j = 0; j < 4; j++) {
			int k = (x + rand.nextInt(8)) - rand.nextInt(8);
			int l = (y + rand.nextInt(4)) - rand.nextInt(4);
			int i1 = (z + rand.nextInt(8)) - rand.nextInt(8);

			final boolean canStay;
			final MetaBlock blockUnder = new MetaBlock(world, k, l - 1, i1);

			final MetaBlock crackedSand = bc
					.getTerrainGenBlock(TerrainGenBlock.CRACKED_SAND);
			final MetaBlock redRock = bc
					.getTerrainGenBlock(TerrainGenBlock.RED_ROCK);

			if (BlockCustomTallGrass.isDead(blockToPlace))
				canStay = (blockUnder.blockId() == Block.sand.blockID)
						|| (blockUnder.equals(crackedSand)
						&& world.getFullBlockLightValue(k, l, i1) >= 8
						&& world.canBlockSeeTheSky(k, l, i1));
			else if (blockToPlace.equals(bc
					.getTerrainGenBlock(TerrainGenBlock.BROWN_GRASS))
					|| blockToPlace.equals(bc
							.getTerrainGenBlock(TerrainGenBlock.SHORT_BROWN_GRASS)))
				canStay = (blockUnder.equals(redRock))
						&& world.getFullBlockLightValue(k, l, i1) >= 8
						&& world.canBlockSeeTheSky(k, l, i1);
			else
				canStay = blockToPlace.block().canBlockStay(world, k, l, i1);

			if (canStay && world.isAirBlock(k, l, i1)) {
				world.setBlockAndMetadata(k, l, i1, blockToPlace.blockId(),
						blockToPlace.metadata());
			}
		}

		return true;
	}
}
