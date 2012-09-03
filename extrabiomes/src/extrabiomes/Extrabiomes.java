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
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.achievements.AchievementManager;
import extrabiomes.api.ExtrabiomesBiome;
import extrabiomes.api.ExtrabiomesBiomeDecorations;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.PluginManager;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.biomes.VanillaBiomeManager;
import extrabiomes.blocks.BlockManager;
import extrabiomes.flora.FloraManager;
import extrabiomes.items.ItemManager;
import extrabiomes.utility.ConfigSettingAnnotationParser;

@Mod(modid = "mod_ExtrabiomesXL", name = "ExtrabiomesXL", version = "3.0 PR1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy			proxy;
	@Instance
	public static Extrabiomes			instance;

	private static BlockManager			blockManager		= new BlockManager();
	private static ItemManager			itemManager			= new ItemManager();
	private static AchievementManager	achievementManager	= new AchievementManager();
	private static VanillaBiomeManager	vanillaBiomeManager	= new VanillaBiomeManager();
	private static FloraManager			floraManager		= new FloraManager();
	private static CustomBiomeManager	customBiomeManager	= new CustomBiomeManager();
	private static PluginManager		pluginManager		= new PluginManagerImpl();
	private static boolean				canActivatePlugins	= false;
	private static CraftingHandler		craftingHandler		= new CraftingHandler();
	private static FuelHandler			fuelHandler			= new FuelHandler();
	private static WorldGenerator		worldGenerator		= new WorldGenerator();

	public static boolean canActivatePlugins() {
		return canActivatePlugins;
	}

	public static BlockManager getBlockManager() {
		return blockManager;
	}

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();
		proxy.registerCraftingHandler(craftingHandler);
		proxy.registerFuelHandler(fuelHandler);
		proxy.registerWorldGenerator(worldGenerator);

		blockManager.registerBlocks();
		initializeBiomeDecorationsManager();
		itemManager.RegisterItems();
		achievementManager.registerAchievements();
		vanillaBiomeManager.applyConfigSettings();
		floraManager.addCustomFlora();
		initializeBiomesinAPI();
		customBiomeManager.applyConfigSettings();
	}

	private static void initializeBiomeDecorationsManager() {
		final Multimap<BiomeGenBase, IBiomeDecoration> multimap = ArrayListMultimap
				.create();
		ExtrabiomesBiomeDecorations.biomeDecorations = Optional
				.of(multimap);
	}

	private static void initializeBiomesinAPI() {
		ExtrabiomesBiome.alpine = Optional.of(CustomBiomes.alpine);
		ExtrabiomesBiome.autumnwoods = Optional
				.of(CustomBiomes.autumnWoods);
		ExtrabiomesBiome.birchforest = Optional
				.of(CustomBiomes.birchForest);
		ExtrabiomesBiome.extremejungle = Optional
				.of(CustomBiomes.extremeJungle);
		ExtrabiomesBiome.forestedhills = Optional
				.of(CustomBiomes.forestedHills);
		ExtrabiomesBiome.forestedisland = Optional
				.of(CustomBiomes.forestedIsland);
		ExtrabiomesBiome.glacier = Optional.of(CustomBiomes.glacier);
		ExtrabiomesBiome.greenhills = Optional
				.of(CustomBiomes.greenHills);
		ExtrabiomesBiome.greenswamp = Optional
				.of(CustomBiomes.greenSwamp);
		ExtrabiomesBiome.icewasteland = Optional
				.of(CustomBiomes.iceWasteland);
		ExtrabiomesBiome.marsh = Optional.of(CustomBiomes.marsh);
		ExtrabiomesBiome.meadow = Optional.of(CustomBiomes.meadow);
		ExtrabiomesBiome.minijungle = Optional
				.of(CustomBiomes.miniJungle);
		ExtrabiomesBiome.mountaindesert = Optional
				.of(CustomBiomes.mountainDesert);
		ExtrabiomesBiome.mountainridge = Optional
				.of(CustomBiomes.mountainRidge);
		ExtrabiomesBiome.mountaintaiga = Optional
				.of(CustomBiomes.mountainTaiga);
		ExtrabiomesBiome.pineforest = Optional
				.of(CustomBiomes.pineForest);
		ExtrabiomesBiome.rainforest = Optional
				.of(CustomBiomes.rainForest);
		ExtrabiomesBiome.redwoodforest = Optional
				.of(CustomBiomes.redwoodForest);
		ExtrabiomesBiome.redwoodlush = Optional
				.of(CustomBiomes.redwoodLush);
		ExtrabiomesBiome.savanna = Optional.of(CustomBiomes.savanna);
		ExtrabiomesBiome.shrubland = Optional
				.of(CustomBiomes.shrubLand);
		ExtrabiomesBiome.snowforest = Optional
				.of(CustomBiomes.snowForest);
		ExtrabiomesBiome.snowyrainforest = Optional
				.of(CustomBiomes.snowRainForest);
		ExtrabiomesBiome.temperaterainforest = Optional
				.of(CustomBiomes.temperateRainForest);
		ExtrabiomesBiome.tundra = Optional.of(CustomBiomes.tundra);
		ExtrabiomesBiome.wasteland = Optional
				.of(CustomBiomes.wasteland);
		ExtrabiomesBiome.woodlands = Optional
				.of(CustomBiomes.woodlands);
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
			ExtrabiomesBiome.wasteland.get().topBlock = ExtrabiomesBiome.wasteland
					.get().fillerBlock = (byte) TerrainGenManager.blockWasteland.blockID;
		if (TerrainGenManager.blockWasteland != null)
			ExtrabiomesBiome.mountainridge.get().topBlock = ExtrabiomesBiome.mountainridge
					.get().fillerBlock = (byte) TerrainGenManager.blockMountainRidge.blockID;

		canActivatePlugins = true;
		pluginManager.activatePlugins();
		canActivatePlugins = false;
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final ExtrabiomesConfig cfg = new ExtrabiomesConfig(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();
			cfg.reconcileConfigFileVersions("3.0");
			final ConfigSettingAnnotationParser parser = new ConfigSettingAnnotationParser(
					cfg);

			parser.parse(blockManager, "Block Manager");
			parser.parse(itemManager, "Item Manager");
			parser.parse(itemManager, "Achievement Manager");
			parser.parse(vanillaBiomeManager, "Vanilla Biome Manager");
			parser.parse(customBiomeManager, "Custom Biome Manager");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}
}
