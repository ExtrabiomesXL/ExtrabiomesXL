package net.minecraft.src;

public class GenLayerBiome extends GenLayer
{
    public static BiomeGenBase biomeArray[];
    private BiomeGenBase allowedBiomes[];

    public GenLayerBiome(long par1, GenLayer par3GenLayer, WorldType par4WorldType)
    {
        super(par1);
        allowedBiomes = biomeArray;
        parent = par3GenLayer;

        if (par4WorldType == WorldType.field_48634_d)
        {
            allowedBiomes = (new BiomeGenBase[]
                    {
            			BiomeGenBase.marsh, BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, 
            			BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.field_48416_w, BiomeGenBase.field_48417_x, BiomeGenBase.field_48418_y, BiomeGenBase.alps, 
            			BiomeGenBase.autumnWoods, BiomeGenBase.birchForest, BiomeGenBase.mountainRidge, BiomeGenBase.desertMountain, BiomeGenBase.forestedHills, BiomeGenBase.forestedIsland, BiomeGenBase.glacier, 
            			BiomeGenBase.greenHills, BiomeGenBase.greenSwamp, BiomeGenBase.meadow, BiomeGenBase.miniJungle, BiomeGenBase.rainForest, BiomeGenBase.savanna, BiomeGenBase.shrubland, BiomeGenBase.snowForest, 
            			BiomeGenBase.taigaMountain, BiomeGenBase.temperateRainforest, BiomeGenBase.temperateRainforestSnow, BiomeGenBase.wasteland, BiomeGenBase.woodlands, BiomeGenBase.redwoodForest, BiomeGenBase.redwoodLush, 
            			BiomeGenBase.iceWasteland, BiomeGenBase.pineForest, BiomeGenBase.tundra
                    });
        }
    }
    
    public int[] getInts(int par1, int par2, int par3, int par4)
    {
        int ai[] = parent.getInts(par1, par2, par3, par4);
        int ai1[] = IntCache.getIntCache(par3 * par4);

        for (int i = 0; i < par4; i++)
        {
            for (int j = 0; j < par3; j++)
            {
                initChunkSeed(j + par1, i + par2);
                int k = ai[j + i * par3];

                if (k == 0)
                {
                    ai1[j + i * par3] = 0;
                }
                else if (k == BiomeGenBase.mushroomIsland.biomeID)
                {
                    ai1[j + i * par3] = k;
                }
                else if (k == 1)
                {
                    ai1[j + i * par3] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
                }
                else
                {
                    ai1[j + i * par3] = BiomeGenBase.icePlains.biomeID;
                    ai1[j + i * par3] = BiomeGenBase.tundra.biomeID;
                }
            }
        }

        return ai1;
    }

    static
    {
        biomeArray = (new BiomeGenBase[]
                {
        			BiomeGenBase.marsh, BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, 
        			BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge, BiomeGenBase.field_48416_w, BiomeGenBase.field_48417_x, BiomeGenBase.field_48418_y, BiomeGenBase.alps, 
        			BiomeGenBase.autumnWoods, BiomeGenBase.birchForest, BiomeGenBase.mountainRidge, BiomeGenBase.desertMountain, BiomeGenBase.forestedHills, BiomeGenBase.forestedIsland, BiomeGenBase.glacier, 
        			BiomeGenBase.greenHills, BiomeGenBase.greenSwamp, BiomeGenBase.meadow, BiomeGenBase.miniJungle, BiomeGenBase.rainForest, BiomeGenBase.savanna, BiomeGenBase.shrubland, BiomeGenBase.snowForest, 
        			BiomeGenBase.taigaMountain, BiomeGenBase.temperateRainforest, BiomeGenBase.temperateRainforestSnow, BiomeGenBase.wasteland, BiomeGenBase.woodlands, BiomeGenBase.redwoodForest, BiomeGenBase.redwoodLush, 
        			BiomeGenBase.iceWasteland, BiomeGenBase.pineForest, BiomeGenBase.tundra
                });
    }
}
