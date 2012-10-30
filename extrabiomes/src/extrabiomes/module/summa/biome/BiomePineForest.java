/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

class BiomePineForest extends ExtrabiomeGenBase {

	public BiomePineForest() {
		super(Biome.PINEFOREST.getBiomeID());

		setColor(0x469C7E);
		setBiomeName("Pine Forest");
		temperature = BiomeGenBase.forest.temperature;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = 0.1F;
		maxHeight = 0.3F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(10)
				.grassPerChunk(1).build();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor() {
		return ColorizerFoliage.getFoliageColor(0.4F, 0.6F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor() {
		return ColorizerGrass.getGrassColor(0.4F, 0.6F);
	}

}
