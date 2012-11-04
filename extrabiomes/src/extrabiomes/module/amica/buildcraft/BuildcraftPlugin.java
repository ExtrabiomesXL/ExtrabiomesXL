/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.buildcraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.src.Block;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginEvent;

public class BuildcraftPlugin {

	private boolean					modifyWorld	= false;
	private Optional<Block>			oilStill = Optional.absent();
	private static Optional<Method>	buildcraftGenerateSurfaceDeposit = Optional.absent();

	static void generateSurfaceDeposit(World world, int x, int y,
			int z, int radius)
	{
		final Object arglist[] = { world, Integer.valueOf(x),
				Integer.valueOf(y), Integer.valueOf(z),
				Integer.valueOf(radius) };

		if (buildcraftGenerateSurfaceDeposit.isPresent())
			try {
				buildcraftGenerateSurfaceDeposit.get().invoke(null,
						arglist);
			} catch (final Exception e) {
				ExtrabiomesLog
						.fine("Could not invoke buildcraft.energy.OilPopulate.generateSurfaceDeposit");
			}
	}

	@ForgeSubscribe
	public void init(PluginEvent.Init event) {
		if (!isEnabled()) return;
		ExtrabiomesLog.info("Initializing Buildcraft plugin.");
		if (modifyWorld && oilStill.isPresent())
			Extrabiomes.proxy.registerWorldGenerator(new OilGenerator(
					oilStill.get().blockID));
	}

	private boolean isEnabled() {
		return Extrabiomes.proxy.isModLoaded("BuildCraft|Energy");
	}

	@ForgeSubscribe
	public void preInit(PluginEvent.Pre event) {
		if (!isEnabled()) return;
		try {
			Class cls = Class.forName("buildcraft.BuildCraftCore");
			Field fld = cls.getField("modifyWorld");
			modifyWorld = fld.getBoolean(null);

			cls = Class.forName("buildcraft.BuildCraftEnergy");
			fld = cls.getField("oilStill");
			oilStill = Optional.fromNullable((Block) fld.get(null));

			final Class parTypes[] = { World.class, Integer.class,
					Integer.class, Integer.class, Integer.class };
			cls = Class.forName("buildcraft.energy.OilPopulate");
			buildcraftGenerateSurfaceDeposit = Optional
					.fromNullable(cls.getMethod(
							"generateSurfaceDeposit", parTypes));
		} catch (final Exception e) {
			ExtrabiomesLog
					.fine("Could not find Buildcraft fields. Disabling plugin.");
		}
	}

}
