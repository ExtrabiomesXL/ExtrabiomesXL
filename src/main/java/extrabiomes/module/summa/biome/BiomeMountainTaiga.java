/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Biomes;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeMountainTaiga extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MOUNTAINTAIGA;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Mountain Taiga");
		props.setWaterColor(0xB6659);
		props.setBaseHeight(0.75F);
		props.setHeightVariation(0.45F);
		props.setTemperature(0.0F);
		props.setRainfall(Biomes.TAIGA_HILLS.getRainfall());
		return props;
	}

    public BiomeMountainTaiga()
    {
		super(getBiomeProperties(), BiomeSettings.MOUNTAINTAIGA, Type.MOUNTAIN, Type.SNOWY, Type.FOREST);
        
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
    }
}
