package extrabiomes.plugins.forestry;

import java.util.Random;
import net.minecraft.src.World;

public class WorldGenChunkBogEarth extends WorldGenBogEarth {

	@Override
	public boolean generate(World world, Random rand, int x, int notUsed, int z) {
	     for (int i = 0; i < 10; i++) {
	         int randPosX = x + rand.nextInt(16) + 8;
	         int randPosY = rand.nextInt(256);
	         int randPosZ = z + rand.nextInt(16) + 8;
	         super.generate(world, rand, randPosX, randPosY, randPosZ);
	       }
	     return true;
	}

}
