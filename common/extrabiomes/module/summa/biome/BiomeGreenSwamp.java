/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeGreenSwamp extends ExtrabiomeGenBase {

    @SuppressWarnings("unchecked")
	public BiomeGreenSwamp() {
        super(BiomeSettings.GREENSWAMP.getID());

        setColor(0x68C474);
        setBiomeName("Green Swamplands");
        temperature = BiomeGenBase.swampland.temperature - 0.1F;
        rainfall = BiomeGenBase.swampland.rainfall;
        minHeight = -0.2F;
        maxHeight = 0.1F;
        spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 1, 1, 1));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.GREENSWAMP).build();
    }
}
