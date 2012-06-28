package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenFirTree;
import extrabiomes.terrain.WorldGenFirTree2;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeTemporateRainforest extends ExtrabiomeGenBase {

	protected WorldGenerator treeGen = null;
	protected WorldGenerator treeGen2 = null;

	public BiomeTemporateRainforest(int id) {
		super(id);

		setColor(0x338235);
		setBiomeName("Temperate Rainforest");
		temperature = 0.6F;
		rainfall = 0.9F;
		minHeight = 0.4F;
		maxHeight = 1.5F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.mushroomsPerChunk(2).grassPerChunk(16).build();
	}

	@Override
	public WorldGenerator func_48410_b(Random par1Random) {
		if (par1Random.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.func_48410_b(par1Random);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(3) == 0) {
			if (treeGen == null)
				if (TerrainGenManager.enableFirGen) {
					treeGen = new WorldGenFirTree(false);
				} else {
					treeGen = new WorldGenNoOp();
				}

			return treeGen;
		}
		if (treeGen2 == null)
			if (TerrainGenManager.enableFirGen)
				treeGen2 = new WorldGenFirTree2(false);
			else
				treeGen2 = new WorldGenNoOp();

		return treeGen2;
	}

}
