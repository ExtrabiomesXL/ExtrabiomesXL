/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.SpawnListEntry;

public class BiomeExtremeJungle extends ExtrabiomeGenBase {

	public BiomeExtremeJungle(int id) {
		super(id);
		setColor(0x2c4205);
		setBiomeName("Extreme Jungle");
		temperature = BiomeGenBase.jungle.temperature;
		rainfall = BiomeGenBase.jungle.rainfall;
		minHeight = 2.1F;
		maxHeight = 2.3F;

		spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class,
				2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(
				EntityChicken.class, 10, 4, 4));

	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(50)
				.grassPerChunk(25).flowersPerChunk(4).build();
	}
}
