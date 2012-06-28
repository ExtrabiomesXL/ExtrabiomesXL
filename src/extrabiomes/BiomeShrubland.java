package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;

public class BiomeShrubland extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.SHRUBLAND;

	public BiomeShrubland(int par1) {
		super(par1);

		setColor(0x51B57D);
		setBiomeName("Shrubland");
		temperature = 0.4F;
		rainfall = 0.6F;
		minHeight = 0.1F;
		maxHeight = 0.3F;
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.SHRUBLAND).setTreesPerChunk(0)
				.setFlowersPerChunk(3).setGrassPerChunk(0);
	}

}
