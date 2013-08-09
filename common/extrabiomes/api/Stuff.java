
package extrabiomes.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import com.google.common.base.Optional;

import extrabiomes.module.fabrica.block.BlockWoodStairs;

/**
 * This class contains all of the custom items and blocks.
 * 
 * @author ScottKillen
 * 
 */
public enum Stuff {
	INSTANCE;

	public static Optional<? extends Item>	scarecrow				= Optional.absent();
	public static Optional<? extends Item>	paste					= Optional.absent();
	public static Optional<? extends Item>	logTurner				= Optional.absent();

	public static Optional<? extends Block>	planks					= Optional.absent();   
	public static Optional<? extends Block>	quickSand				= Optional.absent();
	public static Optional<? extends Block>	slabRedRock				= Optional.absent();
	public static Optional<? extends Block>	slabRedRockDouble		= Optional.absent();
	public static Optional<? extends Block>	slabWood				= Optional.absent();
	public static Optional<? extends Block>	slabWoodDouble			= Optional.absent();
	public static Optional<? extends Block>	newslabWood				= Optional.absent();
	public static Optional<? extends Block>	newslabWoodDouble		= Optional.absent();
	public static Optional<? extends Block>	stairsAcacia			= Optional.absent();
	public static Optional<? extends Block>	stairsFir				= Optional.absent();
	public static Optional<? extends Block>	stairsRedCobble			= Optional.absent();
	public static Optional<? extends Block>	stairsRedRockBrick		= Optional.absent();
	public static Optional<? extends Block>	stairsRedwood			= Optional.absent();
	public static Optional<? extends Block>	wall					= Optional.absent();
	public static Optional<? extends Block> stairsCypress 			= Optional.absent();
	public static Optional<? extends Block> stairsBaldCypress		= Optional.absent();
	public static Optional<? extends Block> stairsJapaneseMaple		= Optional.absent();
	public static Optional<? extends Block> stairsRainbowEucalyptus	= Optional.absent();
	public static Optional<? extends Block> stairsAutumn			= Optional.absent();
	public static Optional<? extends Block> stairsSakuraBlossom		= Optional.absent();
	

}
