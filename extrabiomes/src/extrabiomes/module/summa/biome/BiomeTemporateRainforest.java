/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;

public class BiomeTemporateRainforest extends ExtrabiomeGenBase {

    public BiomeTemporateRainforest() {
        this(BiomeSettings.TEMPORATERAINFOREST.getID());
    }

    BiomeTemporateRainforest(int id) {
        super(id);

        setColor(0x338235);
        setBiomeName("Temperate Rainforest");
        temperature = 0.6F;
        rainfall = 0.9F;
        minHeight = 0.4F;
        maxHeight = 1.5F;

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(17).mushroomsPerChunk(2)
                .grassPerChunk(16).build();
    }
}
