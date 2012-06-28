package extrabiomes;

import java.util.HashMap;
import java.util.Map;

import extrabiomes.api.TerrainGenBlock;

enum GreenLeafType {
	FIR(0,
			"Fir Leaves",
			TerrainGenBlock.FIR_LEAVES,
			TerrainGenBlock.FIR_SAPLING),
	REDWOOD(1,
			"Redwood Leaves",
			TerrainGenBlock.REDWOOD_LEAVES,
			TerrainGenBlock.REDWOOD_SAPLING),
	ACACIA(2,
			"Acacia Leaves",
			TerrainGenBlock.ACACIA_LEAVES,
			TerrainGenBlock.ACACIA_SAPLING);

	private static final Map<Integer, GreenLeafType> metadataLookup = new HashMap<Integer, GreenLeafType>();

	static {
		for (GreenLeafType f : GreenLeafType.values())
			metadataLookup.put(f.metadata, f);
	}

	static GreenLeafType fromMetadata(final int metadata) {
		return metadataLookup.get(metadata);
	}

	private final int metadata;
	private final String displayName;
	private final TerrainGenBlock aliasUsedInTerrainGen;
	private final TerrainGenBlock saplingToDrop;

	private GreenLeafType(final int metadata, final String name,
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
