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

public class BiomeMiniJungle extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MINIJUNGLE;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Mini Jungle");
		// waterColorMultiplier = 0x24b01c;
		props.setWaterColor(0x41D923);
		props.setBaseHeight(0.4F);
		props.setHeightVariation(0.2F);
		props.setTemperature(Biomes.JUNGLE.getTemperature());
		props.setRainfall(Biomes.JUNGLE.getRainfall());
		return props;
	}

    public BiomeMiniJungle()
    {
		super(getBiomeProperties(), BiomeSettings.MINIJUNGLE, Type.JUNGLE);
        
        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }
}
