package extrabiomes.api;

import net.minecraft.src.WorldGenerator;

/**
 * This interface is used by the treeFactory object in {@link TerrainGenManager}
 * . Use it to get a WorldGenerator that will produce any of the trees created
 * by Extrabiomes.
 * 
 * @author ScottKillen
 * 
 */
public interface ITreeFactory {

	public enum TreeType {
		BROWN_AUTUMN, BROWN_AUTUMN_BIG, ORANGE_AUTUMN, ORANGE_AUTUMN_BIG, PURPLE_AUTUMN, PURPLE_AUTUMN_BIG, YELLOW_AUTUMN, YELLOW_AUTUMN_BIG, FIR, FIR_HUGE, REDWOOD, ACACIA
	}

	/**
	 * Builds an object that will produce one of Extrabiomes' unique trees.
	 * 
	 * @param doBlockNotify
	 *            boolean - normally false during terrain gen and true for an
	 *            in-game event like sapling growth
	 * @param tree
	 *            one of the values from the TreeType enum that specifies the
	 *            type of tree you want.
	 * @return A WorldGenerator object that will produce the desired tree.
	 */
	public WorldGenerator makeTreeGenerator(boolean doBlockNotify,
			ITreeFactory.TreeType tree);

}
