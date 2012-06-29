
package extrabiomes.terrain;

import net.minecraft.server.ItemStack;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;

public class TreeFactory implements ITreeFactory {
	enum TreeColor {
		BROWN, ORANGE, PURPLE, YELLOW
	}

	private static WorldGenerator makeAutumnTree(boolean doBlockNotify,
			TreeColor color, boolean isBig)
	{
		ItemStack leaf = null;
		ItemStack wood = null;
		switch (color) {
			case BROWN:
				leaf = new ItemStack(
						TerrainGenManager.blockBrownAutumnLeaves, 1,
						TerrainGenManager.metaBrownAutumnLeaves);
				wood = new ItemStack(
						TerrainGenManager.blockBrownAutumnWood, 1,
						TerrainGenManager.metaBrownAutumnWood);
			case ORANGE:
				leaf = new ItemStack(
						TerrainGenManager.blockOrangeAutumnLeaves, 1,
						TerrainGenManager.metaOrangeAutumnLeaves);
				wood = new ItemStack(
						TerrainGenManager.blockOrangeAutumnWood, 1,
						TerrainGenManager.metaOrangeAutumnWood);
			case PURPLE:
				leaf = new ItemStack(
						TerrainGenManager.blockPurpleAutumnLeaves, 1,
						TerrainGenManager.metaPurpleAutumnLeaves);
				wood = new ItemStack(
						TerrainGenManager.blockPurpleAutumnWood, 1,
						TerrainGenManager.metaPurpleAutumnWood);
			case YELLOW:
				leaf = new ItemStack(
						TerrainGenManager.blockYellowAutumnLeaves, 1,
						TerrainGenManager.metaYellowAutumnLeaves);
				wood = new ItemStack(
						TerrainGenManager.blockYellowAutumnWood, 1,
						TerrainGenManager.metaYellowAutumnWood);
		}

		if (isBig)
			return new WorldGenBigAutumnTree(doBlockNotify, leaf, wood);
		else
			return new WorldGenAutumnTree(doBlockNotify, leaf, wood);
	}

	WorldGenerator	treeAcacia;
	WorldGenerator	treeAcaciaNotify;
	WorldGenerator	treeAutumnBrown;
	WorldGenerator	treeAutumnBrownBig;
	WorldGenerator	treeAutumnBrownBigNotify;
	WorldGenerator	treeAutumnBrownNotify;
	WorldGenerator	treeAutumnOrange;
	WorldGenerator	treeAutumnOrangeBig;
	WorldGenerator	treeAutumnOrangeBigNotify;
	WorldGenerator	treeAutumnOrangeNotify;
	WorldGenerator	treeAutumnPurple;
	WorldGenerator	treeAutumnPurpleBig;
	WorldGenerator	treeAutumnPurpleBigNotify;
	WorldGenerator	treeAutumnPurpleNotify;
	WorldGenerator	treeAutumnYellow;
	WorldGenerator	treeAutumnYellowBig;
	WorldGenerator	treeAutumnYellowBigNotify;
	WorldGenerator	treeAutumnYellowNotify;
	WorldGenerator	treeFir;
	WorldGenerator	treeFirBig;
	WorldGenerator	treeFirBigNotify;
	WorldGenerator	treeFirNotify;
	WorldGenerator	treeRedwood;

	WorldGenerator	treeRedwoodNotify;

	public TreeFactory() {
		treeAcacia = null;
		treeAcaciaNotify = null;
		treeAutumnBrown = null;
		treeAutumnBrownBig = null;
		treeAutumnBrownBigNotify = null;
		treeAutumnBrownNotify = null;
		treeAutumnOrange = null;
		treeAutumnOrangeBig = null;
		treeAutumnOrangeBigNotify = null;
		treeAutumnOrangeNotify = null;
		treeAutumnPurple = null;
		treeAutumnPurpleBig = null;
		treeAutumnPurpleBigNotify = null;
		treeAutumnPurpleNotify = null;
		treeAutumnYellow = null;
		treeAutumnYellowBig = null;
		treeAutumnYellowBigNotify = null;
		treeAutumnYellowNotify = null;
		treeFir = null;
		treeFirBig = null;
		treeFirBigNotify = null;
		treeFirNotify = null;
		treeRedwood = null;
		treeRedwoodNotify = null;
	}

