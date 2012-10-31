/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.events.BlockActiveEvent.AcaciaStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.CrackedSandActiveEvent;
import extrabiomes.events.BlockActiveEvent.FirStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.FlowerActiveEvent;
import extrabiomes.events.BlockActiveEvent.LeafPileActiveEvent;
import extrabiomes.events.BlockActiveEvent.LogActiveEvent;
import extrabiomes.events.BlockActiveEvent.PlankActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedCobbleStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockBrickStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedwoodStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.WallActiveEvent;
import extrabiomes.events.BlockActiveEvent.WoodSlabActiveEvent;
import extrabiomes.module.fabrica.block.BlockCustomWall;
import extrabiomes.module.fabrica.block.BlockCustomWood;
import extrabiomes.module.fabrica.block.BlockCustomWoodSlab;
import extrabiomes.module.fabrica.block.BlockRedRockSlab;
import extrabiomes.module.summa.block.BlockCustomFlower;
import extrabiomes.module.summa.block.BlockCustomLog;
import extrabiomes.module.summa.block.BlockQuarterLog;
import extrabiomes.module.summa.block.BlockRedRock;
import extrabiomes.proxy.CommonProxy;

public class RecipeManager {

	private Optional<ItemStack>		plankAcaciaItem		= Optional.absent();
	private Optional<ItemStack>		plankFirItem		= Optional.absent();
	private Optional<ItemStack>		plankRedwoodItem	= Optional.absent();
	private Optional<ItemStack>		redRockItem			= Optional.absent();
	private Optional<ItemStack>		redCobbleItem		= Optional.absent();
	private Optional<ItemStack>		redRockBrickItem	= Optional.absent();

	private final ItemStack			charcoal			= new ItemStack(Item.coal, 1, 1);

	private final List<ItemStack>	acaciaLogs			= new ArrayList<ItemStack>();
	private final List<ItemStack>	firLogs				= new ArrayList<ItemStack>();
	private final List<ItemStack>	oakLogs				= new ArrayList<ItemStack>();
	private final List<ItemStack>	redwoodLogs			= new ArrayList<ItemStack>();

	@ForgeSubscribe
	public void acaciaStairsRecipeHandler(AcaciaStairsActiveEvent event)
	{
		if (plankAcaciaItem.isPresent())
			addStairsRecipe(plankAcaciaItem.get(), event.block);
	}

