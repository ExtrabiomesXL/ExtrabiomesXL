/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.amica.forestry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.google.common.base.Optional;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.helpers.ForestryModHelper;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;

public class ForestryPlugin
{
    private static Object           fermenterManager;
    private static Object           carpenterManager;
    private static Object           flowerRegistry;
    private static boolean          enabled            = true;
    
    @SuppressWarnings("rawtypes")
    private static ArrayList        plainFlowers;
    @SuppressWarnings("rawtypes")
    private static ArrayList[]      backpackItems;
    
    /**
     * public void addRecipe(int packagingTime, LiquidStack liquid, ItemStack box, ItemStack product, Object materials[]);
     */
    private static Optional<Method> carpenterAddRecipe = Optional.absent();
    
    /**
     * public void addRecipe(ItemStack resource, int fermentationValue, float modifier, LiquidStack output, LiquidStack liquid);
     */
    private static Optional<Method> fermenterAddRecipe = Optional.absent();

    /**
     * public void registerPlantableFlower(Block flowerBlock, int flowerMeta, double weight, String... flowerTypes);
     */
    private static Optional<Method> registerFlower     = Optional.absent();
    
    private static final int        DIGGER             = 1;
    private static final int        FORESTER           = 2;
    private static int              BIOMASS_SAPLINGS;
    
    static ItemStack getBlock(String name)
    {
        return GameRegistry.findItemStack("Forestry", name, 1);
    }
    
