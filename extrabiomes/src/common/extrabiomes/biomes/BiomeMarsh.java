package extrabiomes.biomes;

import java.util.Random;

import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenDirtBed;
import extrabiomes.terrain.WorldGenMarsh;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;

public class BiomeMarsh extends ExtrabiomeGenBase {

	private static WorldGenMarsh genMarsh = null;
	private static WorldGenDirtBed genDirtBed = null;

	public BiomeMarsh(int id) {
		super(id);
		setColor(255);
		setBiomeName("Marsh");
		temperature = BiomeGenBase.swampland.temperature;
		rainfall = BiomeGenBase.swampland.rainfall;
		minHeight = -0.4F;
		maxHeight = 0.0F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(999).build();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);

		if (genMarsh == null)
			genMarsh = new WorldGenMarsh();

		for (int i = 0; i < 127; i++) {
			int x1 = x + rand.nextInt(16) + 8;
			int z1 = z + rand.nextInt(16) + 8;
			genMarsh.generate(world, rand, x1, 0, z1);
		}

		if (genDirtBed == null)
			genDirtBed = new WorldGenDirtBed();

		for (int i = 0; i < 256; i++) {
			int x1 = x + rand.nextInt(1) + 8;
			int z1 = z + rand.nextInt(1) + 8;
			genDirtBed.generate(world, rand, x1, 0, z1);
		}
	}

}
