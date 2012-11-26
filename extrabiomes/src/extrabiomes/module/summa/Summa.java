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
import extrabiomes.biomes.BiomeManagerImpl;
import extrabiomes.core.handler.BiomeHandler;
import extrabiomes.events.ModuleEvent.ModuleInitEvent;
import extrabiomes.events.ModulePreInitEvent;
import extrabiomes.lib.ItemSettings;
import extrabiomes.module.summa.biome.VanillaBiome;
import extrabiomes.module.summa.block.BlockManager;
import extrabiomes.module.summa.tool.LogTurner;
import extrabiomes.module.summa.worldgen.LegendOakGenerator;
import extrabiomes.module.summa.worldgen.MarshGenerator;
import extrabiomes.module.summa.worldgen.MountainDesertGenerator;
import extrabiomes.module.summa.worldgen.MountainRidgeGenerator;
import extrabiomes.module.summa.worldgen.VanillaFloraGenerator;

public class Summa {

    private static BiomeManagerImpl biomeManager = new BiomeManagerImpl();

    private static int              logTurnerID  = 0;

    private static void registerWorldGenerators() {
        if (BiomeManager.marsh.isPresent())
            Extrabiomes.proxy.registerWorldGenerator(new MarshGenerator());

        if (BiomeManager.mountaindesert.isPresent())
            Extrabiomes.proxy.registerWorldGenerator(new MountainDesertGenerator());

        if (BiomeManager.mountainridge.isPresent())
            Extrabiomes.proxy.registerWorldGenerator(new MountainRidgeGenerator());

        Extrabiomes.proxy.registerWorldGenerator(new VanillaFloraGenerator());
        Extrabiomes.proxy.registerWorldGenerator(new LegendOakGenerator());
    }

    @ForgeSubscribe(priority = EventPriority.HIGHEST)
    public void init(ModuleInitEvent event) throws InstantiationException, IllegalAccessException {

        registerWorldGenerators();

        BiomeHandler.enableBiomes();
        biomeManager.buildWeightedFloraLists();
        BlockManager.init();

        if (logTurnerID > 0) {
            Stuff.logTurner = Optional.of(new LogTurner(logTurnerID)
                    .setItemName("extrabiomes.logturner"));

            final IRecipe recipe = new ShapedOreRecipe(Stuff.logTurner.get(), new String[] { "ss",
                    " s", "ss" }, 's', Item.stick);
            Extrabiomes.proxy.addRecipe(recipe);
        }

    }

    @ForgeSubscribe(priority = EventPriority.HIGHEST)
    public void preInit(ModulePreInitEvent event) throws Exception {
        BiomeHandler.init();
        // TODO
        VanillaBiome.preInit();

        BiomeManagerImpl.populateAPIBiomes();
        BlockManager.preInit();

        logTurnerID = ItemSettings.LOGTURNER.getID();

    }

}
