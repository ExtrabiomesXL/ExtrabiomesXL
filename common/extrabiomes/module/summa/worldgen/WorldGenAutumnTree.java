/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class WorldGenAutumnTree extends WorldGenerator {

	public enum AutumnTreeType {
		BROWN, ORANGE, PURPLE, YELLOW;
		
		private ItemStack leaves = new ItemStack(Block.leaves);
	    
	    private static boolean loadedCustomBlocks = false;
	    
	    private static void loadCustomBlocks() {
	        if (Element.LEAVES_AUTUMN_BROWN.isPresent()) BROWN.leaves = Element.LEAVES_AUTUMN_BROWN.get(); 
	        if (Element.LEAVES_AUTUMN_ORANGE.isPresent()) ORANGE.leaves = Element.LEAVES_AUTUMN_ORANGE.get(); 
	        if (Element.LEAVES_AUTUMN_PURPLE.isPresent()) PURPLE.leaves = Element.LEAVES_AUTUMN_PURPLE.get(); 
	        if (Element.LEAVES_AUTUMN_YELLOW.isPresent()) YELLOW.leaves = Element.LEAVES_AUTUMN_YELLOW.get();
	        
	        loadedCustomBlocks = true;
	    }
        
        public int getID() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return leaves.itemID;
        }
        
        public int getMetadata() {
            if (!loadedCustomBlocks) loadCustomBlocks();
            return leaves.getItemDamage();
        }
		
	}

	private static Block		trunkBlock					= Block.wood;
	private static int			trunkMetadata				= 1;
	private static final int	BASE_HEIGHT					= 4;

	private static final int	CANOPY_HEIGHT				= 3;

	private static final int	CANOPY_RADIUS_EXTRA_RADIUS	= 0;

	private static final int	MAX_VARIANCE_HEIGHT			= 2;

	private static boolean isBlockSuitableForGrowing(final World world,
			final int x, final int y, final int z)
	{
		final int id = world.getBlockId(x, y, z);
		return TreeSoilRegistry.isValidSoil(id);
	}

	private static boolean isRoomToGrow(final World world, final int x,
			final int y, final int z, final int height)
	{
		for (int i = y; i <= y + 1 + height; ++i) {

			if (i < 0 || i >= 256) return false;

			int radius = 1;

			if (i == y) radius = 0;

			if (i >= y + 1 + height - 2) radius = 2;

			for (int x1 = x - radius; x1 <= x + radius; ++x1)
				for (int z1 = z - radius; z1 <= z + radius; ++z1) {
					final int id = world.getBlockId(x1, i, z1);

					if (Block.blocksList[id] != null
							&& !Block.blocksList[id].isLeaves(world,
									x1, i, z1)
							&& id != Block.grass.blockID
							&& !Block.blocksList[id].isWood(world, x1,
									i, z1)) return false;
				}
		}
		return true;
	}

	public static void setTrunkBlock(Block block, int metadata) {
		WorldGenAutumnTree.trunkBlock = block;
		WorldGenAutumnTree.trunkMetadata = metadata;
	}

	protected final AutumnTreeType	type;

	public WorldGenAutumnTree(boolean doBlockNotify, AutumnTreeType type)
	{
		super(doBlockNotify);

		this.type = type;
	}
	
	@Override
	public boolean generate(final World world, final Random rand,
			final int x, final int y, final int z)
	{
		final int height = rand.nextInt(MAX_VARIANCE_HEIGHT + 1)
				+ BASE_HEIGHT;

		if (y < 1 || y + height + 1 > 256) return false;

		if (!isBlockSuitableForGrowing(world, x, y - 1, z))
			return false;

		if (!isRoomToGrow(world, x, y, z, height)) return false;

		world.setBlock(x, y - 1, z, Block.dirt.blockID);
		growLeaves(world, rand, x, y, z, height, type.getID(),
				type.getMetadata());
		growTrunk(world, x, y, z, height, trunkBlock.blockID,
				trunkMetadata);

		return true;
	}

	private void growLeaves(final World world, final Random rand,
			final int x, final int y, final int z, final int height,
			int leafID, int leafMeta)
	{
		for (int y1 = y - CANOPY_HEIGHT + height; y1 <= y + height; ++y1)
		{
			final int canopyRow = y1 - (y + height);
			final int radius = CANOPY_RADIUS_EXTRA_RADIUS + 1
					- canopyRow / 2;

			for (int x1 = x - radius; x1 <= x + radius; ++x1) {
				final int xDistanceFromTrunk = x1 - x;

				for (int z1 = z - radius; z1 <= z + radius; ++z1) {
					final int zDistanceFromTrunk = z1 - z;

					final Block block = Block.blocksList[world
							.getBlockId(x1, y1, z1)];

					if ((Math.abs(xDistanceFromTrunk) != radius
							|| Math.abs(zDistanceFromTrunk) != radius || rand
							.nextInt(2) != 0 && canopyRow != 0)
							&& (block == null || block
									.canBeReplacedByLeaves(world, x1,
											y1, z1)))
						setBlockAndMetadata(world, x1, y1, z1, leafID,
								leafMeta);
				}
			}
		}
	}

	private void growTrunk(final World world, final int x, final int y,
			final int z, final int height, int woodID, int woodMeta)
	{
		for (int y1 = 0; y1 < height; ++y1) {
			final int id = world.getBlockId(x, y + y1, z);

			if (Block.blocksList[id] == null
					|| Block.blocksList[id].isLeaves(world, x, y + y1,
							z))
				setBlockAndMetadata(world, x, y + y1, z, woodID,
						woodMeta);
		}
	}
}
