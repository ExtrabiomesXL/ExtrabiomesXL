package extrabiomes;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;

public class BiomeWoodlands extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.WOODLANDS;

	public BiomeWoodlands(int id) {
		super(id);

		setColor(0x85B53E);
		setBiomeName("Woodlands");
		temperature = 2.0F;
		rainfall = 0.2F;
		minHeight = 0.2F;
		maxHeight = 0.4F;

		spawnableCreatureList.add(new SpawnListEntry(
				net.minecraft.src.EntityWolf.class, 5, 4, 4));
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome).setTreesPerChunk(8)
				.setGrassPerChunk(3).setLeafPilePerChunk(30);
	}

}
