/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockRedRock;

public class RecipeManager {

	void registerRecipes() {
		if (ExtrabiomesBlock.redRock.isPresent()) {
			OreDictionary.registerOre("rockRed", new ItemStack(
					ExtrabiomesBlock.redRock.get(), 1,
					BlockRedRock.metaRedRock));
			IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
					Item.clay, 4), "rockRed", Item.bucketWater,
					Item.bucketWater, Item.bucketWater);
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapedOreRecipe(new ItemStack(
					ExtrabiomesBlock.redRock.get(), 4,
					BlockRedRock.metaRedRockBrick), new String[] {
					"rr", "rr" }, 'r', "rockRed");
			Extrabiomes.proxy.addRecipe(recipe);

			FurnaceRecipes.smelting().addSmelting(
					ExtrabiomesBlock.redRock.get().blockID,
					BlockRedRock.metaRedCobble,
					new ItemStack(ExtrabiomesBlock.redRock.get(), 1,
							BlockRedRock.metaRedRock));
		}

		if (ExtrabiomesBlock.crackedSand.isPresent()) {
			OreDictionary.registerOre("sandCracked",
					ExtrabiomesBlock.crackedSand.get());
			final IRecipe recipe = new ShapelessOreRecipe(Block.sand,
					"sandCracked", Item.bucketWater);
			Extrabiomes.proxy.addRecipe(recipe);
		}

		if (ExtrabiomesBlock.flower.isPresent()) {
			final ItemStack dyeLightBlue = new ItemStack(
					Item.dyePowder, 1, 12);
			final ItemStack dyeLightGray = new ItemStack(
					Item.dyePowder, 1, 7);
			final ItemStack dyeOrange = new ItemStack(Item.dyePowder,
					1, 14);
			final ItemStack dyePurple = new ItemStack(Item.dyePowder,
					1, 5);
			final ItemStack flowerHydrangea = new ItemStack(
					ExtrabiomesBlock.flower.get(), 1,
					BlockCustomFlower.metaHydrangea);
			final ItemStack flowerOrange = new ItemStack(
					ExtrabiomesBlock.flower.get(), 1,
					BlockCustomFlower.metaOrange);
			final ItemStack flowerPurple = new ItemStack(
					ExtrabiomesBlock.flower.get(), 1,
					BlockCustomFlower.metaPurple);
			final ItemStack flowerToadstool = new ItemStack(
					ExtrabiomesBlock.flower.get(), 1,
					BlockCustomFlower.metaToadstool);
			final ItemStack flowerWhite = new ItemStack(
					ExtrabiomesBlock.flower.get(), 1,
					BlockCustomFlower.metaWhite);

			IRecipe recipe = new ShapelessOreRecipe(dyeLightBlue,
					flowerHydrangea);
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(dyeOrange, flowerOrange);
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(dyePurple, flowerPurple);
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(dyeLightGray, flowerWhite);
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(Item.bowlSoup,
					Block.mushroomBrown, flowerToadstool,
					flowerToadstool, Item.bowlEmpty);
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapelessOreRecipe(Item.bowlSoup,
					Block.mushroomRed, flowerToadstool,
					flowerToadstool, Item.bowlEmpty);
			Extrabiomes.proxy.addRecipe(recipe);
		}
		if (ExtrabiomesBlock.leafPile.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(Block.leaves,
					new String[] { "lll", "lll", "lll" }, 'l',
					ExtrabiomesBlock.leafPile.get());
			Extrabiomes.proxy.addRecipe(recipe);
		}

	}
}
