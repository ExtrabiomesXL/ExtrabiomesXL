/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Height;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMarsh extends ExtraBiome
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MARSH;
	}

    public BiomeMarsh()
    {
		super(BiomeSettings.MARSH, Type.SWAMP);
        
        setColor(255);
        setBiomeName("Marsh");
        temperature = Biome.swampland.temperature;
        rainfall = Biome.swampland.rainfall;
        this.setHeight(new Height(-0.2F, 0.2F));
    }
    
}
