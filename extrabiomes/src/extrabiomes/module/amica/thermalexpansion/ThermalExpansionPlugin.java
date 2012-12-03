/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.thermalexpansion;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.api.Stuff;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.module.fabrica.block.BlockCustomWood;
import extrabiomes.module.summa.block.BlockQuarterLog;

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
        addSawmillRecipe(Element.LOG_FIR, Optional.of(new ItemStack(Stuff.planks.get(), 1,
                BlockCustomWood.BlockType.FIR.metadata())));

        if (Stuff.planks.isPresent()) {
            ItemStack input = new ItemStack(Stuff.quarterLogNE.get(), 1,
                    BlockQuarterLog.BlockType.FIR.metadata());
            ItemStack primaryOutput = new ItemStack(Stuff.planks.get(), 6,
                    BlockCustomWood.BlockType.FIR.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogNW.get(), 1,
                    BlockQuarterLog.BlockType.FIR.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogSE.get(), 1,
                    BlockQuarterLog.BlockType.FIR.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogSW.get(), 1,
                    BlockQuarterLog.BlockType.FIR.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogNE.get(), 1,
                    BlockQuarterLog.BlockType.REDWOOD.metadata());
            primaryOutput = new ItemStack(Stuff.planks.get(), 6,
                    BlockCustomWood.BlockType.REDWOOD.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogNW.get(), 1,
                    BlockQuarterLog.BlockType.REDWOOD.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogSE.get(), 1,
                    BlockQuarterLog.BlockType.REDWOOD.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogSW.get(), 1,
                    BlockQuarterLog.BlockType.REDWOOD.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogNE.get(), 1,
                    BlockQuarterLog.BlockType.OAK.metadata());
            primaryOutput = new ItemStack(Block.planks, 6);
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogNW.get(), 1,
                    BlockQuarterLog.BlockType.OAK.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogSE.get(), 1,
                    BlockQuarterLog.BlockType.OAK.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);

            input = new ItemStack(Stuff.quarterLogSW.get(), 1,
                    BlockQuarterLog.BlockType.OAK.metadata());
            api.get().addSawmillLogToPlankRecipe(input, primaryOutput);
        }
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
