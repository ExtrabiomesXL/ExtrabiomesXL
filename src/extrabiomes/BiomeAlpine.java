package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;

public class BiomeAlpine extends BiomeBase {

	private static final String NAME = "Alpine";
	private static final Extrabiome BIOME = Extrabiome.ALPINE;
	private WorldGenerator treeGen = null;

	public BiomeAlpine() {
		super(BIOME);
		setProperties();

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(7)
				.setGrassPerChunk(0).setFlowersPerChunk(0);
	}

	private void createTreeGen() {
		if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.FIR_TREE))
			treeGen = new WorldGenFirTree(false);
		else
			treeGen = new WorldGenNoOp();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		if (treeGen == null)
			createTreeGen();

		return treeGen;
	}

	private void setProperties() {
		topBlock = (byte) Block.stone.blockID;
		fillerBlock = (byte) Block.stone.blockID;
		setColor(0x8DACC4);
		setEnableSnow();
		setBiomeName(NAME);
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 1.3F;
		maxHeight = 2.1F;
	}

}
