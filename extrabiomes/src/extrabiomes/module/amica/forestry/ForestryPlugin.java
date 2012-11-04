/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginEvent;
import extrabiomes.api.Stuff;

public class ForestryPlugin {
	private Class liquidStack;
	private Object fermenterManager;
	private Optional<Constructor> liquidStackConstructor = Optional.absent(); // public LiquidStack(int itemID, int amount, int itemDamage)
	private Optional<Method> fermenterAddRecipe = Optional.absent(); // public void addRecipe(ItemStack resource, int fermentationValue, float modifier, LiquidStack output, LiquidStack liquid);
	private Optional<Method> getForestryItem = Optional.absent(); //public static ItemStack getItem(String ident)

	@ForgeSubscribe
	public void init(PluginEvent.Init event) {
		if (!isEnabled()) return;
		ExtrabiomesLog.info("Initializing Forestry plugin.");
	}

	private boolean isEnabled() {
		return Extrabiomes.proxy.isModLoaded("Forestry");
	}
	private Object getLiquidStack(String name) throws Exception {
		ItemStack itemStack = (ItemStack) getForestryItem.get().invoke(null, name);
		return liquidStackConstructor.get().newInstance(itemStack.itemID, 1, itemStack.getItemDamage());
	}
	private void addFermenterRecipeSapling(ItemStack resource) throws Exception {
		fermenterAddRecipe.get().invoke(fermenterManager, resource, 800, 1.0f, getLiquidStack("liquidBiomass"), liquidStackConstructor.get().newInstance(Block.waterStill.blockID, 1, 0));
		fermenterAddRecipe.get().invoke(fermenterManager, resource, 800, 1.5f, getLiquidStack("liquidBiomass"), getLiquidStack("liquidJuice"));
		fermenterAddRecipe.get().invoke(fermenterManager, resource, 800, 1.5f, getLiquidStack("liquidBiomass"), getLiquidStack("liquidHoney"));
	}
	private void addRecipes()  throws Exception {
		if (fermenterAddRecipe.isPresent() && getForestryItem.isPresent() && Stuff.sapling.isPresent() && liquidStackConstructor.isPresent()) 
		{
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 0));
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 1));
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 2));
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 3));
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 4));
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 5));
			addFermenterRecipeSapling(new ItemStack(Stuff.sapling.get().blockID, 1, 6));
		}
	}

	@ForgeSubscribe
	public void postInit(PluginEvent.Post event) {
		if (!isEnabled()) return;
		try {
			liquidStack = Class.forName("buildcraft.api.liquids.LiquidStack");
			liquidStackConstructor = Optional.fromNullable(liquidStack.getConstructor(int.class, int.class, int.class));

			Class cls = Class.forName("forestry.api.core.ItemInterface");
			getForestryItem = Optional.fromNullable(cls.getMethod("getItem", String.class));

			cls = Class.forName("forestry.api.recipes.RecipeManagers");
			Field fld = cls.getField("fermenterManager");
			fermenterManager = fld.get(null);

			cls = Class.forName("forestry.api.recipes.IFermenterManager");
			fermenterAddRecipe = Optional.fromNullable(cls.getMethod("addRecipe", ItemStack.class, int.class, float.class, liquidStack, liquidStack));

			addRecipes();
		} catch (final Exception ex) {
			ex.printStackTrace();
			ExtrabiomesLog
					.fine("Could not find Forestry fields. Disabling plugin.");
		}
	}

}
