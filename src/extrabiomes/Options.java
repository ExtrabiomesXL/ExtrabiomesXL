package extrabiomes;

import java.io.File;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.Configuration;
import net.minecraft.src.forge.Property;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.RemovableVanillaBiome;

public enum Options {
	INSTANCE;

	enum ConfigSwitch {
		CLASSIC_MODE(false);

		private final boolean deflt;

		ConfigSwitch(final boolean defValue) {
			deflt = defValue;
		}

		boolean getDefault() {
			return deflt;
		}
	}

	private static final String CATEGORY_BIOME = "BIOME";

	private static final File CONFIG_DIR = new File(
			Minecraft.getMinecraftDir(), "/config/extrabiomes/");;

	private static final File CONF_FILE = new File(CONFIG_DIR,
			"extrabiomes.cfg");

	private static String formatKey(Enum name) {
		return name.toString().toLowerCase().replace("_", "");
	}

	private static boolean getDefaulBiomeVillageSpawnSetting(Extrabiome biome) {
		switch (biome) {
		case ALPINE:
		case AUTUMN_WOODS:
		case BIRCH_FOREST:
		case EXTREME_JUNGLE:
		case FORESTED_HILLS:
		case FORESTED_ISLAND:
		case GREEN_HILLS:
		case GREEN_SWAMP:
		case MEADOW:
		case MINI_JUNGLE:
		case MOUNTAIN_DESERT:
		case MOUNTAIN_RIDGE:
		case MOUNTAIN_TAIGA:
		case PINE_FOREST:
		case RAINFOREST:
		case REDWOOD_FOREST:
		case REDWOOD_LUSH:
		case SAVANNA:
		case SHRUBLAND:
		case SNOW_FOREST:
		case SNOWY_RAINFOREST:
		case TEMPORATE_RAINFOREST:
		case TUNDRA:
		case WOODLANDS:
			return true;
		}
		return false;
	}
	private boolean initialized = false;
	final private Map<BaseBlock, Integer> baseBlockIds = new EnumMap<BaseBlock, Integer>(
			BaseBlock.class);
	final private Map<CustomItem, Integer> itemIds = new EnumMap<CustomItem, Integer>(
			CustomItem.class);
	final private Set<ConfigSwitch> configSwitches = EnumSet
			.noneOf(ConfigSwitch.class);
	final private Set<Extrabiome> enabledBiomes = EnumSet
			.noneOf(Extrabiome.class);
	final private Set<Extrabiome> villageSpawnBiomes = EnumSet
			.noneOf(Extrabiome.class);

	final private Set<RemovableVanillaBiome> disabledVanillaBiomes = EnumSet
			.noneOf(RemovableVanillaBiome.class);

	void addDisabledVanillaBiomes(
			final Collection<RemovableVanillaBiome> biomesToDisable) {
		disabledVanillaBiomes.addAll(biomesToDisable);
	}

	boolean canSpawnVillage(Extrabiome biome) {
		return villageSpawnBiomes.contains(biome);
	}

	Set<RemovableVanillaBiome> getDisabledVanillaBiomes() {
		return EnumSet.copyOf(disabledVanillaBiomes);
	}

	Set<Extrabiome> getEnabledBiomes() {
		return EnumSet.copyOf(enabledBiomes);
	}

	public int getId(final BaseBlock block) {
		if (isClassicMode())
			return 0;
		return baseBlockIds.get(block);
	}

	public int getId(CustomItem item) {
		return itemIds.get(item);
	}

	int getId(final Extrabiome biome) {
		switch (biome){
		case ALPINE:
			return 32;
		case AUTUMN_WOODS:
			return 33;
		case BIRCH_FOREST:
			return 34;
		case EXTREME_JUNGLE:
			return 35;
		case FORESTED_HILLS:
			return 36;
		case FORESTED_ISLAND:
			return 37;
		case GLACIER:
			return 38;
		case GREEN_HILLS:
			return 39;
		case GREEN_SWAMP:
			return 40;
		case ICE_WASTELAND:
			return 41;
		case MARSH:
			return 42;
		case MEADOW:
			return 23;
		case MINI_JUNGLE:
			return 44;
		case MOUNTAIN_DESERT:
			return 45;
		case MOUNTAIN_RIDGE:
			return 46;
		case MOUNTAIN_TAIGA:
			return 47;
		case PINE_FOREST:
			return 48;
		case RAINFOREST:
			return 49;
		case REDWOOD_FOREST:
			return 50;
		case REDWOOD_LUSH:
			return 51;
		case SAVANNA:
			return 52;
		case SHRUBLAND:
			return 53;
		case SNOW_FOREST:
			return 54;
		case SNOWY_RAINFOREST:
			return 55;
		case TEMPORATE_RAINFOREST:
			return 56;
		case TUNDRA:
			return 57;
		case WASTELAND:
			return 58;
		case WOODLANDS:
			return 59;
		}
		return 0;
	}

