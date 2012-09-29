/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.module.summa.biome;

import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.BROWN;
import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.ORANGE;
import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.PURPLE;
import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.YELLOW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenForest;
import net.minecraft.src.WorldGenHugeTrees;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenSwamp;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import extrabiomes.api.BiomeManager;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.trees.WorldGenAcacia;
import extrabiomes.trees.WorldGenAutumnTree;
import extrabiomes.trees.WorldGenBigAutumnTree;
import extrabiomes.trees.WorldGenCustomSwamp;
import extrabiomes.trees.WorldGenFirTree;
import extrabiomes.trees.WorldGenFirTreeHuge;
import extrabiomes.trees.WorldGenRedwood;
import extrabiomes.utility.WeightedRandomChooser;
import extrabiomes.utility.WeightedWorldGenerator;

public class BiomeManagerImpl extends BiomeManager {

	private static final WorldGenerator													ACACIA_TREE_GEN				= new WorldGenAcacia(
																															false);
	private static final WorldGenerator													ALT_TAIGA_GEN				= new WorldGenTaiga2(
																															false);
	private static final WorldGenerator													BIG_FIR_TREE_GEN			= new WorldGenFirTreeHuge(
																															false);
	private static final WorldGenerator													BIG_OAK_TREE_GEN			= new WorldGenBigTree(
																															false);
	private static final WorldGenerator													BIRCH_TREE_GEN				= new WorldGenForest(
																															false);
	private static final WorldGenerator													CUSTOM_SWAMP_TREE_GEN		= new WorldGenCustomSwamp();
	private static final WorldGenerator													FERN_GEN					= new WorldGenTallGrass(
																															Block.tallGrass.blockID,
																															2);
	private static final WorldGenerator													FIR_TREE_GEN				= new WorldGenFirTree(
																															false);
	private static final WorldGenerator													GRASS_GEN					= new WorldGenTallGrass(
																															Block.tallGrass.blockID,
																															1);
	private static final WorldGenerator													OAK_TREE_GEN				= new WorldGenTrees(
																															false);
	private static final WorldGenerator													REDWOOD_TREE_GEN			= new WorldGenRedwood(
																															false);
	private static final WorldGenerator													SHRUB_GEN					= new WorldGenShrub(
																															3,
																															0);
	private static final WorldGenerator													SWAMP_TREE_GEN				= new WorldGenSwamp();
	private static final WorldGenerator													TAIGA_GEN					= new WorldGenTaiga1();

	private static List<BiomeGenBase>													biomes						= new ArrayList<BiomeGenBase>();

	private final static Map<GenType, Multimap<BiomeGenBase, WeightedWorldGenerator>>	weightedChoices				= new EnumMap(
																															GenType.class);
	static {
		final Multimap<BiomeGenBase, WeightedWorldGenerator> tree = ArrayListMultimap
				.create();
		weightedChoices.put(GenType.TREE, tree);

		final Multimap<BiomeGenBase, WeightedWorldGenerator> grass = ArrayListMultimap
				.create();
		weightedChoices.put(GenType.GRASS, grass);
	}

	private static boolean																initialized					= false;

	private static final Collection<BiomeGenBase>										disableDefaultGrassBiomes	= new ArrayList();

