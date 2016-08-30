/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.init.Biomes;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeWasteland extends ExtraBiome
{
    
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.WASTELAND;
	}
	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Wasteland");
		// waterColorMultiplier = 0xF08000;
		props.setWaterColor(0x9E7C41);
		props.setBaseHeight(0.1F);
		props.setHeightVariation(0.0F);
		props.setTemperature(Biomes.DESERT.getTemperature());
		props.setRainfall(Biomes.DESERT.getRainfall());
		props.setRainDisabled();
		return props;
	}

    public BiomeWasteland()
    {
		super(getBiomeProperties(), BiomeSettings.WASTELAND, Type.WASTELAND);

        spawnableCreatureList.clear();
    }
    
}
