/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import extrabiomes.lib.BiomeSettings;

public class BiomeIceWasteland extends ExtrabiomeGenBase {

    public BiomeIceWasteland() {
        super(BiomeSettings.ICEWASTELAND.getID());

        spawnableCreatureList.clear();
        topBlock = (byte) Block.blockSnow.blockID;
        fillerBlock = (byte) Block.blockSnow.blockID;
        setEnableSnow();
        setColor(0x7DA0B5);
        setBiomeName("Ice Wasteland");
        temperature = 0.0F;
        rainfall = 0.1F;
        minHeight = 0.3F;
        maxHeight = 0.4F;
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(0).build();
    }

}
