package extrabiomes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
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

	static private WorldGenerator chooseTreeForSapling(final Random rand,
			final SaplingType sapling) {
		BlockControl bc = BlockControl.INSTANCE;
		TerrainGenBlock leaf = null;
		TerrainGenBlock wood = TerrainGenBlock.AUTUMN_WOOD;
		switch (sapling) {
		case BROWN:
			leaf = TerrainGenBlock.BROWN_LEAVES;
		case ORANGE:
			if (leaf == null) leaf = TerrainGenBlock.ORANGE_LEAVES;
		case PURPLE:
			if (leaf == null) leaf = TerrainGenBlock.PURPLE_LEAVES;
		case YELLOW:
			if (leaf == null) leaf = TerrainGenBlock.YELLOW_LEAVES;

		if (rand.nextInt(20) == 0)
				return new WorldGenBigAutumnTree(true, leaf,wood);
			return new WorldGenAutumnTree(true, leaf,wood);

		case FIR:
			if (rand.nextInt(2) == 0)
				return new WorldGenFirTree(true);
			return new WorldGenFirTree2(true);

		case REDWOOD:
			return new WorldGenRedwood(true);

		case ACACIA:
			return new WorldGenAcacia(true);

		}
		return null;
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

		Log.write(String.format("%s block initialized with id %d.",
				BLOCK_NAME, id));
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

	public void growTree(final World world, final int x, final int y,
			final int z, final Random rand) {
		final BlockControl blockControl = BlockControl.INSTANCE;
		final int md = unmarkedMetadata(world.getBlockMetadata(x, y, z));
		final WorldGenerator tree = chooseTreeForSapling(rand,
				SaplingType.fromMetadata(md));

		// Remove sapling and put it back if tree doesn't grow.
		world.setBlock(x, y, z, 0);
		if (!tree.generate(world, rand, x, y, z))
			world.setBlockAndMetadata(x, y, z, blockID, md);
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
			BlockControl.INSTANCE.setTerrainGenBlock(
					i.getAliasUsedInTerrainGen(),
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
