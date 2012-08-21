/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugins;

import java.lang.reflect.Method;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.IPlugin;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.blocks.BlockRedRock;

public enum PluginEE implements IPlugin {
	INSTANCE;

	public static Method	setEMC;
	public static Method	getEMC;

	public static int getEMC(int id) {
		return getEMC(id, 0);
	}

	public static int getEMC(int id, int metadata) {
		final ItemStack itemstack = new ItemStack(id, 1, metadata);
		return getEMC(itemstack);
	}

	public static int getEMC(ItemStack itemstack) {
		final Object arglist[] = new Object[1];
		arglist[0] = itemstack;
		Object retobj;
		try {
			retobj = getEMC.invoke(null, arglist);
			return ((Integer) retobj).intValue();
		} catch (final Exception e) {
			ModLoader.getLogger().fine("Could not get EMC.");
			return 0;
		}
	}

	public static void setEMC(int id, int emcValue) {
		setEMC(id, 0, emcValue);
	}

	public static void setEMC(int id, int metadata, int emcValue) {
		final Object arglist[] = new Object[3];
		arglist[0] = new Integer(id);
		arglist[1] = new Integer(metadata);
		arglist[2] = new Integer(emcValue);
		try {
			setEMC.invoke(null, arglist);
		} catch (final Exception e) {
			ModLoader.getLogger().fine("Could not set EMC.");
		}
	}

	public static void setEMC(ItemStack itemstack, int emcValue) {
		setEMC(itemstack.itemID, itemstack.getItemDamage(), emcValue);
	}

	static private void setEMCValues() {
		final int emcLeaves = getEMC(Block.leaves.blockID);
		final int emcCrackedSand = getEMC(Block.sand.blockID);
		final int emcFlower = getEMC(Block.plantRed.blockID);
		final int emcGrass = getEMC(Block.tallGrass.blockID);
		final int emcQuickSand = getEMC(Block.sand.blockID);
		final int emcSapling = getEMC(Block.sapling.blockID);
		final int emcClay = getEMC(new ItemStack(Item.clay));
		final int emcWater = getEMC(new ItemStack(Item.bucketWater))
				- getEMC(new ItemStack(Item.bucketEmpty));;
		final int emcRedRock = emcClay * 4 - emcWater * 3;

		if (ExtrabiomesBlock.autumnLeaves != null && emcLeaves > 0) {
			setEMC(ExtrabiomesBlock.autumnLeaves.blockID,
					BlockAutumnLeaves.metaBrown, emcLeaves);
			setEMC(ExtrabiomesBlock.autumnLeaves.blockID,
					BlockAutumnLeaves.metaOrange, emcLeaves);
			setEMC(ExtrabiomesBlock.autumnLeaves.blockID,
					BlockAutumnLeaves.metaPurple, emcLeaves);
			setEMC(ExtrabiomesBlock.autumnLeaves.blockID,
					BlockAutumnLeaves.metaYellow, emcLeaves);
		}
		if (ExtrabiomesBlock.greenLeaves != null && emcLeaves > 0) {
			setEMC(ExtrabiomesBlock.greenLeaves.blockID,
					BlockGreenLeaves.metaFir, emcLeaves);
			setEMC(ExtrabiomesBlock.greenLeaves.blockID,
					BlockGreenLeaves.metaRedwood, emcLeaves);
			setEMC(ExtrabiomesBlock.greenLeaves.blockID,
					BlockGreenLeaves.metaAcacia, emcLeaves);
		}
		if (ExtrabiomesBlock.catTail != null && emcGrass > 0)
			setEMC(new ItemStack(ExtrabiomesBlock.catTail), emcGrass);
		if (ExtrabiomesBlock.crackedSand != null && emcCrackedSand > 0)
			setEMC(ExtrabiomesBlock.crackedSand.blockID, emcCrackedSand);
		if (ExtrabiomesBlock.flower != null && emcFlower > 0) {
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaAutumnShrub, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaHydrangea, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaOrange, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaPurple, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaRoot, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaTinyCactus, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaToadstool, emcLeaves);
			setEMC(ExtrabiomesBlock.flower.blockID,
					BlockCustomFlower.metaWhite, emcLeaves);
		}
		if (ExtrabiomesBlock.grass != null && emcGrass > 0) {
			setEMC(ExtrabiomesBlock.grass.blockID,
					BlockCustomTallGrass.metaBrown, emcGrass);
			setEMC(ExtrabiomesBlock.grass.blockID,
					BlockCustomTallGrass.metaDead, emcGrass);
			setEMC(ExtrabiomesBlock.grass.blockID,
					BlockCustomTallGrass.metaDeadTall, emcGrass);
			setEMC(ExtrabiomesBlock.grass.blockID,
					BlockCustomTallGrass.metaDeadYellow, emcGrass);
			setEMC(ExtrabiomesBlock.grass.blockID,
					BlockCustomTallGrass.metaShortBrown, emcGrass);
		}
		if (ExtrabiomesBlock.quickSand != null && emcQuickSand > 0)
			setEMC(ExtrabiomesBlock.quickSand.blockID, emcQuickSand);
		if (ExtrabiomesBlock.redRock != null && emcRedRock > 0) {
			setEMC(ExtrabiomesBlock.redRock.blockID,
					BlockRedRock.metaRedCobble, emcRedRock);
			setEMC(ExtrabiomesBlock.redRock.blockID,
					BlockRedRock.metaRedRock, emcRedRock);
			setEMC(ExtrabiomesBlock.redRock.blockID,
					BlockRedRock.metaRedRockBrick, emcRedRock * 4);
		}
		if (ExtrabiomesBlock.sapling != null && emcSapling > 0) {
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaBrown, emcGrass);
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaOrange, emcGrass);
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaPurple, emcGrass);
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaYellow, emcGrass);
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaFir, emcGrass);
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaRedWood, emcGrass);
			setEMC(ExtrabiomesBlock.sapling.blockID,
					BlockCustomSapling.metaAcacia, emcGrass);
		}
	}

	@Override
	public String getName() {
		return "EquivalentExchange";
	}

	@Override
	public void inject() {
		Class proxy;
		try {
			proxy = Class.forName("EEProxy");
			final Class partypes[] = { Integer.TYPE, Integer.TYPE,
					Integer.TYPE };
			setEMC = proxy.getMethod("setEMC", partypes);

			final Class partypes2[] = { ItemStack.class };
			getEMC = proxy.getMethod("getEMC", partypes2);
		} catch (final Exception e) {
			ModLoader.getLogger().fine("Could not find EE proxy.");
			return;
		}
		setEMCValues();
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_EE");
	}

}
