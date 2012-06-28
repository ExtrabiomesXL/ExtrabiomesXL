package extrabiomes.api;

import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to inject your plugin into Extrabiomes
 * 
 * @author ScottKillen
 */
public class PluginManager {

	/**
	 * Add your plugins to this list during BaseMod.load() to have them added to
	 * Extrabiomes
	 */
	public static Set<IPlugin> plugins = new HashSet<IPlugin>();

}
