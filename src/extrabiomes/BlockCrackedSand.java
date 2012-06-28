package extrabiomes;

import java.util.ArrayList;
import java.util.Random;

import extrabiomes.api.TerrainGenBlock;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

class BlockCrackedSand extends Block implements ITextureProvider {

	private static final String BLOCK_NAME = "extrabiomes.crackedsand";
	private static final String DISPLAY_NAME = "Cracked Sand";
	private static final int TEXTURE_INDEX = 0;
	private static final float HARDNESS = 1.2F;

	private enum Side {
		YPLUS, YMINUS, XPLUS, XMINUS, ZPLUS, ZMINUS
	}

	public BlockCrackedSand(int id) {
		super(id, TEXTURE_INDEX, Material.rock);
		setProperties();

		ModLoader.registerBlock(this);
		ModLoader.addName(this, DISPLAY_NAME);

		BlockControl.INSTANCE.setTerrainGenBlock(TerrainGenBlock.CRACKED_SAND,
				new MetaBlock(id, 0));

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));

		ModLoader.addShapelessRecipe(new ItemStack(sand), new Object[] {
				new ItemStack(this), new ItemStack(Item.bucketWater) });
	}

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	private void setProperties() {
		setHardness(HARDNESS);
		setStepSound(Block.soundStoneFootstep);
		setBlockName(BLOCK_NAME);
		this.setTickRandomly(true);
	}

	private void changeNeighbor(World world, int x, int y, int z) {
		if (world.getBlockLightValue(x, y + 1, z) < 9)
			return;

		final int id = world.getBlockId(x, y, z);
		if (id == sand.blockID)
			world.setBlock(x, y, z, blockID);
		else if (id == sandStone.blockID) {
			final int metadata = world.getBlockMetadata(x, y, z);
			if (metadata != 0)
				world.setBlockMetadataWithNotify(x, y, z, 0);
			else
				world.setBlock(x, y, z, sand.blockID);
		} else if (id == dirt.blockID)
			world.setBlock(x, y, z, sand.blockID);
		else if (id == grass.blockID || id == tilledField.blockID)
			world.setBlock(x, y, z, dirt.blockID);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (!world.isRemote) {
			if (world.getBlockLightValue(x, y + 1, z) < 9)
				return;
			for (int i = 0; i < 4; ++i) {
				int x1 = x + rand.nextInt(3) - 1;
				int y1 = y + rand.nextInt(5) - 3;
				int z1 = z + rand.nextInt(3) - 1;
				changeNeighbor(world, x1, y1, z1);
			}
		}
	}
}
