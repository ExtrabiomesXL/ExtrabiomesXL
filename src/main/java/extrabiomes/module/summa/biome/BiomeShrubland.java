/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeShrubland extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.SHRUBLAND;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Shrubland");
		props.setWaterColor(0x51B57D);
		props.setBaseHeight(0.2F);
		props.setHeightVariation(0.1F);
		props.setTemperature(0.4F);
		props.setRainfall(0.6F);
		return props;
	}

    public BiomeShrubland()
    {
		super(getBiomeProperties(), BiomeSettings.SHRUBLAND, Type.PLAINS);
        
        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 6, 1, 5));
    }
}
