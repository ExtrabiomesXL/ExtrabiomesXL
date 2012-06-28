package extrabiomes;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.Flora;

public class BiomeSnowRainforest extends BiomeTemporateRainforest {

	public BiomeSnowRainforest(int id) {
		super(id);

		setColor(0x338277);
		setBiomeName("Snowy Rainforest");
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 0.4F;
		maxHeight = 1.5F;
		setEnableSnow();
		biome = Extrabiome.SNOWY_RAINFOREST;

		if (FloraControl.INSTANCE.isEnabled(biome, Flora.FIR_TREE)) {
			treeGen = new WorldGenFirTree(false);
			treeGen2 = new WorldGenFirTree2(false);
		} else {
			treeGen = new WorldGenNoOp();
			treeGen2 = treeGen;
		}
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.SNOWY_RAINFOREST).setTreesPerChunk(17)
				.setGrassPerChunk(16).setMushroomsPerChunk(2)
				.setToadstoolsPerChunk(2);
	}

}
