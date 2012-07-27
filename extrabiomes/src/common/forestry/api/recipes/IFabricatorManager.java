package forestry.api.recipes;

import forestry.api.liquids.LiquidStack;
import net.minecraft.src.ItemStack;

public interface IFabricatorManager extends ICraftingProvider {

	void addRecipe(ItemStack plan, LiquidStack molten, ItemStack result, Object[] pattern);

	void addSmelting(ItemStack resource, LiquidStack molten, int meltingPoint);

}
