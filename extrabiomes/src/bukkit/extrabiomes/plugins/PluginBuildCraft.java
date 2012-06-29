
package extrabiomes.plugins;

import java.lang.reflect.Field;
import java.util.Collection;

import net.minecraft.server.ModLoader;
import net.minecraft.server.Block;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IPlugin;
import extrabiomes.plugins.buildcraft.OilPopulateMediumLargeDeposits;
import extrabiomes.plugins.buildcraft.OilPopulateSmallDepositDesert;
import extrabiomes.plugins.buildcraft.OilPopulateSmallDepositWasteland;

public enum PluginBuildCraft implements IPlugin {
	INSTANCE;

	public static boolean	modifyWorld	= false;
	public static Block		oilStill	= null;
	public static Block		oilMoving	= null;

	@Override
	public String getName() {
		return "Buildcraft";
	}

	@Override
	public void inject() {
		try {
			Class class1 = Class.forName("BuildCraftCore");
			Field field = class1.getField("modifyWorld");
			modifyWorld = field.getBoolean(null);
			class1 = Class.forName("BuildCraftEnergy");
			field = class1.getField("oilStill");
			oilStill = (Block) field.get(null);
			field = class1.getField("oilMoving");
			oilMoving = (Block) field.get(null);
		} catch (final Exception exception) {
			ModLoader.getLogger().fine(
					"Could not find Buildcraft classes.");
			return;
		}

		Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.mountaindesert);
		collection.add(new OilPopulateSmallDepositDesert());
		collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.wasteland);
		collection.add(new OilPopulateSmallDepositWasteland());
		BiomeDecorationsManager.commonDecorations
				.add(new OilPopulateMediumLargeDeposits());
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_BuildCraftEnergy");
	}
}
