package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockLeavesBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;
import net.minecraft.src.forge.IShearable;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;

public class BlockGreenLeaves extends BlockLeavesBase implements IShearable,
		ITextureProvider {

	private static final int METADATA_BITMASK = 0x3;
	private static final int METADATA_USERPLACEDBIT = 0x4;
	private static final int METADATA_DECAYBIT = 0x8;
	private static final int METADATA_CLEARDECAYBIT = -METADATA_DECAYBIT - 1;

	public static final int metaFir = 0;
	public static final int metaRedwood = 1;
	public static final int metaAcacia = 2;

	private static int calcSmoothedBiomeFoliageColor(
			final IBlockAccess iBlockAccess, final int x, final int z) {
		int red = 0;
		int green = 0;
		int blue = 0;

		for (int z1 = -1; z1 <= 1; ++z1) {
			for (int x1 = -1; x1 <= 1; ++x1) {
				int foliageColor = iBlockAccess.getBiomeGenForCoords(x + x1,
						z + z1).getBiomeFoliageColor();
				red += (foliageColor & 16711680) >> 16;
				green += (foliageColor & 65280) >> 8;
				blue += foliageColor & 255;
			}
		}

		return (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue / 9 & 255;
	}

	static private int clearDecayOnMetadata(final int metadata) {
		return metadata & METADATA_CLEARDECAYBIT;
	}

	private static boolean isDecaying(final int metadata) {
		return (metadata & METADATA_DECAYBIT) != 0;
	}

	private static boolean isUserPlaced(final int metadata) {
		return (metadata & METADATA_USERPLACEDBIT) != 0;
	}

	private static void setDecaying(final World world, final int x,
			final int y, final int z) {
		if (TerrainGenManager.leafBlockIDs.contains(Integer.valueOf(world
				.getBlockId(x, y, z))))
			world.setBlockMetadata(x, y, z,
					setDecayOnMetadata(world.getBlockMetadata(x, y, z)));
	}

	private static int setDecayOnMetadata(final int metadata) {
		return metadata | METADATA_DECAYBIT;
	}

	private static int unmarkedMetadata(final int metadata) {
		return metadata & METADATA_BITMASK;
	}

	int[] adjacentTreeBlocks;

	public BlockGreenLeaves(final int id) {
		super(id, 80, Material.leaves, false);
		setTickRandomly(true);
		setHardness(0.2F);
		setLightOpacity(1);
		setStepSound(soundGrassFootstep);
		setRequiresSelfNotify();
		Block.setBurnProperties(id, 30, 60);

		TerrainGenManager.blockFirLeaves = TerrainGenManager.blockRedwoodLeaves = TerrainGenManager.blockAcaciaLeaves = this;
		TerrainGenManager.metaFirLeaves = metaFir;
		TerrainGenManager.metaRedwoodLeaves = metaRedwood;
		TerrainGenManager.metaAcaciaLeaves = metaAcacia;
		TerrainGenManager.leafBlockIDs.add(Integer.valueOf(id));
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, metaFir));
		itemList.add(new ItemStack(this, 1, metaRedwood));
		itemList.add(new ItemStack(this, 1, metaAcacia));
	}

	@Override
	public int colorMultiplier(final IBlockAccess iBlockAccess, final int x,
			final int y, final int z) {
		final int metadata = unmarkedMetadata(iBlockAccess.getBlockMetadata(x,
				y, z));

		if (metadata != metaRedwood)
			return getRenderColor(metadata);

		return calcSmoothedBiomeFoliageColor(iBlockAccess, x, z);
	}

	@Override
	protected int damageDropped(final int metadata) {

		return unmarkedMetadata(metadata) + 4;
	}

	private void doSaplingDrop(final World world, final int x, final int y,
			final int z, final int metadata, final int par7) {
		int idDropped = idDropped(metadata, world.rand, par7);
		int damageDropped = damageDropped(metadata);
		dropBlockAsItem_do(world, x, y, z, new ItemStack(idDropped, 1,
				damageDropped));
	}

	@Override
	public void dropBlockAsItemWithChance(final World world, final int x,
			final int y, final int z, final int metadata, final float chance,
			final int par7) {
		if (world.isRemote)
			return;

		if (world.rand.nextInt(20) == 0)
			doSaplingDrop(world, x, y, z, metadata, par7);
	}

	@Override
	public int getBlockColor() {
		return ColorizerFoliage.getFoliageColor(0.5D, 1.0D);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(final int side,
			final int metadata) {
		return this.blockIndexInTexture + unmarkedMetadata(metadata) * 2
				+ (!isOpaqueCube() ? 0 : 1);
	}

	@Override
	public int getRenderColor(int metadata) {
		metadata = unmarkedMetadata(metadata);

		return metadata == 0 ? ColorizerFoliage.getFoliageColorPine()
				: metadata == 1 ? ColorizerFoliage.getFoliageColorBasic()
						: ColorizerFoliage.getFoliageColor(0.9F, 0.1F);
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public void harvestBlock(final World world, final EntityPlayer player,
			final int x, final int y, final int z, final int md) {
		super.harvestBlock(world, player, x, y, z, md);
	}

	@Override
	public int idDropped(final int metadata, final Random rand, final int par3) {
		return ExtrabiomesBlock.sapling.blockID;
	}

	@Override
	public boolean isOpaqueCube() {
		return Block.leaves.isOpaqueCube();
	}

	@Override
	public boolean isShearable(final ItemStack item, final World world,
			final int x, final int y, final int z) {
		return true;
	}

	@Override
	public void onBlockRemoval(final World world, final int x, final int y,
			final int z) {
		final int leafDecayRadius = 1;

		final int chuckCheckRadius = leafDecayRadius + 1;
		if (!world.checkChunksExist(x - chuckCheckRadius, y - chuckCheckRadius,
				z - chuckCheckRadius, x + chuckCheckRadius, y
						+ chuckCheckRadius, z + chuckCheckRadius))
			return;

		for (int x1 = -leafDecayRadius; x1 <= leafDecayRadius; ++x1) {
			for (int y1 = -leafDecayRadius; y1 <= leafDecayRadius; ++y1) {
				for (int z1 = -leafDecayRadius; z1 <= leafDecayRadius; ++z1) {
					setDecaying(world, x + x1, y + y1, z + z1);
				}
			}
		}
	}

	@Override
	public void onEntityWalking(final World par1World, final int par2,
			final int par3, final int par4, final Entity par5Entity) {
		super.onEntityWalking(par1World, par2, par3, par4, par5Entity);
	}

	@Override
	public ArrayList<ItemStack> onSheared(final ItemStack item,
			final World world, final int x, final int y, final int z,
			final int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, unmarkedMetadata(world.getBlockMetadata(
				x, y, z))));
		return ret;
	}

	@Override
	public int quantityDropped(final Random rand) {
		return rand.nextInt(20) == 0 ? 1 : 0;
	}

	private void removeLeaves(final World world, final int x, final int y,
			final int z) {
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockWithNotify(x, y, z, 0);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5) {
		graphicsLevel = !Block.leaves.isOpaqueCube(); // fix leaf render bug
		return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4,
				par5);
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand) {
		if (world.isRemote)
			return;

		final int metadata = world.getBlockMetadata(x, y, z);

		if (isUserPlaced(metadata) || !isDecaying(metadata))
			return;

		final int rangeWood = 4;
		final int rangeCheckChunk = rangeWood + 1;
		final byte var9 = 32;
		final int var10 = var9 * var9;
		final int var11 = var9 / 2;

		if (adjacentTreeBlocks == null) {
			adjacentTreeBlocks = new int[var9 * var9 * var9];
		}

		if (world.checkChunksExist(x - rangeCheckChunk, y - rangeCheckChunk, z
				- rangeCheckChunk, x + rangeCheckChunk, y + rangeCheckChunk, z
				+ rangeCheckChunk)) {

			for (int var12 = -rangeWood; var12 <= rangeWood; ++var12) {
				for (int var13 = -rangeWood; var13 <= rangeWood; ++var13) {
					for (int var14 = -rangeWood; var14 <= rangeWood; ++var14) {
						final int id = world.getBlockId(x + var12, y + var13, z
								+ var14);
						if (TerrainGenManager.woodBlockIDs.contains(Integer
								.valueOf(id))) {
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14 + var11] = 0;
						} else if (TerrainGenManager.leafBlockIDs
								.contains(Integer.valueOf(id))) {
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14 + var11] = -2;
						} else {
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14 + var11] = -1;
						}
					}
				}
			}

			for (int var12 = 1; var12 <= 4; ++var12) {
				for (int var13 = -rangeWood; var13 <= rangeWood; ++var13) {
					for (int var14 = -rangeWood; var14 <= rangeWood; ++var14) {
						for (int var15 = -rangeWood; var15 <= rangeWood; ++var15) {
							if (adjacentTreeBlocks[(var13 + var11) * var10
									+ (var14 + var11) * var9 + var15 + var11] == var12 - 1) {
								if (adjacentTreeBlocks[(var13 + var11 - 1)
										* var10 + (var14 + var11) * var9
										+ var15 + var11] == -2) {
									adjacentTreeBlocks[(var13 + var11 - 1)
											* var10 + (var14 + var11) * var9
											+ var15 + var11] = var12;
								}

								if (adjacentTreeBlocks[(var13 + var11 + 1)
										* var10 + (var14 + var11) * var9
										+ var15 + var11] == -2) {
									adjacentTreeBlocks[(var13 + var11 + 1)
											* var10 + (var14 + var11) * var9
											+ var15 + var11] = var12;
								}

								if (adjacentTreeBlocks[(var13 + var11) * var10
										+ (var14 + var11 - 1) * var9 + var15
										+ var11] == -2) {
									adjacentTreeBlocks[(var13 + var11) * var10
											+ (var14 + var11 - 1) * var9
											+ var15 + var11] = var12;
								}

								if (adjacentTreeBlocks[(var13 + var11) * var10
										+ (var14 + var11 + 1) * var9 + var15
										+ var11] == -2) {
									adjacentTreeBlocks[(var13 + var11) * var10
											+ (var14 + var11 + 1) * var9
											+ var15 + var11] = var12;
								}

								if (adjacentTreeBlocks[(var13 + var11) * var10
										+ (var14 + var11) * var9
										+ (var15 + var11 - 1)] == -2) {
									adjacentTreeBlocks[(var13 + var11) * var10
											+ (var14 + var11) * var9
											+ (var15 + var11 - 1)] = var12;
								}

								if (adjacentTreeBlocks[(var13 + var11) * var10
										+ (var14 + var11) * var9 + var15
										+ var11 + 1] == -2) {
									adjacentTreeBlocks[(var13 + var11) * var10
											+ (var14 + var11) * var9 + var15
											+ var11 + 1] = var12;
								}
							}
						}
					}
				}
			}
		}

		if (adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11] >= 0)
			world.setBlockMetadata(x, y, z, clearDecayOnMetadata(metadata));
		else
			this.removeLeaves(world, x, y, z);
	}
}
