/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMountainDesert extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MOUNTAINDESERT;
	}

    public BiomeMountainDesert()
    {
		super(BiomeSettings.MOUNTAINDESERT);
        
        setColor(0xFA9418);
        setBiomeName("Mountainous Desert");
        temperature = BiomeGenBase.desertHills.temperature;
        rainfall = BiomeGenBase.desertHills.rainfall;
        minHeight = 0.4F;
        maxHeight = 1.4F;
        topBlock = (byte) Block.sand.blockID;
        fillerBlock = (byte) Block.sand.blockID;
        spawnableCreatureList.clear();
        setDisableRain();
    }
}
