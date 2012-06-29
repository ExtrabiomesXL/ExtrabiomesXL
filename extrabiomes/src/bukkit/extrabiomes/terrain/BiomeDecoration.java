
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.IBiomeDecoration;

public class BiomeDecoration implements IBiomeDecoration {
	public final int			attempts;
	public final WorldGenerator	worldGen;

	public BiomeDecoration(int i, WorldGenerator worldgenerator) {
		attempts = i;
		worldGen = worldgenerator;
	}

	public BiomeDecoration(WorldGenerator worldgenerator) {
		this(1, worldgenerator);
	}

	@Override
	public void decorate(World world, Random random, int i, int j) {
		for (int k = 0; k < attempts; k++)
			worldGen.a(world, random, i, 0, j);
	}
}
