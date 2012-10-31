/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

class BiomeMeadow extends ExtrabiomeGenBase {

	public BiomeMeadow() {
		super(Biome.MEADOW.getBiomeID());
		minHeight = 0.0F;
		maxHeight = 0.0F;
		setBiomeName("Meadow");
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(12).flowersPerChunk(9).build();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor() {
		return ColorizerFoliage.getFoliageColor(1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor() {
		return ColorizerGrass.getGrassColor(1.0F, 1.0F);
	}

}
