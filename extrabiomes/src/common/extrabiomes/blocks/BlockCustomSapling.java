
package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;

public class BlockCustomSapling extends BlockFlower implements
		IBonemealHandler, ITextureProvider
{

	private static final int	METADATA_BITMASK	= 0x7;
	private static final int	METADATA_MARKBIT	= 0x8;

	public static final int		metaBrown			= 0;
	public static final int		metaOrange			= 1;
	public static final int		metaPurple			= 2;
	public static final int		metaYellow			= 3;
	public static final int		metaFir				= 4;
	public static final int		metaRedWood			= 5;
	public static final int		metaAcacia			= 6;

	public static int			forestryHumusID		= 0;
	public static int			forestryHumusMeta	= 0;

	static private boolean isEnoughLightToGrow(World world, int x,
			int y, int z)
	{
		return world.getBlockLightValue(x, y, z) >= 9;
	}

	static private boolean isMarkedMetadata(int metadata) {
		return (metadata & METADATA_MARKBIT) != 0;
	}

	static private int markedMetadata(int metadata) {
		return metadata | METADATA_MARKBIT;
	}

	static private int unmarkedMetadata(int metadata) {
		return metadata & METADATA_BITMASK;
	}

	public BlockCustomSapling(int id) {
		super(id, 16);
		final float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3,
				var3 * 2.0F, 0.5F + var3);
		setHardness(0F);
		setStepSound(soundGrassFootstep);
		setRequiresSelfNotify();

		MinecraftForge.registerBonemealHandler(this);
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, metaBrown));
		itemList.add(new ItemStack(this, 1, metaOrange));
		itemList.add(new ItemStack(this, 1, metaPurple));
		itemList.add(new ItemStack(this, 1, metaYellow));
		itemList.add(new ItemStack(this, 1, metaFir));
		itemList.add(new ItemStack(this, 1, metaRedWood));
		itemList.add(new ItemStack(this, 1, metaAcacia));
	}

	private void attemptGrowTree(World world, int x, int y, int z,
			Random rand)
	{
		if (isEnoughLightToGrow(world, x, y + 1, z)
				&& rand.nextInt(7) == 0)
		{
			final int metadata = world.getBlockMetadata(x, y, z);

			if (!isMarkedMetadata(metadata))
				world.setBlockMetadataWithNotify(x, y, z,
						markedMetadata(metadata));
			else
				growTree(world, x, y, z, rand);
		}
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(int id) {
		return TerrainGenManager.treesCanGrowOnIDs.contains(Integer
				.valueOf(id));
	}

	@Override
	protected int damageDropped(int metadata) {
		return unmarkedMetadata(metadata);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return super.getBlockTextureFromSide(side)
				+ unmarkedMetadata(metadata);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	public void growTree(World world, int x, int y, int z, Random rand)
	{
		final int metadata = unmarkedMetadata(world.getBlockMetadata(x,
				y, z));
		WorldGenerator tree = null;
		int x1 = 0;
		int z1 = 0;
		boolean isHuge = false;

		final int soilId = world.getBlockId(x, y - 1, z);
		final int soilMeta = world.getBlockMetadata(x, y - 1, z);

		final boolean restrictGrowth = soilId == forestryHumusID
				&& soilMeta == forestryHumusMeta;

		if (metadata == metaBrown) {
			if (!restrictGrowth && rand.nextInt(20) == 0)
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.BROWN_AUTUMN_BIG);
			else
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.BROWN_AUTUMN);
		}

		else if (metadata == metaOrange) {
			if (!restrictGrowth && rand.nextInt(20) == 0)
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.ORANGE_AUTUMN_BIG);
			else
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.ORANGE_AUTUMN);
		}

		else if (metadata == metaPurple) {
			if (!restrictGrowth && rand.nextInt(20) == 0)
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.PURPLE_AUTUMN_BIG);
			else
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.PURPLE_AUTUMN);
		}

		else if (metadata == metaYellow) {
			if (!restrictGrowth && rand.nextInt(20) == 0)
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.YELLOW_AUTUMN_BIG);
			else
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.YELLOW_AUTUMN);
		} else if (metadata == metaAcacia)
			tree = TerrainGenManager.treeFactory.makeTreeGenerator(
					true, ITreeFactory.TreeType.ACACIA);
		else {
			// Check for 2x2 firs and redwoods
			for (x1 = 0; x1 >= -1; --x1) {
				for (z1 = 0; z1 >= -1; --z1)
					if (isSameSapling(world, x + x1, y, z + z1,
							metadata)
							&& isSameSapling(world, x + x1 + 1, y, z
									+ z1, metadata)
							&& isSameSapling(world, x + x1, y, z + z1
									+ 1, metadata)
							&& isSameSapling(world, x + x1 + 1, y, z
									+ z1 + 1, metadata))
					{
						if (metadata == metaFir)
							tree = TerrainGenManager.treeFactory
									.makeTreeGenerator(
											true,
											ITreeFactory.TreeType.FIR_HUGE);
						else
							tree = TerrainGenManager.treeFactory
									.makeTreeGenerator(
											true,
											ITreeFactory.TreeType.REDWOOD);
						isHuge = true;
						break;
					}
				if (tree != null) break;
			}
			if (tree == null && metadata == metaFir) {
				// Single fir sapling generates 1x1 tree
				z1 = 0;
				x1 = 0;
				tree = TerrainGenManager.treeFactory.makeTreeGenerator(
						true, ITreeFactory.TreeType.FIR);
			}
		}

		if (tree != null) {
			if (isHuge) {
				world.setBlock(x + x1, y, z + z1, 0);
				world.setBlock(x + x1 + 1, y, z + z1, 0);
				world.setBlock(x + x1, y, z + z1 + 1, 0);
				world.setBlock(x + x1 + 1, y, z + z1 + 1, 0);
			} else
				world.setBlock(x, y, z, 0);

			final int offset = isHuge ? 1 : 0;

			if (!tree.generate(world, rand, x + x1 + offset, y, z + z1
					+ offset))
				if (isHuge) {
					world.setBlockAndMetadata(x + x1, y, z + z1,
							blockID, metadata);
					world.setBlockAndMetadata(x + x1 + 1, y, z + z1,
							blockID, metadata);
					world.setBlockAndMetadata(x + x1, y, z + z1 + 1,
							blockID, metadata);
					world.setBlockAndMetadata(x + x1 + 1, y,
							z + z1 + 1, blockID, metadata);
				} else
					world.setBlockAndMetadata(x, y, z, blockID,
							metadata);
		}
	}

	public boolean isSameSapling(World world, int x, int y, int z,
			int metadata)
	{
		return world.getBlockId(x, y, z) == blockID
				&& unmarkedMetadata(world.getBlockMetadata(x, y, z)) == metadata;
	}

	@Override
	public boolean onUseBonemeal(World world, int blockID, int x,
			int y, int z)
	{
		if (blockID == this.blockID) {
			if (!world.isRemote) growTree(world, x, y, z, world.rand);
			return true;
		}
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);
			attemptGrowTree(world, x, y, z, rand);
		}
	}
}
