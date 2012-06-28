package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;

public class BiomeGlacier extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.GLACIER;

	public BiomeGlacier(int id) {
		super(id);
		spawnableCreatureList.clear();
		topBlock = (byte) Block.blockSnow.blockID;
		fillerBlock = (byte) Block.ice.blockID;
		setColor(0x77A696);
		setBiomeName("Glacier");
		setEnableSnow();
		temperature = 0.0F;
		rainfall = 0.0F;
		minHeight = 1.4F;
		maxHeight = 2.1F;

		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome);
	}

}
