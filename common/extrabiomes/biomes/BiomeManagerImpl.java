/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.biomes;

import static extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType.BROWN;
import static extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType.ORANGE;
import static extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType.PURPLE;
import static extrabiomes.module.summa.worldgen.WorldGenAutumnTree.AutumnTreeType.YELLOW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import extrabiomes.api.BiomeManager;
import extrabiomes.helpers.BiomeHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.module.summa.biome.WeightedRandomChooser;
import extrabiomes.module.summa.biome.WeightedWorldGenerator;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenBigAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenCustomSwamp;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;

@SuppressWarnings("deprecation")
public class BiomeManagerImpl extends BiomeManager {

    private static final WorldGenerator                                               ACACIA_TREE_GEN           = new WorldGenAcacia(
                                                                                                                        false);
    private static final WorldGenerator                                               ALT_TAIGA_GEN             = new WorldGenTaiga2(
                                                                                                                        false);
    private static final WorldGenerator                                               BIG_FIR_TREE_GEN          = new WorldGenFirTreeHuge(
                                                                                                                        false);
    private static final WorldGenerator                                               BIG_OAK_TREE_GEN          = new WorldGenBigTree(
                                                                                                                        false);
    private static final WorldGenerator                                               BIRCH_TREE_GEN            = new WorldGenForest(
                                                                                                                        false);
    private static final WorldGenerator                                               CUSTOM_SWAMP_TREE_GEN     = new WorldGenCustomSwamp();
    private static final WorldGenerator                                               FERN_GEN                  = new WorldGenTallGrass(
                                                                                                                        Block.tallGrass.blockID,
                                                                                                                        2);
    private static final WorldGenerator                                               FIR_TREE_GEN              = new WorldGenFirTree(
                                                                                                                        false);
    private static final WorldGenerator                                               GRASS_GEN                 = new WorldGenTallGrass(
                                                                                                                        Block.tallGrass.blockID,
                                                                                                                        1);
    private static final WorldGenerator                                               OAK_TREE_GEN              = new WorldGenTrees(
                                                                                                                        false);
    private static final WorldGenerator                                               REDWOOD_TREE_GEN          = new WorldGenRedwood(
                                                                                                                        false);
    private static final WorldGenerator                                               SHRUB_GEN                 = new WorldGenShrub(
                                                                                                                        3,
                                                                                                                        0);
    private static final WorldGenerator                                               SWAMP_TREE_GEN            = new WorldGenSwamp();
    private static final WorldGenerator                                               TAIGA_GEN                 = new WorldGenTaiga1();

    private static final Collection<BiomeGenBase>                                     disableDefaultGrassBiomes = new ArrayList<BiomeGenBase>();

    private final static Map<GenType, Multimap<BiomeGenBase, WeightedWorldGenerator>> weightedChoices           = new EnumMap<GenType, Multimap<BiomeGenBase, WeightedWorldGenerator>>(
                                                                                                                        GenType.class);

