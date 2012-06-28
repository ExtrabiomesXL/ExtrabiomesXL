package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;

public class BiomeAutumnWoods extends ExtrabiomeGenBase {

	static private boolean shouldChooseColor(final Random rand) {
		return rand.nextInt(3) == 0;
	}

	static private boolean shouldGrowBigTree(final Random rand) {
		return rand.nextInt(10) == 0;
	}

	public BiomeAutumnWoods(int id) {
		super(id);
		setColor(0xF29C11);
		setBiomeName("Autumn Woods");
		temperature = BiomeGenBase.forest.temperature;
		rainfall = BiomeGenBase.forest.rainfall;
		minHeight = 0.2F;
		maxHeight = 0.8F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(9)
				.grassPerChunk(6).mushroomsPerChunk(3).build();
	}

	@Override
	public int getBiomeFoliageColor() {
		return ColorizerFoliage.getFoliageColor(1.0F, 0.1F);
	}

	@Override
	public int getBiomeGrassColor() {
		return ColorizerGrass.getGrassColor(1.0F, 0.1F);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(final Random rand) {

		if (!TerrainGenManager.enableAutumnTreeGen)
			return new WorldGenNoOp();

		ITreeFactory.TreeType tree = null;
		final boolean bigTree = shouldGrowBigTree(rand);

		if (shouldChooseColor(rand))
			tree = bigTree ? ITreeFactory.TreeType.BROWN_AUTUMN_BIG
					: ITreeFactory.TreeType.BROWN_AUTUMN;
		else if (shouldChooseColor(rand))
			tree = bigTree ? ITreeFactory.TreeType.ORANGE_AUTUMN_BIG
					: ITreeFactory.TreeType.ORANGE_AUTUMN;
		else if (shouldChooseColor(rand))
			tree = bigTree ? ITreeFactory.TreeType.PURPLE_AUTUMN_BIG
					: ITreeFactory.TreeType.PURPLE_AUTUMN;
		else if (shouldChooseColor(rand))
			tree = bigTree ? ITreeFactory.TreeType.YELLOW_AUTUMN_BIG
					: ITreeFactory.TreeType.YELLOW_AUTUMN;

		if (tree != null) {
			return TerrainGenManager.treeFactory.makeTreeGenerator(false, tree);
		}

		return bigTree ? new WorldGenBigTree(false) : new WorldGenTrees(false);
	}

}
