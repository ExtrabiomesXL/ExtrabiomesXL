
package extrabiomes.plugin.modsupport.buildcraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import net.minecraft.src.Block;
import net.minecraft.src.World;

import com.google.common.base.Optional;

import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.IPlugin;
import extrabiomes.plugin.modsupport.ModSupport;

public class BuildcraftPlugin implements IPlugin {

	private boolean					modifyWorld							= false;
	private Optional<Block>			oilStill							= Optional
																				.absent();
	private static Optional<Method>	buildcraftGenerateSurfaceDeposit	= Optional
																				.absent();

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

	@Override
	public String getName() {
		return "Buildcraft";
	}

	@Override
	public String getUniqueID() {
		return "EXBLbuildcraft";
	}

	@Override
	public void init() {
		if (modifyWorld && oilStill.isPresent())
			ModSupport.proxy.registerWorldGenerator(new OilGenerator(
					oilStill.get().blockID));
	}

	@Override
	public boolean isEnabled() {
		return ModSupport.proxy.isModLoaded("BuildCraft|Energy");
	}

	@Override
	public void postInit() {}

	@Override
	public void preInit() {
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
