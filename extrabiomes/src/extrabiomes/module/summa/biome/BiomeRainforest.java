/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import extrabiomes.lib.BiomeSettings;

public class BiomeRainforest extends ExtrabiomeGenBase {

    public BiomeRainforest() {
        super(BiomeSettings.RAINFOREST.getID());

        setColor(0x0BD626);
        setBiomeName("Rainforest");
        temperature = 1.1F;
        rainfall = 1.4F;
        minHeight = 0.4F;
        maxHeight = 1.3F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(7).grassPerChunk(4)
                .flowersPerChunk(2).build();
    }
}
