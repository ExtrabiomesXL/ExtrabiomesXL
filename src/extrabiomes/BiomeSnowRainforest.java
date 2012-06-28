package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

public class BiomeSnowRainforest extends BiomeTemporateRainforest {

	private static final String NAME = "Snowy Rainforest";
	private static final Extrabiome BIOME = Extrabiome.SNOWY_RAINFOREST;

	public BiomeSnowRainforest() {
		super(BIOME);

		setColor(0x338277);
		setBiomeName(NAME);
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 0.4F;
		maxHeight = 1.5F;
		setEnableSnow();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(17)
				.setGrassPerChunk(16).setMushroomsPerChunk(2)
				.setToadstoolsPerChunk(2);
	}
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(3) == 0) {
			if (treeGen == null)
				if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.FIR_TREE)) {
					treeGen = new WorldGenFirTree(false);
				} else {
					treeGen = new WorldGenNoOp();
				}

			return treeGen;
		}
		if (treeGen2 == null)
			if (FloraControl.INSTANCE.isEnabled(BIOME, Flora.FIR_TREE))
				treeGen2 = new WorldGenFirTree2(false);
			else
				treeGen2 = new WorldGenNoOp();

		return treeGen2;
	}

}
