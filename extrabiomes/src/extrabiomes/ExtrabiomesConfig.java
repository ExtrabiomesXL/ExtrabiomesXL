/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

import extrabiomes.utility.ConfigFile;

public class ExtrabiomesConfig extends ConfigFile {

	public static final String			CATEGORY_BIOME			= "biome";

	public TreeMap<String, Property>	biomeProperties			= new TreeMap<String, Property>();

	private final boolean				loggedConversionMessage	= false;
	private int							conversionCount			= 0;
	private int							discardCount			= 0;

	public ExtrabiomesConfig(File file) {
		super(file);
		categories.put(CATEGORY_BIOME, blockProperties);
	}

	private void addProperty(String key, String category, String value)
	{
		logConversionStart();
		getOrCreateProperty(key, category, value);
	}

	private Optional<Property> findProperty(String key, String category)
	{
		final Optional<Map<String, Property>> properties = Optional
				.fromNullable(categories.get(category));
		if (properties.isPresent())
			return Optional.fromNullable(properties.get().get(key));
		return Optional.absent();
	}

	private void logConversionEnd() {
		if (loggedConversionMessage) {
			ExtrabiomesLog.info("  Converted %d key/value pairs.",
					conversionCount);
			ExtrabiomesLog.info(
					"  Discarded %d invalid key/value pairs.",
					discardCount);
			ExtrabiomesLog.info("Config file version updated..");
		}
	}

	private void logConversionStart() {
		if (!loggedConversionMessage) {
			ExtrabiomesLog
					.info("Detected outdated version of config file.");
			ExtrabiomesLog.info("  Beginning conversion...");
		}
	}

	private void moveAndFlipBinaryProperty(String oldKey,
			String category, String newKey)
	{
		moveAndFlipBinaryProperty(oldKey, category, newKey, category);
	}

	private void moveAndFlipBinaryProperty(String oldKey,
			String oldCategory, String newKey, String newCategory)
	{
		final Optional<Property> optionalProperty = findProperty(
				oldKey, oldCategory);
		if (optionalProperty.isPresent()) {
			if (!optionalProperty.get().isBooleanValue())
				discardCount++;
			else
				addProperty(
						newKey,
						newCategory,
						Boolean.valueOf(
								!Boolean.parseBoolean(optionalProperty
										.get().value)).toString());
			removeProperty(oldKey, oldCategory);

			conversionCount++;
		}
	}

	private void moveProperty(String oldKey, String category,
			String newKey)
	{
		moveProperty(oldKey, category, newKey, category);
	}

	private void moveProperty(String oldKey, String oldCategory,
			String newKey, String newCategory)
	{
		final Optional<Property> optionalProperty = findProperty(
				oldKey, oldCategory);
		if (optionalProperty.isPresent()) {
			addProperty(newKey, newCategory,
					optionalProperty.get().value);
			removeProperty(oldKey, oldCategory);

			conversionCount++;
		}
	}

	public void reconcileConfigFileVersions(String currentVersion) {
		final Property version = getOrCreateProperty("config.version",
				CATEGORY_GENERAL, "");
		if (version.value.isEmpty()) updatePre30Version();
		version.value = currentVersion;
		logConversionEnd();
	}

	private void removeProperty(String key, String category) {
		final Optional<Map<String, Property>> properties = Optional
				.fromNullable(categories.get(category));
		if (properties.isPresent()) properties.get().remove(key);
	}

