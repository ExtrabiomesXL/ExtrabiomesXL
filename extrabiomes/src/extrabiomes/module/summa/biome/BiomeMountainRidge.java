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

public class BiomeMountainRidge extends ExtrabiomeGenBase {

    public BiomeMountainRidge() {
        super(BiomeSettings.MOUNTAINRIDGE.getID());

        setColor(0xC4722F);
        setBiomeName("Mountain Ridge");
        temperature = BiomeGenBase.desert.temperature;
        rainfall = BiomeGenBase.desert.rainfall;
        minHeight = 1.7F;
        maxHeight = 1.7F;
        setDisableRain();
        spawnableCreatureList.clear();
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.MOUNTAINRIDGE).build();
    }
}
