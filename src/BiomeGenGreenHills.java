package net.minecraft.src;

public class BiomeGenGreenHills extends BiomeGenBase
{
    protected BiomeGenGreenHills(int i)
    {
        super(i);
        biomeDecorator.treesPerChunk = 1;
        biomeDecorator.orangeFlowerPerChunk = 1;
        biomeDecorator.whiteFlowerPerChunk = 1;
    }

    public int getBiomeGrassColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerGrass.getGrassColor(var1, var3) & 0xBFFF00) + 0x2B2B21);
    }
    
    public int getBiomeFoliageColor()
    {
        double var1 = (double)this.getFloatTemperature();
        double var3 = (double)this.getFloatRainfall();
        return ((ColorizerFoliage.getFoliageColor(var1, var3) & 0xBFFF00) + 0x2B2B21);
    }
}