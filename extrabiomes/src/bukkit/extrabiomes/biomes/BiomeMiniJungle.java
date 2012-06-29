
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.Block;
import net.minecraft.server.World;
import net.minecraft.server.WorldGenGrass;
import net.minecraft.server.WorldGenerator;
import extrabiomes.terrain.WorldGenPit;
import extrabiomes.terrain.WorldGenPit2;

public class BiomeMiniJungle extends ExtrabiomeGenBase {
	public BiomeMiniJungle(int i) {
		super(i);
		b(0x41d923);
		a("Mini Jungle");
		F = BiomeBase.JUNGLE.F;
		G = BiomeBase.JUNGLE.G;
		D = 0.2F;
		E = 0.6F;
		H = 0x24b01c;
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
				this).treesPerChunk(15).grassPerChunk(9)
				.flowersPerChunk(5).reedsPerChunk(70).clayPerChunk(3)
				.mushroomsPerChunk(2).waterlilyPerChunk(12).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator a(Random random) {
		if (random.nextInt(2) == 0) return Q;

		if (random.nextInt(100) == 0)
			return N;
		else
			return O;
	}

	@Override
	public void a(World world, Random random, int i, int j) {
		super.a(world, random, i, j);

		if (random.nextInt(1) == 0) {
			final int k = i + random.nextInt(16) + 8;
			final int i1 = j + random.nextInt(16) + 8;
			final WorldGenPit2 worldgenpit2 = new WorldGenPit2();
			worldgenpit2.a(world, random, k,
					world.getHighestBlockYAt(k, i1) + 1, i1);
			worldgenpit2.a(world, random, k,
					world.getHighestBlockYAt(k, i1) + 1, i1);
			worldgenpit2.a(world, random, k,
					world.getHighestBlockYAt(k, i1) + 1, i1);
		}

		if (random.nextInt(1) == 0) {
			final int l = i + random.nextInt(16) + 8;
			final int j1 = j + random.nextInt(16) + 8;
			final WorldGenPit worldgenpit = new WorldGenPit();
			worldgenpit.a(world, random, l,
					world.getHighestBlockYAt(l, j1) + 1, j1);
		}
	}

	public WorldGenerator b(Random random) {
		if (random.nextInt(4) == 0)
			return new WorldGenGrass(Block.LONG_GRASS.id, 2);
		else
			return super.b(random);
	}
}
