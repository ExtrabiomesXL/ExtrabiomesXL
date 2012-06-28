package extrabiomes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.Set;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.ModLoader;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.ExtrabiomesAPIInitializer;
import extrabiomes.api.Flora;
import extrabiomes.api.RemovableVanillaBiome;

public enum BiomeControl {
	INSTANCE;
	
	static private void configureCustomBiomes() {

		Log.write("Creating Extrabiomes custome biomes!");
		// Add biomes with configured ids as enabled per config file and AddOns.
		Set<Extrabiome> enabledBiomes = Options.INSTANCE.getEnabledBiomes();
		for (Extrabiome biome : enabledBiomes){
			Log.write(biome.toString());
			ModLoader.addBiome(createBiome(biome));
		}

	}

	static void configureVanillaBiomes() {
		doAddonsSetDisabledVanillaBiomes();
		disableVanillaBiomes(Options.INSTANCE.getDisabledVanillaBiomes());
	}

	private static BiomeGenBase createBiome(Extrabiome biome) {
		if (biome == null)
			throw new IllegalArgumentException("Cannot create null biome.");

		final int id = Options.INSTANCE.getId(biome);
		final BiomeGenBase bgb;

		switch (biome) {
		case ALPINE:
			bgb = new BiomeAlpine(id);
			break;
		case AUTUMN_WOODS:
			bgb = new BiomeAutumnWoods(id);
			break;
		case BIRCH_FOREST:
			bgb = new BiomeBirchForest(id);
			break;
		case EXTREME_JUNGLE:
			bgb = new BiomeExtremeJungle(id);
			break;
		case FORESTED_HILLS:
			bgb = new BiomeForestedHills(id);
			break;
		case GLACIER:
			bgb = new BiomeGlacier(id);
			break;
		case GREEN_HILLS:
			bgb = new BiomeGreenHills(id);
			break;
		case GREEN_SWAMP:
			bgb = new BiomeGreenSwamp(id);
			break;
		case ICE_WASTELAND:
			bgb = new BiomeIceWasteland(id);
			break;
		case MARSH:
			bgb = new BiomeMarsh(id);
			break;
		case MEADOW:
			bgb = new BiomeMeadow(id);
			break;
		case MINI_JUNGLE:
			bgb = new BiomeMiniJungle(id);
			break;
		case MOUNTAIN_DESERT:
			bgb = new BiomeMountainDesert(id);
			break;
		case MOUNTAIN_RIDGE:
			bgb = new BiomeMountainRidge(id);
			break;
		case MOUNTAIN_TAIGA:
			bgb = new BiomeMountainTaiga(id);
			break;
		case PINE_FOREST:
			bgb = new BiomePineForest(id);
			break;
		case RAINFOREST:
			bgb = new BiomeRainforest(id);
			break;
		case REDWOOD_FOREST:
			bgb = new BiomeRedwoodForest(id);
			break;
		case REDWOOD_LUSH:
			bgb = new BiomeRedwoodLush(id);
			break;
		case SAVANNA:
			bgb = new BiomeSavanna(id);
			break;
		case SHRUBLAND:
			bgb = new BiomeShrubland(id);
			break;
		case SNOW_FOREST:
			bgb = new BiomeSnowForest(id);
			break;
		case SNOWY_RAINFOREST:
			bgb = new BiomeSnowRainforest(id);
			break;
		case TEMPORATE_RAINFOREST:
			bgb = new BiomeTemporateRainforest(id);
			break;
		case TUNDRA:
			bgb = new BiomeTundra(id);
			break;
		case WASTELAND:
			bgb = new BiomeWasteland(id);
			break;
		case WOODLANDS:
			bgb = new BiomeWoodlands(id);
			break;
		default:
			bgb = BiomeGenBase.plains;
		}

		return bgb;
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
				Log.write("Disabling  vanilla \"" + biomeToRemove.biomeName + "\".");
				ModLoader.removeBiome(biomeToRemove);}
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
						FloraControl.INSTANCE.disableFloraForBiome(biome, floraToDisable);
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
			
			Log.write("Disabling Extrabiomes biomes for API client mod: " + addOn.getName());
			
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
		
		MapGenVillage.villageSpawnBiomes = new ArrayList(BiomeGenBase.biomeList.length);
		MapGenVillage.villageSpawnBiomes.add(BiomeGenBase.plains);
		MapGenVillage.villageSpawnBiomes.add(BiomeGenBase.desert);

		configureCustomBiomes();
		
	}
}
