/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import java.util.Locale;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.common.config.Property;

import com.google.common.base.Optional;

import extrabiomes.module.summa.biome.BiomeAlpine;
import extrabiomes.module.summa.biome.BiomeAutumnWoods;
import extrabiomes.module.summa.biome.BiomeBirchForest;
import extrabiomes.module.summa.biome.BiomeExtremeJungle;
import extrabiomes.module.summa.biome.BiomeForestedHills;
import extrabiomes.module.summa.biome.BiomeForestedIsland;
import extrabiomes.module.summa.biome.BiomeGlacier;
import extrabiomes.module.summa.biome.BiomeGreenHills;
import extrabiomes.module.summa.biome.BiomeGreenSwamp;
import extrabiomes.module.summa.biome.BiomeIceWasteland;
import extrabiomes.module.summa.biome.BiomeMarsh;
import extrabiomes.module.summa.biome.BiomeMeadow;
import extrabiomes.module.summa.biome.BiomeMiniJungle;
import extrabiomes.module.summa.biome.BiomeMountainDesert;
import extrabiomes.module.summa.biome.BiomeMountainRidge;
import extrabiomes.module.summa.biome.BiomeMountainTaiga;
import extrabiomes.module.summa.biome.BiomePineForest;
import extrabiomes.module.summa.biome.BiomeRainforest;
import extrabiomes.module.summa.biome.BiomeRedwoodForest;
import extrabiomes.module.summa.biome.BiomeRedwoodLush;
import extrabiomes.module.summa.biome.BiomeSavanna;
import extrabiomes.module.summa.biome.BiomeShrubland;
import extrabiomes.module.summa.biome.BiomeSnowForest;
import extrabiomes.module.summa.biome.BiomeSnowRainforest;
import extrabiomes.module.summa.biome.BiomeTemporateRainforest;
import extrabiomes.module.summa.biome.BiomeTundra;
import extrabiomes.module.summa.biome.BiomeWasteland;
import extrabiomes.module.summa.biome.BiomeWoodlands;
import extrabiomes.module.summa.biome.ExtrabiomeGenBase;
import extrabiomes.utility.EnhancedConfiguration;
import extrabiomes.helpers.LogHelper;

public enum BiomeSettings {
	DESERT, EXTREMEHILLS, FOREST, JUNGLE, PLAINS, SWAMPLAND, TAIGA, OCEAN,
/* @formatter:off */
	ALPINE				(60, BiomeAlpine.class),
	AUTUMNWOODS			(61, BiomeAutumnWoods.class),
	BIRCHFOREST			(62, BiomeBirchForest.class),
	EXTREMEJUNGLE		(63, BiomeExtremeJungle.class),
	FORESTEDHILLS		(64, BiomeForestedHills.class),
	FORESTEDISLAND		(65, BiomeForestedIsland.class),
	GLACIER				(66, BiomeGlacier.class,				Weights.LIGHT),
	GREENHILLS			(67, BiomeGreenHills.class,				Weights.HEAVY),
	GREENSWAMP			(40, BiomeGreenSwamp.class,				Weights.HEAVY),
	ICEWASTELAND		(41, BiomeIceWasteland.class),
	MARSH				(-1, BiomeMarsh.class,					false),
	MEADOW				(43, BiomeMeadow.class,					Weights.HEAVY),
	MINIJUNGLE			(44, BiomeMiniJungle.class,				Weights.LIGHT),
	MOUNTAINDESERT		(45, BiomeMountainDesert.class),
	MOUNTAINRIDGE		(46, BiomeMountainRidge.class),
	MOUNTAINTAIGA		(47, BiomeMountainTaiga.class),
	PINEFOREST			(48, BiomePineForest.class),
	RAINFOREST			(49, BiomeRainforest.class),
	REDWOODFOREST		(50, BiomeRedwoodForest.class),
	REDWOODLUSH			(51, BiomeRedwoodLush.class),
	SAVANNA				(52, BiomeSavanna.class),
	SHRUBLAND			(53, BiomeShrubland.class),
	SNOWYFOREST			(54, BiomeSnowForest.class),
	SNOWYRAINFOREST		(55, BiomeSnowRainforest.class),
	TEMPORATERAINFOREST	(56, BiomeTemporateRainforest.class),
	TUNDRA				(57, BiomeTundra.class),
	WASTELAND			(58, BiomeWasteland.class,				Weights.LIGHT),
	WOODLANDS			(59, BiomeWoodlands.class,				Weights.HEAVY);
/* @formatter:on */

	private final int defaultID;

	private int biomeID = 0;
	private int weight = Weights.NORMAL.value;
	private boolean enabled = true;
	private boolean allowVillages = true;
	
