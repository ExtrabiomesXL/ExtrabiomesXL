/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeTemporateRainforest extends ExtrabiomeGenBase {
	public BiomeTemporateRainforest(int id) {
		super(id);

		setColor(0x338235);
		setBiomeName("Temperate Rainforest");
		temperature = 0.6F;
		rainfall = 0.9F;
		minHeight = 0.4F;
		maxHeight = 1.5F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.mushroomsPerChunk(2).grassPerChunk(16).build();
	}
}
