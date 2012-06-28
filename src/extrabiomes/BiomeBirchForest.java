package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BiomeBirchForest extends BiomeBase {

	private static final String NAME = "Birch Forest";
	private static final Extrabiome BIOME = Extrabiome.BIRCH_FOREST;
	
	public BiomeBirchForest() {
		super(BIOME);

		setColor(0x62BF6C);
		setBiomeName(NAME);
		temperature = 0.4F;
		rainfall = 0.7F;
		minHeight = 0.0F;
		maxHeight = 0.4F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(7)
				.setGrassPerChunk(1);
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
