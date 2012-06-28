package extrabiomes;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import net.minecraft.src.forge.MinecraftForge;

class BlockCustomFlower extends Block implements ITextureProvider, MultiBlock {

	private static final String BLOCK_NAME = "extrabiomes.flower";

	public BlockCustomFlower(final int id) {
		super(id, Material.plants);
		setProperties();

		ModLoader.registerBlock(this, extrabiomes.MultiItemBlock.class);
		setSubBlockDisplayNames();
		registerBlocksForTerrainGen(id);

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));

		MinecraftForge.addGrassPlant(id, FlowerType.AUTUMNSHRUB.metadata(), 2);
		MinecraftForge.addGrassPlant(id, FlowerType.HYDRANGEA.metadata(), 2);
		MinecraftForge.addGrassPlant(id, FlowerType.ORANGE.metadata(), 5);
		MinecraftForge.addGrassPlant(id, FlowerType.PURPLE.metadata(), 5);
		MinecraftForge.addGrassPlant(id, FlowerType.WHITE.metadata(), 5);

		ModLoader.addShapelessRecipe(
				new ItemStack(Item.dyePowder, 1, 12),
				new Object[] { new ItemStack(this, 1, FlowerType.HYDRANGEA
						.metadata()) });
		ModLoader.addShapelessRecipe(
				new ItemStack(Item.dyePowder, 1, 14),
				new Object[] { new ItemStack(this, 1, FlowerType.ORANGE
						.metadata()) });
		ModLoader.addShapelessRecipe(
				new ItemStack(Item.dyePowder, 1, 13),
				new Object[] { new ItemStack(this, 1, FlowerType.PURPLE
						.metadata()) });
		ModLoader.addShapelessRecipe(
				new ItemStack(Item.dyePowder, 1, 7),
				new Object[] { new ItemStack(this, 1, FlowerType.WHITE
						.metadata()) });
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		for (FlowerType ft : FlowerType.values())
			itemList.add(new ItemStack(this, 1, ft.metadata()));
	}

	@Override
	public boolean canBlockStay(final World world, final int x, final int y,
			final int z) {
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world
				.canBlockSeeTheSky(x, y, z))
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x, final int y,
			final int z) {
		return super.canPlaceBlockAt(world, x, y, z)
				&& canThisPlantGrowOnThisBlockID(world.getBlockId(x, y - 1, z));
	}

	protected boolean canThisPlantGrowOnThisBlockID(final int id) {
		return id == Block.grass.blockID || id == Block.dirt.blockID
				|| id == Block.tilledField.blockID;
	}

	protected final void checkFlowerChange(final World world, final int x,
			final int y, final int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	protected int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(final int side, final int md) {
		return super.getBlockTextureFromSideAndMetadata(side, md) + md;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	@Override
	public String getNameFromMetadata(final int md) {
		return BLOCK_NAME + "."
				+ FlowerType.fromMetadata(md).toString().toLowerCase();
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final int id) {
		super.onNeighborBlockChange(world, x, y, z, id);
		checkFlowerChange(world, x, y, z);
	}

	private void registerBlocksForTerrainGen(final int id) {
		for (FlowerType i : FlowerType.values())
			BlockControl.setTerrainGenBlock(
					i.getAliasUsedInTerrainGen(),
					new MetaBlock(id, i.metadata()));
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	private void setProperties() {
		blockIndexInTexture = 32;
		setTickRandomly(true);
		float var4 = 0.2F;
		setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4,
				var4 * 3.0F, 0.5F + var4);
		setHardness(0F);
		setBlockName(BLOCK_NAME);
	}

	private void setSubBlockDisplayNames() {
		for (FlowerType i : FlowerType.values())
			ModLoader.addName(new ItemStack(this, 1, i.metadata()),
					i.getDisplayName());
	}

	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random rand) {
		checkFlowerChange(world, x, y, z);
	}
}
