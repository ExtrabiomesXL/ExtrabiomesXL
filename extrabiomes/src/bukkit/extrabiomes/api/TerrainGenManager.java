
package extrabiomes.api;

import java.util.Set;
import java.util.TreeSet;

import net.minecraft.server.Block;

public class TerrainGenManager {
	public static Block			blockWasteland;
	public static Block			blockMountainRidge;
	public static boolean		enableQuickSandGen		= false;
	public static boolean		enableCattailGen		= false;
	public static boolean		enableAutumnTreeGen		= true;
	public static boolean		enableFirGen			= true;
	public static boolean		enableRedwoodGen		= true;
	public static boolean		enableAcaciaGen			= true;
	public static Block			blockBrownAutumnLeaves;
	public static int			metaBrownAutumnLeaves	= 0;
	public static Block			blockBrownAutumnWood;
	public static int			metaBrownAutumnWood		= 0;
	public static Block			blockOrangeAutumnLeaves;
	public static int			metaOrangeAutumnLeaves	= 0;
	public static Block			blockOrangeAutumnWood;
	public static int			metaOrangeAutumnWood	= 0;
	public static Block			blockPurpleAutumnLeaves;
	public static int			metaPurpleAutumnLeaves	= 0;
	public static Block			blockPurpleAutumnWood;
	public static int			metaPurpleAutumnWood	= 0;
	public static Block			blockYellowAutumnLeaves;
	public static int			metaYellowAutumnLeaves	= 0;
	public static Block			blockYellowAutumnWood;
	public static int			metaYellowAutumnWood	= 0;
	public static Block			blockFirLeaves;
	public static int			metaFirLeaves			= 1;
	public static Block			blockFirWood;
	public static int			metaFirWood				= 1;
	public static Block			blockRedwoodLeaves;
	public static int			metaRedwoodLeaves		= 0;
	public static Block			blockRedwoodWood;
	public static int			metaRedwoodWood			= 1;
	public static Block			blockAcaciaLeaves;
	public static int			metaAcaciaLeaves		= 0;
	public static Block			blockAcaciaWood;
	public static int			metaAcaciaWood			= 0;
	public static ITreeFactory	treeFactory;
	public static Set			treesCanGrowOnIDs		= new TreeSet();

	static {
		blockWasteland = Block.SANDSTONE;
		blockMountainRidge = Block.STONE;
		blockBrownAutumnLeaves = Block.LEAVES;
		blockBrownAutumnWood = Block.LOG;
		blockOrangeAutumnLeaves = Block.LEAVES;
		blockOrangeAutumnWood = Block.LOG;
		blockPurpleAutumnLeaves = Block.LEAVES;
		blockPurpleAutumnWood = Block.LOG;
		blockYellowAutumnLeaves = Block.LEAVES;
		blockYellowAutumnWood = Block.LOG;
		blockFirLeaves = Block.LEAVES;
		blockFirWood = Block.LOG;
		blockRedwoodLeaves = Block.LEAVES;
		blockRedwoodWood = Block.LOG;
		blockAcaciaLeaves = Block.LEAVES;
		blockAcaciaWood = Block.LOG;
	}

	public TerrainGenManager() {}
}
