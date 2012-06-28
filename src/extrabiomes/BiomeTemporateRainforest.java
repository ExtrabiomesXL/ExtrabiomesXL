package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;

public class BiomeTemporateRainforest extends BiomeGenBase {

	protected Extrabiome biome = Extrabiome.TEMPORATE_RAINFOREST;

	WorldGenerator treeGen;
	WorldGenerator treeGen2;

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

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.FIR_TREE)) {
			treeGen = new WorldGenFirTree(false);
			treeGen2 = new WorldGenFirTree2(false);
		} else {
			treeGen = new WorldGenNoOp();
			treeGen2 = treeGen;
		}
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.TEMPORATE_RAINFOREST).setTreesPerChunk(17)
				.setGrassPerChunk(16).setMushroomsPerChunk(2)
				.setToadstoolsPerChunk(2);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(3) == 0)
			return treeGen;

		return treeGen2;
	}

	@Override
	public WorldGenerator func_48410_b(Random par1Random) {
		if (par1Random.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);
		return super.func_48410_b(par1Random);
	}

}
