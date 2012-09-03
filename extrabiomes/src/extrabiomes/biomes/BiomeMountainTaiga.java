
package extrabiomes.biomes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import extrabiomes.terrain.CustomBiomeDecorator;


import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTaiga1;
import net.minecraft.src.WorldGenTaiga2;
import net.minecraft.src.WorldGenerator;

public class BiomeMountainTaiga extends ExtrabiomeGenBase {

	public BiomeMountainTaiga(int id) {
		super(id);

		setColor(0xB6659);
		setBiomeName("Mountain Taiga");
		temperature = 0.0F;
		rainfall = BiomeGenBase.taigaHills.rainfall;
		minHeight = 0.3F;
		maxHeight = 1.2F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				8, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(10)
				.grassPerChunk(1).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return checkNotNull(rand).nextInt(3) == 0 ? new WorldGenTaiga1()
				: new WorldGenTaiga2(false);
	}

}
