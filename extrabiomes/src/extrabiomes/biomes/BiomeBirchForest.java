/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.BiomeManager;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeBirchForest extends ExtrabiomeGenBase {

	public BiomeBirchForest(int id) {
		super(id);

		setColor(0x62BF6C);
		setBiomeName("Birch Forest");
		temperature = 0.4F;
		rainfall = 0.7F;
		minHeight = 0.0F;
		maxHeight = 0.4F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.grassPerChunk(1).build();
	}
}
