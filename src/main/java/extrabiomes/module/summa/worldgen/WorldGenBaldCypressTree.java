package extrabiomes.module.summa.worldgen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenBaldCypressTree extends WorldGenNewTreeBase
{
    
    private enum TreeBlock
    {
        LEAVES(new ItemStack(Blocks.leaves, 1, 1)), TRUNK(new ItemStack(Blocks.log, 1, 1)), KNEE_LOG(new ItemStack(Blocks.log, 1, 1)), KNEE(new ItemStack(Blocks.log, 1, 1));
        
        private ItemStack      stack;
        private static boolean loadedCustomBlocks = false;
        
        private static void loadCustomBlocks()
        {
            if (Element.LEAVES_BALD_CYPRESS.isPresent())
                LEAVES.stack = Element.LEAVES_BALD_CYPRESS.get();
            if (Element.LOG_QUARTER_BALD_CYPRESS.isPresent())
                TRUNK.stack = Element.LOG_QUARTER_BALD_CYPRESS.get();
            if (Element.LOG_KNEE_BALD_CYPRESS.isPresent())
                KNEE.stack = Element.LOG_KNEE_BALD_CYPRESS.get();
            if (Element.LOG_BALD_CYPRESS.isPresent())
                KNEE_LOG.stack = Element.LOG_BALD_CYPRESS.get();
            
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
    
    public WorldGenBaldCypressTree(boolean par1)
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
        
        // Check the water level
        int waterLevel = 0;
        for (int yy = y - 1; yy > y - 6; yy--)
        {
            Block block = world.getBlock(x, yy, z);
            if (!block.equals(Blocks.water))
                break;
            waterLevel++;
        }
        
        // Adjust the starting position
        y -= waterLevel;
        
        // Make sure that we can generate the tree
        if (!checkTree(world, new Random(lastSeed), x, y, z, waterLevel))
            return false;
        
        return generateTree(world, new Random(lastSeed), x, y, z, waterLevel);
    }
    
    public boolean generate(World world, long seed, int x, int y, int z)
    {
        // Store the seed
        lastSeed = seed;
        
        // Check the water level
        int waterLevel = 0;
        for (int yy = y - 1; yy > y - 6; yy--)
        {
            Block block = world.getBlock(x, yy, z);
            if (!block.equals(Blocks.water))
                break;
            waterLevel++;
        }
        
        // Adjust the starting position
        y -= waterLevel;
        
        // Make sure that we can generate the tree
        if (!checkTree(world, new Random(lastSeed), x, y, z, waterLevel))
            return false;
        
        return generateTree(world, new Random(seed), x, y, z, waterLevel);
    }
    
    //Variables to control the generation
    private static final int    BASE_HEIGHT               = 24;   // The base height for trees
    private static final int    BASE_HEIGHT_VARIANCE      = 10;   // The Max extra branches that a tree can have
    private static final double TRUNK_HEIGHT_PERCENT      = 0.75D; // What percent of the total height the main trunk extends
    private static final double TRUNK_BRANCHES_START      = 0.25D; // How far up the tree the trunk branches start
    private static final int    BRANCHES_BASE_NUMBER      = 15;   // The total number of branches on the tree
    private static final int    BRANCHES_EXTRA            = 10;   // The how many extra branches can occur on the tree
    private static final int    CANOPY_WIDTH              = 15;   // How many blocks will this tree cover
    private static final int    CANOPY_WIDTH_VARIANCE     = 5;    // How many extra blocks may this tree cover
    private static final int    CLUSTER_DIAMATER          = 3;    // How wide should the leaf cluster be generated
    private static final int    CLUSTER_DIAMATER_VARIANCE = 3;    // How many extra blocks can be added to the leaf cluster.
    private static final int    CLUSTER_HEIGHT            = 1;    // How tall should the leaf cluster be generated
    private static final int    CLUSTER_HEIGHT_VARIANCE   = 3;    // How many extra layers can be added to the leaf cluster.
                                                                   
    static int                  last                      = 0;
    
    private boolean checkTree(World world, Random rand, int x, int y, int z, int waterLevel)
    {
        final int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        int width = CANOPY_WIDTH + rand.nextInt(CANOPY_WIDTH_VARIANCE);
        final int chunkCheck = width + 1;
        
        // Make sure that a tree can grow on the soil
        if (!TreeSoilRegistry.isValidSoil(world.getBlock(x, y - 1, z)) || !TreeSoilRegistry.isValidSoil(world.getBlock(x + 1, y - 1, z)) || !TreeSoilRegistry.isValidSoil(world.getBlock(x + 1, y - 1, z + 1)))
            return false;
        
        // make sure that we have room to grow the tree
        if (y >= 256 - height - 4)
            return false;
        
        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256)
            return false;
        
        // Make sure the cunks are loaded
        if (!world.checkChunksExist(x - chunkCheck, y - chunkCheck, z - chunkCheck, x + chunkCheck, y + chunkCheck, z + chunkCheck))
            return false;
        
        // Draw the main trunk
        if (!check2x2Trunk(x, y, z, (int) (height * TRUNK_HEIGHT_PERCENT) + waterLevel, TreeBlock.TRUNK.get(), world, true))
            return false;
        
        // Generate the branches
        if (!checkBranches(world, rand, x, y, z, height, width, waterLevel))
            return false;
        
        // Place the topper leaves
        if (!checkLeafCluster(world, x, (int) (height * TRUNK_HEIGHT_PERCENT) + y, z, 4 + rand.nextInt(CLUSTER_HEIGHT_VARIANCE), 4 + rand.nextInt(CLUSTER_DIAMATER_VARIANCE)))
            return false;
        
        return true;
    }
    
    private boolean generateTree(World world, Random rand, int x, int y, int z, int waterLevel)
    {
        final int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        int width = CANOPY_WIDTH + rand.nextInt(CANOPY_WIDTH_VARIANCE);
        final int chunkCheck = width + 1;
        
        // Make sure that a tree can grow on the soil
        if (!TreeSoilRegistry.isValidSoil(world.getBlock(x, y - 1, z)) || !TreeSoilRegistry.isValidSoil(world.getBlock(x + 1, y - 1, z)) || !TreeSoilRegistry.isValidSoil(world.getBlock(x, y - 1, z + 1)) || !TreeSoilRegistry.isValidSoil(world.getBlock(x + 1, y - 1, z + 1)))
            return false;
        
        // make sure that we have room to grow the tree
        if (y >= 256 - height - 4)
            return false;
        
        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256)
            return false;
        
        // Make sure the cunks are loaded
        if (!world.checkChunksExist(x - chunkCheck, y - chunkCheck, z - chunkCheck, x + chunkCheck, y + chunkCheck, z + chunkCheck))
            return false;
        
        // Draw the main trunk
        if (place2x2Trunk(x, y, z, (int) (height * TRUNK_HEIGHT_PERCENT) + waterLevel, TreeBlock.TRUNK.get(), world))
        {
            // Draw the knees
            generateKnees(world, rand, x, y, z, waterLevel);
            
            // Generate the branches
            generateBranches(world, rand, x, y, z, height, width, waterLevel);
            
            // Place the topper leaves
            generateLeafCluster(world, x, (int) (height * TRUNK_HEIGHT_PERCENT) + y, z, 4 + rand.nextInt(CLUSTER_HEIGHT_VARIANCE), 4 + rand.nextInt(CLUSTER_DIAMATER_VARIANCE), TreeBlock.LEAVES.get());
            
            // We generated a tree
            return true;
        }
        
        return false;
    }
    
    public boolean checkBranches(World world, Random rand, int x, int y, int z, int height, int width, int startOffset)
    {
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        
        // Make sure that the width is even
        width = (width % 2 == 1) ? width + 1 : width;
        
        // Cache the offset
        int offset = width / 2;
        
        // The max distance for branches to generate
        int branchStart = (int) (height * TRUNK_BRANCHES_START) + startOffset;
        int maxBranchHeight = height - ((int) (height * TRUNK_BRANCHES_START)) - 3;
        int[] start = { 0, 0, 0 };
        int[] end = { 0, 0, 0 };
        Queue<int[]> branches = new LinkedList<int[]>();
        
        // Generate some test branches
        for (int branch = 0; branch < branchCount; branch++)
        {
            // The end position
            end[0] = rand.nextInt(width + 1) - offset + x;
            end[1] = rand.nextInt(maxBranchHeight) + branchStart + y;
            end[2] = rand.nextInt(width + 1) - offset + z;
            
            // Max of tree height
            // Min of branch start
            start[1] = Math.max(branchStart + y, Math.min(height, rand.nextInt(Math.max(end[1] - branchStart - y, 1)) + y));
            
            if (end[0] > x && end[2] > z)
            {
                start[0] = x + 1;
                start[2] = z + 1;
            }
            else if (end[0] > x)
            {
                start[0] = x + 1;
                start[2] = z;
            }
            else if (end[2] > z)
            {
                start[0] = x;
                start[2] = z + 1;
            }
            else
            {
                start[0] = x;
                start[2] = z;
            }
            
            // Place the branch
            if (!checkBlockLine(start, end, TreeBlock.KNEE_LOG.get(), world))
                return false;
            
            int[] node = new int[] { end[0], end[1], end[2] };
            
            // Add the branch end for leaf generation
            branches.add(node);
        }
        
        // Generate the leaf clusters
        Iterator<int[]> itt = branches.iterator();
        while (itt.hasNext())
        {
            int[] cluster = itt.next();
            if (!checkLeafCluster(world, cluster[0], cluster[1], cluster[2], CLUSTER_HEIGHT + rand.nextInt(CLUSTER_HEIGHT_VARIANCE), CLUSTER_DIAMATER + rand.nextInt(CLUSTER_DIAMATER_VARIANCE)))
                return false;
        }
        
        return true;
    }
    
    public void generateBranches(World world, Random rand, int x, int y, int z, int height, int width, int startOffset)
    {
        int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
        
        // Make sure that the width is even
        width = (width % 2 == 1) ? width + 1 : width;
        
        // Cache the offset
        int offset = width / 2;
        
        // The max distance for branches to generate
        int branchStart = (int) (height * TRUNK_BRANCHES_START) + startOffset;
        int maxBranchHeight = height - ((int) (height * TRUNK_BRANCHES_START)) - 3;
        int[] start = { 0, 0, 0 };
        int[] end = { 0, 0, 0 };
        Queue<int[]> branches = new LinkedList<int[]>();
        
        //generate the corner markers
        //setBlockAndNotifyAdequately(world, x-width, y + 10, z-width, TreeBlock.TRUNK.getID(), 0);
        //setBlockAndNotifyAdequately(world, x-width, y + 10, z+width, TreeBlock.TRUNK.getID(), 0);
        //setBlockAndNotifyAdequately(world, x+width, y + 10, z-width, TreeBlock.TRUNK.getID(), 0);
        //setBlockAndNotifyAdequately(world, x+width, y + 10, z+width, TreeBlock.TRUNK.getID(), 0);
        
        // Generate some test branches
        for (int branch = 0; branch < branchCount; branch++)
        {
            // The end position
            end[0] = rand.nextInt(width + 1) - offset + x;
            end[1] = rand.nextInt(maxBranchHeight) + branchStart + y;
            end[2] = rand.nextInt(width + 1) - offset + z;
            
            // Max of tree height
            // Min of branch start
            start[1] = Math.max(branchStart + y, Math.min(height, rand.nextInt(Math.max(end[1] - branchStart - y, 1)) + y));
            
            if (end[0] > x && end[2] > z)
            {
                start[0] = x + 1;
                start[2] = z + 1;
            }
            else if (end[0] > x)
            {
                start[0] = x + 1;
                start[2] = z;
            }
            else if (end[2] > z)
            {
                start[0] = x;
                start[2] = z + 1;
            }
            else
            {
                start[0] = x;
                start[2] = z;
            }
            
            // Place the branch
            placeBlockLine(start, end, TreeBlock.KNEE_LOG.get(), world);
            
            int[] node = new int[] { end[0], end[1], end[2] };
            
            // Add the branch end for leaf generation
            branches.add(node);
        }
        
        // Generate the leaf clusters
        Iterator<int[]> itt = branches.iterator();
        while (itt.hasNext())
        {
            int[] cluster = itt.next();
            generateLeafCluster(world, cluster[0], cluster[1], cluster[2], CLUSTER_HEIGHT + rand.nextInt(CLUSTER_HEIGHT_VARIANCE), CLUSTER_DIAMATER + rand.nextInt(CLUSTER_DIAMATER_VARIANCE), TreeBlock.LEAVES.get());
        }
    }
    
    public void generateKnees(World world, Random rand, int x, int y, int z, int bonusHeight)
    {
        switch (rand.nextInt(11))
        {
            case 0:
            case 1:
            case 2:
            case 3:
                placeKnee(x - 1, y, z, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 2, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placeKnee(x - 1, y, z + 1, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 2, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 8:
                placeKnee(x - 1, y, z, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, 2, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                placeKnee(x - 1, y, z + 1, ((rand.nextInt(2) != 0) ? 1 : 2) + bonusHeight, 2, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            default:
                break;
        }
        
        switch (rand.nextInt(11))
        {
            case 0:
            case 1:
            case 2:
            case 3:
                placeKnee(x, y, z - 1, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 3, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placeKnee(x + 1, y, z - 1, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 3, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 8:
                placeKnee(x, y, z - 1, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 3, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                placeKnee(x + 1, y, z - 1, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, 3, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            default:
                break;
        }
        
        switch (rand.nextInt(11))
        {
            case 0:
            case 1:
            case 2:
            case 3:
                placeKnee(x + 2, y, z, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 0, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placeKnee(x + 2, y, z + 1, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 0, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 8:
                placeKnee(x + 2, y, z, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 0, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                placeKnee(x + 2, y, z + 1, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 0, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            default:
                break;
        }
        
        switch (rand.nextInt(11))
        {
            case 0:
            case 1:
            case 2:
            case 3:
                placeKnee(x, y, z + 2, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 1, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                placeKnee(x + 1, y, z + 2, ((rand.nextInt(3) != 0) ? 1 : 2) + bonusHeight, 1, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            case 8:
                placeKnee(x, y, z + 2, ((rand.nextInt(2) != 0) ? 1 : 2) + bonusHeight, 1, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                placeKnee(x + 1, y, z + 2, ((rand.nextInt(5) != 0) ? 1 : 2) + bonusHeight, 1, TreeBlock.KNEE_LOG.get(), TreeBlock.KNEE.get(), world);
                break;
            default:
                break;
        }
    }
    
    public static long getLastSeed()
    {
        return lastSeed;
    }
    
}
