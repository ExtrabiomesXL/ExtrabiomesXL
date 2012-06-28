package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class BiomeRedwoodLush extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.REDWOOD_LUSH;
	final WorldGenerator worldGenRedwood;
	final WorldGenerator worldGenFirTree;

	public BiomeRedwoodLush(int par1) {
		super(par1);
		setColor(0x4AC758);
		setBiomeName("Lush Redwoods");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.REDWOOD_TREE))
			worldGenRedwood = new WorldGenRedwood(false);
		else
			worldGenRedwood = new WorldGenNoOp();

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.FIR_TREE))
			worldGenFirTree = new WorldGenFirTree(false);
		else
			worldGenFirTree = new WorldGenNoOp();
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.REDWOOD_LUSH).setTreesPerChunk(17)
				.setRootsPerChunk(15).setLeafPilePerChunk(15);
	}

	@Override
	public WorldGenerator func_48410_b(Random par1Random) {
		if (par1Random.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.func_48410_b(par1Random);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(2) == 0)
			return worldGenRedwood;
		return worldGenFirTree;

	}

}
