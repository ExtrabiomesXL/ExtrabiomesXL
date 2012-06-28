package extrabiomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.Extrabiome;

public class BiomeGreenSwamp extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.GREEN_SWAMP;

	private static WorldGenerator genCustomSwampTree;

	BiomeGreenSwamp(int par1) {
		super(par1);
		setColor(0x68C474);
		setBiomeName("Green Swamplands");
		temperature = 0.7F;
		rainfall = 0.8F;
		minHeight = -0.2F;
		maxHeight = 0.1F;

		genCustomSwampTree = new WorldGenCustomSwamp();
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.GREEN_SWAMP).setTreesPerChunk(4)
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

		return genCustomSwampTree;
	}

}
