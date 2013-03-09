/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeSavanna extends ExtrabiomeGenBase {
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
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.SAVANNA).build();
    }
}
