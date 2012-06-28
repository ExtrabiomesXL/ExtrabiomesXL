package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeWoodlands extends BiomeBase {

	private static final String NAME = "Woodlands";
	private static final Extrabiome BIOME = Extrabiome.WOODLANDS;

	public BiomeWoodlands() {
		super(BIOME);

		setColor(0x85B53E);
		setBiomeName(NAME);
		temperature = 2.0F;
		rainfall = 0.2F;
		minHeight = 0.2F;
		maxHeight = 0.4F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(8)
				.setGrassPerChunk(3).setLeafPilePerChunk(30);
	}

}
