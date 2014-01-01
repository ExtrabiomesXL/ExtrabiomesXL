package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class EBXLAridSelector extends NewDawnBiomeSelector {
	protected static final NewDawnBiome biomeAlpine = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.ALPINE);
	protected static final NewDawnBiome biomeGlacier = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.GLACIER);
    protected static final NewDawnBiome biomeMountainDesert = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.MOUNTAINDESERT);
    protected static final NewDawnBiome biomeMountainRidge = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.MOUNTAINRIDGE);
    protected static final NewDawnBiome biomeSavannah = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.SAVANNA);    
    protected static final NewDawnBiome biomeTundra = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.TUNDRA);
	protected static final NewDawnBiome biomeWasteland = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.WASTELAND);
	protected static final NewDawnBiome biomeIceWasteland = NewDawnPlugin.getBiomeIfEnabled(BiomeSettings.ICEWASTELAND);
	
    protected NoiseStretch stretchAlpine;
    protected NoiseStretch stretchGlacier;
    protected NoiseStretch stretchMountain;
    protected NoiseStretch stretchSavannah;
    protected NoiseStretch stretchTundra;
	protected NoiseStretch stretchWasteland;
    
	public EBXLAridSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
		
		stretchAlpine = worldNoise.generateNoiseStretcher(256, 256, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchGlacier = worldNoise.generateNoiseStretcher(256, 128, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchMountain = worldNoise.generateNoiseStretcher(384, 384, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchSavannah = worldNoise.generateNoiseStretcher(512, 512, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchTundra = worldNoise.generateNoiseStretcher(512, 512, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
		stretchWasteland = worldNoise.generateNoiseStretcher(384, 384, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
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
		} else if( stretchSavannah.getNoise(blockX, blockZ) > 0 ) {
			return biomeSavannah;
		} else if( stretchWasteland.getNoise(blockX, blockZ) > 0 )
			return biomeWasteland;
		
		return null;
	}

}
