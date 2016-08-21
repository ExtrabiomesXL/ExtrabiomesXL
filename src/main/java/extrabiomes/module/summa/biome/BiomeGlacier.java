/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeGlacier extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.GLACIER;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Glacier");
		props.setWaterColor(0x77A696);
		props.setBaseHeight(1.75F);
		props.setHeightVariation(0.35F);
		props.setTemperature(0.0F);
		props.setRainfall(0.0F);
		props.setSnowEnabled();
		return props;
	}

	public BiomeGlacier()
    {
		super(getBiomeProperties(), BiomeSettings.GLACIER, Type.SNOWY, Type.WASTELAND, Type.MOUNTAIN);
        spawnableCreatureList.clear();
        topBlock = Blocks.SNOW.getDefaultState();
        fillerBlock = Blocks.ICE.getDefaultState();
    }
    
}
