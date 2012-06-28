package extrabiomes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenBlock;

class BlockLeafPile extends Block implements ITextureProvider {

	private static final String BLOCK_NAME = "extrabiomes.leafpile";
	private static final String DISPLAY_NAME = "Leaf Pile";
	private static final int TEXTURE_INDEX = 64;

	static private boolean canThisPlantGrowOnThisBlockID(final int blockId) {
		return blockId == Block.grass.blockID || blockId == Block.dirt.blockID;
	}

	public BlockLeafPile(final int id) {
		super(id, TEXTURE_INDEX, Material.vine);
		setProperties(id);

		ModLoader.registerBlock(this);
		ModLoader.addName(this, DISPLAY_NAME);

		BlockControl.INSTANCE.setTerrainGenBlock(TerrainGenBlock.LEAF_PILE,
				new MetaBlock(id, 0));

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean canBlockStay(final World world, final int x, final int y,
			final int z) {
		return (canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z)));
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x, final int y,
			final int z) {
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
	}

	private void checkFlowerChange(final World world, final int x, final int y,
			final int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	public int colorMultiplier(final IBlockAccess iBlockAccess, final int x,
			final int y, final int z) {
		return iBlockAccess.getBiomeGenForCoords(x, z).getBiomeFoliageColor();
	}

	@Override
	public int getBlockColor() {
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	@Override
	public int getRenderColor(final int metadata) {
		return getBlockColor();
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public boolean isBlockReplaceable(final World world, final int x,
			final int y, final int z) {
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final int neigborId) {
		super.onNeighborBlockChange(world, x, y, z, neigborId);
		checkFlowerChange(world, x, y, z);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	private void setProperties(final int id) {
		float f = 0.5F;
		float f1 = 0.015625F;
		setTickRandomly(true);
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		setBlockName(BLOCK_NAME);
		setStepSound(soundGrassFootstep);
		Block.setBurnProperties(id, 30, 60);
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand) {
		checkFlowerChange(world, x, y, z);
	}

}
