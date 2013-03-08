/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeTundra extends ExtrabiomeGenBase {

    public BiomeTundra() {
        super(BiomeSettings.TUNDRA.getID());

        setColor(0x305A85);
        setBiomeName("Tundra");
        temperature = 0.0F;
        rainfall = 0.0F;
        minHeight = 0.0F;
        maxHeight = 0.2F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.TUNDRA).build();
    }

}
