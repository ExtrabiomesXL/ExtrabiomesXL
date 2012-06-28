package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.ColorizerFoliage;
import net.minecraft.src.ColorizerGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenCustomSwamp;

public class BiomeGreenSwamp extends ExtrabiomeGenBase {

	private static WorldGenerator genCustomSwampTree = null;

	public BiomeGreenSwamp(int id) {
		super(id);
		setColor(0x68C474);
		setBiomeName("Green Swamplands");
		temperature = BiomeGenBase.swampland.temperature - 0.1F;
		rainfall = BiomeGenBase.swampland.rainfall;
		minHeight = -0.2F;
		maxHeight = 0.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(4)
				.flowersPerChunk(0).deadBushPerChunk(0).mushroomsPerChunk(8)
				.reedsPerChunk(10).clayPerChunk(1).waterlilyPerChunk(4)
				.sandPerChunk(0, 0).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(5) == 0)
			return worldGenSwamp;

		if (genCustomSwampTree == null)
			genCustomSwampTree = new WorldGenCustomSwamp();

		return genCustomSwampTree;
	}

	@Override
	public int getBiomeFoliageColor() {
		return ColorizerFoliage.getFoliageColor(0.7, 0.8);
	}

	@Override
	public int getBiomeGrassColor() {
		return ColorizerGrass.getGrassColor(0.7, 0.8);
	}

}
