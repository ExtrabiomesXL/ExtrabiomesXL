package extrabiomes.lib;

import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class GenesisChunkProvider extends ChunkProviderGenerate {
	private final BiomeGenBase	_biome;
	private final World			_world;

	public GenesisChunkProvider(World world, BiomeGenBase biome) {
		super(world, world.getSeed(), false);
		_world = world;
		_biome = biome;
	}

	public IChunkLoader getCurrentChunkLoader() {
		final ChunkProviderServer parent = (ChunkProviderServer) _world.getChunkProvider();
		return parent.currentChunkLoader;
	}

	public void setCurrentChunkLoader(IChunkLoader loader) {
		final ChunkProviderServer parent = (ChunkProviderServer) _world.getChunkProvider();
		parent.currentChunkLoader = loader;
	}

	public void unloadChunksIfNotNearSpawn(int chunkX, int chunkZ) {
		final ChunkProviderServer parent = (ChunkProviderServer) _world.getChunkProvider();
		parent.unloadChunksIfNotNearSpawn(chunkX, chunkZ);
	}
/*
	@Override
	public Chunk provideChunk(int i, int j) {
		//LogHelper.info("Genesis - provide chunk @ " + i + "," + j + ", biomeId = " + _biome.biomeID);

		//Chunk chunk = super.provideChunk(i, j); // this sets the random seed among other things...

		//chunk.getBlockStorageArray()
		byte[] terrain = new byte[32768];
		this.generateTerrain(i, j, terrain);

		BiomeGenBase[] biomeArray = new BiomeGenBase[256];
		Arrays.fill(biomeArray, _biome);
		this.replaceBlocksForBiome(i, j, terrain, biomeArray);

		Chunk chunk = new Chunk(this._world, terrain, i, j);
		byte[] biomes = chunk.getBiomeArray();

		//for (int k = 0; k < biomes.length; ++k) {
		//	biomes[k] = (byte) _biome.biomeID;
		//}
		//chunk.setBiomeArray(biomes);

		//chunk.generateSkylightMap();
		
		return chunk;
	}

	@Override
	public void populate(IChunkProvider par1IChunkProvider, int i, int j) {
		//System.out.println("Genesis - populate " + i + "," + j);

		BlockSand.fallInstantly = true;
		int k = i * 16;
		int l = j * 16;

		Random rand = new Random(this._world.getSeed());
		long i1 = rand.nextLong() / 2L * 2L + 1L;
		long j1 = rand.nextLong() / 2L * 2L + 1L;
		rand.setSeed(i * i1 + j * j1 ^ this._world.getSeed());
		boolean flag = false;

		MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, _world, rand, i, j, flag));

		int k1;
		int l1;
		int i2;

		if (_biome != BiomeGenBase.desert && _biome != BiomeGenBase.desertHills && !flag && rand.nextInt(4) == 0 && TerrainGen.populate(this, _world, rand, i, j, flag, LAKE)) {
			k1 = k + rand.nextInt(16) + 8;
			l1 = rand.nextInt(128);
			i2 = l + rand.nextInt(16) + 8;
			(new WorldGenLakes(Blocks.water)).generate(this._world, rand, k1, l1, i2);
		}

		if (TerrainGen.populate(this, _world, rand, i, j, flag, LAVA) && !flag && rand.nextInt(8) == 0) {
			k1 = k + rand.nextInt(16) + 8;
			l1 = rand.nextInt(rand.nextInt(120) + 8);
			i2 = l + rand.nextInt(16) + 8;

			if (l1 < 63 || rand.nextInt(10) == 0) {
				(new WorldGenLakes(Blocks.lava)).generate(this._world, rand, k1, l1, i2);
			}
		}

		boolean doGen = TerrainGen.populate(this, _world, rand, i, j, flag, DUNGEON);
		for (k1 = 0; doGen && k1 < 8; ++k1) {
			l1 = k + rand.nextInt(16) + 8;
			i2 = rand.nextInt(128);
			int j2 = l + rand.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(this._world, rand, l1, i2, j2);
		}

		_biome.decorate(this._world, rand, k, l);
		SpawnerAnimals.performWorldGenSpawning(this._world, _biome, k + 8, l + 8, 16, 16, rand);
		k += 8;
		l += 8;

		doGen = TerrainGen.populate(this, _world, rand, i, j, flag, ICE);
		for (k1 = 0; doGen && k1 < 16; ++k1) {
			for (l1 = 0; l1 < 16; ++l1) {
				i2 = this._world.getPrecipitationHeight(k + k1, l + l1);

				if (this._world.isBlockFreezable(k1 + k, i2 - 1, l1 + l)) {
					this._world.setBlock(k1 + k, i2 - 1, l1 + l, Blocks.ice, 0, 2);
				}

				if (this._world.canSnowAt(k1 + k, i2, l1 + l)) {
					this._world.setBlock(k1 + k, i2, l1 + l, Blocks.snow, 0, 2);
				}
			}
		}

		//MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, _world, rand, i, j, flag));

		BlockSand.fallInstantly = false;
	}
*/
}
