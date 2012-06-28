package extrabiomes.biomes;

import java.util.Random;

import net.minecraft.src.BiomeDecorator;
import net.minecraft.src.Block;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.WorldGenerator;
import extrabiomes.api.ITreeFactory;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.terrain.CustomBiomeDecorator;
import extrabiomes.terrain.WorldGenNoOp;

public class BiomeAlpine extends ExtrabiomeGenBase {

	public BiomeAlpine(int id) {
		super(id);
		topBlock = (byte) Block.stone.blockID;
		fillerBlock = (byte) Block.stone.blockID;
		setColor(0x8DACC4);
		setEnableSnow();
		setBiomeName("Alpine");
		temperature = 0.0F;
		rainfall = 0.1F;
		minHeight = 1.3F;
		maxHeight = 2.1F;

		spawnableCreatureList
				.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
	}

	@Override
	protected BiomeDecorator createBiomeDecorator() {
		return new CustomBiomeDecorator.Builder(this).treesPerChunk(7)
				.flowersPerChunk(0).grassPerChunk(0).build();
	}

	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random random) {
		if (TerrainGenManager.enableFirGen)
			return TerrainGenManager.treeFactory.makeTreeGenerator(false,
					ITreeFactory.TreeType.FIR);
		return new WorldGenNoOp();
	}

}
