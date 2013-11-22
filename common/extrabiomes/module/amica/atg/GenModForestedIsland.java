package extrabiomes.module.amica.atg;

import java.util.Random;

import ttftcuts.atg.api.IGenMod;

public class GenModForestedIsland implements IGenMod
{
    @Override
    public int modify(int height, Random random, double rawHeight)
    {
        if (height < 52)
            return height + 25;
        else
            return height;
    }
    
    @Override
    public double noiseFactor()
    {
        return 0.0;
    }
    
}
