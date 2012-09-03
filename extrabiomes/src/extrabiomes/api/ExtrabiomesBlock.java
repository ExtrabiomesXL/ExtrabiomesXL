
package extrabiomes.api;

import net.minecraft.src.Block;

import com.google.common.base.Optional;

/**
 * Allows direct access to Extrabiome's blocks. This class' members will
 * be populated during the @Init event. If a block is absent after the
 * Init event, then it has been deactivated by user configuration.
 * <p>
 * <b>NOTE:</b> Make sure to only reference members of this class in the
 * PostInit event or later.
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
	public static Optional<Block>	autumnLeaves	= Optional.absent();

	public static Optional<Block>	catTail			= Optional.absent();
	public static Optional<Block>	crackedSand		= Optional.absent();

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
	public static Optional<Block>	flower			= Optional.absent();

	/**
	 * 0 - Brown<br>
	 * 1 - Short Brown<br>
	 * 2 - Dead<br>
	 * 3 - Tall Dead<br>
	 * 4 - Yellow Dead
	 */
	public static Optional<Block>	grass			= Optional.absent();

	/**
	 * 0 - Fir<br>
	 * 1 - Redwood<br>
	 * 2 - Acacia
	 */
	public static Optional<Block>	greenLeaves		= Optional.absent();
	public static Optional<Block>	leafPile		= Optional.absent();
	public static Optional<Block>	quickSand		= Optional.absent();
	public static Optional<Block>	redRock			= Optional.absent();

	/**
	 * 0 - Brown Autumn<br>
	 * 1 - Orange Autumn<br>
	 * 2 - Purple Autumn<br>
	 * 3 - Yellow Autumn<br>
	 * 4 - Fir<br>
	 * 5 - Redwood<br>
	 * 6 - Acacia
	 */
	public static Optional<Block>	sapling			= Optional.absent();

}
