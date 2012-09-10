/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.BiomeGenBase;

import com.google.common.base.Optional;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
import extrabiomes.api.ExtrabiomesBiomeDecorations;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.biomes.VanillaBiomeManager;
import extrabiomes.blocks.BlockManager;
import extrabiomes.flora.FloraManager;
import extrabiomes.utility.ConfigSettingAnnotationParser;

@Mod(modid = "ExtrabiomesXL", name = "ExtrabiomesXL", version = "3.0 PR1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy			proxy;
	@Instance("ExtrabiomesXL")
	public static Extrabiomes			instance;

	private static BiomeManagerImpl		biomeManager		= new BiomeManagerImpl();
	private static BlockManager			blockManager		= new BlockManager();
	private static VanillaBiomeManager	vanillaBiomeManager	= new VanillaBiomeManager();
	private static FloraManager			floraManager		= new FloraManager();
	private static PluginManagerImpl	pluginManager		= new PluginManagerImpl();
	private static FuelHandler			fuelHandler			= new FuelHandler();
	private static WorldGenerator		worldGenerator		= new WorldGenerator();

	public static BlockManager getBlockManager() {
		return blockManager;
	}

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();
		proxy.registerFuelHandler(fuelHandler);
		proxy.registerWorldGenerator(worldGenerator);

		biomeManager.initialize();

		blockManager.registerBlocks();
		initializeBiomeDecorationsManager();
		vanillaBiomeManager.applyConfigSettings();
		floraManager.addCustomFlora();
	}

	private static void initializeBiomeDecorationsManager() {
		final Multimap<BiomeGenBase, IBiomeDecoration> multimap = ArrayListMultimap
				.create();
		ExtrabiomesBiomeDecorations.biomeDecorations = Optional
				.of(multimap);
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {

		BlockManager.addNames();

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

			final ConfigSettingAnnotationParser parser = new ConfigSettingAnnotationParser(
					cfg);

			parser.parse(blockManager, "Block Manager");
			parser.parse(vanillaBiomeManager, "Vanilla Biome Manager");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}
}
