package extrabiomes.module.amica.newdawn;

import java.util.HashSet;
import java.util.Set;

import net.minecraftforge.event.ForgeSubscribe;
import two.newdawn.API.NewDawnBiomeList;
import two.newdawn.API.NewDawnBiomeProvider;
import two.newdawn.API.NewDawnBiomeSelector;
import two.newdawn.API.noise.SimplexNoise;
import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.helpers.LogHelper;

public class NewDawnPlugin implements NewDawnBiomeProvider
{
    private static boolean          enabled            = true;

    @ForgeSubscribe
    public void postInit(PluginEvent.Post event) throws Exception
    {
        if (!isEnabled())
            return;
        LogHelper.info("Registering NewDawn biome provider...");
        NewDawnBiomeList.registerProvider(this);
    }
    
    private boolean isEnabled()
    {
        return enabled && Extrabiomes.proxy.isModLoaded("newdawn");
    }
    
    /*
    @SuppressWarnings({ "rawtypes" })
    @ForgeSubscribe
    public void preInit(PluginEvent.Pre event)
    {
        if (!isEnabled())
            return;
        //LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT), "NewDawn");
        LogHelper.fine("Initializing %s plugin.", "NewDawn");
        
        try
        {
        	
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
            //LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR), "NewDawn");
            LogHelper.fine("Could not communicate with %s. Disabling plugin.", "NewDawn");
            enabled = false;
        }
    }
    */

	@Override
	public Set<NewDawnBiomeSelector> getBiomeSelectors(SimplexNoise worldNoise) {
		final HashSet<NewDawnBiomeSelector> selectors = new HashSet<NewDawnBiomeSelector>();
		
		selectors.add(new CommonBiomeSelector(worldNoise,NewDawnBiomeProvider.PRIORITY_MEDIUM));
		//selectors.add(new UncommonBiomeSelector(worldNoise,NewDawnBiomeProvider.PRIORITY_LOWEST));
		
		return selectors;
	}
}
