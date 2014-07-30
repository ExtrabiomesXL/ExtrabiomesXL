/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class WorldGenBigAutumnTree extends WorldGenAutumnTree
{
    
    private static Block          trunkBlock      = Blocks.log;
    private static int          trunkMetadata   = 0;
    
    private static final byte[] otherCoordPairs = new byte[] { (byte) 2, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 1 };
    
    public static void setTrunkBlock(Block block, int metadata)
    {
        WorldGenBigAutumnTree.trunkBlock = block;
        WorldGenBigAutumnTree.trunkMetadata = metadata;
    }
    
    private final Random rand              = new Random();
    private World        world;
    private final int[]  basePos           = new int[] { 0, 0, 0 };
    private int          heightLimit       = 0;
    private int          height;
    private final double heightAttenuation = 0.618D;
    private final double branchSlope       = 0.381D;
    private double       scaleWidth        = 1.1D;
    private double       leafDensity       = 1.0D;
    private int          heightLimitLimit  = 12;
    private int          leafDistanceLimit = 4;
    private int[][]      leafNodes;
    
    public WorldGenBigAutumnTree(boolean notify, AutumnTreeType type)
    {
        super(notify, type);
    }
    
    private int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger)
    {
        final int[] var3 = new int[] { 0, 0, 0 };
        byte var4 = 0;
        byte var5;
        
        for (var5 = 0; var4 < 3; ++var4)
        {
            var3[var4] = par2ArrayOfInteger[var4] - par1ArrayOfInteger[var4];
            
            if (Math.abs(var3[var4]) > Math.abs(var3[var5]))
                var5 = var4;
        }
        
        if (var3[var5] == 0)
        {
            return -1;
        }
        else
        {
            final byte var6 = otherCoordPairs[var5];
            final byte var7 = otherCoordPairs[var5 + 3];
            byte var8;
            
            if (var3[var5] > 0)
                var8 = 1;
            else
                var8 = -1;
            
            final double var9 = (double) var3[var6] / (double) var3[var5];
            final double var11 = (double) var3[var7] / (double) var3[var5];
            final int[] coord = new int[] { 0, 0, 0 };
            int var14 = 0;
            int var15;
            
            for (var15 = var3[var5] + var8; var14 != var15; var14 += var8)
            {
                coord[var5] = par1ArrayOfInteger[var5] + var14;
                coord[var6] = MathHelper.floor_double(par1ArrayOfInteger[var6] + var14 * var9);
                coord[var7] = MathHelper.floor_double(par1ArrayOfInteger[var7] + var14 * var11);
                final Block block = world.getBlock(coord[0], coord[1], coord[2]);
                
                if (!block.isAir(world, coord[0], coord[1], coord[2]) && !block.isLeaves(world, coord[0], coord[1], coord[2]))
                {
                    break;
                }
            }
            
            return var14 == var15 ? -1 : Math.abs(var14);
        }
    }
    
    // Store the last seed that was used to generate a tree
    private static long lastSeed = 0;
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        // Store the seed
        lastSeed = rand.nextLong();
        
        return generateTree(world, new Random(lastSeed), x, y, z);
    }
    
    public boolean generate(World world, long seed, int x, int y, int z)
    {
        // Store the seed
        lastSeed = seed;
        
        return generateTree(world, new Random(seed), x, y, z);
    }
    
    private boolean generateTree(World world, Random rand, int x, int y, int z)
    {
        this.world = world;
        final long var6 = rand.nextLong();
        rand.setSeed(var6);
        basePos[0] = x;
        basePos[1] = y;
        basePos[2] = z;
        
        if (heightLimit == 0)
            heightLimit = 5 + rand.nextInt(heightLimitLimit);
        
        if (!validTreeLocation())
            return false;
        
        generateLeafNodeList();
        generateLeaves(type.getBlock(), type.getMetadata());
        generateTrunk(trunkBlock, trunkMetadata);
        generateLeafNodeBases(trunkBlock, trunkMetadata);
        
        return true;
    }
    
    private void generateLeafNode(int x, int y, int z, Block leaf, int leafMeta)
    {
        int y1 = y;
        
        for (final int heightLimit = y + leafDistanceLimit; y1 < heightLimit; ++y1)
        {
            final float size = leafSize(y1 - y);
            genTreeLayer(x, y1, z, size, (byte) 1, leaf, leafMeta);
        }
    }
    
    private void generateLeafNodeBases(Block wood, int woodMeta)
    {
        int var1 = 0;
        final int var2 = leafNodes.length;
        
        for (final int[] var3 = new int[] { basePos[0], basePos[1], basePos[2] }; var1 < var2; ++var1)
        {
            final int[] var4 = leafNodes[var1];
            final int[] var5 = new int[] { var4[0], var4[1], var4[2] };
            var3[1] = var4[3];
            final int var6 = var3[1] - basePos[1];
            
            if (leafNodeNeedsBase(var6))
                placeBlockLine(var3, var5, wood, woodMeta);
        }
    }
    
    private void generateLeafNodeList()
    {
        height = (int) (heightLimit * heightAttenuation);
        
        if (height >= heightLimit)
            height = heightLimit - 1;
        
        int var1 = (int) (1.382D + Math.pow(leafDensity * heightLimit / 13.0D, 2.0D));
        
        if (var1 < 1)
            var1 = 1;
        
        final int[][] var2 = new int[var1 * heightLimit][4];
        int var3 = basePos[1] + heightLimit - leafDistanceLimit;
        int var4 = 1;
        final int var5 = basePos[1] + height;
        int var6 = var3 - basePos[1];
        var2[0][0] = basePos[0];
        var2[0][1] = var3;
        var2[0][2] = basePos[2];
        var2[0][3] = var5;
        --var3;
        
        while (var6 >= 0)
        {
            int var7 = 0;
            final float var8 = layerSize(var6);
            
            if (var8 < 0.0F)
            {
                --var3;
                --var6;
            }
            else
            {
                for (final double var9 = 0.5D; var7 < var1; ++var7)
                {
                    final double var11 = scaleWidth * var8 * (rand.nextFloat() + 0.328D);
                    final double var13 = rand.nextFloat() * 2.0D * Math.PI;
                    final int var15 = MathHelper.floor_double(var11 * Math.sin(var13) + basePos[0]
                            + var9);
                    final int var16 = MathHelper.floor_double(var11 * Math.cos(var13) + basePos[2]
                            + var9);
                    final int[] var17 = new int[] { var15, var3, var16 };
                    final int[] var18 = new int[] { var15, var3 + leafDistanceLimit, var16 };
                    
                    if (checkBlockLine(var17, var18) == -1)
                    {
                        final int[] var19 = new int[] { basePos[0], basePos[1], basePos[2] };
                        final double var20 = Math.sqrt(Math.pow(Math.abs(basePos[0] - var17[0]),
                                2.0D) + Math.pow(Math.abs(basePos[2] - var17[2]), 2.0D));
                        final double var22 = var20 * branchSlope;
                        
                        if (var17[1] - var22 > var5)
                        {
                            var19[1] = var5;
                        }
                        else
                        {
                            var19[1] = (int) (var17[1] - var22);
                        }
                        
                        if (checkBlockLine(var19, var17) == -1)
                        {
                            var2[var4][0] = var15;
                            var2[var4][1] = var3;
                            var2[var4][2] = var16;
                            var2[var4][3] = var19[1];
                            ++var4;
                        }
                    }
                }
                
                --var3;
                --var6;
            }
        }
        
        leafNodes = new int[var4][4];
        System.arraycopy(var2, 0, leafNodes, 0, var4);
    }
    
    private void generateLeaves(Block leaf, int leafMeta)
    {
        int node = 0;
        
        for (final int length = leafNodes.length; node < length; ++node)
        {
            generateLeafNode(leafNodes[node][0], leafNodes[node][1], leafNodes[node][2], leaf, leafMeta);
        }
    }
    
    private void generateTrunk(Block wood, int woodMeta)
    {
        final int var1 = basePos[0];
        final int var2 = basePos[1];
        final int var3 = basePos[1] + height;
        final int var4 = basePos[2];
        final int[] var5 = new int[] { var1, var2, var4 };
        final int[] var6 = new int[] { var1, var3, var4 };
        placeBlockLine(var5, var6, wood, woodMeta);
    }
    
    private void genTreeLayer(int x, int y, int z, float size, byte par5, Block leafBlock, int leafBlockMeta)
    {
        final int var7 = (int) (size + 0.618D);
        final byte var8 = otherCoordPairs[par5];
        final byte var9 = otherCoordPairs[par5 + 3];
        final int[] var10 = new int[] { x, y, z };
        final int[] var11 = new int[] { 0, 0, 0 };
        int var12 = -var7;
        int var13 = -var7;
        
        for (var11[par5] = var10[par5]; var12 <= var7; ++var12)
        {
            var11[var8] = var10[var8] + var12;
            var13 = -var7;
            
            while (var13 <= var7)
            {
                final double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
                
                if (var15 > size * size)
                {
                    ++var13;
                }
                else
                {
                    var11[var9] = var10[var9] + var13;
                    final Block block = world.getBlock(var11[0], var11[1], var11[2]);
                    
                    if (block != null && block.isLeaves(world, var11[0], var11[1], var11[2]))
                    {
                        ++var13;
                    }
                    else
                    {
                        if (block == null || block.canBeReplacedByLeaves(world, var11[0], var11[1], var11[2]))
                        {
                            setBlockAndNotifyAdequately(world, var11[0], var11[1], var11[2], leafBlock, leafBlockMeta);
                        }
                        
                        ++var13;
                    }
                }
            }
        }
    }
    
    private float layerSize(int par1)
    {
        if (par1 < heightLimit * 0.3D)
        {
            return -1.618F;
        }
        else
        {
            final float var2 = heightLimit / 2.0F;
            final float var3 = heightLimit / 2.0F - par1;
            float var4;
            
            if (var3 == 0.0F)
                var4 = var2;
            else if (Math.abs(var3) >= var2)
                var4 = 0.0F;
            else
                var4 = (float) Math.sqrt(Math.pow(Math.abs(var2), 2.0D)
                        - Math.pow(Math.abs(var3), 2.0D));
            
            var4 *= 0.5F;
            return var4;
        }
    }
    
    private boolean leafNodeNeedsBase(int par1)
    {
        return par1 >= heightLimit * 0.2D;
    }
    
    float leafSize(int par1)
    {
        return par1 >= 0 && par1 < leafDistanceLimit ? par1 != 0 && par1 != leafDistanceLimit - 1 ? 3.0F : 2.0F : -1.0F;
    }
    
    private void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block wood, int woodMeta)
    {
        final int[] var4 = new int[] { 0, 0, 0 };
        byte var5 = 0;
        byte var6;
        
        for (var6 = 0; var5 < 3; ++var5)
        {
            var4[var5] = par2ArrayOfInteger[var5] - par1ArrayOfInteger[var5];
            
            if (Math.abs(var4[var5]) > Math.abs(var4[var6]))
            {
                var6 = var5;
            }
        }
        
        if (var4[var6] != 0)
        {
            final byte var7 = otherCoordPairs[var6];
            final byte var8 = otherCoordPairs[var6 + 3];
            byte var9;
            
            if (var4[var6] > 0)
            {
                var9 = 1;
            }
            else
            {
                var9 = -1;
            }
            
            final double var10 = (double) var4[var7] / (double) var4[var6];
            final double var12 = (double) var4[var8] / (double) var4[var6];
            final int[] var14 = new int[] { 0, 0, 0 };
            int var15 = 0;
            
            for (final int var16 = var4[var6] + var9; var15 != var16; var15 += var9)
            {
                var14[var6] = MathHelper.floor_double(par1ArrayOfInteger[var6] + var15 + 0.5D);
                var14[var7] = MathHelper.floor_double(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
                var14[var8] = MathHelper.floor_double(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
                byte woodMetaWithDirection = (byte) woodMeta;
                final int var18 = Math.abs(var14[0] - par1ArrayOfInteger[0]);
                final int var19 = Math.abs(var14[2] - par1ArrayOfInteger[2]);
                final int var20 = Math.max(var18, var19);
                
                if (var20 > 0)
                    if (var18 == var20)
                    {
                        woodMetaWithDirection |= 4;
                    }
                    else if (var19 == var20)
                    {
                        woodMetaWithDirection |= 8;
                    }
                
                setBlockAndNotifyAdequately(world, var14[0], var14[1], var14[2], wood,
                        woodMetaWithDirection);
            }
        }
    }
    
    @Override
    public void setScale(double par1, double par3, double par5)
    {
        heightLimitLimit = (int) (par1 * 12.0D);
        
        if (par1 > 0.5D)
            leafDistanceLimit = 5;
        
        scaleWidth = par3;
        leafDensity = par5;
    }
    
    private boolean validTreeLocation()
    {
        final int[] var1 = new int[] { basePos[0], basePos[1], basePos[2] };
        final int[] var2 = new int[] { basePos[0], basePos[1] + heightLimit - 1, basePos[2] };
        final Block var3 = world.getBlock(basePos[0], basePos[1] - 1, basePos[2]);
        
        if (!var3.equals(Blocks.grass) && !var3.equals(Blocks.dirt))
            return false;
        else
        {
            final int var4 = checkBlockLine(var1, var2);
            
            if (var4 == -1)
                return true;
            else if (var4 < 6)
                return false;
            else
            {
                heightLimit = var4;
                return true;
            }
        }
    }
    
    public static long getLastSeed()
    {
        return lastSeed;
    }
}
