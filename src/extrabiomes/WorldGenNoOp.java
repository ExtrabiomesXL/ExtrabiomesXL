package extrabiomes;

import java.util.Random;

import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;

public class WorldGenNoOp extends WorldGenerator {

	public WorldGenNoOp() {
		super(false);
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4,
			int var5) {
		return false;
	}

}
