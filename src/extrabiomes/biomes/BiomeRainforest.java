package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeRainforest extends ExtrabiomeGenBase {

	public BiomeRainforest(int id) {
		super(id);

		setColor(0x0BD626);
		setBiomeName("Rainforest");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.4F;
		maxHeight = 1.3F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.grassPerChunk(4).flowersPerChunk(2).build();
	}

	@Override
	public WorldGenerator func_48410_b(Random rand) {
		if (rand.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);

		return super.func_48410_b(rand);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {

		if (rand.nextInt(99999) == 0)
			return worldGenForest;
		if (rand.nextInt(4) == 0)
			return worldGenBigTree;

		return worldGenTrees;
	}

}
