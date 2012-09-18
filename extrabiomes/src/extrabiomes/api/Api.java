/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

/*
 * @author ScottKillen
 */
public class Api {

	/**
	 * @return true if ExtrtabiomesXL is installed and active
	 */
	boolean isActive() {
		return BiomeManager.alpine.isPresent();
	}

}
