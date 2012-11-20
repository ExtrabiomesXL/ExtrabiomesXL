/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidStack;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginEvent;
import extrabiomes.api.Stuff;
import extrabiomes.module.summa.TreeSoilRegistry;
import extrabiomes.module.summa.block.BlockAutumnLeaves;
import extrabiomes.module.summa.block.BlockCustomFlower;
import extrabiomes.module.summa.block.BlockCustomSapling;
import extrabiomes.module.summa.block.BlockGreenLeaves;
import extrabiomes.module.summa.block.BlockRedRock;

public class ForestryPlugin {
	private Object					fermenterManager;
	private Object					carpenterManager;
	private static boolean			enabled					= true;

	private ArrayList				arborealCrops;
	private ArrayList				plainFlowers;
	private ArrayList				leafBlockIds;
	private ArrayList[]				backpackItems;

	public static ArrayList			loggerWindfall;


	/**
	 * public void addRecipe(int packagingTime, LiquidStack liquid,
	 * ItemStack box, ItemStack product, Object materials[]);
	 */
	private Optional<Method>		carpenterAddRecipe		= Optional
																	.absent();

	/**
	 * public void addRecipe(ItemStack resource, int fermentationValue,
	 * float modifier, LiquidStack output, LiquisStack liquid);
	 */
	private Optional<Method>		fermenterAddRecipe		= Optional.absent();

	/**
	 * public static ItemStack getItem(String ident);
	 */
	private Optional<Method>		getForestryItem			= Optional.absent();

	/**
	 * public static ItemStack getItem(String ident);
	 */
	private static Optional<Method>	getForestryBlock		= Optional.absent();

	private static final int		DIGGER					= 1;

	private static final int		FORESTER				= 2;

	static ItemStack getBlock(String name) {
		try {
			return (ItemStack) getForestryBlock.get()
					.invoke(null, name);
		} catch (final Exception e) {
			return null;
		}
	}

	private void addBackPackItems() {
		if (Stuff.crackedSand.isPresent())
			backpackItems[DIGGER].add(new ItemStack(Stuff.crackedSand
					.get()));
		if (Stuff.redRock.isPresent())
			backpackItems[DIGGER].add(new ItemStack(
					Stuff.redRock.get(), 1,
					BlockRedRock.BlockType.RED_COBBLE.metadata()));
		if (Stuff.quickSand.isPresent())
			backpackItems[DIGGER].add(new ItemStack(Stuff.quickSand
					.get()));

		if (Stuff.leavesAutumn.isPresent())
			for (final BlockAutumnLeaves.BlockType type : BlockAutumnLeaves.BlockType
					.values())
				backpackItems[FORESTER].add(new ItemStack(
						Stuff.leavesAutumn.get(), 1, type.metadata()));

		if (Stuff.leavesGreen.isPresent())
			for (final BlockGreenLeaves.BlockType type : BlockGreenLeaves.BlockType
					.values())
				backpackItems[FORESTER].add(new ItemStack(
						Stuff.leavesGreen.get(), 1, type.metadata()));

		if (Stuff.sapling.isPresent())
			for (final BlockCustomSapling.BlockType type : BlockCustomSapling.BlockType
					.values())
				backpackItems[FORESTER].add(new ItemStack(Stuff.sapling
						.get(), 1, type.metadata()));

		if (Stuff.flower.isPresent())
			for (final BlockCustomFlower.BlockType type : BlockCustomFlower.BlockType
					.values())
				backpackItems[FORESTER].add(new ItemStack(Stuff.flower
						.get(), 1, type.metadata()));
	}

	private void addBasicFlowers() {
		if (!Stuff.flower.isPresent()) return;

		plainFlowers.add(new ItemStack(Stuff.flower.get(), 1,
				BlockCustomFlower.BlockType.HYDRANGEA.metadata()));
		plainFlowers.add(new ItemStack(Stuff.flower.get(), 1,
				BlockCustomFlower.BlockType.ORANGE.metadata()));
		plainFlowers.add(new ItemStack(Stuff.flower.get(), 1,
				BlockCustomFlower.BlockType.PURPLE.metadata()));
		plainFlowers.add(new ItemStack(Stuff.flower.get(), 1,
				BlockCustomFlower.BlockType.WHITE.metadata()));
	}

	private void addFermenterRecipeSapling(ItemStack resource)
			throws Exception
	{
		fermenterAddRecipe.get().invoke(
				fermenterManager,
				resource,
				800,
				1.0f,
				getLiquidStack("liquidBiomass"),
				new LiquidStack(Block.waterStill.blockID, 1, 0));
		fermenterAddRecipe.get().invoke(fermenterManager, resource,
				800, 1.5f, getLiquidStack("liquidBiomass"),
				getLiquidStack("liquidJuice"));
		fermenterAddRecipe.get().invoke(fermenterManager, resource,
				800, 1.5f, getLiquidStack("liquidBiomass"),
				getLiquidStack("liquidHoney"));
	}

