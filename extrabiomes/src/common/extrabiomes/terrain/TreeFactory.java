/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.terrain;

import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldGenerator;
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
				break;
			case ORANGE:
				leaf = new ItemStack(
						TerrainGenManager.blockOrangeAutumnLeaves, 1,
						TerrainGenManager.metaOrangeAutumnLeaves);
				wood = new ItemStack(
						TerrainGenManager.blockOrangeAutumnWood, 1,
						TerrainGenManager.metaOrangeAutumnWood);
				break;
			case PURPLE:
				leaf = new ItemStack(
						TerrainGenManager.blockPurpleAutumnLeaves, 1,
						TerrainGenManager.metaPurpleAutumnLeaves);
				wood = new ItemStack(
						TerrainGenManager.blockPurpleAutumnWood, 1,
						TerrainGenManager.metaPurpleAutumnWood);
				break;
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
		return new WorldGenAutumnTree(doBlockNotify, leaf, wood);
	}

	WorldGenerator	treeAcacia					= null;
	WorldGenerator	treeAcaciaNotify			= null;
	WorldGenerator	treeAutumnBrown				= null;
	WorldGenerator	treeAutumnBrownBig			= null;
	WorldGenerator	treeAutumnBrownBigNotify	= null;
	WorldGenerator	treeAutumnBrownNotify		= null;
	WorldGenerator	treeAutumnOrange			= null;
	WorldGenerator	treeAutumnOrangeBig			= null;
	WorldGenerator	treeAutumnOrangeBigNotify	= null;
	WorldGenerator	treeAutumnOrangeNotify		= null;
	WorldGenerator	treeAutumnPurple			= null;
	WorldGenerator	treeAutumnPurpleBig			= null;
	WorldGenerator	treeAutumnPurpleBigNotify	= null;
	WorldGenerator	treeAutumnPurpleNotify		= null;
	WorldGenerator	treeAutumnYellow			= null;
	WorldGenerator	treeAutumnYellowBig			= null;
	WorldGenerator	treeAutumnYellowBigNotify	= null;
	WorldGenerator	treeAutumnYellowNotify		= null;
	WorldGenerator	treeFir						= null;
	WorldGenerator	treeFirBig					= null;
	WorldGenerator	treeFirBigNotify			= null;
	WorldGenerator	treeFirNotify				= null;

	WorldGenerator	treeRedwood					= null;

	WorldGenerator	treeRedwoodNotify			= null;

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
								doBlockNotify, TreeColor.YELLOW, false);
					return treeAutumnYellowNotify;
				}
				if (treeAutumnYellow == null)
					treeAutumnYellow = makeAutumnTree(doBlockNotify,
							TreeColor.YELLOW, false);
				return treeAutumnYellow;

			case YELLOW_AUTUMN_BIG:
				if (doBlockNotify) {
					if (treeAutumnYellowBigNotify == null)
						treeAutumnYellowBigNotify = makeAutumnTree(
								doBlockNotify, TreeColor.YELLOW, true);
					return treeAutumnYellowBigNotify;
				}
				if (treeAutumnYellowBig == null)
					treeAutumnYellowBig = makeAutumnTree(doBlockNotify,
							TreeColor.YELLOW, true);
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
