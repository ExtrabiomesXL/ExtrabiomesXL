package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeBirchForest extends ExtrabiomeGenBase {

	public BiomeBirchForest(int id) {
		super(id);

		setColor(0x62BF6C);
		setBiomeName("Birch Forest");
		temperature = 0.4F;
		rainfall = 0.7F;
		minHeight = 0.0F;
		maxHeight = 0.4F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.grassPerChunk(1).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(100) == 0)
			if (rand.nextInt(100) == 0)
				return worldGenBigTree;
			else
				return worldGenTrees;
		return worldGenForest;
	}

}
