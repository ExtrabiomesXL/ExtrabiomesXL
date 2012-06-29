
package extrabiomes.plugins.redpower;

import java.util.Random;

import net.minecraft.server.World;

import extrabiomes.api.IBiomeDecoration;
import extrabiomes.plugins.PluginRedPower;

public class BiomeDecorationRubberTrees implements IBiomeDecoration {
	public BiomeDecorationRubberTrees() {}

	public void decorate(World world, Random random, int i, int j) {
		for (int k = 0; k < 6; k++) {
			final int l = i + random.nextInt(16) + 8;
			final int i1 = j + random.nextInt(16) + 8;
			final int j1 = world.getHighestBlockYAt(l, i1);
			PluginRedPower.newWorldGenRubberTree().a(world, random, l,
					j1, i1);
		}
	}
}
