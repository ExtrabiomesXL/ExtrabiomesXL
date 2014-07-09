/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.module.cautia.block.BlockManager;

public class Cautia
{
    
    @SubscribeEvent
    public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException
    {
        BlockManager.init();
    }
    
    @SubscribeEvent
    public void preInit(ModulePreInitEvent event) throws Exception
    {
        BlockManager.preInit();
    }
    
}
