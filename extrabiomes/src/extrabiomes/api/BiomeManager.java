/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

/**
 * Allows direct access to Extrabiome's biomes. This class' members will
 * be populated during the @Init event. If a biome is absent after the
 * Init event, then the Extrabiomes mod is not active or not installed.
 * <p>
 * <b>NOTE:</b> Make sure to only reference members of this class in the
 * PostInit event or later.
 * 
 * @author ScottKillen
 * 
 */
public abstract class BiomeManager {

	protected enum GenType {
		TREE, GRASS;
	}
	// @formatter:off
	public static Optional<BiomeGenBase> alpine				 = Optional.absent();
	public static Optional<BiomeGenBase> autumnwoods		 = Optional.absent();
	public static Optional<BiomeGenBase> birchforest		 = Optional.absent();
	public static Optional<BiomeGenBase> extremejungle		 = Optional.absent();
	public static Optional<BiomeGenBase> forestedisland		 = Optional.absent();
	public static Optional<BiomeGenBase> forestedhills		 = Optional.absent();
	public static Optional<BiomeGenBase> glacier			 = Optional.absent();
	public static Optional<BiomeGenBase> greenhills			 = Optional.absent();
	public static Optional<BiomeGenBase> icewasteland		 = Optional.absent();
	public static Optional<BiomeGenBase> greenswamp			 = Optional.absent();
	public static Optional<BiomeGenBase> marsh				 = Optional.absent();
	public static Optional<BiomeGenBase> meadow				 = Optional.absent();
	public static Optional<BiomeGenBase> minijungle			 = Optional.absent();
	public static Optional<BiomeGenBase> mountaindesert		 = Optional.absent();
	public static Optional<BiomeGenBase> mountainridge		 = Optional.absent();
	public static Optional<BiomeGenBase> mountaintaiga		 = Optional.absent();
	public static Optional<BiomeGenBase> pineforest			 = Optional.absent();
	public static Optional<BiomeGenBase> rainforest			 = Optional.absent();
	public static Optional<BiomeGenBase> redwoodforest		 = Optional.absent();
	public static Optional<BiomeGenBase> redwoodlush		 = Optional.absent();
	public static Optional<BiomeGenBase> savanna			 = Optional.absent();
	public static Optional<BiomeGenBase> shrubland			 = Optional.absent();
	public static Optional<BiomeGenBase> snowforest			 = Optional.absent();
	public static Optional<BiomeGenBase> snowyrainforest	 = Optional.absent();
	public static Optional<BiomeGenBase> temperaterainforest = Optional.absent();
	public static Optional<BiomeGenBase> tundra				 = Optional.absent();
	public static Optional<BiomeGenBase> wasteland			 = Optional.absent();

	public static Optional<BiomeGenBase> woodlands			 = Optional.absent();

	protected static Optional<? extends BiomeManager>	instance = Optional.absent();
	// @formatter:on

	/**
	 * This method allows the addition of grasses to custom biomes by
	 * weight.
	 * 
	 * @param biome
	 *            the biomes to add the tree to.
	 * @param grassGen
	 *            the grass generator
	 * @param weight
	 *            the relative probabilty of picking this grass to
	 *            generate. To establish a relative priority, some
	 *            function should be applied to the current total weight
	 *            for a biome.
	 */
	public static void addWeightedGrassGenForBiome(BiomeGenBase biome,
			WorldGenerator grassGen, int weight)
	{
		checkArgument(instance.isPresent(),
				"Cannot add weighted grass gens until after API is initialized.");
		checkNotNull(biome, "Biome is required.");
		checkNotNull(grassGen, "Grass generator is required.");
		checkArgument(weight > 0, "Weight must be greater than zero.");
		instance.get().addBiomeGen(GenType.GRASS, biome, grassGen,
				weight);
	}

	/**
	 * This method allows the addition of trees to custom biomes by
	 * weight.
	 * 
	 * @param biome
	 *            the biomes to add the tree to.
	 * @param treeGen
	 *            the tree generator
	 * @param weight
	 *            the relative probabilty of picking this tree to
	 *            generate. To establish a relative priority, some
	 *            function should be applied to the current total weight
	 *            for a biome.
	 */
	public static void addWeightedTreeGenForBiome(BiomeGenBase biome,
			WorldGenerator treeGen, int weight)
	{
		checkArgument(instance.isPresent(),
				"Cannot add weighted tree gens until after API is initialized.");
		checkNotNull(biome, "Biome is required.");
		checkNotNull(treeGen, "Tree Generator is required.");
		checkArgument(weight > 0, "Weight must be greater than zero.");
		instance.get()
				.addBiomeGen(GenType.TREE, biome, treeGen, weight);
	}

	/**
	 * Returns a random choice from the weighted list of grass
	 * generators
	 * 
	 * @param biome
	 *            The biome for which to select a grass gen
	 * @return the selected grass generator.
	 */
	public static WorldGenerator chooseRandomGrassGenforBiome(
			Random rand, BiomeGenBase biome)
	{
		return instance.get().chooseBiomeRandomGen(GenType.GRASS, rand,
				biome);
	}

	/**
	 * Returns a random choice from the weighted list of tree generators
	 * 
	 * @param biome
	 *            The biome for which to select a tree gen
	 * @return the selected tree generator.
	 */
	public static WorldGenerator chooseRandomTreeGenforBiome(
			Random rand, BiomeGenBase biome)
	{
		return instance.get().chooseBiomeRandomGen(GenType.TREE, rand,
				biome);
	}

	/**
	 * @return An immutable collection of this mod's biomes.
	 */
	public static Collection<BiomeGenBase> getBiomes() {
		checkArgument(instance.isPresent(),
				"Biome list not available until after API is initialized.");
		return instance.get().getBiomeCollection();
	}

	/**
	 * @param biome
	 *            The biome for which to calculate the total weight.
	 * @return the total weight of all grassGen choices for a given
	 *         biome
	 */
	public static int getTotalGrassWeightForBiome(BiomeGenBase biome) {
		checkNotNull(biome, "Biome is required.");
		return instance.get().getBiomeTotalWeight(GenType.GRASS, biome);
	}

	/**
	 * @param biome
	 *            The biome for which to calculate the total weight.
	 * @return the total weight of all treeGen choices for a given biome
	 */
	public static int getTotalTreeWeightForBiome(BiomeGenBase biome) {
		checkNotNull(biome, "Biome is required.");
		return instance.get().getBiomeTotalWeight(GenType.TREE, biome);
	}

	protected abstract void addBiomeGen(GenType genType,
			BiomeGenBase biome, WorldGenerator treeGen, int weight);

	protected abstract WorldGenerator chooseBiomeRandomGen(
			GenType genType, Random rand, BiomeGenBase biome);

	protected abstract Collection<BiomeGenBase> getBiomeCollection();

	protected abstract int getBiomeTotalWeight(GenType genType,
			BiomeGenBase biome);
}
