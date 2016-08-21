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

public class BiomeIceWasteland extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.ICEWASTELAND;
	}
	
	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Ice Wasteland");
		props.setWaterColor(0x7DA0B5);
		props.setBaseHeight(0.35F);
		props.setHeightVariation(0.05F);
		props.setTemperature(0.0F);
		props.setRainfall(0.1F);
		props.setSnowEnabled();
		return props;
	}

    public BiomeIceWasteland()
    {
		super(getBiomeProperties(), BiomeSettings.ICEWASTELAND, Type.SNOWY, Type.WASTELAND);
        
        spawnableCreatureList.clear();
        topBlock = Blocks.SNOW.getDefaultState();
        fillerBlock = Blocks.SNOW.getDefaultState();
    }
    
}
