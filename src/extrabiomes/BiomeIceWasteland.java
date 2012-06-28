package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;

public class BiomeIceWasteland extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.ICE_WASTELAND;

	public BiomeIceWasteland(int par1) {
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte) Block.blockSnow.blockID;
		fillerBlock = (byte) Block.blockSnow.blockID;
		setEnableSnow();
		setColor(0x7DA0B5);
		setBiomeName("Ice Wasteland");
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 0.3F;
		maxHeight = 0.4F;
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome).setTreesPerChunk(0);
	}

}
