/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa;

import extrabiomes.IModule;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.summa.biome.BiomeManagerImpl;

public class Summa implements IModule {

	private static BiomeManagerImpl	biomeManager	= new BiomeManagerImpl();

	@Override
	public void init() {
		biomeManager.initialize();
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		biomeManager.preInit(config);
	}

}
