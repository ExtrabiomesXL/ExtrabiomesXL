/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import extrabiomes.ExtrabiomesLog;

class VillageSpawnHelper {

	private static boolean	villageSpawnBiomeChangesEnabled	= false;

	private static void addVillageSpawnBiome(BiomeGenBase biome) {
		if (!villageSpawnBiomeChangesEnabled)
			enableVillageSpawnBiomeChanges();
		if (MapGenVillage.villageSpawnBiomes.add(biome))
			ExtrabiomesLog
					.info("Enabled village spawning for \"%s\" biome per config file settings.",
							biome.biomeName);
	}

	private static void enableVillageSpawnBiomeChanges() {
		if (!villageSpawnBiomeChangesEnabled) {
			final List<BiomeGenBase> villageSpawnBiomes = new ArrayList();
			villageSpawnBiomes.addAll(MapGenVillage.villageSpawnBiomes);
			MapGenVillage.villageSpawnBiomes = villageSpawnBiomes;
			villageSpawnBiomeChangesEnabled = true;
		}
	}

	private static void removeVillageSpawnBiome(BiomeGenBase biome) {
		if (!villageSpawnBiomeChangesEnabled)
			enableVillageSpawnBiomeChanges();
		if (MapGenVillage.villageSpawnBiomes.remove(biome))
			ExtrabiomesLog
					.info("Disabled village spawning for \"%s\" biome per config file settings.",
							biome.biomeName);
	}

	static void setVillageSpawn(BiomeGenBase biome, boolean spawnVillage)
	{
		if (spawnVillage)
			addVillageSpawnBiome(biome);
		else
			removeVillageSpawnBiome(biome);
	}

}
