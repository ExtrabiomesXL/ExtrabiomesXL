/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.thermalexpansion;

import java.lang.reflect.Method;

import net.minecraft.item.ItemStack;

import com.google.common.base.Optional;

class ThermalExpansionAPI {

    private Optional<Object> craftingHelpers            = Optional.absent();

    /**
     * public boolean addSawmillLogToPlankRecipe(ItemStack inputLog,
     * ItemStack outputPlanks);
     */
    private Optional<Method> addSawmillLogToPlankRecipe = Optional.absent();

    @SuppressWarnings({ "rawtypes", "unchecked" })
	ThermalExpansionAPI() {
        try {
            final Class cls = Class.forName("thermalexpansion.api.crafting.CraftingHelpers");
            addSawmillLogToPlankRecipe = Optional.fromNullable(cls.getMethod(
                    "addSawmillLogToPlankRecipe", ItemStack.class, ItemStack.class));
            craftingHelpers = Optional.of(cls.newInstance());
        } catch (final Exception e) {
            e.printStackTrace();
            craftingHelpers = Optional.absent();
            addSawmillLogToPlankRecipe = Optional.absent();
        }
    }

    public void addSawmillLogToPlankRecipe(ItemStack inputLog, ItemStack outputPlanks) {
        if (!craftingHelpers.isPresent() || !addSawmillLogToPlankRecipe.isPresent()) return;
        try {
            addSawmillLogToPlankRecipe.get().invoke(craftingHelpers.get(), inputLog, outputPlanks);
        } catch (final IllegalStateException e) {} catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
