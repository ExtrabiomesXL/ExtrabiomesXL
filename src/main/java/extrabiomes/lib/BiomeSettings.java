/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.module.summa.biome.BiomeAlpine;
import extrabiomes.module.summa.biome.BiomeAutumnWoods;
import extrabiomes.module.summa.biome.BiomeBirchForest;
import extrabiomes.module.summa.biome.BiomeExtremeJungle;
import extrabiomes.module.summa.biome.BiomeForestedHills;
import extrabiomes.module.summa.biome.BiomeForestedIsland;
import extrabiomes.module.summa.biome.BiomeGlacier;
import extrabiomes.module.summa.biome.BiomeGreenHills;
import extrabiomes.module.summa.biome.BiomeGreenSwamp;
import extrabiomes.module.summa.biome.BiomeIceWasteland;
import extrabiomes.module.summa.biome.BiomeMarsh;
import extrabiomes.module.summa.biome.BiomeMeadow;
import extrabiomes.module.summa.biome.BiomeMiniJungle;
import extrabiomes.module.summa.biome.BiomeMountainDesert;
import extrabiomes.module.summa.biome.BiomeMountainRidge;
import extrabiomes.module.summa.biome.BiomeMountainTaiga;
import extrabiomes.module.summa.biome.BiomePineForest;
import extrabiomes.module.summa.biome.BiomeRainforest;
import extrabiomes.module.summa.biome.BiomeRedwoodForest;
import extrabiomes.module.summa.biome.BiomeRedwoodLush;
import extrabiomes.module.summa.biome.BiomeSavanna;
import extrabiomes.module.summa.biome.BiomeShrubland;
import extrabiomes.module.summa.biome.BiomeSnowForest;
import extrabiomes.module.summa.biome.BiomeSnowRainforest;
import extrabiomes.module.summa.biome.BiomeTemporateRainforest;
import extrabiomes.module.summa.biome.BiomeTundra;
import extrabiomes.module.summa.biome.BiomeWasteland;
import extrabiomes.module.summa.biome.BiomeWoodlands;
import extrabiomes.utility.EnhancedConfiguration;

public enum BiomeSettings
{
    DESERT,
    EXTREMEHILLS,
    FOREST,
    JUNGLE,
    PLAINS,
    SWAMPLAND,
    TAIGA,
    ALPINE(32, BiomeAlpine.class),
    AUTUMNWOODS(33, BiomeAutumnWoods.class),
    BIRCHFOREST(34, BiomeBirchForest.class),
    EXTREMEJUNGLE(35, BiomeExtremeJungle.class),
    FORESTEDHILLS(36, BiomeForestedHills.class),
    FORESTEDISLAND(37, BiomeForestedIsland.class),
    GLACIER(38, BiomeGlacier.class),
    GREENHILLS(39, BiomeGreenHills.class),
    GREENSWAMP(40, BiomeGreenSwamp.class),
    ICEWASTELAND(41, BiomeIceWasteland.class),
    MARSH(42, BiomeMarsh.class, false),
    MEADOW(43, BiomeMeadow.class),
    MINIJUNGLE(44, BiomeMiniJungle.class),
    MOUNTAINDESERT(45, BiomeMountainDesert.class),
    MOUNTAINRIDGE(46, BiomeMountainRidge.class),
    MOUNTAINTAIGA(47, BiomeMountainTaiga.class),
    PINEFOREST(48, BiomePineForest.class),
    RAINFOREST(49, BiomeRainforest.class),
    REDWOODFOREST(50, BiomeRedwoodForest.class),
    REDWOODLUSH(51, BiomeRedwoodLush.class),
    SAVANNA(52, BiomeSavanna.class),
    SHRUBLAND(53, BiomeShrubland.class),
    SNOWYFOREST(54, BiomeSnowForest.class),
    SNOWYRAINFOREST(55, BiomeSnowRainforest.class),
    TEMPORATERAINFOREST(56, BiomeTemporateRainforest.class),
    TUNDRA(57, BiomeTundra.class),
    WASTELAND(58, BiomeWasteland.class),
    WOODLANDS(59, BiomeWoodlands.class);
    
    private final int                                               defaultID;
    
    private int                                                     biomeID       = 0;
    private boolean                                                 enabled       = true;
    private boolean                                                 allowVillages = true;
    private final Optional<? extends Class<? extends BiomeGenBase>> biomeClass;
    private Optional<? extends BiomeGenBase>                        biome         = Optional
                                                                                          .absent();
    
    private BiomeSettings()
    {
        this(0, null);
    }
    
    private BiomeSettings(int defaultID, Class<? extends BiomeGenBase> biomeClass)
    {
        this.defaultID = defaultID;
        biomeID = this.defaultID;
        this.biomeClass = Optional.fromNullable(biomeClass);
    }
    
    private BiomeSettings(int defaultID, Class<? extends BiomeGenBase> biomeClass, boolean defaultGen)
    {
        this.defaultID = defaultID;
        biomeID = this.defaultID;
        this.biomeClass = Optional.fromNullable(biomeClass);
        this.enabled = defaultGen;
    }
    
    public boolean allowVillages()
    {
        return allowVillages;
    }
    
    public void createBiome() throws Exception
    {
        if (biomeClass.isPresent() && !biome.isPresent())
        {
            biome = Optional.of(biomeClass.get().newInstance());
        }
    }
    
    public Optional<? extends BiomeGenBase> getBiome()
    {
        return biome;
    }
    
    public int getID()
    {
        return biomeID;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
    public boolean isVanilla()
    {
        return !biomeClass.isPresent();
    }
    
    private String keyAllowVillages()
    {
        return keyVanillaPrefix() + toString() + ".allowvillages";
    }
    
    private String keyEnabled()
    {
        return keyVanillaPrefix() + toString() + ".enablegeneration";
    }
    
    private String keyID()
    {
        return toString() + ".id";
    }
    
    private String keyVanillaPrefix()
    {
        return isVanilla() ? "vanilla." : "";
    }
    
    public void load(EnhancedConfiguration configuration)
    {
        Property property;
        
        if (!isVanilla())
        {
            property = configuration.getBiome(keyID(), biomeID);
            biomeID = property.getInt(0);
        }
        
        property = configuration.get(EnhancedConfiguration.CATEGORY_BIOME, keyEnabled(), enabled);
        if (!isVanilla() && biomeID == 0) {
            property.set(Boolean.toString(false));
        }
        enabled = property.getBoolean(false);
        
        property = configuration.get(EnhancedConfiguration.CATEGORY_BIOME, keyAllowVillages(), allowVillages);
        if (!isVanilla() && biomeID == 0) {
            property.set(Boolean.toString(false));
        }
        allowVillages = property.getBoolean(false);
        
    }
    
    @Override
    public String toString()
    {
        return super.toString().toLowerCase(Locale.ENGLISH);
    }
    
}
