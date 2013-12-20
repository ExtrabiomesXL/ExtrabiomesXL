/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGenNewTreeBase extends WorldGenerator
{
    
    int leafCount = 0;
    
    public WorldGenNewTreeBase(boolean par1)
    {
        super(par1);
    }
    
    public boolean check1x1Trunk(int x, int y, int z, int height, ItemStack logs, World world)
    {
        for (int y1 = y + 1; y1 < y + height; y1++)
        {
            if (Block.blocksList[world.getBlockId(x, y1, z)] != null)
                return false;
        }
        
        return true;
    }
    
    public boolean place1x1Trunk(int x, int y, int z, int height, ItemStack logs, World world)
    {
        
        // Place the wood blocks
        for (int y1 = y; y1 < y + height; y1++)
        {
            setBlockAndMetadata(world, x, y1, z, logs.itemID, logs.getItemDamage());
        }
        
        return true;
    }
    
    public boolean place2x2Trunk(int x, int y, int z, int height, ItemStack logs, World world)
    {
        for (int y1 = y; y1 < y + height; y1++)
        {
            setBlockAndMetadata(world, x, y1, z, logs.itemID, 0);
            setBlockAndMetadata(world, x + 1, y1, z, logs.itemID, 1);
            setBlockAndMetadata(world, x, y1, z + 1, logs.itemID, 3);
            setBlockAndMetadata(world, x + 1, y1, z + 1, logs.itemID, 2);
        }
        
        return true;
    }
    
    public boolean check2x2Trunk(int x, int y, int z, int height, ItemStack logs, World world, boolean inWater)
    {
        if (inWater)
        {
            for (int y1 = y + 1; y1 < y + height; y1++)
            {
                int b00 = world.getBlockId(x, y1, z);
                int b10 = world.getBlockId(x + 1, y1, z);
                int b01 = world.getBlockId(x, y1, z + 1);
                int b11 = world.getBlockId(x + 1, y1, z + 1);
                if (Block.blocksList[b00] != null && b00 != Block.waterStill.blockID && b00 != Block.waterStill.blockID)
                    return false;
                if (Block.blocksList[b10] != null && b10 != Block.waterStill.blockID && b10 != Block.waterStill.blockID)
                    return false;
                if (Block.blocksList[b01] != null && b01 != Block.waterStill.blockID && b01 != Block.waterStill.blockID)
                    return false;
                if (Block.blocksList[b11] != null && b11 != Block.waterStill.blockID && b11 != Block.waterStill.blockID)
                    return false;
            }
        }
        else
        {
            for (int y1 = y + 1; y1 < y + height; y1++)
            {
                if (Block.blocksList[world.getBlockId(x, y1, z)] != null)
                    return false;
                if (Block.blocksList[world.getBlockId(x + 1, y1, z)] != null)
                    return false;
                if (Block.blocksList[world.getBlockId(x, y1, z + 1)] != null)
                    return false;
                if (Block.blocksList[world.getBlockId(x + 1, y1, z + 1)] != null)
                    return false;
            }
        }
        
        return true;
    }
    
    public boolean placeKnee(int x, int y, int z, int height, int direction, ItemStack logs, ItemStack knees, World world)
    {
        if (direction > 3)
            return false;
        
        int orientation = 0;
        
        switch (direction)
        {
            case 0:
                orientation = 4;
                break;
            case 1:
                orientation = 9;
                break;
            case 2:
                orientation = 5;
                break;
            case 3:
                orientation = 8;
                break;
            default:
                break;
        }
        
        for (int y1 = y - 1; y1 > 1; y1--)
        {
            Block block = Block.blocksList[world.getBlockId(x, y1, z)];
            if (block != null && !block.canBeReplacedByLeaves(world, x, y1, z))
                break;
            
            // If there is an air block here place a root log
            setBlockAndMetadata(world, x, y1, z, logs.itemID, logs.getItemDamage());
        }
        
        for (int y1 = y; y1 < y + height - 1; y1++)
        {
            setBlockAndMetadata(world, x, y1, z, logs.itemID, logs.getItemDamage());
        }
        
        // Place the knee on top
        setBlockAndMetadata(world, x, y + height - 1, z, knees.itemID, orientation);
        
        return true;
    }
    
    public boolean checkBlockLine(int[] start, int[] end, ItemStack logs, World world)
    {
        if (start.length != 3 || end.length != 3)
            return false;
        
        // Get the direction vector
        int[] direction = {
                start[0] - end[0],
                start[1] - end[1],
                start[2] - end[2]
        };
        if (Math.abs(direction[2]) > Math.abs(direction[1]) && Math.abs(direction[2]) > Math.abs(direction[0]))
        {
            // We are going to use the y axis as our major axis
            if (direction[2] >= 0)
            {
                for (int z = start[2]; z >= end[2]; z--)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] != null)
                        return false;
                }
            }
            else
            {
                for (int z = start[2]; z <= end[2]; z++)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] != null)
                        return false;
                }
            }
        }
        else if (Math.abs(direction[0]) > Math.abs(direction[1]))
        {
            // Treverse along the x axis
            if (direction[0] >= 0)
            {
                for (int x = start[0]; x >= end[0]; x--)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] != null)
                        return false;
                }
            }
            else
            {
                for (int x = start[0]; x <= end[0]; x++)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] != null)
                        return false;
                }
            }
        }
        else
        {
            // We will use the y axis as our major axis
            if (direction[1] >= 0)
            {
                for (int y = start[1]; y >= end[1]; y--)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] != null)
                        return false;
                }
            }
            else
            {
                for (int y = start[1]; y <= end[1]; y++)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] != null)
                        return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean placeBlockLine(int[] start, int[] end, ItemStack logs, World world)
    {
        if (start.length != 3 || end.length != 3)
            return false;
        
        // Get the direction vector
        int[] direction = {
                start[0] - end[0],
                start[1] - end[1],
                start[2] - end[2]
        };
        if (Math.abs(direction[2]) > Math.abs(direction[1]) && Math.abs(direction[2]) > Math.abs(direction[0]))
        {
            // We are going to use the y axis as our major axis
            if (direction[2] >= 0)
            {
                for (int z = start[2]; z >= end[2]; z--)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 8);
                }
            }
            else
            {
                for (int z = start[2]; z <= end[2]; z++)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 8);
                }
            }
        }
        else if (Math.abs(direction[0]) > Math.abs(direction[1]))
        {
            // Treverse along the x axis
            if (direction[0] >= 0)
            {
                for (int x = start[0]; x >= end[0]; x--)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 4);
                }
            }
            else
            {
                for (int x = start[0]; x <= end[0]; x++)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 4);
                }
            }
        }
        else
        {
            // We will use the y axis as our major axis
            if (direction[1] >= 0)
            {
                for (int y = start[1]; y >= end[1]; y--)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage());
                }
            }
            else
            {
                for (int y = start[1]; y <= end[1]; y++)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage());
                }
            }
        }
        
        return true;
    }
    
    public boolean placeThinBlockLine(int[] start, int[] end, ItemStack logs, World world)
    {
        if (start.length != 3 || end.length != 3)
            return false;
        
        int[] last = { start[0], start[1], start[2] };
        
        // Get the direction vector
        int[] direction = {
                start[0] - end[0],
                start[1] - end[1],
                start[2] - end[2]
        };
        if (Math.abs(direction[2]) > Math.abs(direction[1]) && Math.abs(direction[2]) > Math.abs(direction[0]))
        {
            // We are going to use the y axis as our major axis
            if (direction[2] >= 0)
            {
                for (int z = start[2]; z >= end[2]; z--)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 8);
                    
                    // Detect the distance
                    int dist = Math.abs(last[0] - x) + Math.abs(last[1] - y) + Math.abs(last[2] - z);
                    //LogHelper.info("Dist: %d", dist);
                    if (dist == 2)
                    {
                        setBlockAndMetadata(world, last[0], last[1], z, logs.itemID, logs.getItemDamage() | 8);
                    }
                    else if (dist == 3)
                    {
                        if (direction[0] > 0)
                        {
                            setBlockAndMetadata(world, x, last[1], last[2], logs.itemID, logs.getItemDamage() | 8);
                            setBlockAndMetadata(world, x, y, last[2], logs.itemID, logs.getItemDamage() | 8);
                        }
                        else
                        {
                            setBlockAndMetadata(world, last[0], y, last[2], logs.itemID, logs.getItemDamage() | 8);
                            setBlockAndMetadata(world, x, y, last[2], logs.itemID, logs.getItemDamage() | 8);
                        }
                    }
                    
                    last[0] = x;
                    last[1] = y;
                    last[2] = z;
                }
            }
            else
            {
                for (int z = start[2]; z <= end[2]; z++)
                {
                    double m = (z - start[2]) / (double) direction[2];
                    int x = (int) (start[0] + (direction[0] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 8);
                    
                    // Detect the distance
                    int dist = Math.abs(last[0] - x) + Math.abs(last[1] - y) + Math.abs(last[2] - z);
                    //LogHelper.info("Dist: %d", dist);
                    if (dist == 2)
                    {
                        setBlockAndMetadata(world, last[0], last[1], z, logs.itemID, logs.getItemDamage() | 8);
                    }
                    else if (dist == 3)
                    {
                        if (direction[0] > 0)
                        {
                            setBlockAndMetadata(world, x, last[1], last[2], logs.itemID, logs.getItemDamage() | 8);
                            setBlockAndMetadata(world, x, y, last[2], logs.itemID, logs.getItemDamage() | 8);
                        }
                        else
                        {
                            setBlockAndMetadata(world, last[0], y, last[2], logs.itemID, logs.getItemDamage() | 8);
                            setBlockAndMetadata(world, x, y, last[2], logs.itemID, logs.getItemDamage() | 8);
                        }
                    }
                    
                    last[0] = x;
                    last[1] = y;
                    last[2] = z;
                }
            }
        }
        else if (Math.abs(direction[0]) > Math.abs(direction[1]))
        {
            // Treverse along the x axis
            if (direction[0] >= 0)
            {
                for (int x = start[0]; x >= end[0]; x--)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 4);
                    
                    // Detect the distance
                    int dist = Math.abs(last[0] - x) + Math.abs(last[1] - y) + Math.abs(last[2] - z);
                    if (dist == 2)
                    {
                        setBlockAndMetadata(world, x, last[1], last[2], logs.itemID, logs.getItemDamage() | 4);
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            setBlockAndMetadata(world, last[0], last[1], z, logs.itemID, logs.getItemDamage() | 4);
                            setBlockAndMetadata(world, last[0], y, z, logs.itemID, logs.getItemDamage() | 4);
                        }
                        else
                        {
                            setBlockAndMetadata(world, last[0], y, last[2], logs.itemID, logs.getItemDamage() | 4);
                            setBlockAndMetadata(world, last[0], y, z, logs.itemID, logs.getItemDamage() | 4);
                        }
                    }
                    
                    last[0] = x;
                    last[1] = y;
                    last[2] = z;
                }
            }
            else
            {
                for (int x = start[0]; x <= end[0]; x++)
                {
                    double m = (x - start[0]) / (double) direction[0];
                    int z = (int) (start[2] + (direction[2] * m));
                    int y = (int) (start[1] + (direction[1] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage() | 4);
                    
                    // Detect the distance
                    int dist = Math.abs(last[0] - x) + Math.abs(last[1] - y) + Math.abs(last[2] - z);
                    if (dist == 2)
                    {
                        setBlockAndMetadata(world, x, last[1], last[2], logs.itemID, logs.getItemDamage() | 4);
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            setBlockAndMetadata(world, last[0], last[1], z, logs.itemID, logs.getItemDamage() | 4);
                            setBlockAndMetadata(world, last[0], y, z, logs.itemID, logs.getItemDamage() | 4);
                        }
                        else
                        {
                            setBlockAndMetadata(world, last[0], y, last[2], logs.itemID, logs.getItemDamage() | 4);
                            setBlockAndMetadata(world, last[0], y, z, logs.itemID, logs.getItemDamage() | 4);
                        }
                    }
                    
                    last[0] = x;
                    last[1] = y;
                    last[2] = z;
                }
            }
        }
        else
        {
            // We will use the y axis as our major axis
            if (direction[1] >= 0)
            {
                for (int y = start[1]; y >= end[1]; y--)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage());
                    
                    // Detect the distance
                    int dist = Math.abs(last[0] - x) + Math.abs(last[1] - y) + Math.abs(last[2] - z);
                    if (dist == 2)
                    {
                        setBlockAndMetadata(world, last[0], y, last[2], logs.itemID, logs.getItemDamage());
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            setBlockAndMetadata(world, last[0], last[1], z, logs.itemID, logs.getItemDamage());
                            setBlockAndMetadata(world, x, last[1], z, logs.itemID, logs.getItemDamage());
                        }
                        else
                        {
                            setBlockAndMetadata(world, x, last[1], last[2], logs.itemID, logs.getItemDamage());
                            setBlockAndMetadata(world, x, last[1], z, logs.itemID, logs.getItemDamage());
                        }
                    }
                    
                    last[0] = x;
                    last[1] = y;
                    last[2] = z;
                }
            }
            else
            {
                for (int y = start[1]; y <= end[1]; y++)
                {
                    double m = (y - start[1]) / (double) direction[1];
                    int x = (int) (start[0] + (direction[0] * m));
                    int z = (int) (start[2] + (direction[2] * m));
                    if (Block.blocksList[world.getBlockId(x, y, z)] == null)
                        setBlockAndMetadata(world, x, y, z, logs.itemID, logs.getItemDamage());
                    
                    // Detect the distance
                    int dist = Math.abs(last[0] - x) + Math.abs(last[1] - y) + Math.abs(last[2] - z);
                    if (dist == 2)
                    {
                        setBlockAndMetadata(world, last[0], y, last[2], logs.itemID, logs.getItemDamage());
                    }
                    else if (dist == 3)
                    {
                        if (direction[2] > 0)
                        {
                            setBlockAndMetadata(world, last[0], last[1], z, logs.itemID, logs.getItemDamage());
                            setBlockAndMetadata(world, x, last[1], z, logs.itemID, logs.getItemDamage());
                        }
                        else
                        {
                            setBlockAndMetadata(world, x, last[1], last[2], logs.itemID, logs.getItemDamage());
                            setBlockAndMetadata(world, x, last[1], z, logs.itemID, logs.getItemDamage());
                        }
                    }
                    
                    last[0] = x;
                    last[1] = y;
                    last[2] = z;
                }
            }
        }
        
        return true;
    }
    
    public boolean checkLeavesCircle(double x, int y, double z, double r, World world)
    {
        double dist = r * r;
        
        for (double z1 = Math.floor(-r); z1 < r + 1; z1++)
        {
            for (double x1 = Math.floor(-r); x1 < r + 1; x1++)
            {
                int x2 = (int) (x1 + x);
                int z2 = (int) (z1 + z);
                
                final Block block = Block.blocksList[world.getBlockId(x2, y, z2)];
                
                if (((x1 * x1) + (z1 * z1)) <= dist)
                {
                    if (block != null)
                        return false;
                }
            }
        }
        
        return true;
    }
    
    public void placeLeavesCircle(double x, int y, double z, double r, ItemStack leaves, World world)
    {
        double dist = r * r;
        
        for (double z1 = Math.floor(-r); z1 < r + 1; z1++)
        {
            for (double x1 = Math.floor(-r); x1 < r + 1; x1++)
            {
                int x2 = (int) (x1 + x);
                int z2 = (int) (z1 + z);
                
                final Block block = Block.blocksList[world.getBlockId(x2, y, z2)];
                
                if ((((x1 * x1) + (z1 * z1)) <= dist) && (block == null || block.canBeReplacedByLeaves(world, x2, y, z2)))
                {
                    setLeafBlock(world, x2, y, z2, leaves);
                }
            }
        }
    }
    
    public void setLeafBlock(World world, int x, int y, int z, ItemStack leaves)
    {
        leafCount++;
        setBlockAndMetadata(world, x, y, z, leaves.itemID, leaves.getItemDamage());
    }
    
    public boolean checkLeafCluster(World world, int x, int y, int z, int height, int radius)
    {
        for (int layer = -height; layer <= height; layer++)
        {
            if (!checkLeavesCircle(x, y + layer, z, radius * Math.cos(layer / (height / 1.3)), world))
                return false;
        }
        
        return true;
    }
    
    public void generateLeafCluster(World world, int x, int y, int z, int height, int radius, ItemStack leaves)
    {
        for (int layer = -height; layer <= height; layer++)
        {
            placeLeavesCircle(x, y + layer, z, radius * Math.cos(layer / (height / 1.3)), leaves, world);
        }
    }
}