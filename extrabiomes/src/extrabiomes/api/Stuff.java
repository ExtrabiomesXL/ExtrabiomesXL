
package extrabiomes.api;

import net.minecraft.src.Block;
import net.minecraft.src.Item;

import com.google.common.base.Optional;

/**
 * This class contains all of the custom items and blocks.
 * 
 * @author Scott
 * 
 */
public enum Stuff {
	INSTANCE;

	public static Optional<? extends Item>	logTurner			= Optional.absent();
	public static Optional<? extends Item>	scarecrow			= Optional.absent();
	
	public static Optional<? extends Block>	autumnLeaves		= Optional.absent();
	public static Optional<? extends Block>	catTail				= Optional.absent();
	public static Optional<? extends Block>	crackedSand			= Optional.absent();
	public static Optional<? extends Block>	flower				= Optional.absent();
	public static Optional<? extends Block>	grass				= Optional.absent();
	public static Optional<? extends Block>	greenLeaves			= Optional.absent();
	public static Optional<? extends Block>	leafPile			= Optional.absent();
	public static Optional<? extends Block>	log					= Optional.absent();
	public static Optional<? extends Block>	planks				= Optional.absent();
	public static Optional<? extends Block>	quarterLogNE		= Optional.absent();
	public static Optional<? extends Block>	quarterLogNW		= Optional.absent();
	public static Optional<? extends Block>	quarterLogSE		= Optional.absent();
	public static Optional<? extends Block>	quarterLogSW		= Optional.absent();
	public static Optional<? extends Block>	quickSand			= Optional.absent();
	public static Optional<? extends Block>	redRock				= Optional.absent();
	public static Optional<? extends Block>	redRockSlab			= Optional.absent();
	public static Optional<? extends Block>	redRockSlabDouble	= Optional.absent();
	public static Optional<? extends Block>	sapling				= Optional.absent();
	public static Optional<? extends Block>	stairsAcacia		= Optional.absent();
	public static Optional<? extends Block>	stairsFir			= Optional.absent();
	public static Optional<? extends Block>	stairsRedCobble		= Optional.absent();
	public static Optional<? extends Block>	stairsRedRockBrick	= Optional.absent();
	public static Optional<? extends Block>	stairsRedwood		= Optional.absent();
	public static Optional<? extends Block>	woodSlab			= Optional.absent();
	public static Optional<? extends Block>	woodSlabDouble		= Optional.absent();

}
