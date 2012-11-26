/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;

public class BiomeAlpine extends ExtrabiomeGenBase {

    public BiomeAlpine() {
        super(BiomeSettings.ALPINE.getID());
        topBlock = (byte) Block.stone.blockID;
        fillerBlock = (byte) Block.stone.blockID;
        setColor(0x8DACC4);
        setEnableSnow();
        setBiomeName("Alpine");
        temperature = 0.0F;
        rainfall = 0.1F;
        minHeight = 1.3F;
        maxHeight = 2.1F;

        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));

    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(7).flowersPerChunk(0)
                .grassPerChunk(0).build();
    }
}
