package extrabiomes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import net.minecraft.src.forge.IBonemealHandler;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.MinecraftForge;
import extrabiomes.api.TerrainGenBlock;

class BlockCustomSapling extends BlockFlower implements MultiBlock,
		IBonemealHandler, IFuel, ITextureProvider {

	private static final String BLOCK_NAME = "extrabiomes.sapling";

	private static final int METADATA_BITMASK = 0x7;
	private static final int METADATA_MARKBIT = 0x8;

	private static final int FUEL_VALUE = 100;
	private static final int LIGHT_NEEDED_TO_GROW = 9;
	private static final int TEXTURE_INDEX = 16;

	private static WorldGenerator ChooseAutumnTree(SaplingType sapling,
			Random rand) {
		final TerrainGenBlock leaf;
		final TerrainGenBlock wood = TerrainGenBlock.AUTUMN_WOOD;
		switch (sapling) {
		case BROWN:
			leaf = TerrainGenBlock.BROWN_LEAVES;
			break;
		case ORANGE:
			leaf = TerrainGenBlock.ORANGE_LEAVES;
			break;
		case PURPLE:
			leaf = TerrainGenBlock.PURPLE_LEAVES;
			break;
		default:
			leaf = TerrainGenBlock.YELLOW_LEAVES;
		}

		if (rand.nextInt(20) == 0)
			return new WorldGenBigAutumnTree(true, leaf, wood);
		return new WorldGenAutumnTree(true, leaf, wood);
	}

	static private boolean isEnoughLightToGrow(final World world, final int x,
			final int y, final int z) {
		return world.getBlockLightValue(x, y, z) >= LIGHT_NEEDED_TO_GROW;
	}

	static private boolean isMarkedMetadata(int metadata) {
		return (metadata & METADATA_MARKBIT) != 0;
	}

	static private int markedMetadata(int metadata) {
		return metadata | METADATA_MARKBIT;
	}

	static private int unmarkedMetadata(final int metadata) {
		return metadata & METADATA_BITMASK;
	}

	public BlockCustomSapling(final int id) {
		super(id, TEXTURE_INDEX);
		setProperties();

		ModLoader.registerBlock(this, extrabiomes.MultiItemBlock.class);
		setSubBlockDisplayNames();
		registerBlocksForTerrainGen(id);

		MinecraftForge.registerBonemealHandler(this);
		BlockControl.INSTANCE.registerFuel(id, this);

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		for (SaplingType i : SaplingType.values())
			itemList.add(new ItemStack(this, 1, i.metadata()));
	}

	private void attemptGrowTree(final World world, final int x, final int y,
			final int z, final Random rand) {
		if (isEnoughLightToGrow(world, x, y + 1, z) && rand.nextInt(7) == 0) {
			int metadata = world.getBlockMetadata(x, y, z);

			if (!isMarkedMetadata(metadata))
				world.setBlockMetadataWithNotify(x, y, z,
						markedMetadata(metadata));
			else
				growTree(world, x, y, z, rand);
		}
	}

	@Override
	protected int damageDropped(final int md) {
		return unmarkedMetadata(md);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(final int side, final int md) {
		return super.getBlockTextureFromSide(side) + (unmarkedMetadata(md));
	}

	@Override
	public int getFuelValue(final int metadata) {
		return FUEL_VALUE;
	}

	@Override
	public String getNameFromMetadata(final int metadata) {
		return BLOCK_NAME
				+ "."
				+ SaplingType.fromMetadata(unmarkedMetadata(metadata))
						.toString().toLowerCase();
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	public void growTree(World world, int x, int y, int z, Random rand) {
		final int metadata = unmarkedMetadata(world.getBlockMetadata(x, y, z));
		final SaplingType sapling = SaplingType.fromMetadata(metadata);
		WorldGenerator tree = null;
		int x1 = 0;
		int z1 = 0;
		boolean isHuge = false;

		if (sapling == SaplingType.BROWN || sapling == SaplingType.ORANGE
				|| sapling == SaplingType.PURPLE
				|| sapling == SaplingType.YELLOW)
			tree = ChooseAutumnTree(sapling, rand);
		else if (sapling == SaplingType.ACACIA)
			tree = new WorldGenAcacia(true);
		else {
			// Check for 2x2 firs and redwoods
			for (x1 = 0; x1 >= -1; --x1) {
				for (z1 = 0; z1 >= -1; --z1) {
					if (isSameSapling(world, x + x1, y, z + z1, metadata)
							&& isSameSapling(world, x + x1 + 1, y, z + z1,
									metadata)
							&& isSameSapling(world, x + x1, y, z + z1 + 1,
									metadata)
							&& isSameSapling(world, x + x1 + 1, y, z + z1 + 1,
									metadata)) {
						if (sapling == SaplingType.FIR)
							tree = new WorldGenFirTree2(true);
						else
							tree = new WorldGenRedwood(true);
						isHuge = true;
						break;
					}
				}
				if (tree != null)
					break;
			}
			if (tree == null && sapling == SaplingType.FIR) {
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
			} else {
				world.setBlock(x, y, z, 0);
			}

			final int offset = isHuge ? 1 : 0;

			if (!tree
					.generate(world, rand, x + x1 + offset, y, z + z1 + offset)) {
				if (isHuge) {
					world.setBlockAndMetadata(x + x1, y, z + z1, blockID,
							metadata);
					world.setBlockAndMetadata(x + x1 + 1, y, z + z1, blockID,
							metadata);
					world.setBlockAndMetadata(x + x1, y, z + z1 + 1, blockID,
							metadata);
					world.setBlockAndMetadata(x + x1 + 1, y, z + z1 + 1,
							blockID, metadata);
				} else {
					world.setBlockAndMetadata(x, y, z, blockID, metadata);
				}
			}
		}
	}

	public boolean isSameSapling(World world, int x, int y, int z, int metadata) {
		return world.getBlockId(x, y, z) == blockID
				&& unmarkedMetadata(world.getBlockMetadata(x, y, z)) == metadata;
	}

	@Override
	public boolean onUseBonemeal(final World world, final int blockID,
			final int x, final int y, final int z) {
		if (blockID == this.blockID) {
			if (!world.isRemote) {
				growTree(world, x, y, z, world.rand);
			}
			return true;
		}
		return false;
	}

	private void registerBlocksForTerrainGen(final int id) {
		for (SaplingType i : SaplingType.values())
			BlockControl.setTerrainGenBlock(i.getAliasUsedInTerrainGen(),
					new MetaBlock(id, i.metadata()));
	}

	private void setProperties() {
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3,
				var3 * 2.0F, 0.5F + var3);
		setHardness(0F);
		setStepSound(soundGrassFootstep);
		setBlockName(BLOCK_NAME);
		setRequiresSelfNotify();
	}

	private void setSubBlockDisplayNames() {
		for (SaplingType i : SaplingType.values())
			ModLoader.addName(new ItemStack(this, 1, i.metadata()),
					i.getDisplayName());
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand) {
		if (!world.isRemote) {
			super.updateTick(world, x, y, z, rand);
			attemptGrowTree(world, x, y, z, rand);
		}
	}
}
