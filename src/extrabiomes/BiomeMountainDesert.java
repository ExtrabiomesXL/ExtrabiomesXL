package extrabiomes;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.BiomeGenDesert;
import net.minecraft.src.Block;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenDesertWells;
import extrabiomes.api.Extrabiome;

public class BiomeMountainDesert extends BiomeBase {

	private static final String NAME = "Mountainous Desert";
	private static final Extrabiome BIOME = Extrabiome.MOUNTAIN_DESERT;

	public BiomeMountainDesert() {
		super(BIOME);
		setProperties();
		spawnableCreatureList.clear();
		disableRain();
	}

	private void setProperties() {
		setColor(0xFA9418);
		setBiomeName(NAME);
		temperature = 2.0F;
		rainfall = 0.0F;
		minHeight = 0.4F;
		maxHeight = 1.4F;
		topBlock = (byte) Block.sand.blockID;
		fillerBlock = (byte) Block.sand.blockID;
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(0).setDeadBushPerChunk(2).setReedsPerChunk(50).setCactiPerChunk(10);
	}

	@Override
	public void decorate(World world, Random random, int x, int z) {
		super.decorate(world, random, x, z);

        if (random.nextInt(1000) == 0)
        {
            int x1 = x + random.nextInt(16) + 8;
            int z1 = z + random.nextInt(16) + 8;
            WorldGenDesertWells wells = new WorldGenDesertWells();
            wells.generate(world, random, x1, world.getHeightValue(x1, z1) + 1, z1);
        }

	}

}
