/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeForestedIsland extends ExtrabiomeGenBase {

    @SuppressWarnings("unchecked")
	public BiomeForestedIsland() {
        super(BiomeSettings.FORESTEDISLAND.getID());

        setColor(0x62BF6C);
        setBiomeName("Forested Island");
        temperature = BiomeGenBase.forest.temperature + 0.1F;
        rainfall = BiomeGenBase.forest.rainfall;
        minHeight = -0.8F;
        maxHeight = 0.8F;

        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new CustomBiomeDecorator.Builder(this).loadSettings(DecorationSettings.FORESTEDISLAND).build();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor() {
        return ColorizerFoliage.getFoliageColor(0.4F, 0.7F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor() {
        return ColorizerGrass.getGrassColor(0.4F, 0.7F);
    }

}
