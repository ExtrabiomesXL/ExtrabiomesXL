package forestry.api.recipes;

import forestry.api.liquids.LiquidStack;
import net.minecraft.src.ItemStack;

/**
 * Provides an interface to the recipe manager of the fermenter.
 * 
 * The manager is initialized at the beginning of Forestry's BaseMod.load() cycle. Begin adding recipes in BaseMod.ModsLoaded() and this shouldn't be null even
 * if your mod loads before Forestry.
 * 
 * Accessible via {@link RecipeManagers.fermenterManager}
 * 
 * @author SirSengir
 */
public interface IFermenterManager extends ICraftingProvider {

	/**
	 * Add a recipe to the fermenter
	 * 
	 * @param resource
	 *            ItemStack representing the resource.
	 * @param fermentationValue
	 *            Value of the given resource, i.e. how much needs to be fermented for the output to be deposited into the product tank.
	 * @param modifier
	 *            Modifies the amount of liquid output per work cycle. (water = 1.0f, honey = 1.5f)
	 * @param output
	 *            LiquidStack representing output liquid. Amount is determined by fermentationValue*modifier.
	 * @param liquid
	 *            LiquidStack representing resource liquid and amount.
	 */
	public void addRecipe(ItemStack resource, int fermentationValue, float modifier, LiquidStack output, LiquidStack liquid);

	/**
	 * Add a recipe to the fermenter. Defaults to water as input liquid.
	 * 
	 * @param resource
	 *            ItemStack representing the resource.
	 * @param modifier
	 *            Modifies the amount of liquid output per work cycle. (water = 1.0f, honey = 1.5f)
	 * @param fermentationValue
	 *            Value of the given resource, i.e. how much needs to be fermented for the output to be deposited into the product tank.
	 * @param output
	 *            LiquidStack representing output liquid. Amount is determined by fermentationValue*modifier.
	 */
	public void addRecipe(ItemStack resource, int fermentationValue, float modifier, LiquidStack output);

}
