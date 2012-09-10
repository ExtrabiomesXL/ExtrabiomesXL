/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.flora;

import net.minecraft.src.Block;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.ExtrabiomesBiomeDecorations;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.BiomeDecoration;
import extrabiomes.terrain.WorldGenChunkOasis;

public class FloraManager {

	private static boolean	initialized	= false;

	private static void configureMountainRidgeDecorations() {
		ExtrabiomesBiomeDecorations.biomeDecorations.get().put(
				BiomeManager.mountainridge.get(),
				new BiomeDecoration(999, new WorldGenChunkOasis()));
	}

	private static void configureTrees() {
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.grass.blockID));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.dirt.blockID));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.tilledField.blockID));
	}

	public void addCustomFlora() {
		if (initialized) return;
		initialized = true;

		configureTrees();

		configureMountainRidgeDecorations();
	}

}
