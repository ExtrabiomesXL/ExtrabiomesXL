/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import extrabiomes.Extrabiomes;
import extrabiomes.IModule;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.summa.block.BlockRedRock;
import extrabiomes.module.summa.block.BlockManager;
import extrabiomes.proxy.CommonProxy;

public class Fabrica implements IModule {

	private static void addCrackedSandRecipes() {
		final IRecipe recipe = new ShapelessOreRecipe(Block.sand,
				"sandCracked", Item.bucketWater);
		Extrabiomes.proxy.addRecipe(recipe);
	}

	private static void addFlowerRecipes() {
		final CommonProxy proxy = Extrabiomes.proxy;

		final ItemStack dyeLightBlue = new ItemStack(Item.dyePowder, 1,
				12);
		final ItemStack dyeLightGray = new ItemStack(Item.dyePowder, 1,
				7);
		final ItemStack dyeOrange = new ItemStack(Item.dyePowder, 1, 14);
		final ItemStack dyePurple = new ItemStack(Item.dyePowder, 1, 5);

		IRecipe recipe = new ShapelessOreRecipe(dyeLightBlue,
				"flowerHydrangea");
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyeOrange, "flowerOrange");
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyePurple, "flowerPurple");
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyeLightGray, "flowerWhite");
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(Item.bowlSoup,
				Block.mushroomBrown, "flowerToadstool",
				"flowerToadstool", Item.bowlEmpty);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(Item.bowlSoup,
				Block.mushroomRed, "flowerToadstool",
				"flowerToadstool", Item.bowlEmpty);
		proxy.addRecipe(recipe);
	}

	private static void addRedRockRecipes() {
		final CommonProxy proxy = Extrabiomes.proxy;
		IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
				Item.clay, 4), "rockRed", Item.bucketWater,
				Item.bucketWater, Item.bucketWater);
		proxy.addRecipe(recipe);

		if (BlockManager.REDROCK.getBlock().isPresent()) {
			recipe = new ShapedOreRecipe(new ItemStack(BlockManager.REDROCK
					.getBlock().get(), 4,
					BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()),
					new String[] { "rr", "rr" }, 'r', "rockRed");
			proxy.addRecipe(recipe);

			final ItemStack redRockItem = new ItemStack(BlockManager.REDROCK
					.getBlock().get(), 1,
					BlockRedRock.BlockType.RED_ROCK.metadata());
			proxy.addSmelting(BlockManager.REDROCK.getBlock().get().blockID,
					BlockRedRock.BlockType.RED_COBBLE.metadata(),
					redRockItem);
		}
	}

	private void addLeafPileRecipes() {
		final CommonProxy proxy = Extrabiomes.proxy;
		final IRecipe recipe = new ShapedOreRecipe(Block.leaves,
				new String[] { "lll", "lll", "lll" }, 'l', "pileLeaf");
		proxy.addRecipe(recipe);
	}

	@Override
	public void init() {
		addCrackedSandRecipes();
		addRedRockRecipes();
		addFlowerRecipes();
		addLeafPileRecipes();
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{}

}
