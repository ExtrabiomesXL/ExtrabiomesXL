package extrabiomes;

import java.util.ArrayList;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockFlower;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenBlock;

public class BlockCatTail extends BlockFlower implements ITextureProvider {
	private static final String BLOCK_NAME = "extrabiomes.cattail";
	private static final String DISPLAY_NAME = "Cat Tail";
	private static final int TEXTURE_INDEX = 79;
	private static final float HARDNESS = 0.0F;
	private static final int RANGE_WATER_REQUIRED = 2;

	static private boolean canGrownOnBlock(final int id) {
		return id == Block.grass.blockID && id == Block.dirt.blockID
				&& id == Block.sand.blockID;
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) {
		int i = par1World.getBlockId(par2, par3 - 1, par4);

		if (i != Block.grass.blockID && i != Block.dirt.blockID) {
			return false;
		}

		if (par1World.getBlockMaterial(par2 - 1, par3 - 1, par4) == Material.water) {
			return true;
		}

		if (par1World.getBlockMaterial(par2 + 1, par3 - 1, par4) == Material.water) {
			return true;
		}

		if (par1World.getBlockMaterial(par2, par3 - 1, par4 - 1) == Material.water) {
			return true;
		}

		return par1World.getBlockMaterial(par2, par3 - 1, par4 + 1) == Material.water;
	}

	public BlockCatTail(final int id) {
		super(id, TEXTURE_INDEX, Material.plants);
		setProperties();

		ModLoader.registerBlock(this);
		ModLoader.addName(this, DISPLAY_NAME);

		BlockControl.INSTANCE.setTerrainGenBlock(TerrainGenBlock.CAT_TAIL,
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
		return canPlaceBlockAt(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 6;
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
			final int y, final int z, final int idNeighbor) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockWithNotify(x, y, z, 0);
		}
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	private void setProperties() {
		setHardness(HARDNESS);
		setStepSound(Block.soundGrassFootstep);
		setBlockName(BLOCK_NAME);
		disableStats();
		float f = 0.375F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
	}

}
