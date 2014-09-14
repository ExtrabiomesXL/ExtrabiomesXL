/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase.Height;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMiniJungle extends ExtrabiomeGenBase
{
	@Override
	public DecorationSettings getDecorationSettings() {
		return DecorationSettings.MINIJUNGLE;
	}

    @SuppressWarnings("unchecked")
    public BiomeMiniJungle()
    {
		super(BiomeSettings.MINIJUNGLE, Type.JUNGLE);
        
        setColor(0x41D923);
        setBiomeName("Mini Jungle");
        temperature = BiomeGenBase.jungle.temperature;
        rainfall = BiomeGenBase.jungle.rainfall;
        this.setHeight(new Height(0.4F, 0.2F));
        waterColorMultiplier = 0x24b01c;
        
        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }
}
