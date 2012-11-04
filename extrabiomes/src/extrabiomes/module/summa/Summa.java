/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa;

import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.Stuff;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.module.summa.biome.BiomeManagerImpl;
import extrabiomes.module.summa.block.BlockManager;
import extrabiomes.module.summa.tool.LogTurner;
import extrabiomes.module.summa.worldgen.LegendOakGenerator;
import extrabiomes.module.summa.worldgen.MarshGenerator;
import extrabiomes.module.summa.worldgen.MountainDesertGenerator;
import extrabiomes.module.summa.worldgen.MountainRidgeGenerator;
import extrabiomes.module.summa.worldgen.VanillaFloraGenerator;

public class Summa {

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
		Extrabiomes.proxy
				.registerWorldGenerator(new LegendOakGenerator());
	}

	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void init(ModuleInitEvent event)
			throws InstantiationException, IllegalAccessException
	{

		registerWorldGenerators();

		biomeManager.init();
		BlockManager.init();

		if (logTurnerID > 0) {
			Stuff.logTurner = Optional.of(new LogTurner(logTurnerID)
					.setItemName("logturner"));

			Extrabiomes.proxy.addName(Stuff.logTurner.get(),
					"Log Turner");

			final IRecipe recipe = new ShapedOreRecipe(
					Stuff.logTurner.get(), new String[] { "ss", " s",
							"ss" }, 's', Item.stick);
			Extrabiomes.proxy.addRecipe(recipe);
		}

	}

	@ForgeSubscribe(priority = EventPriority.HIGHEST)
	public void preInit(ModulePreInitEvent event) throws Exception {
		biomeManager.preInit(event.config);
		BlockManager.preInit(event.config);

		logTurnerID = event.config.getItem("logturner.id",
				Extrabiomes.getNextDefaultItemID()).getInt(0);

	}

}
