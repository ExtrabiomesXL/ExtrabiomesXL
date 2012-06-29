
package extrabiomes.config;

import java.io.File;

import extrabiomes.Proxy;
import forge.Configuration;
import forge.Property;

public class Config {
	public static boolean		enableClassicMode	= false;
	public static final String	CATEGORY_BIOME		= "BIOME";
	public static Configuration	config;

	public static void addNames() {
		ConfigureBlocks.addNames();
		ConfigureItems.addNames();
	}

	public static int getOrCreateBlockIdProperty(String s, int i) {
		return Integer
				.parseInt(config.getOrCreateBlockIdProperty(s, i).value);
	}

	public static boolean getOrCreateBooleanProperty(String s,
			String s1, boolean flag)
	{
		return Boolean.parseBoolean(config.getOrCreateBooleanProperty(
				s, s1, flag).value);
	}

	public static int getOrCreateIntProperty(String s, String s1, int i)
	{
		return Integer
				.parseInt(config.getOrCreateIntProperty(s, s1, i).value);
	}

	public static Property getProperty(String s, String s1, String s2) {
		return config.getOrCreateProperty(s, s1, s2);
	}

	public static void load() {
		config = new Configuration(new File(Proxy.getExtrabiomesRoot(),
				"/config/extrabiomes/extrabiomes.cfg"));
		config.load();
		final Property property = config.getOrCreateBooleanProperty(
				"classicmode.enable", "general", enableClassicMode);
		property.comment = "set to true to disable all custom blocks";
		enableClassicMode = Boolean.parseBoolean(property.value);
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

	public static void modsLoaded() {}

	public Config() {}
}
