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
import extrabiomes.api.BiomeManager;
import extrabiomes.api.ExtrabiomesBiomeDecorations;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.biomes.VanillaBiomeManager;
import extrabiomes.blocks.BlockManager;
import extrabiomes.flora.FloraManager;
import extrabiomes.items.ItemManager;
import extrabiomes.utility.ConfigSettingAnnotationParser;

@Mod(modid = "ExtrabiomesXL", name = "ExtrabiomesXL", version = "3.0 PR1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy			proxy;
	@Instance
	public static Extrabiomes			instance;

	private static BiomeManagerImpl			biomeManager		= new BiomeManagerImpl();
	private static BlockManager			blockManager		= new BlockManager();
	private static ItemManager			itemManager			= new ItemManager();
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
		itemManager.RegisterItems();
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

		final RecipeManager recipeManager = new RecipeManager();
		recipeManager.registerRecipes();

		BlockManager.addNames();
		ItemManager.addNames();

		if (ExtrabiomesItem.scarecrow != null) {
			ExtrabiomesEntity.scarecrow = Extrabiomes.proxy
					.findGlobalUniqueEntityId();
			Extrabiomes.proxy.registerEntityID(EntityScarecrow.class,
					"scarecrow", ExtrabiomesEntity.scarecrow);
			Extrabiomes.proxy.registerEntity(EntityScarecrow.class,
					"scarecrow", instance, ExtrabiomesEntity.scarecrow,
					300, 2, true);
		}

		if (TerrainGenManager.blockWasteland != null)
			BiomeManager.wasteland.get().topBlock = BiomeManager.wasteland.get().fillerBlock = (byte) TerrainGenManager.blockWasteland.blockID;
		if (TerrainGenManager.blockWasteland != null)
			BiomeManager.mountainridge.get().topBlock = BiomeManager.mountainridge
					.get().fillerBlock = (byte) TerrainGenManager.blockMountainRidge.blockID;

		pluginManager.activatePlugins();
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
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
			parser.parse(itemManager, "Item Manager");
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
