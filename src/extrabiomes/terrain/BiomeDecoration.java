package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.IBiomeDecoration;

public class BiomeDecoration implements IBiomeDecoration {

	final public int attempts;
	final public WorldGenerator worldGen;

	private BiomeDecoration() {
		this(0, null);
	}

	public BiomeDecoration(int attempts, WorldGenerator worldGen) {
		this.attempts = attempts;
		this.worldGen = worldGen;
	}

	public BiomeDecoration(WorldGenerator worldGen) {
		this(1, worldGen);
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		for (int i = 0; i < attempts; i++)
			worldGen.generate(world, rand, x, 0, z);
	}

}
