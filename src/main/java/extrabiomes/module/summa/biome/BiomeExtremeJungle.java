/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeExtremeJungle extends ExtrabiomeGenBase
{

	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.EXTREMEJUNGLE;
	}

    @SuppressWarnings("unchecked")
    public BiomeExtremeJungle()
    {
		super(BiomeSettings.EXTREMEJUNGLE, Type.JUNGLE, Type.MOUNTAIN);
        setColor(0x2c4205);
        setBiomeName("Extreme Jungle");
        temperature = BiomeGenBase.jungle.temperature;
        rainfall = BiomeGenBase.jungle.rainfall;
        // TODO: Check This new height
        this.setHeight(new Height(1.2F, 0.1F));
        
        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }
    
}
