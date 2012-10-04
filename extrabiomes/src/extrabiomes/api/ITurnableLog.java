/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.api;

import net.minecraft.src.World;

/**
 * Blocks implement ITurnableLog to react to the log turner tool
 */
public interface ITurnableLog {

	/**
	 * Called when the player right clicks with the log turner rool
	 * 
	 * @param world
	 *            current world
	 * @param x
	 * @param y
	 * @param z
	 */
	void onLogTurner(World world, int x, int y, int z);

}
