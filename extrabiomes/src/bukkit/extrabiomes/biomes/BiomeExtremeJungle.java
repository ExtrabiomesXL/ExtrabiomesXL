
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.Block;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenGrass;
import net.minecraft.server.WorldGenGroundBush;
import net.minecraft.server.WorldGenMegaTree;
import net.minecraft.server.WorldGenTrees;
import net.minecraft.server.WorldGenVines;
import net.minecraft.server.WorldGenerator;

public class BiomeExtremeJungle extends ExtrabiomeGenBase {
	public BiomeExtremeJungle(int i) {
		super(i);
		b(0x2c4205);
		a("Extreme Jungle");
		F = BiomeBase.JUNGLE.F;
		G = BiomeBase.JUNGLE.G;
		D = 2.1F;
		E = 2.3F;
		J.add(new BiomeMeta(net.minecraft.server.EntityOcelot.class, 2,
				1, 1));
		K.add(new BiomeMeta(net.minecraft.server.EntityChicken.class,
				10, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	@Override
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(50).grassPerChunk(25)
				.flowersPerChunk(4).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator a(Random random) {
		return random.nextInt(10) != 0 ? random.nextInt(2) != 0 ? random
				.nextInt(3) != 0 ? new WorldGenTrees(false,
				4 + random.nextInt(7), 3, 3, true)
				: new WorldGenMegaTree(false, 10 + random.nextInt(20),
						3, 3) : new WorldGenGroundBush(3, 0)
				: O;
	}

	@Override
	public void a(World world, Random random, int i, int j) {
		super.a(world, random, i, j);
		final WorldGenVines worldgenvines = new WorldGenVines();

		for (int k = 0; k < 50; k++) {
			final int l = i + random.nextInt(16) + 8;
			final int i1 = j + random.nextInt(16) + 8;
			worldgenvines.a(world, random, l, 64, i1);
		}
	}

	@Override
	public WorldGenerator b(Random random) {
		return random.nextInt(4) != 0 ? new WorldGenGrass(
				Block.LONG_GRASS.id, 1) : new WorldGenGrass(
				Block.LONG_GRASS.id, 2);
	}
}
