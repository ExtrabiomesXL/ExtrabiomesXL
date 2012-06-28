package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;
import extrabiomes.terrain.WorldGenRedwood;

public class BiomeRedwoodForest extends ExtrabiomeGenBase {

	private WorldGenerator treeGen = null;

	public BiomeRedwoodForest(int id) {
		super(id);

		setColor(0x0BD626);
		setBiomeName("Redwood Forest");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (treeGen == null)
			if (TerrainGenManager.enableRedwoodGen)
				treeGen = new WorldGenRedwood(false);
			else
				treeGen = new WorldGenNoOp();

		return treeGen;
	}

}
