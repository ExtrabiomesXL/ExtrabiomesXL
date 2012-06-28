package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;

public class BiomeSavanna extends BiomeBase {

	private static final String NAME = "Savanna";
	private static final Extrabiome BIOME = Extrabiome.SAVANNA;
	private WorldGenerator treeGen = null;

	public BiomeSavanna() {
		super(BIOME);

		setColor(0xBFA243);
		setBiomeName(NAME);
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(0)
				.setFlowersPerChunk(1).setGrassPerChunk(17)
				.setPurpleFlowerPerChunk(1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		if (treeGen == null)
			if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.ACACIA_TREE))
				treeGen = new WorldGenAcacia(false);
			else
				treeGen = new WorldGenNoOp();

		return treeGen;
	}

}
