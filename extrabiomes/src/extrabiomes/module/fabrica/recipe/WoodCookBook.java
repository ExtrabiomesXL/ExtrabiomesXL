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
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.module.fabrica.block.BlockCustomWood;
import extrabiomes.module.fabrica.block.BlockCustomWoodSlab;

public class WoodCookBook {

	private static void addLogRecipes() {

		final Optional<? extends Block> planks = Stuff.planks;

		if (planks.isPresent()) {
			IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
					planks.get(), 4,
					BlockCustomWood.BlockType.FIR.metadata()), "logFir");
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(new ItemStack(planks.get(),
					4, BlockCustomWood.BlockType.ACACIA.metadata()),
					"logAcacia");
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(new ItemStack(planks.get(),
					4, BlockCustomWood.BlockType.REDWOOD.metadata()),
					"logRedwood");
			Extrabiomes.proxy.addRecipe(recipe);
		}
	}

	private static void addPlankCustomRecipes() {

		if (Stuff.stairsAcacia.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					Stuff.stairsAcacia.get(), 4), new String[] { "p  ",
					"pp ", "ppp" }, 'p', "plankAcacia");
			Extrabiomes.proxy.addRecipe(recipe);
		}

		if (Stuff.stairsFir.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					Stuff.stairsFir.get(), 4), new String[] { "p  ",
					"pp ", "ppp" }, 'p', "plankFir");
			Extrabiomes.proxy.addRecipe(recipe);
		}

		if (Stuff.stairsRedwood.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					Stuff.stairsRedwood.get(), 4), new String[] {
					"p  ", "pp ", "ppp" }, 'p', "plankRedwood");
			Extrabiomes.proxy.addRecipe(recipe);
		}

		if (Stuff.woodSlab.isPresent()) {
			IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					Stuff.woodSlab.get(), 6,
					BlockCustomWoodSlab.BlockType.ACACIA.metadata()),
					new String[] { "ppp" }, 'p', "plankAcacia");
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapedOreRecipe(new ItemStack(
					Stuff.woodSlab.get(), 6,
					BlockCustomWoodSlab.BlockType.FIR.metadata()),
					new String[] { "ppp" }, 'p', "plankFir");
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapedOreRecipe(new ItemStack(
					Stuff.woodSlab.get(), 6,
					BlockCustomWoodSlab.BlockType.REDWOOD.metadata()),
					new String[] { "ppp" }, 'p', "plankRedwood");
			Extrabiomes.proxy.addRecipe(recipe);
		}

	}

	private static void addPlankVanillaRecipes() {
		OreDictionary.registerOre("plankWood", Block.planks);

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

		recipe = new ShapedOreRecipe(new ItemStack(Item.stick, 4),
				new String[] { "p", "p" }, 'p', "plankWood");
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

		recipe = new ShapedOreRecipe(Item.bed, new String[] { "ccc",
				"ppp" }, 'p', "plankWood", 'c', Block.cloth);
		Extrabiomes.proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(
				Block.tripWireSource, 2),
				new String[] { "i", "s", "p" }, 'p', "plankWood", 'i',
				Item.ingotIron, 's', Item.stick);
		Extrabiomes.proxy.addRecipe(recipe);

	}

	public static void init() {
		addPlankVanillaRecipes();
		addPlankCustomRecipes();
		addLogRecipes();
	}

}
