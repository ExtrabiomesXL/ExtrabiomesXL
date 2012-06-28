package extrabiomes.plugins;

import java.lang.reflect.Field;
import java.util.Collection;

import net.minecraft.src.Block;
import net.minecraft.src.ModLoader;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.IPlugin;
import extrabiomes.plugins.buildcraft.OilPopulateMediumLargeDeposits;
import extrabiomes.plugins.buildcraft.OilPopulateSmallDepositDesert;
import extrabiomes.plugins.buildcraft.OilPopulateSmallDepositWasteland;

public enum PluginBuildCraft implements IPlugin {
	INSTANCE;

	public static boolean modifyWorld = false;
	public static Block oilStill = null;
	public static Block oilMoving = null;

	@Override
	public String getName() {
		return "Buildcraft";
	}

	@Override
	public void inject() {
		try {
			Class cls = Class.forName("BuildCraftCore");
			Field fld = cls.getField("modifyWorld");
			modifyWorld = fld.getBoolean(null);

			cls = Class.forName("BuildCraftEnergy");
			fld = cls.getField("oilStill");
			oilStill = (Block) fld.get(null);

			fld = cls.getField("oilMoving");
			oilMoving = (Block) fld.get(null);
		} catch (Exception e) {
			ModLoader.getLogger().fine("Could not find Buildcraft classes.");
			return;
		}
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.mountaindesert);
		decorations.add(new OilPopulateSmallDepositDesert());

		decorations = BiomeDecorationsManager.biomeDecorations
				.get(BiomeManager.wasteland);
		decorations.add(new OilPopulateSmallDepositWasteland());

		BiomeDecorationsManager.commonDecorations
				.add(new OilPopulateMediumLargeDeposits());
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_BuildCraftEnergy");
	}

}
