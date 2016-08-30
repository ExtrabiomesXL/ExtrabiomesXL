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

public class BiomeWoodlands extends ExtraBiome
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.WOODLANDS;
	}
    
	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("Woodlands");
		props.setWaterColor(0x85B53E);
		props.setBaseHeight(0.3F);
		props.setHeightVariation(0.1F);
		props.setTemperature(Biomes.FOREST.getTemperature());
		props.setRainfall(Biomes.FOREST.getRainfall());
		return props;
	}

    public BiomeWoodlands()
    {
		super(getBiomeProperties(), BiomeSettings.WOODLANDS, Type.FOREST);        
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return ColorizerFoliage.getFoliageColor(1.0F, 1.0F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return ColorizerGrass.getGrassColor(1.0F, 1.0F);
    }
}
