package extrabiomes.module.amica.atg;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModGlacier implements IGenMod
{
    @Override
    public int modify(int height, Random random, double rawHeight)
    {
        return height + 4;
    }
    
    @Override
    public double noiseFactor()
    {
        return 20.0;
    }
    
}
