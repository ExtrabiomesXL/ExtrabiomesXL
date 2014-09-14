/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeAlpine extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.ALPINE;
	}

    @SuppressWarnings("unchecked")
    public BiomeAlpine()
    {
		super(BiomeSettings.ALPINE, Type.SNOWY, Type.MOUNTAIN);
        topBlock = Blocks.stone;
        fillerBlock = Blocks.stone;
        setColor(0x8DACC4);
        setEnableSnow();
        setBiomeName("Alpine");
        temperature = 0.0F;
        rainfall = 0.1F;
        this.setHeight(new Height(1.7F, 0.4F));
        
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
    }
    
}
