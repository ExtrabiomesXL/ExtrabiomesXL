/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
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
	 *         activated.
	 */
	public boolean isEnabled();

	/**
	 * Plugins requiring post initialization should override this
	 * method. This method is called during the @Mod @PostInit event.
	 * This method is called after all plugins' initialization.
	 */
	public void postInit();

	/**
	 * Plugins requiring pre initialization should override this method.
	 * This method is called before any plugin's initialization.
	 */
	public void preInit();

}
