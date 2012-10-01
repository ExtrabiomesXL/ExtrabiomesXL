/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica;

import extrabiomes.IModule;
import extrabiomes.api.PluginManager;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.amica.buildcraft.BuildcraftPlugin;

public class Amica implements IModule {

	@Override
	public void init() throws InstantiationException,
			IllegalAccessException
	{
		PluginManager.registerPlugin(new BuildcraftPlugin());
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{}

}
