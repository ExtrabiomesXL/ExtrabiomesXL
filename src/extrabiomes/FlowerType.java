package extrabiomes;

import java.util.HashMap;
import java.util.Map;

import extrabiomes.api.TerrainGenBlock;

enum FlowerType {

	AUTUMNSHRUB(0, "Autumn Shrub", TerrainGenBlock.AUTUMN_SHRUB), HYDRANGEA(1,
			"Hydrangea", TerrainGenBlock.HYDRANGEA), ORANGE(2, "Orange Flower",
			TerrainGenBlock.ORANGE_FLOWER), PURPLE(3, "Purple Flower",
			TerrainGenBlock.PURPLE_FLOWER), TINYCACTUS(4, "Tiny Cactus",
			TerrainGenBlock.TINY_CACTUS), ROOT(5, "Root", TerrainGenBlock.ROOT), TOADSTOOL(
			6, "Toad Stool", TerrainGenBlock.TOAD_STOOL), WHITE(7,
			"White Flower", TerrainGenBlock.WHITE_FLOWER);

	private static final Map<Integer, FlowerType> metadataLookup = new HashMap<Integer, FlowerType>();

	static {
		for (FlowerType f : FlowerType.values())
			metadataLookup.put(f.metadata, f);
	}

	static FlowerType fromMetadata(final int metadata) {
		return metadataLookup.get(metadata);
	}

	private final String displayName;
	private final int metadata;
	private final TerrainGenBlock aliasUsedInTerrainGen;

	private FlowerType(final int metadata, final String name,
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
