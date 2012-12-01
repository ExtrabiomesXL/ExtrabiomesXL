/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraft.src.ItemStack;

import com.google.common.base.Optional;

public enum Element {
    // @formatter:off
    LEAVES_ACACIA,
    LEAVES_AUTUMN_BROWN,
    LEAVES_AUTUMN_ORANGE,
    LEAVES_AUTUMN_PURPLE,
    LEAVES_AUTUMN_YELLOW,
    LEAVES_FIR,
    LEAVES_REDWOOD;
    // @formatter:on

    private Optional<ItemStack> stack = Optional.absent();

    public ItemStack get() {
        return stack.get();
    }

    public boolean isPresent() {
        return stack.isPresent();
    }

    public void set(ItemStack stack) {
        this.stack = Optional.of(stack);
    }
}
