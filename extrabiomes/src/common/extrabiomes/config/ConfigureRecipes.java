/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.config;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import extrabiomes.Proxy;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.blocks.BlockCustomFlower;

public class ConfigureRecipes {

	public static void initialize() {
		if (ExtrabiomesItem.scarecrow != null)
			Proxy.addRecipe(
					new ItemStack(ExtrabiomesItem.scarecrow),
					new Object[] { " a ", "cbc", " c ",
							Character.valueOf('a'), Block.pumpkin,
							Character.valueOf('b'), Block.melon,
							Character.valueOf('c'), Item.stick });

		if (ExtrabiomesBlock.redRock != null)
			Proxy.addShapelessRecipe(new ItemStack(Item.clay, 4),
					new Object[] {
							new ItemStack(ExtrabiomesBlock.redRock),
							new ItemStack(Item.bucketWater),
							new ItemStack(Item.bucketWater),
							new ItemStack(Item.bucketWater) });

		if (ExtrabiomesBlock.crackedSand != null)
			Proxy.addShapelessRecipe(
					new ItemStack(Block.sand),
					new Object[] {
							new ItemStack(ExtrabiomesBlock.crackedSand),
							new ItemStack(Item.bucketWater) });

		if (ExtrabiomesBlock.flower != null) {
			Proxy.addShapelessRecipe(new ItemStack(Item.dyePowder, 1,
					12), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaHydrangea) });
			Proxy.addShapelessRecipe(new ItemStack(Item.dyePowder, 1,
					14), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaOrange) });
			Proxy.addShapelessRecipe(new ItemStack(Item.dyePowder, 1,
					13), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaPurple) });
			Proxy.addShapelessRecipe(
					new ItemStack(Item.dyePowder, 1, 7),
					new Object[] { new ItemStack(
							ExtrabiomesBlock.flower, 1,
							BlockCustomFlower.metaWhite) });
			Proxy.addShapelessRecipe(new ItemStack(Item.bowlSoup),
					new Object[] {
							Block.mushroomBrown,
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							Item.bowlEmpty });
			Proxy.addShapelessRecipe(new ItemStack(Item.bowlSoup),
					new Object[] {
							Block.mushroomRed,
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							Item.bowlEmpty });
		}
		if (ExtrabiomesBlock.leafPile != null)
			Proxy.addRecipe(new ItemStack(Block.leaves), new Object[] {
					"###", "###", "###", Character.valueOf('#'),
					new ItemStack(ExtrabiomesBlock.leafPile) });
	}

}
