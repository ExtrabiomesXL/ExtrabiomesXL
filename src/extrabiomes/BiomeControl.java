package extrabiomes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.ModLoader;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.ExtrabiomesAPIInitializer;
import extrabiomes.api.Flora;
import extrabiomes.api.RemovableVanillaBiome;
import extrabiomes.api.TerrainGenBlock;

public enum BiomeControl {
	INSTANCE;

	static private void configureCustomBiomes() {
		Log.write("Enabling Extrabiomes custom biomes!");
		// Add biomes with configured ids as enabled per config file and AddOns.
		Set<Extrabiome> enabledBiomes = Options.INSTANCE.getEnabledBiomes();
		for (Extrabiome biome : enabledBiomes) {
			Log.write(biome.toString());
			ModLoader.addBiome(ExtrabiomesAPIImpl.INSTANCE.getBiome(biome));
		}
	}

	static void configureVanillaBiomes() {
		doAddonsSetDisabledVanillaBiomes();
		disableVanillaBiomes(Options.INSTANCE.getDisabledVanillaBiomes());
	}

	static private void disableVanillaBiomes(Set<RemovableVanillaBiome> biomes) {
		// Remove vanilla biomes per config file and AddOns
		for (RemovableVanillaBiome biome : biomes) {
			BiomeGenBase biomeToRemove = null;

			switch (biome) {
			case DESERT:
				biomeToRemove = BiomeGenBase.desert;
				break;
			case FOREST:
				biomeToRemove = BiomeGenBase.forest;
				break;
			case EXTREMEHILLS:
				biomeToRemove = BiomeGenBase.extremeHills;
				break;
			case SWAMPLAND:
				biomeToRemove = BiomeGenBase.swampland;
				break;
			case PLAINS:
				biomeToRemove = BiomeGenBase.plains;
				break;
			case TAIGA:
				biomeToRemove = BiomeGenBase.taiga;
				break;
			case JUNGLE:
				biomeToRemove = BiomeGenBase.jungle;
				break;
			}

			if (biomeToRemove != null) {
				Log.write("Disabling  vanilla \"" + biomeToRemove.biomeName
						+ "\".");
				ModLoader.removeBiome(biomeToRemove);
			}
		}
	}

	static void doAddOnsAddBiomes() {
		final Set<AddOn> addOnCollection = new LinkedHashSet<AddOn>(
				AddOnControl.INSTANCE.getAddOns());
		for (AddOn addOn : addOnCollection) {
			ExtrabiomesAPIInitializer initializer = addOn.getInitializer();
			if (initializer == null)
				continue;

			Collection<BiomeGenBase> biomesToAdd = initializer
					.getCustomBiomes(ExtrabiomesAPIImpl.INSTANCE);

			if (biomesToAdd == null)
				continue;

			biomesToAdd = new LinkedHashSet<BiomeGenBase>(biomesToAdd);

			for (BiomeGenBase biome : biomesToAdd) {
				Log.write("Adding biome \"" + biome.biomeName
						+ "\" from API client mod:  " + addOn.getName() + ".");
				ModLoader.addBiome(biome);
			}
		}
	}

	static void doAddOnsDisableFlora() {
		final Set<AddOn> addOnCollection = new LinkedHashSet<AddOn>(
				AddOnControl.INSTANCE.getAddOns());
		for (AddOn addOn : addOnCollection) {
			ExtrabiomesAPIInitializer initializer = addOn.getInitializer();
			if (initializer == null)
				continue;

			for (Extrabiome biome : Extrabiome.values()) {
				Collection<Flora> disabledFloraFromAddOn = initializer
						.getDisabledFlora(biome);

				if (disabledFloraFromAddOn != null) {

					final Collection<Flora> floraToDisable = EnumSet
							.noneOf(Flora.class);
					floraToDisable.addAll(disabledFloraFromAddOn);
					disabledFloraFromAddOn = null;

					for (Flora flora : floraToDisable)
						FloraControl.INSTANCE.disableFloraForBiome(biome,
								floraToDisable);
				}
			}
		}
	}

