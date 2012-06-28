package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenAcacia;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeSavanna extends ExtrabiomeGenBase {

	private WorldGenerator treeGen = null;

	public BiomeSavanna(int id) {
		super(id);

		setColor(0xBFA243);
		setBiomeName("Savanna");
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.flowersPerChunk(1).grassPerChunk(17).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (treeGen == null)
			if (TerrainGenManager.enableAcaciaGen)
				treeGen = new WorldGenAcacia(false);
			else
				treeGen = new WorldGenNoOp();

		return treeGen;
	}

}
