package forestry.api.recipes;

import forestry.api.liquids.LiquidStack;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ShapedRecipes;

/**
 * Provides an interface to the recipe manager of the carpenter.
 * 
 * The manager is initialized at the beginning of Forestry's BaseMod.load() cycle. Begin adding recipes in BaseMod.ModsLoaded() and this shouldn't be null even
 * if your mod loads before Forestry.
 * 
 * Accessible via {@link RecipeManagers.carpenterManager}
 * 
 * Only shaped recipes can be added currently.
 * 
 * @author SirSengir
 */
public interface ICarpenterManager extends ICraftingProvider {
	/**
	 * Add a crating (and corresponding uncrating) recipe
	 * 
	 * @param crated
	 *            an {@link ItemGenericCrate} with a set contained item
	 */
	public void addCrating(ItemStack crated);

	/**
	 * Add a crating (and corresponding uncrating) recipe
	 * 
	 * @param toCrate
	 *            Item to crate
	 * @param crated
	 *            Crated item
	 */
	public void addCrating(Item toCrate, IGenericCrate crated);

	/**
	 * Add a crating (and corresponding uncrating) recipe
	 * 
	 * @param toCrate
	 *            ItemStack of item to crate
	 * @param crated
	 *            Crated item
	 */
	public void addCrating(ItemStack toCrate, ItemStack crated);

	/**
	 * Add a shaped recipe to the carpenter.
	 * 
	 * @param box
	 *            ItemStack of one item representing the required box (carton, crate) for this recipe. May be null.
	 * @param product
	 *            Crafting result.
	 * @param materials
	 *            Materials needed in the crafting matrix. This gets passed directly to {@link ShapedRecipes}. Notation is the same.
	 */
	public void addRecipe(ItemStack box, ItemStack product, Object materials[]);

	/**
	 * Add a shaped recipe to the carpenter.
	 * 
	 * @param packagingTime
	 *            Number of work cycles required to craft the recipe once.
	 * @param box
	 *            ItemStack of one item representing the required box (carton, crate) for this recipe. May be null.
	 * @param product
	 *            Crafting result.
	 * @param materials
	 *            Materials needed in the crafting matrix. This gets passed directly to {@link ShapedRecipes}. Notation is the same.
	 */
	public void addRecipe(int packagingTime, ItemStack box, ItemStack product, Object materials[]);

	/**
	 * Add a shaped recipe to the carpenter.
	 * 
	 * @param packagingTime
	 *            Number of work cycles required to craft the recipe once.
	 * @param liquidId
	 *            Id of liquid required in tank. Anything other than Block.waterStill.blockId is untested!
	 * @param liquidAmount
	 *            Amount of liquid required to craft the recipe once. One bucket of water = one unit on the carpenter's tank = 1000.
	 * @param box
	 *            ItemStack of one item representing the required box (carton, crate) for this recipe. May be null.
	 * @param product
	 *            Crafting result.
	 * @param materials
	 *            Materials needed in the crafting matrix. This gets passed directly to {@link ShapedRecipes}. Notation is the same.
	 */
	public void addRecipe(int packagingTime, LiquidStack liquid, ItemStack box, ItemStack product, Object materials[]);
}
