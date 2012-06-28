package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

public class BiomeRedwoodForest extends BiomeBase {

	private static final String NAME = "Redwood Forest";
	private static final Extrabiome BIOME = Extrabiome.REDWOOD_FOREST;

	private WorldGenerator treeGen = null;

	public BiomeRedwoodForest() {
		super(BIOME);

		setColor(0x0BD626);
		setBiomeName(NAME);
		temperature = 1.1F;
		rainfall = 1.4F;
		minHeight = 0.9F;
		maxHeight = 1.5F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(17);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (treeGen == null)
			if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.REDWOOD_TREE))
				treeGen = new WorldGenRedwood(false);
			else
				treeGen = new WorldGenNoOp();

		return treeGen;
	}

}
