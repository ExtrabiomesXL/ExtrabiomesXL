package extrabiomes.plugins;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ModLoader;
import net.minecraft.src.WorldGenerator;
import extrabiomes.Log;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.IPlugin;
import extrabiomes.plugins.redpower.BiomeDecorationPlants;
import extrabiomes.plugins.redpower.BiomeDecorationRubberTrees;

public enum PluginRedPower implements IPlugin {
	INSTANCE;

	public static int idPlants = 0;
	public static Class worldGenRubberTree = null;
	public static Field fldPlants = null;

	private static void addBiomeDecoration(BiomeGenBase biome,
			IBiomeDecoration decoration) {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(biome);
		decorations.add(decoration);
	}

	public static WorldGenerator newWorldGenRubberTree() {
		if (worldGenRubberTree != null)
			try {
				return (WorldGenerator) worldGenRubberTree.newInstance();
			} catch (Exception e) {
				Log.write("Could not create RedPower's rubber tree generator.");
			}
		return null;
	}

	@Override
	public String getName() {
		return "RedPower";
	}

	@Override
	public void inject() {
		try {
			// Class cls = Class.forName("RedPowerWorld");
			// Field fld = cls.getField("blockPlants");

			worldGenRubberTree = Class
					.forName("eloraam.world.WorldGenRubberTree");
		} catch (Exception e) {
			Log.write("Could not find RedPower classes. Terrain generation features disabled.");
		}
//		IBiomeDecoration plantsJungleForest = new BiomeDecorationPlants();
//		IBiomeDecoration plantsPlains = new BiomeDecorationPlants(4);
		IBiomeDecoration rubberTree = new BiomeDecorationRubberTrees();

		// Extreme Jungle
//		addBiomeDecoration(BiomeManager.extremejungle, plantsJungleForest);
		addBiomeDecoration(BiomeManager.extremejungle, rubberTree);

		// Mini Jungle
//		addBiomeDecoration(BiomeManager.minijungle, plantsJungleForest);
		addBiomeDecoration(BiomeManager.minijungle, rubberTree);

		// Forests
//		addBiomeDecoration(BiomeManager.birchforest, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.forestedhills, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.forestedisland, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.pineforest, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.rainforest, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.redwoodforest, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.temperaterainforest, plantsJungleForest);
//		addBiomeDecoration(BiomeManager.woodlands, plantsJungleForest);

		// Plains
//		addBiomeDecoration(BiomeManager.meadow, plantsPlains);
//		addBiomeDecoration(BiomeManager.savanna, plantsPlains);
//		addBiomeDecoration(BiomeManager.shrubland, plantsPlains);

	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_RedPowerWorld");
	}

	public static void initBlockId() {
		Block objBlock = null;
		try {
			objBlock = (Block) fldPlants.get(null);
		} catch (Exception e) {
			Log.write("could not find RedPower plant block id. Disabling plant generation.");
		}
		if (objBlock != null)
			idPlants = objBlock.blockID;
	}

}
