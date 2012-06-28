package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenBigTree;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;
import extrabiomes.api.TerrainGenBlock;

class BiomeAutumnWoods extends BiomeGenBase {

	private static final String DISPLAY_NAME = "Autumn Woods";
	private static final int COLOR = 0xF29C11;
	private static final float MAX_HEIGHT = 0.8F;
	private static final float MIN_HEIGHT = 0.2F;
	private static final float RAINFALL = 0.1F;
	private static final float TEMPERATURE = 2.0F;
	private static final int BIG_TREE_RARITY = 10;
	private static final int COLOR_RARITY = 3;
	private static final Extrabiome biome = Extrabiome.AUTUMN_WOODS;

	static private boolean shouldChooseColor(final Random rand) {
		return rand.nextInt(COLOR_RARITY) == 0;
	}

	static private boolean shouldGrowBigTree(final Random rand) {
		return rand.nextInt(BIG_TREE_RARITY) == 0;
	}

	public BiomeAutumnWoods(int id) {
		super(id);
		setProperties();
		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 5, 4, 4));
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.AUTUMN_WOODS).setTreesPerChunk(9)
				.setGrassPerChunk(6).setMushroomsPerChunk(3)
				.setToadStoolsPerChunk(2).setAutumnShrubsPerChunk(2);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(final Random rand) {

		if (!FloraControl.INSTANCE.isEnabled(biome, Flora.AUTUMN_TREE))
			return new WorldGenNoOp();

		TerrainGenBlock leaf = null;
		if (shouldChooseColor(rand))
			leaf = TerrainGenBlock.BROWN_LEAVES;
		else if (shouldChooseColor(rand))
			leaf = TerrainGenBlock.ORANGE_LEAVES;
		else if (shouldChooseColor(rand))
			leaf = TerrainGenBlock.PURPLE_LEAVES;
		else if (shouldChooseColor(rand))
			leaf = TerrainGenBlock.YELLOW_LEAVES;

		final TerrainGenBlock wood = TerrainGenBlock.AUTUMN_WOOD;

		final boolean bigTree = shouldGrowBigTree(rand);

		if (leaf != null)
			return bigTree ? new WorldGenBigAutumnTree(false, leaf, wood)
					: new WorldGenAutumnTree(false, leaf, wood);

		return bigTree ? new WorldGenBigTree(false) : new WorldGenTrees(false);
	}

	private void setProperties() {
		setColor(COLOR);
		setBiomeName(DISPLAY_NAME);
		temperature = TEMPERATURE;
		rainfall = RAINFALL;
		minHeight = MIN_HEIGHT;
		maxHeight = MAX_HEIGHT;
	}

}