	@Override
	public WorldGenerator makeTreeGenerator(boolean doBlockNotify,
			ITreeFactory.TreeType tree)
	{
		switch (tree) {
			case BROWN_AUTUMN:
				if (doBlockNotify) {
					if (treeAutumnBrownNotify == null)
						treeAutumnBrownNotify = makeAutumnTree(
								doBlockNotify, TreeColor.BROWN, false);
					return treeAutumnBrownNotify;
				}
				if (treeAutumnBrown == null)
					treeAutumnBrown = makeAutumnTree(doBlockNotify,
							TreeColor.BROWN, false);
				return treeAutumnBrown;

			case BROWN_AUTUMN_BIG:
				if (doBlockNotify) {
					if (treeAutumnBrownBigNotify == null)
						treeAutumnBrownBigNotify = makeAutumnTree(
								doBlockNotify, TreeColor.BROWN, true);
					return treeAutumnBrownBigNotify;
				}
				if (treeAutumnBrownBig == null)
					treeAutumnBrownBig = makeAutumnTree(doBlockNotify,
							TreeColor.BROWN, true);
				return treeAutumnBrownBig;

			case ORANGE_AUTUMN:
				if (doBlockNotify) {
					if (treeAutumnOrangeNotify == null)
						treeAutumnOrangeNotify = makeAutumnTree(
								doBlockNotify, TreeColor.ORANGE, false);
					return treeAutumnOrangeNotify;
				}
				if (treeAutumnOrange == null)
					treeAutumnOrange = makeAutumnTree(doBlockNotify,
							TreeColor.ORANGE, false);
				return treeAutumnOrange;

			case ORANGE_AUTUMN_BIG:
				if (doBlockNotify) {
					if (treeAutumnOrangeBigNotify == null)
						treeAutumnOrangeBigNotify = makeAutumnTree(
								doBlockNotify, TreeColor.ORANGE, true);
					return treeAutumnOrangeBigNotify;
				}
				if (treeAutumnOrangeBig == null)
					treeAutumnOrangeBig = makeAutumnTree(doBlockNotify,
							TreeColor.ORANGE, true);
				return treeAutumnOrangeBig;

			case PURPLE_AUTUMN:
				if (doBlockNotify) {
					if (treeAutumnPurpleNotify == null)
						treeAutumnPurpleNotify = makeAutumnTree(
								doBlockNotify, TreeColor.PURPLE, false);
					return treeAutumnPurpleNotify;
				}
				if (treeAutumnPurple == null)
					treeAutumnPurple = makeAutumnTree(doBlockNotify,
							TreeColor.PURPLE, false);
				return treeAutumnPurple;

			case PURPLE_AUTUMN_BIG:
				if (doBlockNotify) {
					if (treeAutumnPurpleBigNotify == null)
						treeAutumnPurpleBigNotify = makeAutumnTree(
								doBlockNotify, TreeColor.PURPLE, true);
					return treeAutumnPurpleBigNotify;
				}
				if (treeAutumnPurpleBig == null)
					treeAutumnPurpleBig = makeAutumnTree(doBlockNotify,
							TreeColor.PURPLE, true);
				return treeAutumnPurpleBig;

			case YELLOW_AUTUMN:
				if (doBlockNotify) {
					if (treeAutumnYellowNotify == null)
						treeAutumnYellowNotify = makeAutumnTree(
								doBlockNotify, TreeColor.PURPLE, false);
					return treeAutumnYellowNotify;
				}
				if (treeAutumnYellow == null)
					treeAutumnYellow = makeAutumnTree(doBlockNotify,
							TreeColor.PURPLE, false);
				return treeAutumnYellow;

			case YELLOW_AUTUMN_BIG:
				if (doBlockNotify) {
					if (treeAutumnYellowBigNotify == null)
						treeAutumnYellowBigNotify = makeAutumnTree(
								doBlockNotify, TreeColor.PURPLE, true);
					return treeAutumnYellowBigNotify;
				}
				if (treeAutumnYellowBig == null)
					treeAutumnYellowBig = makeAutumnTree(doBlockNotify,
							TreeColor.PURPLE, true);
				return treeAutumnYellowBig;

			case FIR:
				if (doBlockNotify) {
					if (treeFirNotify == null)
						treeFirNotify = new WorldGenFirTree(
								doBlockNotify);
					return treeFirNotify;
				}
				if (treeFir == null)
					treeFir = new WorldGenFirTree(doBlockNotify);
				return treeFir;
			case FIR_HUGE:
				if (doBlockNotify) {
					if (treeFirBigNotify == null)
						treeFirBigNotify = new WorldGenFirTree2(
								doBlockNotify);
					return treeFirBigNotify;
				}
				if (treeFirBig == null)
					treeFirBig = new WorldGenFirTree2(doBlockNotify);
				return treeFirBig;
			case REDWOOD:
				if (doBlockNotify) {
					if (treeRedwoodNotify == null)
						treeRedwoodNotify = new WorldGenRedwood(
								doBlockNotify);
					return treeRedwoodNotify;
				}
				if (treeRedwood == null)
					treeRedwood = new WorldGenRedwood(doBlockNotify);
				return treeRedwood;
			case ACACIA:
				if (doBlockNotify) {
					if (treeAcaciaNotify == null)
						treeAcaciaNotify = new WorldGenAcacia(
								doBlockNotify);
					return treeAcaciaNotify;
				}
				if (treeAcacia == null)
					treeAcacia = new WorldGenAcacia(doBlockNotify);
				return treeAcacia;
		}

		return null;
	}

