package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class EBXLNormalSelector extends NewDawnBiomeSelector {
	protected static final NewDawnBiome biomeAutumnWoods = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.AUTUMNWOODS);
	protected static final NewDawnBiome biomeBirchForest = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.BIRCHFOREST);
	protected static final NewDawnBiome biomeForestedHills = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.FORESTEDHILLS);
	protected static final NewDawnBiome biomeMeadow = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.MEADOW);
	protected static final NewDawnBiome biomeMountainTaiga = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.MOUNTAINTAIGA);
	protected static final NewDawnBiome biomePineForest = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.PINEFOREST);
    protected static final NewDawnBiome biomeShrubland = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.SHRUBLAND);
    protected static final NewDawnBiome biomeSnowForest = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.SNOWYFOREST);
    protected static final NewDawnBiome biomeWoodlands = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.WOODLANDS);
	
    protected NoiseStretch stretchAutumn;
    protected NoiseStretch stretchBirch;
    protected NoiseStretch stretchForest;
    protected NoiseStretch stretchSnowy;
    protected NoiseStretch stretchMeadow;
    
	public EBXLNormalSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
		
		stretchForest = NewDawnPlugin.getFuzzyStretch(NewDawnSettings.FOREST.getStretchSize(),  worldNoise);
		stretchAutumn = NewDawnPlugin.getFuzzyStretch(NewDawnSettings.AUTUMN.getStretchSize(),  worldNoise);
		stretchSnowy = NewDawnPlugin.getFuzzyStretch(NewDawnSettings.SNOWY.getStretchSize(),  worldNoise);
		stretchBirch = NewDawnPlugin.getFuzzyStretch(NewDawnSettings.BIRCH.getStretchSize(),  worldNoise);
		stretchMeadow = NewDawnPlugin.getFuzzyStretch(NewDawnSettings.MEADOW.getStretchSize(),  worldNoise);
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
