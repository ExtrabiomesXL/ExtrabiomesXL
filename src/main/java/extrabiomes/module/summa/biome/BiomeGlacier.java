/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeGlacier extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.GLACIER;
	}

    public BiomeGlacier()
    {
		super(BiomeSettings.GLACIER, Type.FROZEN, Type.WASTELAND, Type.MOUNTAIN);
        spawnableCreatureList.clear();
        topBlock = (byte) Block.blockSnow;
        fillerBlock = (byte) Block.ice;
        setColor(0x77A696);
        setBiomeName("Glacier");
        setEnableSnow();
        temperature = 0.0F;
        rainfall = 0.0F;
        minHeight = 1.4F;
        maxHeight = 2.1F;
    }
    
}
