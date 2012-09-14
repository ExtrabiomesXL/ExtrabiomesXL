/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

import static extrabiomes.plugin.trees.SaplingType.ACACIA;
import static extrabiomes.plugin.trees.SaplingType.BROWN;
import static extrabiomes.plugin.trees.SaplingType.FIR;
import static extrabiomes.plugin.trees.SaplingType.ORANGE;
import static extrabiomes.plugin.trees.SaplingType.PURPLE;
import static extrabiomes.plugin.trees.SaplingType.YELLOW;

import java.util.List;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.trees.TreeBlocks;
import extrabiomes.trees.WorldGenAcacia;
import extrabiomes.trees.WorldGenAutumnTree;
import extrabiomes.trees.WorldGenBigAutumnTree;
import extrabiomes.trees.WorldGenFirTree;
import extrabiomes.trees.WorldGenFirTreeHuge;
import extrabiomes.trees.WorldGenRedwood;

public class BlockCustomSapling extends BlockFlower {

	private static final int	METADATA_BITMASK	= 0x7;
	private static final int	METADATA_MARKBIT	= 0x8;

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
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabDeco);
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
		return TreeBlocks.treesCanGrowOnIDs.contains(Integer
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
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
		if (tab == CreativeTabs.tabDeco)
			for (final SaplingType blockType : SaplingType.values())
				itemList.add(new ItemStack(this, 1, blockType
						.metadata()));
	}

	public void growTree(World world, int x, int y, int z, Random rand)
	{
		final int metadata = unmarkedMetadata(world.getBlockMetadata(x,
				y, z));
		WorldGenerator tree = null;
		int x1 = 0;
		int z1 = 0;
		boolean isHuge = false;

		world.getBlockId(x, y - 1, z);
		world.getBlockMetadata(x, y - 1, z);

		if (metadata == BROWN.metadata()) {
			if (rand.nextInt(20) == 0)
				tree = new WorldGenBigAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.BROWN),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.BROWN),
						TreeBlocks.getLeafID(TreeBlocks.Type.BROWN),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.BROWN));
			else
				tree = new WorldGenAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.BROWN),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.BROWN),
						TreeBlocks.getLeafID(TreeBlocks.Type.BROWN),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.BROWN));
		}

		else if (metadata == ORANGE.metadata()) {
			if (rand.nextInt(20) == 0)
				tree = new WorldGenBigAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.ORANGE),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.ORANGE),
						TreeBlocks.getLeafID(TreeBlocks.Type.ORANGE),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.ORANGE));
			else
				tree = new WorldGenAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.ORANGE),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.ORANGE),
						TreeBlocks.getLeafID(TreeBlocks.Type.ORANGE),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.ORANGE));
		}

		else if (metadata == PURPLE.metadata()) {
			if (rand.nextInt(20) == 0)
				tree = new WorldGenBigAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.PURPLE),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.PURPLE),
						TreeBlocks.getLeafID(TreeBlocks.Type.PURPLE),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.PURPLE));
			else
				tree = new WorldGenAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.PURPLE),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.PURPLE),
						TreeBlocks.getLeafID(TreeBlocks.Type.PURPLE),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.PURPLE));
		}

		else if (metadata == YELLOW.metadata()) {
			if (rand.nextInt(20) == 0)
				tree = new WorldGenBigAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.YELLOW),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.YELLOW),
						TreeBlocks.getLeafID(TreeBlocks.Type.YELLOW),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.YELLOW));
			else
				tree = new WorldGenAutumnTree(true,
						TreeBlocks.getWoodID(TreeBlocks.Type.YELLOW),
						TreeBlocks.getWoodMeta(TreeBlocks.Type.YELLOW),
						TreeBlocks.getLeafID(TreeBlocks.Type.YELLOW),
						TreeBlocks.getLeafMeta(TreeBlocks.Type.YELLOW));
		} else if (metadata == ACACIA.metadata())
			tree = new WorldGenAcacia(true);
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
						if (metadata == FIR.metadata())
							tree = new WorldGenFirTreeHuge(true);
						else
							tree = new WorldGenRedwood(true);
						isHuge = true;
						break;
					}
				if (tree != null) break;
			}
			if (tree == null && metadata == FIR.metadata()) {
				// Single fir sapling generates 1x1 tree
				z1 = 0;
				x1 = 0;
				tree = new WorldGenFirTree(true);
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

	@ForgeSubscribe
	void reactToBonemeal(BonemealEvent e) {
		if (!e.isHandeled() && e.ID == blockID) {
			if (!e.world.isRemote)
				growTree(e.world, e.X, e.Y, e.Z, e.world.rand);
			e.setHandeled();
		}
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
