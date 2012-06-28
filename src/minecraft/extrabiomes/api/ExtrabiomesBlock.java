package extrabiomes.api;


import net.minecraft.src.Block;

/**
 * Allows direct access to Extrabiome's blocks. Will be populated during
 * BaseMod.load(). If block is null, then it has been deactivated by user
 * configuration.
 * <p>
 * <b>NOTE:</b> Make sure to only reference it in ModsLoaded() or later.
 * 
 * @author ScottKillen
 * 
 */
public class ExtrabiomesBlock {

	/**
	 * 0 - Brown<br>
	 * 1 - Orange<br>
	 * 2 - Purple<br>
	 * 3 - Yellow
	 */
	public static Block autumnLeaves = null;
	
	public static Block catTail = null;
	public static Block crackedSand = null;
	
	/**
	 * 0 - Autumn Shrub<br>
	 * 1 - Hydrangea<br>
	 * 2 - Orange Flower<br>
	 * 3 - Purple Flower<br>
	 * 4 - Tiny Cactus<br>
	 * 5 - Root<br>
	 * 6 - Toadstool<br>
	 * 7 - White Flower
	 */
	public static Block flower = null;
	
	/**
	 * 0 - Brown<br>
	 * 1 - Short Brown<br>
	 * 2 - Dead<br>
	 * 3 - Tall Dead<br>
	 * 4 - Yellow Dead
	 */
	public static Block grass = null;

	/**
	 * 0 - Fir<br>
	 * 1 - Redwood<br>
	 * 2 - Acacia
	 */
	public static Block greenLeaves = null;
	public static Block leafPile = null;
	public static Block quickSand = null;
	public static Block redRock = null;

	/**
	 * 0 - Brown Autumn<br>
	 * 1 - Orange Autumn<br>
	 * 2 - Purple Autumn<br>
	 * 3 - Yellow Autumn<br>
	 * 4 - Fir<br>
	 * 5 - Redwood<br>
	 * 6 - Acacia
	 */
	public static Block sapling = null;
	
}
