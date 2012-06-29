
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.server.BiomeDecorator;
import net.minecraft.server.BiomeMeta;
import net.minecraft.server.Block;
import net.minecraft.server.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeAlpine extends ExtrabiomeGenBase {
	public BiomeAlpine(int i) {
		super(i);
		A = (byte) Block.STONE.id;
		B = (byte) Block.STONE.id;
		b(0x8dacc4);
		b();
		a("Alpine");
		F = 0.0F;
		G = 0.1F;
		D = 1.3F;
		E = 2.1F;
		K.add(new BiomeMeta(net.minecraft.server.EntityWolf.class, 8, 4, 4));
	}

	/**
	 * Allocate a new BiomeDecorator for this BiomeGenBase
	 */
	protected BiomeDecorator a() {
		return new extrabiomes.terrain.CustomBiomeDecorator.Builder(
				this).treesPerChunk(7).flowersPerChunk(0)
				.grassPerChunk(0).build();
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	public WorldGenerator a(Random random) {
		if (TerrainGenManager.enableFirGen)
			return TerrainGenManager.treeFactory.makeTreeGenerator(
					false, extrabiomes.api.ITreeFactory.TreeType.FIR);
		else
			return new WorldGenNoOp();
	}
}