    @SuppressWarnings("unchecked")
    private static void addBackPackItems()
    {
        Collection<ItemStack> items = ForestryModHelper.getForesterBackPackItems();
        for (final ItemStack item : items)
        {
            backpackItems[FORESTER].add(item);
        }
        
        items = ForestryModHelper.getDiggerBackPackItems();
        for (final ItemStack item : items)
        {
            backpackItems[DIGGER].add(item);
        }
        
        if (Stuff.quickSand.isPresent())
        {
            backpackItems[DIGGER].add(new ItemStack(Stuff.quickSand.get()));
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void addBasicFlowers()
    {
        if (registerFlower.isPresent())
        {
            try
            {
                String[] flowerTypes = new String[] { "flowersVanilla" };
                for (final ItemStack flower : ForestryModHelper.getBasicFlowers())
                {
                    Block block = Block.getBlockFromItem(flower.getItem());
                    int meta = flower.getItemDamage();
                    registerFlower.get().invoke(flowerRegistry, block, meta, 1.0, flowerTypes);
                }
            }
            catch (Exception e)
            {
                LogHelper.severe("The forestry API changed in regards to flowers.");
            }
        }
        else
        {
            for (final ItemStack flower : ForestryModHelper.getBasicFlowers())
            {
                plainFlowers.add(flower);
            }
        }
    }

    private static void addSaplingRecipes() {
        if (fermenterAddRecipe.isPresent())
        {
            // Make sure that all the fluids that we need exist
            for (final String type : new String[] { "water", "biomass", "honey", "juice" })
            {
                if (!FluidRegistry.isFluidRegistered(type))
                {
                    LogHelper.warning("Unable to find fluid named '%s' when adding Forestry fermenter recipes.", type);
                    return;
                }
            }

            try
            {
                for (final ItemStack sapling : ForestryModHelper.getSaplings())
                {
                    fermenterAddRecipe.get().invoke(fermenterManager, sapling, BIOMASS_SAPLINGS, 1.0f, getFluidStack("biomass"), getFluidStack("water"));
                    fermenterAddRecipe.get().invoke(fermenterManager, sapling, BIOMASS_SAPLINGS, 1.5f, getFluidStack("biomass"), getFluidStack("juice"));
                    fermenterAddRecipe.get().invoke(fermenterManager, sapling, BIOMASS_SAPLINGS, 1.5f, getFluidStack("biomass"), getFluidStack("honey"));
                }
            }
            catch (Exception e)
            {
                LogHelper.severe("The forestry API changed in reguards to fluids/liquids.");
            }
        }
    }

    private static void addRedrockCarpenterRecipes()
    {
        try
        {
            if (carpenterAddRecipe.isPresent() && Element.RED_COBBLE.isPresent()) {
                carpenterAddRecipe.get().invoke(carpenterManager, 10, getFluidStack("water", 3000), null, new ItemStack(Items.clay_ball, 4), new Object[]{"#", '#', Element.RED_COBBLE.get()});
            }
        }
        catch (Exception e)
        {
            LogHelper.severe("The forestry API changed for the carpenter.");
        }
    }

    private static void addSoils()
    {
        final Optional<ItemStack> soil = Optional.fromNullable(getBlock("soil"));
        if (soil.isPresent())
        {
            Block soilBlock = Block.getBlockFromItem(soil.get().getItem());
            TreeSoilRegistry.addValidSoil(soilBlock);
            BlockCustomSapling.setForestrySoil(soilBlock);
            BlockNewSapling.setForestrySoil(soilBlock);
        }
    }
    
    private static void addSaplings()
    {
        for (ItemStack sapling : ForestryModHelper.getSaplings())
        {
            Block blockSapling = Block.getBlockFromItem(sapling.getItem());
            String saplingName = GameData.getBlockRegistry().getNameForObject(blockSapling);
            FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", String.format("farmArboreal@%s.%s", saplingName, sapling.getItemDamage()));
        }
    }
    
    private static FluidStack getFluidStack(String name)
    {
        return getFluidStack(name, 1000);
    }
    
    private static FluidStack getFluidStack(String name, int ammount)
    {
        return FluidRegistry.getFluidStack(name, ammount);
    }
    
    public static void init()
    {
        if (!isEnabled())
            return;

        setupPlugin();

        addSoils();
        addSaplings();
        addBasicFlowers();
        addBackPackItems();
        addRedrockCarpenterRecipes();
        addSaplingRecipes();
    }
    
    private static boolean isEnabled()
    {
        return enabled && Extrabiomes.proxy.isModLoaded("Forestry");
    }
    
    @SuppressWarnings({ "rawtypes" })
    public static void setupPlugin()
    {
        if (!isEnabled())
            return;
        //LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_INIT), "Forestry");
        LogHelper.fine("Initializing %s plugin.", "Forestry");
        
        try
        {
            Class<?> cls = Class.forName("forestry.api.recipes.RecipeManagers");
            Field fld = cls.getField("fermenterManager");
            fermenterManager = fld.get(null);
            fld = cls.getField("carpenterManager");
            carpenterManager = fld.get(null);
            
            cls = Class.forName("forestry.api.apiculture.FlowerManager");
            try {
                fld = cls.getField("plainFlowers");
                plainFlowers = (ArrayList) fld.get(null);
            } catch (NoSuchFieldException e) {
                // newer API has no plainFlowers, uses IFlowerRegistry
                fld = cls.getField("flowerRegistry");
                flowerRegistry = fld.get(null);
                cls = Class.forName("forestry.api.genetics.IFlowerRegistry");
                registerFlower = Optional.fromNullable(cls.getMethod("registerPlantableFlower", Block.class, int.class, double.class, String[].class));
            }
            
            cls = Class.forName("forestry.api.storage.BackpackManager");
            fld = cls.getField("backpackItems");
            backpackItems = (ArrayList[]) fld.get(null);
            
            cls = Class.forName("forestry.api.recipes.IFermenterManager");
            fermenterAddRecipe = Optional.fromNullable(cls.getMethod("addRecipe", ItemStack.class, int.class, float.class, FluidStack.class, FluidStack.class));
            cls = Class.forName("forestry.api.recipes.ICarpenterManager");
            carpenterAddRecipe = Optional.fromNullable(cls.getMethod("addRecipe", int.class, FluidStack.class, ItemStack.class, ItemStack.class, Object[].class));

            cls = Class.forName("forestry.api.core.ForestryAPI");
            fld = cls.getField("activeMode");
            Object activeMode = fld.get(null);
            cls = Class.forName("forestry.api.core.IGameMode");
            Optional<Method> getIntegerSetting = Optional.fromNullable(cls.getMethod("getIntegerSetting", String.class));
            BIOMASS_SAPLINGS = (Integer) getIntegerSetting.get().invoke(activeMode, "fermenter.yield.sapling");
        }
        catch (final Exception ex)
        {
            ex.printStackTrace();
            //LogHelper.fine(Extrabiomes.proxy.getStringLocalization(LOG_MESSAGE_PLUGIN_ERROR), "Forestry");
            LogHelper.fine("Could not communicate with %s. Disabling plugin.", "Forestry");
            enabled = false;
        }
    }
}
