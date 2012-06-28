package extrabiomes.terrain;

import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;

public class TreeFactory implements ITreeFactory {

	@Override
	public WorldGenerator makeTreeGenerator(boolean doBlockNotify, ITreeFactory.TreeType tree) {
		ItemStack leaf;
		ItemStack wood;
		
		switch (tree) {
		case BROWN_AUTUMN:
		case BROWN_AUTUMN_BIG:
			leaf = new ItemStack(TerrainGenManager.blockBrownAutumnLeaves, 1, TerrainGenManager.metaBrownAutumnLeaves);
			wood = new ItemStack(TerrainGenManager.blockBrownAutumnWood, 1, TerrainGenManager.metaBrownAutumnWood);
			if (tree == ITreeFactory.TreeType.BROWN_AUTUMN_BIG)
				return new WorldGenBigAutumnTree(doBlockNotify, leaf, wood);
			return new WorldGenAutumnTree(doBlockNotify, leaf, wood);
		case ORANGE_AUTUMN:
		case ORANGE_AUTUMN_BIG:
			leaf = new ItemStack(TerrainGenManager.blockOrangeAutumnLeaves, 1, TerrainGenManager.metaOrangeAutumnLeaves);
			wood = new ItemStack(TerrainGenManager.blockOrangeAutumnWood, 1, TerrainGenManager.metaOrangeAutumnWood);
			if (tree == ITreeFactory.TreeType.ORANGE_AUTUMN_BIG)
				return new WorldGenBigAutumnTree(doBlockNotify, leaf, wood);
			return new WorldGenAutumnTree(doBlockNotify, leaf, wood);
		case PURPLE_AUTUMN:
		case PURPLE_AUTUMN_BIG:
			leaf = new ItemStack(TerrainGenManager.blockPurpleAutumnLeaves, 1, TerrainGenManager.metaPurpleAutumnLeaves);
			wood = new ItemStack(TerrainGenManager.blockPurpleAutumnWood, 1, TerrainGenManager.metaPurpleAutumnWood);
			if (tree == ITreeFactory.TreeType.PURPLE_AUTUMN_BIG)
				return new WorldGenBigAutumnTree(doBlockNotify, leaf, wood);
			return new WorldGenAutumnTree(doBlockNotify, leaf, wood);
		case YELLOW_AUTUMN:
		case YELLOW_AUTUMN_BIG:
			leaf = new ItemStack(TerrainGenManager.blockYellowAutumnLeaves, 1, TerrainGenManager.metaYellowAutumnLeaves);
			wood = new ItemStack(TerrainGenManager.blockYellowAutumnWood, 1, TerrainGenManager.metaYellowAutumnWood);
			if (tree == ITreeFactory.TreeType.YELLOW_AUTUMN_BIG)
				return new WorldGenBigAutumnTree(doBlockNotify, leaf, wood);
			return new WorldGenAutumnTree(doBlockNotify, leaf, wood);
		case FIR:
			return new WorldGenFirTree(doBlockNotify);
		case FIR_HUGE:
			return new WorldGenFirTree2(doBlockNotify);
		case REDWOOD:
			return new WorldGenRedwood(doBlockNotify);
		case ACACIA:
			return new WorldGenAcacia(doBlockNotify);
		}

		return null;
	}

}
