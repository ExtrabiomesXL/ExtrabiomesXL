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

public class BiomeGreenHills extends ExtrabiomeGenBase {

    public BiomeGreenHills() {
        super(BiomeSettings.GREENHILLS.getID());

        setColor(0x68C474);
        setBiomeName("Green Hills");
        temperature = BiomeGenBase.forest.temperature - 0.1F;
        rainfall = BiomeGenBase.forest.rainfall + 0.1F;
        minHeight = 0.6F;
        maxHeight = 1.2F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(1).build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor() {
        return ColorizerFoliage.getFoliageColor(0.7F, 0.8F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor() {
        return ColorizerGrass.getGrassColor(0.7F, 0.8F);
    }

}
