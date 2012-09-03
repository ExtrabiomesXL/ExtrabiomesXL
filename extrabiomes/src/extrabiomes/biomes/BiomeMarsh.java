/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.biomes;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Random;

import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenDirtBed;
import extrabiomes.terrain.WorldGenMarsh;


import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.World;

public class BiomeMarsh extends ExtrabiomeGenBase {

	private static WorldGenMarsh	genMarsh	= new WorldGenMarsh();
	private static WorldGenDirtBed	genDirtBed	= new WorldGenDirtBed();

	public BiomeMarsh(int id) {
		super(id);
		setColor(255);
		setBiomeName("Marsh");
		temperature = BiomeGenBase.swampland.temperature;
		rainfall = BiomeGenBase.swampland.rainfall;
		minHeight = -0.4F;
		maxHeight = 0.0F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(999).build();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(checkNotNull(world), checkNotNull(rand), x, z);

		for (int i = 0; i < 127; i++) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			genMarsh.generate(world, rand, x1, 0, z1);
		}

		for (int i = 0; i < 256; i++) {
			final int x1 = x + rand.nextInt(1) + 8;
			final int z1 = z + rand.nextInt(1) + 8;
			genDirtBed.generate(world, rand, x1, 0, z1);
		}
	}

}
