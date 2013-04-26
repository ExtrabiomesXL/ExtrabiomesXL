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

public class BiomeTemporateRainforest extends ExtrabiomeGenBase {

    public BiomeTemporateRainforest() {
        this(BiomeSettings.TEMPORATERAINFOREST.getID());
    }

    @SuppressWarnings("unchecked")
	BiomeTemporateRainforest(int id) {
        super(id);

        setColor(0x338235);
        setBiomeName("Temperate Rainforest");
        temperature = 0.6F;
        rainfall = 0.9F;
        minHeight = 0.4F;
        maxHeight = 1.5F;

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.TEMPORATERAINFOREST).build();
    }
}
