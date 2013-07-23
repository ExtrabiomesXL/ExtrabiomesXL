/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.EventBus;

import com.google.common.base.Optional;

//import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
<<<<<<< HEAD
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
=======
//import cpw.mods.fml.common.Mod.PostInit;
//import cpw.mods.fml.common.Mod.PreInit;
//import cpw.mods.fml.common.Mod.ServerStarting;
>>>>>>> origin/3.14.0
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.handlers.BiomeHandler;
import extrabiomes.handlers.BlockHandler;
import extrabiomes.handlers.ConfigurationHandler;
import extrabiomes.handlers.ItemHandler;
import extrabiomes.handlers.RecipeHandler;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Reference;
import extrabiomes.localization.LocalizationHandler;
import extrabiomes.module.fabrica.recipe.RecipeManager;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.utility.CreativeTab;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Extrabiomes {

    @Instance(Reference.MOD_ID)
    public static Extrabiomes         instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy         proxy;

    public static final CreativeTabs tabsEBXL = new CreativeTab("extrabiomesTab");

    private static final String       LOG_MESSAGE_INITIALIZING = "log.message.initializing";
    private static final String       LOG_MESSAGE_LOAD_SUCCESS = "log.message.load.success";
    
    public static final String        TEXTURE_PATH = Reference.MOD_ID.toLowerCase() + ":";

    private static Optional<EventBus> initBus                  = Optional.of(new EventBus());

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) throws InstantiationException, IllegalAccessException {
        proxy.registerRenderInformation();
        Module.postEvent(new ModuleInitEvent());
        //sendTCIMC();
    }
    
    /*
    public static void sendTCIMC()
    {
        if (Loader.isModLoaded("TreeCapitator"))
        {
            NBTTagCompound tpModCfg = new NBTTagCompound();
            tpModCfg.setString("modID", Reference.MOD_ID);
            tpModCfg.setString("configPath", "extrabiomes/extrabiomes.cfg");
            tpModCfg.setString("blockConfigKeys", "block:customlog.id; block:quarterlog0.id; block:quarterlog1.id; block:quarterlog2.id; block:quarterlog3.id; " +
                    "block:autumnleaves.id; block:greenleaves.id");
            tpModCfg.setString("itemConfigKeys", "");
            tpModCfg.setString("axeIDList", "");
            tpModCfg.setString("shearsIDList", "");
            tpModCfg.setBoolean("useShiftedItemID", true);
            
            NBTTagList treeList = new NBTTagList();
            
            // Vanilla Oak additions
            NBTTagCompound tree = new NBTTagCompound();
            tree.setString("treeName", "vanilla_oak");
            tree.setString("logConfigKeys", "<block:quarterlog0.id>,2; <block:quarterlog1.id>,2; <block:quarterlog2.id>,2; <block:quarterlog3.id>,2;");
            tree.setString("leafConfigKeys", "<block:autumnleaves.id>");
            treeList.appendTag(tree);
            
            // Vanilla Spruce additions
            tree = new NBTTagCompound();
            tree.setString("treeName", "vanilla_spruce");
            tree.setString("logConfigKeys", "");
            tree.setString("leafConfigKeys", "<block:autumnleaves.id>");
            treeList.appendTag(tree);
            
            // EBXL fir
            tree = new NBTTagCompound();
            tree.setString("treeName", "fir");
            tree.setString("logConfigKeys", "<block:customlog.id>,0; <block:quarterlog0.id>,1; <block:quarterlog1.id>,1; <block:quarterlog2.id>,1; <block:quarterlog3.id>,1");
            tree.setString("leafConfigKeys", "<block:greenleaves.id>,0; <block:greenleaves.id>,8");
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL redwood
            tree = new NBTTagCompound();
            tree.setString("treeName", "redwood");
            tree.setString("logConfigKeys", "<block:quarterlog0.id>,0; <block:quarterlog1.id>,0; <block:quarterlog2.id>,0; <block:quarterlog3.id>,0");
            tree.setString("leafConfigKeys", "<block:greenleaves.id>,1; <block:greenleaves.id>,9");
            tree.setInteger("maxHorLeafBreakDist", 10);
            tree.setBoolean("requireLeafDecayCheck", false);
            treeList.appendTag(tree);
            
            // EBXL acacia
            tree = new NBTTagCompound();
            tree.setString("treeName", "acacia");
            tree.setString("logConfigKeys", "<block:customlog.id>,1");
            tree.setString("leafConfigKeys", "<block:greenleaves.id>,2");
            treeList.appendTag(tree);
            
            tpModCfg.setTag("trees", treeList);
            
            FMLInterModComms.sendMessage("TreeCapitator", Reference.MOD_ID, tpModCfg);
        }
    }//*/

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        PluginManager.activatePlugins();
        RecipeHandler.init();
        initBus = Optional.absent();
        Module.releaseStaticResources();
        LogHelper.info("Successfully Loaded.");
    }

    public static boolean postInitEvent(Event event) {
        return initBus.isPresent() ? initBus.get().post(event) : false;
    }

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) throws Exception {
        LogHelper.info("Intializing.");

        // Load the localization files into the LanguageRegistry
        LocalizationHandler.loadLanguages();

        ConfigurationHandler.init(new File(event.getModConfigurationDirectory(), "/extrabiomes/extrabiomes.cfg"));

        BiomeHandler.init();

        // remove after 3.6.0 release
        BiomeManagerImpl.populateAPIBiomes();
        new BiomeManagerImpl();
        //

        Extrabiomes.registerInitEventHandler(new RecipeManager());

        BlockHandler.createBlocks();
        ItemHandler.createItems();
 
        BiomeHandler.registerWorldGenerators();
        BiomeHandler.enableBiomes();
        BiomeManagerImpl.buildWeightedFloraLists();

        Module.registerModules();
        Module.postEvent(new ModulePreInitEvent());
        proxy.addStringLocalization("itemGroup.extrabiomesTab", "en_US", Reference.MOD_ID);

    }

    public static void registerInitEventHandler(Object target) {
        if (initBus.isPresent()) initBus.get().register(target);
    }
}
