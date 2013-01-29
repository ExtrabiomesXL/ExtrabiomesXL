
package extrabiomes.module.amica.buildcraft;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import net.minecraft.world.World;

import com.google.common.base.Optional;

public class BuildcraftAPI {

	private boolean modifyWorld = false;

	/**
	 * public static void generateSurfaceDeposit(World world, Random rand, int x, int
	 * y, int z, int radius);
	 */
	private static Optional<Method>	generateSurfaceDeposit	= Optional.absent();

	BuildcraftAPI() {
		Class cls;
		try {
			cls = Class.forName("buildcraft.BuildCraftCore");
			final Field fld = cls.getField("modifyWorld");
			modifyWorld = fld.getBoolean(null);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		try {
			cls = Class.forName("buildcraft.energy.OilPopulate");
			generateSurfaceDeposit = Optional.fromNullable(cls
					.getMethod("generateSurfaceDeposit", World.class, Random.class,
							Integer.TYPE, Integer.TYPE,
							Integer.TYPE, Integer.TYPE));
		} catch (final Exception e) {
			e.printStackTrace();
			generateSurfaceDeposit = Optional.absent();
		}
	}

	void generateSurfaceDeposit(World world, Random rand, int x, int y, int z,
			int radius)
	{
		try {
			generateSurfaceDeposit.get().invoke(null, world, rand, x, y, z,
					radius);
		} catch (final IllegalStateException e) {} catch (final Exception e)
		{
			e.printStackTrace();
		}
	}

	boolean modifyWorld() {
		return modifyWorld;
	}

}
