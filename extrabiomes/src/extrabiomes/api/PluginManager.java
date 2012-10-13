/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.api;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.google.common.base.Optional;

/**
 * This class is used to add plugins to Extrabiomes.
 * 
 * @author ScottKillen
 */
public abstract class PluginManager {

	protected static Optional<PluginManager>	instance	= Optional
																	.absent();

	/**
	 * @param pluginUniqueID
	 *            the unique identifier of the plugin to retrieve
	 * @return The plugin requested. the value will be absent if no
	 *         plugin was found.
	 */
	public static Optional<IPlugin> getPlugin(String pluginUniqueID) {
		checkArgument(instance.isPresent(),
				"Plugin list not available until after PluginManager is initialized.");
		if (pluginUniqueID == null) return null;
		return instance.get().getPluginById(pluginUniqueID);

	}

	/**
	 * @return An immutable collection of the installed plugins sorted
	 *         by insertion order.
	 */
	public static Collection<IPlugin> getPlugins() {
		checkArgument(instance.isPresent(),
				"Plugin list not available until after PluginManager is initialized.");
		return instance.get().getPluginCollection();
	}

	/**
	 * Registers a plugin to extend Extrabiomes.
	 * 
	 * @param plugin
	 *            the plugin instance to register.
	 */
	public static void registerPlugin(IPlugin plugin) {
		checkArgument(
				instance.isPresent(),
				"Plugins cannot be registered until after PluginManager is initialized.");
		checkNotNull(plugin, "Cannot register null plugin.");
		instance.get().doPluginRegistration(plugin);
	}

	protected abstract void doPluginRegistration(IPlugin plugin);

	protected abstract Optional<IPlugin> getPluginById(
			String pluginUniqueID);

	protected abstract Collection<IPlugin> getPluginCollection();

}
