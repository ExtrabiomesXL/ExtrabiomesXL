/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.thermalexpansion;

import java.lang.reflect.Method;

import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

class SawmillManager {

    private final Object           sawmill;

    /**
     * boolean addRecipe(int energy, ItemStack input, ItemStack primary,
     * ItemStack secondary, int secondaryChance);
     */
    private final Optional<Method> addRecipe;

    SawmillManager(Object sawmill) throws Exception {
        this.sawmill = sawmill;

        addRecipe = Optional.fromNullable(sawmill.getClass().getMethod("addRecipe", Integer.TYPE,
                ItemStack.class, ItemStack.class, ItemStack.class, Integer.TYPE));
    }

    boolean addRecipe(int energy, ItemStack input, ItemStack primaryOutput) {
        return addRecipe(energy, input, primaryOutput, null, 0);
    }

    boolean addRecipe(int energy, ItemStack input, ItemStack primaryOutput,
            ItemStack secondaryOutput)
    {
        return addRecipe(energy, input, primaryOutput, secondaryOutput, 100);
    }

    boolean addRecipe(int energy, ItemStack input, ItemStack primary, ItemStack secondary,
            int secondaryChance)
    {
        try {
            addRecipe.get().invoke(sawmill, energy, input, primary, secondary, secondaryChance);
            return true;
        } catch (final IllegalStateException e) {} catch (final Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
