package extrabiomes;

import java.util.HashMap;
import java.util.Map;

import extrabiomes.api.TerrainGenBlock;

enum SaplingType {

	BROWN(0, "Brown Autumn Sapling", TerrainGenBlock.BROWN_SAPLING), ORANGE(1,
			"Orange Autumn Sapling", TerrainGenBlock.ORANGE_SAPLING), PURPLE(2,
			"Purple Autumn Sapling", TerrainGenBlock.PURPLE_SAPLING), YELLOW(3,
			"Yellow Autumn Sapling", TerrainGenBlock.YELLOW_SAPLING), FIR(4,
			"Fir Sapling", TerrainGenBlock.FIR_SAPLING), REDWOOD(5,
			"Redwood Sapling", TerrainGenBlock.REDWOOD_SAPLING), ACACIA(6,
			"Acacia Sapling", TerrainGenBlock.ACACIA_SAPLING);

	private static final Map<Integer, SaplingType> metadataLookup = new HashMap<Integer, SaplingType>();

	static {
		for (SaplingType f : SaplingType.values())
			metadataLookup.put(f.metadata, f);
	}

	static SaplingType fromMetadata(final int metadata) {
		return metadataLookup.get(metadata);
	}

	private final String displayName;
	private final int metadata;
	private final TerrainGenBlock aliasUsedInTerrainGen;

	private SaplingType(final int metadata, final String name,
			TerrainGenBlock aliasUsedInTerrainGen) {
		this.metadata = metadata;
		displayName = name;
		this.aliasUsedInTerrainGen = aliasUsedInTerrainGen;
	}

	TerrainGenBlock getAliasUsedInTerrainGen() {
		return aliasUsedInTerrainGen;
	}

	String getDisplayName() {
		return displayName;
	}

	int metadata() {
		return metadata;
	}

}
