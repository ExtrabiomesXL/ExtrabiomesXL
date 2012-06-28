package net.minecraft.src;

import java.io.PrintStream;
import java.util.*;

public class MapGenStronghold extends MapGenStructure
{
    private BiomeGenBase allowedBiomeGenBases[];
    
    private boolean ranBiomeCheck;
    private ChunkCoordIntPair structureCoords[];

    public MapGenStronghold()
    {
        allowedBiomeGenBases = (new BiomeGenBase[]
                {
                    BiomeGenBase.marsh, BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, 
                    BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.field_48416_w, BiomeGenBase.field_48417_x, BiomeGenBase.field_48418_y, BiomeGenBase.alps, 
                    BiomeGenBase.autumnWoods, BiomeGenBase.birchForest, BiomeGenBase.mountainRidge, BiomeGenBase.desertMountain, BiomeGenBase.forestedHills, BiomeGenBase.forestedIsland, BiomeGenBase.glacier, 
                    BiomeGenBase.greenHills, BiomeGenBase.greenSwamp, BiomeGenBase.meadow, BiomeGenBase.miniJungle, BiomeGenBase.miniJungleMountain, BiomeGenBase.rainForest, BiomeGenBase.savanna, BiomeGenBase.shrubland, 
                    BiomeGenBase.snowForest, BiomeGenBase.taigaMountain, BiomeGenBase.temperateRainforest, BiomeGenBase.temperateRainforestSnow, BiomeGenBase.wasteland, BiomeGenBase.woodlands, BiomeGenBase.redwoodForest, 
                    BiomeGenBase.redwoodLush, BiomeGenBase.iceWasteland, BiomeGenBase.pineForest, BiomeGenBase.tundra
                });
        structureCoords = new ChunkCoordIntPair[3];
    }

    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        if (!ranBiomeCheck)
        {
            Random random = new Random();
            random.setSeed(worldObj.getSeed());
            double d = random.nextDouble() * Math.PI * 2D;

            for (int k = 0; k < structureCoords.length; k++)
            {
                double d1 = (1.25D + random.nextDouble()) * 32D;
                int l = (int)Math.round(Math.cos(d) * d1);
                int i1 = (int)Math.round(Math.sin(d) * d1);
                ArrayList arraylist = new ArrayList();
                BiomeGenBase abiomegenbase[] = allowedBiomeGenBases;
                int j1 = abiomegenbase.length;

                for (int k1 = 0; k1 < j1; k1++)
                {
                    BiomeGenBase biomegenbase = abiomegenbase[k1];
                    arraylist.add(biomegenbase);
                }

                ChunkPosition chunkposition = worldObj.getWorldChunkManager().findBiomePosition((l << 4) + 8, (i1 << 4) + 8, 112, arraylist, random);

                if (chunkposition != null)
                {
                    l = chunkposition.x >> 4;
                    i1 = chunkposition.z >> 4;
                }
                else
                {
                    System.out.println((new StringBuilder()).append("Placed stronghold in INVALID biome at (").append(l).append(", ").append(i1).append(")").toString());
                }

                structureCoords[k] = new ChunkCoordIntPair(l, i1);
                d += (Math.PI * 2D) / (double)structureCoords.length;
            }

            ranBiomeCheck = true;
        }

        ChunkCoordIntPair achunkcoordintpair[] = structureCoords;
        int i = achunkcoordintpair.length;

        for (int j = 0; j < i; j++)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[j];

            if (par1 == chunkcoordintpair.chunkXPos && par2 == chunkcoordintpair.chunkZPos)
            {
                System.out.println((new StringBuilder()).append(par1).append(", ").append(par2).toString());
                return true;
            }
        }

        return false;
    }

    protected List func_40482_a()
    {
        ArrayList arraylist = new ArrayList();
        ChunkCoordIntPair achunkcoordintpair[] = structureCoords;
        int i = achunkcoordintpair.length;

        for (int j = 0; j < i; j++)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[j];

            if (chunkcoordintpair != null)
            {
                arraylist.add(chunkcoordintpair.getChunkPosition(64));
            }
        }

        return arraylist;
    }

    protected StructureStart getStructureStart(int par1, int par2)
    {
        StructureStrongholdStart structurestrongholdstart;

        for (structurestrongholdstart = new StructureStrongholdStart(worldObj, rand, par1, par2); structurestrongholdstart.getComponents().isEmpty() || ((ComponentStrongholdStairs2)structurestrongholdstart.getComponents().get(0)).field_40009_b == null; structurestrongholdstart = new StructureStrongholdStart(worldObj, rand, par1, par2)) { }

        return structurestrongholdstart;
    }
}
