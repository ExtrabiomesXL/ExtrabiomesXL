package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;

public class BiomeGreenSwamp extends BiomeBase {

	private static final String NAME = "Green Swamplands";
	private static final Extrabiome BIOME = Extrabiome.GREEN_SWAMP;

	private static WorldGenerator genCustomSwampTree = null;

	public BiomeGreenSwamp() {
		super(BIOME);
		setColor(0x68C474);
		setBiomeName(NAME);
		temperature = 0.7F;
		rainfall = 0.8F;
		minHeight = -0.2F;
		maxHeight = 0.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(4)
				.setFlowersPerChunk(0).setDeadBushPerChunk(1)
				.setMushroomsPerChunk(8).setReedsPerChunk(10)
				.setClayPerChunk(1).setWaterLilliesPerChunk(4)
				.setCatTailPerChunk(999).setHydrangeaPerChunk(1)
				.setSandPerChunk(0, 0).setRootsPerChunk(15)
				.setLeafPilePerChunk(10);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(5) == 0)
			return worldGenSwamp;

		if (genCustomSwampTree == null)
			genCustomSwampTree = new WorldGenCustomSwamp();
		
		return genCustomSwampTree;
	}

}
