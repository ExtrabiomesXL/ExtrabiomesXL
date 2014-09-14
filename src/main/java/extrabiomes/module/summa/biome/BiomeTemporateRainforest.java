/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeTemporateRainforest extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.TEMPORATERAINFOREST;
	}

    @SuppressWarnings("unchecked")
	public BiomeTemporateRainforest()
    {
		super(BiomeSettings.TEMPORATERAINFOREST, Type.FOREST, Type.HILLS);
        
        setColor(0x338235);
        setBiomeName("Temperate Rainforest");
        temperature = 0.6F;
        rainfall = 0.9F;
        this.setHeight(new Height(0.95F, 0.55F));
        
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }
}
