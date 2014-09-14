/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomePineForest extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.PINEFOREST;
	}

    @SuppressWarnings("unchecked")
    public BiomePineForest()
    {
		super(BiomeSettings.PINEFOREST, Type.FOREST);
        
        setColor(0x469C7E);
        setBiomeName("Pine Forest");
        temperature = BiomeGenBase.forest.temperature;
        rainfall = BiomeGenBase.forest.rainfall;
        this.setHeight(new Height(0.2F, 0.1F));
        
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int x, int y, int z)
    {
        return ColorizerFoliage.getFoliageColor(0.4F, 0.6F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int x, int y, int z)
    {
        return ColorizerGrass.getGrassColor(0.4F, 0.6F);
    }
}
