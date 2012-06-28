package extrabiomes;

import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.ExtrabiomeDecorateSubscriber;
import extrabiomes.api.Flora;
import extrabiomes.api.TerrainGenBlock;

public class CustomDecorator extends BiomeDecorator {

	private static Map<Extrabiome, Set<ExtrabiomeDecorateSubscriber>> biomeDecorateSubscriptions = new EnumMap(
			Extrabiome.class);

	private static Set<ExtrabiomeDecorateSubscriber> getSubscriptionsForBiome(
			Extrabiome biome) {
		Set<ExtrabiomeDecorateSubscriber> subscriptions = biomeDecorateSubscriptions
				.get(biome);
		if (subscriptions == null) {
			subscriptions = new LinkedHashSet<ExtrabiomeDecorateSubscriber>();
			biomeDecorateSubscriptions.put(biome, subscriptions);
		}
		return subscriptions;
	}
	static void subscribe(Collection<Extrabiome> filter,
			ExtrabiomeDecorateSubscriber subscriber) {
		if (filter == null || subscriber == null)
			return;
		for (Extrabiome biome : filter) {
			Set<ExtrabiomeDecorateSubscriber> subscriptions = getSubscriptionsForBiome(biome);
			subscriptions.add(subscriber);
		}
	}
	private int deadGrassPerChunk = 0;
	private int deadGrassYPerChunk = 0;
	private int deadTallGrassPerChunk = 0;
	private int catTailPerChunk = 0;
	private int hydrangeaPerChunk = 0;
	private int brownGrassPerChunk = 0;
	private int brownGrassShortPerChunk = 0;
	private int orangeFlowerPerChunk = 0;
	private int whiteFlowerPerChunk = 0;
	private int purpleFlowerPerChunk = 0;
	private int autumnShrubsPerChunk = 0;
	private int toadstoolsPerChunk = 0;
	private int tinyCactiPerChunk = 0;
	private int rootsPerChunk = 0;
	private int leafPilePerChunk = 0;
	private int oasisPerChunk = 0;

	private int oasisPerChunk2 = 0;
	final private Extrabiome biome;

	private final Set<ExtrabiomeDecorateSubscriber> subscriptions;


	public CustomDecorator(BiomeGenBase par1BiomeGenBase, Extrabiome biome) {
		super(par1BiomeGenBase);
		this.biome = biome;
		subscriptions = getSubscriptionsForBiome(biome);
	}

