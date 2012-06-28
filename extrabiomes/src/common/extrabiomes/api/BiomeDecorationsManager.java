package extrabiomes.api;

import java.util.Collection;
import java.util.Map;

import net.minecraft.src.BiomeGenBase;

/**
 * Allows direct access to Extrabiome's BiomeDecorations. Will be populated
 * during BaseMod.load(). Each biome's decorations will be applied during
 * BaseMod.generateSurface().
 * <p>
 * <b>NOTE:</b> Make sure to only reference this class in ModsLoaded() or later.
 * 
 * @author ScottKillen
 * 
 */
public class BiomeDecorationsManager {

	/**
	 * A map of collections of biome decorations to the biome the collection of
	 * decorations is applied to. The Map is immutable. To add your biome
	 * decorations to a biome, <code>get</code> the collection of the biome you
	 * wish to modify and add your decorations to it.
	 */
	public static Map<BiomeGenBase, Collection<IBiomeDecoration>> biomeDecorations = null;

	/**
	 * The collection of biomes decorations to be applied to all Extrabiomes;
	 * biomes. Add your decorations here to have them be applied to all biomes.
	 */
	public static Collection<IBiomeDecoration> commonDecorations = null;

}
