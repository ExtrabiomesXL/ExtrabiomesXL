/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugins.pamsmods;

import java.lang.reflect.Constructor;
import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenerator;
import extrabiomes.Log;
import extrabiomes.api.IBiomeDecoration;

public enum PamGen implements IBiomeDecoration {
	BUSHES, TREES, WARM_BUSHES, WARM_TREES;

	private Constructor		ctor			= null;
	private int				rarity			= 0;
	private WorldGenerator	generators[]	= null;

	@Override
	public void decorate(World world, Random rand, int xChunk,
			int zChunk)
	{
		if (generators == null) initializeGenerators();
		for (int i = 0; i < rarity; i++)
			for (final WorldGenerator generator : generators) {
				final int x = xChunk + rand.nextInt(16);
				final int y = rand.nextInt(128);
				final int z = zChunk + rand.nextInt(16);
				final int id = world.getBlockId(x, y - 1, z);
				if (this == TREES || this == WARM_TREES)
					if (id != Block.dirt.blockID
							&& id != Block.grass.blockID) continue;
				generator.generate(world, rand, x, y, z);
			}
	}

	private void initializeGenerators() {
		generators = new WorldGenerator[this == WARM_TREES ? 16
				: this == WARM_BUSHES ? 7 : 8];
		for (int i = 0; i < generators.length; i++)
			generators[i] = newInstance(i);
	}

	public WorldGenerator newInstance(int subType) {
		if (ctor != null)
			try {
				final Object arglist[] = { Integer.valueOf(subType) };
				return (WorldGenerator) ctor.newInstance(arglist);
			} catch (final Exception e) {
				Log.write("Could not create HarvestCraft's "
						+ toString() + " generator.");
			}
		return null;
	}

	public void setConstructor(Constructor ctor) {
		this.ctor = ctor;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

}
