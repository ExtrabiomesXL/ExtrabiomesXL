package extrabiomes.api;

import java.util.Collection;
import java.util.Random;

import net.minecraft.src.World;

/**
 * The <code>ExtrabiomeDecoratorSubscriber</code> interface is implemented by
 * any API client mod object that wishes to participate in Extrabiomes biome
 * decoration. The object can subscribe to biome decoration using
 * {@link ExtrabiomesAPI#subscribeExtrabiomeDecorate}
 * 
 * @author ScottKillen
 * 
 */
public interface ExtrabiomeDecorateSubscriber {

	/**
	 * Hook called during decoration of Extrabiomes.
	 * 
	 * @param biome {@link Extrabiome} identifier of chunk being decorated.
	 * @param world <code>World</code> being decorated.
	 * @param random <code>Random</code> in use by the terrain engine in biome decoration 
	 * @param x X-coordinate in the chunk.
	 * @param y Z-coordinate in the chunk.
	 */
	void onDecorateExtrabiome(Extrabiome biome, World world, Random random,
			int x, int z);
}
