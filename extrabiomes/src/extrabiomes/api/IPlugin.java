/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.api;

/**
 * Plugins to Extrabiomes should implement this interface. They will be
 * loaded and run in @PostInit.
 * 
 * @author ScottKillen
 * 
 */
public interface IPlugin {

	/**
	 * @return a string description of the plugin
	 */
	public String getName();

	/**
	 * @return the plugin's unique id string
	 */
	public String getUniqueID();

	/**
	 * Plugins requiring initialization should override this method.
	 * This method is called during the @Mod @PostInit event. This
	 * method is called after all plugins' pre initialization but before
	 * any plugin's post initialization.
	 */
	public void init();

	/**
	 * @return boolean true if the plugin's prerequisites have been met.
	 *         If this function returns false, this plugin will not be
	 *         activated. Plugins are invoked in the order they were
	 *         registered.
	 */
	public boolean isEnabled();

	/**
	 * Plugins requiring post initialization should override this
	 * method. This method is called during the @Mod @PostInit event.
	 * This method is called after all plugins' initialization. Plugins
	 * are invoked in the order they were registered.
	 */
	public void postInit();

	/**
	 * Plugins requiring pre initialization should override this method.
	 * This method is called before any plugin's initialization. Plugins
	 * are invoked in the order they were registered.
	 */
	public void preInit();

}
