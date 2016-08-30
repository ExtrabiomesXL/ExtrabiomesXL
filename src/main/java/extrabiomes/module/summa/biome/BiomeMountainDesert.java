/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeMountainDesert extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MOUNTAINDESERT;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Mountainous Desert");
		props.setWaterColor(0xFA9418);
		props.setBaseHeight(0.9F);
		props.setHeightVariation(0.5F);
		props.setTemperature(Biomes.DESERT_HILLS.getTemperature());
		props.setRainfall(Biomes.DESERT_HILLS.getRainfall());
		props.setRainDisabled();
		return props;
	}

    public BiomeMountainDesert()
    {
		super(getBiomeProperties(), BiomeSettings.MOUNTAINDESERT, Type.MOUNTAIN, Type.SANDY);
        
        topBlock = Blocks.SAND.getDefaultState();
        fillerBlock = Blocks.SAND.getDefaultState();
        spawnableCreatureList.clear();
    }
}
