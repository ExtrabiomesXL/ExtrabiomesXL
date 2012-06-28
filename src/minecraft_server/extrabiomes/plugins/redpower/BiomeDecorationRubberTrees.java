package extrabiomes.plugins.redpower;

import java.util.Random;

import net.minecraft.src.World;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginRedPower;

public class BiomeDecorationRubberTrees implements IBiomeDecoration {

	@Override
	public void decorate(World world, Random rand, int chunkX, int chunkZ) {
		for (int a = 0; a < 6; a++) {
			int x = chunkX + rand.nextInt(16) + 8;
			int z = chunkZ + rand.nextInt(16) + 8;
			int y = world.getHeightValue(x, z);
			PluginRedPower.newWorldGenRubberTree().generate(world, rand, x, y,
					z);
		}
	}

}
