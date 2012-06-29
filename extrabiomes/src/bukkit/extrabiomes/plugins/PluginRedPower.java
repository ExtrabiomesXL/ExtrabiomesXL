
package extrabiomes.plugins;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collection;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.ModLoader;
import net.minecraft.server.WorldGenerator;
import net.minecraft.server.Block;
import extrabiomes.Log;
import extrabiomes.Proxy;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.IPlugin;
import extrabiomes.plugins.redpower.BiomeDecorationPlants;
import extrabiomes.plugins.redpower.BiomeDecorationRubberTrees;
import forge.Configuration;
import forge.Property;

public enum PluginRedPower implements IPlugin {
	INSTANCE;

	public static int			idPlants			= 0;
	public static Class			worldGenRubberTree	= null;
	public static Field			fldPlants			= null;
	public static final String	CATEGORY_PLUGIN		= "PLUGINS";

	private static void addBiomeDecoration(BiomeBase biomebase,
			IBiomeDecoration ibiomedecoration)
	{
		if (ibiomedecoration == null)
			return;
		else {
			final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
					.get(biomebase);
			collection.add(ibiomedecoration);
			return;
		}
	}

	public static void initBlockId() {
		Block block = null;

		try {
			block = (Block) fldPlants.get(null);
		} catch (final Exception exception) {
			Log.write("could not find RedPower plant block id. Disabling plant generation.");
		}

		if (block != null) idPlants = block.id;
	}

	public static WorldGenerator newWorldGenRubberTree() {
		if (worldGenRubberTree != null)
			try {
				return (WorldGenerator) worldGenRubberTree
						.newInstance();
			} catch (final Exception exception) {
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
			worldGenRubberTree = Class
					.forName("eloraam.world.WorldGenRubberTree");
		} catch (final Exception exception) {
			Log.write("Could not find RedPower classes. Terrain generation features disabled.");
		}

		final Configuration configuration = new Configuration(new File(
				Proxy.getExtrabiomesRoot(),
				"/config/extrabiomes/extrabiomes.cfg"));
		configuration.load();
		final Property property = configuration.getOrCreateIntProperty(
				"redpower.plants.id", "PLUGINS", idPlants);
		idPlants = Integer.parseInt(property.value);

		if (idPlants == 0)
			property.comment = "Set this to the same value as plants.id in the world section of redpower/redpower.cfg";

		configuration.save();
		final BiomeDecorationPlants biomedecorationplants = idPlants == 0 ? null
				: new BiomeDecorationPlants();
		final BiomeDecorationPlants biomedecorationplants1 = idPlants == 0 ? null
				: new BiomeDecorationPlants(4);
		final BiomeDecorationRubberTrees biomedecorationrubbertrees = new BiomeDecorationRubberTrees();
		addBiomeDecoration(BiomeManager.extremejungle,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.extremejungle,
				biomedecorationrubbertrees);
		addBiomeDecoration(BiomeManager.minijungle,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.minijungle,
				biomedecorationrubbertrees);
		addBiomeDecoration(BiomeManager.birchforest,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.forestedhills,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.forestedisland,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.pineforest,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.rainforest,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.redwoodforest,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.temperaterainforest,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.woodlands,
				biomedecorationplants);
		addBiomeDecoration(BiomeManager.meadow, biomedecorationplants1);
		addBiomeDecoration(BiomeManager.savanna, biomedecorationplants1);
		addBiomeDecoration(BiomeManager.shrubland,
				biomedecorationplants1);
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_RedPowerWorld");
	}
}
