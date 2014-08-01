package extrabiomes.module.amica.atg;

import ttftcuts.atg.api.ATGBiomes;
import ttftcuts.atg.api.ATGBiomes.BiomeType;
import extrabiomes.lib.BiomeSettings;

public class ATGPluginImpl
{
    public void init()
    {
        addSubBiomes();
        addLandBiomes();
        addBeachBiomes();
    }
    
    private void addSubBiomes()
    {
        /*BiomeGenBase parent = null;
        
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
        if (BiomeSettings.SNOWYFOREST.isEnabled() && BiomeSettings.SNOWYFOREST.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.SNOWYFOREST.getBiome().get(), 0.5);
        if (BiomeSettings.SNOWYRAINFOREST.isEnabled() && BiomeSettings.SNOWYRAINFOREST.getBiome().isPresent())
            ATGBiomes.addSubBiome(parent, BiomeSettings.SNOWYRAINFOREST.getBiome().get(), 0.5);
        
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
            ATGBiomes.addSubBiome(parent, BiomeSettings.FORESTEDISLAND.getBiome().get(), 0.01);*/
        
    }
    
    private void addLandBiomes()
    {
        addForestBiomes();
        addJungleBiomes();
        addPlainsBiomes();
        addIcePlainsBiomes();
        addTaigaBiomes();
        addDesertBiomes();
        addShrublandBiomes();
        addBorealForestBiomes();
        addTundraBiomes();
        addSteppeBiomes();
        addSavannahBiomes();
        addWoodlandBiomes();
        addMesaBiomes();
        addSwamplandBiomes();
    }
    
    private void addForestBiomes()
    {
        String group = "Forest";
        if (BiomeSettings.AUTUMNWOODS.isEnabled() && BiomeSettings.AUTUMNWOODS.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.AUTUMNWOODS.getBiome().get(), 1.0);
        if (BiomeSettings.BIRCHFOREST.isEnabled() && BiomeSettings.BIRCHFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.BIRCHFOREST.getBiome().get(), 0.4);
        if (BiomeSettings.REDWOODFOREST.isEnabled() && BiomeSettings.REDWOODFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.REDWOODFOREST.getBiome().get(), 0.5);
    }
    
    private void addJungleBiomes()
    {
        String group = "Jungle";
        if (BiomeSettings.RAINFOREST.isEnabled() && BiomeSettings.RAINFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.RAINFOREST.getBiome().get(), 0.4);
        if (BiomeSettings.REDWOODLUSH.isEnabled() && BiomeSettings.REDWOODLUSH.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.REDWOODLUSH.getBiome().get(), 0.7);
        if (BiomeSettings.GREENSWAMP.isEnabled() && BiomeSettings.GREENSWAMP.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.GREENSWAMP.getBiome().get(), 0.2);
        if (BiomeSettings.MINIJUNGLE.isEnabled() && BiomeSettings.MINIJUNGLE.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.MINIJUNGLE.getBiome().get(), 0.4);
    }
    
    private void addPlainsBiomes()
    {
        String group = "Plains";
        if (BiomeSettings.MEADOW.isEnabled() && BiomeSettings.MEADOW.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.MEADOW.getBiome().get(), 1.0);
    }
    
