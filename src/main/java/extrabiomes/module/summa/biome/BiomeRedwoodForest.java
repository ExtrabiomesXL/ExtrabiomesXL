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

public class BiomeRedwoodForest extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.REDWOODFOREST;
	}

    public BiomeRedwoodForest()
    {
		super(BiomeSettings.REDWOODFOREST, Type.FOREST, Type.HILLS);
        
        setColor(0x0BD626);
        setBiomeName("Redwood Forest");
        temperature = 1.1F;
        rainfall = 1.4F;
        this.setHeight(new Height(1.2F, 0.3F));
    }
}
