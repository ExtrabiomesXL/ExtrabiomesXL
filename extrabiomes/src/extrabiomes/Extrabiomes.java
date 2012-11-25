/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.io.File;

import net.minecraft.src.CreativeTabs;
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
import extrabiomes.core.handler.ConfigurationHandler;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.lib.Reference;
import extrabiomes.localization.LocalizationHandler;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.utility.CreativeTab;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Extrabiomes {

    @Instance(Reference.MOD_ID)
    public static Extrabiomes         instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy         proxy;

    public static final CreativeTabs  tabsEBXL                 = new CreativeTab(
                                                                       CreativeTabs.creativeTabArray.length,
                                                                       Reference.MOD_ID);

    private static final String       LOG_MESSAGE_INITIALIZING = "log.message.initializing";
    private static final String       LOG_MESSAGE_LOAD_SUCCESS = "log.message.load.success";

    private static Optional<EventBus> initBus                  = Optional.of(new EventBus());

    @Init
    public static void init(FMLInitializationEvent event) throws InstantiationException,
            IllegalAccessException
    {
        proxy.registerRenderInformation();
        Module.postEvent(new ModuleInitEvent());
    }

    @PostInit
    public static void postInit(FMLPostInitializationEvent event) {
        PluginManager.activatePlugins();
        initBus = Optional.absent();
        Module.releaseStaticResources();
        LogHelper.info(proxy.getStringLocalization(LOG_MESSAGE_LOAD_SUCCESS));
    }

    public static boolean postInitEvent(Event event) {
        return initBus.isPresent() ? initBus.get().post(event) : false;
    }

    @PreInit
    public static void preInit(FMLPreInitializationEvent event) throws InstantiationException,
            IllegalAccessException
    {

        LogHelper.info(proxy.getStringLocalization(LOG_MESSAGE_INITIALIZING));

        // Load the localization files into the LanguageRegistry
        LocalizationHandler.loadLanguages();

        ConfigurationHandler.init(new File(event.getModConfigurationDirectory(),
                "/extrabiomes/extrabiomes.cfg"));

        Module.registerModules();
        Module.postEvent(new ModulePreInitEvent());
    }

    public static void registerInitEventHandler(Object target) {
        if (initBus.isPresent()) initBus.get().register(target);
    }
}
