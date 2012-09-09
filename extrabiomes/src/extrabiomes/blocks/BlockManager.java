/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static extrabiomes.utility.ConfigSetting.IntegerSettingType.BLOCK_ID;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

import extrabiomes.CommonProxy;
import extrabiomes.Extrabiomes;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.utility.ConfigSetting;

public class BlockManager {

	public static void addNames() {

		final CommonProxy proxy = Extrabiomes.proxy;

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
	boolean					enableClassicMode	= false;

	@ConfigSetting(integerType = BLOCK_ID)
	int						autumnLeaves		= 150;

	@ConfigSetting(integerType = BLOCK_ID)
	int						greenLeaves			= 155;

	@ConfigSetting(integerType = BLOCK_ID)
	int						sapling				= 159;

	private static boolean	blocksRegistered	= false;

	public void registerBlocks() {
		if (blocksRegistered) return;
		blocksRegistered = true;

		if (enableClassicMode) return;

		if (autumnLeaves > 0)
			ExtrabiomesBlock.autumnLeaves = Optional
					.of(new BlockAutumnLeaves(autumnLeaves)
							.setBlockName("autumnleaves"));

		if (greenLeaves > 0)
			ExtrabiomesBlock.greenLeaves = Optional
					.of(new BlockGreenLeaves(greenLeaves)
							.setBlockName("greenleaves"));

		if (sapling > 0)
			ExtrabiomesBlock.sapling = Optional
					.of(new BlockCustomSapling(sapling)
							.setBlockName("sapling"));

		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.autumnLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.greenLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Extrabiomes.proxy.registerBlock(ExtrabiomesBlock.sapling,
				extrabiomes.utility.MultiItemBlock.class);
	}

}
