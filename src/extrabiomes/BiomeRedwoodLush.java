package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

public class BiomeRedwoodLush extends BiomeBase {

	private static final String NAME = "Lush Redwoods";
	private static final Extrabiome BIOME = Extrabiome.REDWOOD_LUSH;
	private WorldGenerator worldGenRedwood = null;
	private WorldGenerator worldGenFirTree = null;

	public BiomeRedwoodLush() {
		super(BIOME);
		setColor(0x4AC758);
		setBiomeName(NAME);
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(17)
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
		if (random.nextInt(2) == 0) {
			if (worldGenRedwood == null)
				if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.REDWOOD_TREE))
					worldGenRedwood = new WorldGenRedwood(false);
				else
					worldGenRedwood = new WorldGenNoOp();
			return worldGenRedwood;
		}

		if (worldGenFirTree == null)
			if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.FIR_TREE))
				worldGenFirTree = new WorldGenFirTree(false);
			else
				worldGenFirTree = new WorldGenNoOp();
		return worldGenFirTree;

	}

}
