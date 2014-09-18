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

public class BiomeTundra extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.TUNDRA;
	}

    public BiomeTundra()
    {
		super(BiomeSettings.TUNDRA, Type.SNOWY, Type.SANDY);
        
        setColor(0x305A85);
        setBiomeName("Tundra");
        temperature = 0.0F;
        rainfall = 0.0F;
        this.setHeight(new Height(0.1F, 0.1F));
    }
}
