/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMountainDesert extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MOUNTAINDESERT;
	}

    public BiomeMountainDesert()
    {
		super(BiomeSettings.MOUNTAINDESERT, Type.MOUNTAIN, Type.SANDY);
        
        setColor(0xFA9418);
        setBiomeName("Mountainous Desert");
        temperature = BiomeGenBase.desertHills.temperature;
        rainfall = BiomeGenBase.desertHills.rainfall;
        this.setHeight(new Height(0.9F, 0.5F));
        topBlock = Blocks.sand;
        fillerBlock = Blocks.sand;
        spawnableCreatureList.clear();
        setDisableRain();
    }
}
