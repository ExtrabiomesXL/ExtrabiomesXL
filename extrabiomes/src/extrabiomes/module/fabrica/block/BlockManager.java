/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.Locale;

import net.minecraft.src.Block;
import net.minecraft.src.BlockHalfSlab;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.Stuff;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.events.BlockActiveEvent.AcaciaStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.FirStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.PlankActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedCobbleStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockBrickStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedRockSlabActiveEvent;
import extrabiomes.events.BlockActiveEvent.RedwoodStairsActiveEvent;
import extrabiomes.events.BlockActiveEvent.WallActiveEvent;
import extrabiomes.events.BlockActiveEvent.WoodSlabActiveEvent;
import extrabiomes.module.summa.block.BlockRedRock;
import extrabiomes.proxy.CommonProxy;

public enum BlockManager {
	PLANKS {
		@Override
		protected void create() {
			Stuff.planks = Optional.of(new BlockCustomWood(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.planks.get();

			thisBlock.setBlockName("extrabiomes.planks");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);
			for (final BlockCustomWood.BlockType blockType : BlockCustomWood.BlockType
					.values())
			{
				final ItemStack itemstack = new ItemStack(thisBlock, 1,
						blockType.metadata());
				proxy.addName(itemstack, blockType.itemName());
				proxy.registerOre("plankWood", itemstack);
			}

			Extrabiomes.postInitEvent(new PlankActiveEvent(thisBlock));
		}
	},
	WOODSLAB {
		@Override
		protected void create() {
			Stuff.slabWood = Optional.of(new BlockCustomWoodSlab(
					blockID, false));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.slabWood.get();

			thisBlock.setBlockName("extrabiomes.woodslab");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);

			proxy.registerFuelHandler(new FuelHandlerWoodSlabs(
					thisBlock.blockID));
			Extrabiomes
					.postInitEvent(new WoodSlabActiveEvent(thisBlock));
		}
	},
	DOUBLEWOODSLAB {
		@Override
		protected void create() {
			Stuff.slabWoodDouble = Optional.of(new BlockCustomWoodSlab(
					blockID, true));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.slabWoodDouble.get();

			thisBlock.setBlockName("extrabiomes.woodslab");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
			ItemWoodSlab.setSlabs((BlockHalfSlab) Stuff.slabWood.get(),
					(BlockHalfSlab) Stuff.slabWoodDouble.get());
			proxy.registerBlock(Stuff.slabWood.get(),
					extrabiomes.module.fabrica.block.ItemWoodSlab.class);
			proxy.registerBlock(thisBlock,
					extrabiomes.module.fabrica.block.ItemWoodSlab.class);
			for (final BlockCustomWoodSlab.BlockType blockType : BlockCustomWoodSlab.BlockType
					.values())
			{
				final ItemStack itemstack = new ItemStack(
						Stuff.slabWood.get(), 1, blockType.metadata());
				proxy.addName(itemstack, blockType.itemName());
				proxy.registerOre("slabWood", itemstack);
			}

			new ItemStack(Stuff.slabWood.get(), 1,
					BlockCustomWoodSlab.BlockType.FIR.metadata());
			new ItemStack(Stuff.slabWood.get(), 1,
					BlockCustomWoodSlab.BlockType.REDWOOD.metadata());
			new ItemStack(Stuff.slabWood.get(), 1,
					BlockCustomWoodSlab.BlockType.ACACIA.metadata());
		}
	},
	REDWOODSTAIRS {
		@Override
		protected void create() {
			Stuff.stairsRedwood = Optional.of(new BlockWoodStairs(
					blockID, Stuff.planks.get(),
					BlockCustomWood.BlockType.REDWOOD.metadata()));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.stairsRedwood.get();

			thisBlock.setBlockName("extrabiomes.stairsRedwood");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
			proxy.registerBlock(thisBlock);

			proxy.addName(thisBlock, "Redwood Stairs");
			proxy.registerOre("stairWood", thisBlock);
			Extrabiomes.postInitEvent(new RedwoodStairsActiveEvent(
					thisBlock));
		}
	},
	FIRSTAIRS {
		@Override
		protected void create() {
			Stuff.stairsFir = Optional.of(new BlockWoodStairs(blockID,
					Stuff.planks.get(), BlockCustomWood.BlockType.FIR
							.metadata()));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.stairsFir.get();

			thisBlock.setBlockName("extrabiomes.stairsFir");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
			proxy.registerBlock(thisBlock);

			proxy.addName(thisBlock, "Fir Wood Stairs");
			proxy.registerOre("stairWood", thisBlock);
			Extrabiomes.postInitEvent(new FirStairsActiveEvent(
					thisBlock));
		}
	},
	ACACIASTAIRS {
		@Override
		protected void create() {
			Stuff.stairsAcacia = Optional.of(new BlockWoodStairs(
					blockID, Stuff.planks.get(),
					BlockCustomWood.BlockType.ACACIA.metadata()));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.stairsAcacia.get();

			thisBlock.setBlockName("extrabiomes.stairAcacia");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
			proxy.registerBlock(thisBlock);

			proxy.addName(thisBlock, "Acacia Wood Stairs");
			proxy.registerOre("stairWood", thisBlock);
			Extrabiomes.postInitEvent(new AcaciaStairsActiveEvent(
					thisBlock));
		}
	},
	REDROCKSLAB {
		@Override
		protected void create() {
			Stuff.slabRedRock = Optional.of(new BlockRedRockSlab(
					blockID, false));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.slabRedRock.get();

			thisBlock.setBlockName("extrabiomes.redrockslab");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);

			Extrabiomes.postInitEvent(new RedRockSlabActiveEvent(
					thisBlock));
		}
	},
	DOUBLEREDROCKSLAB {
		@Override
		protected void create() {
			Stuff.slabRedRockDouble = Optional.of(new BlockRedRockSlab(
					blockID, true));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.slabRedRockDouble.get();

			thisBlock.setBlockName("extrabiomes.redrockslab");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
			ItemRedRockSlab.setSlabs(
					(BlockHalfSlab) Stuff.slabRedRock.get(),
					(BlockHalfSlab) Stuff.slabRedRockDouble.get());
			proxy.registerBlock(
					Stuff.slabRedRock.get(),
					extrabiomes.module.fabrica.block.ItemRedRockSlab.class);
			proxy.registerBlock(
					thisBlock,
					extrabiomes.module.fabrica.block.ItemRedRockSlab.class);
			for (final BlockRedRockSlab.BlockType blockType : BlockRedRockSlab.BlockType
					.values())
			{
				proxy.addName(
						new ItemStack(thisBlock, 1, blockType
								.metadata()), blockType.itemName());
				proxy.addName(new ItemStack(Stuff.slabRedRock.get(), 1,
						blockType.metadata()), blockType.itemName());
			}
		}
	},
	REDCOBBLESTAIRS {
		@Override
		protected void create() {
			Stuff.stairsRedCobble = Optional.of(new BlockCustomStairs(
					blockID, Stuff.redRock.get(),
					BlockRedRock.BlockType.RED_COBBLE.metadata()));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.stairsRedCobble.get();

			thisBlock.setBlockName("extrabiomes.stairsRedCobble");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
			proxy.registerBlock(thisBlock);

			proxy.addName(thisBlock, "Red Cobble Stairs");

			Extrabiomes.postInitEvent(new RedCobbleStairsActiveEvent(
					thisBlock));
		}
	},
	REDROCKBRICKSTAIRS {
		@Override
		protected void create() {
			Stuff.stairsRedRockBrick = Optional
					.of(new BlockCustomStairs(blockID, Stuff.redRock
							.get(),
							BlockRedRock.BlockType.RED_ROCK_BRICK
									.metadata()));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.stairsRedRockBrick.get();

			thisBlock.setBlockName("extrabiomes.stairsRedRockBrick");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
			proxy.registerBlock(thisBlock);

			proxy.addName(thisBlock, "Red Rock Brick Stairs");

			Extrabiomes
					.postInitEvent(new RedRockBrickStairsActiveEvent(
							thisBlock));
		}
	},
	WALL {
		@Override
		protected void create() {
			Stuff.wall = Optional.of(new BlockCustomWall(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = Stuff.wall.get();

			thisBlock.setBlockName("extrabiomes.wall");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);
			for (final BlockCustomWall.BlockType blockType : BlockCustomWall.BlockType
					.values())
			{
				final ItemStack itemstack = new ItemStack(thisBlock, 1,
						blockType.metadata());
				proxy.addName(itemstack, blockType.itemName() + " Wall");
			}

			Extrabiomes.postInitEvent(new WallActiveEvent(thisBlock));
		}
	};

	private static boolean	settingsLoaded	= false;

	private static void createBlocks() throws InstantiationException,
			IllegalAccessException
	{
		for (final BlockManager block : BlockManager.values())
			if (block.blockID > 0) {
				block.create();
				block.blockCreated = true;
			}
	}

	public static void init() throws InstantiationException,
			IllegalAccessException
	{
		for (final BlockManager block : values())
			if (block.blockCreated) block.prepare();
	}

	private static void loadSettings(ExtrabiomesConfig config) {
		settingsLoaded = true;

		ExtrabiomesLog.info("== Fabrica Block ID List ==");
		ExtrabiomesLog
				.info("  (may be changed by ID Resolver, if installed.)");

		// Load config settings
		for (final BlockManager cube : BlockManager.values()) {
			final Property property = config.getBlock(cube.idKey(),
					Extrabiomes.getNextDefaultBlockID());
			cube.blockID = property.getInt(0);

			ExtrabiomesLog.info("  %s: %d", cube.toString(),
					cube.blockID);
		}

		ExtrabiomesLog.info("=== End Block ID List ===");

		if (PLANKS.blockID == 0) {
			WOODSLAB.blockID = 0;
			FIRSTAIRS.blockID = 0;
			REDWOODSTAIRS.blockID = 0;
			ACACIASTAIRS.blockID = 0;
		}

		if (WOODSLAB.blockID == 0 || DOUBLEWOODSLAB.blockID == 0) {
			WOODSLAB.blockID = 0;
			DOUBLEWOODSLAB.blockID = 0;
		}

		if (!Stuff.redRock.isPresent()) {
			REDROCKSLAB.blockID = 0;
			WALL.blockID = 0;
		}

		if (REDROCKSLAB.blockID == 0 || DOUBLEREDROCKSLAB.blockID == 0)
		{
			REDROCKSLAB.blockID = 0;
			DOUBLEREDROCKSLAB.blockID = 0;
		}
	}

	public static void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		if (settingsLoaded) return;

		loadSettings(config);
		createBlocks();
	}

	protected int	blockID			= 0;
	private boolean	blockCreated	= false;

	protected abstract void create();

	private String idKey() {
		return toString() + ".id";
	}

	protected abstract void prepare();

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.US);
	}
}
