package extrabiomes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.BlockFlower;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ForgeHooks;
import net.minecraft.src.forge.IShearable;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenBlock;

public class BlockCustomTallGrass extends BlockFlower implements IShearable,
		ITextureProvider, MultiBlock {

	private static final String BLOCK_NAME = "extrabiomes.tallgrass";
	private static final int TEXTURE_INDEX = 48;

	private static MetaBlock brownGrass = null;
	private static MetaBlock crackedSand = null;
	private static MetaBlock deadGrass = null;
	private static MetaBlock deadTallGrass = null;
	private static MetaBlock deadYellowGrass = null;
	private static MetaBlock redRock = null;
	private static MetaBlock shortBrownGrass = null;
	private static boolean cacheLoaded = false;

	private static void loadCachedBlocks() {
		if (cacheLoaded)
			return;
		cacheLoaded = true;

		final BlockControl bc = BlockControl.INSTANCE;
		brownGrass = bc.getTerrainGenBlock(TerrainGenBlock.BROWN_GRASS);
		crackedSand = bc.getTerrainGenBlock(TerrainGenBlock.CRACKED_SAND);
		deadGrass = bc.getTerrainGenBlock(TerrainGenBlock.DEAD_GRASS);
		deadTallGrass = bc.getTerrainGenBlock(TerrainGenBlock.DEAD_TALL_GRASS);
		deadYellowGrass = bc
				.getTerrainGenBlock(TerrainGenBlock.YELLOW_DEAD_GRASS);
		redRock = bc.getTerrainGenBlock(TerrainGenBlock.RED_ROCK);
		shortBrownGrass = bc
				.getTerrainGenBlock(TerrainGenBlock.SHORT_BROWN_GRASS);
	}

	static private boolean isBlockRedRockOrCrackedSand(final MetaBlock block) {
		loadCachedBlocks();

		return block.equals(redRock) || block.equals(crackedSand);
	}

	private static boolean isBrown(final MetaBlock grass) {
		loadCachedBlocks();

		return grass.equals(brownGrass) || grass.equals(shortBrownGrass);
	}

	static private boolean isBrownAndCanStay(final MetaBlock thisGrass,
			final MetaBlock blockUnder) {
		loadCachedBlocks();

		return isBrown(thisGrass) && blockUnder.equals(redRock);
	}

	public static boolean isDead(final MetaBlock grass) {
		loadCachedBlocks();

		return grass.equals(deadGrass) || grass.equals(deadTallGrass)
				|| grass.equals(deadYellowGrass);
	}

	static private boolean isDeadAndCanStay(final MetaBlock thisGrass,
			final MetaBlock blockUnder) {
		loadCachedBlocks();

		return (isDead(thisGrass)) && blockUnder.equals(crackedSand);
	}

	public BlockCustomTallGrass(final int id) {
		super(id, TEXTURE_INDEX, Material.vine);
		setProperties();

		ModLoader.registerBlock(this, extrabiomes.MultiItemBlock.class);
		setSubBlockDisplayNames();
		registerBlocksForTerrainGen(id);

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		for (TallGrassType i : TallGrassType.values())
			itemList.add(new ItemStack(this, 1, i.metadata()));
	}

	@Override
	public boolean canBlockStay(final World world, final int x, final int y,
			final int z) {
		final MetaBlock thisGrass = new MetaBlock(world, x, y, z);
		final MetaBlock blockUnder = new MetaBlock(world, x, y - 1, z);

		return isBrownAndCanStay(thisGrass, blockUnder)
				|| isDeadAndCanStay(thisGrass, blockUnder)
				|| super.canBlockStay(world, x, y, z);
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x, final int y,
			final int z) {
		final MetaBlock block = new MetaBlock(world, x, y, z);
		return isBlockRedRockOrCrackedSand(block)
				|| super.canPlaceBlockAt(world, x, y, z);
	}

	@Override
	protected boolean canThisPlantGrowOnThisBlockID(final int id) {
		final MetaBlock block = new MetaBlock(id, 0);

		return isBlockRedRockOrCrackedSand(block)
				|| super.canThisPlantGrowOnThisBlockID(id);
	}

	@Override
	public int colorMultiplier(final IBlockAccess iBlockAccess, final int x,
			final int y, final int z) {
		return ColorizerExtraBiomes.getNonBiomeColor();
	}

	@Override
	public int getBlockColor() {
		final double temperature = 0.5D;
		final double hunmidity = 1.0D;
		return ColorizerGrass.getGrassColor(temperature, hunmidity);
	}

	@Override
	public ArrayList<ItemStack> getBlockDropped(final World world, final int x,
			final int y, final int z, final int md, final int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int rarity = isDead(new MetaBlock(blockID, md)) ? 16 : 8;
		if (world.rand.nextInt(rarity) != 0) {
			return ret;
		}

		ItemStack item = ForgeHooks.getGrassSeed(world);

		if (item != null) {
			ret.add(item);
		}
		return ret;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(final int side,
			final int metadata) {
		return super.getBlockTextureFromSideAndMetadata(side, metadata)
				+ metadata;
	}

	@Override
	public String getNameFromMetadata(final int md) {
		return BLOCK_NAME + "."
				+ TallGrassType.fromMetadata(md).toString().toLowerCase();
	}

	@Override
	public int getRenderColor(final int md) {
		return ColorizerExtraBiomes.getNonBiomeColor();
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public void harvestBlock(final World world, final EntityPlayer player,
			final int x, final int y, final int z, final int metadata) {
		super.harvestBlock(world, player, x, y, z, metadata);
	}

	@Override
	public int idDropped(final int par1, final Random par2Random, final int par3) {
		return 0;
	}

	@Override
	public boolean isShearable(final ItemStack item, final World world,
			final int x, final int y, final int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(final ItemStack item,
			final World world, final int x, final int y, final int z,
			final int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
		return ret;
	}

	@Override
	public int quantityDroppedWithBonus(final int i, final Random rand) {
		return 1 + rand.nextInt(i * 2 + 1);
	}

	private void registerBlocksForTerrainGen(final int id) {
		for (TallGrassType i : TallGrassType.values())
			BlockControl.setTerrainGenBlock(
					i.getAliasUsedInTerrainGen(),
					new MetaBlock(id, i.metadata()));
	}

	private void setProperties() {
		float var3 = 0.4F;
		setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F,
				0.5F + var3);
		setHardness(0F);
		setBlockName(BLOCK_NAME);
		setStepSound(soundGrassFootstep);
	}

	private void setSubBlockDisplayNames() {
		for (TallGrassType i : TallGrassType.values())
			ModLoader.addName(new ItemStack(this, 1, i.metadata()),
					i.getDisplayName());
	}
}
