
package extrabiomes.config;

import extrabiomes.api.BiomeManager;
import extrabiomes.biomes.CustomBiomeManager;

public class ConfigureBiomeManager {
	public static void initialize() {
		BiomeManager.alpine = CustomBiomeManager.alpine;
		BiomeManager.autumnwoods = CustomBiomeManager.autumnWoods;
		BiomeManager.birchforest = CustomBiomeManager.birchForest;
		BiomeManager.extremejungle = CustomBiomeManager.extremeJungle;
		BiomeManager.forestedhills = CustomBiomeManager.forestedHills;
		BiomeManager.forestedisland = CustomBiomeManager.forestedIsland;
		BiomeManager.glacier = CustomBiomeManager.glacier;
		BiomeManager.greenhills = CustomBiomeManager.greenHills;
		BiomeManager.greenswamp = CustomBiomeManager.greenSwamp;
		BiomeManager.icewasteland = CustomBiomeManager.iceWasteland;
		BiomeManager.marsh = CustomBiomeManager.marsh;
		BiomeManager.meadow = CustomBiomeManager.meadow;
		BiomeManager.minijungle = CustomBiomeManager.miniJungle;
		BiomeManager.mountaindesert = CustomBiomeManager.mountainDesert;
		BiomeManager.mountainridge = CustomBiomeManager.mountainRidge;
		BiomeManager.mountaintaiga = CustomBiomeManager.mountainTaiga;
		BiomeManager.pineforest = CustomBiomeManager.pineForest;
		BiomeManager.rainforest = CustomBiomeManager.rainForest;
		BiomeManager.redwoodforest = CustomBiomeManager.redwoodForest;
		BiomeManager.redwoodlush = CustomBiomeManager.redwoodLush;
		BiomeManager.savanna = CustomBiomeManager.savanna;
		BiomeManager.shrubland = CustomBiomeManager.shrubLand;
		BiomeManager.snowforest = CustomBiomeManager.snowForest;
		BiomeManager.snowyrainforest = CustomBiomeManager.snowRainForest;
		BiomeManager.temperaterainforest = CustomBiomeManager.temperateRainForest;
		BiomeManager.tundra = CustomBiomeManager.tundra;
		BiomeManager.wasteland = CustomBiomeManager.wasteland;
		BiomeManager.woodlands = CustomBiomeManager.woodlands;
	}

	public ConfigureBiomeManager() {}
}
