/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeWasteland extends ExtrabiomeGenBase
{
    
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.WASTELAND;
	}

    public BiomeWasteland()
    {
		super(BiomeSettings.WASTELAND, Type.WASTELAND);
        
        setColor(0x9E7C41);
        setBiomeName("Wasteland");
        temperature = BiomeGenBase.desert.temperature;
        rainfall = BiomeGenBase.desert.rainfall;
        this.setHeight(new Height(0.1F, 0.0F));
        waterColorMultiplier = 0xF08000;
        
        spawnableCreatureList.clear();
        
        setDisableRain();
    }
    
}
