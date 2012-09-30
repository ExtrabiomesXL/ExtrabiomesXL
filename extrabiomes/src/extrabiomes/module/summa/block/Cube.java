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
import extrabiomes.proxy.CommonProxy;

public enum Cube {
	CRACKEDSAND(BlockCrackedSand.class, true),
	REDROCK(BlockRedRock.class, true);

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
			if (block.blockID > 0)
				block.block = Optional.of(block.blockClass
						.newInstance());
	}

	public static void init() throws InstantiationException,
			IllegalAccessException
	{
		if (CRACKEDSAND.block.isPresent()) {
			CRACKEDSAND.prepare();
			addCrackedSandToWasteland();
		}

		if (REDROCK.block.isPresent()) {
			REDROCK.prepare();
			addRedRockToMountainRidge();
		}
	}

	private static void loadSettings(ExtrabiomesConfig config) {
		settingsLoaded = true;

		ExtrabiomesLog.info("== Summa Block ID List ==");

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
	}

	public static void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		if (settingsLoaded) return;

		loadSettings(config);
		createBlocks();
	}

	private final Class<? extends Block>	blockClass;
	private final boolean					restrictTo256;
	private int								blockID	= 0;
	private Optional<? extends Block>		block	= Optional.absent();

	Cube(Class<? extends Block> blockClass) {
		this(blockClass, false);
	}

	Cube(Class<? extends Block> blockClass, boolean restrictTo256) {
		this.blockClass = blockClass;
		this.restrictTo256 = restrictTo256;
	}

	public Optional<? extends Block> getBlock() {
		return block;
	}

	public int getBlockID() {
		return blockID;
	}

	private String idKey() {
		return toString() + ".id";
	}

	private void prepare() {
		final CommonProxy proxy = Extrabiomes.proxy;
		final Block thisBlock = block.get();

		switch (this) {
			case CRACKEDSAND:
				thisBlock.setBlockName("crackedsand");
				proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
				proxy.registerBlock(thisBlock);
				proxy.addName(thisBlock, "Cracked Sand");

				proxy.registerOre("sandCracked", thisBlock);
				break;
			case REDROCK:
				thisBlock.setBlockName("redrock");
				proxy.setBlockHarvestLevel(thisBlock, "pickaxe", 0);
				proxy.registerBlock(thisBlock,
						extrabiomes.utility.MultiItemBlock.class);
				for (final BlockRedRock.BlockType blockType : BlockRedRock.BlockType
						.values())
					proxy.addName(
							new ItemStack(thisBlock, 1, blockType
									.metadata()), blockType.itemName());

				final ItemStack redRockItem = new ItemStack(thisBlock,
						1, RED_ROCK.metadata());
				final ItemStack redCobbleItem = new ItemStack(
						thisBlock, 1, RED_COBBLE.metadata());
				final ItemStack redRockBrickItem = new ItemStack(
						thisBlock, 1, RED_ROCK_BRICK.metadata());

				OreDictionary.registerOre("rockRed", redRockItem);
				OreDictionary.registerOre("cobbleRed", redCobbleItem);
				OreDictionary.registerOre("brickRedRock",
						redRockBrickItem);
				break;
		}
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.US);
	}
}
