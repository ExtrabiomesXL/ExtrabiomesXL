/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static extrabiomes.utility.ConfigSetting.IntegerSettingType.BLOCK_ID;
import static extrabiomes.utility.ConfigSetting.IntegerSettingType.RESTRICTED_BLOCK_ID;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

import extrabiomes.CommonProxy;
import extrabiomes.Extrabiomes;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.plugin.crackedsand.BlockCrackedSand;
import extrabiomes.utility.ConfigSetting;


public class BlockManager {

	public static void addNames() {

		final CommonProxy proxy = Extrabiomes.proxy;

		if (ExtrabiomesBlock.catTail.isPresent())
			proxy.addName(ExtrabiomesBlock.catTail.get(),
					"Cat Tail");

		if (ExtrabiomesBlock.leafPile.isPresent())
			proxy.addName(ExtrabiomesBlock.leafPile.get(), "Leaf Pile");

		if (ExtrabiomesBlock.redRock.isPresent()) {
			final Block block = ExtrabiomesBlock.redRock.get();
			proxy.addName(new ItemStack(block, 1,
					BlockRedRock.metaRedRock), "Red Rock");
			proxy.addName(new ItemStack(block, 1,
					BlockRedRock.metaRedCobble), "Red Cobblestone");
			proxy.addName(new ItemStack(block, 1,
					BlockRedRock.metaRedRockBrick), "Red Rock Brick");
		}
		if (ExtrabiomesBlock.autumnLeaves.isPresent()) {
			final Block block = ExtrabiomesBlock.autumnLeaves.get();
			proxy.addName(new ItemStack(block, 1, 0),
					"Brown Autumn Leaves");
			proxy.addName(new ItemStack(block, 1, 1),
					"Orange Autumn Leaves");
			proxy.addName(new ItemStack(block, 1, 2),
					"Purple Autumn Leaves");
			proxy.addName(new ItemStack(block, 1, 3),
					"Yellow Autumn Leaves");
		}

		if (ExtrabiomesBlock.flower.isPresent()) {
			final Block block = ExtrabiomesBlock.flower.get();
			proxy.addName(new ItemStack(block, 1, 0), "Autumn Shrub");
			proxy.addName(new ItemStack(block, 1, 1), "Hydrangea");
			proxy.addName(new ItemStack(block, 1, 2), "Orange Flower");
			proxy.addName(new ItemStack(block, 1, 3), "Purple Flower");
			proxy.addName(new ItemStack(block, 1, 4), "Tiny Cactus");
			proxy.addName(new ItemStack(block, 1, 5), "Root");
			proxy.addName(new ItemStack(block, 1, 6), "Toad Stool");
			proxy.addName(new ItemStack(block, 1, 7), "White Flower");
		}

		if (ExtrabiomesBlock.grass.isPresent()) {
			final Block block = ExtrabiomesBlock.grass.get();
			proxy.addName(new ItemStack(block, 1, 0), "Brown Grass");
			proxy.addName(new ItemStack(block, 1, 1),
					"Short Brown Grass");
			proxy.addName(new ItemStack(block, 1, 2), "Dead Grass");
			proxy.addName(new ItemStack(block, 1, 3), "Tall Dead Grass");
			proxy.addName(new ItemStack(block, 1, 4),
					"Yellow Dead Grass");
		}

		if (ExtrabiomesBlock.greenLeaves.isPresent()) {
			final Block block = ExtrabiomesBlock.greenLeaves.get();
			proxy.addName(new ItemStack(block, 1, 0), "Fir Leaves");
			proxy.addName(new ItemStack(block, 1, 1), "Redwood Leaves");
			proxy.addName(new ItemStack(block, 1, 2), "Acacia Leaves");
		}

		if (ExtrabiomesBlock.sapling.isPresent()) {
			final Block block = ExtrabiomesBlock.sapling.get();
			proxy.addName(new ItemStack(block, 1, 0),
					"Brown Autumn Sapling");
			proxy.addName(new ItemStack(block, 1, 1),
					"Orange Autumn Sapling");
			proxy.addName(new ItemStack(block, 1, 2),
					"Purple Autumn Sapling");
			proxy.addName(new ItemStack(block, 1, 3),
					"Yellow Autumn Sapling");
			proxy.addName(new ItemStack(block, 1, 4), "Fir Sapling");
			proxy.addName(new ItemStack(block, 1, 5), "Redwood Sapling");
			proxy.addName(new ItemStack(block, 1, 6), "Acacia Sapling");
		}
	}

	@ConfigSetting(key = "classicmode.enable", comment = "Set to true to disable all custom blocks.")
	boolean					enableClassicMode						= false;

	@ConfigSetting(integerType = BLOCK_ID)
	int						autumnLeaves							= 150;

	@ConfigSetting(integerType = BLOCK_ID)
	int						catTail									= 151;

	@ConfigSetting(integerType = BLOCK_ID)
	int						flower									= 153;

	@ConfigSetting(integerType = BLOCK_ID)
	int						grass									= 154;

	@ConfigSetting(integerType = BLOCK_ID)
	int						greenLeaves								= 155;

	@ConfigSetting(integerType = BLOCK_ID)
	int						leafPile								= 156;

	@ConfigSetting(integerType = RESTRICTED_BLOCK_ID, comment = "RedRock is used in terrain generation. Its id must be less than 256.")
	int						redRock									= 158;

	@ConfigSetting(integerType = BLOCK_ID)
	int						sapling									= 159;

	private static boolean	blocksRegistered						= false;

	public void registerBlocks() {
		if (blocksRegistered) return;
		blocksRegistered = true;

		if (enableClassicMode) return;

		if (redRock > 0) {
			ExtrabiomesBlock.redRock = Optional.of(new BlockRedRock(
					redRock).setBlockName("redrock"));
			Extrabiomes.proxy.setBlockHarvestLevel(
					ExtrabiomesBlock.redRock.get(), "pickaxe", 0);
		}

		if (autumnLeaves > 0)
			ExtrabiomesBlock.autumnLeaves = Optional
					.of(new BlockAutumnLeaves(autumnLeaves)
							.setBlockName("autumnleaves"));

		if (greenLeaves > 0)
			ExtrabiomesBlock.greenLeaves = Optional
					.of(new BlockGreenLeaves(greenLeaves)
							.setBlockName("greenleaves"));

		if (flower > 0)
			ExtrabiomesBlock.flower = Optional
					.of(new BlockCustomFlower(flower)
							.setBlockName("flower"));

		if (grass > 0)
			ExtrabiomesBlock.grass = Optional
					.of(new BlockCustomTallGrass(grass)
							.setBlockName("grass"));

		if (sapling > 0)
			ExtrabiomesBlock.sapling = Optional
					.of(new BlockCustomSapling(sapling)
							.setBlockName("sapling"));

		if (catTail > 0)
			ExtrabiomesBlock.catTail = Optional.of(new BlockCatTail(
					catTail).setBlockName("cattail"));

		if (leafPile > 0)
			ExtrabiomesBlock.leafPile = Optional.of(new BlockLeafPile(
					leafPile).setBlockName("leafpile"));

		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.leafPile);

		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.redRock,
				extrabiomes.MultiItemBlock.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.autumnLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.catTail,
				extrabiomes.items.ItemCatTail.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.flower,
				extrabiomes.MultiItemBlock.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.grass,
				extrabiomes.MultiItemBlock.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.greenLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.sapling,
				extrabiomes.MultiItemBlock.class);
	}

}
