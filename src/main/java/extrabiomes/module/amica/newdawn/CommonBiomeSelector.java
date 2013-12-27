package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class CommonBiomeSelector extends NewDawnBiomeSelector {

	protected static final NewDawnBiome biomeMountainTaiga = NewDawnBiome.copyVanilla(BiomeSettings.MOUNTAINTAIGA.getBiome().get());
	protected static final NewDawnBiome biomeSavannah = NewDawnBiome.copyVanilla(BiomeSettings.SAVANNA.getBiome().get());;
	//protected static final NewDawnBiome biomeMountainRidge = null;
	//protected static final NewDawnBiome biomeMountainDesert = null;
	//protected static final NewDawnBiome biomeRedwoodForest = null;
	//protected static final NewDawnBiome biomeTemperateRainforest = null;
	protected static final NewDawnBiome biomeGreenHills = NewDawnBiome.copyVanilla(BiomeSettings.GREENHILLS.getBiome().get());;
	protected static final NewDawnBiome biomeRainforest = NewDawnBiome.copyVanilla(BiomeSettings.RAINFOREST.getBiome().get());;
	//protected static final NewDawnBiome biomeForestedHills = null;
	protected static final NewDawnBiome biomeAutumnForest = NewDawnBiome.copyVanilla(BiomeSettings.AUTUMNWOODS.getBiome().get());;
	protected static final NewDawnBiome biomePineForest = NewDawnBiome.copyVanilla(BiomeSettings.PINEFOREST.getBiome().get());;
	protected static final NewDawnBiome biomeWoodlands = NewDawnBiome.copyVanilla(BiomeSettings.WOODLANDS.getBiome().get());;
	protected static final NewDawnBiome biomeShrubland = NewDawnBiome.copyVanilla(BiomeSettings.SHRUBLAND.getBiome().get());;
	protected static final NewDawnBiome biomeMeadow = NewDawnBiome.copyVanilla(BiomeSettings.MEADOW.getBiome().get());;
	
	public CommonBiomeSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
	}

	@Override
	public boolean isApplicable(int blockX, int blockZ,
			ChunkInformation chunkInfo) {
		return chunkInfo.isAboveSeaLevel(blockX, blockZ);
	}

	@Override
	public NewDawnBiome selectBiome(int blockX, int blockZ,
			ChunkInformation chunkInfo) {
		boolean isCold = chunkInfo.isTemperatureFreezing(blockX, blockZ);
		boolean isHot = chunkInfo.isTemperatureHot(blockX, blockZ);
		boolean isWet = chunkInfo.isHumidityWet(blockX, blockZ);
		boolean isDry = chunkInfo.isHumiditySparse(blockX, blockZ);
		boolean isHigh = chunkInfo.isMountain(blockX, blockZ);
		boolean isForest = chunkInfo.isWoodland(blockX, blockZ);
		
		if( isCold ) {
			if( isHigh ) {
				return biomeMountainTaiga;
			}
		} else if( isHot ) {
			if( isDry ) {
				return biomeSavannah;
				//return biomeMountainRidge;
				//return biomeMountainDesert;
			}
		} else {
			// normal temperature
			if( isWet ) {
				if( isForest ) {
					//return biomeRedwoodForest;
					//return biomeTemperateRainforest;
					return biomeRainforest;
				} else {
					return biomeGreenHills;
				}
			} else {
				if( isForest ) {
					//return biomeForestedHills;
					if( chunkInfo.isTemperatureFreezing(blockX, blockZ, -0.5F))
						return biomeAutumnForest;
					else
						return biomePineForest;
					//return biomeWoodlands;
				} else {
					return biomeShrubland;
				}
			}
		}
		// failover to meadow or woodland
		if( isForest )
			return biomeWoodlands;
		else
			return biomeMeadow;
	}

}
