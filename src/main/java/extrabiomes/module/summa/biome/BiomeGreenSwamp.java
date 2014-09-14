/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeGreenSwamp extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.GREENSWAMP;
	}

    @SuppressWarnings("unchecked")
    public BiomeGreenSwamp()
    {
		super(BiomeSettings.GREENSWAMP, Type.SWAMP, Type.WATER);
        
        setColor(0x68C474);
        setBiomeName("Green Swamplands");
        temperature = BiomeGenBase.swampland.temperature - 0.1F;
        rainfall = BiomeGenBase.swampland.rainfall;
        this.setHeight(new Height(-0.05F, 0.15F));
        spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }
    
}
