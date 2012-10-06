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

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.module.fabrica.block.BlockRedRockSlab;
import extrabiomes.module.summa.Summa;
import extrabiomes.module.summa.block.BlockCustomLog;
import extrabiomes.module.summa.block.BlockManager;
import extrabiomes.module.summa.block.BlockQuarterLog;
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

	private static void addLogSmelting() {

		final ItemStack charcoal = new ItemStack(Item.coal, 1, 1);

		if (BlockManager.CUSTOMLOG.getBlock().isPresent())
			for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(BlockManager.CUSTOMLOG
						.getBlock().get().blockID, type.metadata(),
						charcoal);

		if (BlockManager.QUARTERLOG0.getBlock().isPresent())
			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(BlockManager.QUARTERLOG0
						.getBlock().get().blockID, type.metadata(),
						charcoal);

		if (BlockManager.QUARTERLOG1.getBlock().isPresent())
			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(BlockManager.QUARTERLOG1
						.getBlock().get().blockID, type.metadata(),
						charcoal);

		if (BlockManager.QUARTERLOG2.getBlock().isPresent())
			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(BlockManager.QUARTERLOG2
						.getBlock().get().blockID, type.metadata(),
						charcoal);

		if (BlockManager.QUARTERLOG3.getBlock().isPresent())
			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(BlockManager.QUARTERLOG3
						.getBlock().get().blockID, type.metadata(),
						charcoal);
	}

	private static void addLogTurnerRecipe() {
		final Optional<Item> logTurner = Summa.getLogTurner();

		if (logTurner.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(logTurner.get(),
					new String[] { "ss", " s", "ss" }, 's', Item.stick);
			Extrabiomes.proxy.addRecipe(recipe);
		}
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

		final Optional<? extends Block> slab = extrabiomes.module.fabrica.block.BlockManager.REDROCKSLAB
				.getBlock();

		if (slab.isPresent()) {
			recipe = new ShapedOreRecipe(new ItemStack(slab.get(), 6,
					BlockRedRockSlab.BlockType.RED_ROCK.metadata()),
					new String[] { "rrr" }, 'r', "rockRed");
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapedOreRecipe(new ItemStack(slab.get(), 6,
					BlockRedRockSlab.BlockType.RED_COBBLE.metadata()),
					new String[] { "rrr" }, 'r', "cobbleRed");
			Extrabiomes.proxy.addRecipe(recipe);

			recipe = new ShapedOreRecipe(new ItemStack(slab.get(), 6,
					BlockRedRockSlab.BlockType.RED_ROCK_BRICK
							.metadata()), new String[] { "rrr" }, 'r',
					"brickRedRock");
			Extrabiomes.proxy.addRecipe(recipe);
		}

		Optional<? extends Block> stairs = extrabiomes.module.fabrica.block.BlockManager.REDCOBBLESTAIRS
				.getBlock();

		if (stairs.isPresent()) {
			recipe = new ShapedOreRecipe(
					new ItemStack(stairs.get(), 4), new String[] {
							"r  ", "rr ", "rrr" }, 'r', "cobbleRed");
			Extrabiomes.proxy.addRecipe(recipe);
		}

		stairs = extrabiomes.module.fabrica.block.BlockManager.REDROCKBRICKSTAIRS
				.getBlock();

		if (stairs.isPresent()) {
			recipe = new ShapedOreRecipe(
					new ItemStack(stairs.get(), 4), new String[] {
							"r  ", "rr ", "rrr" }, 'r', "brickRedRock");
			Extrabiomes.proxy.addRecipe(recipe);
		}

	}

	public static void init() {
		addCrackedSandRecipes();
		addRedRockRecipes();
		addFlowerRecipes();
		addLeafPileRecipes();
		addLogTurnerRecipe();
		addLogSmelting();
	}
}
