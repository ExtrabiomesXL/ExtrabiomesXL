package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeGreenHills extends BiomeGenBase {

	private static final  Extrabiome biome = Extrabiome.GREEN_HILLS;

	public BiomeGreenHills(int id) {
		super(id);
		
		setColor(0x68C474);
		setBiomeName("Green Hills");
		temperature = 0.7F;
		rainfall = 0.8F;
		minHeight = 0.6F;
		maxHeight = 1.2F;
		MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, Extrabiome.GREEN_HILLS).setTreesPerChunk(1)
				.setOrangeFlowerPerChunk(1).setWhiteFlowerPerChunk(1);
	}

	@Override
	public int getBiomeFoliageColor() {
		return (super.getBiomeFoliageColor() & 0xBFFF00) + 0x2B2B21;
	}

	@Override
	public int getBiomeGrassColor() {
		return (super.getBiomeGrassColor() & 0xBFFF00) + 0x2B2B21;
	}

}
