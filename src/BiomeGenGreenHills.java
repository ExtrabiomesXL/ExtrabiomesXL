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

    public int func_48415_j()
    {
        double d = func_48411_i();
        double d1 = func_48414_h();
        return ((ColorizerGrass.getGrassColor(d, d1) & 0xBFFF00) + 0x2B2B21);
    }

    public int func_48412_k()
    {
        double d = func_48411_i();
        double d1 = func_48414_h();
        return ((ColorizerFoliage.getFoliageColor(d, d1) & 0xBFFF00) + 0x2B2B21);
    }
}
