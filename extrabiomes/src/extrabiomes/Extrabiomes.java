/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

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
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.features.FeatureGenerator;
import extrabiomes.flora.FloraManager;
import extrabiomes.trees.TreeBlocks;

@Mod(modid = "ExtrabiomesXL", name = "ExtrabiomesXL", version = "3.0 PR1")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy			proxy;
	@Instance("ExtrabiomesXL")
	public static Extrabiomes			instance;

	private static BiomeManagerImpl		biomeManager	= new BiomeManagerImpl();
	private static FloraManager			floraManager	= new FloraManager();
	private static PluginManagerImpl	pluginManager	= new PluginManagerImpl();

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();
		proxy.registerWorldGenerator(new FeatureGenerator());

		biomeManager.initialize();

		floraManager.addCustomFlora();
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
		pluginManager.activatePlugins();
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final ExtrabiomesConfig cfg = new ExtrabiomesConfig(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			BiomeManagerImpl.loadSettings(cfg);
			BiomeManagerImpl.preInit();

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}

		TreeBlocks.setBlocks(BROWN, Block.wood.blockID, 0,
				Block.leaves.blockID, 0);
		TreeBlocks.setBlocks(ORANGE, Block.wood.blockID, 0,
				Block.leaves.blockID, 0);
		TreeBlocks.setBlocks(PURPLE, Block.wood.blockID, 0,
				Block.leaves.blockID, 0);
		TreeBlocks.setBlocks(YELLOW, Block.wood.blockID, 0,
				Block.leaves.blockID, 0);
		TreeBlocks.setBlocks(FIR, Block.wood.blockID, 1,
				Block.leaves.blockID, 1);
		TreeBlocks.setBlocks(REDWOOD, Block.wood.blockID, 1,
				Block.leaves.blockID, 0);
		TreeBlocks.setBlocks(ACACIA, Block.wood.blockID, 0,
				Block.leaves.blockID, 0);
	}
}
