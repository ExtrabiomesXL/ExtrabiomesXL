/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeSnowForest extends ExtrabiomeGenBase {

    @SuppressWarnings("unchecked")
	public BiomeSnowForest() {
        super(BiomeSettings.SNOWYFOREST.getID());

        setColor(0x5BA68D);
        setBiomeName("Snow Forest");
        temperature = BiomeGenBase.taigaHills.temperature;
        rainfall = BiomeGenBase.taigaHills.rainfall;
        minHeight = 0.1F;
        maxHeight = 0.5F;
        setEnableSnow();

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.entity.passive.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.SNOWYFOREST).build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor() {
        return ColorizerFoliage.getFoliageColor(0.0F, 0.2F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor() {
        return ColorizerGrass.getGrassColor(0.0F, 0.2F);
    }

}
