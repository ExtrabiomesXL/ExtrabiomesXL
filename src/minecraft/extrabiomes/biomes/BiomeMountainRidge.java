package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeMountainRidge extends ExtrabiomeGenBase {

	public BiomeMountainRidge(int id) {
		super(id);
		setColor(0xC4722F);
		setBiomeName("Mountain Ridge");
		temperature = BiomeGenBase.desert.temperature;
		rainfall = BiomeGenBase.desert.rainfall;
		minHeight = 1.7F;
		maxHeight = 1.7F;
		disableRain();
		spawnableCreatureList.clear();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(0).build();
	}
}
