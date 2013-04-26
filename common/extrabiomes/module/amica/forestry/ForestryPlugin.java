/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_ERROR;
import static extrabiomes.module.amica.Amica.LOG_MESSAGE_PLUGIN_INIT;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.liquids.LiquidStack;

import com.google.common.base.Optional;

import cpw.mods.fml.common.event.FMLInterModComms;

import extrabiomes.Extrabiomes;
import extrabiomes.api.PluginEvent;
import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.helpers.ForestryModHelper;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class ForestryPlugin {
    private Object                  fermenterManager;
    private Object                  carpenterManager;
    private static boolean          enabled            = true;

    private ArrayList<ItemStack>               plainFlowers;
    private ArrayList<Integer>               leafBlockIds;
    private ArrayList<ItemStack>[]             backpackItems;

    /**
     * public void addRecipe(int packagingTime, LiquidStack liquid,
     * ItemStack box, ItemStack product, Object materials[]);
     */
    private Optional<Method>        carpenterAddRecipe = Optional.absent();

    /**
     * public void addRecipe(ItemStack resource, int fermentationValue,
     * float modifier, LiquidStack output, LiquisStack liquid);
     */
    private Optional<Method>        fermenterAddRecipe = Optional.absent();

    /**
     * public static ItemStack getItem(String ident);
     */
    private Optional<Method>        getForestryItem    = Optional.absent();

    /**
     * public static ItemStack getItem(String ident);
     */
    private static Optional<Method> getForestryBlock   = Optional.absent();

    private static final int        DIGGER             = 1;

    private static final int        FORESTER           = 2;

    private static final int        BIOMASS_SAPLINGS   = 250;
    
    static ItemStack getBlock(String name) {
        try {
            return (ItemStack) getForestryBlock.get().invoke(null, name);
        } catch (final Exception e) {
            return null;
        }
    }

    private void addBackPackItems() {
        Collection<ItemStack> items = ForestryModHelper.getForesterBackPackItems();
        for (final ItemStack item : items)
            backpackItems[FORESTER].add(item);

        items = ForestryModHelper.getDiggerBackPackItems();
        for (final ItemStack item : items)
            backpackItems[DIGGER].add(item);

        if (Stuff.quickSand.isPresent())
            backpackItems[DIGGER].add(new ItemStack(Stuff.quickSand.get()));
    }

    private void addBasicFlowers() {
        for (final ItemStack flower : ForestryModHelper.getBasicFlowers())
            plainFlowers.add(flower);
    }

    private void addFermenterRecipeSapling(ItemStack resource) throws Exception {
        fermenterAddRecipe.get().invoke(fermenterManager, resource, BIOMASS_SAPLINGS, 1.0f,
                getLiquidStack("liquidBiomass"), new LiquidStack(Block.waterStill.blockID, 1, 0));
        fermenterAddRecipe.get().invoke(fermenterManager, resource, BIOMASS_SAPLINGS, 1.5f,
                getLiquidStack("liquidBiomass"), getLiquidStack("liquidJuice"));
        fermenterAddRecipe.get().invoke(fermenterManager, resource, BIOMASS_SAPLINGS, 1.5f,
                getLiquidStack("liquidBiomass"), getLiquidStack("liquidHoney"));
    }

    private void addGlobals() {
        final Collection<ItemStack> items = ForestryModHelper.getLeaves();
        for (final ItemStack item : items)
            leafBlockIds.add(item.itemID);
    }

    private void addRecipes() throws Exception {
        if (fermenterAddRecipe.isPresent() && getForestryItem.isPresent())
            for (final ItemStack sapling : ForestryModHelper.getSaplings())
                addFermenterRecipeSapling(sapling);
        if (carpenterAddRecipe.isPresent() && Element.RED_COBBLE.isPresent())
            carpenterAddRecipe.get().invoke(carpenterManager, 10,
                    new LiquidStack(Block.waterStill.blockID, 3000, 0), null,
                    new ItemStack(Item.clay, 4),
                    new Object[] { "#", Character.valueOf('#'), Element.RED_COBBLE.get() });
    }

    private void addSaplings() {

        final Optional<ItemStack> soil = Optional.fromNullable(getBlock("soil"));
        if (soil.isPresent()) {
            TreeSoilRegistry.addValidSoil(Block.blocksList[soil.get().itemID]);
            BlockCustomSapling.setForestrySoilID(soil.get().itemID);
        }
        
        for(ItemStack sapling : ForestryModHelper.getSaplings())
        	FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", String.format("farmArboreal@%s.%s", sapling.itemID, sapling.getItemDamage()));
    }

    private LiquidStack getLiquidStack(String name) throws Exception {
        final ItemStack itemStack = (ItemStack) getForestryItem.get().invoke(null, name);
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

    @SuppressWarnings("unchecked")
	@ForgeSubscribe
    public void preInit(PluginEvent.Pre event) {
        if (!isEnabled()) return;
        LogHelper
                .fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT), "Forestry");
        try {
            Class<?> cls = Class.forName("forestry.api.core.ItemInterface");
            getForestryItem = Optional.fromNullable(cls.getMethod("getItem", String.class));

            cls = Class.forName("forestry.api.core.BlockInterface");
            getForestryBlock = Optional.fromNullable(cls.getMethod("getBlock", String.class));

            cls = Class.forName("forestry.api.recipes.RecipeManagers");
            Field fld = cls.getField("fermenterManager");
            fermenterManager = fld.get(null);
            fld = cls.getField("carpenterManager");
            carpenterManager = fld.get(null);

            cls = Class.forName("forestry.api.apiculture.FlowerManager");
            fld = cls.getField("plainFlowers");
            plainFlowers = (ArrayList<ItemStack>) fld.get(null);

            cls = Class.forName("forestry.api.core.GlobalManager");
            fld = cls.getField("leafBlockIds");
            leafBlockIds = (ArrayList<Integer>) fld.get(null);

            cls = Class.forName("forestry.api.storage.BackpackManager");
            fld = cls.getField("backpackItems");
            backpackItems = (ArrayList[]) fld.get(null);

            cls = Class.forName("forestry.api.recipes.IFermenterManager");
            fermenterAddRecipe = Optional.fromNullable(cls.getMethod("addRecipe", ItemStack.class,
                    int.class, float.class, LiquidStack.class, LiquidStack.class));
            cls = Class.forName("forestry.api.recipes.ICarpenterManager");
            carpenterAddRecipe = Optional.fromNullable(cls.getMethod("addRecipe", int.class,
                    LiquidStack.class, ItemStack.class, ItemStack.class, Object[].class));
        } catch (final Exception ex) {
            ex.printStackTrace();
            LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR),
                    "Forestry");
            enabled = false;
        }
    }
}
