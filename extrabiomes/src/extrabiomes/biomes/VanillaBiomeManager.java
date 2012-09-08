/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeGenBase;
import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesConfig;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.utility.ConfigSetting;

public class VanillaBiomeManager {

	@ConfigSetting(key = "vanilla.desert.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaDesert					= true;

	@ConfigSetting(key = "vanilla.extremehills.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaExtremeHills			= true;

	@ConfigSetting(key = "vanilla.forest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaForest					= true;

	@ConfigSetting(key = "vanilla.jungle.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaJungle					= true;

	@ConfigSetting(key = "vanilla.plains.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaPlains					= true;

	@ConfigSetting(key = "vanilla.swampland.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaSwampland				= true;

	@ConfigSetting(key = "vanilla.taiga.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaTaiga					= true;

	@ConfigSetting(key = "vanilla.desert.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaDesertVillages			= true;

	@ConfigSetting(key = "vanilla.extremehills.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaExtremeHillsVillages	= true;

	@ConfigSetting(key = "vanilla.forest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaForestVillages			= true;

	@ConfigSetting(key = "vanilla.jungle.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaJungleVillages			= true;

	@ConfigSetting(key = "vanilla.plains.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaPlainsVillages			= true;

	@ConfigSetting(key = "vanilla.swampland.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaSwamplandVillages		= true;

	@ConfigSetting(key = "vanilla.taiga.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableVanillaTaigaVillages			= true;

	public void applyConfigSettings() {
		removeDisabledBiomes();
		setVanillaVillageConfiguration();
	}

	private void removeBiome(BiomeGenBase biome) {
		Extrabiomes.proxy.removeBiome(biome);
		ExtrabiomesLog
				.info("Disabled vanilla biome \"%s\" per config file settings.",
						biome.biomeName);
	}

	private void removeDisabledBiomes() {
		if (!enableVanillaDesert) removeBiome(BiomeGenBase.desert);

		if (!enableVanillaExtremeHills)
			removeBiome(BiomeGenBase.extremeHills);

		if (!enableVanillaForest) removeBiome(BiomeGenBase.forest);

		if (!enableVanillaJungle) removeBiome(BiomeGenBase.jungle);

		if (!enableVanillaPlains) removeBiome(BiomeGenBase.plains);

		if (!enableVanillaSwampland)
			removeBiome(BiomeGenBase.swampland);

		if (!enableVanillaTaiga) removeBiome(BiomeGenBase.taiga);
	}

	private void setVanillaVillageConfiguration() {
		if (enableVanillaDesertVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(BiomeGenBase.desert);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.desert);

		if (enableVanillaExtremeHillsVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(BiomeGenBase.extremeHills);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.extremeHills);

		if (enableVanillaForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(BiomeGenBase.forest);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.forest);

		if (enableVanillaJungleVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(BiomeGenBase.jungle);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.jungle);

		if (enableVanillaPlainsVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(BiomeGenBase.plains);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.plains);

		if (enableVanillaSwamplandVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(BiomeGenBase.swampland);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.swampland);

		if (enableVanillaTaigaVillages)
			VillageSpawnHelper.addVillageSpawnBiome(BiomeGenBase.taiga);
		else
			VillageSpawnHelper
					.removeVillageSpawnBiome(BiomeGenBase.taiga);
	}

}
