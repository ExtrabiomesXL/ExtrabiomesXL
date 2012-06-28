package net.minecraft.src;

import java.util.Random;

public class WorldGenSavanna extends WorldGenerator
{
    private final int field_48202_a;
    private final boolean field_48200_b;
    private final int field_48201_c;
    private final int field_48199_d;

    public WorldGenSavanna(boolean par1)
    {
        this(par1, 4, 0, 0, false);
    }

    public WorldGenSavanna(boolean par1, int par2, int par3, int par4, boolean par5)
    {
        super(par1);
        field_48202_a = par2;
        field_48201_c = par3;
        field_48199_d = par4;
        field_48200_b = par5;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
    	int i = par2Random.nextInt(6) + field_48202_a;
        boolean flag = true;

        if (par4 < 1 || par4 + i + 1 > 256)
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
                byte0 = 2;
            }

            for (int l = par3 - byte0; l <= par3 + byte0 && flag; l++)
            {
                for (int j1 = par5 - byte0; j1 <= par5 + byte0 && flag; j1++)
                {
                    if (j >= 0 && j < 256)
                    {
                        int j2 = par1World.getBlockId(l, j, j1);

                        if (j2 != 0 && j2 != Block.leaves.blockID && j2 != Block.grass.blockID && j2 != Block.dirt.blockID && j2 != Block.wood.blockID)
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

        if (k != Block.grass.blockID && k != Block.dirt.blockID || par4 >= 256 - i - 1)
        {
            return false;
        }

        par1World.setBlock(par3, par4 - 1, par5, Block.dirt.blockID);
        byte byte1 = 3;
        int i1 = 0;

        for (int k1 = (par4 - byte1) + i; k1 <= par4 + i; k1++)
        {
            int k2 = k1 - (par4 + i);
            int j3 = (i1 + 1) - k2;

            for (int l3 = par3 - j3; l3 <= par3 + j3; l3++)
            {
                int j4 = l3 - par3;

                for (int l4 = par5 - j3; l4 <= par5 + j3; l4++)
                {
                    int i5 = l4 - par5;

                    if ((Math.abs(j4) != j3 || Math.abs(i5) != j3 || par2Random.nextInt(2) != 0 && k2 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(l3, k1, l4)])
                    {
                        setBlockAndMetadata(par1World, l3, k1, l4, Block.leaves.blockID, field_48199_d);
                    }
                }
            }
        }

        for (int l1 = 0; l1 < i; l1++)
        {
            int l2 = par1World.getBlockId(par3, par4 + l1, par5);

            if (l2 != 0 && l2 != Block.leaves.blockID)
            {
                continue;
            }

            setBlockAndMetadata(par1World, par3, par4 + l1, par5, Block.wood.blockID, field_48201_c);

            if (!field_48200_b || l1 <= 0)
            {
                continue;
            }

            if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + l1, par5))
            {
                setBlockAndMetadata(par1World, par3 - 1, par4 + l1, par5, Block.vine.blockID, 8);
            }

            if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + l1, par5))
            {
                setBlockAndMetadata(par1World, par3 + 1, par4 + l1, par5, Block.vine.blockID, 2);
            }

            if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + l1, par5 - 1))
            {
                setBlockAndMetadata(par1World, par3, par4 + l1, par5 - 1, Block.vine.blockID, 1);
            }

            if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + l1, par5 + 1))
            {
                setBlockAndMetadata(par1World, par3, par4 + l1, par5 + 1, Block.vine.blockID, 4);
            }
        }

        if (field_48200_b)
        {
            for (int i2 = (par4 - 3) + i; i2 <= par4 + i; i2++)
            {
                int i3 = i2 - (par4 + i);
                int k3 = 2 - i3 / 2;

                for (int i4 = par3 - k3; i4 <= par3 + k3; i4++)
                {
                    for (int k4 = par5 - k3; k4 <= par5 + k3; k4++)
                    {
                        if (par1World.getBlockId(i4, i2, k4) != Block.leaves.blockID)
                        {
                            continue;
                        }

                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(i4 - 1, i2, k4) == 0)
                        {
                            func_48198_a(par1World, i4 - 1, i2, k4, 8);
                        }

                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(i4 + 1, i2, k4) == 0)
                        {
                            func_48198_a(par1World, i4 + 1, i2, k4, 2);
                        }

                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(i4, i2, k4 - 1) == 0)
                        {
                            func_48198_a(par1World, i4, i2, k4 - 1, 1);
                        }

                        if (par2Random.nextInt(4) == 0 && par1World.getBlockId(i4, i2, k4 + 1) == 0)
                        {
                            func_48198_a(par1World, i4, i2, k4 + 1, 4);
                        }
                    }
                }
            }
        }

        return true;
    }

    private void func_48198_a(World par1World, int par2, int par3, int par4, int par5)
    {
        par1World.setBlockAndMetadataWithNotify(par2, par3, par4, Block.vine.blockID, par5);

        for (int i = 4; par1World.getBlockId(par2, --par3, par4) == 0 && i > 0; i--)
        {
            par1World.setBlockAndMetadataWithNotify(par2, par3, par4, Block.vine.blockID, par5);
        }
    }
}