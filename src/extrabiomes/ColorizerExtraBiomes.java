package extrabiomes;

import net.minecraft.src.ColorizerFoliage;

public class ColorizerExtraBiomes {
	private static final int WHITE = 0xFFFFFF;

	public static int getNonBiomeColor() {
		return WHITE;
	}
	
	static int getFoliageColorFir() {
		return ColorizerFoliage.getFoliageColorPine();
	}
}
