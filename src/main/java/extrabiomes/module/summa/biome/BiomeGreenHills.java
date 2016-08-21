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

public class BiomeGreenHills extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.GREENHILLS;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Green Hills");
		props.setWaterColor(0x68C474);
		props.setBaseHeight(0.9F);
		props.setHeightVariation(0.3F);
		props.setTemperature(Biomes.FOREST.getTemperature() - 0.1F);
		props.setRainfall(Biomes.FOREST.getRainfall() + 0.1F);
		return props;
	}

	public BiomeGreenHills()
    {
		super(getBiomeProperties(), BiomeSettings.GREENHILLS, Type.HILLS);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return ColorizerFoliage.getFoliageColor(0.7F, 0.8F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return ColorizerGrass.getGrassColor(0.7F, 0.8F);
    }
    
}
