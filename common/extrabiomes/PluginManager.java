/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import com.google.common.base.Optional;

import extrabiomes.api.Api;
import extrabiomes.api.PluginEvent;

class PluginManager extends Api
{
    
    static void activatePlugins()
    {
        if (Api.pluginBus.isPresent())
        {
            Api.pluginBus.get().post(new PluginEvent.Pre());
            Api.pluginBus.get().post(new PluginEvent.Init());
            Api.pluginBus.get().post(new PluginEvent.Post());
            Api.pluginBus = Optional.absent();
        }
    }
}
