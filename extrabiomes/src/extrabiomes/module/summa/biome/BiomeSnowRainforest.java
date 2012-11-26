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
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.lib.BiomeSettings;

class BiomeSnowRainforest extends BiomeTemporateRainforest {

    public BiomeSnowRainforest() {
        super(BiomeSettings.SNOWYRAINFOREST.getID());

        setColor(0x338277);
        setBiomeName("Snowy Rainforest");
        temperature = BiomeGenBase.taigaHills.temperature;
        rainfall = 1.3F;
        minHeight = 0.4F;
        maxHeight = 1.5F;
        setEnableSnow();
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(17).mushroomsPerChunk(2)
                .grassPerChunk(16).build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor() {
        return ColorizerFoliage.getFoliageColor(0.0F, 0.1F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor() {
        return ColorizerGrass.getGrassColor(0.0F, 0.1F);
    }

}
