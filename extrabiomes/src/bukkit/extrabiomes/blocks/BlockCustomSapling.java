
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.server.BlockFlower;
import net.minecraft.server.ItemStack;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import forge.IBonemealHandler;
import forge.ITextureProvider;
import forge.MinecraftForge;

public class BlockCustomSapling extends BlockFlower implements
		IBonemealHandler, ITextureProvider
{
	private static final int	METADATA_BITMASK	= 7;
	private static final int	METADATA_MARKBIT	= 8;
	public static final int		metaBrown			= 0;
	public static final int		metaOrange			= 1;
	public static final int		metaPurple			= 2;
	public static final int		metaYellow			= 3;
	public static final int		metaFir				= 4;
	public static final int		metaRedWood			= 5;
	public static final int		metaAcacia			= 6;

	private static boolean isEnoughLightToGrow(World world, int i,
			int j, int k)
	{
		return world.getLightLevel(i, j, k) >= 9;
	}

	private static boolean isMarkedMetadata(int i) {
		return (i & 8) != 0;
	}

	private static int markedMetadata(int i) {
		return i | 8;
	}

	private static int unmarkedMetadata(int i) {
		return i & 7;
	}

	public BlockCustomSapling(int i) {
		super(i, 16);
		final float f = 0.4F;
		a(0.09999999F, 0.0F, 0.09999999F, 0.9F, 0.8F, 0.9F);
		c(0.0F);
		a(g);
		j();
		MinecraftForge.registerBonemealHandler(this);
	}

	/**
	 * From the specified side and block metadata retrieves the blocks
	 * texture. Args: side, metadata
	 */
	public int a(int i, int j) {
		return super.a(i) + unmarkedMetadata(j);
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	public void a(World world, int i, int j, int k, Random random) {
		if (!world.isStatic) {
			super.a(world, i, j, k, random);
			attemptGrowTree(world, i, j, k, random);
		}
	}

	public void addCreativeItems(ArrayList arraylist) {
		arraylist.add(new ItemStack(this, 1, 0));
		arraylist.add(new ItemStack(this, 1, 1));
		arraylist.add(new ItemStack(this, 1, 2));
		arraylist.add(new ItemStack(this, 1, 3));
		arraylist.add(new ItemStack(this, 1, 4));
		arraylist.add(new ItemStack(this, 1, 5));
		arraylist.add(new ItemStack(this, 1, 6));
	}

	private void attemptGrowTree(World world, int i, int j, int k,
			Random random)
	{
		if (isEnoughLightToGrow(world, i, j + 1, k)
				&& random.nextInt(7) == 0)
		{
			final int l = world.getData(i, j, k);

			if (!isMarkedMetadata(l))
				world.setData(i, j, k, markedMetadata(l));
			else
				growTree(world, i, j, k, random);
		}
	}

	/**
	 * Gets passed in the blockID of the block below and supposed to
	 * return true if its allowed to grow on the type of blockID passed
	 * in. Args: blockID
	 */
	protected boolean d(int i) {
		return TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(i));
	}

	/**
	 * Determines the damage on the item the block drops. Used in cloth
	 * and wood.
	 */
	protected int getDropData(int i) {
		return unmarkedMetadata(i);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	public void growTree(World world, int i, int j, int k, Random random)
	{
		final int l = unmarkedMetadata(world.getData(i, j, k));
		WorldGenerator worldgenerator = null;
		int i1 = 0;
		int j1 = 0;
		boolean flag = false;

		if (l == 0) {
			if (random.nextInt(20) == 0)
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.BROWN_AUTUMN_BIG);
			else
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.BROWN_AUTUMN);
		} else if (l == 1) {
			if (random.nextInt(20) == 0)
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.ORANGE_AUTUMN_BIG);
			else
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.ORANGE_AUTUMN);
		} else if (l == 2) {
			if (random.nextInt(20) == 0)
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.PURPLE_AUTUMN_BIG);
			else
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.PURPLE_AUTUMN);
		} else if (l == 3) {
			if (random.nextInt(20) == 0)
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.YELLOW_AUTUMN_BIG);
			else
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.YELLOW_AUTUMN);
		} else if (l == 6)
			worldgenerator = TerrainGenManager.treeFactory
					.makeTreeGenerator(
							true,
							extrabiomes.api.ITreeFactory.TreeType.ACACIA);
		else {
			i1 = 0;

			do {
				if (i1 < -1) break;

				j1 = 0;

				do {
					if (j1 < -1) break;

					if (isSameSapling(world, i + i1, j, k + j1, l)
							&& isSameSapling(world, i + i1 + 1, j, k
									+ j1, l)
							&& isSameSapling(world, i + i1, j, k + j1
									+ 1, l)
							&& isSameSapling(world, i + i1 + 1, j, k
									+ j1 + 1, l))
					{
						if (l == 4)
							worldgenerator = TerrainGenManager.treeFactory
									.makeTreeGenerator(
											true,
											extrabiomes.api.ITreeFactory.TreeType.FIR_HUGE);
						else
							worldgenerator = TerrainGenManager.treeFactory
									.makeTreeGenerator(
											true,
											extrabiomes.api.ITreeFactory.TreeType.REDWOOD);

						flag = true;
						break;
					}

					j1--;
				} while (true);

				if (worldgenerator != null) break;

				i1--;
			} while (true);

			if (worldgenerator == null && l == 4) {
				j1 = 0;
				i1 = 0;
				worldgenerator = TerrainGenManager.treeFactory
						.makeTreeGenerator(
								true,
								extrabiomes.api.ITreeFactory.TreeType.FIR);
			}
		}

		if (worldgenerator != null) {
			if (flag) {
				world.setRawTypeId(i + i1, j, k + j1, 0);
				world.setRawTypeId(i + i1 + 1, j, k + j1, 0);
				world.setRawTypeId(i + i1, j, k + j1 + 1, 0);
				world.setRawTypeId(i + i1 + 1, j, k + j1 + 1, 0);
			} else
				world.setRawTypeId(i, j, k, 0);

			final int k1 = flag ? 1 : 0;

			if (!worldgenerator.a(world, random, i + i1 + k1, j, k + j1
					+ k1))
				if (flag) {
					world.setRawTypeIdAndData(i + i1, j, k + j1, id, l);
					world.setRawTypeIdAndData(i + i1 + 1, j, k + j1,
							id, l);
					world.setRawTypeIdAndData(i + i1, j, k + j1 + 1,
							id, l);
					world.setRawTypeIdAndData(i + i1 + 1, j,
							k + j1 + 1, id, l);
				} else
					world.setRawTypeIdAndData(i, j, k, id, l);
		}
	}

	public boolean isSameSapling(World world, int i, int j, int k, int l)
	{
		return world.getTypeId(i, j, k) == id
				&& unmarkedMetadata(world.getData(i, j, k)) == l;
	}

	public boolean onUseBonemeal(World world, int i, int j, int k, int l)
	{
		if (i == id) {
			if (!world.isStatic)
				growTree(world, j, k, l, world.random);

			return true;
		} else
			return false;
	}
}
