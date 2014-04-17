package extrabiomes.module.amica.newdawn;

import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.helpers.LogHelper;

public class NewDawnPlugin
{
	private static final String					MOD_ID	= "newdawn";
	private static Optional<NewDawnPluginImpl>	api		= Optional.absent();

    @ForgeSubscribe
	public void preInit(PluginEvent.Pre event)
    {
		if (!Extrabiomes.proxy.isModLoaded(MOD_ID)) {
            return;
		}

		LogHelper.fine("Initializing %s plugin.", MOD_ID);
		try {
			api = Optional.of(new NewDawnPluginImpl());
		} catch (final Exception ex) {
			ex.printStackTrace();
			LogHelper.fine("Could not communicate with %s. Disabling plugin.", MOD_ID);
			api = Optional.absent();
		}
    }
    
	@ForgeSubscribe
	public void postInit(PluginEvent.Post event) {
		if (!api.isPresent()) return;
		try {
			api.get().postInit(event);
		} catch( Exception e ) {
			LogHelper.warning("Exception in %s postInit: %s", MOD_ID, e.getMessage());
		}
		api = Optional.absent();
    }
}
