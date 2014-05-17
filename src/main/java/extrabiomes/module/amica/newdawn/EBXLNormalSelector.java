package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class EBXLNormalSelector extends NewDawnBiomeSelector {
	protected static final NewDawnBiome biomeAutumnWoods = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.AUTUMNWOODS);
	protected static final NewDawnBiome biomeBirchForest = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.BIRCHFOREST);
	protected static final NewDawnBiome biomeForestedHills = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.FORESTEDHILLS);
	protected static final NewDawnBiome biomeMeadow = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.MEADOW);
	protected static final NewDawnBiome biomeMountainTaiga = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.MOUNTAINTAIGA);
	protected static final NewDawnBiome biomePineForest = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.PINEFOREST);
    protected static final NewDawnBiome biomeShrubland = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.SHRUBLAND);
    protected static final NewDawnBiome biomeSnowForest = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.SNOWYFOREST);
    protected static final NewDawnBiome biomeWoodlands = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.WOODLANDS);
	
    protected NoiseStretch stretchAutumn;
    protected NoiseStretch stretchBirch;
    protected NoiseStretch stretchForest;
    protected NoiseStretch stretchSnowy;
    protected NoiseStretch stretchMeadow;
    
	public EBXLNormalSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
		
		stretchForest = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.FOREST.getStretchSize(),  worldNoise);
		stretchAutumn = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.AUTUMN.getStretchSize(),  worldNoise);
		stretchSnowy = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.SNOWY.getStretchSize(),  worldNoise);
		stretchBirch = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.BIRCH.getStretchSize(),  worldNoise);
		stretchMeadow = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.MEADOW.getStretchSize(),  worldNoise);
	}

	@Override
	public NewDawnBiome selectBiome(int blockX, int blockZ,
			ChunkInformation chunkInfo) {
		if( chunkInfo.isBelowGroundLevel(blockX, blockZ) ||
			!chunkInfo.isHumidityMedium(blockX, blockZ) ) return null;
		
		final boolean isForest = chunkInfo.isWoodland(blockX, blockZ);
		final boolean isCold = chunkInfo.isTemperatureFreezing(blockX, blockZ);
		final boolean isCool = chunkInfo.isTemperatureFreezing(blockX, blockZ, -0.25f);
		
		if( isForest ) {
			if( isCold ) {
				final double snowNoise = stretchSnowy.getNoise(blockX,blockZ); 
				if( snowNoise > 0 ) {
					if( chunkInfo.isMountain(blockX, blockZ) )
						return biomeMountainTaiga;
					else if( snowNoise > 0.5 )
						return biomeSnowForest;
					else
						return biomePineForest;
				}
			} else if( isCool ) {
				if( stretchAutumn.getNoise(blockX, blockZ) > 0 )
					return biomeAutumnWoods;
			} else {
				if( chunkInfo.getAverageHeight() < 100 &&
					stretchBirch.getNoise(blockX, blockZ) > 0 ) {
						return biomeBirchForest;
				} else if( stretchForest.getNoise(blockX, blockZ) > 0 ) {
					if( chunkInfo.getAverageHeight() > 100 )
						return biomeForestedHills;
					else
						return biomeWoodlands;
				}
			}
		} else {
			final double noise = stretchMeadow.getNoise(blockX, blockZ);
			if( noise > 0.6 )
				return biomeShrubland;
			else if( noise > 0 )
				return biomeMeadow;
		}
		
		return null;
	}

}
