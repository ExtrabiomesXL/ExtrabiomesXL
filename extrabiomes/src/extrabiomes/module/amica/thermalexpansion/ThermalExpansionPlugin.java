/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.thermalexpansion;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.api.Stuff;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.module.fabrica.block.BlockCustomWood;

public class ThermalExpansionPlugin {

    private static final String           MODID    = "ThermalExpansion";
    private static final String           MOD_NAME = "Thermal Expansion";
    private Optional<ThermalExpansionAPI> api      = Optional.absent();

    private void addSawmillRecipe(Element input, Optional<ItemStack> output) {
        if (!output.isPresent() || !input.isPresent()) return;
        api.get().addSawmillLogToPlankRecipe(input.get(), output.get());
    }

    private void addSawmillRecipes() {
        if (!api.isPresent()) return;

        addSawmillRecipe(Element.LOG_ACACIA, Optional.of(new ItemStack(Stuff.planks.get(), 1,
                BlockCustomWood.BlockType.ACACIA.metadata())));

        for (final Element input : new Element[] { Element.LOG_FIR, Element.LOG_HUGE_FIR_NE,
                Element.LOG_HUGE_FIR_NW, Element.LOG_HUGE_FIR_SE, Element.LOG_HUGE_FIR_SW })
            addSawmillRecipe(input, Optional.of(new ItemStack(Stuff.planks.get(), 1,
                    BlockCustomWood.BlockType.FIR.metadata())));

        for (final Element input : new Element[] { Element.LOG_HUGE_REDWOOD_NE,
                Element.LOG_HUGE_REDWOOD_NW, Element.LOG_HUGE_REDWOOD_SE,
                Element.LOG_HUGE_REDWOOD_SW })
            addSawmillRecipe(input, Optional.of(new ItemStack(Stuff.planks.get(), 1,
                    BlockCustomWood.BlockType.REDWOOD.metadata())));

        for (final Element input : new Element[] { Element.LOG_HUGE_OAK_NE,
                Element.LOG_HUGE_OAK_NW, Element.LOG_HUGE_OAK_SE, Element.LOG_HUGE_OAK_SW })
            addSawmillRecipe(input, Optional.of(new ItemStack(Block.planks)));
    }

    @ForgeSubscribe
    public void init(PluginEvent.Init event) {
        if (!api.isPresent()) return;

        addSawmillRecipes();
    }

    @ForgeSubscribe
    public void postInit(PluginEvent.Post event) {
        api = Optional.absent();
    }

    @ForgeSubscribe
    public void preInit(PluginEvent.Pre event) {
        if (!Extrabiomes.proxy.isModLoaded(MODID)) return;
        LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT), MOD_NAME);
        try {
            api = Optional.of(new ThermalExpansionAPI());
        } catch (final Exception ex) {
            ex.printStackTrace();
            LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR),
                    MOD_NAME);
            api = Optional.absent();
        }
    }

}
