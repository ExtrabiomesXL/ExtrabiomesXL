package net.minecraft.src;

import java.util.*;

public class WorldChunkManager
{
    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private BiomeCache biomeCache;
    private List biomesToSpawnIn;

    protected WorldChunkManager()
    {
        biomeCache = new BiomeCache(this);
        biomesToSpawnIn = new ArrayList();
		biomesToSpawnIn.add(BiomeGenBase.desert);
		biomesToSpawnIn.add(BiomeGenBase.ocean);
		biomesToSpawnIn.add(BiomeGenBase.mushroomIsland);
		biomesToSpawnIn.add(BiomeGenBase.mushroomIslandShore);
		biomesToSpawnIn.add(BiomeGenBase.river);
		biomesToSpawnIn.add(BiomeGenBase.swampland);
		biomesToSpawnIn.add(BiomeGenBase.extremeHills);
		biomesToSpawnIn.add(BiomeGenBase.beach);
		biomesToSpawnIn.add(BiomeGenBase.icePlains);
		biomesToSpawnIn.add(BiomeGenBase.iceMountains);
		biomesToSpawnIn.add(BiomeGenBase.frozenOcean);
		biomesToSpawnIn.add(BiomeGenBase.frozenRiver);
		biomesToSpawnIn.add(BiomeGenBase.desertHills);
		biomesToSpawnIn.add(BiomeGenBase.forestHills);
		biomesToSpawnIn.add(BiomeGenBase.extremeHillsEdge);
        biomesToSpawnIn.add(BiomeGenBase.forest);
        biomesToSpawnIn.add(BiomeGenBase.plains);
        biomesToSpawnIn.add(BiomeGenBase.taiga);
        biomesToSpawnIn.add(BiomeGenBase.taigaHills);
        biomesToSpawnIn.add(BiomeGenBase.forestHills);
        biomesToSpawnIn.add(BiomeGenBase.field_48416_w);
        biomesToSpawnIn.add(BiomeGenBase.field_48417_x);
        biomesToSpawnIn.add(BiomeGenBase.field_48418_y);
        biomesToSpawnIn.add(BiomeGenBase.alps);
        biomesToSpawnIn.add(BiomeGenBase.autumnWoods);
        biomesToSpawnIn.add(BiomeGenBase.birchForest);
        biomesToSpawnIn.add(BiomeGenBase.mountainRidge);
        biomesToSpawnIn.add(BiomeGenBase.desertMountain);
        biomesToSpawnIn.add(BiomeGenBase.forestedHills);
        biomesToSpawnIn.add(BiomeGenBase.forestedIsland);
        biomesToSpawnIn.add(BiomeGenBase.greenHills);
        biomesToSpawnIn.add(BiomeGenBase.greenSwamp);
		biomesToSpawnIn.add(BiomeGenBase.glacier);
        biomesToSpawnIn.add(BiomeGenBase.meadow);
        biomesToSpawnIn.add(BiomeGenBase.miniJungle);
        biomesToSpawnIn.add(BiomeGenBase.rainForest);
        biomesToSpawnIn.add(BiomeGenBase.savanna);
        biomesToSpawnIn.add(BiomeGenBase.shrubland);
        biomesToSpawnIn.add(BiomeGenBase.snowForest);
        biomesToSpawnIn.add(BiomeGenBase.taigaMountain);
        biomesToSpawnIn.add(BiomeGenBase.temperateRainforest);
        biomesToSpawnIn.add(BiomeGenBase.temperateRainforestSnow);
        biomesToSpawnIn.add(BiomeGenBase.wasteland);
        biomesToSpawnIn.add(BiomeGenBase.woodlands);
        biomesToSpawnIn.add(BiomeGenBase.redwoodForest);
        biomesToSpawnIn.add(BiomeGenBase.redwoodLush);
        biomesToSpawnIn.add(BiomeGenBase.marsh);
        biomesToSpawnIn.add(BiomeGenBase.pineForest);
        biomesToSpawnIn.add(BiomeGenBase.tundra);
    }

