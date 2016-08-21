/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeAlpine extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.ALPINE;
	}
	
	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Alpine");
		props.setTemperature(0.0F);
		props.setRainfall(0.1F);
		props.setSnowEnabled();
		props.setBaseHeight(1.7F);
		props.setHeightVariation(0.4F);
		props.setWaterColor(0x8DACC4);
		return props;
	}

    public BiomeAlpine() {
		super(getBiomeProperties(), BiomeSettings.ALPINE, Type.SNOWY, Type.MOUNTAIN);
        topBlock = Blocks.STONE.getDefaultState();
        fillerBlock = Blocks.STONE.getDefaultState();

        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
    }

}
