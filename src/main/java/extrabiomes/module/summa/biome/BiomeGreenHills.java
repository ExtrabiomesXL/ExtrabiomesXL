/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeGreenHills extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.GREENHILLS;
	}

    public BiomeGreenHills()
    {
		super(BiomeSettings.GREENHILLS);
        
        setColor(0x68C474);
        setBiomeName("Green Hills");
        temperature = BiomeGenBase.forest.temperature - 0.1F;
        rainfall = BiomeGenBase.forest.rainfall + 0.1F;
        minHeight = 0.6F;
        maxHeight = 1.2F;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor()
    {
        return ColorizerFoliage.getFoliageColor(0.7F, 0.8F);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor()
    {
        return ColorizerGrass.getGrassColor(0.7F, 0.8F);
    }
    
}