	private void updatePre30Version() {
		moveProperty("wasteland.enable.growth", CATEGORY_BIOME,
				"crackedsand.growth.enable", CATEGORY_GENERAL);
		moveAndFlipBinaryProperty("wasteland.restrict.growth.to.biome",
				CATEGORY_BIOME,
				"crackedsand.growthoutsidewasteland.allow",
				CATEGORY_GENERAL);
		moveAndFlipBinaryProperty("vanilla.desert.disable",
				CATEGORY_BIOME, "vanilla.desert.enablegeneration");
		moveAndFlipBinaryProperty("vanilla.extremehills.disable",
				CATEGORY_BIOME, "vanilla.extremehills.enablegeneration");
		moveAndFlipBinaryProperty("vanilla.forest.disable",
				CATEGORY_BIOME, "vanilla.forest.enablegeneration");
		moveAndFlipBinaryProperty("vanilla.jungle.disable",
				CATEGORY_BIOME, "vanilla.jungle.enablegeneration");
		moveAndFlipBinaryProperty("vanilla.plains.disable",
				CATEGORY_BIOME, "vanilla.plains.enablegeneration");
		moveAndFlipBinaryProperty("vanilla.swampland.disable",
				CATEGORY_BIOME, "vanilla.swampland.enablegeneration");
		moveAndFlipBinaryProperty("vanilla.taiga.disable",
				CATEGORY_BIOME, "vanilla.taiga.enablegeneration");
		moveProperty("alpine.enable", CATEGORY_BIOME,
				"alpine.enablegeneration");
		moveProperty("alpine.allow.village", CATEGORY_BIOME,
				"alpine.allowvillages");
		moveProperty("autumnwoods.enable", CATEGORY_BIOME,
				"autumnwoods.enablegeneration");
		moveProperty("autumnwoods.allow.village", CATEGORY_BIOME,
				"autumnwoods.allowvillages");
		moveProperty("birchforest.enable", CATEGORY_BIOME,
				"birchforest.enablegeneration");
		moveProperty("birchforest.allow.village", CATEGORY_BIOME,
				"birchforest.allowvillages");
		moveProperty("extremejungle.enable", CATEGORY_BIOME,
				"extremejungle.enablegeneration");
		moveProperty("extremejungle.allow.village", CATEGORY_BIOME,
				"extremejungle.allowvillages");
		moveProperty("forestedhills.enable", CATEGORY_BIOME,
				"forestedhills.enablegeneration");
		moveProperty("forestedhills.allow.village", CATEGORY_BIOME,
				"forestedhills.allowvillages");
		moveProperty("forestedisland.enable", CATEGORY_BIOME,
				"forestedisland.enablegeneration");
		moveProperty("forestedisland.allow.village", CATEGORY_BIOME,
				"forestedisland.allowvillages");
		moveProperty("glacier.enable", CATEGORY_BIOME,
				"glacier.enablegeneration");
		moveProperty("glacier.allow.village", CATEGORY_BIOME,
				"glacier.allowvillages");
		moveProperty("greenhills.enable", CATEGORY_BIOME,
				"greenhills.enablegeneration");
		moveProperty("greenhills.allow.village", CATEGORY_BIOME,
				"greenhills.allowvillages");
		moveProperty("greenswamp.enable", CATEGORY_BIOME,
				"greenswamp.enablegeneration");
		moveProperty("greenswamp.allow.village", CATEGORY_BIOME,
				"greenswamp.allowvillages");
		moveProperty("icewasteland.enable", CATEGORY_BIOME,
				"icewasteland.enablegeneration");
		moveProperty("icewasteland.allow.village", CATEGORY_BIOME,
				"icewasteland.allowvillages");
		moveProperty("marsh.enable", CATEGORY_BIOME,
				"marsh.enablegeneration");
		moveProperty("marsh.allow.village", CATEGORY_BIOME,
				"marsh.allowvillages");
		moveProperty("meadow.enable", CATEGORY_BIOME,
				"meadow.enablegeneration");
		moveProperty("meadow.allow.village", CATEGORY_BIOME,
				"meadow.allowvillages");
		moveProperty("minijungle.enable", CATEGORY_BIOME,
				"minijungle.enablegeneration");
		moveProperty("minijungle.allow.village", CATEGORY_BIOME,
				"minijungle.allowvillages");
		moveProperty("mountaindesert.enable", CATEGORY_BIOME,
				"mountaindesert.enablegeneration");
		moveProperty("mountaindesert.allow.village", CATEGORY_BIOME,
				"mountaindesert.allowvillages");
		moveProperty("mountainridge.enable", CATEGORY_BIOME,
				"mountainridge.enablegeneration");
		moveProperty("mountainridge.allow.village", CATEGORY_BIOME,
				"mountainridge.allowvillages");
		moveProperty("mountaintaiga.enable", CATEGORY_BIOME,
				"mountaintaiga.enablegeneration");
		moveProperty("mountaintaiga.allow.village", CATEGORY_BIOME,
				"mountaintaiga.allowvillages");
		moveProperty("pineforest.enable", CATEGORY_BIOME,
				"pineforest.enablegeneration");
		moveProperty("pineforest.allow.village", CATEGORY_BIOME,
				"pineforest.allowvillages");
		moveProperty("rainforest.enable", CATEGORY_BIOME,
				"rainforest.enablegeneration");
		moveProperty("rainforest.allow.village", CATEGORY_BIOME,
				"rainforest.allowvillages");
		moveProperty("redwoodforest.enable", CATEGORY_BIOME,
				"redwoodforest.enablegeneration");
		moveProperty("redwoodforest.allow.village", CATEGORY_BIOME,
				"redwoodforest.allowvillages");
		moveProperty("redwoodlush.enable", CATEGORY_BIOME,
				"redwoodlush.enablegeneration");
		moveProperty("redwoodlush.allow.village", CATEGORY_BIOME,
				"redwoodlush.allowvillages");
		moveProperty("savanna.enable", CATEGORY_BIOME,
				"savanna.enablegeneration");
		moveProperty("savanna.allow.village", CATEGORY_BIOME,
				"savanna.allowvillages");
		moveProperty("shrubland.enable", CATEGORY_BIOME,
				"shrubland.enablegeneration");
		moveProperty("shrubland.allow.village", CATEGORY_BIOME,
				"shrubland.allowvillages");
		moveProperty("snowforest.enable", CATEGORY_BIOME,
				"snowyforest.enablegeneration");
		moveProperty("snowforest.allow.village", CATEGORY_BIOME,
				"snowyforest.allowvillages");
		moveProperty("snowyrainforest.enable", CATEGORY_BIOME,
				"snowyrainforest.enablegeneration");
		moveProperty("snowyrainforest.allow.village", CATEGORY_BIOME,
				"snowyrainforest.allowvillages");
		moveProperty("temporaterainforest.enable", CATEGORY_BIOME,
				"temporaterainforest.enablegeneration");
		moveProperty("temporaterainforest.allow.village",
				CATEGORY_BIOME, "temporaterainforest.allowvillages");
		moveProperty("tundra.enable", CATEGORY_BIOME,
				"tundra.enablegeneration");
		moveProperty("tundra.allow.village", CATEGORY_BIOME,
				"tundra.allowvillages");
		moveProperty("wasteland.enable", CATEGORY_BIOME,
				"wasteland.enablegeneration");
		moveProperty("wasteland.allow.village", CATEGORY_BIOME,
				"wasteland.allowvillages");
		moveProperty("woodlands.enable", CATEGORY_BIOME,
				"woodlands.enablegeneration");
		moveProperty("woodlands.allow.village", CATEGORY_BIOME,
				"woodlands.allowvillages");
	}

}
