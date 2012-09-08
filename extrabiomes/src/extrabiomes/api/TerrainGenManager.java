/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import java.util.Set;
import java.util.TreeSet;

import net.minecraft.src.Block;

import com.google.common.base.Optional;


/**
 * This class pecifies how Extrabiomes generates terrain. Blocks can be
 * substituted to replace Extrabiomes terrain gen blocks with custom
 * blocks from other mods. The defaults for custom (or
 * "no custom block") mode are shown and are overwritten by user
 * configuration.
 * <p>
 * <b>NOTE:</b> Make sure to only reference members of this class in the
 * PostInit event or later.
 * 
 * @author ScottKillen
 * 
 */
public class TerrainGenManager {

	public static boolean					enableCattailGen		= false;
	public static boolean					enableAcaciaGen			= true;

	/**
	 * The predominant block in Mountain Ridge biomes.
	 */
	public static Block						blockMountainRidge		= Block.stone;

	public static Block						blockBrownAutumnLeaves	= Block.leaves;
	public static int						metaBrownAutumnLeaves	= 0;

	public static Block						blockBrownAutumnWood	= Block.wood;
	public static int						metaBrownAutumnWood		= 0;

	public static Block						blockOrangeAutumnLeaves	= Block.leaves;
	public static int						metaOrangeAutumnLeaves	= 0;

	public static Block						blockOrangeAutumnWood	= Block.wood;
	public static int						metaOrangeAutumnWood	= 0;

	public static Block						blockPurpleAutumnLeaves	= Block.leaves;
	public static int						metaPurpleAutumnLeaves	= 0;

	public static Block						blockPurpleAutumnWood	= Block.wood;
	public static int						metaPurpleAutumnWood	= 0;

	public static Block						blockYellowAutumnLeaves	= Block.leaves;
	public static int						metaYellowAutumnLeaves	= 0;

	public static Block						blockYellowAutumnWood	= Block.wood;
	public static int						metaYellowAutumnWood	= 0;

	public static Block						blockFirLeaves			= Block.leaves;
	public static int						metaFirLeaves			= 1;

	public static Block						blockFirWood			= Block.wood;
	public static int						metaFirWood				= 1;

	public static Block						blockRedwoodLeaves		= Block.leaves;
	public static int						metaRedwoodLeaves		= 0;

	public static Block						blockRedwoodWood		= Block.wood;
	public static int						metaRedwoodWood			= 1;

	public static Block						blockAcaciaLeaves		= Block.leaves;
	public static int						metaAcaciaLeaves		= 0;

	public static Block						blockAcaciaWood			= Block.wood;
	public static int						metaAcaciaWood			= 0;

	/**
	 * The instantiation of {@link ITreeFactory}
	 */
	public static Optional<? extends ITreeFactory>	treeFactory;

	/**
	 * The list of ids of blocks upon which saplings and trees may grow.
	 */
	public final static Set<Integer>		treesCanGrowOnIDs		= new TreeSet<Integer>();
}