	private void addStairsRecipe(ItemStack source, Block target) {
		final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
				target, 4), new String[] { "r  ", "rr ", "rrr" }, 'r',
				source);
		Extrabiomes.proxy.addRecipe(recipe);
	}

	@ForgeSubscribe
	public void crackedSandRecipeHandler(CrackedSandActiveEvent event) {
		final IRecipe recipe = new ShapelessOreRecipe(Block.sand,
				event.block, Item.bucketWater);
		Extrabiomes.proxy.addRecipe(recipe);
	}

	@ForgeSubscribe
	public void firStairsRecipeHandler(FirStairsActiveEvent event) {
		if (plankFirItem.isPresent())
			addStairsRecipe(plankFirItem.get(), event.block);
	}

	@ForgeSubscribe
	public void flowerRecipeHandler(FlowerActiveEvent event) {
		final CommonProxy proxy = Extrabiomes.proxy;

		final ItemStack dyeLightBlue = new ItemStack(Item.dyePowder, 1,
				12);
		final ItemStack dyeLightGray = new ItemStack(Item.dyePowder, 1,
				7);
		final ItemStack dyeOrange = new ItemStack(Item.dyePowder, 1, 14);
		final ItemStack dyePurple = new ItemStack(Item.dyePowder, 1, 5);

		IRecipe recipe = new ShapelessOreRecipe(dyeLightBlue,
				new ItemStack(event.block, 1,
						BlockCustomFlower.BlockType.HYDRANGEA
								.metadata()));
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyeOrange, new ItemStack(
				event.block, 1,
				BlockCustomFlower.BlockType.ORANGE.metadata()));
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyePurple, new ItemStack(
				event.block, 1,
				BlockCustomFlower.BlockType.PURPLE.metadata()));
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyeLightGray, new ItemStack(
				event.block, 1,
				BlockCustomFlower.BlockType.WHITE.metadata()));
		proxy.addRecipe(recipe);

		final ItemStack toadstool = new ItemStack(event.block, 1,
				BlockCustomFlower.BlockType.TOADSTOOL.metadata());

		recipe = new ShapelessOreRecipe(Item.bowlSoup,
				Block.mushroomBrown, toadstool, toadstool,
				Item.bowlEmpty);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(Item.bowlSoup,
				Block.mushroomRed, toadstool, toadstool, Item.bowlEmpty);
		proxy.addRecipe(recipe);
	}

	@ForgeSubscribe
	public void leafPileRecipeHandler(LeafPileActiveEvent event) {
		final IRecipe recipe = new ShapedOreRecipe(Block.leaves,
				new String[] { "lll", "lll", "lll" }, 'l', event.block);
		Extrabiomes.proxy.addRecipe(recipe);
	}

	@ForgeSubscribe
	public void logRecipeHandler(LogActiveEvent event) {

		if (event.block instanceof BlockCustomLog) {
			for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(event.block.blockID,
						type.metadata(), charcoal, 0.15F);
			acaciaLogs.add(new ItemStack(event.block, 1,
					BlockCustomLog.BlockType.ACACIA.metadata()));
			firLogs.add(new ItemStack(event.block, 1,
					BlockCustomLog.BlockType.FIR.metadata()));
		} else {
			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				Extrabiomes.proxy.addSmelting(event.block.blockID,
						type.metadata(), charcoal, 0.15F);
			firLogs.add(new ItemStack(event.block, 1,
					BlockQuarterLog.BlockType.FIR.metadata()));
			oakLogs.add(new ItemStack(event.block, 1,
					BlockQuarterLog.BlockType.OAK.metadata()));
			redwoodLogs.add(new ItemStack(event.block, 1,
					BlockQuarterLog.BlockType.REDWOOD.metadata()));
		}
	}

	@ForgeSubscribe
	public void plankRecipeHandler(PlankActiveEvent event) {
		ItemStack planks = new ItemStack(event.block, 4,
				BlockCustomWood.BlockType.ACACIA.metadata());
		for (final ItemStack itemstack : acaciaLogs) {
			final IRecipe recipe = new ShapelessOreRecipe(planks,
					itemstack);
			Extrabiomes.proxy.addRecipe(recipe);
		}

		plankAcaciaItem = Optional.of(new ItemStack(event.block, 1,
				BlockCustomWood.BlockType.ACACIA.metadata()));

		planks = new ItemStack(event.block, 4,
				BlockCustomWood.BlockType.FIR.metadata());
		for (final ItemStack itemstack : firLogs) {
			final IRecipe recipe = new ShapelessOreRecipe(planks,
					itemstack);
			Extrabiomes.proxy.addRecipe(recipe);
		}

		plankFirItem = Optional.of(new ItemStack(event.block, 1,
				BlockCustomWood.BlockType.FIR.metadata()));

		planks = new ItemStack(Block.planks, 4);
		for (final ItemStack itemstack : oakLogs) {
			final IRecipe recipe = new ShapelessOreRecipe(planks,
					itemstack);
			Extrabiomes.proxy.addRecipe(recipe);
		}

		planks = new ItemStack(event.block, 4,
				BlockCustomWood.BlockType.REDWOOD.metadata());
		for (final ItemStack itemstack : redwoodLogs) {
			final IRecipe recipe = new ShapelessOreRecipe(planks,
					itemstack);
			Extrabiomes.proxy.addRecipe(recipe);
		}

		plankRedwoodItem = Optional.of(new ItemStack(event.block, 1,
				BlockCustomWood.BlockType.REDWOOD.metadata()));
	}

	@ForgeSubscribe
	public void redCobbleStairsRecipeHandler(
			RedCobbleStairsActiveEvent event)
	{
		if (redCobbleItem.isPresent())
			addStairsRecipe(redCobbleItem.get(), event.block);
	}

	@ForgeSubscribe
	public void redRockBrickStairsRecipeHandler(
			RedRockBrickStairsActiveEvent event)
	{
		if (redRockBrickItem.isPresent())
			addStairsRecipe(redRockBrickItem.get(), event.block);
	}

	@ForgeSubscribe
	public void redRockRecipeHandler(RedRockActiveEvent event) {
		final CommonProxy proxy = Extrabiomes.proxy;

		redRockItem = Optional.of(new ItemStack(event.block, 1,
				BlockRedRock.BlockType.RED_ROCK.metadata()));
		redCobbleItem = Optional.of(new ItemStack(event.block, 1,
				BlockRedRock.BlockType.RED_COBBLE.metadata()));
		redRockBrickItem = Optional.of(new ItemStack(event.block, 1,
				BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()));

		IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
				Item.clay, 4), redCobbleItem.get(), Item.bucketWater,
				Item.bucketWater, Item.bucketWater);
		proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(event.block, 4,
				BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()),
				new String[] { "rr", "rr" }, 'r', redRockItem.get());
		proxy.addRecipe(recipe);

		proxy.addSmelting(event.block.blockID,
				BlockRedRock.BlockType.RED_COBBLE.metadata(),
				redRockItem.get(), 0.1F);
	}

	@ForgeSubscribe
	public void redRockSlabRecipeHandler(RedRockSlabActiveEvent event) {
		final CommonProxy proxy = Extrabiomes.proxy;

		if (redRockItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockRedRockSlab.BlockType.RED_ROCK.metadata()),
					new String[] { "rrr" }, 'r', redRockItem.get());
			proxy.addRecipe(recipe);
		}

		if (redCobbleItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockRedRockSlab.BlockType.RED_COBBLE.metadata()),
					new String[] { "rrr" }, 'r', redCobbleItem.get());
			proxy.addRecipe(recipe);
		}

		if (redRockBrickItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockRedRockSlab.BlockType.RED_ROCK_BRICK
							.metadata()), new String[] { "rrr" }, 'r',
					redRockBrickItem.get());
			proxy.addRecipe(recipe);
		}
	}

	@ForgeSubscribe
	public void redwoodStairsRecipeHandler(
			RedwoodStairsActiveEvent event)
	{
		if (plankRedwoodItem.isPresent())
			addStairsRecipe(plankRedwoodItem.get(), event.block);
	}

	@ForgeSubscribe
	public void wallRecipeHandler(WallActiveEvent event) {

		if (redCobbleItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockCustomWall.BlockType.RED_COBBLE.metadata()),
					new String[] { "ppp", "ppp" }, 'p',
					redCobbleItem.get());
			Extrabiomes.proxy.addRecipe(recipe);
		}
	}

	@ForgeSubscribe
	public void woodSlabRecipeHandler(WoodSlabActiveEvent event) {

		if (plankAcaciaItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockCustomWoodSlab.BlockType.ACACIA.metadata()),
					new String[] { "ppp" }, 'p', plankAcaciaItem.get());
			Extrabiomes.proxy.addRecipe(recipe);
		}

		if (plankFirItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockCustomWoodSlab.BlockType.FIR.metadata()),
					new String[] { "ppp" }, 'p', plankFirItem.get());
			Extrabiomes.proxy.addRecipe(recipe);
		}

		if (plankRedwoodItem.isPresent()) {
			final IRecipe recipe = new ShapedOreRecipe(new ItemStack(
					event.block, 6,
					BlockCustomWoodSlab.BlockType.REDWOOD.metadata()),
					new String[] { "ppp" }, 'p', plankRedwoodItem.get());
			Extrabiomes.proxy.addRecipe(recipe);
		}
	}
}
