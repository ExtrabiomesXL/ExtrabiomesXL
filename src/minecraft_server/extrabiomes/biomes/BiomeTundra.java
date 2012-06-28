package extrabiomes.biomes;

import net.minecraft.src.BiomeDecorator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeTundra extends ExtrabiomeGenBase {

	public BiomeTundra(int id) {
		super(id);

		setColor(0x305A85);
		setBiomeName("Tundra");
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.2F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).flowersPerChunk(0)
				.grassPerChunk(0).sandPerChunk(0, 0).build();
	}

}