    public WorldChunkManager(long par1, WorldType par3WorldType)
    {
        this();
        GenLayer agenlayer[] = GenLayer.func_48425_a(par1, par3WorldType);
        genBiomes = agenlayer[0];
        biomeIndexLayer = agenlayer[1];
    }

    public WorldChunkManager(World par1World)
    {
        this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
    }
    
    public List getBiomesToSpawnIn()
    {
        return biomesToSpawnIn;
    }
    
    public BiomeGenBase getBiomeGenAt(int par1, int par2)
    {
        return biomeCache.getBiomeGenAt(par1, par2);
    }
    
    public float[] getRainfall(float par1ArrayOfFloat[], int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int ai[] = biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int i = 0; i < par4 * par5; i++)
        {
            float f = (float)BiomeGenBase.biomeList[ai[i]].getIntRainfall() / 65536F;

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            par1ArrayOfFloat[i] = f;
        }

        return par1ArrayOfFloat;
    }
    
    public float getTemperatureAtHeight(float par1, int par2)
    {
        return par1;
    }
    
    public float[] getTemperatures(float par1ArrayOfFloat[], int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
        {
            par1ArrayOfFloat = new float[par4 * par5];
        }

        int ai[] = biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int i = 0; i < par4 * par5; i++)
        {
            float f = (float)BiomeGenBase.biomeList[ai[i]].getIntTemperature() / 65536F;

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            par1ArrayOfFloat[i] = f;
        }

        return par1ArrayOfFloat;
    }
    
    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3, int par4, int par5)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        int ai[] = genBiomes.getInts(par2, par3, par4, par5);

        for (int i = 0; i < par4 * par5; i++)
        {
            par1ArrayOfBiomeGenBase[i] = BiomeGenBase.biomeList[ai[i]];
        }

        return par1ArrayOfBiomeGenBase;
    }
    
    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3, int par4, int par5)
    {
        return getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
    }
    
    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase par1ArrayOfBiomeGenBase[], int par2, int par3, int par4, int par5, boolean par6)
    {
        IntCache.resetIntCache();

        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
        {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }

        if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xf) == 0 && (par3 & 0xf) == 0)
        {
            BiomeGenBase abiomegenbase[] = biomeCache.getCachedBiomes(par2, par3);
            System.arraycopy(abiomegenbase, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
            return par1ArrayOfBiomeGenBase;
        }

        int ai[] = biomeIndexLayer.getInts(par2, par3, par4, par5);

        for (int i = 0; i < par4 * par5; i++)
        {
            par1ArrayOfBiomeGenBase[i] = BiomeGenBase.biomeList[ai[i]];
        }

        return par1ArrayOfBiomeGenBase;
    }
    
    public boolean areBiomesViable(int par1, int par2, int par3, List par4List)
    {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = (k - i) + 1;
        int j1 = (l - j) + 1;
        int ai[] = genBiomes.getInts(i, j, i1, j1);

        for (int k1 = 0; k1 < i1 * j1; k1++)
        {
            BiomeGenBase biomegenbase = BiomeGenBase.biomeList[ai[k1]];

            if (!par4List.contains(biomegenbase))
            {
                return false;
            }
        }

        return true;
    }
    
    public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random)
    {
        int i = par1 - par3 >> 2;
        int j = par2 - par3 >> 2;
        int k = par1 + par3 >> 2;
        int l = par2 + par3 >> 2;
        int i1 = (k - i) + 1;
        int j1 = (l - j) + 1;
        int ai[] = genBiomes.getInts(i, j, i1, j1);
        ChunkPosition chunkposition = null;
        int k1 = 0;

        for (int l1 = 0; l1 < ai.length; l1++)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.biomeList[ai[l1]];

            if (par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(k1 + 1) == 0))
            {
                chunkposition = new ChunkPosition(i2, 0, j2);
                k1++;
            }
        }

        return chunkposition;
    }
    
    public void cleanupCache()
    {
        biomeCache.cleanupCache();
    }
}
