package extrabiomes.api;

import java.util.Set;
import java.util.TreeSet;

import net.minecraft.src.Block;

/**
 * Specifies how Extrabiomes generates terrain. You can substitute your own
 * locks and metadata values here to replace Extrabiomes terrain gen blocks with
 * your own custom blocks. The defaults for custom (or "no custom block") mode
 * are shown and are overwritten by user configuration.
 * <p>
 * <b>NOTE:</b> Make sure to only reference it in ModsLoaded() or later.
 * 
 * @author ScottKillen
 * 
 */
public class TerrainGenManager {

	/**
	 * The predominant block in Wasteland biomes.
	 */
	public static Block blockWasteland = Block.sandStone;

	/**
	 * The predominant block in Mountain Ridge biomes.
	 */
	public static Block blockMountainRidge = Block.stone;

	public static boolean enableQuickSandGen = false;
	public static boolean enableCattailGen = false;
	public static boolean enableAutumnTreeGen = true;
	public static boolean enableFirGen = true;
	public static boolean enableRedwoodGen = true;
	public static boolean enableAcaciaGen = true;

	public static Block blockBrownAutumnLeaves = Block.leaves;
	public static int metaBrownAutumnLeaves = 0;

	public static Block blockBrownAutumnWood = Block.wood;
	public static int metaBrownAutumnWood = 0;

	public static Block blockOrangeAutumnLeaves = Block.leaves;
	public static int metaOrangeAutumnLeaves = 0;

	public static Block blockOrangeAutumnWood = Block.wood;
	public static int metaOrangeAutumnWood = 0;

	public static Block blockPurpleAutumnLeaves = Block.leaves;
	public static int metaPurpleAutumnLeaves = 0;

	public static Block blockPurpleAutumnWood = Block.wood;
	public static int metaPurpleAutumnWood = 0;

	public static Block blockYellowAutumnLeaves = Block.leaves;
	public static int metaYellowAutumnLeaves = 0;

	public static Block blockYellowAutumnWood = Block.wood;
	public static int metaYellowAutumnWood = 0;

	public static Block blockFirLeaves = Block.leaves;
	public static int metaFirLeaves = 1;

	public static Block blockFirWood = Block.wood;
	public static int metaFirWood = 1;

	public static Block blockRedwoodLeaves = Block.leaves;
	public static int metaRedwoodLeaves = 0;

	public static Block blockRedwoodWood = Block.wood;
	public static int metaRedwoodWood = 1;

	public static Block blockAcaciaLeaves = Block.leaves;
	public static int metaAcaciaLeaves = 0;

	public static Block blockAcaciaWood = Block.wood;
	public static int metaAcaciaWood = 0;

	/**
	 * The instantiation of {@link I TreeFactory}
	 */
	public static ITreeFactory treeFactory;

	/**
	 * Add ids here to allow saplings and trees to grow on them.
	 */
	public static Set<Integer> treesCanGrowOnIDs = new TreeSet<Integer>();
}
