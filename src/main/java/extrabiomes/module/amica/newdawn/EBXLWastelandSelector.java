package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.NoiseStretch;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.lib.BiomeSettings;

public class EBXLWastelandSelector extends NewDawnBiomeSelector {
	protected static final NewDawnBiome biomeWasteland = NewDawnBiome.copyVanilla(BiomeSettings.WASTELAND.getBiome().get());
	protected static final NewDawnBiome biomeIceWasteland = NewDawnBiome.copyVanilla(BiomeSettings.ICEWASTELAND.getBiome().get());
	
	protected NoiseStretch stretchWasteland;
	
	public EBXLWastelandSelector(SimplexNoise worldNoise, int priority) {
		super(worldNoise, priority);
		stretchWasteland = worldNoise.generateNoiseStretcher(512, 512, worldNoise.getRandom().nextDouble(), worldNoise.getRandom().nextDouble());
	}

	@Override
	public NewDawnBiome selectBiome(int blockX, int blockZ,
			ChunkInformation chunkInfo) {
		if( chunkInfo.isDeepWater(blockX, blockZ) ||
			stretchWasteland.getNoise(blockX,blockZ) < 0 )
				return null;
		if( chunkInfo.isTemperatureFreezing(blockX, blockZ) )
			return biomeIceWasteland;
		return biomeWasteland;
	}

}
