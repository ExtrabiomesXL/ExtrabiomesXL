/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Biomes;
import net.minecraftforge.common.BiomeDictionary.Type;

/**
 * NB: Vanilla straight copied this biome from us back in 1.7, I am not 100%
 *     sure whether to keep this around or just drop it. For now, we're keeping
 *     it because it IS still prettier than their version.
 */

public class BiomeSavanna extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.SAVANNA;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Savanna");
		props.setWaterColor(0xBFA243);
		props.setBaseHeight(0.1F);
		props.setHeightVariation(0.05F);
		props.setTemperature(Biomes.DESERT.getTemperature());
		props.setRainfall(Biomes.DESERT.getRainfall());
		return props;
	}

    public BiomeSavanna()
    {
		super(getBiomeProperties(), BiomeSettings.SAVANNA, Type.PLAINS, Type.SANDY);
                
        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 3, 2, 4));
    }
}
