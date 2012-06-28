package net.minecraft.src;

import java.util.*;

public class MapGenVillage extends MapGenStructure
{
	
    public static List villageSpawnBiomes;
    private final int terrainType;

    public MapGenVillage(int par1)
    {
        terrainType = par1;
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        byte byte0 = 32;
        byte byte1 = 8;
        int i = par1;
        int j = par2;

        if (par1 < 0)
        {
            par1 -= byte0 - 1;
        }

        if (par2 < 0)
        {
            par2 -= byte0 - 1;
        }

        int k = par1 / byte0;
        int l = par2 / byte0;
        Random random = worldObj.setRandomSeed(k, l, 0x9e7f70);
        k *= byte0;
        l *= byte0;
        k += random.nextInt(byte0 - byte1);
        l += random.nextInt(byte0 - byte1);
        par1 = i;
        par2 = j;

        if (par1 == k && par2 == l)
        {
            boolean flag = worldObj.getWorldChunkManager().areBiomesViable(par1 * 16 + 8, par2 * 16 + 8, 0, villageSpawnBiomes);

            if (flag)
            {
                return true;
            }
        }

        return false;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        return new StructureVillageStart(worldObj, rand, par1, par2, terrainType);
    }

    static
    {
        villageSpawnBiomes = Arrays.asList(new BiomeGenBase[]
                {
                    BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.desertHills, 
                    BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.field_48416_w, BiomeGenBase.field_48417_x, BiomeGenBase.field_48418_y, BiomeGenBase.alps, BiomeGenBase.autumnWoods, 
                    BiomeGenBase.birchForest, BiomeGenBase.mountainRidge, BiomeGenBase.desertMountain, BiomeGenBase.forestedHills, BiomeGenBase.forestedIsland, BiomeGenBase.greenHills, 
                    BiomeGenBase.greenSwamp, BiomeGenBase.meadow, BiomeGenBase.miniJungle, BiomeGenBase.rainForest, BiomeGenBase.savanna, BiomeGenBase.shrubland, BiomeGenBase.snowForest, BiomeGenBase.taigaMountain, 
                    BiomeGenBase.temperateRainforest, BiomeGenBase.temperateRainforestSnow, BiomeGenBase.woodlands, BiomeGenBase.redwoodForest, BiomeGenBase.redwoodLush, BiomeGenBase.pineForest, BiomeGenBase.tundra
                });
    }
}
