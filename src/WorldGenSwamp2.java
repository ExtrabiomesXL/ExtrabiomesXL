package net.minecraft.src;

import java.util.Random;

public class WorldGenSwamp2 extends WorldGenerator
{
    public WorldGenSwamp2()
    {
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int i = par2Random.nextInt(4) + 10;

        for (; par1World.getBlockMaterial(par3, par4 - 1, par5) == Material.water; par4--) { }

        boolean flag = true;

        if (par4 < 1 || par4 + i + 1 > 128)
        {
            return false;
        }

        for (int j = par4; j <= par4 + 1 + i; j++)
        {
            byte byte0 = 1;

            if (j == par4)
            {
                byte0 = 0;
            }

            if (j >= (par4 + 1 + i) - 2)
            {
                byte0 = 3;
            }

            for (int k1 = par3 - byte0; k1 <= par3 + byte0 && flag; k1++)
            {
                for (int k2 = par5 - byte0; k2 <= par5 + byte0 && flag; k2++)
                {
                    if (j >= 0 && j < 128)
                    {
                        int j3 = par1World.getBlockId(k1, j, k2);

                        if (j3 == 0 || j3 == Block.leaves.blockID)
                        {
                            continue;
                        }

                        if (j3 == Block.waterStill.blockID || j3 == Block.waterMoving.blockID)
                        {
                            if (j > par4)
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                    else
                    {
                        flag = false;
                    }
                }
            }
        }

        if (!flag)
        {
            return false;
        }

        int k = par1World.getBlockId(par3, par4 - 1, par5);

        if (k != Block.grass.blockID && k != Block.dirt.blockID || par4 >= 128 - i - 1)
        {
            return false;
        }

        par1World.setBlock(par3, par4 - 1, par5, Block.dirt.blockID);

        for (int l = (par4 - 3) + i; l <= par4 + i; l++)
        {
            int l1 = l - (par4 + i);
            int l2 = 2 - l1 / 2;

            for (int k3 = par3 - l2; k3 <= par3 + l2; k3++)
            {
                int i4 = k3 - par3;

                for (int k4 = par5 - l2; k4 <= par5 + l2; k4++)
                {
                    int l4 = k4 - par5;

                    if ((Math.abs(i4) != l2 || Math.abs(l4) != l2 || par2Random.nextInt(2) != 0 && l1 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(k3, l, k4)])
                    {
                        par1World.setBlock(k3, l, k4, Block.leaves.blockID);
                    }
                }
            }
        }

        for (int i1 = 0; i1 < i; i1++)
        {
            int i2 = par1World.getBlockId(par3, par4 + i1, par5);

            if (i2 == 0 || i2 == Block.leaves.blockID || i2 == Block.waterMoving.blockID || i2 == Block.waterStill.blockID)
            {
                par1World.setBlock(par3, par4 + i1, par5, Block.wood.blockID);
            }
        }

        for (int j1 = (par4 - 3) + i; j1 <= par4 + i; j1++)
        {
            int j2 = j1 - (par4 + i);
            int i3 = 2 - j2 / 2;

            for (int l3 = par3 - i3; l3 <= par3 + i3; l3++)
            {
                for (int j4 = par5 - i3; j4 <= par5 + i3; j4++)
                {
                    if (par1World.getBlockId(l3, j1, j4) != Block.leaves.blockID)
                    {
                        continue;
                    }

                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(l3 - 1, j1, j4) == 0)
                    {
                        generateVines(par1World, l3 - 1, j1, j4, 8);
                    }

                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(l3 + 1, j1, j4) == 0)
                    {
                        generateVines(par1World, l3 + 1, j1, j4, 2);
                    }

                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(l3, j1, j4 - 1) == 0)
                    {
                        generateVines(par1World, l3, j1, j4 - 1, 1);
                    }

                    if (par2Random.nextInt(4) == 0 && par1World.getBlockId(l3, j1, j4 + 1) == 0)
                    {
                        generateVines(par1World, l3, j1, j4 + 1, 4);
                    }
                }
            }
        }

        return true;
    }
    
    private void generateVines(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.setBlockAndMetadataWithNotify(par2, par3, par4, Block.vine.blockID, par5);

        for (int i = 4; par1World.getBlockId(par2, --par3, par4) == 0 && i > 0; i--)
        {
            par1World.setBlockAndMetadataWithNotify(par2, par3, par4, Block.vine.blockID, par5);
        }
    }
}
