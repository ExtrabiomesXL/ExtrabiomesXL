/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;

import net.minecraft.src.Block;
import net.minecraft.src.BlockLeavesBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraftforge.common.IShearable;

public class BlockGreenLeaves extends BlockLeavesBase implements
		IShearable
{

	private static final int	METADATA_BITMASK		= 0x3;
	private static final int	METADATA_USERPLACEDBIT	= 0x4;
	private static final int	METADATA_DECAYBIT		= 0x8;
	private static final int	METADATA_CLEARDECAYBIT	= -METADATA_DECAYBIT - 1;

	public static final int		metaFir					= 0;
	public static final int		metaRedwood				= 1;
	public static final int		metaAcacia				= 2;

	private static int calcSmoothedBiomeFoliageColor(
			IBlockAccess iBlockAccess, int x, int z)
	{
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int z1 = -1; z1 <= 1; ++z1)
			for (int x1 = -1; x1 <= 1; ++x1) {
				final int foliageColor = iBlockAccess
						.getBiomeGenForCoords(x + x1, z + z1)
						.getBiomeFoliageColor();
				red += (foliageColor & 16711680) >> 16;
				green += (foliageColor & 65280) >> 8;
				blue += foliageColor & 255;
			}

		return (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue
				/ 9 & 255;
	}

	static private int clearDecayOnMetadata(int metadata) {
		return metadata & METADATA_CLEARDECAYBIT;
	}

	private static boolean isDecaying(int metadata) {
		return (metadata & METADATA_DECAYBIT) != 0;
	}

	private static boolean isUserPlaced(int metadata) {
		return (metadata & METADATA_USERPLACEDBIT) != 0;
	}

	private static int setDecayOnMetadata(int metadata) {
		return metadata | METADATA_DECAYBIT;
	}

	private static int unmarkedMetadata(int metadata) {
		return metadata & METADATA_BITMASK;
	}

	int[]	adjacentTreeBlocks;

	public BlockGreenLeaves(int id) {
		super(id, 80, Material.leaves, false);
		setTickRandomly(true);
		setHardness(0.2F);
		setLightOpacity(1);
		setStepSound(soundGrassFootstep);
		setRequiresSelfNotify();
		Block.setBurnProperties(id, 30, 60);
		setTextureFile("/extrabiomes/extrabiomes.png");

		TerrainGenManager.blockFirLeaves = TerrainGenManager.blockRedwoodLeaves = TerrainGenManager.blockAcaciaLeaves = this;
		TerrainGenManager.metaFirLeaves = metaFir;
		TerrainGenManager.metaRedwoodLeaves = metaRedwood;
		TerrainGenManager.metaAcaciaLeaves = metaAcacia;
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, metaFir));
		itemList.add(new ItemStack(this, 1, metaRedwood));
		itemList.add(new ItemStack(this, 1, metaAcacia));
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		world.setBlockMetadata(x, y, z,
				setDecayOnMetadata(world.getBlockMetadata(x, y, z)));
	}

	@Override
	public void breakBlock(World world, int x, int y, int z,
			int BlockID, int metadata)
	{
		final int leafDecayRadius = 1;

		final int chuckCheckRadius = leafDecayRadius + 1;
		if (!world.checkChunksExist(x - chuckCheckRadius, y
				- chuckCheckRadius, z - chuckCheckRadius, x
				+ chuckCheckRadius, y + chuckCheckRadius, z
				+ chuckCheckRadius)) return;

		for (int x1 = -leafDecayRadius; x1 <= leafDecayRadius; ++x1)
			for (int y1 = -leafDecayRadius; y1 <= leafDecayRadius; ++y1)
				for (int z1 = -leafDecayRadius; z1 <= leafDecayRadius; ++z1)
				{
					final int id = world.getBlockId(x + x1, y + y1, z
							+ z1);

					if (Block.blocksList[id] != null)
						Block.blocksList[id].beginLeavesDecay(world, x
								+ x1, y + y1, z + z1);
				}
	}

	@Override
	public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y,
			int z)
	{
		final int metadata = unmarkedMetadata(iBlockAccess
				.getBlockMetadata(x, y, z));

		if (metadata != metaRedwood) return getRenderColor(metadata);

		return calcSmoothedBiomeFoliageColor(iBlockAccess, x, z);
	}

	@Override
	protected int damageDropped(int metadata) {

		return unmarkedMetadata(metadata) + 4;
	}

	private void doSaplingDrop(World world, int x, int y, int z,
			int metadata, int par7)
	{
		final int idDropped = idDropped(metadata, world.rand, par7);
		final int damageDropped = damageDropped(metadata);
		dropBlockAsItem_do(world, x, y, z, new ItemStack(idDropped, 1,
				damageDropped));
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y,
			int z, int metadata, float chance, int par7)
	{
		if (world.isRemote) return;

		if (world.rand.nextInt(20) == 0)
			doSaplingDrop(world, x, y, z, metadata, par7);
	}

	@Override
	public int getBlockColor() {
		return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return blockIndexInTexture + unmarkedMetadata(metadata) * 2
				+ (!isOpaqueCube() ? 0 : 1);
	}

	@Override
	public int getRenderColor(int metadata) {
		metadata = unmarkedMetadata(metadata);

		return metadata == 0 ? ColorizerFoliage.getFoliageColorPine()
				: metadata == 1 ? ColorizerFoliage
						.getFoliageColorBasic() : ColorizerFoliage
						.getFoliageColor(0.9F, 0.1F);
	}

	@Override
	public void harvestBlock(World world, final EntityPlayer player,
			final int x, final int y, final int z, final int md)
	{
		super.harvestBlock(world, player, x, y, z, md);
	}

	@Override
	public int idDropped(int metadata, Random rand, int par3) {
		return ExtrabiomesBlock.sapling.get().blockID;
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return Block.leaves.isOpaqueCube();
	}

	@Override
	public boolean isShearable(ItemStack item, World world, int x,
			int y, int z)
	{
		return true;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z,
			Entity entity)
	{
		beginLeavesDecay(world, x, y, z);
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, World world,
			int x, int y, int z, int fortune)
	{
		final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, unmarkedMetadata(world
				.getBlockMetadata(x, y, z))));
		return ret;
	}

	@Override
	public int quantityDropped(Random rand) {
		return rand.nextInt(20) == 0 ? 1 : 0;
	}

	private void removeLeaves(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z,
				world.getBlockMetadata(x, y, z), 0);
		world.setBlockWithNotify(x, y, z, 0);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5)
	{
		graphicsLevel = !Block.leaves.isOpaqueCube(); // fix leaf render
														// bug
		return super.shouldSideBeRendered(par1iBlockAccess, par2, par3,
				par4, par5);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (world.isRemote) return;

		final int metadata = world.getBlockMetadata(x, y, z);

		if (isUserPlaced(metadata) || !isDecaying(metadata)) return;

		final int rangeWood = 4;
		final int rangeCheckChunk = rangeWood + 1;
		final byte var9 = 32;
		final int var10 = var9 * var9;
		final int var11 = var9 / 2;

		if (adjacentTreeBlocks == null)
			adjacentTreeBlocks = new int[var9 * var9 * var9];

		if (world.checkChunksExist(x - rangeCheckChunk, y
				- rangeCheckChunk, z - rangeCheckChunk, x
				+ rangeCheckChunk, y + rangeCheckChunk, z
				+ rangeCheckChunk))
		{

			for (int var12 = -rangeWood; var12 <= rangeWood; ++var12)
				for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
					for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
					{
						final int id = world.getBlockId(x + var12, y
								+ var13, z + var14);

						final Block block = Block.blocksList[id];

						if (block != null
								&& block.canSustainLeaves(world, x
										+ var12, y + var13, z + var14))
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14
									+ var11] = 0;
						else if (block != null
								&& block.isLeaves(world, x + var12, y
										+ var13, z + var14))
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14
									+ var11] = -2;
						else
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14
									+ var11] = -1;
					}

			for (int var12 = 1; var12 <= 4; ++var12)
				for (int var13 = -rangeWood; var13 <= rangeWood; ++var13)
					for (int var14 = -rangeWood; var14 <= rangeWood; ++var14)
						for (int var15 = -rangeWood; var15 <= rangeWood; ++var15)
							if (adjacentTreeBlocks[(var13 + var11)
									* var10 + (var14 + var11) * var9
									+ var15 + var11] == var12 - 1)
							{
								if (adjacentTreeBlocks[(var13 + var11 - 1)
										* var10
										+ (var14 + var11)
										* var9 + var15 + var11] == -2)
									adjacentTreeBlocks[(var13 + var11 - 1)
											* var10
											+ (var14 + var11)
											* var9 + var15 + var11] = var12;

								if (adjacentTreeBlocks[(var13 + var11 + 1)
										* var10
										+ (var14 + var11)
										* var9 + var15 + var11] == -2)
									adjacentTreeBlocks[(var13 + var11 + 1)
											* var10
											+ (var14 + var11)
											* var9 + var15 + var11] = var12;

								if (adjacentTreeBlocks[(var13 + var11)
										* var10 + (var14 + var11 - 1)
										* var9 + var15 + var11] == -2)
									adjacentTreeBlocks[(var13 + var11)
											* var10
											+ (var14 + var11 - 1)
											* var9 + var15 + var11] = var12;

								if (adjacentTreeBlocks[(var13 + var11)
										* var10 + (var14 + var11 + 1)
										* var9 + var15 + var11] == -2)
									adjacentTreeBlocks[(var13 + var11)
											* var10
											+ (var14 + var11 + 1)
											* var9 + var15 + var11] = var12;

								if (adjacentTreeBlocks[(var13 + var11)
										* var10 + (var14 + var11)
										* var9 + var15 + var11 - 1] == -2)
									adjacentTreeBlocks[(var13 + var11)
											* var10 + (var14 + var11)
											* var9 + var15 + var11 - 1] = var12;

								if (adjacentTreeBlocks[(var13 + var11)
										* var10 + (var14 + var11)
										* var9 + var15 + var11 + 1] == -2)
									adjacentTreeBlocks[(var13 + var11)
											* var10 + (var14 + var11)
											* var9 + var15 + var11 + 1] = var12;
							}
		}

		if (adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11] >= 0)
			world.setBlockMetadata(x, y, z,
					clearDecayOnMetadata(metadata));
		else
			removeLeaves(world, x, y, z);
	}

}
