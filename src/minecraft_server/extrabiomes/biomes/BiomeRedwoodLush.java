package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenFirTree;
import extrabiomes.terrain.WorldGenNoOp;
import extrabiomes.terrain.WorldGenRedwood;

public class BiomeRedwoodLush extends ExtrabiomeGenBase {

	private WorldGenerator worldGenRedwood = null;
	private WorldGenerator worldGenFirTree = null;

	public BiomeRedwoodLush(int id) {
		super(id);
		setColor(0x4AC758);
		setBiomeName("Lush Redwoods");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random) {
		if (par1Random.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.getRandomWorldGenForGrass(par1Random);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		if (random.nextInt(2) == 0) {
			if (worldGenRedwood == null)
				if (TerrainGenManager.enableRedwoodGen)
					worldGenRedwood = new WorldGenRedwood(false);
				else
					worldGenRedwood = new WorldGenNoOp();
			return worldGenRedwood;
		}

		if (worldGenFirTree == null)
			if (TerrainGenManager.enableFirGen)
				worldGenFirTree = new WorldGenFirTree(false);
			else
				worldGenFirTree = new WorldGenNoOp();
		return worldGenFirTree;

	}

}
