/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraftforge.common.BiomeDictionary.Type;

// TODO: Fix this spelling everywhere :)
public class BiomeTemporateRainforest extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.TEMPORATERAINFOREST;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Temperate Rainforest");
		props.setWaterColor(0x338235);
		props.setBaseHeight(0.95F);
		props.setHeightVariation(0.55F);
		props.setTemperature(0.6F);
		props.setRainfall(0.9F);
		return props;
	}

    public BiomeTemporateRainforest()
    {
		super(getBiomeProperties(), BiomeSettings.TEMPORATERAINFOREST, Type.FOREST, Type.HILLS);
        
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }
}
