/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.BROWN;
import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.ORANGE;
import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.PURPLE;
import static extrabiomes.trees.WorldGenAutumnTree.AutumnTreeType.YELLOW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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

import extrabiomes.BiomesConfig;
import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
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

	private enum BiomeEnum {
		ALPINE(new BiomeAlpine(32)), AUTUMNWOODS(new BiomeAutumnWoods(
				33)), BIRCHFOREST(new BiomeBirchForest(34)), EXTREMEJUNGLE(
				new BiomeExtremeJungle(35)), FORESTEDHILLS(
				new BiomeForestedHills(36)), FORESTEDISLAND(
				new BiomeForestedIsland(37)), GLACIER(new BiomeGlacier(
				38)), GREENHILLS(new BiomeGreenHills(39)), GREENSWAMP(
				new BiomeGreenSwamp(40)), ICEWASTELAND(
				new BiomeIceWasteland(41)), MARSH(new BiomeMarsh(42)), MEADOW(
				new BiomeMeadow(43)), MINIJUNGLE(
				new BiomeMiniJungle(44)), MOUNTAINDESERT(
				new BiomeMountainDesert(45)), MOUNTAINRIDGE(
				new BiomeMountainRidge(46)), MOUNTAINTAIGA(
				new BiomeMountainTaiga(47)), PINEFOREST(
				new BiomePineForest(48)), RAINFOREST(
				new BiomeRainforest(49)), REDWOODFOREST(
				new BiomeRedwoodForest(50)), REDWOODLUSH(
				new BiomeRedwoodLush(51)), SAVANNA(new BiomeSavanna(52)), SHRUBLAND(
				new BiomeShrubland(53)), SNOWYFOREST(
				new BiomeSnowForest(54)), SNOWYRAINFOREST(
				new BiomeSnowRainforest(55)), TEMPORATERAINFOREST(
				new BiomeTemporateRainforest(56)), TUNDRA(
				new BiomeTundra(57)), WASTELAND(new BiomeWasteland(58)), WOODLANDS(
				new BiomeWoodlands(59));

		private static final Set<BiomeEnum>	enabledBiomes			= EnumSet
																			.noneOf(BiomeEnum.class);
		private static final Set<BiomeEnum>	biomesAllowingVillages	= EnumSet
																			.noneOf(BiomeEnum.class);

		public static Set<BiomeEnum> getBiomesAllowingVillages() {
			return ImmutableSet.copyOf(biomesAllowingVillages);
		}

		public static Set<BiomeEnum> getEnabledBiomes() {
			return ImmutableSet.copyOf(enabledBiomes);
		}

		private final BiomeGenBase	biome;

		BiomeEnum(BiomeGenBase biome) {
			this.biome = biome;
		}

		private String enabledKey() {
			return toString() + ".enablegeneration";
		}

		public BiomeGenBase getBiome() {
			return biome;
		}

		public void loadSettings(BiomesConfig cfg) {
			if (cfg.getOrCreateBooleanProperty(enabledKey(),
					BiomesConfig.CATEGORY_BIOME, true).getBoolean(true))
				enabledBiomes.add(this);
			if (cfg.getOrCreateBooleanProperty(villagesKey(),
					BiomesConfig.CATEGORY_BIOME, true).getBoolean(true))
				biomesAllowingVillages.add(this);
		}

		@Override
		public String toString() {
			return super.toString().toLowerCase(Locale.US);
		}

		private String villagesKey() {
			return toString() + ".allowvillages";
		}
	}

	private enum VanillaBiomeEnum {
		DESERT, EXTREMEHILLS, FOREST, JUNGLE, PLAINS, SWAMPLAND, TAIGA;

		private static final Set<VanillaBiomeEnum>	disabledBiomes			= EnumSet
																					.noneOf(VanillaBiomeEnum.class);
		private static final Set<VanillaBiomeEnum>	biomesAllowingVillages	= EnumSet
																					.noneOf(VanillaBiomeEnum.class);

		public static Set<VanillaBiomeEnum> getBiomesAllowingVillages()
		{
			return ImmutableSet.copyOf(biomesAllowingVillages);
		}

		public static Set<VanillaBiomeEnum> getDisabledBiomes() {
			return ImmutableSet.copyOf(disabledBiomes);
		}

		private String enabledKey() {
			return "vanilla." + toString() + ".enablegeneration";
		}

		public void loadSettings(BiomesConfig cfg) {
			if (!cfg.getOrCreateBooleanProperty(enabledKey(),
					BiomesConfig.CATEGORY_BIOME, true).getBoolean(true))
				disabledBiomes.add(this);
			if (cfg.getOrCreateBooleanProperty(villagesKey(),
					BiomesConfig.CATEGORY_BIOME, true).getBoolean(true))
				biomesAllowingVillages.add(this);
		}

		@Override
		public String toString() {
			return super.toString().toLowerCase(Locale.US);
		}

		private String villagesKey() {
			return "vanilla." + toString() + ".allowvillages";
		}
	}

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

	private static void addBiome(BiomeGenBase biome) {
		Extrabiomes.proxy.addBiome(biome);
		ExtrabiomesLog.info(
				"Enabled biome \"%s\" per config file settings.",
				biome.biomeName);
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

	private static void addEnabledBiomes() {
		for (final BiomeEnum biome : BiomeEnum.getEnabledBiomes())
			addBiome(biome.getBiome());

		final Set<VanillaBiomeEnum> disabledBiomes = VanillaBiomeEnum
				.getDisabledBiomes();

		if (disabledBiomes.contains(VanillaBiomeEnum.DESERT))
			removeBiome(BiomeGenBase.desert);
		if (disabledBiomes.contains(VanillaBiomeEnum.EXTREMEHILLS))
			removeBiome(BiomeGenBase.extremeHills);
		if (disabledBiomes.contains(VanillaBiomeEnum.FOREST))
			removeBiome(BiomeGenBase.forest);
		if (disabledBiomes.contains(VanillaBiomeEnum.JUNGLE))
			removeBiome(BiomeGenBase.jungle);
		if (disabledBiomes.contains(VanillaBiomeEnum.PLAINS))
			removeBiome(BiomeGenBase.plains);
		if (disabledBiomes.contains(VanillaBiomeEnum.SWAMPLAND))
			removeBiome(BiomeGenBase.swampland);
		if (disabledBiomes.contains(VanillaBiomeEnum.TAIGA))
			removeBiome(BiomeGenBase.taiga);
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

	private static void buildBiomeList() {
		for (final BiomeEnum biome : BiomeEnum.values())
			biomes.add(biome.getBiome());
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

	private static void enableVillages() {
		final Set<BiomeEnum> biomes = BiomeEnum
				.getBiomesAllowingVillages();
		for (final BiomeEnum biome : BiomeEnum.values())
			VillageSpawnHelper.setVillageSpawn(biome.getBiome(),
					biomes.contains(biome));

		final Set<VanillaBiomeEnum> villageBiomes = VanillaBiomeEnum
				.getBiomesAllowingVillages();

		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.desert,
				villageBiomes.contains(VanillaBiomeEnum.DESERT));
		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.extremeHills,
				villageBiomes.contains(VanillaBiomeEnum.EXTREMEHILLS));
		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.forest,
				villageBiomes.contains(VanillaBiomeEnum.FOREST));
		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.jungle,
				villageBiomes.contains(VanillaBiomeEnum.JUNGLE));
		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.plains,
				villageBiomes.contains(VanillaBiomeEnum.PLAINS));
		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.swampland,
				villageBiomes.contains(VanillaBiomeEnum.SWAMPLAND));
		VillageSpawnHelper.setVillageSpawn(BiomeGenBase.taiga,
				villageBiomes.contains(VanillaBiomeEnum.TAIGA));
	}

	public static void loadSettings(BiomesConfig cfg) {
		for (final BiomeEnum biome : BiomeEnum.values())
			biome.loadSettings(cfg);
		for (final VanillaBiomeEnum biome : VanillaBiomeEnum.values())
			biome.loadSettings(cfg);
	}

	private static void populateAPIBiomes() {
		alpine = Optional.of(BiomeEnum.ALPINE.getBiome());
		autumnwoods = Optional.of(BiomeEnum.AUTUMNWOODS.getBiome());
		birchforest = Optional.of(BiomeEnum.BIRCHFOREST.getBiome());
		extremejungle = Optional.of(BiomeEnum.EXTREMEJUNGLE.getBiome());
		forestedhills = Optional.of(BiomeEnum.FORESTEDHILLS.getBiome());
		forestedisland = Optional.of(BiomeEnum.FORESTEDISLAND
				.getBiome());
		glacier = Optional.of(BiomeEnum.GLACIER.getBiome());
		greenhills = Optional.of(BiomeEnum.GREENHILLS.getBiome());
		greenswamp = Optional.of(BiomeEnum.GREENSWAMP.getBiome());
		icewasteland = Optional.of(BiomeEnum.ICEWASTELAND.getBiome());
		marsh = Optional.of(BiomeEnum.MARSH.getBiome());
		meadow = Optional.of(BiomeEnum.MEADOW.getBiome());
		minijungle = Optional.of(BiomeEnum.MINIJUNGLE.getBiome());
		mountaindesert = Optional.of(BiomeEnum.MOUNTAINDESERT
				.getBiome());
		mountainridge = Optional.of(BiomeEnum.MOUNTAINRIDGE.getBiome());
		mountaintaiga = Optional.of(BiomeEnum.MOUNTAINTAIGA.getBiome());
		pineforest = Optional.of(BiomeEnum.PINEFOREST.getBiome());
		rainforest = Optional.of(BiomeEnum.RAINFOREST.getBiome());
		redwoodforest = Optional.of(BiomeEnum.REDWOODFOREST.getBiome());
		redwoodlush = Optional.of(BiomeEnum.REDWOODLUSH.getBiome());
		savanna = Optional.of(BiomeEnum.SAVANNA.getBiome());
		shrubland = Optional.of(BiomeEnum.SHRUBLAND.getBiome());
		snowforest = Optional.of(BiomeEnum.SNOWYFOREST.getBiome());
		snowyrainforest = Optional.of(BiomeEnum.SNOWYRAINFOREST
				.getBiome());
		temperaterainforest = Optional.of(BiomeEnum.TEMPORATERAINFOREST
				.getBiome());
		tundra = Optional.of(BiomeEnum.TUNDRA.getBiome());
		wasteland = Optional.of(BiomeEnum.WASTELAND.getBiome());
		woodlands = Optional.of(BiomeEnum.WOODLANDS.getBiome());
	}

	public static void preInit() {
		populateAPIBiomes();
	}

	private static void removeBiome(BiomeGenBase biome) {
		Extrabiomes.proxy.removeBiome(biome);
		ExtrabiomesLog.info(
				"Disabled biome \"%s\" per config file settings.",
				biome.biomeName);
	}

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
		return ImmutableSet.copyOf(biomes);
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

		addEnabledBiomes();
		enableVillages();
		buildBiomeList();
		buildWeightedBiomeTreeList();
		buildWeightedBiomeGrassList();
	}
}