	private final Optional<? extends Class<? extends BiomeGenBase>> biomeClass;
	private Optional<? extends BiomeGenBase> biome = Optional.absent();

	static {
		EXTREMEJUNGLE.allowVillages = false;
		GLACIER.allowVillages = false;
		RAINFOREST.allowVillages = false;
		REDWOODFOREST.allowVillages = false;
		REDWOODLUSH.allowVillages = false;
		SNOWYRAINFOREST.allowVillages = false;
		TEMPORATERAINFOREST.allowVillages = false;
	}

	private enum Weights {
		NONE(0), LIGHT(5), NORMAL(10), HEAVY(20);

		public int value;

		Weights(int value) {
			this.value = value;
		}
	}

	private BiomeSettings() {
		this(0, null);
	}

	private BiomeSettings(int defaultID, Class<? extends BiomeGenBase> biomeClass) {
		this.defaultID = defaultID;
		this.biomeID = this.defaultID;
		this.biomeClass = Optional.fromNullable(biomeClass);
	}

	private BiomeSettings(int defaultID, Class<? extends BiomeGenBase> biomeClass, Weights defaultWeight) {
		this(defaultID, biomeClass);
		this.weight = defaultWeight.value;
	}

	private BiomeSettings(int defaultID, Class<? extends BiomeGenBase> biomeClass, boolean defaultGen) {
		this(defaultID, biomeClass);
		this.enabled = defaultGen;
		if (!this.enabled) {
			this.weight = Weights.NONE.value;
		}
	}

	public static BiomeSettings findBiomeSettings(int id) {
		for (BiomeSettings settings : BiomeSettings.values()) {
			if (settings.biomeID == id)
				return settings;
		}
		return null;
	}

	public boolean allowVillages() {
		return allowVillages;
	}

	public void createBiome() throws Exception {
		if (biomeClass.isPresent() && !biome.isPresent()) {
			biome = Optional.of(biomeClass.get().newInstance());
		}
	}

	public void postLoad() {
		if (!isVanilla() && biome.isPresent()) {
			final ExtrabiomeGenBase egb = (ExtrabiomeGenBase) biome.get();
			BiomeDictionary.registerBiomeType(egb, egb.getBiomeTypeFlags());
			LogHelper.fine("registering " + this.name() + " with dictionary");

			// register ourselves with the biome manager
			BiomeManager.BiomeEntry entry = new BiomeManager.BiomeEntry(egb, weight);
			if (egb.temperature > 0.5f) {
				if (egb.isHighHumidity()) {
					BiomeManager.addBiome(BiomeType.WARM, entry);
				} else {
					BiomeManager.addBiome(BiomeType.DESERT, entry);
				}
			} else {
				if (egb.getEnableSnow()) {
					BiomeManager.addBiome(BiomeType.ICY, entry);
				} else {
					BiomeManager.addBiome(BiomeType.COOL, entry);
				}
			}

		} else {
			LogHelper.fine("NOT registering " + this.name() + " with dictionary, biome = " + biome);
		}
	}

	public Optional<? extends BiomeGenBase> getBiome() {
		return biome;
	}

	public int getID() {
		return biomeID;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isVanilla() {
		return !biomeClass.isPresent();
	}

	private String keyWeight() {
		return keyVanillaPrefix() + toString() + ".weight";
	}

	private String keyAllowVillages() {
		return keyVanillaPrefix() + toString() + ".allowvillages";
	}

	private String keyEnabled() {
		return keyVanillaPrefix() + toString() + ".enablegeneration";
	}

	private String keyID() {
		return toString() + ".id";
	}

	private String keyVanillaPrefix() {
		return isVanilla() ? "vanilla." : "";
	}

	public void load(EnhancedConfiguration configuration) {
		Property property;

		if (!isVanilla()) {
			property = configuration.getBiome(keyID(), biomeID);
			biomeID = property.getInt(0);
		}

		property = configuration.get(EnhancedConfiguration.CATEGORY_BIOME, keyEnabled(), enabled);
		if (!isVanilla() && biomeID < 1) {
			property.set(Boolean.toString(false));
		}
		enabled = property.getBoolean(false);

		if (!isVanilla() && biomeID > -1) {
			property = configuration.get(EnhancedConfiguration.CATEGORY_BIOME, keyWeight(), weight);
			weight = property.getInt(Weights.NONE.value);
		}

		if (enabled) {
			property = configuration.get(EnhancedConfiguration.CATEGORY_BIOME, keyAllowVillages(), allowVillages);
			if (!isVanilla() && biomeID < 1) {
				property.set(Boolean.toString(false));
			}
			allowVillages = property.getBoolean(false);
		}
	}

	@Override
	public String toString() {
		return super.toString().toLowerCase(Locale.ENGLISH);
	}

}
