/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module;

import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;

public interface IModule {
	public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException;
    
    public void preInit(ModulePreInitEvent event) throws Exception;
}