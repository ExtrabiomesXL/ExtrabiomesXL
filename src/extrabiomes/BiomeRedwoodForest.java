package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;

public class BiomeRedwoodForest extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.REDWOOD_FOREST;

	final WorldGenerator treeGen;

	public BiomeRedwoodForest(int id) {
		super(id);

		setColor(0x0BD626);
		setBiomeName("Redwood Forest");
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.REDWOOD_TREE))
			treeGen = new WorldGenRedwood(false);
		else
			treeGen = new WorldGenNoOp();
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.REDWOOD_FOREST).setTreesPerChunk(17);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return treeGen;
	}

}
