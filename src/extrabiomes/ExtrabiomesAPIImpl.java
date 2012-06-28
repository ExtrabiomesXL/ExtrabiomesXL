package extrabiomes;

import java.util.Collection;

import net.minecraft.src.BiomeGenBase;
import extrabiomes.api.Extrabiome;
import extrabiomes.api.ExtrabiomeDecorateSubscriber;
import extrabiomes.api.ExtrabiomesAPI;
import extrabiomes.api.TerrainGenBlock;

enum ExtrabiomesAPIImpl implements ExtrabiomesAPI {
	INSTANCE;

	@Override
	public BiomeGenBase getBiome(Extrabiome biome) {
		final int id = getBiomeId(biome);
		if (id == 0)
			return null;

		return BiomeGenBase.biomeList[id];
	}

	@Override
	public int getBiomeId(Extrabiome biome) {
		if (biome == null)
			return 0;
		return Options.INSTANCE.getId(biome);
	}

	@Override
	public extrabiomes.api.MetaBlock getMetaBlock(TerrainGenBlock block) {
		if (block == null)
			return null;
		return BlockControl.INSTANCE.getTerrainGenBlock(block);
	}

	@Override
	public void registerLeaves(int blockId) {
		BlockControl.INSTANCE.registerLeaves(blockId);
	}

	@Override
	public void registerWood(int blockId) {
		BlockControl.INSTANCE.registerWood(blockId);
	}

	@Override
	public void subscribeExtrabiomeDecorate(
			ExtrabiomeDecorateSubscriber subscriber,
			Collection<Extrabiome> filter) {
		CustomDecorator.subscribe(filter, subscriber);

	}

	@Override
	public boolean isLeaves(int blockId) {
		return BlockControl.INSTANCE.isLeaves(blockId);
	}

	@Override
	public boolean isWood(int blockId) {
		return BlockControl.INSTANCE.isWood(blockId);
	}
}
