package net.minecraft.src;

import java.util.Random;

public class WorldGenCatTail extends WorldGenerator
{
    public WorldGenCatTail()
    {
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for (int l = 0; l < 20; l++)
        {
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = j;
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if (!world.isAirBlock(i1, j1, k1) || world.getBlockMaterial(i1 - 1, j1 - 1, k1) != Material.water && world.getBlockMaterial(i1 + 1, j1 - 1, k1) != Material.water && world.getBlockMaterial(i1, j1 - 1, k1 - 1) != Material.water && world.getBlockMaterial(i1, j1 - 1, k1 + 1) != Material.water)
            {
                continue;
            }
            int l1 = 1 + random.nextInt(random.nextInt(1) + 1);
            for (int i2 = 0; i2 < l1; i2++)
            {
                if (mod_ExtraBiomesXL.catTail.canBlockStay(world, i1, j1 + i2, k1))
                {
                    world.setBlock(i1, j1 + i2, k1, mod_ExtraBiomesXL.catTail.blockID);
                }
            }
        }

        return true;
    }
}