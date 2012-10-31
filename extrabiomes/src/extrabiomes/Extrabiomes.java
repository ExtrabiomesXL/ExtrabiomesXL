/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.event.Event;
import net.minecraftforge.event.EventBus;

import com.google.common.base.Optional;

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
import extrabiomes.configuration.EnhancedConfiguration;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.proxy.CommonProxy;

@Mod(modid = "ExtrabiomesXL", name = "ExtrabiomesXL", version = "3.2.2")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "extrabiomes.proxy.ClientProxy", serverSide = "extrabiomes.proxy.CommonProxy")
	public static CommonProxy			proxy;
	@Instance("ExtrabiomesXL")
	public static Extrabiomes			instance;

	private static PluginManagerImpl	pluginManager = new PluginManagerImpl();

	private static int					nextDefaultBlockID = 200;
	private static int					nextDefaultItemID  = 12870;

	private static Optional<EventBus>	initBus = Optional.of(new EventBus());

	public static int getNextDefaultBlockID() {
		return nextDefaultBlockID++;
	}

	public static int getNextDefaultItemID() {
		return nextDefaultItemID++;
	}

	@Init
	public static void init(FMLInitializationEvent event)
			throws InstantiationException, IllegalAccessException
	{
		proxy.registerRenderInformation();
		Module.postEvent(new ModuleInitEvent());
	}

	@PostInit
	public static void postInit(FMLPostInitializationEvent event) {
		pluginManager.activatePlugins();
		EnhancedConfiguration.releaseStaticResources();
		initBus = Optional.absent();
		Module.releaseStaticResources();
	}

	public static boolean postInitEvent(Event event) {
		return initBus.isPresent() ? initBus.get().post(event) : false;
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final ExtrabiomesConfig cfg = new ExtrabiomesConfig(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			Module.registerModules(cfg);
			Module.postEvent(new ModulePreInitEvent(cfg));

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}

	public static void registerInitEventHandler(Object target) {
		if (initBus.isPresent()) initBus.get().register(target);
	}
}
