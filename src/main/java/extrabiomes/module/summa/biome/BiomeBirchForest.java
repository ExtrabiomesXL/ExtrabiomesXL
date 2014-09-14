/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeBirchForest extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.BIRCHFOREST;
	}

    @SuppressWarnings("unchecked")
    public BiomeBirchForest()
    {
		super(BiomeSettings.BIRCHFOREST, Type.FOREST);
        
        setColor(0x62BF6C);
        setBiomeName("Birch Forest");
        temperature = 0.4F;
        rainfall = 0.7F;
        this.setHeight(new Height(0.2F, 0.2F));
        
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }
    
}
