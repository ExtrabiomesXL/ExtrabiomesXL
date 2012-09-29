/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.plugin.trees;

import static extrabiomes.plugin.trees.AutumnLeafType.BROWN;
import static extrabiomes.plugin.trees.AutumnLeafType.ORANGE;
import static extrabiomes.plugin.trees.AutumnLeafType.PURPLE;
import static extrabiomes.plugin.trees.AutumnLeafType.YELLOW;

import java.io.File;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.configuration.EnhancedConfiguration;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.plugin.trees.BlockQuarterLog.BarkOn;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.trees.WorldGenAcacia;
import extrabiomes.trees.WorldGenAutumnTree;
import extrabiomes.trees.WorldGenFirTree;
import extrabiomes.trees.WorldGenFirTreeHuge;
import extrabiomes.trees.WorldGenRedwood;

@Mod(modid = "EBXLTree", name = "ExtrabiomesXL Custom Trees Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Tree {

	@SidedProxy(clientSide = "extrabiomes.proxy.ClientProxy", serverSide = "extrabiomes.proxy.CommonProxy")
	public static CommonProxy									proxy;
	@Instance("EBXLTree")
	public static Tree											instance;
	private static int											autumnLeavesID;
	private static int											customLogID;
	private static Map<BlockQuarterLog.BarkOn, Integer>			quarterLogIds		= new EnumMap(
																							BlockQuarterLog.BarkOn.class);
	private static Map<BlockQuarterLog.BarkOn, Optional<Block>>	quarterLogBlocks	= new EnumMap(
																							BlockQuarterLog.BarkOn.class);
	private static int											greenLeavesID;
	private static int											saplingID;
	private static Optional<Block>								autumnLeaves		= Optional
																							.absent();
	private static Optional<Block>								customLog			= Optional
																							.absent();
	private static Optional<Block>								greenLeaves			= Optional
																							.absent();
	static Optional<BlockCustomSapling>							sapling				= Optional
																							.absent();

	static {
		for (final BarkOn bo : BarkOn.values()) {
			quarterLogIds.put(bo, 0);
			quarterLogBlocks.put(bo,
					Optional.fromNullable((Block) null));
		}
	}

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();

		BlockCustomSapling.addValidSoil(Block.grass);
		BlockCustomSapling.addValidSoil(Block.dirt);
		BlockCustomSapling.addValidSoil(Block.tilledField);

		if (autumnLeavesID != 0) {
			autumnLeaves = Optional.of(new BlockAutumnLeaves(
					autumnLeavesID).setBlockName("autumnleaves"));

			proxy.registerBlock(autumnLeaves,
					extrabiomes.ItemCustomLeaves.class);

			for (final AutumnLeafType type : AutumnLeafType.values())
				proxy.addName(
						new ItemStack(autumnLeaves.get(), 1, type
								.metadata()), type.itemName());
		}

		if (customLogID != 0) {
			customLog = Optional.of(new BlockCustomLog(customLogID)
					.setBlockName("customlog"));

			proxy.registerBlock(customLog,
					extrabiomes.utility.MultiItemBlock.class);

			for (final WoodType type : WoodType.values())
				proxy.addName(
						new ItemStack(customLog.get(), 1, type
								.metadata()), type.itemName() + " Log");
		}

		boolean quarterLogsEnabled = false;
		for (final BarkOn bo : BarkOn.values()) {
			final int quarterLogID = quarterLogIds.get(bo);
			if (quarterLogID != 0) {
				final Optional<Block> quarterLog = Optional
						.of(new BlockQuarterLog(quarterLogID, bo)
								.setBlockName("quarterlog." + bo.name()));

				quarterLogBlocks.put(bo, quarterLog);

				proxy.registerBlock(quarterLog,
						extrabiomes.utility.MultiItemBlock.class);

				for (final QuarterWoodType type : QuarterWoodType
						.values())
					proxy.addName(new ItemStack(quarterLog.get(), 1,
							type.metadata()), type.itemName() + " Log"
							+ bo.name());

				if (bo == BarkOn.SE)
					BlockQuarterLog
							.setRenderId(Tree.proxy
									.registerBlockHandler(new RenderQuarterLog()));
				quarterLogsEnabled = true;
			}
		}

		if (greenLeavesID != 0) {
			greenLeaves = Optional.of(new BlockGreenLeaves(
					greenLeavesID).setBlockName("greenleaves"));

			proxy.registerBlock(greenLeaves,
					extrabiomes.ItemCustomLeaves.class);

			for (final GreenLeafType type : GreenLeafType.values())
				proxy.addName(
						new ItemStack(greenLeaves.get(), 1, type
								.metadata()), type.itemName());
		}

		if (customLog.isPresent()) {
			WorldGenAcacia.setTrunkBlock(customLog.get(),
					WoodType.ACACIA.metadata());
			WorldGenFirTree.setTrunkBlock(customLog.get(),
					WoodType.FIR.metadata());
		}

		if (quarterLogsEnabled) {
			WorldGenFirTreeHuge.setTrunkBlock(
					quarterLogBlocks.get(BarkOn.NW).get(),
					quarterLogBlocks.get(BarkOn.NE).get(),
					quarterLogBlocks.get(BarkOn.SW).get(),
					quarterLogBlocks.get(BarkOn.SE).get(),
					QuarterWoodType.FIR.metadata());
			WorldGenRedwood.setTrunkBlock(
					quarterLogBlocks.get(BarkOn.NW).get(),
					quarterLogBlocks.get(BarkOn.NE).get(),
					quarterLogBlocks.get(BarkOn.SW).get(),
					quarterLogBlocks.get(BarkOn.SE).get(),
					QuarterWoodType.REDWOOD.metadata());
		}

		if (greenLeaves.isPresent()) {
			WorldGenAcacia.setLeavesBlock(greenLeaves.get(),
					GreenLeafType.ACACIA.metadata());
			WorldGenFirTree.setLeavesBlock(greenLeaves.get(),
					GreenLeafType.FIR.metadata());
			WorldGenFirTreeHuge.setLeavesBlock(greenLeaves.get(),
					GreenLeafType.FIR.metadata());
			WorldGenRedwood.setLeavesBlock(greenLeaves.get(),
					GreenLeafType.REDWOOD.metadata());
		}

		if (autumnLeaves.isPresent())
			WorldGenAutumnTree.setLeavesBlock(autumnLeaves.get(),
					BROWN.metadata(), ORANGE.metadata(),
					PURPLE.metadata(), YELLOW.metadata());

		if (0 < saplingID) {
			sapling = Optional.of(new BlockCustomSapling(saplingID));
			sapling.get().setBlockName("sapling");

			proxy.registerBlock(sapling,
					extrabiomes.utility.MultiItemBlock.class);

			for (final SaplingType type : SaplingType.values())
				proxy.addName(
						new ItemStack(sapling.get(), 1, type.metadata()),
						type.itemName());

			proxy.registerEventHandler(new ForgeEventHandler(sapling
					.get()));
			proxy.registerFuelHandler(new FuelHandler(saplingID));
		}
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final ExtrabiomesConfig cfg = new ExtrabiomesConfig(
				new File(event.getModConfigurationDirectory(),
						"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			autumnLeavesID = cfg.getOrCreateBlockIdProperty(
					"autumnleaves.id", 150).getInt(0);

			if (0 == autumnLeavesID)
				ExtrabiomesLog
						.info("autumnleaves.id = 0, so autumn leaves have been disabled.");

			customLogID = cfg.getOrCreateBlockIdProperty(
					"customlog.id", 160).getInt(0);

			if (0 == customLogID)
				ExtrabiomesLog
						.info("customlog.id = 0, so fir and acacia logs have been disabled.");

			int i = 0;
			for (final BarkOn bo : BarkOn.values())
				quarterLogIds.put(
						bo,
						cfg.getOrCreateBlockIdProperty(
								String.format("quarterlog%d.id",
										bo.ordinal()), 165 + i++)
								.getInt(0));

			boolean clearMap = false;
			for (final BarkOn bo : BarkOn.values())
				if (quarterLogIds.get(bo) == 0) {
					clearMap = true;
					ExtrabiomesLog
							.info("quarterlog%d.id = 0, so 2x2 logs have been disabled.",
									bo.ordinal());
				}
			if (clearMap) for (final BarkOn bo : BarkOn.values())
				quarterLogIds.put(bo, 0);

			greenLeavesID = cfg.getOrCreateBlockIdProperty(
					"greenleaves.id", 155).getInt(0);

			if (0 == greenLeavesID)
				ExtrabiomesLog
						.info("greenleaves.id = 0, so green leaves have been disabled.");

			saplingID = cfg.getOrCreateBlockIdProperty("sapling.id",
					159).getInt(0);

			if (0 == saplingID)
				ExtrabiomesLog
						.info("sapling.id = 0, so saplings have been disabled.");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}
}
