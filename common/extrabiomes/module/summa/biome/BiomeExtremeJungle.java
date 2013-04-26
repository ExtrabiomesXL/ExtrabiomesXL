/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeExtremeJungle extends ExtrabiomeGenBase {

    @SuppressWarnings("unchecked")
	public BiomeExtremeJungle() {
        super(BiomeSettings.EXTREMEJUNGLE.getID());
        setColor(0x2c4205);
        setBiomeName("Extreme Jungle");
        temperature = BiomeGenBase.jungle.temperature;
        rainfall = BiomeGenBase.jungle.rainfall;
        minHeight = 2.1F;
        maxHeight = 2.3F;

        spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.EXTREMEJUNGLE).build();
    }
}
