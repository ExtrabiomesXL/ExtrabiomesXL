package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenerator;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenPit;
import extrabiomes.terrain.WorldGenPit2;

public class BiomeMiniJungle extends ExtrabiomeGenBase {

	public BiomeMiniJungle(int id) {
		super(id);
		setColor(0x41D923);
		setBiomeName("Mini Jungle");
		temperature = BiomeGenBase.jungle.temperature;
		rainfall = BiomeGenBase.jungle.rainfall;
		minHeight = 0.2F;
		maxHeight = 0.6F;
		waterColorMultiplier = 0x24b01c;

		spawnableMonsterList
				.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class, 10,
				4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(15)
				.grassPerChunk(9).flowersPerChunk(5).reedsPerChunk(70)
				.clayPerChunk(3).mushroomsPerChunk(2).waterlilyPerChunk(12)
				.build();
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);

		if (rand.nextInt(1) == 0) {
			int x1 = x + rand.nextInt(16) + 8;
			int z1 = z + rand.nextInt(16) + 8;
			WorldGenPit2 worldgenpit2 = new WorldGenPit2();
			worldgenpit2.generate(world, rand, x1,
					world.getHeightValue(x1, z1) + 1, z1);
			worldgenpit2.generate(world, rand, x1,
					world.getHeightValue(x1, z1) + 1, z1);
			worldgenpit2.generate(world, rand, x1,
					world.getHeightValue(x1, z1) + 1, z1);
		}

		if (rand.nextInt(1) == 0) {
			int x1 = x + rand.nextInt(16) + 8;
			int y1 = z + rand.nextInt(16) + 8;
			WorldGenPit worldgenpit = new WorldGenPit();
			worldgenpit.generate(world, rand, x1,
					world.getHeightValue(x1, y1) + 1, y1);
		}
	}

	@Override
	public WorldGenerator func_48410_b(Random rand) {
		if (rand.nextInt(4) == 0)
			return new WorldGenTallGrass(Block.tallGrass.blockID, 2);

		return super.func_48410_b(rand);

	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		if (rand.nextInt(2) == 0)
			return worldGenSwamp;
		if (rand.nextInt(100) == 0)
			return worldGenTrees;
		return worldGenBigTree;
	}
}
