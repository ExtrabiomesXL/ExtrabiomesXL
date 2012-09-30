/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import static com.google.common.base.Preconditions.checkElementIndex;

import java.util.Locale;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.proxy.CommonProxy;

public enum Cube {
	CRACKEDSAND(BlockCrackedSand.class, true);

	private static boolean	settingsLoaded	= false;

	private static void addCrackedSandToWasteland() {
		if (!BiomeManager.wasteland.isPresent()) return;
		if (BiomeManager.wasteland.isPresent()) {
			final BiomeGenBase wasteland = BiomeManager.wasteland.get();
			wasteland.topBlock = (byte) CRACKEDSAND.block.get().blockID;
			wasteland.fillerBlock = (byte) CRACKEDSAND.block.get().blockID;
			ExtrabiomesLog
					.info("Added cracked sand to wasteland biome.");
		}
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
	private int								blockID			= 0;
	private Optional<? extends Block>		block			= Optional
																	.absent();
	private static final String				TERRAIN_COMMENT	= "%s is used in terrain generation. Its id must be less than 256.";

	Cube(Class<? extends Block> blockClass, boolean restrictTo256) {
		this.blockClass = blockClass;
		this.restrictTo256 = restrictTo256;
	}

	public int getBlockID() {
		return blockID;
	}

	private String idKey() {
		return toString() + ".id";
	}

	private void prepare() {
		switch (this) {
			case CRACKEDSAND:
				block.get().setBlockName("crackedsand");
				final CommonProxy proxy = Extrabiomes.proxy;

				proxy.setBlockHarvestLevel(block.get(), "pickaxe", 0);
				proxy.registerBlock(block.get());
				CommonProxy.registerOre("sandCracked", block.get());
				proxy.addName(block.get(), "Cracked Sand");
		}
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.US);
	}
}
