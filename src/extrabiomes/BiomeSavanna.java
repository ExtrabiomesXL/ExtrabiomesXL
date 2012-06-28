package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;

public class BiomeSavanna extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.SAVANNA;
	final WorldGenerator treeGen;

	public BiomeSavanna(int id) {
		super(id);

		setColor(0xBFA243);
		setBiomeName("Savanna");
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.1F;

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.ACACIA_TREE))
			treeGen = new WorldGenAcacia(false);
		else
			treeGen = new WorldGenNoOp();
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome).setTreesPerChunk(0)
				.setFlowersPerChunk(1).setGrassPerChunk(17)
				.setPurpleFlowerPerChunk(1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random) {
		return treeGen;
	}

}
