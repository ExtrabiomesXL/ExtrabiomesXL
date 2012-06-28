package extrabiomes.config;

import net.minecraft.src.BiomeGenBase;
import extrabiomes.Proxy;
import extrabiomes.api.BiomeManager;

public class ConfigureVanillaBiomes {

	public static void initialize() {
		if (Config.getOrCreateBooleanProperty("vanilla.desert.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes.add(BiomeGenBase.desert);

		if (Config.getOrCreateBooleanProperty("vanilla.extremehills.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes
					.add(BiomeGenBase.extremeHills);

		if (Config.getOrCreateBooleanProperty("vanilla.forest.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes.add(BiomeGenBase.forest);

		if (Config.getOrCreateBooleanProperty("vanilla.jungle.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes.add(BiomeGenBase.jungle);

		if (Config.getOrCreateBooleanProperty("vanilla.plains.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes.add(BiomeGenBase.plains);

		if (Config.getOrCreateBooleanProperty("vanilla.swampland.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes
					.add(BiomeGenBase.swampland);

		if (Config.getOrCreateBooleanProperty("vanilla.taiga.disable",
				Config.CATEGORY_BIOME, false))
			BiomeManager.disabledVanillaBiomes.add(BiomeGenBase.taiga);
	}

	public static void disableVanillaBiomes() {
		for (BiomeGenBase biome : BiomeManager.disabledVanillaBiomes)
			Proxy.removeBiome(biome);
		BiomeManager.disabledVanillaBiomes = null;
	}

}
