package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenTaiga;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class BiomeMountainTaiga extends BiomeBase {

	private static final String NAME = "Mountain Taiga";
	private static final Extrabiome BIOME = Extrabiome.MOUNTAIN_TAIGA;

	public BiomeMountainTaiga() {
		super(BIOME);

		setColor(0xB6659);
		setBiomeName(NAME);
		temperature = 0.0F;
		rainfall = 0.2F;
		minHeight = 0.3F;
		maxHeight = 1.2F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(10)
				.setGrassPerChunk(1);
	}

	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		return (WorldGenerator) (random.nextInt(3) == 0 ? new WorldGenTaiga1()
				: new WorldGenTaiga2(false));
	}

}
