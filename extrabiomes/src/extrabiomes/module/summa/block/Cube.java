/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import static com.google.common.base.Preconditions.checkElementIndex;
import static extrabiomes.module.summa.block.BlockRedRock.BlockType.RED_COBBLE;
import static extrabiomes.module.summa.block.BlockRedRock.BlockType.RED_ROCK;
import static extrabiomes.module.summa.block.BlockRedRock.BlockType.RED_ROCK_BRICK;

import java.util.Locale;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.summa.worldgen.CatTailGenerator;
import extrabiomes.module.summa.worldgen.WorldGenAcacia;
import extrabiomes.module.summa.worldgen.WorldGenAutumnTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTree;
import extrabiomes.module.summa.worldgen.WorldGenFirTreeHuge;
import extrabiomes.module.summa.worldgen.WorldGenRedwood;
import extrabiomes.proxy.CommonProxy;

public enum Cube {
	AUTUMNLEAVES {
		@Override
		protected void create() {
			block = Optional.of(new BlockAutumnLeaves(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("autumnleaves");
			proxy.registerBlock(
					thisBlock,
					extrabiomes.module.summa.block.ItemCustomLeaves.class);

			for (final BlockAutumnLeaves.BlockType type : BlockAutumnLeaves.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());

			WorldGenAutumnTree.setLeavesBlock(thisBlock,
					BlockAutumnLeaves.BlockType.BROWN.metadata(),
					BlockAutumnLeaves.BlockType.ORANGE.metadata(),
					BlockAutumnLeaves.BlockType.PURPLE.metadata(),
					BlockAutumnLeaves.BlockType.YELLOW.metadata());

		}
	},
	CATTAIL {
		@Override
		protected void create() {
			block = Optional.of(new BlockCatTail(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("cattail");
			proxy.registerBlock(thisBlock,
					extrabiomes.module.summa.block.ItemCatTail.class);

			proxy.addName(thisBlock, "Cat Tail");

			proxy.registerWorldGenerator(new CatTailGenerator(
					thisBlock.blockID));
		}
	},
	CRACKEDSAND(true) {
		@Override
		protected void create() {
			block = Optional.of(new BlockCrackedSand(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("crackedsand");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
			proxy.registerBlock(thisBlock);
			proxy.addName(thisBlock, "Cracked Sand");

			proxy.registerOre("sandCracked", thisBlock);
			addCrackedSandToWasteland();
		}
	},
	GREENLEAVES {
		@Override
		protected void create() {
			block = Optional.of(new BlockGreenLeaves(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("greenleaves");
			proxy.registerBlock(
					thisBlock,
					extrabiomes.module.summa.block.ItemCustomLeaves.class);

			for (final BlockGreenLeaves.BlockType type : BlockGreenLeaves.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());

			WorldGenAcacia.setLeavesBlock(thisBlock,
					BlockGreenLeaves.BlockType.ACACIA.metadata());
			WorldGenFirTree.setLeavesBlock(thisBlock,
					BlockGreenLeaves.BlockType.FIR.metadata());
			WorldGenFirTreeHuge.setLeavesBlock(thisBlock,
					BlockGreenLeaves.BlockType.FIR.metadata());
			WorldGenRedwood.setLeavesBlock(thisBlock,
					BlockGreenLeaves.BlockType.REDWOOD.metadata());

		}
	},
	REDROCK(true) {
		@Override
		protected void create() {
			block = Optional.of(new BlockRedRock(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("redrock");
			proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);
			for (final BlockRedRock.BlockType blockType : BlockRedRock.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, blockType
								.metadata()), blockType.itemName());

			final ItemStack redRockItem = new ItemStack(thisBlock, 1,
					RED_ROCK.metadata());
			final ItemStack redCobbleItem = new ItemStack(thisBlock, 1,
					RED_COBBLE.metadata());
			final ItemStack redRockBrickItem = new ItemStack(thisBlock,
					1, RED_ROCK_BRICK.metadata());

			OreDictionary.registerOre("rockRed", redRockItem);
			OreDictionary.registerOre("cobbleRed", redCobbleItem);
			OreDictionary.registerOre("brickRedRock", redRockBrickItem);
			addRedRockToMountainRidge();
		}
	},
	SAPLING {
		@Override
		protected void create() {
			block = Optional.of(new BlockCustomSapling(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("sapling");
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);

			for (final BlockCustomSapling.BlockType type : BlockCustomSapling.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());

			proxy.registerEventHandler(new SaplingBonemealEventhandler());
			proxy.registerFuelHandler(new FuelHandlerSapling());
		}
	},
	CUSTOMLOG {
		@Override
		protected void create() {
			block = Optional.of(new BlockCustomLog(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("customlog");
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);

			for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());

			WorldGenAcacia.setTrunkBlock(thisBlock,
					BlockCustomLog.BlockType.ACACIA.metadata());
			WorldGenFirTree.setTrunkBlock(thisBlock,
					BlockCustomLog.BlockType.FIR.metadata());
		}
	},
	QUARTERLOG0 {
		@Override
		protected void create() {
			block = Optional.of(new BlockQuarterLog(blockID,
					BlockQuarterLog.BarkOn.NW));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("quarterlog.NW");
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);

			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());
		}
	},
	QUARTERLOG1 {
		@Override
		protected void create() {
			block = Optional.of(new BlockQuarterLog(blockID,
					BlockQuarterLog.BarkOn.NE));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("quarterlog.NE");
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);

			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());
		}
	},
	QUARTERLOG2 {
		@Override
		protected void create() {
			block = Optional.of(new BlockQuarterLog(blockID,
					BlockQuarterLog.BarkOn.SW));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("quarterlog.SW");
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);

			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());
		}
	},
	QUARTERLOG3 {
		@Override
		protected void create() {
			block = Optional.of(new BlockQuarterLog(blockID,
					BlockQuarterLog.BarkOn.SE));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("quarterlog.SE");
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);

			for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, type.metadata()),
						type.itemName());
		}
	};

