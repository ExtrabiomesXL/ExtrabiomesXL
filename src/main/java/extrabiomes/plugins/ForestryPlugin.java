package extrabiomes.plugins;

import java.util.Collection;

import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import extrabiomes.Extrabiomes;
import extrabiomes.api.Stuff;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.helpers.ForestryModHelper;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Element;
import extrabiomes.module.summa.TreeSoilRegistry;
import forestry.api.farming.ICrop;
import forestry.api.farming.IFarmable;

public class ForestryPlugin {
  private class EbXLSapling implements IFarmable {
    
    Block block = null;
    int metadata = 0;
    ItemStack itemSapling = null; 
    
    
    public EbXLSapling(ItemStack item) {
      this.block = Block.getBlockFromItem(item.getItem());
      this.metadata = item.getItemDamage();
      this.itemSapling = item.copy();
    }

    @Override
    public boolean isSaplingAt(World world, int x, int y, int z) {
      return world.getBlock(x, y, z) == block && world.getBlockMetadata(x, y, z) == metadata;
    }

    @Override
    public ICrop getCropAt(World world, int x, int y, int z) {
      return null;
    }

    @Override
    public boolean isGermling(ItemStack itemstack) {
      return this.itemSapling.equals(itemstack);
    }

    @Override
    public boolean isWindfall(ItemStack itemstack) {
      return this.itemSapling.equals(itemstack);
    }

    @Override
    public boolean plantSaplingAt(EntityPlayer player, ItemStack germling, World world, int x, int y, int z) {
      return true;
    }
    
  }
  
  private static final int        DIGGER             = 1;
  private static final int        FORESTER           = 2;
  
  public static void preInit() { }
  
  public static void init() {
    if(!isEnabled()) return;
    
    // Add saplings to the the multifarm
    addFarmableSaplings();
  }
  
  public static boolean isEnabled() {
    return Extrabiomes.proxy.isModLoaded("Forestry"); 
  }
    
  public static void postInit() {
    if(!isEnabled()) return;
    
    // Add forestry soils
    addForestrySoils();
    
    // Add basic flowers
    addFlowers();
    
    // Add globals
    
    // Add backpack items
    addBackpaclItems();

    // Add the redrock recipe
    addRedrockCarpenterRecipes();
    
    // Add the fermenter recipes
    addSaplingRecipes();
  }
  
  private static void addForestrySoils() {
    Block soil =GameData.getBlockRegistry().getObject("Forestry:soil");
    
    if(soil == null || soil == Blocks.air) {
      LogHelper.info("Unable to find forestry soil block");
      return;
    }
    
    TreeSoilRegistry.addValidSoil(soil);
    BlockCustomSapling.setForestrySoil(soil);
    BlockNewSapling.setForestrySoil(soil);
       
  }

  private static void addFarmableSaplings() {
    for (ItemStack sapling : ForestryModHelper.getSaplings()) {
      FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", String.format("farmArboreal@%s.%s", GameData.getBlockRegistry().getNameForObject(Block.getBlockFromItem(sapling.getItem())), sapling.getItemDamage()));
    }
  }

  private static void addBackpaclItems() {
    Collection<ItemStack> items = ForestryModHelper.getForesterBackPackItems();
    for (final ItemStack item : items) {
      forestry.api.storage.BackpackManager.backpackItems[FORESTER].add(item);
    }
    
    items = ForestryModHelper.getDiggerBackPackItems();
    for (final ItemStack item : items) {
      forestry.api.storage.BackpackManager.backpackItems[DIGGER].add(item);
    }
    
    if (Stuff.quickSand.isPresent()) {
      forestry.api.storage.BackpackManager.backpackItems[DIGGER].add(new ItemStack(Stuff.quickSand.get()));
    }
  }

  private static void addFlowers() {
    for (final ItemStack flower: ForestryModHelper.getBasicFlowers()) {
      forestry.api.apiculture.FlowerManager.plainFlowers.add(flower);
    }
  }

  private static void addRedrockCarpenterRecipes() {
    try {
      forestry.api.recipes.RecipeManagers.carpenterManager.addRecipe(10, getFluidStack("water", 3000), null, new ItemStack(Items.clay_ball, 4), new Object[] { "#", Character.valueOf('#'), Element.RED_COBBLE.get() });
    } catch (Exception e) {
      LogHelper.severe("The forestry API changed in reguards to fluids/liquids.");
    }
  }

  private static void addSaplingRecipes() {
    int amnt = forestry.api.core.ForestryAPI.activeMode.getIntegerSetting("fermenter.yield.sapling");
    
    for (final ItemStack sapling : ForestryModHelper.getSaplings()) {
      try {
        forestry.api.recipes.RecipeManagers.fermenterManager.addRecipe(sapling, amnt, 1.0F, getFluidStack("biomass"), getFluidStack("water"));
        forestry.api.recipes.RecipeManagers.fermenterManager.addRecipe(sapling, amnt, 1.5F, getFluidStack("biomass"), getFluidStack("juice"));
        forestry.api.recipes.RecipeManagers.fermenterManager.addRecipe(sapling, amnt, 1.5F, getFluidStack("biomass"), getFluidStack("honey"));
      } catch (Exception e){
        LogHelper.severe("The forestry API changed in reguards to fluids/liquids.");
      }
    }
  }

  private static FluidStack getFluidStack(String name) throws Exception {
    return getFluidStack(name, 1000);
  }
  
  private static FluidStack getFluidStack(String name, int ammount) throws Exception {
    return FluidRegistry.getFluidStack(name, ammount);
  }
}
