/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import extrabiomes.api.Api;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.module.amica.atg.ATGPlugin;
import extrabiomes.module.amica.buildcraft.BuildcraftPlugin;
import extrabiomes.module.amica.ic2.IC2Plugin;
import extrabiomes.module.amica.newdawn.NewDawnPlugin;

public class Amica
{
    
    public static final String LOG_MESSAGE_PLUGIN_ERROR = "log.message.plugin.error";
    public static final String LOG_MESSAGE_PLUGIN_INIT  = "log.message.plugin.init";
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException
    {
        Api.registerPlugin(new BuildcraftPlugin());
        Api.registerPlugin(new IC2Plugin());
        Api.registerPlugin(new ATGPlugin());
        Api.registerPlugin(new NewDawnPlugin());
    }
    
}
