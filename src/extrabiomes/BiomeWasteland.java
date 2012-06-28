package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.TerrainGenBlock;

public class BiomeWasteland extends BiomeBase {

	private static final String NAME = "Wasteland";
	private static final Extrabiome BIOME = Extrabiome.WASTELAND;

	public BiomeWasteland() {
		super(BIOME);

		setColor(0x9E7C41);
		setBiomeName(NAME);
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.0F;
		waterColorMultiplier = 0xF08000;

		spawnableCreatureList.clear();

		disableRain();
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setDeadBushPerChunk(3)
				.setDeadGrassPerChunk(9).setDeadGrassYPerChunk(9)
				.setDeadTallGrassPerChunk(7);
	}

}
