/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import extrabiomes.api.IPlugin;
import extrabiomes.api.PluginManager;

public class PluginManagerImpl extends PluginManager {

	private final Map<String, IPlugin>	plugins	= new LinkedHashMap<String, IPlugin>();

	PluginManagerImpl() {
		instance = Optional.of((PluginManager) this);
	}

	public void activatePlugins() {
		removeDeactivatedPlugins();

		final String LOG_FORMAT = "%s plugins...";

		ExtrabiomesLog.info(LOG_FORMAT, "Preinitializing");
		for (final IPlugin plugin : plugins.values()) {
			ExtrabiomesLog.info("PreInit: %s", plugin.getName());
			plugin.preInit();
		}

		ExtrabiomesLog.info(LOG_FORMAT, "Initializing");
		for (final IPlugin plugin : plugins.values()) {
			ExtrabiomesLog.info("Init: %s", plugin.getName());
			plugin.init();
		}

		ExtrabiomesLog.info(LOG_FORMAT, "Initializing");
		for (final IPlugin plugin : plugins.values()) {
			ExtrabiomesLog.info("PostInit: %s", plugin.getName());
			plugin.postInit();
		}
	}

	@Override
	protected void doPluginRegistration(IPlugin plugin) {
		ExtrabiomesLog.info("Registered plugin: %s", plugin.getName());
		plugins.put(plugin.getUniqueID(), plugin);
	}

	@Override
	protected Optional<IPlugin> getPluginById(String pluginUniqueID) {
		return Optional.fromNullable(plugins.get(pluginUniqueID));
	}

	@Override
	protected Collection<IPlugin> getPluginCollection() {
		return ImmutableSet.copyOf(plugins.values());
	}

	private void removeDeactivatedPlugins() {
		final List<IPlugin> pluginList = new ArrayList<IPlugin>(
				plugins.values());

		ExtrabiomesLog.info("Removing disabled plugins...");
		for (final IPlugin plugin : pluginList)
			if (!plugin.isEnabled()) {
				ExtrabiomesLog.info("Inactive plugin: %s",
						plugin.getName());
				plugins.remove(plugin.getUniqueID());
			}
	}
}
