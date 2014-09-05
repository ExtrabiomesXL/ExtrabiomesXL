/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenFirTree extends WorldGenAbstractTree
{
    
    private enum TreeBlock
    {
        LEAVES(new ItemStack(Blocks.leaves, 1, 1)), TRUNK(new ItemStack(Blocks.log, 1, 1));
        
        private ItemStack      stack;
        
        private static boolean loadedCustomBlocks = false;
        
        private static void loadCustomBlocks()
        {
            if (Element.LEAVES_FIR.isPresent())
                LEAVES.stack = Element.LEAVES_FIR.get();
            if (Element.LOG_FIR.isPresent())
                TRUNK.stack = Element.LOG_FIR.get();
            
            loadedCustomBlocks = true;
        }
        
        TreeBlock(ItemStack stack)
        {
            this.stack = stack;
        }
        
        public Block getBlock()
        {
            if (!loadedCustomBlocks)
                loadCustomBlocks();
            return Block.getBlockFromItem(stack.getItem());
        }
        
        public int getMetadata()
        {
            if (!loadedCustomBlocks)
                loadCustomBlocks();
            return stack.getItemDamage();
        }
        
    }
    
    public WorldGenFirTree(boolean par1)
    {
        super(par1);
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
        final Block below = world.getBlock(x, y - 1, z);
        final int height = rand.nextInt(8) + 24;
        
        if (!TreeSoilRegistry.isValidSoil(below) || y >= 256 - height - 1)
            return false;
        
        if (y < 1 || y + height + 1 > 256)
            return false;
        
        final int j = 1 + rand.nextInt(12);
        final int l = 2 + rand.nextInt(6);
        
        for (int i1 = y; i1 <= y + 1 + height; i1++)
        {
            if (i1 < 0 || i1 >= 256)
                return false;
            
            int k1 = 1;
            
            if (i1 - y < j)
                k1 = 0;
            else
                k1 = l;
            
            for (int x1 = x - k1; x1 <= x + k1; x1++)
            {
                for (int z1 = z - k1; z1 <= z + k1; z1++)
                {
                    final Block block = world.getBlock(x1, i1, z1);
                    
                    if (block != null && !block.isReplaceable(world, x1, i1, z1))
                        return false;
                }
            }
        }
        
        world.setBlock(x, y - 1, z, Blocks.dirt);
        int l1 = rand.nextInt(2);
        int j2 = 1;
        boolean flag1 = false;
        
        for (int i3 = 0; i3 <= height - j; i3++)
        {
            final int k3 = y + height - i3;
            
            for (int i4 = x - l1; i4 <= x + l1; i4++)
            {
                final int k4 = i4 - x;
                
                for (int l4 = z - l1; l4 <= z + l1; l4++)
                {
                    final int i5 = l4 - z;
                    
                    final Block block = world.getBlock(i4, k3, l4);
                    
                    if ((Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0) && (block == null || block.canBeReplacedByLeaves(world, i4, k3, l4)))
                    {
                        setBlockAndNotifyAdequately(world, i4, k3, l4, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                    }
                }
            }
            
            if (l1 >= j2)
            {
                l1 = flag1 ? 1 : 0;
                flag1 = true;
                
                if (++j2 > l)
                    j2 = l;
            }
            else
            {
                l1++;
            }
        }
        
        final int j3 = rand.nextInt(3);
        
        for (int l3 = 0; l3 < height - j3; l3++)
        {
            final Block block = world.getBlock(x, y + l3, z);
            
            if (block == null || block.isLeaves(world, x, y + l3, z) || block.isAir(world, x, y + l3, z) )
            {
                setBlockAndNotifyAdequately(world, x, y + l3, z, TreeBlock.TRUNK.getBlock(), TreeBlock.TRUNK.getMetadata());
            }
        }
        
        return true;
    }
    
    public static long getLastSeed()
    {
        return lastSeed;
    }
}