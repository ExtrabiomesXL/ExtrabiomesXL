package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenJungle;
import net.minecraft.src.MapGenVillage;

public class BiomeExtremeJungle extends BiomeGenJungle {

	private static final Extrabiome biome = Extrabiome.EXTREME_JUNGLE;
	
	public BiomeExtremeJungle(int id) {
		super(id);
		setColor(0x2c4205);
		setBiomeName("Extreme Jungle");
		func_4124_a(0x537b09);
		temperature = 1.2F;
		rainfall = 0.9F;
		minHeight = 2.1F;
		maxHeight = 2.3F;

		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);

	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome);
	}

}
