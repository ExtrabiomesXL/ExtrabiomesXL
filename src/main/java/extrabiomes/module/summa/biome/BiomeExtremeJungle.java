/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Biomes;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeExtremeJungle extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.EXTREMEJUNGLE;
	}
	
	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Extreme Jungle");
		props.setWaterColor(0x2c4205);
		props.setBaseHeight(1.2F);
		props.setHeightVariation(0.1F);
		props.setTemperature(Biomes.JUNGLE.getTemperature());
		props.setRainfall(Biomes.JUNGLE.getRainfall());
		return props;
	}

    public BiomeExtremeJungle()
    {
		super(getBiomeProperties(), BiomeSettings.EXTREMEJUNGLE, Type.JUNGLE, Type.MOUNTAIN);
        
        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }
    
}
