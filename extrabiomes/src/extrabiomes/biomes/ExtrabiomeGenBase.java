/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Random;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldGenerator;

import com.google.common.base.Optional;

import extrabiomes.api.BiomeManager;

public class ExtrabiomeGenBase extends BiomeGenBase {

	public static Collection<BiomeGenBase>	customBiomeList	= new LinkedHashSet<BiomeGenBase>();

	protected ExtrabiomeGenBase(int id) {
		super(id);
		customBiomeList.add(this);
	}

	protected void disableRain() {
		try {
			final Field enabledRainField = BiomeGenBase.class
					.getDeclaredField("S"); // enableRain
			enabledRainField.setAccessible(true);
			enabledRainField.setBoolean(this, false);
		} catch (final Throwable e) {
			// Do nothing... (This is NOT critical)
		}
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		final Optional<? extends WorldGenerator> grassGen = BiomeManager
				.chooseRandomGrassGenforBiome(rand, this);
		if (grassGen.isPresent()) return grassGen.get();
		return super.getRandomWorldGenForGrass(rand);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		final Optional<? extends WorldGenerator> treeGen = BiomeManager
				.chooseRandomTreeGenforBiome(rand, this);
		if (treeGen.isPresent()) return treeGen.get();
		return super.getRandomWorldGenForTrees(rand);
	}
}
