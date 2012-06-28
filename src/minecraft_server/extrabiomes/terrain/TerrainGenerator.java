package extrabiomes.terrain;

import java.util.Collection;
import java.util.Random;

import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.biomes.ExtrabiomeGenBase;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;

public class TerrainGenerator {

	static protected void applyDecorations(
			Collection<IBiomeDecoration> decorations, World world, Random rand,
			int x, int z) {
		for (IBiomeDecoration decoration : decorations)
			decoration.decorate(world, rand, x, z);
	}

	public static void generateSurface(World world, Random rand, int x, int z) {
		BiomeGenBase biomeGen = world.getWorldChunkManager()
				.getBiomeGenAt(x, z);

		// Apply biome specific decorations
		Collection<IBiomeDecoration> decorationsForBiome = BiomeDecorationsManager.biomeDecorations
				.get(biomeGen);
		applyDecorations(decorationsForBiome, world, rand, x, z);

		// Apply decorations common to all biomes
		if (biomeGen instanceof ExtrabiomeGenBase)
			applyDecorations(BiomeDecorationsManager.commonDecorations, world,
					rand, x, z);
	}

}
