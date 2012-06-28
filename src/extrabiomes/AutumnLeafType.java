package extrabiomes;

import java.util.HashMap;
import java.util.Map;

import extrabiomes.api.TerrainGenBlock;

enum AutumnLeafType {
	BROWN(0,
			"Brown Autumn Leaves",
			TerrainGenBlock.BROWN_LEAVES,
			TerrainGenBlock.BROWN_SAPLING),
	ORANGE(1,
			"Orange Autumn Leaves",
			TerrainGenBlock.ORANGE_LEAVES,
			TerrainGenBlock.ORANGE_SAPLING),
	PURPLE(2,
			"Purple Autumn Leaves",
			TerrainGenBlock.PURPLE_LEAVES,
			TerrainGenBlock.PURPLE_SAPLING),
	YELLOW(3,
			"Yellow Autumn Leaves",
			TerrainGenBlock.YELLOW_LEAVES,
			TerrainGenBlock.YELLOW_SAPLING);

	private static final Map<Integer, AutumnLeafType> metadataLookup = new HashMap<Integer, AutumnLeafType>();

	static {
		for (AutumnLeafType f : AutumnLeafType.values())
			metadataLookup.put(f.metadata, f);
	}

	static AutumnLeafType fromMetadata(final int metadata) {
		return metadataLookup.get(metadata);
	}

	private final String displayName;
	private final int metadata;
	private final TerrainGenBlock aliasUsedInTerrainGen;
	private final TerrainGenBlock saplingToDrop;

	private AutumnLeafType(final int metadata, final String name,
			TerrainGenBlock aliasUsedInTerrainGen, TerrainGenBlock saplingToDrop) {
		this.metadata = metadata;
		displayName = name;
		this.aliasUsedInTerrainGen = aliasUsedInTerrainGen;
		this.saplingToDrop = saplingToDrop;
	}

	TerrainGenBlock getAliasUsedInTerrainGen() {
		return aliasUsedInTerrainGen;
	}

	String getDisplayName() {
		return displayName;
	}

	TerrainGenBlock getSaplingToDrop() {
		return saplingToDrop;
	}

	int metadata() {
		return metadata;
	}
}
