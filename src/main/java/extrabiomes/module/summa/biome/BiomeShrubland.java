/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeShrubland extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.SHRUBLAND;
	}

    @SuppressWarnings("unchecked")
    public BiomeShrubland()
    {
		super(BiomeSettings.SHRUBLAND, Type.PLAINS);
        
        setColor(0x51B57D);
        setBiomeName("Shrubland");
        temperature = 0.4F;
        rainfall = 0.6F;
        this.setHeight(new Height(0.2F, 0.1F));
        
        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 6, 1, 5));
    }
}
