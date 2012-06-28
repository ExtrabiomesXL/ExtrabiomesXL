package extrabiomes.blocks;

import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.EnumCreatureType;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;
import extrabiomes.api.TerrainGenManager;

public class BlockRedRock extends Block implements ITextureProvider {

	public BlockRedRock(final int id) {
		super(id, 2, Material.rock);
		setHardness(1.5F);
		setResistance(2.0F);

		TerrainGenManager.blockMountainRidge = this;

		ModLoader.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[] {
				new ItemStack(this), new ItemStack(Item.bucketWater),
				new ItemStack(Item.bucketWater) });
	}

	@Override
	public void addCreativeItems(final ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean canCreatureSpawn(EnumCreatureType type, World world, int x,
			int y, int z) {
		return true;
	}

	@Override
	public String getTextureFile() {
		return "/extrabiomes/extrabiomes.png";
	}

}
