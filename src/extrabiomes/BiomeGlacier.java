package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;

public class BiomeGlacier extends BiomeBase {

	private static final String NAME = "Glacier";
	private static final Extrabiome BIOME = Extrabiome.GLACIER;

	public BiomeGlacier() {
		super(BIOME);
		spawnableCreatureList.clear();
		topBlock = (byte) Block.blockSnow.blockID;
		fillerBlock = (byte) Block.ice.blockID;
		setColor(0x77A696);
		setBiomeName(NAME);
		setEnableSnow();
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 1.4F;
		maxHeight = 2.1F;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME);
	}

}
