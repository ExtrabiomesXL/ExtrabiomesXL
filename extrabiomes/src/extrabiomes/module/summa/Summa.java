/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.IModule;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.Stuff;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.summa.biome.BiomeManagerImpl;
import extrabiomes.module.summa.block.BlockManager;
import extrabiomes.module.summa.tool.LogTurner;
import extrabiomes.module.summa.worldgen.MarshGenerator;
import extrabiomes.module.summa.worldgen.MountainDesertGenerator;
import extrabiomes.module.summa.worldgen.MountainRidgeGenerator;
import extrabiomes.module.summa.worldgen.VanillaFloraGenerator;

public class Summa implements IModule {

	private static BiomeManagerImpl	biomeManager	= new BiomeManagerImpl();

	private static int				logTurnerID		= 0;

	private static void registerWorldGenerators() {
		if (BiomeManager.marsh.isPresent())
			Extrabiomes.proxy
					.registerWorldGenerator(new MarshGenerator());

		if (BiomeManager.mountaindesert.isPresent())
			Extrabiomes.proxy
					.registerWorldGenerator(new MountainDesertGenerator());

		if (BiomeManager.mountainridge.isPresent())
			Extrabiomes.proxy
					.registerWorldGenerator(new MountainRidgeGenerator());

		Extrabiomes.proxy
				.registerWorldGenerator(new VanillaFloraGenerator());
	}

	@Override
	public void init() throws InstantiationException,
			IllegalAccessException
	{

		registerWorldGenerators();

		biomeManager.init();
		BlockManager.init();

		if (logTurnerID > 0) {
			Stuff.logTurner = Optional.of(new LogTurner(logTurnerID)
					.setItemName("logturner"));

			Extrabiomes.proxy.addName(Stuff.logTurner.get(),
					"Log Turner");
		}

	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		biomeManager.preInit(config);
		BlockManager.preInit(config);

		logTurnerID = config.getItem("logturner.id",
				Extrabiomes.getNextDefaultItemID()).getInt(0);

	}

}
