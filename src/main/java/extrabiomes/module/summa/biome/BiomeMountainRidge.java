/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.BiomeProperties;
import net.minecraft.world.biome.Biome.Height;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraftforge.common.BiomeDictionary.Type;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

public class BiomeMountainRidge extends ExtraBiome {
  @Override
  public DecorationSettings getDecorationSettings() {
  	return DecorationSettings.MOUNTAINRIDGE;
  }

	private static BiomeProperties getBiomeProperties() {
		final BiomeProperties props = new BiomeProperties("");
		props.setWaterColor();
		props.setBaseHeight();
		props.setHeightVariation();
		props.setTemperature();
		props.setRainfall();
		return props;
	}

  @SuppressWarnings("unchecked")
  public BiomeMountainRidge() {
    super(BiomeSettings.MOUNTAINRIDGE, Type.MOUNTAIN, Type.SANDY);
    setColor(0xC4722F);
    setBiomeName("Red Rock Mountains");
    temperature = Biome.desert.temperature;
    rainfall = Biome.desert.rainfall;
    // TODO: Check height
    this.setHeight(new Height(1.7F, -0.1F));
    setDisableRain();
    spawnableCreatureList.clear();
    spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 3, 1, 3));
  }
}
