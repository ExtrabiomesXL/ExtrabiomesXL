/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.recipe;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import extrabiomes.Extrabiomes;
import extrabiomes.module.summa.block.BlockManager;
import extrabiomes.module.summa.block.BlockRedRock;
import extrabiomes.proxy.CommonProxy;

public class CookBook {

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

	private static void addLeafPileRecipes() {
		final CommonProxy proxy = Extrabiomes.proxy;
		final IRecipe recipe = new ShapedOreRecipe(Block.leaves,
				new String[] { "lll", "lll", "lll" }, 'l', "pileLeaf");
		proxy.addRecipe(recipe);
	}

	private static void addRedRockRecipes() {
		final CommonProxy proxy = Extrabiomes.proxy;
		IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
				Item.clay, 4), "rockRed", Item.bucketWater,
				Item.bucketWater, Item.bucketWater);
		proxy.addRecipe(recipe);

		if (BlockManager.REDROCK.getBlock().isPresent()) {
			recipe = new ShapedOreRecipe(new ItemStack(
					BlockManager.REDROCK.getBlock().get(), 4,
					BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()),
					new String[] { "rr", "rr" }, 'r', "rockRed");
			proxy.addRecipe(recipe);

			final ItemStack redRockItem = new ItemStack(
					BlockManager.REDROCK.getBlock().get(), 1,
					BlockRedRock.BlockType.RED_ROCK.metadata());
			proxy.addSmelting(
					BlockManager.REDROCK.getBlock().get().blockID,
					BlockRedRock.BlockType.RED_COBBLE.metadata(),
					redRockItem);
		}
	}

	private static void addWoodRecipes() {
		IRecipe recipe = new ShapedOreRecipe(Block.chest, new String[] {
				"ppp", "p p", "ppp" }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.workbench, new String[] {
				"pp", "pp" }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.pickaxeWood, new String[] {
				"ppp", " s ", " s " }, 'p', "plankWood", 's',
				Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.shovelWood, new String[] {
				"p", "s", "s" }, 'p', "plankWood", 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.axeWood, new String[] { "pp",
				"ps", " s" }, 'p', "plankWood", 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.hoeWood, new String[] { "pp",
				" s", " s" }, 'p', "plankWood", 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.swordWood, new String[] {
				"p", "p", "s" }, 'p', "plankWood", 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.fenceGate, new String[] {
				"sps", "sps" }, 'p', "plankWood", 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.jukebox, new String[] {
				"ppp", "pdp", "ppp" }, 'p', "plankWood", 'r',
				Item.diamond);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.music, new String[] { "ppp",
				"prp", "ppp" }, 'p', "plankWood", 'r', Item.redstone);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.bookShelf, new String[] {
				"ppp", "bbb", "ppp" }, 'p', "plankWood", 'b', Item.book);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.doorWood, new String[] {
				"pp", "pp", "pp" }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(Block.trapdoor, 2),
				new String[] { "ppp", "ppp" }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.sign, new String[] { "ppp",
				"ppp", " s " }, 'p', "plankWood", 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(new ItemStack(Item.stick, 4),
				"plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(Item.bowlEmpty, 4),
				new String[] { "p p", " p " }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.boat, new String[] { "p p",
				"ppp" }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.pressurePlatePlanks,
				new String[] { "pp" }, 'p', "plankWood");
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Block.pistonBase, new String[] {
				"ppp", "cic", "crc" }, 'p', "plankWood", 'c',
				Block.cobblestone, 'i', Item.ingotIron, 'r',
				Item.redstone);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(Item.bed, new String[] { "ppp",
				"ccc", "ppp" }, 'p', "plankWood", 'c', Block.cloth);
		Extrabiomes.proxy.addRecipe(recipe);
	}

	public static void init() {
		addCrackedSandRecipes();
		addRedRockRecipes();
		addFlowerRecipes();
		addLeafPileRecipes();
		addWoodRecipes();
	}

}
