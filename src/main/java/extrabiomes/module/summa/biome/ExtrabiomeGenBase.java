/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Random;

import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

import com.google.common.base.Optional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import extrabiomes.api.BiomeManager;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;

@SuppressWarnings("deprecation")
public abstract class ExtrabiomeGenBase extends BiomeGenBase {
  protected BiomeSettings biomeSettings;
  protected Type[] biomeTypeFlags;

  // protected DecorationSettings decorationSettings;

  protected ExtrabiomeGenBase(BiomeSettings biomeSettings, Type... biomeTypeFlags) {
    super(biomeSettings.getID());
    this.biomeSettings = biomeSettings;
    this.biomeTypeFlags = biomeTypeFlags;
    /*
     * NB: DecorationSettings cannot be set here, it MUST be hard-coded because
     * of how the vanilla parent class's constructor works :(
     */
  }

  public BiomeSettings getBiomeSettings() {
    return biomeSettings;
  }

  public Type[] getBiomeTypeFlags() {
    return biomeTypeFlags;
  }

  abstract public DecorationSettings getDecorationSettings();

  @Override
  public BiomeDecorator createBiomeDecorator() {
    try {
      return new CustomBiomeDecorator.Builder(this).loadSettings(getDecorationSettings()).build();
    } catch (Exception e) {
      LogHelper.severe("No decoration settings found for " + this);
      return null;
    }
  }

  @Override
  public WorldGenerator getRandomWorldGenForGrass(Random rand) {
    final Optional<? extends WorldGenerator> grassGen = BiomeManager.chooseRandomGrassGenforBiome(rand, this);
    if (grassGen.isPresent())
      return grassGen.get();
    return super.getRandomWorldGenForGrass(rand);
  }

  // public WorldGenerator getRandomWorldGenForTrees(Random rand)
  @Override
  public WorldGenAbstractTree func_150567_a(Random rand) {
    final Optional<? extends WorldGenerator> treeGen = BiomeManager.chooseRandomTreeGenforBiome(rand, this);
    if (treeGen.isPresent() && treeGen.get() instanceof WorldGenAbstractTree) {
      return (WorldGenAbstractTree) treeGen.get();
    }
    return super.func_150567_a(rand);
  }
  
  public void canSpawnEvent(CheckSpawn event) { }
}
