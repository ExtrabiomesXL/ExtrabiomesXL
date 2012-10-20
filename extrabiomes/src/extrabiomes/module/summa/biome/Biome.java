/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import net.minecraft.src.BiomeGenBase;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.configuration.ExtrabiomesConfig;

enum Biome {
	ALPINE(BiomeAlpine.class),
	AUTUMNWOODS(BiomeAutumnWoods.class),
	BIRCHFOREST(BiomeBirchForest.class),
	EXTREMEJUNGLE(BiomeExtremeJungle.class),
	FORESTEDHILLS(BiomeForestedHills.class),
	FORESTEDISLAND(BiomeForestedIsland.class),
	GLACIER(BiomeGlacier.class),
	GREENHILLS(BiomeGreenHills.class),
	GREENSWAMP(BiomeGreenSwamp.class),
	ICEWASTELAND(BiomeIceWasteland.class),
	MARSH(BiomeMarsh.class),
	MEADOW(BiomeMeadow.class),
	MINIJUNGLE(BiomeMiniJungle.class),
	MOUNTAINDESERT(BiomeMountainDesert.class),
	MOUNTAINRIDGE(BiomeMountainRidge.class),
	MOUNTAINTAIGA(BiomeMountainTaiga.class),
	PINEFOREST(BiomePineForest.class),
	RAINFOREST(BiomeRainforest.class),
	REDWOODFOREST(BiomeRedwoodForest.class),
	REDWOODLUSH(BiomeRedwoodLush.class),
	SAVANNA(BiomeSavanna.class),
	SHRUBLAND(BiomeShrubland.class),
	SNOWYFOREST(BiomeSnowForest.class),
	SNOWYRAINFOREST(BiomeSnowRainforest.class),
	TEMPORATERAINFOREST(BiomeTemporateRainforest.class),
	TUNDRA(BiomeTundra.class),
	WASTELAND(BiomeWasteland.class),
	WOODLANDS(BiomeWoodlands.class);

	private static boolean	settingsLoaded	= false;

	private static void createBiomes() throws InstantiationException,
			IllegalAccessException
	{
		for (final Biome biome : Biome.values()) {
			if (biome.biomeID > 0) {
				if (BiomeGenBase.biomeList[biome.biomeID] != null)
					throw new IllegalArgumentException(
							String.format(
									"Biome id %d is already in use by %s when adding %s. Please review the configuration file.",
									biome.biomeID,
									BiomeGenBase.biomeList[biome.biomeID].biomeName,
									biome.toString()));

				biome.biome = Optional.of(biome.biomeClass
						.newInstance());
			}
			if (biome.enableGeneration && biome.biome.isPresent())
				Extrabiomes.proxy.addBiome(biome.biome.get());
			else
				ExtrabiomesLog.info("Custom biome %s disabled.",
						biome.toString());
			if (biome.enableVillages && biome.biome.isPresent()) {
				VillageSpawnHelper.setVillageSpawn(biome.biome.get(),
						true);
				ExtrabiomesLog
						.info("Village spawning enabled for custom biome %s.",
								biome.toString());
			}
		}
	}

	static Collection<BiomeGenBase> getActive() {
		if (!activeBiomes.isPresent()) {
			activeBiomes = Optional.of(new ArrayList<BiomeGenBase>(
					Biome.values().length));
			for (final Biome biome : Biome.values())
				if (biome.biome.isPresent())
					activeBiomes.get().add(biome.biome.get());
		}
		return ImmutableSet.copyOf(activeBiomes.get());
	}

	private static void loadSettings(ExtrabiomesConfig config) {
		settingsLoaded = true;

		ExtrabiomesLog.info("===== Biome ID List =====");

		// Load config settings
		int defaultID = 32;
		for (final Biome biome : Biome.values()) {

			Property property = config.getBiome(biome.idKey(),
					defaultID++);
			biome.biomeID = property.getInt(0);

			ExtrabiomesLog.info("  %s: %d", biome.toString(),
					biome.biomeID);

			property = config.get(ExtrabiomesConfig.CATEGORY_BIOME,
					biome.enabledKey(), true);
			if (biome.biomeID == 0)
				property.value = Boolean.toString(false);
			if (property.getBoolean(false))
				biome.enableGeneration = true;

			property = config.get(ExtrabiomesConfig.CATEGORY_BIOME,
					biome.villagesKey(), true);
			if (biome.biomeID == 0)
				property.value = Boolean.toString(false);
			if (property.getBoolean(false))
				biome.enableVillages = true;
		}

		ExtrabiomesLog.info("=== End Biome ID List ===");
	}

	static void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		if (settingsLoaded) return;

		loadSettings(config);
		createBiomes();
	}

	private Optional<? extends BiomeGenBase>				biome				= Optional
																						.absent();

	private int												biomeID				= 0;
	private boolean											enableGeneration	= false;

	private boolean											enableVillages		= false;

	private final Class<? extends BiomeGenBase>				biomeClass;

	private static Optional<? extends List<BiomeGenBase>>	activeBiomes		= Optional
																						.absent();

	Biome(Class<? extends BiomeGenBase> biomeClass) {
		this.biomeClass = biomeClass;
	}

	private String enabledKey() {
		return toString() + ".enablegeneration";
	}

	Optional<? extends BiomeGenBase> getBiome() {
		return biome;
	}

	int getBiomeID() {
		return biomeID;
	}

	private String idKey() {
		return toString() + ".id";
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.US);
	}

	private String villagesKey() {
		return toString() + ".allowvillages";
	}
}
