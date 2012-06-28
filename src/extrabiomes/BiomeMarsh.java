package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.World;

public class BiomeMarsh extends BiomeBase {

	private static final String NAME = "Marsh";
	private static final Extrabiome BIOME = Extrabiome.MARSH;
	private static WorldGenMarsh genMarsh = null;
	private static WorldGenDirtBed genDirtBed = null;

	public BiomeMarsh() {
		super(BIOME);
		setColor(255);
		setBiomeName(NAME);
		temperature = 0.8F;
		rainfall = 0.9F;
		minHeight = -0.4F;
		maxHeight = 0.0F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(0)
				.setGrassPerChunk(999);
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);

		if (genMarsh == null)
			genMarsh = new WorldGenMarsh();

		for (int i = 0; i < 127; i++) {
			int j = x + rand.nextInt(16) + 8;
			byte byte0 = 0;
			int k = z + rand.nextInt(16) + 8;
			genMarsh.generate(world, rand, j, byte0, k);
		}

		if (genDirtBed == null)
			genDirtBed = new WorldGenDirtBed();

		for (int i = 0; i < 256; i++) {
			int j = x + rand.nextInt(1) + 8;
			byte byte0 = 0;
			int k = z + rand.nextInt(1) + 8;
			genDirtBed.generate(world, rand, j, byte0, k);
		}
	}

}
