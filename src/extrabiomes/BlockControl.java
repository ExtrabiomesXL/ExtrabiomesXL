package extrabiomes;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.minecraft.src.Block;
import extrabiomes.api.ExtrabiomesAPIInitializer;
import extrabiomes.api.TerrainGenBlock;

public enum BlockControl {
	INSTANCE;

	private static boolean initialized = false;

	private static final Map<Integer, IFuel> blockFuel = new HashMap<Integer, IFuel>();
	private static ArrayList<Integer> leavesBlockIds = new ArrayList();
	private static ArrayList<Integer> woodBlockIds = new ArrayList();
	private static Map<TerrainGenBlock, MetaBlock> metaBlocksForTerrainGen = new EnumMap(
			TerrainGenBlock.class);

	static void setClassicModeBlocks() {
		Log.write("Initializing classic mode blocks.");
		for (TerrainGenBlock i : TerrainGenBlock.values()) {
			MetaBlock block = VanillaMetaBlockFactory.getMetaBlock(i);
			if (block != null)
				setTerrainGenBlock(i, block);
		}
		Log.write("Classic mode block initialization complete.");
	}

	static void setTerrainGenBlock(TerrainGenBlock blocktype, MetaBlock metadata) {
		metaBlocksForTerrainGen.put(blocktype, metadata);
	}

	void doAddOnsBlockSubstitutions() {
		final Set<AddOn> addOnCollection = new LinkedHashSet<AddOn>(
				AddOnControl.INSTANCE.getAddOns());
		for (AddOn addOn : addOnCollection) {
			ExtrabiomesAPIInitializer initializer = addOn.getInitializer();
			if (initializer == null)
				continue;

			Map<TerrainGenBlock, extrabiomes.api.MetaBlock> substitutionsFromAddOn = initializer
					.getMetaBlockSubstitutions(ExtrabiomesAPIImpl.INSTANCE);

			if (substitutionsFromAddOn == null)
				continue;
			Log.write("Performing block substitutions for API client mod:" + addOn.getName());
			final Map<TerrainGenBlock, MetaBlock> substitutions = new EnumMap<TerrainGenBlock, MetaBlock>(
					TerrainGenBlock.class);
			for (TerrainGenBlock block:TerrainGenBlock.values()){
				extrabiomes.api.MetaBlock apiBlock = substitutionsFromAddOn.get(block);
				if (apiBlock != null)
					substitutions.put(block, new MetaBlock(apiBlock.blockId(),apiBlock.metadata()));
			}
			substitutionsFromAddOn = null;
			for (TerrainGenBlock block:substitutions.keySet())
				Log.write(block.toString());
			performSubstitutions(substitutions);
			Log.write("Substitutions complete");
		}
	}

	int getFuelValue(int id, int damage) {
		final IFuel fuel = blockFuel.get(id);
		if (fuel != null)
			return fuel.getFuelValue(damage);
		return 0;
	}

	MetaBlock getTerrainGenBlock(TerrainGenBlock blocktype) {
		return metaBlocksForTerrainGen.get(blocktype);
	}

	void initialize() {
		if (initialized)
			return;
		initialized = true;

		initializeBaseBlocks(); // Initialize blocks based on user config

		registerLeaves(Block.leaves.blockID);
		registerWood(Block.wood.blockID);
	}

	private void initializeBaseBlocks() {
		Options opt = Options.INSTANCE;
		if (opt.isClassicMode()) {
			Log.write("Classic mode enabled. Custom blocks not initialized.");
			return;
		}
		Log.write("Classic mode not enabled. Custom blocks initializing.");
		for (BaseBlock block : BaseBlock.values()) {
			int id = Options.INSTANCE.getId(block);
			if (id != 0)
				BaseBlock.makeBlock(block, id);
		}
		Log.write("Custom blocks initialization complete.");
	}

	boolean isLeaves(int id) {
		return leavesBlockIds.contains(id);
	}

	boolean isWood(int id) {
		return woodBlockIds.contains(id);
	}

	private void performSubstitutions(
			Map<TerrainGenBlock, MetaBlock> substitutions) {
		if (substitutions == null)
			return;

		metaBlocksForTerrainGen.putAll(substitutions);
	}

	void registerFuel(int id, IFuel fuel) {
		blockFuel.put(id, fuel);
	}

	void registerLeaves(int id) {
		leavesBlockIds.add(id);
	}

	void registerWood(int id) {
		woodBlockIds.add(id);
	}
}
