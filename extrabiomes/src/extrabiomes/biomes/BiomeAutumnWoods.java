/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static extrabiomes.api.ITreeFactory.TreeType.BROWN_AUTUMN;
import static extrabiomes.api.ITreeFactory.TreeType.BROWN_AUTUMN_BIG;
import static extrabiomes.api.ITreeFactory.TreeType.ORANGE_AUTUMN;
import static extrabiomes.api.ITreeFactory.TreeType.ORANGE_AUTUMN_BIG;
import static extrabiomes.api.ITreeFactory.TreeType.PURPLE_AUTUMN;
import static extrabiomes.api.ITreeFactory.TreeType.PURPLE_AUTUMN_BIG;
import static extrabiomes.api.ITreeFactory.TreeType.YELLOW_AUTUMN;
import static extrabiomes.api.ITreeFactory.TreeType.YELLOW_AUTUMN_BIG;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeAutumnWoods extends ExtrabiomeGenBase {

	public BiomeAutumnWoods(int id) {
		super(id);
		setColor(0xF29C11);
		setBiomeName("Autumn Woods");
		temperature = BiomeGenBase.forest.temperature;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = 0.2F;
		maxHeight = 0.8F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(9)
				.grassPerChunk(6).mushroomsPerChunk(3).build();
	}

}
