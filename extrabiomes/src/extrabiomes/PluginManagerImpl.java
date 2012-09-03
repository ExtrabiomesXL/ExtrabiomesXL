/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.google.common.base.Optional;

import extrabiomes.api.IPlugin;
import extrabiomes.api.PluginManager;

public class PluginManagerImpl extends PluginManager {

	private final Map<String, IPlugin>	plugins	= new HashMap<String, IPlugin>();

	PluginManagerImpl() {
		instance = Optional.of((PluginManager) this);
	}

	@Override
	public void activatePlugins() {
		if (!Extrabiomes.canActivatePlugins()) return;

		removeDeactivatedPlugins();

		for (final IPlugin plugin : plugins.values())
			plugin.preInit();

		for (final IPlugin plugin : plugins.values())
			plugin.init();

		for (final IPlugin plugin : plugins.values())
			plugin.postInit();
	}

	@Override
	protected void doPluginRegistration(IPlugin plugin) {
		plugins.put(plugin.getUniqueID(), plugin);
	}

	@Override
	public Optional<IPlugin> getPlugin(String pluginUniqueID) {
		return Optional.fromNullable(plugins.get(pluginUniqueID));
	}

	private void removeDeactivatedPlugins() {
		final List<IPlugin> pluginList = new ArrayList<IPlugin>(
				plugins.values());

		for (final IPlugin plugin : pluginList)
			if (!plugin.isEnabled())
				plugins.remove(plugin.getUniqueID());
	}

}
