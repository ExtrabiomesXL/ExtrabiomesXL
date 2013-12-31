package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class EBXLNormalSelector extends NewDawnBiomeSelector {
	protected static final NewDawnBiome biomeAutumnWoods = NewDawnBiome.copyVanilla(BiomeSettings.AUTUMNWOODS.getBiome().get());
	protected static final NewDawnBiome biomeBirchForest = NewDawnBiome.copyVanilla(BiomeSettings.BIRCHFOREST.getBiome().get());
	protected static final NewDawnBiome biomeForestedHills = NewDawnBiome.copyVanilla(BiomeSettings.FORESTEDHILLS.getBiome().get());
	protected static final NewDawnBiome biomeMeadow = NewDawnBiome.copyVanilla(BiomeSettings.MEADOW.getBiome().get());
	protected static final NewDawnBiome biomeMountainTaiga = NewDawnBiome.copyVanilla(BiomeSettings.MOUNTAINTAIGA.getBiome().get());
	protected static final NewDawnBiome biomePineForest = NewDawnBiome.copyVanilla(BiomeSettings.PINEFOREST.getBiome().get());
    protected static final NewDawnBiome biomeShrubland = NewDawnBiome.copyVanilla(BiomeSettings.SHRUBLAND.getBiome().get());
    protected static final NewDawnBiome biomeSnowForest = NewDawnBiome.copyVanilla(BiomeSettings.SNOWYFOREST.getBiome().get());
    protected static final NewDawnBiome biomeWoodlands = NewDawnBiome.copyVanilla(BiomeSettings.WOODLANDS.getBiome().get());
	
    protected NoiseStretch stretchAutumn;
    protected NoiseStretch stretchBirch;
    protected NoiseStretch stretchForest;
    protected NoiseStretch stretchSnowy;
    protected NoiseStretch stretchMeadow;
    
	public EBXLNormalSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
		
		stretchAutumn = worldNoise.generateNoiseStretcher(384, 384, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchBirch = worldNoise.generateNoiseStretcher(512, 512, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchForest = worldNoise.generateNoiseStretcher(256, 256, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchSnowy = worldNoise.generateNoiseStretcher(384, 384, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchMeadow = worldNoise.generateNoiseStretcher(512, 512, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
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
