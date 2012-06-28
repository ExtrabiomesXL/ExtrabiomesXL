package extrabiomes;

import java.util.ArrayList;

import extrabiomes.api.TerrainGenBlock;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.forge.ITextureProvider;

public class BlockRedRock extends Block implements ITextureProvider {

	private static final int TEXTURE_INDEX = 2;
	private static final float HARDNESS = 1.5F;
	private static final float RESISTANCE = 2.0F;
	private static final String BLOCK_NAME = "extrabiomes.redrock";
	private static final String DISPLAY_NAME = "Red Rock";

	public BlockRedRock(final int id) {
		super(id, TEXTURE_INDEX, Material.rock);
		setProperties();

		ModLoader.registerBlock(this);
		ModLoader.addName(this, DISPLAY_NAME);

		BlockControl.INSTANCE.setTerrainGenBlock(TerrainGenBlock.RED_ROCK,
				new MetaBlock(id, 0));

		Log.write(String.format("%s block initialized with id %d.", BLOCK_NAME,
				id));

		ModLoader.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[] {
				new ItemStack(this), new ItemStack(Item.bucketWater),
				new ItemStack(Item.bucketWater) });
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

	private void setProperties() {
		setHardness(HARDNESS);
		setResistance(RESISTANCE);
		setBlockName(BLOCK_NAME);
	}

}
