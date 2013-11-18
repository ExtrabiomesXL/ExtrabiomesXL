package extrabiomes.module.amica.atg;

import static ttftcuts.atg.api.ATGBiomes.BiomeType.LAND;
import static ttftcuts.atg.api.ATGBiomes.BiomeType.SEA;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.ForgeSubscribe;
import ttftcuts.atg.api.ATGBiomes;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Biomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;

public class ATGPlugin
{
    private static final String               MOD_ID = "ATG";
    private static Optional<Class<ATGBiomes>> api    = Optional.absent();
    
    @ForgeSubscribe
    public void preInit(PluginEvent.Pre event)
    {
        if (!Extrabiomes.proxy.isModLoaded(MOD_ID))
        {
            return;
        }
        
        LogHelper.fine("Initializing %s plugin.", MOD_ID);
        try
        {
            api = Optional.of(ATGBiomes.class);
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
            LogHelper.fine("Could not communicate with %s. Disabling plugin.", MOD_ID);
            api = Optional.absent();
        }
    }
    
    @ForgeSubscribe
    public void init(PluginEvent.Init event)
    {
        if (!api.isPresent())
            return;
        
        addSubBiomes();
        addLandBiomes();
        addOceanBiomes();
    }
    
    @ForgeSubscribe
    public void postInit(PluginEvent.Post event)
    {
        api = Optional.absent();
    }
    
    private static void addSubBiomes()
    {
        BiomeGenBase parent = null;
        
        // icePlains
        parent = BiomeGenBase.icePlains;
        if (BiomeSettings.ICEWASTELAND.isEnabled() && BiomeSettings.ICEWASTELAND.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.ICEWASTELAND.getBiome().get(), 0.25);
        if (BiomeSettings.GLACIER.isEnabled() && BiomeSettings.GLACIER.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.GLACIER.getBiome().get(), 0.75);
        
        // iceMountains
        parent = BiomeGenBase.iceMountains;
        if (BiomeSettings.ALPINE.isEnabled() && BiomeSettings.ALPINE.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.ALPINE.getBiome().get(), 0.5);
        if (BiomeSettings.MOUNTAINTAIGA.isEnabled() && BiomeSettings.MOUNTAINTAIGA.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.MOUNTAINTAIGA.getBiome().get(), 0.25);
        
        // taiga
        parent = BiomeGenBase.taiga;
        if (BiomeSettings.PINEFOREST.isEnabled() && BiomeSettings.PINEFOREST.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.PINEFOREST.getBiome().get(), 0.5);
        if (BiomeSettings.SNOWYFOREST.isEnabled() && BiomeSettings.SNOWYFOREST.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.SNOWYFOREST.getBiome().get(), 0.5);
        
        // plains
        parent = BiomeGenBase.plains;
        if (BiomeSettings.MEADOW.isEnabled() && BiomeSettings.MEADOW.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.MEADOW.getBiome().get(), 0.75);
        
        // swampland
        parent = BiomeGenBase.swampland;
        if (BiomeSettings.GREENSWAMP.isEnabled() && BiomeSettings.GREENSWAMP.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.GREENSWAMP.getBiome().get(), 0.75);
        if (BiomeSettings.MARSH.isEnabled() && BiomeSettings.MARSH.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.MARSH.getBiome().get(), 0.75);
        
        // forest
        parent = BiomeGenBase.forest;
        if (BiomeSettings.AUTUMNWOODS.isEnabled() && BiomeSettings.AUTUMNWOODS.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.AUTUMNWOODS.getBiome().get(), 1.0);
        if (BiomeSettings.BIRCHFOREST.isEnabled() && BiomeSettings.BIRCHFOREST.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.BIRCHFOREST.getBiome().get(), 0.1);
        if (BiomeSettings.REDWOODFOREST.isEnabled() && BiomeSettings.REDWOODFOREST.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.REDWOODFOREST.getBiome().get(), 0.5);
        if (BiomeSettings.WOODLANDS.isEnabled() && BiomeSettings.WOODLANDS.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.WOODLANDS.getBiome().get(), 1.0);
        
        // forestHills
        parent = BiomeGenBase.forestHills;
        if (BiomeSettings.GREENHILLS.isEnabled() && BiomeSettings.GREENHILLS.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.GREENHILLS.getBiome().get(), 1.0);
        
        // ocean
        parent = BiomeGenBase.ocean;
        if (BiomeSettings.FORESTEDISLAND.isEnabled() && BiomeSettings.FORESTEDISLAND.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.FORESTEDISLAND.getBiome().get(), 0.01);
        
    }
    
    private static void addLandBiomes()
    {
        addForestBiomes();
        addJungleBiomes();
        addPlainsBiomes();
        addIcePlainsBiomes();
        addTaigaBiomes();
        addDesertBiomes();
        addShrublandBiomes();
        addTundraBiomes();
        addSavannahBiomes();
        addWoodlandBiomes();
        addSwamplandBiomes();
    }
    
    private static void addForestBiomes()
    {
        if (BiomeSettings.AUTUMNWOODS.isEnabled() && BiomeSettings.AUTUMNWOODS.getBiome().isPresent())
            ATGBiomes.addBiome(LAND, "forest", BiomeSettings.AUTUMNWOODS.getBiome().get(), 1.0);
    }
    
    private static void addJungleBiomes()
    {
        if (BiomeSettings.RAINFOREST.isEnabled() && BiomeSettings.RAINFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(LAND, "jungle", BiomeSettings.RAINFOREST.getBiome().get(), 0.8);
    }
    
    private static void addPlainsBiomes()
    {
        if (BiomeSettings.MEADOW.isEnabled() && BiomeSettings.MEADOW.getBiome().isPresent())
            ATGBiomes.addBiome(LAND, "plains", BiomeSettings.MEADOW.getBiome().get(), 0.75);
    }
    
    private static void addIcePlainsBiomes()
    {
        if (BiomeSettings.ICEWASTELAND.isEnabled() && BiomeSettings.ICEWASTELAND.getBiome().isPresent())
            ATGBiomes.addBiome(LAND, "icePlains", BiomeSettings.ICEWASTELAND.getBiome().get(), 0.25);
        if (BiomeSettings.GLACIER.isEnabled() && BiomeSettings.GLACIER.getBiome().isPresent())
            ATGBiomes.addBiome(LAND, "icePlains", BiomeSettings.GLACIER.getBiome().get(), 0.75);
    }
    
    private static void addTaigaBiomes()
    {}
    
    private static void addDesertBiomes()
    {}
    
    private static void addShrublandBiomes()
    {}
    
    private static void addTundraBiomes()
    {}
    
    private static void addSavannahBiomes()
    {}
    
    private static void addWoodlandBiomes()
    {}
    
    private static void addSwamplandBiomes()
    {}
    
    private static void addOceanBiomes()
    {
        if (BiomeSettings.FORESTEDISLAND.isEnabled())
            ATGBiomes.addBiome(SEA, "Ocean", Biomes.getBiome(BiomeSettings.FORESTEDISLAND.name()).get(), 0.05);
    }
}
