package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomePineForest extends ExtrabiomeGenBase {

	public BiomePineForest(int id) {
		super(id);

		setColor(0x469C7E);
		setBiomeName("Pine Forest");
		temperature = BiomeGenBase.forest.temperature;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = 0.1F;
		maxHeight = 0.3F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(10)
				.grassPerChunk(1).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return rand.nextInt(3) == 0 ? new WorldGenTaiga1()
				: new WorldGenTaiga2(false);
	}

}
