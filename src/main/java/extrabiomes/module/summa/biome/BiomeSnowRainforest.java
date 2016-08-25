/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.init.Biomes;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeSnowRainforest extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.SNOWYRAINFOREST;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Snowy Rainforest");
		props.setWaterColor(0x338277);
		props.setBaseHeight(0.95F);
		props.setHeightVariation(0.55F);
		props.setTemperature(Biomes.TAIGA_HILLS.getTemperature());
		props.setRainfall(Biomes.TAIGA_HILLS.getRainfall());
		props.setSnowEnabled();
		return props;
	}

	public BiomeSnowRainforest()
    {
		super(getBiomeProperties(), BiomeSettings.SNOWYRAINFOREST, Type.FOREST, Type.HILLS, Type.SNOWY);

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return ColorizerFoliage.getFoliageColor(0.0F, 0.1F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return ColorizerGrass.getGrassColor(0.0F, 0.1F);
    }
}
