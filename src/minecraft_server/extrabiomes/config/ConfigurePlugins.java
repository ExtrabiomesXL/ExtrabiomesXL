package extrabiomes.config;

import extrabiomes.api.PluginManager;
import extrabiomes.plugins.PluginBuildCraft;
import extrabiomes.plugins.PluginEE;
import extrabiomes.plugins.PluginForestry;
import extrabiomes.plugins.PluginRedPower;

public class ConfigurePlugins {

	public static void initialize() {
		PluginManager.plugins.add(PluginBuildCraft.INSTANCE);
		PluginManager.plugins.add(PluginEE.INSTANCE);
		PluginManager.plugins.add(PluginForestry.INSTANCE);
		PluginManager.plugins.add(PluginRedPower.INSTANCE);
	}

}
