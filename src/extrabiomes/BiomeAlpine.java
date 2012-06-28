package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BiomeAlpine extends BiomeGenBase {

	final WorldGenerator treeGen;
	final Extrabiome biome = Extrabiome.ALPINE;
	
	public BiomeAlpine(int id) {
		super(id);
		topBlock = (byte) Block.stone.blockID;
		fillerBlock = (byte) Block.stone.blockID;
		setColor(0x8DACC4);
		setEnableSnow();
		setBiomeName("Alpine");
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 1.3F;
		maxHeight = 2.1F;

		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.FIR_TREE))
			treeGen = new WorldGenFirTree(false);
		else
			treeGen = new WorldGenNoOp();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.ALPINE).setTreesPerChunk(7)
				.setGrassPerChunk(0).setFlowersPerChunk(0);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		return treeGen;
	}

}
