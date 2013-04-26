/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMountainTaiga extends ExtrabiomeGenBase {

    @SuppressWarnings("unchecked")
	public BiomeMountainTaiga() {
        super(BiomeSettings.MOUNTAINTAIGA.getID());

        setColor(0xB6659);
        setBiomeName("Mountain Taiga");
        temperature = 0.0F;
        rainfall = BiomeGenBase.taigaHills.rainfall;
        minHeight = 0.3F;
        maxHeight = 1.2F;

        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.MOUNTAINTAIGA).build();
    }
}
