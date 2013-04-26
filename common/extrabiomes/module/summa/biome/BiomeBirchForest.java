/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.SpawnListEntry;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeBirchForest extends ExtrabiomeGenBase {

    @SuppressWarnings("unchecked")
	public BiomeBirchForest() {
        super(BiomeSettings.BIRCHFOREST.getID());

        setColor(0x62BF6C);
        setBiomeName("Birch Forest");
        temperature = 0.4F;
        rainfall = 0.7F;
        minHeight = 0.0F;
        maxHeight = 0.4F;

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.BIRCHFOREST).build();
    }
}