	private static void addAlpineTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, FIR_TREE_GEN, 100);
	}

	private static void addAutumnTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, new WorldGenAutumnTree(false,
				BROWN), 90);
		addWeightedTreeGenForBiome(biome, new WorldGenBigAutumnTree(
				false, BROWN), 10);
		addWeightedTreeGenForBiome(biome, new WorldGenAutumnTree(false,
				ORANGE), 90);
		addWeightedTreeGenForBiome(biome, new WorldGenBigAutumnTree(
				false, ORANGE), 10);
		addWeightedTreeGenForBiome(biome, new WorldGenAutumnTree(false,
				PURPLE), 90);
		addWeightedTreeGenForBiome(biome, new WorldGenBigAutumnTree(
				false, PURPLE), 10);
		addWeightedTreeGenForBiome(biome, new WorldGenAutumnTree(false,
				YELLOW), 90);
		addWeightedTreeGenForBiome(biome, new WorldGenBigAutumnTree(
				false, YELLOW), 10);
		addWeightedTreeGenForBiome(biome, OAK_TREE_GEN, 90);
		addWeightedTreeGenForBiome(biome, BIG_OAK_TREE_GEN, 10);
	}

	private static void addBirchForestTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, OAK_TREE_GEN, 99);
		addWeightedTreeGenForBiome(biome, BIG_OAK_TREE_GEN, 1);
		addWeightedTreeGenForBiome(biome, BIRCH_TREE_GEN, 9900);
	}

	private static void addDefaultTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, OAK_TREE_GEN, 90);
		addWeightedTreeGenForBiome(biome, BIG_OAK_TREE_GEN, 10);
	}

	private static void addExtremeJungleTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, BIG_OAK_TREE_GEN, 2);
		addWeightedTreeGenForBiome(biome, SHRUB_GEN, 9);
		addWeightedTreeGenForBiome(biome, new WorldGenerator() {
			@Override
			public boolean generate(World world, Random rand, int x,
					int y, int z)
			{
				final WorldGenerator worldGen = new WorldGenHugeTrees(
						false, 10 + rand.nextInt(20), 3, 3);
				return worldGen.generate(world, rand, x, y, z);
			}

		}, 3);
		addWeightedTreeGenForBiome(biome, new WorldGenerator() {

			@Override
			public boolean generate(World world, Random rand, int x,
					int y, int z)
			{
				final WorldGenerator worldGen = new WorldGenTrees(
						false, 4 + rand.nextInt(7), 3, 3, true);
				return worldGen.generate(world, rand, x, y, z);
			}

		}, 6);
	}

	private static void addGrass(BiomeGenBase biome) {
		if (!disableDefaultGrassBiomes.contains(biome))
			addWeightedGrassGenForBiome(biome, GRASS_GEN, 100);
	}

	private static void addGrassandFerns(BiomeGenBase biome) {
		addWeightedGrassGenForBiome(biome, FERN_GEN, 25);
		addWeightedGrassGenForBiome(biome, GRASS_GEN, 75);
	}

	private static void addGreenSwampTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, SWAMP_TREE_GEN, 20);
		addWeightedTreeGenForBiome(biome, CUSTOM_SWAMP_TREE_GEN, 80);
	}

	private static void addMiniJungleTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, SWAMP_TREE_GEN, 100);
		addWeightedTreeGenForBiome(biome, OAK_TREE_GEN, 1);
		addWeightedTreeGenForBiome(biome, BIG_OAK_TREE_GEN, 99);
	}

	private static void addRainforestTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, BIRCH_TREE_GEN, 2);
		addWeightedTreeGenForBiome(biome, BIG_OAK_TREE_GEN, 49999);
		addWeightedTreeGenForBiome(biome, OAK_TREE_GEN, 149997);
	}

	private static void addRedwoodForestTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, REDWOOD_TREE_GEN, 100);
	}

	private static void addRedwoodLushTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, REDWOOD_TREE_GEN, 50);
		addWeightedTreeGenForBiome(biome, FIR_TREE_GEN, 50);
	}

	private static void addSavannaTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, ACACIA_TREE_GEN, 100);
	}

	private static void addShrublandTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, new WorldGenerator() {
			@Override
			public boolean generate(World world, Random rand, int x,
					int y, int z)
			{
				final WorldGenerator worldGen = new WorldGenShrub(3,
						rand.nextInt(3));
				return worldGen.generate(world, rand, x, y, z);
			}
		}, 200);
		addWeightedTreeGenForBiome(biome, new WorldGenerator() {
			@Override
			public boolean generate(World world, Random rand, int x,
					int y, int z)
			{
				return false; // NO OP
			}
		}, 100);
	}

	private static void addTaigaTrees(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, TAIGA_GEN, 50);
		addWeightedTreeGenForBiome(biome, ALT_TAIGA_GEN, 100);
	}

	private static void addTemporateRainforest(BiomeGenBase biome) {
		addWeightedTreeGenForBiome(biome, BIG_FIR_TREE_GEN, 200);
		addWeightedTreeGenForBiome(biome, FIR_TREE_GEN, 100);
	}

	private static void buildWeightedBiomeGrassList() {
		addGrass(alpine.get());
		addGrass(autumnwoods.get());
		addGrass(birchforest.get());
		addGrassandFerns(extremejungle.get());
		addGrass(forestedhills.get());
		addGrass(forestedisland.get());
		addGrass(glacier.get());
		addGrass(greenhills.get());
		addGrass(greenswamp.get());
		addGrass(icewasteland.get());
		addGrass(marsh.get());
		addGrass(meadow.get());
		addGrassandFerns(minijungle.get());
		addGrass(mountaindesert.get());
		addGrass(mountainridge.get());
		addGrass(mountaintaiga.get());
		addGrass(pineforest.get());
		addGrassandFerns(rainforest.get());
		addGrassandFerns(redwoodforest.get());
		addGrassandFerns(redwoodlush.get());
		addGrass(savanna.get());
		addGrass(shrubland.get());
		addGrass(snowforest.get());
		addGrass(snowyrainforest.get());
		addGrassandFerns(temperaterainforest.get());
		addGrass(tundra.get());
		addGrass(wasteland.get());
		addGrass(woodlands.get());
	}

	private static void buildWeightedBiomeTreeList() {
		addAlpineTrees(alpine.get());
		addAutumnTrees(autumnwoods.get());
		addBirchForestTrees(birchforest.get());
		addExtremeJungleTrees(extremejungle.get());
		addDefaultTrees(forestedhills.get());
		addDefaultTrees(forestedisland.get());
		addDefaultTrees(glacier.get());
		addDefaultTrees(greenhills.get());
		addGreenSwampTrees(greenswamp.get());
		addDefaultTrees(icewasteland.get());
		addDefaultTrees(marsh.get());
		addDefaultTrees(meadow.get());
		addMiniJungleTrees(minijungle.get());
		addDefaultTrees(mountaindesert.get());
		addDefaultTrees(mountainridge.get());
		addTaigaTrees(mountaintaiga.get());
		addTaigaTrees(pineforest.get());
		addRainforestTrees(rainforest.get());
		addRedwoodForestTrees(redwoodforest.get());
		addRedwoodLushTrees(redwoodlush.get());
		addSavannaTrees(savanna.get());
		addShrublandTrees(shrubland.get());
		addDefaultTrees(snowforest.get());
		addTemporateRainforest(snowyrainforest.get());
		addTemporateRainforest(temperaterainforest.get());
		addDefaultTrees(tundra.get());
		addDefaultTrees(wasteland.get());
		addDefaultTrees(woodlands.get());
	}

	public static void disableDefaultGrassforBiomes(
			Collection<BiomeGenBase> biomes)
	{
		disableDefaultGrassBiomes.addAll(biomes);
	}

	private static void populateAPIBiomes() {
		alpine = Biome.ALPINE.getBiome();
		autumnwoods = Biome.AUTUMNWOODS.getBiome();
		birchforest = Biome.BIRCHFOREST.getBiome();
		extremejungle = Biome.EXTREMEJUNGLE.getBiome();
		forestedhills = Biome.FORESTEDHILLS.getBiome();
		forestedisland = Biome.FORESTEDISLAND.getBiome();
		glacier = Biome.GLACIER.getBiome();
		greenhills = Biome.GREENHILLS.getBiome();
		greenswamp = Biome.GREENSWAMP.getBiome();
		icewasteland = Biome.ICEWASTELAND.getBiome();
		marsh = Biome.MARSH.getBiome();
		meadow = Biome.MEADOW.getBiome();
		minijungle = Biome.MINIJUNGLE.getBiome();
		mountaindesert = Biome.MOUNTAINDESERT.getBiome();
		mountainridge = Biome.MOUNTAINRIDGE.getBiome();
		mountaintaiga = Biome.MOUNTAINTAIGA.getBiome();
		pineforest = Biome.PINEFOREST.getBiome();
		rainforest = Biome.RAINFOREST.getBiome();
		redwoodforest = Biome.REDWOODFOREST.getBiome();
		redwoodlush = Biome.REDWOODLUSH.getBiome();
		savanna = Biome.SAVANNA.getBiome();
		shrubland = Biome.SHRUBLAND.getBiome();
		snowforest = Biome.SNOWYFOREST.getBiome();
		snowyrainforest = Biome.SNOWYRAINFOREST.getBiome();
		temperaterainforest = Biome.TEMPORATERAINFOREST.getBiome();
		tundra = Biome.TUNDRA.getBiome();
		wasteland = Biome.WASTELAND.getBiome();
		woodlands = Biome.WOODLANDS.getBiome();
	}

	boolean	preInitDone	= false;

	public BiomeManagerImpl() {
		instance = Optional.of(this);
	}

	@Override
	protected void addBiomeGen(GenType genType, BiomeGenBase biome,
			WorldGenerator treeGen, int weight)
	{
		final Multimap<BiomeGenBase, WeightedWorldGenerator> choices = weightedChoices
				.get(genType);
		choices.put(biome, new WeightedWorldGenerator(treeGen, weight));
	}

	@Override
	protected Optional<? extends WorldGenerator> chooseBiomeRandomGen(
			GenType genType, Random rand, BiomeGenBase biome)
	{

		final Optional<Multimap<BiomeGenBase, WeightedWorldGenerator>> choicesForGenType = Optional
				.fromNullable(weightedChoices.get(genType));
		if (choicesForGenType.isPresent()) {
			final Collection<WeightedWorldGenerator> choicesForBiome = choicesForGenType
					.get().get(biome);
			final Optional<WeightedWorldGenerator> randomItem = WeightedRandomChooser
					.getRandomItem(rand, choicesForBiome);
			if (randomItem.isPresent())
				return Optional.of(randomItem.get().getWorldGen());
		}
		return Optional.absent();
	}

	@Override
	protected Collection<BiomeGenBase> getBiomeCollection() {
		return ImmutableSet.copyOf(Biome.getActive());
	}

	@Override
	protected int getBiomeTotalWeight(GenType genType,
			BiomeGenBase biome)
	{
		return WeightedRandomChooser.getTotalWeight(weightedChoices
				.get(genType).get(biome));
	}

	public void initialize() {
		if (initialized) return;
		initialized = true;

		buildWeightedBiomeTreeList();
		buildWeightedBiomeGrassList();
	}

	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		if (preInitDone) return;
		preInitDone = true;
		Biome.preInit(config);
		VanillaBiome.preInit(config);

		populateAPIBiomes();
	}
}