	static void doAddonsSetDisabledCustomBiomes() {
		final Set<AddOn> addOnCollection = new LinkedHashSet<AddOn>(
				AddOnControl.INSTANCE.getAddOns());
		for (AddOn addOn : addOnCollection) {
			ExtrabiomesAPIInitializer initializer = addOn.getInitializer();
			if (initializer == null)
				continue;

			Collection<Extrabiome> disabledBiomesFromAddOn = initializer
					.getDisabledExtrabiomes();

			if (disabledBiomesFromAddOn == null)
				continue;

			Log.write("Disabling Extrabiomes biomes for API client mod: "
					+ addOn.getName());

			final Collection<Extrabiome> biomesToDisable = EnumSet
					.noneOf(Extrabiome.class);
			biomesToDisable.addAll(disabledBiomesFromAddOn);
			for (Extrabiome biome : biomesToDisable)
				Log.write(biome.toString());
			disabledBiomesFromAddOn = null;
			Options.INSTANCE.deselectBiomes(biomesToDisable);
		}
	}

	private static void doAddonsSetDisabledVanillaBiomes() {
		final Set<AddOn> addOnCollection = new LinkedHashSet<AddOn>(
				AddOnControl.INSTANCE.getAddOns());
		for (AddOn addOn : addOnCollection) {
			ExtrabiomesAPIInitializer initializer = addOn.getInitializer();
			if (initializer == null)
				continue;

			Collection<RemovableVanillaBiome> disabledBiomesFromAddOn = initializer
					.getDisabledVanillaBiomes();

			if (disabledBiomesFromAddOn == null)
				continue;

			final Collection<RemovableVanillaBiome> biomesToDisable = EnumSet
					.noneOf(RemovableVanillaBiome.class);
			biomesToDisable.addAll(disabledBiomesFromAddOn);
			disabledBiomesFromAddOn = null;
			Options.INSTANCE.addDisabledVanillaBiomes(biomesToDisable);
		}
	}

	private boolean initialized = false;

	public void initialize() {
		if (initialized)
			return;
		initialized = true;

		restructureVillageSpawnBiomes();
		configureCustomBiomes();

		MetaBlock redRock = BlockControl.INSTANCE
				.getTerrainGenBlock(TerrainGenBlock.RED_ROCK);
		BiomeGenBase biomeMountainRidge = ExtrabiomesAPIImpl.INSTANCE
				.getBiome(Extrabiome.MOUNTAIN_RIDGE);
		if (biomeMountainRidge != null && redRock != null) {
			biomeMountainRidge.topBlock = (byte) redRock.blockId();
			biomeMountainRidge.fillerBlock = (byte) redRock.blockId();
		}

		MetaBlock crackedSand = BlockControl.INSTANCE
				.getTerrainGenBlock(TerrainGenBlock.CRACKED_SAND);
		BiomeGenBase biomeWasteland = ExtrabiomesAPIImpl.INSTANCE
				.getBiome(Extrabiome.WASTELAND);
		if (biomeWasteland != null && crackedSand != null) {
			biomeWasteland.topBlock = (byte) crackedSand.blockId();
			biomeWasteland.fillerBlock = (byte) crackedSand.blockId();
		}
	}

	private static void restructureVillageSpawnBiomes() {
		List newVillageSpawnBiomes = new ArrayList(
				BiomeGenBase.biomeList.length);
		newVillageSpawnBiomes.addAll(MapGenVillage.villageSpawnBiomes);

		for (Extrabiome biome : Extrabiome.values())
			if (Options.INSTANCE.canSpawnVillage(biome))
				newVillageSpawnBiomes.add(ExtrabiomesAPIImpl.INSTANCE
						.getBiome(biome));

		MapGenVillage.villageSpawnBiomes = newVillageSpawnBiomes;
	}
}
