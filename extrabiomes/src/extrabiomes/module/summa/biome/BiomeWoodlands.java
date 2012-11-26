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

class BiomeWoodlands extends ExtrabiomeGenBase {

    public BiomeWoodlands() {
        super(BiomeSettings.WOODLANDS.getID());

        setColor(0x85B53E);
        setBiomeName("Woodlands");
        temperature = BiomeGenBase.forest.temperature;
        rainfall = BiomeGenBase.forest.rainfall;
        minHeight = 0.2F;
        maxHeight = 0.4F;

        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).treesPerChunk(8).grassPerChunk(3).build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor() {
        return ColorizerFoliage.getFoliageColor(1.0F, 0.2F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor() {
        return ColorizerGrass.getGrassColor(1.0F, 0.2F);
    }

}
