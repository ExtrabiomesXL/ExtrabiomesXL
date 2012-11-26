/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import extrabiomes.lib.BiomeSettings;

class BiomeSavanna extends ExtrabiomeGenBase {
    public BiomeSavanna() {
        super(BiomeSettings.SAVANNA.getID());

        setColor(0xBFA243);
        setBiomeName("Savanna");
        temperature = BiomeGenBase.desert.temperature;
        rainfall = BiomeGenBase.desert.rainfall;
        minHeight = 0.0F;
        maxHeight = 0.1F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(0).flowersPerChunk(1)
                .grassPerChunk(17).build();
    }
}