	private static boolean		settingsLoaded	= false;

	private static final String	TERRAIN_COMMENT	= "%s is used in terrain generation. Its id must be less than 256.";

	private static void addCrackedSandToWasteland() {
		if (!BiomeManager.wasteland.isPresent()) return;

		final BiomeGenBase wasteland = BiomeManager.wasteland.get();
		wasteland.topBlock = (byte) CRACKEDSAND.block.get().blockID;
		wasteland.fillerBlock = (byte) CRACKEDSAND.block.get().blockID;
		ExtrabiomesLog.info("Added cracked sand to wasteland biome.");
	}

	private static void addRedRockToMountainRidge() {
		if (!BiomeManager.mountainridge.isPresent()) return;

		final BiomeGenBase mountainridge = BiomeManager.mountainridge
				.get();
		mountainridge.topBlock = (byte) REDROCK.block.get().blockID;
		mountainridge.fillerBlock = (byte) REDROCK.block.get().blockID;
		ExtrabiomesLog.info("Added red rock to mountain ridge biome.");
	}

	private static void createBlocks() throws InstantiationException,
			IllegalAccessException
	{
		for (final Cube block : Cube.values())
			if (block.blockID > 0) block.create();
	}

	public static void init() throws InstantiationException,
			IllegalAccessException
	{
		for (final Cube block : values())
			if (block.block.isPresent()) block.prepare();

		if (QUARTERLOG0.block.isPresent()
				|| QUARTERLOG1.block.isPresent()
				|| QUARTERLOG2.block.isPresent()
				|| QUARTERLOG3.block.isPresent())
			BlockQuarterLog.setRenderId(Extrabiomes.proxy
					.registerBlockHandler(new RenderQuarterLog()));

		if (QUARTERLOG0.block.isPresent()
				&& QUARTERLOG1.block.isPresent()
				&& QUARTERLOG2.block.isPresent()
				&& QUARTERLOG3.block.isPresent())
		{
			WorldGenFirTreeHuge.setTrunkBlock(QUARTERLOG0.block.get(),
					QUARTERLOG1.block.get(), QUARTERLOG2.block.get(),
					QUARTERLOG3.block.get(),
					BlockQuarterLog.BlockType.FIR.metadata());
			WorldGenRedwood.setTrunkBlock(QUARTERLOG0.block.get(),
					QUARTERLOG1.block.get(), QUARTERLOG2.block.get(),
					QUARTERLOG3.block.get(),
					BlockQuarterLog.BlockType.REDWOOD.metadata());
		}
	}

	private static void loadSettings(ExtrabiomesConfig config) {
		settingsLoaded = true;

		ExtrabiomesLog.info("== Summa Block ID List ==");
		ExtrabiomesLog.info("  (may be changed by ID");
		ExtrabiomesLog.info("   Resolver)");

		// Load config settings
		int defaultID = 150;
		for (final Cube cube : Cube.values()) {
			final Property property = cube.restrictTo256 ? config
					.getOrCreateRestrictedBlockIdProperty(cube.idKey(),
							defaultID)
					: config.getOrCreateBlockIdProperty(cube.idKey(),
							defaultID);
			++defaultID;
			cube.blockID = property.getInt(0);
			if (cube.restrictTo256) {
				final String comment = String.format(TERRAIN_COMMENT,
						cube.toString());
				property.comment = comment;
				checkElementIndex(cube.blockID, 256, comment);
			}

			ExtrabiomesLog.info("  %s: %d", cube.toString(),
					cube.blockID);
		}

		ExtrabiomesLog.info("=== End Block ID List ===");

		if (QUARTERLOG0.blockID == 0 || QUARTERLOG1.blockID == 0
				|| QUARTERLOG2.blockID == 0 || QUARTERLOG3.blockID == 0)
		{
			QUARTERLOG0.blockID = 0;
			QUARTERLOG1.blockID = 0;
			QUARTERLOG2.blockID = 0;
			QUARTERLOG3.blockID = 0;
			ExtrabiomesLog
					.info("At least one quarterlog was disabled, so all have been.");
		}
	}

	public static void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		if (settingsLoaded) return;

		loadSettings(config);
		createBlocks();
	}

	private final boolean				restrictTo256;
	protected int						blockID	= 0;
	protected Optional<? extends Block>	block	= Optional.absent();

	Cube() {
		this(false);
	}

	Cube(boolean restrictTo256) {
		this.restrictTo256 = restrictTo256;
	}

	protected abstract void create();

	public Optional<? extends Block> getBlock() {
		return block;
	}

	private String idKey() {
		return toString() + ".id";
	}

	protected abstract void prepare();

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.US);
	}
}
