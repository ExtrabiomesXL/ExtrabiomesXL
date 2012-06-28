package extrabiomes;

import java.lang.reflect.Field;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.MapGenVillage;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.TerrainGenBlock;

public class BiomeWasteland extends BiomeGenBase {

	private static final Extrabiome biome = Extrabiome.WASTELAND;

	public BiomeWasteland(int par1) {
		super(par1);

		setColor(0x9E7C41);
		setBiomeName("Wasteland");
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.0F;
		maxHeight = 0.0F;
		waterColorMultiplier = 0xF08000;

		spawnableCreatureList.clear();

		MetaBlock crackedSand = BlockControl.INSTANCE
				.getTerrainGenBlock(TerrainGenBlock.CRACKED_SAND);
		topBlock = (byte) crackedSand.blockId();
		fillerBlock = (byte) crackedSand.blockId();
		setDisabledRain();
		if (Options.INSTANCE.canSpawnVillage(biome))
			MapGenVillage.villageSpawnBiomes.add(this);
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, biome)
				.setDeadBushPerChunk(3).setDeadGrassPerChunk(9)
				.setDeadGrassYPerChunk(9).setDeadTallGrassPerChunk(7);
	}

	void setDisabledRain() {
		Field enabledRainField;
		try {
			enabledRainField = BiomeGenBase.class
					.getDeclaredField("field_27079_v"); // enabled rain
			enabledRainField.setAccessible(true);
			enabledRainField.setBoolean(this, false);
		} catch (Throwable e) {
			// Do nothing... (This is NOT critical)
		}
	}

}
