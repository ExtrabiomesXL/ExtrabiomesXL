package extrabiomes.api;

import java.util.Random;

import net.minecraft.src.World;

/**
 * This interface is used by lists in {@link TerrainGenManager} to add your
 * biome decorators to biomes.
 * 
 * @author ScottKillen
 * 
 */

public interface IBiomeDecoration {

	/**
	 * decorate terrain at the specified location
	 */
	public void decorate(World world, Random rand, int x, int z);

}
