package extrabiomes;

import net.minecraft.src.Block;
import extrabiomes.api.TerrainGenBlock;

final class VanillaMetaBlockFactory {

	private static MetaBlock dirt = null;
	private static MetaBlock deadBush = null;
	private static MetaBlock oakLeaves = null;
	private static MetaBlock pineLeaves = null;
	private static MetaBlock oakSapling = null;
	private static MetaBlock pineSapling = null;
	private static MetaBlock oakWood = null;
	private static MetaBlock pineWood = null;
	private static MetaBlock plantRed = null;
	private static MetaBlock plantYellow = null;
	private static MetaBlock sandStone = null;
	private static MetaBlock stone = null;

	/**
	 * @return Returns classic mode equivalents of terrain generation blocks.
	 *         Null means there is no classic mode equivalent.
	 */
	static MetaBlock getMetaBlock(TerrainGenBlock block) {

		switch (block) {
		case CRACKED_SAND:
			if (sandStone == null)
				sandStone = new MetaBlock(Block.sandStone.blockID, 0);
			return sandStone;

		case RED_ROCK:
			if (stone == null)
				stone = new MetaBlock(Block.stone.blockID, 0);
			return stone;

		case QUICKSAND:
			if (dirt == null)
				dirt = new MetaBlock(Block.dirt.blockID, 0);

			return dirt;

		case BROWN_LEAVES:
		case ORANGE_LEAVES:
		case PURPLE_LEAVES:
		case YELLOW_LEAVES:
		case ACACIA_LEAVES:
		case REDWOOD_LEAVES:
			if (oakLeaves == null)
				oakLeaves = new MetaBlock(Block.leaves.blockID, 0);

			return oakLeaves;

		case FIR_LEAVES:
			if (pineLeaves == null)
				pineLeaves = new MetaBlock(Block.leaves.blockID, 1);

			return pineLeaves;

		case ORANGE_FLOWER:
		case WHITE_FLOWER:
			if (plantYellow == null)
				plantYellow = new MetaBlock(Block.plantYellow.blockID, 0);

			return plantYellow;

		case PURPLE_FLOWER:
			if (plantRed == null)
				plantRed = new MetaBlock(Block.plantRed.blockID, 0);

			return plantRed;

		case BROWN_SAPLING:
		case ORANGE_SAPLING:
		case PURPLE_SAPLING:
		case YELLOW_SAPLING:
		case ACACIA_SAPLING:
			if (oakSapling == null)
				oakSapling = new MetaBlock(Block.sapling.blockID, 0);

			return oakSapling;

		case FIR_SAPLING:
		case REDWOOD_SAPLING:
			if (pineSapling == null)
				pineSapling = new MetaBlock(Block.sapling.blockID, 1);

			return pineSapling;

		case AUTUMN_WOOD:
		case ACACIA_WOOD:
			if (oakWood == null)
				oakWood = new MetaBlock(Block.wood.blockID, 0);

			return oakWood;

		case FIR_WOOD:
		case REDWOOD_WOOD:
			if (pineWood == null)
				pineWood = new MetaBlock(Block.wood.blockID, 1);

			return pineWood;
		}
		return null; // undefined blocks have no classic mode equivalent
	}

}
