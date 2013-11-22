/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica;

import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import extrabiomes.api.Api;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.module.amica.atg.ATGPlugin;
import extrabiomes.module.amica.buildcraft.BuildcraftPlugin;
import extrabiomes.module.amica.forestry.ForestryPlugin;
import extrabiomes.module.amica.ic2.IC2Plugin;
import extrabiomes.module.amica.treecapitator.TreeCapitatorPlugin;

public class Amica
{
    
    public static final String LOG_MESSAGE_PLUGIN_ERROR = "log.message.plugin.error";
    public static final String LOG_MESSAGE_PLUGIN_INIT  = "log.message.plugin.init";
    
    @ForgeSubscribe(priority = EventPriority.LOWEST)
    public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException
    {
        Api.registerPlugin(new BuildcraftPlugin());
        Api.registerPlugin(new ForestryPlugin());
        Api.registerPlugin(new IC2Plugin());
        Api.registerPlugin(new TreeCapitatorPlugin());
        Api.registerPlugin(new ATGPlugin());
    }
    
}
