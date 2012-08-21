package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeGreenHills extends ExtrabiomeGenBase {

	public BiomeGreenHills(int id) {
		super(id);

		setColor(0x68C474);
		setBiomeName("Green Hills");
		temperature = BiomeGenBase.forest.temperature - 0.1F;
		rainfall = BiomeGenBase.forest.rainfall + 0.1F;
		minHeight = 0.6F;
		maxHeight = 1.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(1).build();
	}

}
