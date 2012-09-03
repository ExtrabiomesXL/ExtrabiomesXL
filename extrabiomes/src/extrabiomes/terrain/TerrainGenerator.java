/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.terrain;

import java.util.Collection;
import java.util.Random;

import extrabiomes.api.ExtrabiomesBiomeDecorations;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.biomes.ExtrabiomeGenBase;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;

public class TerrainGenerator {

	static protected void applyDecorations(
			Collection<IBiomeDecoration> decorations, World world,
			Random rand, int x, int z)
	{
		for (final IBiomeDecoration decoration : decorations)
			decoration.decorate(world, rand, x, z);
	}

	public static void generateSurface(World world, Random rand, int x,
			int z)
	{
		final BiomeGenBase biomeGen = world.getWorldChunkManager()
				.getBiomeGenAt(x, z);

		// Apply biome specific decorations
		final Collection<IBiomeDecoration> decorationsForBiome = ExtrabiomesBiomeDecorations.biomeDecorations
				.get().get(biomeGen);
		applyDecorations(decorationsForBiome, world, rand, x, z);

		// Apply decorations common to all biomes
		if (biomeGen instanceof ExtrabiomeGenBase) {
			final Collection<IBiomeDecoration> decorations = ExtrabiomesBiomeDecorations.biomeDecorations
					.get().get(null);
			applyDecorations(decorations, world, rand, x, z);
		}
	}

}
