package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;

public class BiomeRainforest extends BiomeBase {

	private static final String NAME = "Rainforest";
	private static final Extrabiome BIOME = Extrabiome.RAINFOREST;

	public BiomeRainforest() {
		super(BIOME);

		setColor(0x0BD626);
		setBiomeName(NAME);
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.4F;
		maxHeight = 1.3F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(7)
				.setFlowersPerChunk(2).setGrassPerChunk(4);
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
