/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
	 * Registers a plugin to extend Extrabiomes.
	 * 
	 * @param plugin
	 *            the plugin instance to register.
	 */
	public static void registerPlugin(IPlugin plugin) {
		checkArgument(
				instance.isPresent(),
				"Plugins cannot be registered until after Plugin Manager is initialized.");
		checkNotNull(plugin, "Cannot register null plugin.");
		instance.get().doPluginRegistration(plugin);
	}

	/**
	 * Used internally. Any other use has no effect.
	 */
	public abstract void activatePlugins();

	protected abstract void doPluginRegistration(IPlugin plugin);

	/**
	 * @param pluginUniqueID
	 *            the unique identifier of the plugin to retrieve
	 * @return The plugin requested. the value will be absent if no
	 *         plugin was found.
	 */
	public abstract Optional<IPlugin> getPlugin(String pluginUniqueID);

}
