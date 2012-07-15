package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.blocks.BlockCustomTallGrass;

public class WorldGenGrass extends WorldGenerator {

	final int metaToPlace;

	public WorldGenGrass(int metaToPlace) {
		this.metaToPlace = metaToPlace;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		if (ExtrabiomesBlock.grass != null) {
			int i;
			while ((Block.blocksList[(i = world.getBlockId(x, y, z))] == null
					|| Block.blocksList[i].isLeaves(world, x, y, z)) && y > 0)
				y--;

			for (int j = 0; j < 4; j++) {
				int k = (x + rand.nextInt(8)) - rand.nextInt(8);
				int l = (y + rand.nextInt(4)) - rand.nextInt(4);
				int i1 = (z + rand.nextInt(8)) - rand.nextInt(8);

				final boolean canStay;
				final int blockUnder = world.getBlockId(k, l - 1, i1);

				if (metaToPlace == BlockCustomTallGrass.metaDead
						|| metaToPlace == BlockCustomTallGrass.metaDeadTall
						|| metaToPlace == BlockCustomTallGrass.metaDeadYellow)
					canStay = (blockUnder == Block.sand.blockID)
							|| (blockUnder == TerrainGenManager.blockWasteland.blockID)
							&& world.getFullBlockLightValue(k, l, i1) >= 8
							&& world.canBlockSeeTheSky(k, l, i1);
				else if (metaToPlace == BlockCustomTallGrass.metaBrown
						|| metaToPlace == BlockCustomTallGrass.metaShortBrown)
					canStay = (blockUnder == TerrainGenManager.blockMountainRidge.blockID)
							&& world.getFullBlockLightValue(k, l, i1) >= 8
							&& world.canBlockSeeTheSky(k, l, i1);
				else
					canStay = false;

				if (canStay && world.isAirBlock(k, l, i1)) {
					world.setBlockAndMetadata(k, l, i1,
							ExtrabiomesBlock.grass.blockID, metaToPlace);
				}
			}
		}

		return true;
	}
}
