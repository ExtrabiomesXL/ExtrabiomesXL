package extrabiomes.module.amica.newdawn;

import two.newdawn.API.ChunkInformation;
import two.newdawn.API.NewDawnBiome;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.SimplexNoise;

public class UncommonBiomeSelector extends NewDawnBiomeSelector {

	public UncommonBiomeSelector(SimplexNoise worldNoise, int priority) {
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
		// TODO Auto-generated method stub
		return null;
	}

}
