
package extrabiomes.terrain;

import java.util.Random;

import net.minecraft.server.World;
import net.minecraft.server.WorldGenerator;

public class WorldGenNoOp extends WorldGenerator {
	public WorldGenNoOp() {
		super(false);
	}

	public boolean a(World world, Random random, int i, int j, int k) {
		return false;
	}
}
