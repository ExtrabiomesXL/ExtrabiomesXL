package extrabiomes;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

/**
 * A convenience class that aids manipulation of blockId/metadata pairs
 * 
 * @author ScottKillen
 */
public class MetaBlock implements extrabiomes.api.MetaBlock {
	private final int blockId;
	private final int metadata;

	/**
	 * Class constructor used when blockId and metadata are known.
	 * 
	 * @param blockId
	 *            the blockId to be recorded
	 * @param metadata
	 *            the metadata to be recorded
	 */
	public MetaBlock(int blockId, int metadata) {
		super();
		this.blockId = blockId;
		this.metadata = metadata;
	}

	/**
	 * Class constructor used to collect data from a block existing in the game
	 * world.
	 * 
	 * @param world
	 *            The {@link World} in which to examine the block.
	 * @param x
	 *            the x-coordinate of the block.
	 * @param y
	 *            the y-coordinate of the block.
	 * @param z
	 *            the z-coordinate of the block.
	 */
	public MetaBlock(World world, int x, int y, int z) {
		this(world.getBlockId(x, y, z), world.getBlockMetadata(x, y, z));
	}

	/**
	 * Returns the block that is identified by the stored blockId.
	 * 
	 * @return the {@link Block} that is identified by the stored blockId.
	 */
	public Block block() {
		return Block.blocksList[blockId];
	}

	/* (non-Javadoc)
	 * @see extrabiomes.api.IMetaBlock#blockId()
	 */
	@Override
	public int blockId() {
		return blockId;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof MetaBlock))
			return false;
		MetaBlock mb = (MetaBlock) o;
		return mb.blockId == blockId && mb.metadata == metadata;
	}

	/**
	 * Returns the properly constructed {@link ItemStack} for the block that is
	 * identified by the stored blockId.
	 * 
	 * @return the properly constructed <code>ItemStack</code> for the block
	 *         that is identified by the stored blockId.
	 */
	public ItemStack item() {
		return new ItemStack(blockId, 1, metadata);
	}

	/* (non-Javadoc)
	 * @see extrabiomes.api.IMetaBlock#metadata()
	 */
	@Override
	public int metadata() {
		return metadata;
	}
}
