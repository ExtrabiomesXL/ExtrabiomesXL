/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Property;

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
import extrabiomes.configuration.EnhancedConfiguration;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.features.FeatureGenerator;
import extrabiomes.proxy.CommonProxy;

@Mod(modid = "ExtrabiomesXL", name = "ExtrabiomesXL", version = "3.0.5")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "extrabiomes.proxy.ClientProxy", serverSide = "extrabiomes.proxy.CommonProxy")
	public static CommonProxy			proxy;
	@Instance("ExtrabiomesXL")
	public static Extrabiomes			instance;

	private static BiomeManagerImpl		biomeManager	= new BiomeManagerImpl();
	private static PluginManagerImpl	pluginManager	= new PluginManagerImpl();

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();
		proxy.registerWorldGenerator(new FeatureGenerator());

		biomeManager.initialize();
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
		pluginManager.activatePlugins();
		EnhancedConfiguration.releaseStaticResources();
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final ExtrabiomesConfig cfg = new ExtrabiomesConfig(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			Module.loadControlSettings(cfg);
			Module.do_PreInit(cfg);
			
			BiomeManagerImpl.loadSettings(cfg);
			BiomeManagerImpl.preInit();

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}
}
