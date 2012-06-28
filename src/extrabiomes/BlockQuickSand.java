package extrabiomes;

import java.util.ArrayList;

import extrabiomes.api.TerrainGenBlock;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

class BlockQuickSand extends Block implements ITextureProvider {

	private static final String BLOCK_NAME = "extrabiomes.quicksand";
	private static final String DISPLAY_NAME = "Quicksand";
	private static final int TEXTURE_INDEX = 1;
	private static final float HARDNESS = 4.0F;

	public BlockQuickSand(final int id) {
		super(id, TEXTURE_INDEX, Material.sand);
		setProperties();

		ModLoader.registerBlock(this);
		ModLoader.addName(this, DISPLAY_NAME);

		BlockControl.INSTANCE.setTerrainGenBlock(TerrainGenBlock.QUICKSAND,
				new MetaBlock(id, 0));

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
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
	public void onEntityCollidedWithBlock(final World world, final int x,
			final int y, final int z, final Entity entity) {
		entity.setInWeb();
	}

	private void setProperties() {
		setHardness(HARDNESS);
		setStepSound(Block.soundSandFootstep);
		setBlockName(BLOCK_NAME);
	}

}
