
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.World;
import extrabiomes.terrain.WorldGenDirtBed;
import extrabiomes.terrain.WorldGenMarsh;

public class BiomeMarsh extends ExtrabiomeGenBase {
	private static WorldGenMarsh	genMarsh	= null;
	private static WorldGenDirtBed	genDirtBed	= null;

	public BiomeMarsh(int i) {
		super(i);
		b(255);
		a("Marsh");
		F = BiomeBase.SWAMPLAND.F;
		G = BiomeBase.SWAMPLAND.G;
		D = -0.4F;
		E = 0.0F;
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(0).grassPerChunk(999).build();
	}

	public void a(World world, Random random, int i, int j) {
		super.a(world, random, i, j);

		if (genMarsh == null) genMarsh = new WorldGenMarsh();

		for (int k = 0; k < 127; k++) {
			final int i1 = i + random.nextInt(16) + 8;
			final int k1 = j + random.nextInt(16) + 8;
			genMarsh.a(world, random, i1, 0, k1);
		}

		if (genDirtBed == null) genDirtBed = new WorldGenDirtBed();

		for (int l = 0; l < 256; l++) {
			final int j1 = i + random.nextInt(1) + 8;
			final int l1 = j + random.nextInt(1) + 8;
			genDirtBed.a(world, random, j1, 0, l1);
		}
	}
}
