/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMountainTaiga extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MOUNTAINTAIGA;
	}

    @SuppressWarnings("unchecked")
    public BiomeMountainTaiga()
    {
		super(BiomeSettings.MOUNTAINTAIGA, Type.MOUNTAIN, Type.SNOWY, Type.FOREST);
        
        setColor(0xB6659);
        setBiomeName("Mountain Taiga");
        temperature = 0.0F;
        rainfall = BiomeGenBase.taigaHills.rainfall;
        this.setHeight(new Height(0.75F, 0.45F));
        
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
    }
}