    private void addIcePlainsBiomes()
    {
        String group = "Ice Plains";
        if (BiomeSettings.ICEWASTELAND.isEnabled() && BiomeSettings.ICEWASTELAND.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.ICEWASTELAND.getBiome().get(), 0.25);
        if (BiomeSettings.GLACIER.isEnabled() && BiomeSettings.GLACIER.getBiome().isPresent())
        {
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.GLACIER.getBiome().get(), 0.75);
            ATGBiomes.addGenMod(BiomeSettings.GLACIER.getBiome().get(), new GenModGlacier());
        }
    }
    
    private void addTaigaBiomes()
    {
        String group = "Taiga";
        if (BiomeSettings.MOUNTAINTAIGA.isEnabled() && BiomeSettings.MOUNTAINTAIGA.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.MOUNTAINTAIGA.getBiome().get(), 0.75);
        if (BiomeSettings.SNOWYFOREST.isEnabled() && BiomeSettings.SNOWYFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.SNOWYFOREST.getBiome().get(), 0.75);
        if (BiomeSettings.SNOWYRAINFOREST.isEnabled() && BiomeSettings.SNOWYRAINFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.SNOWYRAINFOREST.getBiome().get(), 0.75);
    }
    
    private void addDesertBiomes()
    {
        String group = "Desert";
        if (BiomeSettings.WASTELAND.isEnabled() && BiomeSettings.WASTELAND.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.WASTELAND.getBiome().get(), 1.0);
    }
    
    private void addShrublandBiomes()
    {
        String group = "Shrubland";
        if (BiomeSettings.SHRUBLAND.isEnabled() && BiomeSettings.SHRUBLAND.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.SHRUBLAND.getBiome().get(), 1.0);
    }
    
    private void addBorealForestBiomes()
    {
        String group = "Boreal Forest";
        if (BiomeSettings.PINEFOREST.isEnabled() && BiomeSettings.PINEFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.PINEFOREST.getBiome().get(), 0.75);
        if (BiomeSettings.TEMPORATERAINFOREST.isEnabled() && BiomeSettings.TEMPORATERAINFOREST.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.TEMPORATERAINFOREST.getBiome().get(), 0.4);
    }
    
    private void addTundraBiomes()
    {
        String group = "Tundra";
        if (BiomeSettings.TUNDRA.isEnabled() && BiomeSettings.TUNDRA.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.TUNDRA.getBiome().get(), 1.0);
    }
    
    private void addSteppeBiomes()
    {
        String group = "Steppe";
        if (BiomeSettings.GREENHILLS.isEnabled() && BiomeSettings.GREENHILLS.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.GREENHILLS.getBiome().get(), 1.0);
        if (BiomeSettings.FORESTEDHILLS.isEnabled() && BiomeSettings.FORESTEDHILLS.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.FORESTEDHILLS.getBiome().get(), 1.0);
        if (BiomeSettings.ALPINE.isEnabled() && BiomeSettings.ALPINE.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.ALPINE.getBiome().get(), 1.0);
    }
    
    private void addSavannahBiomes()
    {
        String group = "Savanna";
        if (BiomeSettings.SAVANNA.isEnabled() && BiomeSettings.SAVANNA.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.SAVANNA.getBiome().get(), 1.0);
    }
    
    private void addWoodlandBiomes()
    {
        String group = "Woodland";
        if (BiomeSettings.WOODLANDS.isEnabled() && BiomeSettings.WOODLANDS.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.WOODLANDS.getBiome().get(), 1.0);
    }
    
    private void addMesaBiomes()
    {
        String group = "Mesa";
        if (BiomeSettings.MOUNTAINRIDGE.isEnabled() && BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.MOUNTAINRIDGE.getBiome().get(), 0.8);
        if (BiomeSettings.MOUNTAINDESERT.isEnabled() && BiomeSettings.MOUNTAINDESERT.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.LAND, group, BiomeSettings.MOUNTAINDESERT.getBiome().get(), 0.3);
    }
    
    private void addSwamplandBiomes()
    {
        String group = "Swampland";
        if (BiomeSettings.SWAMPLAND.isEnabled() && BiomeSettings.SWAMPLAND.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.COAST, group, BiomeSettings.SWAMPLAND.getBiome().get(), 0.05);
        if (BiomeSettings.GREENSWAMP.isEnabled() && BiomeSettings.GREENSWAMP.getBiome().isPresent())
            ATGBiomes.addBiome(BiomeType.COAST, group, BiomeSettings.GREENSWAMP.getBiome().get(), 0.05);
    }
    
    private void addBeachBiomes()
    {
        String group = "Beach";
        /*if (BiomeSettings.FORESTEDISLAND.isEnabled() && BiomeSettings.FORESTEDISLAND.getBiome().isPresent())
        {
            ATGBiomes.addBiome(COAST, group, BiomeSettings.FORESTEDISLAND.getBiome().get(), 1.0);
            //ATGBiomes.addGenMod(BiomeSettings.FORESTEDISLAND.getBiome().get(), new GenModForestedIsland());
        }*/
    }
}
