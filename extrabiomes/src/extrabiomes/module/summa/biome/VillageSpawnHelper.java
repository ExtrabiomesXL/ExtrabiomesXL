/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import extrabiomes.core.helper.LogHelper;

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

			// get MapGenVillageSpawnBiomes.villageSpawnBiomes field
			Field field = MapGenVillage.class.getDeclaredFields()[0];
			field.setAccessible(true);
			
			// Make the field non final and set it
			try {
				Field modifiersField = Field.class.getDeclaredField("modifiers");
				modifiersField.setAccessible(true);
				modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		    
				field.set(null, villageSpawnBiomes);
			} catch (Exception e) {
				LogHelper.fine("Could not access village spawn biomes.");
			}
			finally {
				villageSpawnBiomeChangesEnabled = true;
			}
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
