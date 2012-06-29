
package extrabiomes.api;

import java.util.Random;

import net.minecraft.server.World;

public interface IBiomeDecoration {
	public abstract void decorate(World world, Random random, int i,
			int j);
}
