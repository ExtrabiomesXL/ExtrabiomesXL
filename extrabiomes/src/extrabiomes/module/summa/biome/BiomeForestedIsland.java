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

class BiomeForestedIsland extends ExtrabiomeGenBase {

	public BiomeForestedIsland() {
		super(Biome.FORESTEDISLAND.getBiomeID());

		setColor(0x62BF6C);
		setBiomeName("Forested Island");
		temperature = BiomeGenBase.forest.temperature + 0.1F;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = -0.8F;
		maxHeight = 0.8F;

		spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class,
				5, 4, 4));
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.grassPerChunk(3).build();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor() {
		return ColorizerFoliage.getFoliageColor(0.4F, 0.7F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor() {
		return ColorizerGrass.getGrassColor(0.4F, 0.7F);
	}

}