	@Override
	protected void decorate() {
		super.decorate();

		for (int i = 0; i < oasisPerChunk2; i++) {
			final int x = chunk_X + randomGenerator.nextInt(16) + 8;
			final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
			final WorldGenerator wg = new WorldGenOasis(7, Block.grass.blockID);
			wg.generate(currentWorld, randomGenerator, x,
					currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}

		for (int i = 0; i < oasisPerChunk; i++) {
			final int x = chunk_X + randomGenerator.nextInt(16) + 8;
			final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
			final WorldGenerator wg = new WorldGenOasis(7, Block.grass.blockID);
			wg.generate(currentWorld, randomGenerator, x,
					currentWorld.getTopSolidOrLiquidBlock(x, z), z);
		}

		final BlockControl bc = BlockControl.INSTANCE;
		final FloraControl opt = FloraControl.INSTANCE;

		MetaBlock blockToGen = bc
				.getTerrainGenBlock(TerrainGenBlock.DEAD_GRASS);
		if (blockToGen != null && opt.isEnabled(biome, Flora.DEAD_GRASS))
			for (int i = 0; i < deadGrassPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenGrass(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.YELLOW_DEAD_GRASS);
		if (blockToGen != null && opt.isEnabled(biome, Flora.DEAD_YELLOW_GRASS))
			for (int i = 0; i < deadGrassYPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenGrass(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.BROWN_GRASS);
		if (blockToGen != null && opt.isEnabled(biome, Flora.BROWN_GRASS))
			for (int i = 0; i < brownGrassPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenGrass(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.SHORT_BROWN_GRASS);
		if (blockToGen != null && opt.isEnabled(biome, Flora.SHORT_BROWN_GRASS))
			for (int i = 0; i < brownGrassShortPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenGrass(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.DEAD_TALL_GRASS);
		if (blockToGen != null && opt.isEnabled(biome, Flora.DEAD_TALL_GRASS))
			for (int i = 0; i < deadTallGrassPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenGrass(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.HYDRANGEA);
		if (blockToGen != null && opt.isEnabled(biome, Flora.HYDRANGEA))
			for (int i = 0; i < hydrangeaPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCustomFlower(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.ORANGE_FLOWER);
		if (blockToGen != null && opt.isEnabled(biome, Flora.ORANGE_FLOWER))
			for (int i = 0; i < orangeFlowerPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCustomFlower(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.WHITE_FLOWER);
		if (blockToGen != null && opt.isEnabled(biome, Flora.WHITE_FLOWER))
			for (int i = 0; i < whiteFlowerPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCustomFlower(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.PURPLE_FLOWER);
		if (blockToGen != null && opt.isEnabled(biome, Flora.PURPLE_FLOWER))
			for (int i = 0; i < purpleFlowerPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCustomFlower(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.AUTUMN_SHRUB);
		if (blockToGen != null && opt.isEnabled(biome, Flora.AUTUMN_SHRUB))
			for (int i = 0; i < autumnShrubsPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCustomFlower(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.TOAD_STOOL);
		if (blockToGen != null && opt.isEnabled(biome, Flora.TOADSTOOL))
			for (int i = 0; i < toadstoolsPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCustomFlower(blockToGen);
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.LEAF_PILE);
		if (blockToGen != null && opt.isEnabled(biome, Flora.LEAF_PILE))
			for (int i = 0; i < leafPilePerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenLeafPile();
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.TINY_CACTUS);
		if (blockToGen != null && opt.isEnabled(biome, Flora.TINY_CACTUS))
			for (int i = 0; i < tinyCactiPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenTinyCactus();
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.ROOT);
		if (blockToGen != null && opt.isEnabled(biome, Flora.ROOT))
			for (int i = 0; i < rootsPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenRoot();
				wg.generate(currentWorld, randomGenerator, x, y, z);
			}

		blockToGen = bc.getTerrainGenBlock(TerrainGenBlock.CAT_TAIL);
		if (blockToGen != null && opt.isEnabled(biome, Flora.CATTAIL))
			for (int i = 0; i < catTailPerChunk; i++) {
				final int x = chunk_X + randomGenerator.nextInt(16) + 8;
				final int y = randomGenerator.nextInt(128);
				final int z = chunk_Z + randomGenerator.nextInt(16) + 8;
				final WorldGenerator wg = new WorldGenCatTail();
				wg.generate(currentWorld, randomGenerator, x, z, y);
			}

		for (ExtrabiomeDecorateSubscriber subscriber : subscriptions)
			subscriber.onDecorateExtrabiome(biome, currentWorld,
					randomGenerator, chunk_X, chunk_Z);

	}

	public int getAutumnShrubPerChunk() {
		return autumnShrubsPerChunk;
	}

	public int getBrownGrassPerChunk() {
		return brownGrassPerChunk;
	}

	public int getBrownGrassShortPerChunk() {
		return brownGrassShortPerChunk;
	}

	public int getCatTailPerChunk() {
		return catTailPerChunk;
	}

	public int getDeadGrassPerChunk() {
		return deadGrassPerChunk;
	}

	public int getDeadGrassYPerChunk() {
		return deadGrassYPerChunk;
	}

	public int getDeadTallGrassPerChunk() {
		return deadTallGrassPerChunk;
	}

	public int getFlowersPerChunk() {
		return flowersPerChunk;
	}

	public int getGrassPerChunk() {
		return grassPerChunk;
	}

	public int getHydrangeaPerChunk() {
		return hydrangeaPerChunk;
	}

	public int getLeafPilePerChunk() {
		return leafPilePerChunk;
	}

	public int getOasisPerChunk() {
		return oasisPerChunk;
	}

	public int getOasisPerChunk2() {
		return oasisPerChunk2;
	}

	public int getOrangeFlowerPerChunk() {
		return orangeFlowerPerChunk;
	}

	public int getPurpleFlowerPerChunk() {
		return purpleFlowerPerChunk;
	}

	public int getRootPerChunk() {
		return rootsPerChunk;
	}

	public int getTinyCactiPerChunk() {
		return tinyCactiPerChunk;
	}

	public int getToadstoolsPerChunk() {
		return toadstoolsPerChunk;
	}

	public int getTreesPerChunk() {
		return treesPerChunk;
	}

	public int getWhiteFlowerPerChunk() {
		return whiteFlowerPerChunk;
	}

	public CustomDecorator setAutumnShrubsPerChunk(int autumnShrubsPerChunk) {
		this.autumnShrubsPerChunk = autumnShrubsPerChunk;
		return this;
	}

	public CustomDecorator setBrownGrassPerChunk(int brownGrassPerChunk) {
		this.brownGrassPerChunk = brownGrassPerChunk;
		return this;
	}

	public CustomDecorator setBrownGrassShortPerChunk(
			int brownGrassShortPerChunk) {
		this.brownGrassShortPerChunk = brownGrassShortPerChunk;
		return this;
	}

	public CustomDecorator setCatTailPerChunk(int catTailPerChunk) {
		this.catTailPerChunk = catTailPerChunk;
		return this;
	}

	public CustomDecorator setClayPerChunk(int clayPerChunk) {
		this.clayPerChunk = clayPerChunk;
		return this;
	}

	public CustomDecorator setDeadBushPerChunk(int deadBushPerChunk) {
		this.deadBushPerChunk = deadBushPerChunk;
		return this;
	}

	public CustomDecorator setDeadGrassPerChunk(int deadGrassPerChunk) {
		this.deadGrassPerChunk = deadGrassPerChunk;
		return this;
	}

	public CustomDecorator setDeadGrassYPerChunk(int deadGrassYPerChunk) {
		this.deadGrassYPerChunk = deadGrassYPerChunk;
		return this;
	}

	public CustomDecorator setDeadTallGrassPerChunk(int deadTallGrassPerChunk) {
		this.deadTallGrassPerChunk = deadTallGrassPerChunk;
		return this;
	}

	public CustomDecorator setFlowersPerChunk(int flowersPerChunk) {
		this.flowersPerChunk = flowersPerChunk;
		return this;
	}

	public CustomDecorator setGrassPerChunk(int grassPerChunk) {
		this.grassPerChunk = grassPerChunk;
		return this;
	}

	public CustomDecorator setHydrangeaPerChunk(int hydrangeaPerChunk) {
		this.hydrangeaPerChunk = hydrangeaPerChunk;
		return this;
	}

	public CustomDecorator setLeafPilePerChunk(int leafPilePerChunk) {
		this.leafPilePerChunk = leafPilePerChunk;
		return this;
	}

	public CustomDecorator setMushroomsPerChunk(int mushroomsPerChunk) {
		this.mushroomsPerChunk = mushroomsPerChunk;
		return this;
	}

	public CustomDecorator setOasisPerChunk(int oasisPerChunk) {
		this.oasisPerChunk = oasisPerChunk;
		return this;
	}

	public CustomDecorator setOasisPerChunk2(int oasisPerChunk2) {
		this.oasisPerChunk2 = oasisPerChunk2;
		return this;
	}

	public CustomDecorator setOrangeFlowerPerChunk(int orangeFlowerPerChunk) {
		this.orangeFlowerPerChunk = orangeFlowerPerChunk;
		return this;
	}

	public CustomDecorator setPurpleFlowerPerChunk(int purpleFlowerPerChunk) {
		this.purpleFlowerPerChunk = purpleFlowerPerChunk;
		return this;
	}

	public CustomDecorator setReedsPerChunk(int reedsPerChunk) {
		this.reedsPerChunk = reedsPerChunk;
		return this;
	}

	public CustomDecorator setRootsPerChunk(int rootsPerChunk) {
		this.rootsPerChunk = rootsPerChunk;
		return this;
	}

	public CustomDecorator setSandPerChunk(int firstPassSandPerChunk,
			int secondPassSandPerChunk) {
		this.sandPerChunk2 = firstPassSandPerChunk;
		this.sandPerChunk = secondPassSandPerChunk;
		return this;
	}

	public CustomDecorator setTinyCactiPerChunk(int tinyCactiPerChunk) {
		this.tinyCactiPerChunk = tinyCactiPerChunk;
		return this;
	}

	public CustomDecorator setToadstoolsPerChunk(int toadstoolsPerChunk) {
		this.toadstoolsPerChunk = toadstoolsPerChunk;
		return this;
	}

	public CustomDecorator setToadStoolsPerChunk(int toadStoolsPerChunk) {
		this.toadstoolsPerChunk = toadStoolsPerChunk;
		return this;
	}

	public CustomDecorator setTreesPerChunk(int treesPerChunk) {
		this.treesPerChunk = treesPerChunk;
		return this;
	}

	public CustomDecorator setWaterLilliesPerChunk(int waterLilliesPerChunk) {
		this.waterlilyPerChunk = waterLilliesPerChunk;
		return this;
	}

	public CustomDecorator setWhiteFlowerPerChunk(int whiteFlowerPerChunk) {
		this.whiteFlowerPerChunk = whiteFlowerPerChunk;
		return this;
	}
	public CustomDecorator setCactiPerChunk(int cactiPerChunk) {
		this.cactiPerChunk = cactiPerChunk;
		return this;
	}

}
