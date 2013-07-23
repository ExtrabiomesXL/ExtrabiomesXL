package extrabiomes.module.summa.worldgen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenJapaneseMapleShrub extends WorldGenNewTreeBase {
	
	private enum TreeBlock {
        LEAVES(new ItemStack(Block.leaves, 1, 1)), TRUNK(new ItemStack(Block.wood, 1, 1));

        private ItemStack      stack;
        private static boolean loadedCustomBlocks = false;

        private static void loadCustomBlocks() {
            if (Element.LEAVES_JAPANESE_MAPLE_SHRUB.isPresent()) LEAVES.stack = Element.LEAVES_JAPANESE_MAPLE_SHRUB.get();
            if (Element.LOG_JAPANESE_MAPLE.isPresent()) TRUNK.stack = Element.LOG_JAPANESE_MAPLE.get();

            loadedCustomBlocks = true;
        }

        TreeBlock(ItemStack stack) {
            this.stack = stack;
        }
        
        public ItemStack get() {
        	if (!loadedCustomBlocks) loadCustomBlocks();
        	return this.stack;
        }

        public int getID() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return stack.itemID;
        }

        public int getMetadata() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return stack.getItemDamage();
        }

    }

	public WorldGenJapaneseMapleShrub(boolean par1) {
		super(par1);
	}
	
	// Store the last seed that was used to generate a tree
    private static long lastSeed = 0;

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) {
    	// Store the seed
    	lastSeed = rand.nextLong();
    	
        return generateTree(world, new Random(lastSeed), x, y, z);
    }
    
    public boolean generate(World world, long seed, int x, int y, int z) {
    	// Store the seed
    	lastSeed = seed;
    	
        return generateTree(world, new Random(seed), x, y, z);
    }
    
    //Variables to control the generation
    private static final int	BASE_HEIGHT					= 3;		// The base height for trees
	private static final int	BASE_HEIGHT_VARIANCE		= 4;		// The Max extra branches that a tree can have
	private static final double	TRUNK_HEIGHT_PERCENT		= 0.10D;	// What percent of the total height the main trunk extends
	private static final int	BRANCHES_BASE_NUMBER		= 3;		// The total number of branches on the tree
	private static final int	BRANCHES_EXTRA				= 1;		// The how many extra branches can occur on the tree
	private static final int	CANOPY_WIDTH				= 4;		// How many blocks will this tree cover
	private static final int	CANOPY_WIDTH_VARIANCE		= 3;		// How many extra blocks may this tree cover
	
	static int last = 0;
    
    private boolean generateTree(World world, Random rand, int x, int y, int z) {
        final int below = world.getBlockId(x, y - 1, z);
        final int height = rand.nextInt(BASE_HEIGHT_VARIANCE) + BASE_HEIGHT;
        final double radius = (CANOPY_WIDTH + rand.nextInt(CANOPY_WIDTH_VARIANCE)) / 2.0D;
        final int chunkCheck = (int)Math.ceil(radius) + 1;

        // Make sure that a tree can grow on the soil
        if (!TreeSoilRegistry.isValidSoil(Integer.valueOf(world.getBlockId(x, y - 1, z)))) return false;
        
        // make sure that we have room to grow the tree
        if(y >= 256 - height - 4) return false;

        // Make sure that the tree can fit in the world
        if (y < 1 || y + height + 4 > 256) return false;
        
        // Make sure that all the needed chunks are loaded
        if (!world.checkChunksExist(x - chunkCheck, y - chunkCheck, z - chunkCheck, x + chunkCheck, y + chunkCheck, z + chunkCheck)) return false;
        
        // Draw the main trunk
        if(place1x1Trunk(x, y, z, (int)(height * TRUNK_HEIGHT_PERCENT), TreeBlock.TRUNK.get(), world)) {
	        // Generate the branches
	        generateBranches(world, rand, x, y + (int)(height * TRUNK_HEIGHT_PERCENT), z, height - (int)(height * TRUNK_HEIGHT_PERCENT) - 2, radius);
	        	        
	        return true;
        }

        return false;
    }
    
    public void generateBranches(World world, Random rand, int x, int y, int z, int height, double radius) {
    	int branchCount = BRANCHES_BASE_NUMBER + rand.nextInt(BRANCHES_EXTRA);
    	double curAngle = 0.0D;
    	
    	double[] average = { 0, 0, 0 };
    	int[] start = {x, y, z};
    	Queue<int[]> branches = new LinkedList<int[]>();
    	    	
    	// Generate the branches
    	for(int i = 0; i < branchCount; i++) {
    		// Get the branch radius and height
    		double angle = (rand.nextInt(50) + 35) / 90.0D;
    		double thisHeight = ((double)(height + 1)) * Math.sin(angle) / 1.3;
    		double thisRadius = radius * Math.cos(angle);
    		
    		// Get the branch rotation
    		curAngle += (rand.nextInt(360/branchCount) + (360 / branchCount)) / 90.0D;//  + (360.0D/branchCount) / 180.0D ;
    		
    		int x1 = (int)(((double)thisRadius) * Math.cos(curAngle));
    		int z1 = (int)(((double)thisRadius) * Math.sin(curAngle));
    		
    		// Add the the average count
    		average[0] += x1;
    		average[1] += thisHeight;
    		average[2] += z1;
    		
    		// Add to the branch list
    		int[] node = new int[] {x1 + x, (int)thisHeight + y, z1 + z};
    		
    		// Add the branch end for leaf generation
    		branches.add(node);
    		
    		// Generate the branch
    		placeBlockLine(start, node, TreeBlock.TRUNK.get(), world);	
    	}
    	
    	// Place the branch tips
    	Iterator<int[]> itt = branches.iterator();
    	while (itt.hasNext()) {
    	   int[] cluster = itt.next();
    	   generateLeafCluster(world, rand, cluster[0], cluster[1], cluster[2], 1, 1, TreeBlock.LEAVES.get());
    	}

    	// Calculate the center position
    	average[0] /= (double)branchCount;
    	average[1] = (branchCount / average[1]) + 2.3D;
    	average[2] /= branchCount;
    	
    	// Generate the canopy
    	generateCanopy(world, rand, average[0] + x, y, average[2] + z, radius, height, TreeBlock.LEAVES.get());

    }
    
    public void generateCanopy(World world, Random rand, double x, double y, double z, double radius, int height, ItemStack leaves) {
    	int layers = height;
    	for(int y1 = (int)y, layer = 0; layer < layers; layer++, y1 ++) {
    		double radiusOuter = radius * Math.cos((layer) / (height / 1.3));
    		double radiusInner = (radiusOuter - 1 > 0) ? radiusOuter -1: -1; 
    		
    		if(layer + 1 == layers) {
    			generateCanopyLayer(world, rand, x, y1, z, radiusOuter, 0, leaves);
    		} else {
    			generateCanopyLayer(world, rand, x, y1, z, radiusOuter, (radiusOuter - 1), leaves);
    		}
    	}
    }
    
    public void generateCanopyLayer(World world, Random rand, double x, double y, double z, double radiusOuter, double radiusInner, ItemStack leaves) {
    	double minDist = (radiusInner > 0) ? (radiusInner * radiusInner) : radiusInner;
    	double maxDist = radiusOuter * radiusOuter;
    	    	
    	for(int z1 = (int)-radiusOuter; z1 < (radiusOuter + 1); z1++) {
    		for(int x1 = (int)-radiusOuter; x1 < (radiusOuter + 1); x1++) {
    			final Block block = Block.blocksList[world.getBlockId((int)(x1 + x), (int)y, (int)(z1 + z))];
    			
        		if((((x1*x1) + (z1*z1)) <= maxDist) && (((x1*x1) + (z1*z1)) >= minDist)) {
        			if(block == null || block.canBeReplacedByLeaves(world, (int)(x1 + x), (int)y, (int)(z1 + z))) {
        				setLeafBlock(world, (int)(x1 + x), (int)y, (int)(z1 + z), leaves);		
        			}
        		}
        	}
    	}
    }
    
    public static long getLastSeed(){ 
    	return lastSeed;
    }

}