	private void addGlobals() {
		if (Stuff.leavesAutumn.isPresent())
			leafBlockIds.add(Stuff.leavesAutumn.get().blockID);
		if (Stuff.leavesGreen.isPresent())
			leafBlockIds.add(Stuff.leavesGreen.get().blockID);
	}

	private void addRecipes() throws Exception {
		if (fermenterAddRecipe.isPresent()
				&& getForestryItem.isPresent()
				&& Stuff.sapling.isPresent())
			for (final BlockCustomSapling.BlockType type : BlockCustomSapling.BlockType
					.values())
				addFermenterRecipeSapling(new ItemStack(
						Stuff.sapling.get().blockID, 1, type.metadata()));
		if (carpenterAddRecipe.isPresent()
				&& Stuff.redRock.isPresent())
			carpenterAddRecipe.get().invoke(carpenterManager, 10,
				new LiquidStack(Block.waterStill.blockID, 3000, 0),
				null, new ItemStack(Item.clay, 4),
				new Object[] { "#", Character.valueOf('#'), new ItemStack(Stuff.redRock.get(), 1, 1)}
				);
	}

	private void addSaplings() {
		if (!Stuff.sapling.isPresent()) return;

		final Optional<ItemStack> soil = Optional
				.fromNullable(getBlock("soil"));
		if (soil.isPresent())
			TreeSoilRegistry
					.addValidSoil(Block.blocksList[soil.get().itemID]);
		arborealCrops.add(new CropProviderSapling());
	}

	private LiquidStack getLiquidStack(String name) throws Exception {
		final ItemStack itemStack = (ItemStack) getForestryItem.get()
				.invoke(null, name);
		return new LiquidStack(itemStack.itemID, 1, itemStack.getItemDamage());
	}

	@ForgeSubscribe
	public void init(PluginEvent.Init event) throws Exception {
		if (!isEnabled()) return;
		addSaplings();
		addBasicFlowers();
		addGlobals();
		addBackPackItems();
		addRecipes();
	}

	private boolean isEnabled() {
		return enabled && Extrabiomes.proxy.isModLoaded("Forestry");
	}

	@ForgeSubscribe
	public void preInit(PluginEvent.Pre event) {
		if (!isEnabled()) return;
		ExtrabiomesLog.fine(Extrabiomes.proxy
				.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT),
				"Forestry");
		try {
			Class cls = Class
					.forName("forestry.api.core.ItemInterface");
			getForestryItem = Optional.fromNullable(cls.getMethod(
					"getItem", String.class));

			cls = Class.forName("forestry.api.core.BlockInterface");
			getForestryBlock = Optional.fromNullable(cls.getMethod(
					"getBlock", String.class));

			cls = Class.forName("forestry.api.recipes.RecipeManagers");
			Field fld = cls.getField("fermenterManager");
			fermenterManager = fld.get(null);
			fld = cls.getField("carpenterManager");
			carpenterManager = fld.get(null);

			cls = Class
					.forName("forestry.api.core.ForestryAPI");
			fld = cls.getField("loggerWindfall");
			loggerWindfall = (ArrayList) fld.get(null);

			cls = Class.forName("forestry.api.cultivation.CropProviders");
			fld = cls.getField("arborealCrops");
			arborealCrops = (ArrayList) fld.get(null);

			cls = Class
					.forName("forestry.api.apiculture.FlowerManager");
			fld = cls.getField("plainFlowers");
			plainFlowers = (ArrayList) fld.get(null);

			cls = Class.forName("forestry.api.core.GlobalManager");
			fld = cls.getField("leafBlockIds");
			leafBlockIds = (ArrayList) fld.get(null);

			cls = Class.forName("forestry.api.storage.BackpackManager");
			fld = cls.getField("backpackItems");
			backpackItems = (ArrayList[]) fld.get(null);

			cls = Class
					.forName("forestry.api.recipes.IFermenterManager");
			fermenterAddRecipe = Optional.fromNullable(cls.getMethod(
					"addRecipe", ItemStack.class, int.class,
					float.class, LiquidStack.class, LiquidStack.class));
			cls = Class
					.forName("forestry.api.recipes.ICarpenterManager");
			carpenterAddRecipe = Optional.fromNullable(cls.getMethod(
					"addRecipe", int.class, LiquidStack.class, ItemStack.class,
					ItemStack.class, Object[].class));
		} catch (final Exception ex) {
			ex.printStackTrace();
			ExtrabiomesLog.fine(Extrabiomes.proxy
					.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR),
					"Forestry");
			enabled = false;
		}
	}
}
