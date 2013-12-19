/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.SpawnListEntry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMeadow extends ExtrabiomeGenBase
{
    
    @SuppressWarnings("unchecked")
    public BiomeMeadow()
    {
        super(BiomeSettings.MEADOW.getID());
        minHeight = 0.0F;
        maxHeight = 0.0F;
        setBiomeName("Meadow");
        
        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 6, 2, 6));
    }
    
    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.MEADOW).build();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor()
    {
        return ColorizerFoliage.getFoliageColor(1.0F, 1.0F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor()
    {
        return ColorizerGrass.getGrassColor(1.0F, 1.0F);
    }
    
}
