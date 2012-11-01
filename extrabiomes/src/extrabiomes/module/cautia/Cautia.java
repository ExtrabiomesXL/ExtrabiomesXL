/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia;

import net.minecraftforge.event.ForgeSubscribe;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.module.cautia.block.BlockManager;

public class Cautia {

	@ForgeSubscribe
	public void init(ModuleInitEvent event)
			throws InstantiationException, IllegalAccessException
	{
		BlockManager.init();
	}

	@ForgeSubscribe
	public void preInit(ModulePreInitEvent event)
			throws Exception
	{
		BlockManager.preInit(event.config);
	}

}
