/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.config;

import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import extrabiomes.Proxy;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCatTail;
import extrabiomes.blocks.BlockCrackedSand;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.blocks.BlockLeafPile;
import extrabiomes.blocks.BlockQuickSand;
import extrabiomes.blocks.BlockRedRock;

public class ConfigureBlocks {

	public static boolean	crackedSandCanGrow						= true;
	public static boolean	crackedSandGrowthRestrictedToWasteland	= true;

	public static void addNames() {
		Proxy.addName(ExtrabiomesBlock.catTail, "Cat Tail");
		Proxy.addName(ExtrabiomesBlock.crackedSand, "Cracked Sand");
		Proxy.addName(ExtrabiomesBlock.leafPile, "Leaf Pile");
		Proxy.addName(ExtrabiomesBlock.quickSand, "Quick Sand");

		if (ExtrabiomesBlock.redRock != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.redRock, 1,
					BlockRedRock.metaRedRock), "Red Rock");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.redRock, 1,
					BlockRedRock.metaRedCobble), "Red Cobblestone");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.redRock, 1,
					BlockRedRock.metaRedRockBrick), "Red Rock Brick");
		}
		if (ExtrabiomesBlock.autumnLeaves != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 0), "Brown Autumn Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 1), "Orange Autumn Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 2), "Purple Autumn Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 3), "Yellow Autumn Leaves");
		}

		if (ExtrabiomesBlock.flower != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 0),
					"Autumn Shrub");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 1),
					"Hydrangea");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 2),
					"Orange Flower");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 3),
					"Purple Flower");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 4),
					"Tiny Cactus");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 5),
					"Root");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 6),
					"Toad Stool");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 7),
					"White Flower");
		}

		if (ExtrabiomesBlock.grass != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 0),
					"Brown Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 1),
					"Short Brown Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 2),
					"Dead Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 3),
					"Tall Dead Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 4),
					"Yellow Dead Grass");
		}

		if (ExtrabiomesBlock.greenLeaves != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.greenLeaves,
					1, 0), "Fir Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.greenLeaves,
					1, 1), "Redwood Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.greenLeaves,
					1, 2), "Acacia Leaves");
		}

		if (ExtrabiomesBlock.sapling != null) {
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 0),
					"Brown Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 1),
					"Orange Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 2),
					"Purple Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 3),
					"Yellow Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 4),
					"Fir Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 5),
					"Redwood Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 6),
					"Acacia Sapling");
		}
	}

	public static void initialize() {
		if (Config.enableClassicMode) return;

		final int crackedSandID = Config.getOrCreateIntProperty(
				"crackedsand.id", Configuration.CATEGORY_BLOCK, 150) & 0xFF;
		if (crackedSandID != 0) {
			crackedSandCanGrow = Config.getOrCreateBooleanProperty(
					"wasteland.enable.growth", Config.CATEGORY_BIOME,
					crackedSandCanGrow);
			crackedSandGrowthRestrictedToWasteland = Config
					.getOrCreateBooleanProperty(
							"wasteland.restrict.growth.to.biome",
							Config.CATEGORY_BIOME,
							crackedSandGrowthRestrictedToWasteland);
			ExtrabiomesBlock.crackedSand = new BlockCrackedSand(
					crackedSandID).setBlockName("crackedsand");
			MinecraftForge.setBlockHarvestLevel(
					ExtrabiomesBlock.crackedSand, "pickaxe", 0);
		}

		// Fix for 4096 patch errors
		final Property crackedSand = Config.getProperty(
				"crackedsand.id", Configuration.CATEGORY_BLOCK,
				String.valueOf(crackedSandID));
		crackedSand.value = String.valueOf(crackedSandID);
		crackedSand.comment = "Due to a hole in the 4096 patch crackedsand.id must be set to a value less than 256.";

		final int redRockID = Config.getOrCreateIntProperty(
				"redrock.id", Configuration.CATEGORY_BLOCK, 151) & 0xFF;
		if (redRockID != 0) {
			ExtrabiomesBlock.redRock = new BlockRedRock(redRockID)
					.setBlockName("redrock");
			MinecraftForge.setBlockHarvestLevel(
					ExtrabiomesBlock.redRock, "pickaxe", 0);
		}

		// Fix for 4096 patch errors
		final Property redRock = Config
				.getProperty("redrock.id",
						Configuration.CATEGORY_BLOCK,
						String.valueOf(redRockID));
		redRock.value = String.valueOf(redRockID);
		redRock.comment = "Due to a hole in the 4096 patch redrock.id must be set to a value less than 256.";

		final int quickSandID = Config.getOrCreateBlockIdProperty(
				"quicksand.id", 152);
		if (quickSandID != 0) {
			ExtrabiomesBlock.quickSand = new BlockQuickSand(quickSandID)
					.setBlockName("quicksand");
			MinecraftForge.setBlockHarvestLevel(
					ExtrabiomesBlock.quickSand, "shovel", 0);
		}

		final int autumnLeavesID = Config.getOrCreateBlockIdProperty(
				"autumnleaves.id", 153);
		if (autumnLeavesID != 0)
			ExtrabiomesBlock.autumnLeaves = new BlockAutumnLeaves(
					autumnLeavesID).setBlockName("autumnleaves");

		final int greenLeavesID = Config.getOrCreateBlockIdProperty(
				"greenleaves.id", 154);
		if (greenLeavesID != 0)
			ExtrabiomesBlock.greenLeaves = new BlockGreenLeaves(
					greenLeavesID).setBlockName("greenleaves");

		final int flowerID = Config.getOrCreateBlockIdProperty(
				"flower.id", 155);
		if (flowerID != 0)
			ExtrabiomesBlock.flower = new BlockCustomFlower(flowerID)
					.setBlockName("flower");

		final int grassID = Config.getOrCreateBlockIdProperty(
				"grass.id", 156);
		if (grassID != 0)
			ExtrabiomesBlock.grass = new BlockCustomTallGrass(grassID)
					.setBlockName("grass");

		final int saplingID = Config.getOrCreateBlockIdProperty(
				"sapling.id", 157);
		if (saplingID != 0)
			ExtrabiomesBlock.sapling = new BlockCustomSapling(saplingID)
					.setBlockName("sapling");

		final int catTailID = Config.getOrCreateBlockIdProperty(
				"cattail.id", 158);
		if (catTailID != 0)
			ExtrabiomesBlock.catTail = new BlockCatTail(catTailID)
					.setBlockName("cattail");

		final int leafPileID = Config.getOrCreateBlockIdProperty(
				"leafpile.id", 159);
		if (leafPileID != 0)
			ExtrabiomesBlock.leafPile = new BlockLeafPile(leafPileID)
					.setBlockName("leafpile");

		Proxy.registerBlock(ExtrabiomesBlock.crackedSand);
		Proxy.registerBlock(ExtrabiomesBlock.leafPile);
		Proxy.registerBlock(ExtrabiomesBlock.quickSand);

		Proxy.registerBlock(ExtrabiomesBlock.redRock,
				extrabiomes.MultiItemBlock.class);
		Proxy.registerBlock(ExtrabiomesBlock.autumnLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Proxy.registerBlock(ExtrabiomesBlock.catTail,
				extrabiomes.ItemCatTail.class);
		Proxy.registerBlock(ExtrabiomesBlock.flower,
				extrabiomes.MultiItemBlock.class);
		Proxy.registerBlock(ExtrabiomesBlock.grass,
				extrabiomes.MultiItemBlock.class);
		Proxy.registerBlock(ExtrabiomesBlock.greenLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Proxy.registerBlock(ExtrabiomesBlock.sapling,
				extrabiomes.MultiItemBlock.class);
	}

}
