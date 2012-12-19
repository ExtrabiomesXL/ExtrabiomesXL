/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import extrabiomes.lib.BiomeSettings;

public class BiomeMarsh extends ExtrabiomeGenBase {

    public BiomeMarsh() {
        super(BiomeSettings.MARSH.getID());

        setColor(255);
        setBiomeName("Marsh");
        temperature = BiomeGenBase.swampland.temperature;
        rainfall = BiomeGenBase.swampland.rainfall;
        minHeight = -0.4F;
        maxHeight = 0.0F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(0).grassPerChunk(999)
                .reedsPerChunk(10).build();
    }

}
