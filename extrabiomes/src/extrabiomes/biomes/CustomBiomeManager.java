/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesConfig;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.ExtrabiomesBiome;
import extrabiomes.utility.ConfigSetting;
import net.minecraft.src.BiomeGenBase;

public class CustomBiomeManager {
	@ConfigSetting(key = "alpine.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableAlpine						= true;

	@ConfigSetting(key = "alpine.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableAlpineVillages				= true;

	@ConfigSetting(key = "autumnwoods.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableAutumnWoods					= true;

	@ConfigSetting(key = "autumnwoods.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableAutumnWoodsVillages			= true;

	@ConfigSetting(key = "birchforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableBirchForest					= true;

	@ConfigSetting(key = "birchforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableBirchForestVillages			= true;

	@ConfigSetting(key = "extremejungle.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableExtremeJungle					= true;

	@ConfigSetting(key = "extremejungle.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableExtremeJungleVillages			= true;

	@ConfigSetting(key = "forestedhills.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableForestedHills					= true;

	@ConfigSetting(key = "forestedhills.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableForestedHillsVillages			= true;

	@ConfigSetting(key = "forestedisland.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableForestedIsland				= true;

	@ConfigSetting(key = "forestedisland.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableForestedIslandVillages		= true;

	@ConfigSetting(key = "glacier.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableGlacier						= true;

	@ConfigSetting(key = "glacier.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableGlacierVillages				= true;

	@ConfigSetting(key = "greenhills.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableGreenHills					= true;

	@ConfigSetting(key = "greenhills.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableGreenHillsVillages			= true;

	@ConfigSetting(key = "greenswamp.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableGreenSwamp					= true;

	@ConfigSetting(key = "greenswamp.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableGreenSwampVillages			= true;

	@ConfigSetting(key = "icewasteland.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableIceWasteland					= true;

	@ConfigSetting(key = "icewasteland.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableIceWastelandVillages			= true;

	@ConfigSetting(key = "marsh.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMarsh							= true;

	@ConfigSetting(key = "marsh.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMarshVillages					= true;

	@ConfigSetting(key = "meadow.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMeadow						= true;

	@ConfigSetting(key = "meadow.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMeadowVillages				= true;

	@ConfigSetting(key = "minijungle.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMiniJungle					= true;

	@ConfigSetting(key = "minijungle.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMiniJungleVillages			= true;

	@ConfigSetting(key = "mountaindesert.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMountainDesert				= true;

	@ConfigSetting(key = "mountaindesert.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMountainDesertVillages		= true;

	@ConfigSetting(key = "mountainridge.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMountainRidge					= true;

	@ConfigSetting(key = "mountainridge.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMountainRidgeVillages			= true;

	@ConfigSetting(key = "mountaintaiga.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMountainTaiga					= true;

	@ConfigSetting(key = "mountaintaiga.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableMountainTaigaVillages			= true;

	@ConfigSetting(key = "pineforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enablePineForest					= true;

	@ConfigSetting(key = "pineforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enablePineForestVillages			= true;

	@ConfigSetting(key = "rainforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableRainForest					= true;

	@ConfigSetting(key = "rainforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableRainForestVillages			= true;

	@ConfigSetting(key = "redwoodforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableRedwoodForest					= true;

	@ConfigSetting(key = "redwoodforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableRedwoodForestVillages			= true;

	@ConfigSetting(key = "redwoodlush.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableRedwoodLush					= true;

	@ConfigSetting(key = "redwoodlush.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableRedwoodLushVillages			= true;

	@ConfigSetting(key = "savanna.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableSavanna						= true;

	@ConfigSetting(key = "savanna.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableSavannaVillages				= true;

	@ConfigSetting(key = "shrubland.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableShrubland						= true;

	@ConfigSetting(key = "shrubland.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableShrublandVillages				= true;

	@ConfigSetting(key = "snowyforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableSnowyForest					= true;

	@ConfigSetting(key = "snowyforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableSnowyForestVillages			= true;

	@ConfigSetting(key = "snowyrainforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableSnowyRainForest				= true;

	@ConfigSetting(key = "snowyrainforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableSnowyRainForestVillages		= true;

	@ConfigSetting(key = "temporaterainforest.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableTemperateRainForest			= true;

	@ConfigSetting(key = "temporaterainforest.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableTemperateRainForestVillages	= true;

	@ConfigSetting(key = "tundra.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableTundra						= true;

	@ConfigSetting(key = "tundra.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableTundraVillages				= true;

	@ConfigSetting(key = "wasteland.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableWasteland						= true;

	@ConfigSetting(key = "wasteland.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableWastelandVillages				= true;

	@ConfigSetting(key = "woodlands.enablegeneration", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableWoodland						= true;

	@ConfigSetting(key = "woodlands.allowvillages", category = ExtrabiomesConfig.CATEGORY_BIOME)
	private final boolean	enableWoodlandVillages				= true;

	private void addBiome(BiomeGenBase biome) {
		Extrabiomes.proxy.addBiome(biome);
		ExtrabiomesLog.info(
				"Enabled biome \"%s\" per config file settings.",
				biome.biomeName);
	}

	private void addEnabledBiomes() {
		if (enableAlpine) addBiome(ExtrabiomesBiome.alpine.get());
		if (enableAutumnWoods)
			addBiome(ExtrabiomesBiome.autumnwoods.get());
		if (enableBirchForest)
			addBiome(ExtrabiomesBiome.birchforest.get());
		if (enableExtremeJungle)
			addBiome(ExtrabiomesBiome.extremejungle.get());
		if (enableForestedHills)
			addBiome(ExtrabiomesBiome.forestedhills.get());
		if (enableForestedIsland)
			addBiome(ExtrabiomesBiome.forestedisland.get());
		if (enableGlacier) addBiome(ExtrabiomesBiome.glacier.get());
		if (enableGreenHills)
			addBiome(ExtrabiomesBiome.greenhills.get());
		if (enableGreenSwamp)
			addBiome(ExtrabiomesBiome.greenswamp.get());
		if (enableMarsh) addBiome(ExtrabiomesBiome.marsh.get());
		if (enableMeadow) addBiome(ExtrabiomesBiome.meadow.get());
		if (enableMiniJungle)
			addBiome(ExtrabiomesBiome.minijungle.get());
		if (enableMountainDesert)
			addBiome(ExtrabiomesBiome.mountaindesert.get());
		if (enableMountainRidge)
			addBiome(ExtrabiomesBiome.mountainridge.get());
		if (enableMountainTaiga)
			addBiome(ExtrabiomesBiome.mountaintaiga.get());
		if (enablePineForest)
			addBiome(ExtrabiomesBiome.pineforest.get());
		if (enableRainForest)
			addBiome(ExtrabiomesBiome.rainforest.get());
		if (enableRedwoodForest)
			addBiome(ExtrabiomesBiome.redwoodforest.get());
		if (enableRedwoodLush)
			addBiome(ExtrabiomesBiome.redwoodlush.get());
		if (enableSavanna) addBiome(ExtrabiomesBiome.savanna.get());
		if (enableShrubland)
			addBiome(ExtrabiomesBiome.shrubland.get());
		if (enableSnowyForest)
			addBiome(ExtrabiomesBiome.snowforest.get());
		if (enableSnowyRainForest)
			addBiome(ExtrabiomesBiome.snowyrainforest.get());
		if (enableTemperateRainForest)
			addBiome(ExtrabiomesBiome.temperaterainforest.get());
		if (enableTundra) addBiome(ExtrabiomesBiome.tundra.get());
		if (enableWasteland)
			addBiome(ExtrabiomesBiome.wasteland.get());
		if (enableWoodland) addBiome(ExtrabiomesBiome.woodlands.get());
	}

	public void applyConfigSettings() {
		addEnabledBiomes();
		setVillageConfiguration();
	}

	private void setVillageConfiguration() {
		if (enableAlpineVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.alpine.get());
		if (enableAutumnWoodsVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.autumnwoods
							.get());
		if (enableBirchForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.birchforest
							.get());
		if (enableExtremeJungleVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.extremejungle
							.get());
		if (enableForestedHillsVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.forestedhills
							.get());
		if (enableForestedIslandVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.forestedisland
							.get());
		if (enableGlacierVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.glacier
							.get());
		if (enableGreenHillsVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.greenhills
							.get());
		if (enableGreenSwampVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.greenswamp
							.get());
		if (enableMarshVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.marsh.get());
		if (enableMeadowVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.meadow.get());
		if (enableMiniJungleVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.minijungle
							.get());
		if (enableMountainDesertVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.mountaindesert
							.get());
		if (enableMountainRidgeVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.mountainridge
							.get());
		if (enableMountainTaigaVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.mountaintaiga
							.get());
		if (enablePineForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.pineforest
							.get());
		if (enableRainForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.rainforest
							.get());
		if (enableRedwoodForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.redwoodforest
							.get());
		if (enableRedwoodLushVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.redwoodlush
							.get());
		if (enableSavannaVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.savanna
							.get());
		if (enableShrublandVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.shrubland
							.get());
		if (enableSnowyForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.snowforest
							.get());
		if (enableSnowyRainForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.snowyrainforest
							.get());
		if (enableTemperateRainForestVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.temperaterainforest
							.get());
		if (enableTundraVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.tundra.get());
		if (enableWastelandVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.wasteland
							.get());
		if (enableWoodlandVillages)
			VillageSpawnHelper
					.addVillageSpawnBiome(ExtrabiomesBiome.woodlands
							.get());
	}

}
