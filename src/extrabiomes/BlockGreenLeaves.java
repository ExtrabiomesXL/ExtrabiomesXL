package extrabiomes;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockLeavesBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.IShearable;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenBlock;

class BlockGreenLeaves extends BlockLeavesBase implements MultiBlock,
		IShearable, ITextureProvider {

	private static final String BLOCK_NAME = "extrabiomes.greenleaves";

	private static final int METADATA_BITMASK = 0x3;
	private static final int METADATA_USERPLACEDBIT = 0x4;
	private static final int METADATA_DECAYBIT = 0x8;
	private static final int METADATA_CLEARDECAYBIT = -METADATA_DECAYBIT - 1;

	private static final int TEXTURE_INDEX = 80;
	private static final int LIGHT_OPACITY = 1;
	private static final int BURN_ENCOURAGEMENT = 30;
	private static final int FLAMMABILITY = 60;
	private static final float HARDNESS = 0.2F;
	private static final int SAPLING_DROP_RARITY = 20;
	private static final int RANGE_WOOD_PREVENTS_DECAY = 4;

	private static Map<GreenLeafType, MetaBlock> saplingDrops = null;

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

	private static void loadCachedBlocks() {
		if (saplingDrops != null)
			return;

		saplingDrops = new EnumMap<GreenLeafType, MetaBlock>(
				GreenLeafType.class);

		for (GreenLeafType i : GreenLeafType.values())
			saplingDrops.put(i, BlockControl.INSTANCE.getTerrainGenBlock(i
					.getSaplingToDrop()));
	}

	private static void setDecaying(final World world, final int x,
			final int y, final int z) {
		if (BlockControl.INSTANCE.isLeaves(world.getBlockId(x, y, z)))
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
		super(id, TEXTURE_INDEX, Material.leaves, false);
		setProperties(id);

		ModLoader
				.registerBlock(this, extrabiomes.ItemCustomLeaves.class);
		setSubBlockDisplayNames();
		registerBlocksForTerrainGen(id);

		BlockControl.INSTANCE.registerLeaves(id);

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		for (GreenLeafType lt : GreenLeafType.values())
			itemList.add(new ItemStack(this, 1, lt.metadata()));
	}

	@Override
	public int colorMultiplier(final IBlockAccess iBlockAccess, final int x,
			final int y, final int z) {
		final int metadata = unmarkedMetadata(iBlockAccess.getBlockMetadata(x,
				y, z));

		if (metadata != BlockControl.INSTANCE.getTerrainGenBlock(
				TerrainGenBlock.REDWOOD_LEAVES).metadata())
			return getRenderColor(metadata);

		return calcSmoothedBiomeFoliageColor(iBlockAccess, x, z);
	}

	@Override
	protected int damageDropped(final int metadata) {
		loadCachedBlocks();

		MetaBlock metaBlock = saplingDrops.get(
				GreenLeafType.fromMetadata(unmarkedMetadata(metadata)));
		return metaBlock
				.metadata();
	}

	private void doSaplingDrop(final World world, final int x, final int y,
			final int z, final int metadata, final int par7) {
		int idDropped = idDropped(metadata, world.rand, par7);
		int damageDropped = damageDropped(metadata);
		dropBlockAsItem_do(world, x, y, z,
				new ItemStack(idDropped, 1,
						damageDropped));
	}

	@Override
	public void dropBlockAsItemWithChance(final World world, final int x,
			final int y, final int z, final int metadata, final float chance,
			final int par7) {
		if (world.isRemote)
			return;

		if (world.rand.nextInt(SAPLING_DROP_RARITY) == 0)
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
	public String getNameFromMetadata(final int metadata) {
		return BLOCK_NAME
				+ "."
				+ GreenLeafType.fromMetadata(unmarkedMetadata(metadata))
						.toString().toLowerCase();
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
		loadCachedBlocks();

		return saplingDrops.get(
				GreenLeafType.fromMetadata(unmarkedMetadata(metadata)))
				.blockId();
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
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess,
			int par2, int par3, int par4, int par5) {
		graphicsLevel = !Block.leaves.isOpaqueCube(); // fix leaf render bug
		return super.shouldSideBeRendered(par1iBlockAccess, par2, par3, par4, par5);
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
		return rand.nextInt(SAPLING_DROP_RARITY) == 0 ? 1 : 0;
	}

	private void registerBlocksForTerrainGen(final int id) {
		for (GreenLeafType i : GreenLeafType.values())
			BlockControl.setTerrainGenBlock(
					i.getAliasUsedInTerrainGen(),
					new MetaBlock(id, i.metadata()));
	}

	private void removeLeaves(final World world, final int x, final int y,
			final int z) {
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockWithNotify(x, y, z, 0);
	}

	private void setProperties(final int id) {
		setTickRandomly(true);
		setHardness(HARDNESS);
		setLightOpacity(LIGHT_OPACITY);
		setStepSound(soundGrassFootstep);
		setBlockName(BLOCK_NAME);
		setRequiresSelfNotify();
		Block.setBurnProperties(id, BURN_ENCOURAGEMENT, FLAMMABILITY);
	}

	private void setSubBlockDisplayNames() {
		for (GreenLeafType i : GreenLeafType.values())
			ModLoader.addName(new ItemStack(this, 1, i.metadata()),
					i.getDisplayName());
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand) {
		if (world.isRemote)
			return;

		final int metadata = world.getBlockMetadata(x, y, z);

		if (isUserPlaced(metadata) || !isDecaying(metadata))
			return;

		final int rangeWood = RANGE_WOOD_PREVENTS_DECAY;
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
						final BlockControl bc = BlockControl.INSTANCE;
						if (bc.isWood(id)) {
							adjacentTreeBlocks[(var12 + var11) * var10
									+ (var13 + var11) * var9 + var14 + var11] = 0;
						} else if (bc.isLeaves(id)) {
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
