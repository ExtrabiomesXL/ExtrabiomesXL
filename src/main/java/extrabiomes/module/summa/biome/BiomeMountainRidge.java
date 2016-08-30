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

public class BiomeMountainRidge extends ExtraBiome {
  @Override
  public DecorationSettings getDecorationSettings() {
  	return DecorationSettings.MOUNTAINRIDGE;
  }

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Red Rock Mountains");
		props.setWaterColor(0xC4722F);
		props.setBaseHeight(1.7F);
		props.setHeightVariation(-0.1F);	// TODO: verify if this is correct
		props.setTemperature(Biomes.DESERT.getTemperature());
		props.setRainfall(Biomes.DESERT.getRainfall());
		props.setRainDisabled();
		return props;
	}

  public BiomeMountainRidge() {
    super(getBiomeProperties(), BiomeSettings.MOUNTAINRIDGE, Type.MOUNTAIN, Type.SANDY);
    spawnableCreatureList.clear();
    spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 3, 1, 3));
  }
}
