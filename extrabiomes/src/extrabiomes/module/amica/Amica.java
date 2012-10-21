/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica;

import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import extrabiomes.api.PluginManager;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.module.amica.buildcraft.BuildcraftPlugin;

public class Amica {

	@ForgeSubscribe(priority = EventPriority.LOWEST)
	public void init(ModuleInitEvent event)
			throws InstantiationException, IllegalAccessException
	{
		PluginManager.registerPlugin(new BuildcraftPlugin());
	}

}
