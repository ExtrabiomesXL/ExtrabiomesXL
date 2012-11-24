/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.buildcraft;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.core.helper.LogHelper;

public class BuildcraftPlugin {

	private static final String		MOD_NAME	= "Buildcraft";
	private Optional<BuildcraftAPI>	api			= Optional.absent();

	private void addOilSpawns() {
		if (!api.get().modifyWorld() || !api.isPresent()) return;

		Extrabiomes.proxy.registerWorldGenerator(new OilGenerator(api
				.get()));
	}

	@ForgeSubscribe
	public void init(PluginEvent.Init event) {
		if (!api.isPresent()) return;

		addOilSpawns();
	}

	@ForgeSubscribe
	public void postInit(PluginEvent.Post event) {
		api = Optional.absent();
	}

	@ForgeSubscribe
	public void preInit(PluginEvent.Pre event) {
		if (!Extrabiomes.proxy.isModLoaded("BuildCraft|Energy"))
			return;
		LogHelper.fine(Extrabiomes.proxy
				.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT),
				MOD_NAME);
		try {
			api = Optional.of(new BuildcraftAPI());
		} catch (final Exception ex) {
			ex.printStackTrace();
			LogHelper.fine(Extrabiomes.proxy
					.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR),
					MOD_NAME);
			api = Optional.absent();
		}
	}

}