	void initialize() {
		if (initialized)
			return;

		initialized = true;

		Configuration config = new Configuration(CONF_FILE);
		config.load();

		// load block id for each custom block
		final int BASE_DEFAULT_BLOCK_ID = 150;
		for (BaseBlock b : BaseBlock.values())
			baseBlockIds.put(b, Integer.parseInt(config
					.getOrCreateBlockIdProperty(formatKey(b) + ".id",
							b.ordinal() + BASE_DEFAULT_BLOCK_ID).value));

		Property property = config.getOrCreateIntProperty("redrock.id",
				Configuration.CATEGORY_BLOCK, 152);
		property.comment = "If using a 4096 blockID patch, do not set redrock,id above 255. Doing so will cause unpredictable results.";
		property = config.getOrCreateIntProperty("crackedsand.id",
				Configuration.CATEGORY_BLOCK, 153);
		property.comment = "If using a 4096 blockID patch, do not set crackedsand.id above 255. Doing so will cause unpredictable results.";

		// remove biomeIDs from config file
		Map<String, Property> biomeProperties = config.categories.get(CATEGORY_BIOME.toLowerCase());
		if (biomeProperties != null)
			for (Extrabiome biome : Extrabiome.values()) {
				biomeProperties.remove(formatKey(biome) + ".id");
			}
			
		// load item id for each custom item
		final int BASE_DEFAULT_ITEM_ID = 2870;
		for (CustomItem item : CustomItem.values())
			itemIds.put(item, Integer.parseInt(config.getOrCreateIntProperty(
					formatKey(item) + ".id", Configuration.CATEGORY_ITEM,
					item.ordinal() + BASE_DEFAULT_ITEM_ID).value));

		// load vanilla biome switches from BIOME section
		for (RemovableVanillaBiome biome : RemovableVanillaBiome.values())
			if (Boolean.parseBoolean(config.getOrCreateBooleanProperty(
					"vanilla." + formatKey(biome) + ".disable", CATEGORY_BIOME,
					false).value))
				disabledVanillaBiomes.add(biome);

		// load switches from BIOME section
		for (Extrabiome biome : Extrabiome.values())
			if (Boolean.parseBoolean(config.getOrCreateBooleanProperty(
					formatKey(biome) + ".enable", CATEGORY_BIOME, true).value))
				enabledBiomes.add(biome);

		// load switches from BIOME section
		for (Extrabiome biome : Extrabiome.values())
			if (Boolean.parseBoolean(config.getOrCreateBooleanProperty(
					formatKey(biome) + ".allow.village", CATEGORY_BIOME,
					getDefaulBiomeVillageSpawnSetting(biome)).value))
				villageSpawnBiomes.add(biome);

		// load switches from GENERAL section
		for (ConfigSwitch s : ConfigSwitch.values())
			if (Boolean.parseBoolean(config.getOrCreateBooleanProperty(
					formatKey(s) + ".enable", Configuration.CATEGORY_GENERAL,
					s.getDefault()).value))
				configSwitches.add(s);

		config.save();
	}

	public boolean isClassicMode() {
		return state(ConfigSwitch.CLASSIC_MODE);
	}

	boolean isEnabled(Extrabiome biome) {
		if (biome == null)
			return false;
		return enabledBiomes.contains(biome);
	}

	boolean isEnabled(RemovableVanillaBiome biome) {
		if (biome == null)
			return false;
		return !disabledVanillaBiomes.contains(biome);
	}

	boolean state(ConfigSwitch s) {
		if (s == null)
			return false;
		return configSwitches.contains(s);
	}

	public void deselectBiomes(Collection<Extrabiome> biomesToDisable) {
		enabledBiomes.removeAll(biomesToDisable);
	}
}
