
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.BlockTransparant;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.Block;
import net.minecraft.server.Entity;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.Material;
import net.minecraft.server.World;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;
import forge.IShearable;
import forge.ITextureProvider;

public class BlockAutumnLeaves extends BlockTransparant implements
		IShearable, ITextureProvider
{
	private static final int	METADATA_BITMASK		= 3;
	private static final int	METADATA_USERPLACEDBIT	= 4;
	private static final int	METADATA_DECAYBIT		= 8;
	private static final int	METADATA_CLEARDECAYBIT	= -9;
	public static final int		metaBrown				= 0;
	public static final int		metaOrange				= 1;
	public static final int		metaPurple				= 2;
	public static final int		metaYellow				= 3;

	private static int clearDecayOnMetadata(int i) {
		return i & -9;
	}

	private static boolean isDecaying(int i) {
		return (i & 8) != 0;
	}

	private static boolean isUserPlaced(int i) {
		return (i & 4) != 0;
	}

	private static int setDecayOnMetadata(int i) {
		return i | 8;
	}

	private static int unmarkedMetadata(int i) {
		return i & 3;
	}

	int	adjacentTreeBlocks[];

	public BlockAutumnLeaves(int i) {
		super(i, 3, Material.LEAVES, false);
		a(true);
		c(0.2F);
		f(1);
		a(g);
		j();
		Block.setBurnProperties(i, 30, 60);
		TerrainGenManager.blockBrownAutumnLeaves = TerrainGenManager.blockOrangeAutumnLeaves = TerrainGenManager.blockPurpleAutumnLeaves = TerrainGenManager.blockYellowAutumnLeaves = this;
		TerrainGenManager.metaBrownAutumnLeaves = 0;
		TerrainGenManager.metaOrangeAutumnLeaves = 1;
		TerrainGenManager.metaPurpleAutumnLeaves = 2;
		TerrainGenManager.metaYellowAutumnLeaves = 3;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines
	 * whether or not to render the shared face of two adjacent blocks
	 * and also whether the player can attach torches, redstone wire,
	 * etc to this block.
	 */
	public boolean a() {
		return Block.LEAVES.a();
	}

	/**
	 * From the specified side and block metadata retrieves the blocks
	 * texture. Args: side, metadata
	 */
	public int a(int i, int j) {
		return textureId + 2 * unmarkedMetadata(j) + (a() ? 1 : 0);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	public int a(Random random) {
		return random.nextInt(20) != 0 ? 0 : 1;
	}

	/**
	 * Called when the player destroys a block with an item that can
	 * harvest it. (i, j, k) are the coordinates of the block and l is
	 * the block's subtype/damage.
	 */
	public void a(World world, EntityHuman entityhuman, int i, int j,
			int k, int l)
	{
		super.a(world, entityhuman, i, j, k, l);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void a(World world, int i, int j, int k, Random random) {
		if (world.isStatic) return;

		final int l = world.getData(i, j, k);

		if (isUserPlaced(l) || !isDecaying(l)) return;

		final byte byte0 = 4;
		final byte byte1 = 5;
		final byte byte2 = 32;
		final char c = 1024;
		final byte byte3 = 16;

		if (adjacentTreeBlocks == null)
			adjacentTreeBlocks = new int[32768];

		if (world.a(i - 5, j - 5, k - 5, i + 5, j + 5, k + 5)) {
			for (int i1 = -4; i1 <= 4; i1++)
				for (int k1 = -4; k1 <= 4; k1++)
					for (int i2 = -4; i2 <= 4; i2++) {
						final int k2 = world.getTypeId(i + i1, j + k1,
								k + i2);

						if (Block.byId[k2] != null
								&& Block.byId[k2].isWood(world, i + i1,
										j + k1, k + i2))
						{
							adjacentTreeBlocks[(i1 + 16) * 1024
									+ (k1 + 16) * 32 + i2 + 16] = 0;
							continue;
						}

						if (Block.byId[k2] != null
								&& Block.byId[k2].isLeaves(world, i
										+ i1, j + k1, k + i2))
							adjacentTreeBlocks[(i1 + 16) * 1024
									+ (k1 + 16) * 32 + i2 + 16] = -2;
						else
							adjacentTreeBlocks[(i1 + 16) * 1024
									+ (k1 + 16) * 32 + i2 + 16] = -1;
					}

			for (int j1 = 1; j1 <= 4; j1++)
				for (int l1 = -4; l1 <= 4; l1++)
					for (int j2 = -4; j2 <= 4; j2++)
						for (int l2 = -4; l2 <= 4; l2++) {
							if (adjacentTreeBlocks[(l1 + 16) * 1024
									+ (j2 + 16) * 32 + l2 + 16] != j1 - 1)
								continue;

							if (adjacentTreeBlocks[(l1 + 16 - 1) * 1024
									+ (j2 + 16) * 32 + l2 + 16] == -2)
								adjacentTreeBlocks[(l1 + 16 - 1) * 1024
										+ (j2 + 16) * 32 + l2 + 16] = j1;

							if (adjacentTreeBlocks[(l1 + 16 + 1) * 1024
									+ (j2 + 16) * 32 + l2 + 16] == -2)
								adjacentTreeBlocks[(l1 + 16 + 1) * 1024
										+ (j2 + 16) * 32 + l2 + 16] = j1;

							if (adjacentTreeBlocks[(l1 + 16) * 1024
									+ (j2 + 16 - 1) * 32 + l2 + 16] == -2)
								adjacentTreeBlocks[(l1 + 16) * 1024
										+ (j2 + 16 - 1) * 32 + l2 + 16] = j1;

							if (adjacentTreeBlocks[(l1 + 16) * 1024
									+ (j2 + 16 + 1) * 32 + l2 + 16] == -2)
								adjacentTreeBlocks[(l1 + 16) * 1024
										+ (j2 + 16 + 1) * 32 + l2 + 16] = j1;

							if (adjacentTreeBlocks[(l1 + 16) * 1024
									+ (j2 + 16) * 32 + l2 + 16 - 1] == -2)
								adjacentTreeBlocks[(l1 + 16) * 1024
										+ (j2 + 16) * 32 + l2 + 16 - 1] = j1;

							if (adjacentTreeBlocks[(l1 + 16) * 1024
									+ (j2 + 16) * 32 + l2 + 16 + 1] == -2)
								adjacentTreeBlocks[(l1 + 16) * 1024
										+ (j2 + 16) * 32 + l2 + 16 + 1] = j1;
						}
		}

		if (adjacentTreeBlocks[16912] >= 0)
			world.setRawData(i, j, k, clearDecayOnMetadata(l));
		else
			removeLeaves(world, i, j, k);
	}

	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this, 1, 0));
		arraylist.add(new ItemStack(this, 1, 1));
		arraylist.add(new ItemStack(this, 1, 2));
		arraylist.add(new ItemStack(this, 1, 3));
	}

	/**
	 * Called whenever an entity is walking on top of this block. Args:
	 * world, x, y, z, entity
	 */
	public void b(World world, int i, int j, int k, Entity entity) {
		beginLeavesDecay(world, i, j, k);
	}

	public void beginLeavesDecay(World world, int i, int j, int k) {
		world.setRawData(i, j, k,
				setDecayOnMetadata(world.getData(i, j, k)));
	}

	private void doSaplingDrop(World world, int i, int j, int k, int l,
			int i1)
	{
		a(world, i, j, k, new ItemStack(
				getDropType(l, world.random, i1), 1, getDropData(l)));
	}

	/**
	 * Drops the block items with a specified chance of dropping the
	 * specified items
	 */
	public void dropNaturally(World world, int i, int j, int k, int l,
			float f, int i1)
	{
		if (world.isStatic) return;

		if (world.random.nextInt(20) == 0)
			doSaplingDrop(world, i, j, k, l, i1);

		if (world.random.nextInt(200) == 0)
			a(world, i, j, k, new ItemStack(Item.APPLE, 1, 0));
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth
	 * and wood.
	 */
	protected int getDropData(int i) {
		return unmarkedMetadata(i);
	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int getDropType(int i, Random random, int j) {
		return ExtrabiomesBlock.sapling.id;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	public boolean isLeaves(World world, int i, int j, int k) {
		return true;
	}

	public boolean isShearable(ItemStack itemstack, World world, int i,
			int j, int k)
	{
		return true;
	}

	public ArrayList onSheared(ItemStack itemstack, World world, int i,
			int j, int k, int l)
	{
		final ArrayList arraylist = new ArrayList();
		arraylist.add(new ItemStack(this, 1, unmarkedMetadata(world
				.getData(i, j, k))));
		return arraylist;
	}

	/**
	 * Called whenever the block is removed.
	 */
	public void remove(World world, int i, int j, int k) {
		final boolean flag = true;
		final byte byte0 = 2;

		if (!world.a(i - 2, j - 2, k - 2, i + 2, j + 2, k + 2)) return;

		for (int l = -1; l <= 1; l++)
			for (int i1 = -1; i1 <= 1; i1++)
				for (int j1 = -1; j1 <= 1; j1++) {
					final int k1 = world.getTypeId(i + l, j + i1, k
							+ j1);

					if (Block.byId[k1] != null)
						Block.byId[k1].beginLeavesDecay(world, i + l, j
								+ i1, k + j1);
				}
	}

	private void removeLeaves(World world, int i, int j, int k) {
		b(world, i, j, k, world.getData(i, j, k), 0);
		world.setTypeId(i, j, k, 0);
	}
}
