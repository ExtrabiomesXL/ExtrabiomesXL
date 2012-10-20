/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Locale;

import net.minecraft.src.BiomeGenBase;
import net.minecraftforge.common.Property;
import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.configuration.ExtrabiomesConfig;

enum VanillaBiome {
	DESERT, EXTREMEHILLS, FOREST, JUNGLE, PLAINS, SWAMPLAND, TAIGA;

	private static boolean	settingsLoaded	= false;

	private static void createBiomes() throws InstantiationException,
			IllegalAccessException
	{
		for (final VanillaBiome biome : VanillaBiome.values()) {
			final BiomeGenBase biomeGen = biome.toBiomeGen();
			if (!biome.enableGeneration) {
				Extrabiomes.proxy.removeBiome(biomeGen);
				ExtrabiomesLog.info("Vanilla biome %s disabled.",
						biome.toString());
			}

			VillageSpawnHelper.setVillageSpawn(biomeGen,
					biome.enableVillages);
			ExtrabiomesLog.info(
					"Village spawning %s for vanilla biome %s.",
					biome.enableVillages ? "enabled" : "disabled",
					biome.toString());
		}
	}

	private static void loadSettings(ExtrabiomesConfig config) {
		settingsLoaded = true;

		for (final VanillaBiome biome : VanillaBiome.values()) {

			Property property = config.get(
					ExtrabiomesConfig.CATEGORY_BIOME,
					biome.enabledKey(), true);
			if (property.getBoolean(false))
				biome.enableGeneration = true;

			property = config.get(ExtrabiomesConfig.CATEGORY_BIOME,
					biome.villagesKey(), true);
			if (property.getBoolean(false))
				biome.enableVillages = true;
		}
	}

	static void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		if (settingsLoaded) return;

		loadSettings(config);
		createBiomes();
	}

	private boolean	enableGeneration	= false;

	private boolean	enableVillages		= false;

	private String enabledKey() {
		return "vanilla." + toString() + ".enablegeneration";
	}

	private BiomeGenBase toBiomeGen() {
		switch (this) {
			case DESERT:
				return BiomeGenBase.desert;
			case EXTREMEHILLS:
				return BiomeGenBase.extremeHills;
			case FOREST:
				return BiomeGenBase.forest;
			case JUNGLE:
				return BiomeGenBase.jungle;
			case SWAMPLAND:
				return BiomeGenBase.swampland;
			case TAIGA:
				return BiomeGenBase.taiga;
			default:
				return BiomeGenBase.plains;
		}
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.US);
	}

	private String villagesKey() {
		return "vanilla." + toString() + ".allowvillages";
	}
}
