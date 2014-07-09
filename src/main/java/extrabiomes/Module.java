/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventBus;

import com.google.common.base.Optional;

import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.module.amica.Amica;
import extrabiomes.module.cautia.Cautia;
import extrabiomes.module.fabrica.Fabrica;

enum Module
{
    CAUTIA(Cautia.class), FABRICA(Fabrica.class), AMICA(Amica.class);
    
    private static Optional<EventBus> eventBus = Optional.of(new EventBus());
    
    public static boolean postEvent(Event event)
    {
        return eventBus.isPresent() ? eventBus.get().post(event) : false;
    }
    
    static void registerModules() throws InstantiationException, IllegalAccessException
    {
        for (final Module module : Module.values())
        {
            
            switch (module)
            {
                case CAUTIA:
                    module.enabled = ModuleControlSettings.CAUTIA.isEnabled();
                    break;
                case FABRICA:
                    module.enabled = ModuleControlSettings.FABRICA.isEnabled();
                    break;
                case AMICA:
                    module.enabled = ModuleControlSettings.AMICA.isEnabled();
                    break;
            }
            
            LogHelper.info(module.enabled ? "Module %s is enabled." : "Module %s is disabled, skipping.", module.toString());
            
            // skip disabled modules
            if (!module.enabled)
                continue;
            
            if (eventBus.isPresent())
                eventBus.get().register(module.pluginClass.newInstance());
        }
    }
    
    public static void releaseStaticResources()
    {
        eventBus = Optional.absent();
    }
    
    private boolean     enabled = false;
    
    @SuppressWarnings("rawtypes")
    private final Class pluginClass;
    
    @SuppressWarnings("rawtypes")
    private Module(Class pluginClass)
    {
        this.pluginClass = pluginClass;
    }
    
    public boolean isEnabled()
    {
        return enabled;
    }
    
}
