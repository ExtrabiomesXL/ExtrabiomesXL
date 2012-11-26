/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.SpawnListEntry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import extrabiomes.lib.BiomeSettings;

class BiomeSnowForest extends ExtrabiomeGenBase {

    public BiomeSnowForest() {
        super(BiomeSettings.SNOWYFOREST.getID());

        setColor(0x5BA68D);
        setBiomeName("Snow Forest");
        temperature = BiomeGenBase.taigaHills.temperature;
        rainfall = BiomeGenBase.taigaHills.rainfall;
        minHeight = 0.1F;
        maxHeight = 0.5F;
        setEnableSnow();

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(8).flowersPerChunk(1)
                .grassPerChunk(4).build();
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
