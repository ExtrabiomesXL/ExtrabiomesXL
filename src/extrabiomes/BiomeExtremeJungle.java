package extrabiomes;

import java.util.Random;

import extrabiomes.api.Extrabiome;
import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.MapGenVillage;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.World;
import net.minecraft.src.WorldGenHugeTrees;
import net.minecraft.src.WorldGenShrub;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraft.src.WorldGenTrees;
import net.minecraft.src.WorldGenVines;
import net.minecraft.src.WorldGenerator;

public class BiomeExtremeJungle extends BiomeBase {

	private static final String NAME = "Extreme Jungle";
	private static final Extrabiome BIOME = Extrabiome.EXTREME_JUNGLE;

	public BiomeExtremeJungle() {
		super(BIOME);
		setColor(0x2c4205);
		setBiomeName(NAME);
		func_4124_a(0x537b09);
		temperature = 1.2F;
		rainfall = 0.9F;
		minHeight = 2.1F;
		maxHeight = 2.3F;

		this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2,
				1, 1));
		this.spawnableCreatureList.add(new SpawnListEntry(EntityChicken.class,
				10, 4, 4));

	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomDecorator(this, BIOME).setTreesPerChunk(50)
				.setGrassPerChunk(25).setFlowersPerChunk(4);
	}

	@Override
	public void decorate(World world, Random rand, int x, int z) {
		super.decorate(world, rand, x, z);
		WorldGenVines var5 = new WorldGenVines();

		for (int i = 0; i < 50; ++i) {
			int var7 = x + rand.nextInt(16) + 8;
			byte var8 = 64;
			int var9 = z + rand.nextInt(16) + 8;
			var5.generate(world, rand, var7, var8, var9);
		}
	}

	@Override
	public WorldGenerator func_48410_b(Random rand) {
		return rand.nextInt(4) == 0 ? new WorldGenTallGrass(
				Block.tallGrass.blockID, 2) : new WorldGenTallGrass(
				Block.tallGrass.blockID, 1);
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random rand) {
		return (rand.nextInt(10) == 0 ? this.worldGenBigTree
				: (rand.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (rand
						.nextInt(3) == 0 ? new WorldGenHugeTrees(false,
						10 + rand.nextInt(20), 3, 3) : new WorldGenTrees(false,
						4 + rand.nextInt(7), 3, 3, true))));
	}

}
