/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

import static extrabiomes.trees.TreeBlocks.Type.ACACIA;
import static extrabiomes.trees.TreeBlocks.Type.BROWN;
import static extrabiomes.trees.TreeBlocks.Type.FIR;
import static extrabiomes.trees.TreeBlocks.Type.ORANGE;
import static extrabiomes.trees.TreeBlocks.Type.PURPLE;
import static extrabiomes.trees.TreeBlocks.Type.REDWOOD;
import static extrabiomes.trees.TreeBlocks.Type.YELLOW;

import java.io.File;
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
import extrabiomes.CommonProxy;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.trees.TreeBlocks;
import extrabiomes.utility.EnhancedConfiguration;

@Mod(modid = "EBXLTree", name = "ExtrabiomesXL Custom Trees Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Tree {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy			proxy;
	@Instance("EBXLTree")
	public static Tree					instance;
	private static int					autumnLeavesID;
	private static int					greenLeavesID;
	private static int					saplingID;
	private static Optional<Block>		autumnLeaves	= Optional.absent();
	private static Optional<Block>		greenLeaves		= Optional.absent();
	static Optional<BlockCustomSapling>	sapling			= Optional.absent();

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();

		if (isAutumnLeavesEnabled()) {
			autumnLeaves = Optional.of(new BlockAutumnLeaves(
					autumnLeavesID).setBlockName("autumnleaves"));

			proxy.registerBlock(autumnLeaves,
					extrabiomes.ItemCustomLeaves.class);

			for (final AutumnLeafType type : AutumnLeafType.values())
				proxy.addName(
						new ItemStack(autumnLeaves.get(), 1, type
								.metadata()), type.itemName());

			TreeBlocks.setBlocks(BROWN, Block.wood.blockID, 0,
					autumnLeavesID, AutumnLeafType.BROWN.metadata());
			TreeBlocks.setBlocks(ORANGE, Block.wood.blockID, 0,
					autumnLeavesID, AutumnLeafType.ORANGE.metadata());
			TreeBlocks.setBlocks(PURPLE, Block.wood.blockID, 0,
					autumnLeavesID, AutumnLeafType.PURPLE.metadata());
			TreeBlocks.setBlocks(YELLOW, Block.wood.blockID, 0,
					autumnLeavesID, AutumnLeafType.YELLOW.metadata());
		}

		if (isGreenLeavesEnabled()) {
			greenLeaves = Optional.of(new BlockGreenLeaves(
					greenLeavesID).setBlockName("greenleaves"));

			proxy.registerBlock(greenLeaves,
					extrabiomes.ItemCustomLeaves.class);

			for (final GreenLeafType type : GreenLeafType.values())
				proxy.addName(
						new ItemStack(greenLeaves.get(), 1, type
								.metadata()), type.itemName());

			TreeBlocks.setBlocks(FIR, Block.wood.blockID, 0,
					greenLeavesID, GreenLeafType.FIR.metadata());
			TreeBlocks.setBlocks(REDWOOD, Block.wood.blockID, 0,
					greenLeavesID, GreenLeafType.REDWOOD.metadata());
			TreeBlocks.setBlocks(ACACIA, Block.wood.blockID, 0,
					greenLeavesID, GreenLeafType.ACACIA.metadata());
		}

		if (isSaplingEnabled()) {
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

	public static boolean isAutumnLeavesEnabled() {
		return 0 < autumnLeavesID;
	}

	public static boolean isGreenLeavesEnabled() {
		return 0 < greenLeavesID;
	}

	public static boolean isSaplingEnabled() {
		return 0 < saplingID;
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final EnhancedConfiguration cfg = new EnhancedConfiguration(
				new File(event.getModConfigurationDirectory(),
						"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			autumnLeavesID = cfg.getOrCreateBlockIdProperty(
					"autumnleaves.id", 150).getInt(0);

			if (!isAutumnLeavesEnabled())
				ExtrabiomesLog
						.info("autumnleaves.id = 0, so autumn leaves have been disabled.");

			greenLeavesID = cfg.getOrCreateBlockIdProperty(
					"greenleaves.id", 155).getInt(0);

			if (!isGreenLeavesEnabled())
				ExtrabiomesLog
						.info("greenleaves.id = 0, so green leaves have been disabled.");

			saplingID = cfg.getOrCreateBlockIdProperty("sapling.id",
					159).getInt(0);

			if (!isSaplingEnabled())
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
