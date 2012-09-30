/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import extrabiomes.configuration.ExtrabiomesConfig;

public interface IModule {

	public void init() throws InstantiationException,
			IllegalAccessException;

	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException;

}
