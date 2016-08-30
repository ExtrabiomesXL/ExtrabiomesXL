/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeRedwoodLush extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.REDWOODLUSH;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Lush Redwoods");
		props.setWaterColor(0x4AC758);
		props.setBaseHeight(1.2F);
		props.setHeightVariation(0.3F);
		props.setTemperature(1.1F);
		props.setRainfall(1.4F);
		return props;
	}

    public BiomeRedwoodLush()
    {
		super(getBiomeProperties(), BiomeSettings.REDWOODLUSH, Type.FOREST, Type.HILLS);
    }
}
