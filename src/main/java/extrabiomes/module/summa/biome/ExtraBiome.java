/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Random;

import com.google.common.base.Optional;

import extrabiomes.api.BiomeManager;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.DecorationSettings;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

@SuppressWarnings("deprecation")
public abstract class ExtraBiome extends Biome {
	protected BiomeSettings biomeSettings;
	protected Type[] biomeTypeFlags;

	// protected DecorationSettings decorationSettings;

	protected ExtraBiome(BiomeProperties biomeProps, BiomeSettings biomeSettings, Type... biomeTypeFlags) {
		super(biomeProps);
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

	@Override
	public WorldGenAbstractTree genBigTreeChance(Random rand) {
		final Optional<? extends WorldGenerator> treeGen = BiomeManager.chooseRandomTreeGenforBiome(rand, this);
		if (treeGen.isPresent() && treeGen.get() instanceof WorldGenAbstractTree) {
			return (WorldGenAbstractTree) treeGen.get();
		}
		return super.genBigTreeChance(rand);
	}
  
	public void canSpawnEvent(CheckSpawn event) { }
 
	/** Deprecated/renamed methods from old format, included here for sanity when porting to 1.10 */
	@Deprecated
	protected void setColor(int i) {
	}
	@Deprecated
	protected void setEnableSnow() {
	}
	@Deprecated
	protected void setBiomeName(String string) {
	}
	@Deprecated
	public int getBiomeFoliageColor(int x, int y, int z) {
		return 0;
	}
	@Deprecated
	public int getBiomeGrassColor(int x, int y, int z) {
		return 0;
	}

}
