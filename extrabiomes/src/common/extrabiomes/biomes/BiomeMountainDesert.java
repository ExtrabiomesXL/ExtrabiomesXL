package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeMountainDesert extends ExtrabiomeGenBase {

	public BiomeMountainDesert(int id) {
		super(id);
		setColor(0xFA9418);
		setBiomeName("Mountainous Desert");
		temperature = BiomeGenBase.desertHills.temperature;
		rainfall = BiomeGenBase.desertHills.rainfall;
		minHeight = 0.4F;
		maxHeight = 1.4F;
		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sand.blockID;
		spawnableCreatureList.clear();
		disableRain();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.deadBushPerChunk(2).reedsPerChunk(50).cactiPerChunk(10)
				.build();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);

		if (rand.nextInt(1000) == 0) {
			int x1 = x + rand.nextInt(16) + 8;
			int z1 = z + rand.nextInt(16) + 8;
			WorldGenDesertWells wells = new WorldGenDesertWells();
			wells.generate(world, rand, x1, world.getHeightValue(x1, z1) + 1,
					z1);
		}

	}

}
