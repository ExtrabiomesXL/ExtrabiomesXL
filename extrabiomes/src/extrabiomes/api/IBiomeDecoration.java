/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.api;

import java.util.Random;

import net.minecraft.src.World;

/**
 * This interface is used by lists in {@link TerrainGenManager}.
 * 
 * @author ScottKillen
 * 
 */
public interface IBiomeDecoration {

	/**
	 * Decorate terrain in the chunk at the specified location
	 */
	public void decorate(World world, Random rand, int x, int z);

}
