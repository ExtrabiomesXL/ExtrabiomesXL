package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenCypressTree extends WorldGenNewTreeBase
{
    
    private enum TreeBlock
    {
        LEAVES(new ItemStack(Blocks.leaves, 1, 1)), TRUNK(new ItemStack(Blocks.log, 1, 1));
        
        private ItemStack      stack;
        private static boolean loadedCustomBlocks = false;
        
        private static void loadCustomBlocks()
        {
            if (Element.LEAVES_CYPRESS.isPresent())
                LEAVES.stack = Element.LEAVES_CYPRESS.get();
            if (Element.LOG_CYPRESS.isPresent())
                TRUNK.stack = Element.LOG_CYPRESS.get();
            
            loadedCustomBlocks = true;
        }
        
        TreeBlock(ItemStack stack)
        {
            this.stack = stack;
        }
        
        public ItemStack get()
        {
            if (!loadedCustomBlocks)
                loadCustomBlocks();
            return this.stack;
        }
        
    }
    
    public WorldGenCypressTree(boolean par1)
    {
        super(par1);
    }
    
    // Store the last seed that was used to generate a tree
    private static long lastSeed = 1234;
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        // Store the seed
        lastSeed = rand.nextLong();
        
        // Make sure we can generate the tree
        if (!checkTree(world, new Random(lastSeed), x, y, z))
            return false;
        
        return generateTree(world, new Random(lastSeed), x, y, z);
    }
    
    public boolean generate(World world, long seed, int x, int y, int z)
    {
        // Store the seed
        lastSeed = seed;
        
        // Make sure we can generate the tree
        if (!checkTree(world, new Random(lastSeed), x, y, z))
            return false;
        
        return generateTree(world, new Random(seed), x, y, z);
    }
    
    //Variables to control the generation
    private static final int    BASE_HEIGHT            = 12;
    private static final int    BASE_HEIGHT_VARIANCE   = 6;
    private static final int    CANOPY_START_HEIGHT    = 0;
    private static final int    CANOPY_START_VARIANCE  = 4;
    private static final double CANOPY_RADIUS          = 1.5D;
    private static final double CANOPY_RADIUS_VARIANCE = 1.0D;
    
    private boolean checkTree(World world, Random rand, int x, int y, int z)
    {
        final Block below = world.getBlock(x, y - 1, z);
        final int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        int start = CANOPY_START_HEIGHT + (int) ((rand.nextDouble() * CANOPY_START_VARIANCE) - (CANOPY_START_VARIANCE / 2));
        double radius = (CANOPY_RADIUS + ((rand.nextDouble() * CANOPY_RADIUS_VARIANCE) + (CANOPY_RADIUS_VARIANCE / 2)));
        double factor = 16.0D / (2 + height - start);
        final int chunkCheck = (int) Math.ceil(radius) + 1;
        
        // Make sure that a tree can grow on the soil
        if (!TreeSoilRegistry.isValidSoil(below) || y >= 256 - height - 4)
            return false;
        
        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256)
            return false;
        
        // Make sure the chunks are loaded
        if (!world.checkChunksExist(x - chunkCheck, y - chunkCheck, z - chunkCheck, x + chunkCheck, y + chunkCheck, z + chunkCheck))
            return false;
        
        // See if we can generate the tree
        if (!check1x1Trunk(x, y, z, height, TreeBlock.TRUNK.get(), world))
            return false;
        
        // Check the leaves the leaves
        for (int layer = 0; layer < 4 + height - start; layer++)
        {
            double offset = factor * layer;
            double offset2 = offset * offset;
            double offset3 = offset2 * offset;
            double r1 = radius * ((0.00142 * offset3) - (0.0517 * offset2) + (0.5085 * offset) - 0.4611);
            if (!checkLeavesCircle(x, layer + start + y, z, r1, world))
                return false;
        }
        
        return true;
    }
    
    private boolean generateTree(World world, Random rand, int x, int y, int z)
    {
        final Block below = world.getBlock(x, y - 1, z);
        final int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        int start = CANOPY_START_HEIGHT + (int) ((rand.nextDouble() * CANOPY_START_VARIANCE) - (CANOPY_START_VARIANCE / 2));
        double radius = (CANOPY_RADIUS + ((rand.nextDouble() * CANOPY_RADIUS_VARIANCE) + (CANOPY_RADIUS_VARIANCE / 2)));
        double factor = 16.0D / (2 + height - start);
        final int chunkCheck = (int) Math.ceil(radius) + 1;
        
        // Make sure that a tree can grow on the soil
        if (!TreeSoilRegistry.isValidSoil(below) || y >= 256 - height - 4)
            return false;
        
        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256)
            return false;
        
        // Make sure the chunks are loaded
        if (!world.checkChunksExist(x - chunkCheck, y - chunkCheck, z - chunkCheck, x + chunkCheck, y + chunkCheck, z + chunkCheck))
            return false;
        
        // See if we can generate the tree
        if (place1x1Trunk(x, y, z, height, TreeBlock.TRUNK.get(), world))
        {
            // Generate the leaves
            for (int layer = 0; layer < 4 + height - start; layer++)
            {
                double offset = factor * layer;
                double offset2 = offset * offset;
                double offset3 = offset2 * offset;
                double r1 = radius * ((0.00142 * offset3) - (0.0517 * offset2) + (0.5085 * offset) - 0.4611);
                placeLeavesCircle(x, layer + start + y, z, r1, TreeBlock.LEAVES.get(), world);
            }
            
            return true;
        }
        
        return false;
    }
    
    public static long getLastSeed()
    {
        return lastSeed;
    }
    
}
