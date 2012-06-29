
package extrabiomes.config;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.WorldGenVillage;
import extrabiomes.Proxy;
import extrabiomes.api.BiomeManager;

public class ConfigureCustomBiomes {
	private static void configureCustomBiome(String s,
			BiomeBase biomebase)
	{
		if (Config.getOrCreateBooleanProperty(new StringBuilder()
				.append(s).append(".enable").toString(), "BIOME", true))
		{
			BiomeManager.enabledCustomBiomes.add(biomebase);

			if (Config.getOrCreateBooleanProperty(new StringBuilder()
					.append(s).append(".allow.village").toString(),
					"BIOME", true))
				BiomeManager.villageSpawnBiomes.add(biomebase);
		}
	}

	public static void enableCustomBiomes() {
		BiomeBase biomebase;

		for (final Iterator iterator = BiomeManager.enabledCustomBiomes
				.iterator(); iterator.hasNext(); Proxy
				.addBiome(biomebase))
			biomebase = (BiomeBase) iterator.next();

		final ArrayList arraylist = new ArrayList();
		arraylist.addAll(WorldGenVillage.a);
		arraylist.addAll(BiomeManager.villageSpawnBiomes);
		WorldGenVillage.a = arraylist;
		BiomeManager.villageSpawnBiomes = null;
	}

	public static void initialize() {
		configureCustomBiome("alpine", BiomeManager.alpine);
		configureCustomBiome("autumnwoods", BiomeManager.autumnwoods);
		configureCustomBiome("birchforest", BiomeManager.birchforest);
		configureCustomBiome("extremejungle",
				BiomeManager.extremejungle);
		configureCustomBiome("forestedhills",
				BiomeManager.forestedhills);
		configureCustomBiome("forestedisland",
				BiomeManager.forestedisland);
		configureCustomBiome("glacier", BiomeManager.glacier);
		configureCustomBiome("greenhills", BiomeManager.greenhills);
		configureCustomBiome("greenswamp", BiomeManager.greenswamp);
		configureCustomBiome("icewasteland", BiomeManager.icewasteland);
		configureCustomBiome("marsh", BiomeManager.marsh);
		configureCustomBiome("meadow", BiomeManager.meadow);
		configureCustomBiome("minijungle", BiomeManager.minijungle);
		configureCustomBiome("mountaindesert",
				BiomeManager.mountaindesert);
		configureCustomBiome("mountainridge",
				BiomeManager.mountainridge);
		configureCustomBiome("mountaintaiga",
				BiomeManager.mountaintaiga);
		configureCustomBiome("pineforest", BiomeManager.pineforest);
		configureCustomBiome("rainforest", BiomeManager.rainforest);
		configureCustomBiome("redwoodforest",
				BiomeManager.redwoodforest);
		configureCustomBiome("redwoodlush", BiomeManager.redwoodlush);
		configureCustomBiome("savanna", BiomeManager.savanna);
		configureCustomBiome("shrubland", BiomeManager.shrubland);
		configureCustomBiome("snowforest", BiomeManager.snowforest);
		configureCustomBiome("snowyrainforest",
				BiomeManager.snowyrainforest);
		configureCustomBiome("temporaterainforest",
				BiomeManager.temperaterainforest);
		configureCustomBiome("tundra", BiomeManager.tundra);
		configureCustomBiome("wasteland", BiomeManager.wasteland);
		configureCustomBiome("woodlands", BiomeManager.woodlands);
	}

	public ConfigureCustomBiomes() {}
}
