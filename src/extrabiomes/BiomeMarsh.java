package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class BiomeMarsh extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.MARSH;
	private static final WorldGenerator genMarsh = new WorldGenMarsh();
	private static final WorldGenerator genDirtBed = new WorldGenDirtBed();

	public BiomeMarsh(int id) {
		super(id);
		setColor(255);
		setBiomeName("Marsh");
		temperature = 0.8F;
		rainfall = 0.9F;
		minHeight = -0.4F;
		maxHeight = 0.0F;
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome).setTreesPerChunk(0)
				.setGrassPerChunk(999);
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);

		for (int i = 0; i < 127; i++) {
			int j = x + rand.nextInt(16) + 8;
			byte byte0 = 0;
			int k = z + rand.nextInt(16) + 8;
			genMarsh.generate(world, rand, j, byte0, k);
		}

		for (int i = 0; i < 256; i++) {
			int j = x + rand.nextInt(1) + 8;
			byte byte0 = 0;
			int k = z + rand.nextInt(1) + 8;
			genDirtBed.generate(world, rand, j, byte0, k);
		}
	}

}
