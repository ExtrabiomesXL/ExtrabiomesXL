/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeBirchForest extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.BIRCHFOREST;
	}
	
	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Birch Forest");
		props.setWaterColor(0x62BF6C);
		props.setBaseHeight(0.2F);
		props.setHeightVariation(0.2F);
		props.setTemperature(0.4F);
		props.setRainfall(0.7F);
		return props;
	}

    public BiomeBirchForest()
    {
		super(getBiomeProperties(), BiomeSettings.BIRCHFOREST, Type.FOREST);
                
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }
    
}
