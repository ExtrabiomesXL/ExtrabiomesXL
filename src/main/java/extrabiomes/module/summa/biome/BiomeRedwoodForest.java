/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeRedwoodForest extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.REDWOODFOREST;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Redwood Forest");
		props.setWaterColor(0x0BD626);
		props.setBaseHeight(1.2F);
		props.setHeightVariation(0.3F);
		props.setTemperature(1.1F);
		props.setRainfall(1.4F);
		return props;
	}

    public BiomeRedwoodForest()
    {
		super(getBiomeProperties(), BiomeSettings.REDWOODFOREST, Type.FOREST, Type.HILLS);
    }
}
