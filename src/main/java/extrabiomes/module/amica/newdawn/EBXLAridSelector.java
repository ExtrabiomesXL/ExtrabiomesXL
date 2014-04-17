package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class EBXLAridSelector extends NewDawnBiomeSelector {
	protected static final NewDawnBiome biomeAlpine = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.ALPINE);
	protected static final NewDawnBiome biomeGlacier = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.GLACIER);
    protected static final NewDawnBiome biomeMountainDesert = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.MOUNTAINDESERT);
    protected static final NewDawnBiome biomeMountainRidge = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.MOUNTAINRIDGE);
    protected static final NewDawnBiome biomeSavanna = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.SAVANNA);    
    protected static final NewDawnBiome biomeTundra = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.TUNDRA);
	protected static final NewDawnBiome biomeWasteland = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.WASTELAND);
	protected static final NewDawnBiome biomeIceWasteland = NewDawnPluginImpl.getBiomeIfEnabled(BiomeSettings.ICEWASTELAND);
	
    protected NoiseStretch stretchAlpine;
    protected NoiseStretch stretchGlacier;
    protected NoiseStretch stretchMountain;
    protected NoiseStretch stretchSavanna;
    protected NoiseStretch stretchTundra;
	protected NoiseStretch stretchWasteland;
    
	public EBXLAridSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
		
		stretchGlacier = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.GLACIER.getStretchSize(),  worldNoise);
		stretchAlpine = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.ALPINE.getStretchSize(),  worldNoise);
		stretchMountain = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.MOUNTAIN.getStretchSize(),  worldNoise);
		stretchWasteland = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.WASTELAND.getStretchSize(),  worldNoise);
		stretchSavanna = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.SAVANNA.getStretchSize(),  worldNoise);
		stretchTundra = NewDawnPluginImpl.getFuzzyStretch(NewDawnSettings.TUNDRA.getStretchSize(),  worldNoise);
	}

	@Override
	public NewDawnBiome selectBiome(int blockX, int blockZ,
			ChunkInformation chunkInfo) {
		if( chunkInfo.isBelowGroundLevel(blockX, blockZ) ||
			!chunkInfo.isHumiditySparse(blockX, blockZ) ) return null;
		
		final boolean isMountain = chunkInfo.isMountain(blockX, blockZ);
		final boolean isFrigid = chunkInfo.getAverageTemperature() < 0.25;
		final boolean isCold = chunkInfo.isTemperatureFreezing(blockX, blockZ);
		
		if( isCold ) {
			if( isMountain ) {
				if( stretchAlpine.getNoise(blockX, blockZ) > 0 )
					return biomeAlpine;
				if( isFrigid && stretchGlacier.getNoise(blockX, blockZ) > 0 )
					return biomeGlacier;
			} else {
				if( stretchTundra.getNoise(blockX, blockZ) > 0 )
					return biomeTundra;
				else if( stretchWasteland.getNoise(blockX, blockZ) > 0 )
					return biomeIceWasteland;
			}
		} else if( isMountain ) {
			final double noise = stretchMountain.getNoise(blockX, blockZ);
			if( noise > 0.5 )
				return biomeMountainDesert;
			else if( noise > 0 )
				return biomeMountainRidge;
		} else if( stretchSavanna.getNoise(blockX, blockZ) > 0 ) {
			return biomeSavanna;
		} else if( stretchWasteland.getNoise(blockX, blockZ) > 0 )
			return biomeWasteland;
		
		return null;
	}

}
