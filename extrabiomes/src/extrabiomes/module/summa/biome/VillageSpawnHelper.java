/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;

enum VillageSpawnHelper {
	INSTANCE;

	private static boolean	villageSpawnBiomeChangesEnabled	= false;

	private static void addVillageSpawnBiome(BiomeGenBase biome) {
		if (!villageSpawnBiomeChangesEnabled)
			enableVillageSpawnBiomeChanges();
		MapGenVillage.villageSpawnBiomes.add(biome);
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
		MapGenVillage.villageSpawnBiomes.remove(biome);
	}

	static void setVillageSpawn(BiomeGenBase biome, boolean spawnVillage)
	{
		if (spawnVillage)
			addVillageSpawnBiome(biome);
		else
			removeVillageSpawnBiome(biome);
	}
}
