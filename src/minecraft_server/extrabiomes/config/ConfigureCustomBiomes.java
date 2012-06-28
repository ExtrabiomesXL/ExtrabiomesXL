package extrabiomes.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import extrabiomes.Proxy;
import extrabiomes.api.BiomeManager;

public class ConfigureCustomBiomes {

	private static void configureCustomBiome(String biomeName,
			BiomeGenBase biome) {
		if (Config.getOrCreateBooleanProperty(biomeName + ".enable",
				Config.CATEGORY_BIOME, true)) {
			BiomeManager.enabledCustomBiomes.add(biome);

			if (Config.getOrCreateBooleanProperty(biomeName + ".allow.village",
					Config.CATEGORY_BIOME, true))
				BiomeManager.villageSpawnBiomes.add(biome);
		}
	}

	public static void enableCustomBiomes() {
		for (BiomeGenBase biome : BiomeManager.enabledCustomBiomes)
			Proxy.addBiome(biome);

		List<BiomeGenBase> newVillageSpawnBiomes = new ArrayList();
		newVillageSpawnBiomes.addAll(MapGenVillage.villageSpawnBiomes);
		newVillageSpawnBiomes.addAll(BiomeManager.villageSpawnBiomes);
		MapGenVillage.villageSpawnBiomes = newVillageSpawnBiomes;
		BiomeManager.villageSpawnBiomes = null;
	}

	public static void initialize() {
		configureCustomBiome("alpine", BiomeManager.alpine);
		configureCustomBiome("autumnwoods", BiomeManager.autumnwoods);
		configureCustomBiome("birchforest", BiomeManager.birchforest);
		configureCustomBiome("extremejungle", BiomeManager.extremejungle);
		configureCustomBiome("forestedhills", BiomeManager.forestedhills);
		configureCustomBiome("forestedisland", BiomeManager.forestedisland);
		configureCustomBiome("glacier", BiomeManager.glacier);
		configureCustomBiome("greenhills", BiomeManager.greenhills);
		configureCustomBiome("greenswamp", BiomeManager.greenswamp);
		configureCustomBiome("icewasteland", BiomeManager.icewasteland);
		configureCustomBiome("marsh", BiomeManager.marsh);
		configureCustomBiome("meadow", BiomeManager.meadow);
		configureCustomBiome("minijungle", BiomeManager.minijungle);
		configureCustomBiome("mountaindesert", BiomeManager.mountaindesert);
		configureCustomBiome("mountainridge", BiomeManager.mountainridge);
		configureCustomBiome("mountaintaiga", BiomeManager.mountaintaiga);
		configureCustomBiome("pineforest", BiomeManager.pineforest);
		configureCustomBiome("rainforest", BiomeManager.rainforest);
		configureCustomBiome("redwoodforest", BiomeManager.redwoodforest);
		configureCustomBiome("redwoodlush", BiomeManager.redwoodlush);
		configureCustomBiome("savanna", BiomeManager.savanna);
		configureCustomBiome("shrubland", BiomeManager.shrubland);
		configureCustomBiome("snowforest", BiomeManager.snowforest);
		configureCustomBiome("snowyrainforest", BiomeManager.snowyrainforest);
		configureCustomBiome("temporaterainforest",
				BiomeManager.temperaterainforest);
		configureCustomBiome("tundra", BiomeManager.tundra);
		configureCustomBiome("wasteland", BiomeManager.wasteland);
		configureCustomBiome("woodlands", BiomeManager.woodlands);
	}

}
