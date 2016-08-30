/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeTundra extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.TUNDRA;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Tundra");
		props.setWaterColor(0x305A85);
		props.setBaseHeight(0.1F);
		props.setHeightVariation(0.1F);
		props.setTemperature(0.0F);
		props.setRainfall(0.0F);
		return props;
	}

    public BiomeTundra()
    {
		super(getBiomeProperties(), BiomeSettings.TUNDRA, Type.SNOWY, Type.SANDY);
    }
}
