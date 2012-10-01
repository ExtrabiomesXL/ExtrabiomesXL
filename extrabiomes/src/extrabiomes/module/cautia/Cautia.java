/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia;

import extrabiomes.IModule;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.cautia.blocks.BlockManager;

public class Cautia implements IModule {

	@Override
	public void init() throws InstantiationException,
			IllegalAccessException
	{
		BlockManager.init();
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		BlockManager.preInit(config);
	}

}
