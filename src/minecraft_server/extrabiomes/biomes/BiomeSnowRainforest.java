package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeSnowRainforest extends BiomeTemporateRainforest {

	public BiomeSnowRainforest(int id) {
		super(id);

		setColor(0x338277);
		setBiomeName("Snowy Rainforest");
		temperature = BiomeGenBase.taigaHills.temperature;
		rainfall = 1.3F;
		setEnableSnow();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(17)
				.mushroomsPerChunk(2).grassPerChunk(16).build();
	}

}
