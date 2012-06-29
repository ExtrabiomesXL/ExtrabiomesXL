
package extrabiomes.config;

import java.util.Iterator;

import net.minecraft.server.BiomeBase;
import extrabiomes.Proxy;
import extrabiomes.api.BiomeManager;

public class ConfigureVanillaBiomes {
	public static void disableVanillaBiomes() {
		BiomeBase biomebase;

		for (final Iterator iterator = BiomeManager.disabledVanillaBiomes
				.iterator(); iterator.hasNext(); Proxy
				.removeBiome(biomebase))
			biomebase = (BiomeBase) iterator.next();

		BiomeManager.disabledVanillaBiomes = null;
	}

	public static void initialize() {
		if (Config.getOrCreateBooleanProperty("vanilla.desert.disable",
				"BIOME", false))
			BiomeManager.disabledVanillaBiomes.add(BiomeBase.DESERT);

		if (Config.getOrCreateBooleanProperty(
				"vanilla.extremehills.disable", "BIOME", false))
			BiomeManager.disabledVanillaBiomes
					.add(BiomeBase.EXTREME_HILLS);

		if (Config.getOrCreateBooleanProperty("vanilla.forest.disable",
				"BIOME", false))
			BiomeManager.disabledVanillaBiomes.add(BiomeBase.FOREST);

		if (Config.getOrCreateBooleanProperty("vanilla.jungle.disable",
				"BIOME", false))
			BiomeManager.disabledVanillaBiomes.add(BiomeBase.JUNGLE);

		if (Config.getOrCreateBooleanProperty("vanilla.plains.disable",
				"BIOME", false))
			BiomeManager.disabledVanillaBiomes.add(BiomeBase.PLAINS);

		if (Config.getOrCreateBooleanProperty(
				"vanilla.swampland.disable", "BIOME", false))
			BiomeManager.disabledVanillaBiomes.add(BiomeBase.SWAMPLAND);

		if (Config.getOrCreateBooleanProperty("vanilla.taiga.disable",
				"BIOME", false))
			BiomeManager.disabledVanillaBiomes.add(BiomeBase.TAIGA);
	}

	public ConfigureVanillaBiomes() {}
}
