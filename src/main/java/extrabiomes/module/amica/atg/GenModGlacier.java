package extrabiomes.module.amica.atg;

import java.util.Random;

import net.minecraft.world.World;
import ttftcuts.atg.api.IGenMod;

public class GenModGlacier implements IGenMod
{
    @Override
    public int modify(World world, int height, Random random, double rawHeight,
    		int x, int z)
    {
        return height + 3;
    }
    
    @Override
    public double noiseFactor()
    {
        return 20.0;
    }
    
}
