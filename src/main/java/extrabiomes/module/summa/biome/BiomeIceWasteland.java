/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeIceWasteland extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.ICEWASTELAND;
	}

    public BiomeIceWasteland()
    {
		super(BiomeSettings.ICEWASTELAND, Type.SNOWY, Type.WASTELAND);
        
        spawnableCreatureList.clear();
        topBlock = Blocks.snow;
        fillerBlock = Blocks.snow;
        setEnableSnow();
        setColor(0x7DA0B5);
        setBiomeName("Ice Wasteland");
        temperature = 0.0F;
        rainfall = 0.1F;
        this.setHeight(new Height(0.35F, 0.05F));
    }
    
}
