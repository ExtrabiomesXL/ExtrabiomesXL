/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.io.File;
import java.util.Locale;

import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.handlers.BiomeHandler;
import extrabiomes.handlers.BlockHandler;
import extrabiomes.handlers.CanMobSpawnHandler;
import extrabiomes.handlers.ConfigurationHandler;
import extrabiomes.handlers.CropHandler;
import extrabiomes.handlers.EBXLCommandHandler;
import extrabiomes.handlers.GenesisBiomeOverrideHandler;
import extrabiomes.handlers.ItemHandler;
import extrabiomes.handlers.RecipeHandler;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.GeneralSettings;
import extrabiomes.lib.Reference;
import extrabiomes.module.amica.forestry.ForestryPlugin;
import extrabiomes.module.amica.treecapitator.TreecapitatorPlugin;
import extrabiomes.module.fabrica.recipe.RecipeManager;
import extrabiomes.plugins.PluginThaumcraft4;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.utility.CreativeTab;
import extrabiomes.utility.EnhancedConfiguration;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION, dependencies = "")
public class Extrabiomes {

  @Instance(Reference.MOD_ID)
  public static Extrabiomes instance;

  @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
  public static CommonProxy proxy;

  public static final CreativeTabs tabsEBXL = new CreativeTab("extrabiomesTab");

  public static final String TEXTURE_PATH = Reference.MOD_ID.toLowerCase(Locale.ENGLISH) + ":";

  private static Optional<EventBus> initBus = Optional.of(new EventBus());

  @Mod.EventHandler
  public static void init(FMLInitializationEvent event) throws InstantiationException, IllegalAccessException {
    proxy.registerRenderInformation();
    TreecapitatorPlugin.init();
    ForestryPlugin.init();

  }

  @Mod.EventHandler
  public static void postInit(FMLPostInitializationEvent event) {
    PluginManager.activatePlugins();
    RecipeHandler.init();
    initBus = Optional.absent();
    Module.releaseStaticResources();

    if (PluginThaumcraft4.isEnabled()) {
      try {
        PluginThaumcraft4.postInit();
      } catch (Exception e) {
        LogHelper.warning("ExtrabiomesXL's Thaumcraft API implementaion is most likely out of date. Fell free to let us know.");
      }
    }

    LogHelper.info("Successfully Loaded.");
  }

  public static boolean postInitEvent(Event event) {
    return initBus.isPresent() ? initBus.get().post(event) : false;
  }

  @Mod.EventHandler
  public static void preInit(FMLPreInitializationEvent event) throws Exception {
    LogHelper.info("Initializing.");

    MinecraftForge.EVENT_BUS.register(CanMobSpawnHandler.INSTANCE);

    // Handle upgrading
    File test = new File(event.getModConfigurationDirectory(), "/extrabiomes/extrabiomes.cfg");
    if (test.exists()) {
      ConfigurationHandler.init(test, true);

      File newFile = new File(event.getModConfigurationDirectory(), "/extrabiomes/oldunusedconfig.cfg");

      if (!newFile.exists()) {
        test.renameTo(newFile);
      }

      LogHelper.info("Upgrading Configfile");
    }

    final EnhancedConfiguration config = ConfigurationHandler.init(new File(event.getModConfigurationDirectory(), "/extrabiomes.cfg"), false);

    BiomeHandler.init();

    // remove after 3.6.0 release
    BiomeManagerImpl.populateAPIBiomes();
    new BiomeManagerImpl();

    Extrabiomes.registerInitEventHandler(new RecipeManager());

    BlockHandler.createBlocks();
    ItemHandler.createItems();
    CropHandler.createCrops();

    BiomeHandler.registerWorldGenerators(config);
    BiomeHandler.enableBiomes();
    BiomeManagerImpl.buildWeightedFloraLists();

    Module.registerModules();
    Module.postEvent(new ModulePreInitEvent());
    Module.postEvent(new ModuleInitEvent());

    // just in case anything else updated config settings
    config.save();
  }

  @Mod.EventHandler
  public void serverStart(FMLServerStartingEvent event) {
    if (GeneralSettings.consoleCommandsDisabled)
      return;

    MinecraftServer server = MinecraftServer.getServer(); // Gets current server
    ICommandManager command = server.getCommandManager(); // Gets the command
                                                          // manager to use for
                                                          // server
    ServerCommandManager serverCommand = ((ServerCommandManager) command); // Turns
                                                                           // it
                                                                           // into
                                                                           // another
                                                                           // form
                                                                           // to
                                                                           // use

    serverCommand.registerCommand(new EBXLCommandHandler());
  }

  public static void registerInitEventHandler(Object target) {
    if (initBus.isPresent())
      initBus.get().register(target);
  }
}
