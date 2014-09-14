/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeRedwoodLush extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.REDWOODLUSH;
	}

    public BiomeRedwoodLush()
    {
		super(BiomeSettings.REDWOODLUSH, Type.FOREST, Type.HILLS);
        
        setColor(0x4AC758);
        setBiomeName("Lush Redwoods");
        temperature = 1.1F;
        rainfall = 1.4F;
        this.setHeight(new Height(1.2F, 0.3F));
    }
}
