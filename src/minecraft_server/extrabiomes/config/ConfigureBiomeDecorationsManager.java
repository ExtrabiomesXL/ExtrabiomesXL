package extrabiomes.config;

import java.util.ArrayList;

import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.terrain.BiomeDecorationsMap;

public class ConfigureBiomeDecorationsManager {

	public static void initialize() {
		BiomeDecorationsManager.biomeDecorations = BiomeDecorationsMap
				.newInstance();
		BiomeDecorationsManager.commonDecorations = new ArrayList<IBiomeDecoration>();
	}

}
