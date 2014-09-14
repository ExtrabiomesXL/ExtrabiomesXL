/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenAcacia extends WorldGenAbstractTree
{
    
    private enum TreeBlock
    {
        LEAVES(new ItemStack(Blocks.leaves)), TRUNK(new ItemStack(Blocks.log));
        
        private ItemStack      stack;
        
        private static boolean loadedCustomBlocks = false;
        
        private static void loadCustomBlocks()
        {
            if (Element.LEAVES_ACACIA.isPresent())
                LEAVES.stack = Element.LEAVES_ACACIA.get();
            if (Element.LOG_ACACIA.isPresent())
                TRUNK.stack = Element.LOG_ACACIA.get();
            
            loadedCustomBlocks = true;
        }
        
        TreeBlock(ItemStack stack)
        {
            this.stack = stack;
        }

        public Item getItem()
        {
            if (!loadedCustomBlocks)
                loadCustomBlocks();
            return stack.getItem();
        }
        
        public Block getBlock() {
        	return Block.getBlockFromItem(getItem());
        }
        
        public int getMetadata()
        {
            if (!loadedCustomBlocks)
                loadCustomBlocks();
            return stack.getItemDamage();
        }
        
    }
    
    public WorldGenAcacia(final boolean doNotify)
    {
        super(doNotify);
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
        final int height = rand.nextInt(4) + 6;
        boolean canGrow = true;
        
        if (y < 1 || y + height + 1 > 256)
            return false;
        
        for (int y1 = y; y1 <= y + 1 + height; y1++)
        {
            byte clearance = 1;
            
            if (y1 == y)
                clearance = 0;
            
            if (y1 >= y + 1 + height - 2)
                clearance = 2;
            
            for (int x1 = x - clearance; x1 <= x + clearance && canGrow; x1++)
            {
                for (int z1 = z - clearance; z1 <= z + clearance && canGrow; z1++)
                {
                    if (y1 >= 0 && y1 < 256)
                    {
                        final Block block = world.getBlock(x1, y1, z1);
                        
                        if (block != null
                                && !block.isLeaves(world, x1, y1, z1)
                                && !block.equals(Blocks.grass) && !block.equals(Blocks.dirt)
                                && !block.isWood(world, x1, y1, z1)
                                && !block.isReplaceable(world, x1, y1, z1))
                            canGrow = false;
                        
                    }
                    else
                    {
                        canGrow = false;
                    }
                }
            }
        }
        
        if (!canGrow)
            return false;
        
        if (!TreeSoilRegistry.isValidSoil(world.getBlock(x, y - 1, z)) || y >= 256 - height - 1)
            return false;
        
        world.setBlock(x, y - 1, z, Blocks.dirt);
        final byte canopyHeight = 3;
        final int minCanopyRadius = 0;
        
        for (int y1 = y - canopyHeight + height; y1 <= y + height; y1++)
        {
            final int distanceFromTop = y1 - (y + height);
            final int canopyRadius = minCanopyRadius + 1 - distanceFromTop;
            
            for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++)
            {
                final int xOnRadius = x1 - x;
                
                for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++)
                {
                    final int zOnRadius = z1 - z;
                    
                    final Block block = world.getBlock(x1, y1, z1);
                    
                    if ((Math.abs(xOnRadius) != canopyRadius || Math.abs(zOnRadius) != canopyRadius || rand.nextInt(2) != 0 && distanceFromTop != 0) && (block == null || block.canBeReplacedByLeaves(world, x1, y1, z1)))
                    {
                        setBlockAndNotifyAdequately(world, x1, y1, z1, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                    }
                }
            }
        }
        
        for (int y1 = 0; y1 < height; y1++)
        {
            final Block block = world.getBlock(x, y + y1, z);
            
            if (block != null && !block.isLeaves(world, x, y + y1, z) && !block.isAir(world, x, y + y1, z))
            {
                continue;
            }
            
            setBlockAndNotifyAdequately(world, x, y + y1, z, TreeBlock.TRUNK.getBlock(), TreeBlock.TRUNK.getMetadata());
            
        }
        
        return true;
    }
    
    public static long getLastSeed()
    {
        return lastSeed;
    }
}
