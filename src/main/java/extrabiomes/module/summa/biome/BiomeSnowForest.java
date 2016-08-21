/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.Biome.Height;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeSnowForest extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.SNOWYFOREST;
	}

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("");
		props.setWaterColor();
		props.setBaseHeight();
		props.setHeightVariation();
		props.setTemperature();
		props.setRainfall();
		return props;
	}

    @SuppressWarnings("unchecked")
    public BiomeSnowForest()
    {
		super(BiomeSettings.SNOWYFOREST, Type.FOREST, Type.SNOWY);
        
        setColor(0x5BA68D);
        setBiomeName("Snow Forest");
        temperature = Biome.taigaHills.temperature;
        rainfall = Biome.taigaHills.rainfall;
        this.setHeight(new Height(0.3F, 0.2F));
        setEnableSnow();
        
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return ColorizerFoliage.getFoliageColor(0.0F, 0.2F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return ColorizerGrass.getGrassColor(0.0F, 0.2F);
    }
}
