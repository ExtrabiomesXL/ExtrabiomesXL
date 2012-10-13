/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.api;

/*
 * @author ScottKillen
 */
public class Api {

	/**
	 * @return true if ExtrtabiomesXL is installed and active
	 */
	public static boolean isActive() {
		return BiomeManager.isActive();
	}

}
