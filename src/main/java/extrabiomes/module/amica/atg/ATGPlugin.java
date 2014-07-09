package extrabiomes.module.amica.atg;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.helpers.LogHelper;

public class ATGPlugin
{
    private static final String            MOD_ID = "ATG";
    private static Optional<ATGPluginImpl> api    = Optional.absent();
    
    @SubscribeEvent
    public void preInit(PluginEvent.Pre event)
    {
        if (!Extrabiomes.proxy.isModLoaded(MOD_ID))
        {
            return;
        }
        
        LogHelper.fine("Initializing %s plugin.", MOD_ID);
        try
        {
            api = Optional.of(new ATGPluginImpl());
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
            LogHelper.fine("Could not communicate with %s. Disabling plugin.", MOD_ID);
            api = Optional.absent();
        }
    }
    
    @SubscribeEvent
    public void init(PluginEvent.Init event)
    {
        if (!api.isPresent())
            return;
        
        api.get().init();
    }
    
    @SubscribeEvent
    public void postInit(PluginEvent.Post event)
    {
        api = Optional.absent();
    }
}
