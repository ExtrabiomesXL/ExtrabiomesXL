package net.minecraft.src;

import java.util.Map;

import net.minecraft.src.forge.MinecraftForge;
import net.minecraft.src.forge.MinecraftForgeClient;
import extrabiomes.BiomeAlpine;
import extrabiomes.BiomeAutumnWoods;
import extrabiomes.BiomeBirchForest;
import extrabiomes.BiomeExtremeJungle;
import extrabiomes.BiomeForestedHills;
import extrabiomes.BiomeForestedIsland;
import extrabiomes.BiomeGlacier;
import extrabiomes.BiomeGreenHills;
import extrabiomes.BiomeGreenSwamp;
import extrabiomes.BiomeIceWasteland;
import extrabiomes.BiomeMarsh;
import extrabiomes.BiomeMeadow;
import extrabiomes.BiomeMiniJungle;
import extrabiomes.BiomeMountainDesert;
import extrabiomes.BiomeMountainRidge;
import extrabiomes.BiomeMountainTaiga;
import extrabiomes.BiomePineForest;
import extrabiomes.BiomeRainforest;
import extrabiomes.BiomeRedwoodForest;
import extrabiomes.BiomeRedwoodLush;
import extrabiomes.BiomeSavanna;
import extrabiomes.BiomeShrubland;
import extrabiomes.BiomeSnowForest;
import extrabiomes.BiomeSnowRainforest;
import extrabiomes.BiomeTemporateRainforest;
import extrabiomes.BiomeTundra;
import extrabiomes.BiomeWasteland;
import extrabiomes.BiomeWoodlands;
import extrabiomes.Extrabiomes;

public class mod_ExtraBiomesXL extends BaseMod {

	/*
	 * This causes all of the biomes to be statically instantiated to support
	 * Mystcraft.
	 */
	static BiomeGenBase[] biomeList = { new BiomeAlpine(),
			new BiomeAutumnWoods(), new BiomeBirchForest(),
			new BiomeExtremeJungle(), new BiomeForestedHills(),
			new BiomeForestedIsland(), new BiomeGlacier(),
			new BiomeGreenHills(), new BiomeGreenSwamp(),
			new BiomeIceWasteland(), new BiomeMarsh(), new BiomeMeadow(),
			new BiomeMiniJungle(), new BiomeMountainDesert(),
			new BiomeMountainRidge(), new BiomeMountainTaiga(),
			new BiomePineForest(), new BiomeRainforest(),
			new BiomeRedwoodForest(), new BiomeRedwoodLush(),
			new BiomeSavanna(), new BiomeShrubland(), new BiomeSnowForest(),
			new BiomeSnowRainforest(), new BiomeTemporateRainforest(),
			new BiomeTundra(), new BiomeWasteland(), new BiomeWoodlands() };

	@Override
	public int addFuel(int id, int damage) {
		return Extrabiomes.INSTANCE.addFuel(id, damage);
	}

	@Override
	public void addRenderer(Map map) {
		Extrabiomes.INSTANCE.addRenderer(map);
	}

	@Override
	public String getVersion() {
		return Extrabiomes.INSTANCE.getVersion();
	}

	@Override
	public void load() {
		MinecraftForge.versionDetect("Extrabiomes XL", 3, 1, 3);
		MinecraftForgeClient.preloadTexture("/extrabiomes/extrabiomes.png");
		Extrabiomes.INSTANCE.onModLoad();
	}

	@Override
	public void modsLoaded() {
		Extrabiomes.INSTANCE.onModsLoaded();
		super.modsLoaded();
	}
}
