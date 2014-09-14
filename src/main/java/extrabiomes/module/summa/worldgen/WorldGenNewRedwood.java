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

public class WorldGenNewRedwood extends WorldGenAbstractTree
{

    private enum TreeBlock
    {
        LEAVES(new ItemStack(Blocks.leaves)), TRUNK(new ItemStack(Blocks.log, 1, 1)), BRANCH(new ItemStack(Blocks.log, 1, 1));

        private ItemStack      stack;

        private static boolean loadedCustomBlocks = false;

        private static void loadCustomBlocks()
        {
            if (Element.LEAVES_REDWOOD.isPresent())
            {
                LEAVES.stack = Element.LEAVES_REDWOOD.get();
            }

            if(Element.LOG_REDWOOD.isPresent()){
            	BRANCH.stack = Element.LOG_REDWOOD.get();
            }

            if(Element.LOG_QUARTER_REDWOOD.isPresent()){
            	useQuarter = true;
                TRUNK.stack = Element.LOG_QUARTER_REDWOOD.get();
            }            

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

    public WorldGenNewRedwood(boolean doNotify)
    {
        super(doNotify);
    }

    // Store the last seed that was used to generate a tree
    private static long lastSeed = 0;
    private static boolean useQuarter = false;

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
        final int height = rand.nextInt(23) + 26;
        byte b0 = 1;
        int flag1 = 1;
        int chunkCheck =  13;

        if (y < 1 || y + height + 5 > 256)
            return false;

        if (!TreeSoilRegistry.isValidSoil(world.getBlock(x, y - 1, z)) || y >= 256 - height - 1)
            return false;
        
        if (!world.checkChunksExist(x - chunkCheck, y - chunkCheck, z - chunkCheck, x + chunkCheck, y + chunkCheck, z + chunkCheck))
            return false;

        // Draw the main trunk
        if (!check2x2Trunk(x, y, z, height + 1, world, false)) {
          return false;
        }

        /*
        if (rand.nextInt(4) == 0)
        {
            b0 = 1;

            if (rand.nextInt(3) == 0)
            {
                b0 = 2;
            }
        }
        else
        {
            flag1 = 1;
        }
        
        //x-=1;
        //z-=1;
       

        //for (int j1 = -b0; j1 <= b0; j1++)
        //{
        //    for (int j2 = -b0; j2 <= b0; j2++)
        //    {
        //        if(world.getBlock(x + j1, y - 1, z + j2) == 0) {
        //            return false;
        //        }
        //    }
        //}

        
        int b1 = b0;
        if (flag1 == 0)
        {
            for (int j1 = -b0; j1 <= b0; j1++)
            {
                for (int j2 = -b0; j2 <= b0; j2++)
                {
                    if (j1 == b0)
                    {
                        setBlockAndNotifyAdequately(world, x + j1, y, z + j2, 0, 0);
                    }

                    int blockID;
                    for (int k3 = 0; k3 <= height; k3++)
                    {
                        blockID = world.getBlock(x + j1, y + k3, z + j2);
                        if(Block.blocksList[blockID] == null || Block.blocksList[blockID].isLeaves(world, x + j1, y + k3, z + j2))
                        {
                            setBlockAndNotifyAdequately(world, x + j1, y + k3, z + j2, TreeBlock.BRANCH.getBlock(), TreeBlock.BRANCH.getMetadata());
                        }
                    }

                    if ((b0 != Math.abs(j1)) || (b0 != Math.abs(j2)) || (b0 == 1))
                        continue;

                    for (int j3 = height; j3 >= 0; j3--)
                    {
                        setBlockAndNotifyAdequately(world, x + j1, y + j3, z + j2, 0, 0);

                        blockID = world.getBlock(x + j1, y + j3, z + j2);
                        if (!(j3 < height / 2) && (Block.blocksList[blockID] == null || Block.blocksList[blockID].canBeReplacedByLeaves(world, x + j1, y + j3, z + j2)))
                        {
                            //setBlockAndNotifyAdequately(world, x + j1, y + j3, z + j2, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                        }


                    }

                    if(world.getBlock(x + j1, y, z + j2) != 0) {
                        setBlockAndNotifyAdequately(world, x + j1, y - 1, z + j2, Block.dirt, 0);
                    } else {
                        setBlockAndNotifyAdequately(world, x + j1, y - 1, z + j2, Block.grass, 0);
                    }
                }
            }

            b0++;

            for (int k1 = -b0; k1 <= b0; k1++)
            {
                for (int k2 = -b0; k2 <= b0; k2++)
                {
                    if ((b0 != Math.abs(k1)) && (b0 != Math.abs(k2)))
                    {
                        continue;
                    }

                    for (int j4 = height / 2; j4 <= height; j4++)
                    {
                        if ((rand.nextInt(1) == 0) && (world.getBlock(x + k1, y + j4, z + k2) == 0)) {
                            //setBlockAndNotifyAdequately(world, x + k1, y + j4, z + k2, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                        }
                    }
                }
            }

            for (int j3 = height / 2; j3 <= height - 3; j3++)
            {
                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x - b1, y + j3, z, -1, 0);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x + b1, y + j3, z, 1, 0);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x, y + j3, z - b1, 0, -1);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x, y + j3, z + b1, 0, 1);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x - b1, y + j3, z + b1, -1, 1);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x - b1, y + j3, z - b1, -1, -1);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x + b1, y + j3, z + b1, 1, 1);
                }

                if (rand.nextInt(2) == 0)
                {
                    generateBranch(world, rand, x + b1, y + j3, z - b1, 1, -1);
                }
            }
            return true;
        }*/

        if(TreeBlock.TRUNK.getBlock() != null && !useQuarter) {
	        for (int j1 = 0; j1 <= 1; j1++)
	        {
	            for (int j2 = 0; j2 <= 1; j2++)
	            {
	                if (j1 == 1)
	                {
	                    setBlockAndNotifyAdequately(world, x + j1, y, z + j2, Blocks.air, 0);
	                }
	
	                setBlockAndNotifyAdequately(world, x + j1, y - 1, z + j2, Blocks.dirt, 0);
	
	                Block block = world.getBlock(x + j1, y, z + j2);
	                if(block == null || block.isAir(world, x + j1, y, z + j2) || block.isLeaves(world, x + j1, y, z + j2))
	                {
	                    setBlockAndNotifyAdequately(world, x + j1, y, z + j2, TreeBlock.BRANCH.getBlock(), TreeBlock.BRANCH.getMetadata());
	                }
	
	                for (int k3 = 1; k3 <= height - 4; k3++)
	                {
	                    block = world.getBlock(x + j1, y + k3, z + j2);
	                    if(block == null || block.isAir(world, x + j1, y + k3, z + j2) || block.isLeaves(world, x + j1, y + k3, z + j2))
	                    {
	                        setBlockAndNotifyAdequately(world, x + j1, y + k3, z + j2, TreeBlock.BRANCH.getBlock(), TreeBlock.BRANCH.getMetadata());
	                    }
	                }
	            }
	        }
        } else {
        	for (int offset = 0; offset <= height - 4; offset++) {
        		setBlockAndNotifyAdequately(world, x, y + offset, z, TreeBlock.TRUNK.getBlock(), 0);
        		setBlockAndNotifyAdequately(world, x, y + offset, z + 1, TreeBlock.TRUNK.getBlock(), 3);
        		setBlockAndNotifyAdequately(world, x + 1, y + offset, z, TreeBlock.TRUNK.getBlock(), 1);
        		setBlockAndNotifyAdequately(world, x + 1, y + offset, z + 1, TreeBlock.TRUNK.getBlock(), 2);
        	}
        }

        for (int j3 = height / 2; j3 <= height - 6; j3++)
        {
            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x, y + j3, z+1, -1, 0);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x+1, y + j3, z+1, 1, 0);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x+1, y + j3, z, 0, -1);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x+1, y + j3, z+1, 0, 1);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x, y + j3, z, -1, 1);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x, y + j3, z, -1, -1);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x, y + j3, z+1, 1, 1);
            }

            if (rand.nextInt(4) == 0)
            {
                generateBranch(world, rand, x, y + j3, z, 1, -1);
            }
        }

        return true;
    }

    public void generateBranch(World world, Random random, int x, int y, int z, int xD, int zD)
    {
        for (int br = 0; br < 9; br++)
        {
            if ((xD == -1) && (random.nextInt(3) == 0))
            {
                x--;
            }

            if ((xD == 1) && (random.nextInt(3) == 0))
            {
                x++;
            }

            if ((zD == -1) && (random.nextInt(3) == 0))
            {
                z--;
            }

            if ((zD == 1) && (random.nextInt(3) == 0))
            {
                z++;
            }

            final Block block = world.getBlock(x, y, z);
            if (block == null || block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z))
            {
                setBlockAndNotifyAdequately(world, x, y, z, TreeBlock.BRANCH.getBlock(), TreeBlock.BRANCH.getMetadata());
            }

            if ((br == 8) || (random.nextInt(6) == 0))
            {
                generateLeaves(world, x, y, z);
            }

            y++;
        }
    }

    public void generateLeaves(World world, int x, int y, int z) {
        for (int i = -3; i <= 3; i++)
        {
            for (int j = -3; j <= 3; j++)
            {
                if(!world.getChunkProvider().chunkExists((x + i) >> 4, (z + j) >> 4)) {
                    continue;
                }

                Block block;
                if (((Math.abs(i) != 3) || (Math.abs(j) != 3)) && ((Math.abs(i) != 2) || (Math.abs(j) != 3)) && ((Math.abs(i) != 3) || (Math.abs(j) != 2)))
                {
                    block = world.getBlock(x + i, y, z + j);
                    if(block == null || block.canBeReplacedByLeaves(world, x + i, y, z + j))
                    {
                        setBlockAndNotifyAdequately(world, x + i, y, z + j, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                    }
                }

                if ((Math.abs(i) >= 3) || (Math.abs(j) >= 3) || ((Math.abs(i) == 2) && (Math.abs(j) == 2)))
                    continue;

                block = world.getBlock(x + i, y + 1, z + j);
                if(block == null || block.canBeReplacedByLeaves(world, x + i, y + 1, z + j))
                {
                    setBlockAndNotifyAdequately(world, x + i, y + 1, z + j, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                }

                block = world.getBlock(x + i, y - 1, z + j);
                if(block == null || block.canBeReplacedByLeaves(world, x + i, y - 1, z + j))
                {
                    setBlockAndNotifyAdequately(world, x + i, y - 1, z + j, TreeBlock.LEAVES.getBlock(), TreeBlock.LEAVES.getMetadata());
                }
            }
        }
    }

    public static long getLastSeed()
    {
        return lastSeed;
    }
    
    public boolean check2x2Trunk(int x, int y, int z, int height, World world, boolean inWater) {
        if (inWater) {
            for (int y1 = y + 1; y1 < y + height; y1++) {
                Block b00 = world.getBlock(x, y1, z);
                Block b10 = world.getBlock(x + 1, y1, z);
                Block b01 = world.getBlock(x, y1, z + 1);
                Block b11 = world.getBlock(x + 1, y1, z + 1);
                if (b00 != null && !b00.equals(Blocks.water) && !b00.isReplaceable(world, x, y1, z))
                    return false;
                if (b01 != null && !b01.equals(Blocks.water) && !b01.isReplaceable(world, x + 1, y1, z))
                    return false;
                if (b10 != null && !b10.equals(Blocks.water) && !b10.isReplaceable(world, x, y1, z + 1))
                    return false;
                if (b11 != null && !b11.equals(Blocks.water) && !b11.isReplaceable(world, x + 1, y1, z + 1))
                    return false;
            }
        } else {
            for (int y1 = y + 1; y1 < y + height; y1++) {
                if (!world.isAirBlock(x, y1, z))
                    return false;
                if (!world.isAirBlock(x + 1, y1, z))
                    return false;
                if (!world.isAirBlock(x, y1, z + 1))
                    return false;
                if (!world.isAirBlock(x + 1, y1, z + 1))
                    return false;
            }
        }

        return true;
    }
}
