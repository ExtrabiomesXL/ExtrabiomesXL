package extrabiomes.api;

/**
 * Your plugins to Extrabiomes should implement this interface. They will be
 * loaded and run in modsLoaded.
 * 
 * @author ScottKillen
 * 
 */
public interface IPlugin {

	/**
	 * @return boolean true if the plugin's prerequisites have been met. If this
	 *         function returns false, this plugin will not be run.
	 */
	public boolean isEnabled();

	/**
	 * This method is called during theexecution of Extrabiomes' ModsLoaded
	 * hook. This is only invoked if isAvalable() returns true.
	 */
	public void inject();

	/**
	 * @return a string description of the plugin
	 */
	public String getName();

}
