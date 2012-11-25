/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldType;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Api;
import extrabiomes.api.DiscoverWorldTypesEvent;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.core.utility.EnhancedConfiguration;
import extrabiomes.lib.BiomeSettings;

enum Biome {
    ALPINE(BiomeAlpine.class),
    AUTUMNWOODS(BiomeAutumnWoods.class),
    BIRCHFOREST(BiomeBirchForest.class),
    EXTREMEJUNGLE(BiomeExtremeJungle.class),
    FORESTEDHILLS(BiomeForestedHills.class),
    FORESTEDISLAND(BiomeForestedIsland.class),
    GLACIER(BiomeGlacier.class),
    GREENHILLS(BiomeGreenHills.class),
    GREENSWAMP(BiomeGreenSwamp.class),
    ICEWASTELAND(BiomeIceWasteland.class),
    MARSH(BiomeMarsh.class),
    MEADOW(BiomeMeadow.class),
    MINIJUNGLE(BiomeMiniJungle.class),
    MOUNTAINDESERT(BiomeMountainDesert.class),
    MOUNTAINRIDGE(BiomeMountainRidge.class),
    MOUNTAINTAIGA(BiomeMountainTaiga.class),
    PINEFOREST(BiomePineForest.class),
    RAINFOREST(BiomeRainforest.class),
    REDWOODFOREST(BiomeRedwoodForest.class),
    REDWOODLUSH(BiomeRedwoodLush.class),
    SAVANNA(BiomeSavanna.class),
    SHRUBLAND(BiomeShrubland.class),
    SNOWYFOREST(BiomeSnowForest.class),
    SNOWYRAINFOREST(BiomeSnowRainforest.class),
    TEMPORATERAINFOREST(BiomeTemporateRainforest.class),
    TUNDRA(BiomeTundra.class),
    WASTELAND(BiomeWasteland.class),
    WOODLANDS(BiomeWoodlands.class);

    private static final String LOG_MESSAGE_BIOME_DISABLED           = "log.message.biome.disabled";
    private static final String LOG_MESSAGE_BIOME_VILLAGE_ENABLED    = "log.message.biome.village.enabled";
    private static final String LOG_MESSAGE_CONFIG_EXCEPTION_BIOMEID = "log.message.config.exception.biomeid";

