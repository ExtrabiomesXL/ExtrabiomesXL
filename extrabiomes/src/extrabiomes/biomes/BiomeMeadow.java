package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeMeadow extends ExtrabiomeGenBase {

	public BiomeMeadow(int id) {
		super(id);
		setBiomeName("Meadow");
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(0)
				.grassPerChunk(12).flowersPerChunk(9).build();
	}

}
