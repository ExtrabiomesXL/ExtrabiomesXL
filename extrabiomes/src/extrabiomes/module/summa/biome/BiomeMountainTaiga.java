/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;

public class BiomeMountainTaiga extends ExtrabiomeGenBase {

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
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(10).grassPerChunk(1).build();
    }
}
