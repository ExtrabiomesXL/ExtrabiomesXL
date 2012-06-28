package extrabiomes;

import java.io.File;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.Configuration;
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

	private boolean initialized = false;
	final private Map<BaseBlock, Integer> baseBlockIds = new EnumMap<BaseBlock, Integer>(
			BaseBlock.class);
	final private Map<Extrabiome, Integer> biomeIds = new EnumMap<Extrabiome, Integer>(
			Extrabiome.class);
	final private Map<CustomItem, Integer> itemIds = new EnumMap<CustomItem, Integer>(
			CustomItem.class);
	final private Set<ConfigSwitch> configSwitches = EnumSet
			.noneOf(ConfigSwitch.class);
	final private Set<Extrabiome> enabledBiomes = EnumSet
			.noneOf(Extrabiome.class);

	final private Set<RemovableVanillaBiome> disabledVanillaBiomes = EnumSet
			.noneOf(RemovableVanillaBiome.class);

	void addDisabledVanillaBiomes(
			final Collection<RemovableVanillaBiome> biomesToDisable) {
		disabledVanillaBiomes.addAll(biomesToDisable);
	}

	void deselectBiomes(final Collection<Extrabiome> biomesToDeselect) {
		enabledBiomes.removeAll(biomesToDeselect);
		biomeIds.keySet().removeAll(biomesToDeselect);
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
		return biomeIds.get(biome);
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

		// load biome id for each custom biome
		final int BASE_DEFAULT_BIOME_ID = 32;
		for (Extrabiome biome : Extrabiome.values())
			biomeIds.put(biome, Integer.parseInt(config.getOrCreateIntProperty(
					formatKey(biome) + ".id", CATEGORY_BIOME, biome.ordinal()
							+ BASE_DEFAULT_BIOME_ID).value));

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
}
