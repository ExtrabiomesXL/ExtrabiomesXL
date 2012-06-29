
package extrabiomes.terrain;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.World;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.biomes.ExtrabiomeGenBase;

public class TerrainGenerator {
	protected static void applyDecorations(Collection collection,
			World world, Random random, int i, int j)
	{
		IBiomeDecoration ibiomedecoration;

		for (final Iterator iterator = collection.iterator(); iterator
				.hasNext(); ibiomedecoration.decorate(world, random, i,
				j))
			ibiomedecoration = (IBiomeDecoration) iterator.next();
	}

	public static void generateSurface(World world, Random random,
			int i, int j)
	{
		final BiomeBase biomebase = world.getWorldChunkManager()
				.getBiome(i, j);
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(biomebase);
		applyDecorations(collection, world, random, i, j);

		if (biomebase instanceof ExtrabiomeGenBase)
			applyDecorations(BiomeDecorationsManager.commonDecorations,
					world, random, i, j);
	}

	public TerrainGenerator() {}
}