    private static void addAlpineTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), FIR_TREE_GEN, 100);
    }

    private static void addAutumnTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), new WorldGenAutumnTree(false, BROWN), 90);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenBigAutumnTree(false, BROWN), 10);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenAutumnTree(false, ORANGE), 90);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenBigAutumnTree(false, ORANGE), 10);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenAutumnTree(false, PURPLE), 90);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenBigAutumnTree(false, PURPLE), 10);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenAutumnTree(false, YELLOW), 90);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenBigAutumnTree(false, YELLOW), 10);
        addWeightedTreeGenForBiome(biome.get(), OAK_TREE_GEN, 90);
        addWeightedTreeGenForBiome(biome.get(), BIG_OAK_TREE_GEN, 10);
    }

    private static void addBirchForestTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), OAK_TREE_GEN, 99);
        addWeightedTreeGenForBiome(biome.get(), BIG_OAK_TREE_GEN, 1);
        addWeightedTreeGenForBiome(biome.get(), BIRCH_TREE_GEN, 9900);
    }

    private static void addDefaultTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), OAK_TREE_GEN, 90);
        addWeightedTreeGenForBiome(biome.get(), BIG_OAK_TREE_GEN, 10);
    }

     private static void addExtremeJungleTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), BIG_OAK_TREE_GEN, 2);
        addWeightedTreeGenForBiome(biome.get(), SHRUB_GEN, 9);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenerator() {
            @Override
            public boolean generate(World world, Random rand, int x, int y, int z) {
                final WorldGenerator worldGen = new WorldGenHugeTrees(false, 10 + rand.nextInt(20),
                        3, 3);
                return worldGen.generate(world, rand, x, y, z);
            }

        }, 3);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenerator() {

            @Override
            public boolean generate(World world, Random rand, int x, int y, int z) {
                final WorldGenerator worldGen = new WorldGenTrees(false, 4 + rand.nextInt(7), 3, 3,
                        true);
                return worldGen.generate(world, rand, x, y, z);
            }

        }, 6);
    }

    private static void addGrass(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        if (!disableDefaultGrassBiomes.contains(biome.get()))
            addWeightedGrassGenForBiome(biome.get(), GRASS_GEN, 100);
    }

    private static void addGrassandFerns(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedGrassGenForBiome(biome.get(), FERN_GEN, 25);
        addWeightedGrassGenForBiome(biome.get(), GRASS_GEN, 75);
    }

    private static void addGreenSwampTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), SWAMP_TREE_GEN, 20);
        addWeightedTreeGenForBiome(biome.get(), CUSTOM_SWAMP_TREE_GEN, 80);
    }

    private static void addMiniJungleTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), SWAMP_TREE_GEN, 100);
        addWeightedTreeGenForBiome(biome.get(), OAK_TREE_GEN, 1);
        addWeightedTreeGenForBiome(biome.get(), BIG_OAK_TREE_GEN, 99);
    }

    private static void addRainforestTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), BIRCH_TREE_GEN, 2);
        addWeightedTreeGenForBiome(biome.get(), BIG_OAK_TREE_GEN, 49999);
        addWeightedTreeGenForBiome(biome.get(), OAK_TREE_GEN, 149997);
    }

    private static void addRedwoodForestTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), REDWOOD_TREE_GEN, 100);
    }

    private static void addRedwoodLushTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), REDWOOD_TREE_GEN, 50);
        addWeightedTreeGenForBiome(biome.get(), FIR_TREE_GEN, 50);
    }

    private static void addSavannaTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), ACACIA_TREE_GEN, 100);
    }

    private static void addShrublandTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), new WorldGenerator() {
            @Override
            public boolean generate(World world, Random rand, int x, int y, int z) {
                final WorldGenerator worldGen = new WorldGenShrub(3, rand.nextInt(3));
                return worldGen.generate(world, rand, x, y, z);
            }
        }, 200);
        addWeightedTreeGenForBiome(biome.get(), new WorldGenerator() {
            @Override
            public boolean generate(World world, Random rand, int x, int y, int z) {
                return false; // NO OP
            }
        }, 100);
    }

    private static void addTaigaTrees(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), TAIGA_GEN, 50);
        addWeightedTreeGenForBiome(biome.get(), ALT_TAIGA_GEN, 100);
    }

    private static void addTemporateRainforest(Optional<? extends BiomeGenBase> biome) {
        if (!biome.isPresent()) return;
        addWeightedTreeGenForBiome(biome.get(), BIG_FIR_TREE_GEN, 200);
        addWeightedTreeGenForBiome(biome.get(), FIR_TREE_GEN, 100);
    }

    private static void buildWeightedBiomeGrassList() {
        addGrass(alpine);
        addGrass(autumnwoods);
        addGrass(birchforest);
        addGrassandFerns(extremejungle);
        addGrass(forestedhills);
        addGrass(forestedisland);
        addGrass(glacier);
        addGrass(greenhills);
        addGrass(greenswamp);
        addGrass(icewasteland);
        addGrass(marsh);
        addGrass(meadow);
        addGrassandFerns(minijungle);
        addGrass(mountaindesert);
        addGrass(mountainridge);
        addGrass(mountaintaiga);
        addGrass(pineforest);
        addGrassandFerns(rainforest);
        addGrassandFerns(redwoodforest);
        addGrassandFerns(redwoodlush);
        addGrass(savanna);
        addGrass(shrubland);
        addGrass(snowforest);
        addGrass(snowyrainforest);
        addGrassandFerns(temperaterainforest);
        addGrass(tundra);
        addGrass(wasteland);
        addGrass(woodlands);
    }

    private static void buildWeightedBiomeTreeList() {
        addAlpineTrees(alpine);
        addAutumnTrees(autumnwoods);
        addBirchForestTrees(birchforest);
        addExtremeJungleTrees(extremejungle);
        addDefaultTrees(forestedhills);
        addDefaultTrees(forestedisland);
        addDefaultTrees(glacier);
        addDefaultTrees(greenhills);
        addGreenSwampTrees(greenswamp);
        addDefaultTrees(icewasteland);
        addDefaultTrees(marsh);
        addDefaultTrees(meadow);
        addMiniJungleTrees(minijungle);
        addDefaultTrees(mountaindesert);
        addDefaultTrees(mountainridge);
        addTaigaTrees(mountaintaiga);
        addTaigaTrees(pineforest);
        addRainforestTrees(rainforest);
        addRedwoodForestTrees(redwoodforest);
        addRedwoodLushTrees(redwoodlush);
        addSavannaTrees(savanna);
        addShrublandTrees(shrubland);
        addDefaultTrees(snowforest);
        addTemporateRainforest(snowyrainforest);
        addTemporateRainforest(temperaterainforest);
        addDefaultTrees(tundra);
        addDefaultTrees(wasteland);
        addDefaultTrees(woodlands);
    }

    public static void disableDefaultGrassforBiomes(Collection<BiomeGenBase> biomes) {
        disableDefaultGrassBiomes.addAll(biomes);
    }

    public static void populateAPIBiomes() {
        alpine = BiomeSettings.ALPINE.getBiome();
        autumnwoods = BiomeSettings.AUTUMNWOODS.getBiome();
        birchforest = BiomeSettings.BIRCHFOREST.getBiome();
        extremejungle = BiomeSettings.EXTREMEJUNGLE.getBiome();
        forestedhills = BiomeSettings.FORESTEDHILLS.getBiome();
        forestedisland = BiomeSettings.FORESTEDISLAND.getBiome();
        glacier = BiomeSettings.GLACIER.getBiome();
        greenhills = BiomeSettings.GREENHILLS.getBiome();
        greenswamp = BiomeSettings.GREENSWAMP.getBiome();
        icewasteland = BiomeSettings.ICEWASTELAND.getBiome();
        marsh = BiomeSettings.MARSH.getBiome();
        meadow = BiomeSettings.MEADOW.getBiome();
        minijungle = BiomeSettings.MINIJUNGLE.getBiome();
        mountaindesert = BiomeSettings.MOUNTAINDESERT.getBiome();
        mountainridge = BiomeSettings.MOUNTAINRIDGE.getBiome();
        mountaintaiga = BiomeSettings.MOUNTAINTAIGA.getBiome();
        pineforest = BiomeSettings.PINEFOREST.getBiome();
        rainforest = BiomeSettings.RAINFOREST.getBiome();
        redwoodforest = BiomeSettings.REDWOODFOREST.getBiome();
        redwoodlush = BiomeSettings.REDWOODLUSH.getBiome();
        savanna = BiomeSettings.SAVANNA.getBiome();
        shrubland = BiomeSettings.SHRUBLAND.getBiome();
        snowforest = BiomeSettings.SNOWYFOREST.getBiome();
        snowyrainforest = BiomeSettings.SNOWYRAINFOREST.getBiome();
        temperaterainforest = BiomeSettings.TEMPORATERAINFOREST.getBiome();
        tundra = BiomeSettings.TUNDRA.getBiome();
        wasteland = BiomeSettings.WASTELAND.getBiome();
        woodlands = BiomeSettings.WOODLANDS.getBiome();
    }

    public BiomeManagerImpl() {
        final Multimap<BiomeGenBase, WeightedWorldGenerator> tree = ArrayListMultimap.create();
        weightedChoices.put(GenType.TREE, tree);

        final Multimap<BiomeGenBase, WeightedWorldGenerator> grass = ArrayListMultimap.create();
        weightedChoices.put(GenType.GRASS, grass);

        instance = Optional.of(this);
    }

    @Override
    protected void addBiomeGen(GenType genType, BiomeGenBase biome, WorldGenerator treeGen,
            int weight)
    {
        final Multimap<BiomeGenBase, WeightedWorldGenerator> choices = weightedChoices.get(genType);
        choices.put(biome, new WeightedWorldGenerator(treeGen, weight));
    }

    public static void buildWeightedFloraLists() {
        buildWeightedBiomeTreeList();
        buildWeightedBiomeGrassList();
    }

    @Override
    protected Optional<? extends WorldGenerator> chooseBiomeRandomGen(GenType genType, Random rand,
            BiomeGenBase biome)
    {
        final Optional<Multimap<BiomeGenBase, WeightedWorldGenerator>> choicesForGenType = Optional
                .fromNullable(weightedChoices.get(genType));
        if (choicesForGenType.isPresent()) {
            final Collection<WeightedWorldGenerator> choicesForBiome = choicesForGenType.get().get(
                    biome);
            final Optional<WeightedWorldGenerator> randomItem = WeightedRandomChooser
                    .getRandomItem(rand, choicesForBiome);
            if (randomItem.isPresent()) return Optional.of(randomItem.get().getWorldGen());
        }
        return Optional.absent();
    }

    @Override
	protected Collection<BiomeGenBase> getBiomeCollection() {
        return ImmutableSet.copyOf(BiomeHelper.getActiveBiomes());
    }

    @Override
    protected int getBiomeTotalWeight(GenType genType, BiomeGenBase biome) {
        return WeightedRandomChooser.getTotalWeight(weightedChoices.get(genType).get(biome));
    }

}
