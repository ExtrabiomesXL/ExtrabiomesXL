/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityWolf;
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
		super(BiomeSettings.ALPINE, Type.FROZEN, Type.MOUNTAIN);
        topBlock = (byte) Block.stone.blockID;
        fillerBlock = (byte) Block.stone.blockID;
        setColor(0x8DACC4);
        setEnableSnow();
        setBiomeName("Alpine");
        temperature = 0.0F;
        rainfall = 0.1F;
        minHeight = 1.3F;
        maxHeight = 2.1F;
        
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
    }
    
}
