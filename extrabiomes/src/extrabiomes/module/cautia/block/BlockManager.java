/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import static com.google.common.base.Preconditions.checkElementIndex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.cautia.worldgen.QuicksandGenerator;
import extrabiomes.proxy.CommonProxy;

public enum BlockManager {
	QUICKSAND {
		@Override
		protected void create() {
			block = Optional.of(new BlockQuicksand(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("extrabiomes.quicksand");
			proxy.setBlockHarvestLevel(thisBlock, "shovel", 0);
			proxy.registerBlock(thisBlock);

			proxy.addName(thisBlock, "Quicksand");

			proxy.registerOre("sandQuick", thisBlock);

			proxy.registerWorldGenerator(new QuicksandGenerator(
					thisBlock.blockID));
		}
	};

	private static boolean		settingsLoaded	= false;

	private static void createBlocks() throws InstantiationException,
			IllegalAccessException
	{
		for (final BlockManager block : BlockManager.values())
			if (block.blockID > 0) block.create();
	}

	public static void init() throws InstantiationException,
			IllegalAccessException
	{
		for (final BlockManager block : values())
			if (block.block.isPresent()) block.prepare();
	}

	private static void loadSettings(ExtrabiomesConfig config) {
		settingsLoaded = true;

		ExtrabiomesLog.info("== Cautia Block ID List ==");
		ExtrabiomesLog.info("  (may be changed by ID Resolver, if installed.)");

		// Load config settings
		for (final BlockManager cube : BlockManager.values()) {
			final Property property = config.getBlock(cube.idKey(),
							Extrabiomes.getNextDefaultBlockID());
			cube.blockID = property.getInt(0);

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

	protected int						blockID	= 0;
	protected Optional<? extends Block>	block	= Optional.absent();

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
