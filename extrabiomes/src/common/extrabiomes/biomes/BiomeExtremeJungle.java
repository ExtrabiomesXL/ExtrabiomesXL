
package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenHugeTrees;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;

public class BiomeExtremeJungle extends ExtrabiomeGenBase {

	public BiomeExtremeJungle(int id) {
		super(id);
		setColor(0x2c4205);
		setBiomeName("Extreme Jungle");
		temperature = BiomeGenBase.jungle.temperature;
		rainfall = BiomeGenBase.jungle.rainfall;
		minHeight = 2.1F;
		maxHeight = 2.3F;

		spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class,
				2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(
				EntityChicken.class, 10, 4, 4));

	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(50)
				.grassPerChunk(25).flowersPerChunk(4).build();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);
		final WorldGenVines vineGen = new WorldGenVines();

		for (int i = 0; i < 50; ++i) {
			final int x1 = x + rand.nextInt(16) + 8;
			final int z1 = z + rand.nextInt(16) + 8;
			vineGen.generate(world, rand, x1, 64, z1);
		}
	}

	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random rand) {
		return rand.nextInt(4) == 0 ? new WorldGenTallGrass(
				Block.tallGrass.blockID, 2) : new WorldGenTallGrass(
				Block.tallGrass.blockID, 1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return rand.nextInt(10) == 0 ? worldGenBigTree : rand
				.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : rand
				.nextInt(3) == 0 ? new WorldGenHugeTrees(false,
				10 + rand.nextInt(20), 3, 3) : new WorldGenTrees(false,
				4 + rand.nextInt(7), 3, 3, true);
	}

}
