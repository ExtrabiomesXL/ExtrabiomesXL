package extrabiomes.config;

import java.io.File;

import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;
import extrabiomes.Proxy;

public class Config {

	public static boolean enableClassicMode = false;

	public static final String CATEGORY_BIOME = "BIOME";
	public static Configuration config;

	public static void addNames() {
		ConfigureBlocks.addNames();
		ConfigureItems.addNames();
	}

	public static int getOrCreateBlockIdProperty(String key, int defaultId) {
		return Integer.parseInt(config.getOrCreateBlockIdProperty(key,
				defaultId).value);
	}

	public static boolean getOrCreateBooleanProperty(String key, String kind,
			boolean defaults) {
		return Boolean.parseBoolean(config.getOrCreateBooleanProperty(key,
				kind, defaults).value);
	}

	public static int getOrCreateIntProperty(String string, String category,
			int defaultValue) {
		return Integer.parseInt(config.getOrCreateIntProperty(string, category,
				defaultValue).value);
	}

	public static Property getProperty(String key, String category,
			String defaultValue) {
		return config.getOrCreateProperty(key, category, defaultValue);
	}

	public static void load() {
		config = new Configuration(new File(Proxy.getExtrabiomesRoot(),
				"/config/extrabiomes/extrabiomes.cfg"));
		config.load();

		Property classicMode = config.getOrCreateBooleanProperty(
				"classicmode.enable", Configuration.CATEGORY_GENERAL,
				enableClassicMode);
		classicMode.comment = "set to true to disable all custom blocks";
		enableClassicMode = Boolean.parseBoolean(classicMode.value);

		ConfigureBlocks.initialize();
		ConfigureBiomeDecorationsManager.initialize();
		ConfigureItems.initialize();
		AchievementManager.initialize();
		ConfigureVanillaBiomes.initialize();
		ConfigureTerrainGen.initialize();
		ConfigureBiomeManager.initialize();
		ConfigureCustomBiomes.initialize();
		ConfigurePlugins.initialize();

		config.save();
	}

	public static void modsLoaded() {
		// Future expansion
	}
}
