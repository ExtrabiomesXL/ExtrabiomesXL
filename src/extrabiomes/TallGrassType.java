package extrabiomes;

import java.util.HashMap;
import java.util.Map;

import extrabiomes.api.TerrainGenBlock;

enum TallGrassType {
	BROWN(0, "Brown Grass", TerrainGenBlock.BROWN_GRASS), SHORTBROWN(1,
			"Short Brown Grass", TerrainGenBlock.SHORT_BROWN_GRASS), DEAD(2,
			"Dead Grass", TerrainGenBlock.DEAD_GRASS), DEADTALL(3,
			"Tall Dead Grass", TerrainGenBlock.DEAD_TALL_GRASS), YELLOWDEAD(4,
			"Yellow Dead Grass", TerrainGenBlock.YELLOW_DEAD_GRASS);

	private static final Map<Integer, TallGrassType> metadataLookup = new HashMap<Integer, TallGrassType>();

	static {
		for (TallGrassType f : TallGrassType.values())
			metadataLookup.put(f.metadata, f);
	}

	static TallGrassType fromMetadata(final int metadata) {
		return metadataLookup.get(metadata);
	}

	private final String displayName;
	private final int metadata;
	private final TerrainGenBlock aliasUsedInTerrainGen;

	private TallGrassType(final int metadata, final String name,
			TerrainGenBlock aliasUsedInTerrainGen) {
		this.metadata = metadata;
		displayName = name;
		this.aliasUsedInTerrainGen = aliasUsedInTerrainGen;
	}

	TerrainGenBlock getAliasUsedInTerrainGen() {
		return aliasUsedInTerrainGen;
	}

	public String getDisplayName() {
		return displayName;
	}

	public int metadata() {
		return metadata;
	}
}
