/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa;

import extrabiomes.Extrabiomes;
import extrabiomes.IModule;
import extrabiomes.api.BiomeManager;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.summa.biome.BiomeManagerImpl;
import extrabiomes.module.summa.worldgen.MarshGenerator;
import extrabiomes.module.summa.worldgen.MountainDesertGenerator;
import extrabiomes.module.summa.worldgen.MountainRidgeGenerator;

public class Summa implements IModule {

	private static BiomeManagerImpl	biomeManager	= new BiomeManagerImpl();

	@Override
	public void init() {

		if (BiomeManager.marsh.isPresent())
			Extrabiomes.proxy
					.registerWorldGenerator(new MarshGenerator());

		if (BiomeManager.mountaindesert.isPresent())
			Extrabiomes.proxy
					.registerWorldGenerator(new MountainDesertGenerator());

		if (BiomeManager.mountainridge.isPresent())
			Extrabiomes.proxy
					.registerWorldGenerator(new MountainRidgeGenerator());

		biomeManager.initialize();
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		biomeManager.preInit(config);
	}

}
