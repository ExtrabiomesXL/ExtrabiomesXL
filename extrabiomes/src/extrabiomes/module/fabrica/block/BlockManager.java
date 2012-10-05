/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.Locale;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.proxy.CommonProxy;

public enum BlockManager {
	PLANK {
		@Override
		protected void create() {
			block = Optional.of(new BlockCustomWood(blockID));
		}

		@Override
		protected void prepare() {
			final CommonProxy proxy = Extrabiomes.proxy;
			final Block thisBlock = block.get();

			thisBlock.setBlockName("extrabiomes.planks");
			proxy.setBlockHarvestLevel(thisBlock, "axe", 0);
			proxy.registerBlock(thisBlock,
					extrabiomes.utility.MultiItemBlock.class);
			for (final BlockCustomWood.BlockType blockType : BlockCustomWood.BlockType
					.values())
				proxy.addName(
						new ItemStack(thisBlock, 1, blockType
								.metadata()), blockType.itemName());

			final ItemStack firPlankItem = new ItemStack(thisBlock, 1,
					BlockCustomWood.BlockType.FIR.metadata());
			final ItemStack redwoodPlankItem = new ItemStack(thisBlock,
					1, BlockCustomWood.BlockType.REDWOOD.metadata());
			final ItemStack acaciaPlankItem = new ItemStack(thisBlock,
					1, BlockCustomWood.BlockType.ACACIA.metadata());

			OreDictionary.registerOre("plankWood", firPlankItem);
			OreDictionary.registerOre("plankWood", acaciaPlankItem);
			OreDictionary.registerOre("plankWood", redwoodPlankItem);
			OreDictionary.registerOre("plankFir", firPlankItem);
			OreDictionary.registerOre("plankAcacia", acaciaPlankItem);
			OreDictionary.registerOre("plankRedwood", redwoodPlankItem);
		}
	};

	private static boolean	settingsLoaded	= false;

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

		ExtrabiomesLog.info("== Fabrica Block ID List ==");
		ExtrabiomesLog
				.info("  (may be changed by ID Resolver, if installed.)");

		// Load config settings
		for (final BlockManager cube : BlockManager.values()) {
			final Property property = config
					.getOrCreateBlockIdProperty(cube.idKey(),
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
