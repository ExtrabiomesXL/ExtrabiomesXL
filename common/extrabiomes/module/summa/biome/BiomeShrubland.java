/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeShrubland extends ExtrabiomeGenBase {

    public BiomeShrubland() {
        super(BiomeSettings.SHRUBLAND.getID());

        setColor(0x51B57D);
        setBiomeName("Shrubland");
        temperature = 0.4F;
        rainfall = 0.6F;
        minHeight = 0.1F;
        maxHeight = 0.3F;

        //spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 6, 1, 5));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.SHRUBLAND).build();
    }
}