	@Override
	public void registerTreeGen(TreeType type, WorldGenerator tree,
			boolean doNotify)
	{
		switch (type) {
			case BROWN_AUTUMN:
				if (doNotify)
					treeAutumnBrownNotify = tree;
				else
					treeAutumnBrown = tree;
				break;
			case BROWN_AUTUMN_BIG:
				if (doNotify)
					treeAutumnBrownBigNotify = tree;
				else
					treeAutumnBrownBig = tree;
				break;
			case ORANGE_AUTUMN:
				if (doNotify)
					treeAutumnOrangeNotify = tree;
				else
					treeAutumnOrange = tree;
				break;
			case ORANGE_AUTUMN_BIG:
				if (doNotify)
					treeAutumnOrangeBigNotify = tree;
				else
					treeAutumnOrangeBig = tree;
				break;
			case PURPLE_AUTUMN:
				if (doNotify)
					treeAutumnPurpleNotify = tree;
				else
					treeAutumnPurple = tree;
				break;
			case PURPLE_AUTUMN_BIG:
				if (doNotify)
					treeAutumnPurpleBigNotify = tree;
				else
					treeAutumnPurpleBig = tree;
				break;
			case YELLOW_AUTUMN:
				if (doNotify)
					treeAutumnYellowNotify = tree;
				else
					treeAutumnYellow = tree;
				break;
			case YELLOW_AUTUMN_BIG:
				if (doNotify)
					treeAutumnYellowBigNotify = tree;
				else
					treeAutumnYellowBig = tree;
				break;
			case FIR:
				if (doNotify)
					treeFirNotify = tree;
				else
					treeFir = tree;
				break;
			case FIR_HUGE:
				if (doNotify)
					treeFirBigNotify = tree;
				else
					treeFirBig = tree;
				break;
			case REDWOOD:
				if (doNotify)
					treeRedwoodNotify = tree;
				else
					treeRedwood = tree;
				break;
			case ACACIA:
				if (doNotify)
					treeAcaciaNotify = tree;
				else
					treeAcacia = tree;
				break;
		}
	}
}
