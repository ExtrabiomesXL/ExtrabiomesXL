package extrabiomes;

import net.minecraft.src.Block;

public enum BaseBlock {
	CRACKED_SAND, RED_ROCK, QUICKSAND, AUTUMN_LEAVES, GREEN_LEAVES, FLOWER, GRASS, SAPLING, CAT_TAIL, LEAF_PILE;

	static Block makeBlock(final BaseBlock block, final int id) {
		if (block != null)

			switch (block) {
			case CRACKED_SAND:
				return new BlockCrackedSand(id);
			case RED_ROCK:
				return new BlockRedRock(id);
			case QUICKSAND:
				return new BlockQuickSand(id);
			case AUTUMN_LEAVES:
				return new BlockAutumnLeaves(id);
			case GREEN_LEAVES:
				return new BlockGreenLeaves(id);
			case FLOWER:
				return new BlockCustomFlower(id);
			case GRASS:
				return new BlockCustomTallGrass(id);
			case SAPLING:
				return new BlockCustomSapling(id);
			case CAT_TAIL:
				return new BlockCatTail(id);
			case LEAF_PILE:
				return new BlockLeafPile(id);

			}

		return null;
	}
}
