package net.minecraft.src;

import java.util.Random;

public class BlockDeadGrass extends BlockFlower {
	protected BlockDeadGrass(int i, int j) {
		super(i, j);
		setBlockBounds(0.0F, 0.0F, 0.0F, 0.01F, 0.125F, 0.01F);
	}

	protected boolean canThisPlantGrowOnThisBlockID(int i) {
		return i == mod_ExtraBiomesXL.crackedSand.blockID;
	}

	public int idDropped(int i, Random random, int j) {
		return -1;
	}
}
