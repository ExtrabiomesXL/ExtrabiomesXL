/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeMountainTaiga extends ExtrabiomeGenBase {

	public BiomeMountainTaiga(int id) {
		super(id);

		setColor(0xB6659);
		setBiomeName("Mountain Taiga");
		temperature = 0.0F;
		rainfall = BiomeGenBase.taigaHills.rainfall;
		minHeight = 0.3F;
		maxHeight = 1.2F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				8, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(10)
				.grassPerChunk(1).build();
	}
}