    private static void createBiomes() throws InstantiationException, IllegalAccessException {
        final Set<WorldType> worldTypes = new HashSet<WorldType>();
        worldTypes.add(WorldType.DEFAULT);
        worldTypes.add(WorldType.LARGE_BIOMES);
        final DiscoverWorldTypesEvent event = new DiscoverWorldTypesEvent(worldTypes);
        Api.getExtrabiomesXLEventBus().post(event);
        for (final Biome biome : Biome.values()) {
            if (biome.biomeID > 0) {
                if (BiomeGenBase.biomeList[biome.biomeID] != null)
                    throw new IllegalArgumentException(String.format(Extrabiomes.proxy
                            .getStringLocalization(LOG_MESSAGE_CONFIG_EXCEPTION_BIOMEID),
                            biome.biomeID, BiomeGenBase.biomeList[biome.biomeID].biomeName, biome
                                    .toString()));

                biome.biome = Optional.of(biome.biomeClass.newInstance());
            }
            if (biome.enableGeneration && biome.biome.isPresent())
                Extrabiomes.proxy.addBiome(worldTypes, biome.biome.get());
            else
                LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_BIOME_DISABLED),
                        biome.toString());
            if (biome.enableVillages && biome.biome.isPresent()) {
                VillageSpawnHelper.setVillageSpawn(biome.biome.get(), true);
                LogHelper.fine(
                        Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_BIOME_VILLAGE_ENABLED),
                        biome.toString());
            }
        }
    }

    static Collection<BiomeGenBase> getActive() {
        if (!activeBiomes.isPresent()) {
            activeBiomes = Optional.of(new ArrayList<BiomeGenBase>(Biome.values().length));
            for (final Biome biome : Biome.values())
                if (biome.biome.isPresent()) activeBiomes.get().add(biome.biome.get());
        }
        return ImmutableSet.copyOf(activeBiomes.get());
    }

    private static void loadSettings() {

        for (final Biome biome : Biome.values()) {

            final BiomeSettings settings = getBiomeSettings(biome);

            biome.biomeID = settings.getID();
            biome.enableGeneration = settings.isEnabled();
            biome.enableVillages = settings.allowVillages();
        }
    }

    private static BiomeSettings getBiomeSettings(final Biome biome) {
        final BiomeSettings settings;
        switch (biome) {
            case ALPINE:
                return BiomeSettings.ALPINE;
            case AUTUMNWOODS:
                return BiomeSettings.AUTUMNWOODS;
            case BIRCHFOREST:
                return BiomeSettings.BIRCHFOREST;
            case EXTREMEJUNGLE:
                return BiomeSettings.EXTREMEJUNGLE;
            case FORESTEDHILLS:
                return BiomeSettings.FORESTEDHILLS;
            case FORESTEDISLAND:
                return BiomeSettings.FORESTEDISLAND;
            case GLACIER:
                return BiomeSettings.GLACIER;
            case GREENHILLS:
                return BiomeSettings.GREENHILLS;
            case GREENSWAMP:
                return BiomeSettings.GREENSWAMP;
            case ICEWASTELAND:
                return BiomeSettings.ICEWASTELAND;
            case MARSH:
                return BiomeSettings.MARSH;
            case MEADOW:
                return BiomeSettings.MEADOW;
            case MINIJUNGLE:
                return BiomeSettings.MINIJUNGLE;
            case MOUNTAINDESERT:
                return BiomeSettings.MOUNTAINDESERT;
            case MOUNTAINRIDGE:
                return BiomeSettings.MOUNTAINRIDGE;
            case MOUNTAINTAIGA:
                return BiomeSettings.MOUNTAINTAIGA;
            case PINEFOREST:
                return BiomeSettings.PINEFOREST;
            case RAINFOREST:
                return BiomeSettings.RAINFOREST;
            case REDWOODFOREST:
                return BiomeSettings.REDWOODFOREST;
            case REDWOODLUSH:
                return BiomeSettings.REDWOODLUSH;
            case SAVANNA:
                return BiomeSettings.SAVANNA;
            case SHRUBLAND:
                return BiomeSettings.SHRUBLAND;
            case SNOWYFOREST:
                return BiomeSettings.SNOWYFOREST;
            case SNOWYRAINFOREST:
                return BiomeSettings.SNOWYRAINFOREST;
            case TEMPORATERAINFOREST:
                return BiomeSettings.TEMPORATERAINFOREST;
            case TUNDRA:
                return BiomeSettings.TUNDRA;
            case WASTELAND:
                return BiomeSettings.WASTELAND;
            case WOODLANDS:
                return BiomeSettings.WOODLANDS;
        }
        return null;
    }

    static void preInit() throws InstantiationException, IllegalAccessException {
        loadSettings();
        createBiomes();
    }

    private Optional<? extends BiomeGenBase>              biome            = Optional.absent();
    private int                                           biomeID          = 0;
    private boolean                                       enableGeneration = false;
    private boolean                                       enableVillages   = false;
    private final Class<? extends BiomeGenBase>           biomeClass;
    private static Optional<? extends List<BiomeGenBase>> activeBiomes     = Optional.absent();

    Biome(Class<? extends BiomeGenBase> biomeClass) {
        this.biomeClass = biomeClass;
    }

    private String enabledKey() {
        return toString() + ".enablegeneration";
    }

    Optional<? extends BiomeGenBase> getBiome() {
        return biome;
    }

    int getBiomeID() {
        return biomeID;
    }

    private String idKey() {
        return toString() + ".id";
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.US);
    }

    private String villagesKey() {
        return toString() + ".allowvillages";
    }
}
