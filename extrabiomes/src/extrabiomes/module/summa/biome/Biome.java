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
import java.util.Set;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldType;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Api;
import extrabiomes.api.DiscoverWorldTypesEvent;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.lib.BiomeSettings;

public enum Biome {
    ALPINE(BiomeAlpine.class, BiomeSettings.ALPINE),
    AUTUMNWOODS(BiomeAutumnWoods.class, BiomeSettings.AUTUMNWOODS),
    BIRCHFOREST(BiomeBirchForest.class, BiomeSettings.BIRCHFOREST),
    EXTREMEJUNGLE(BiomeExtremeJungle.class, BiomeSettings.EXTREMEJUNGLE),
    FORESTEDHILLS(BiomeForestedHills.class, BiomeSettings.FORESTEDHILLS),
    FORESTEDISLAND(BiomeForestedIsland.class, BiomeSettings.FORESTEDISLAND),
    GLACIER(BiomeGlacier.class, BiomeSettings.GLACIER),
    GREENHILLS(BiomeGreenHills.class, BiomeSettings.GREENHILLS),
    GREENSWAMP(BiomeGreenSwamp.class, BiomeSettings.GREENSWAMP),
    ICEWASTELAND(BiomeIceWasteland.class, BiomeSettings.ICEWASTELAND),
    MARSH(BiomeMarsh.class, BiomeSettings.MARSH),
    MEADOW(BiomeMeadow.class, BiomeSettings.MEADOW),
    MINIJUNGLE(BiomeMiniJungle.class, BiomeSettings.MINIJUNGLE),
    MOUNTAINDESERT(BiomeMountainDesert.class, BiomeSettings.MOUNTAINDESERT),
    MOUNTAINRIDGE(BiomeMountainRidge.class, BiomeSettings.MOUNTAINRIDGE),
    MOUNTAINTAIGA(BiomeMountainTaiga.class, BiomeSettings.MOUNTAINTAIGA),
    PINEFOREST(BiomePineForest.class, BiomeSettings.PINEFOREST),
    RAINFOREST(BiomeRainforest.class, BiomeSettings.RAINFOREST),
    REDWOODFOREST(BiomeRedwoodForest.class, BiomeSettings.REDWOODFOREST),
    REDWOODLUSH(BiomeRedwoodLush.class, BiomeSettings.REDWOODLUSH),
    SAVANNA(BiomeSavanna.class, BiomeSettings.SAVANNA),
    SHRUBLAND(BiomeShrubland.class, BiomeSettings.SHRUBLAND),
    SNOWYFOREST(BiomeSnowForest.class, BiomeSettings.SNOWYFOREST),
    SNOWYRAINFOREST(BiomeSnowRainforest.class, BiomeSettings.SNOWYRAINFOREST),
    TEMPORATERAINFOREST(BiomeTemporateRainforest.class, BiomeSettings.TEMPORATERAINFOREST),
    TUNDRA(BiomeTundra.class, BiomeSettings.TUNDRA),
    WASTELAND(BiomeWasteland.class, BiomeSettings.WASTELAND),
    WOODLANDS(BiomeWoodlands.class, BiomeSettings.WOODLANDS);

    private static final String                           LOG_MESSAGE_CONFIG_EXCEPTION_BIOMEID = "log.message.config.exception.biomeid";

    private static Optional<? extends List<BiomeGenBase>> activeBiomes                         = Optional
                                                                                                       .absent();
    public static Collection<BiomeGenBase> getActive() {
        if (!activeBiomes.isPresent()) {
            activeBiomes = Optional.of(new ArrayList<BiomeGenBase>(Biome.values().length));
            for (final Biome biome : Biome.values())
                if (biome.biome.isPresent()) activeBiomes.get().add(biome.biome.get());
        }
        return ImmutableSet.copyOf(activeBiomes.get());
    }

    public Optional<? extends BiomeGenBase>    biome = Optional.absent();
    private final BiomeSettings                 settings;
    public final Class<? extends BiomeGenBase> biomeClass;

    Biome(Class<? extends BiomeGenBase> biomeClass, BiomeSettings settings) {
        this.biomeClass = biomeClass;
        this.settings = settings;
    }

    public Optional<? extends BiomeGenBase> getBiome() {
        return biome;
    }

    public BiomeSettings getSettings() {
        return settings;
    }
}
