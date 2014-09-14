/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

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

public class BiomeWoodlands extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.WOODLANDS;
	}
    
    @SuppressWarnings("unchecked")
    public BiomeWoodlands()
    {
		super(BiomeSettings.WOODLANDS, Type.FOREST);
        
        //setColor(0x056621);
        setColor(0x85B53E);
        setBiomeName("Woodlands");
        temperature = BiomeGenBase.forest.temperature;
        rainfall = BiomeGenBase.forest.rainfall;
        this.setHeight(new Height(0.3F, 0.1F));
        
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
