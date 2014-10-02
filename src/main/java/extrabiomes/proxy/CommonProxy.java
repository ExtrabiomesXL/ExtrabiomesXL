/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.proxy;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.eventhandler.Event;
import extrabiomes.api.Stuff;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Reference;

public class CommonProxy
{

    public void addBiome(Collection<WorldType> worldTypes, BiomeGenBase biome)
    {
    	// TODO
        /*for (final WorldType worldType : worldTypes)
            worldType.addNewBiome(biome);*/
    }

    public void addGrassPlant(Block block, int metadata, int weight, BiomeGenBase biome)
    {
    	if(biome != null) biome.addFlower(block, metadata, weight);
    }
    public void addGrassPlant(Block block, int metadata, int weight)
    {
        for(BiomeGenBase biome: BiomeGenBase.getBiomeGenArray()) {
            addGrassPlant(block, metadata, weight, biome);
        }
    }

    @SuppressWarnings("unchecked")
    public void addRecipe(IRecipe recipe)
    {
        CraftingManager.getInstance().getRecipeList().add(0, recipe);
    }

    public void addSmelting(Item item, int metadata, ItemStack itemstack, float experience)
    {
        FurnaceRecipes.smelting().func_151394_a(new ItemStack(item, 1, metadata), itemstack, experience);
    }

    public void addSmelting(ItemStack input, ItemStack output, float experience)
    {
        FurnaceRecipes.smelting().func_151394_a(input, output, experience);
    }

    public int findGlobalUniqueEntityId()
    {
        return EntityRegistry.findGlobalUniqueEntityId();
    }

    public Logger getFMLLogger()
    {
        return FMLLog.getLogger();
    }

    public Optional<ItemStack> getGrassSeed(World world)
    {
        return Optional.fromNullable(ForgeHooks.getGrassSeed(world));
    }

    public ArrayList<ItemStack> getOres(String name)
    {
        return OreDictionary.getOres(name);
    }

    public boolean isModLoaded(String modID)
    {
        return Loader.isModLoaded(modID);
    }

    public boolean postEventToBus(Event event)
    {
        return MinecraftForge.EVENT_BUS.post(event);
    }

    public void registerBlock(Block block, String uniqueName)
    {
        GameRegistry.registerBlock(block, ItemBlock.class, uniqueName);
    }

    public void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String uniqueName)
    {
        GameRegistry.registerBlock(block, itemclass, uniqueName);
    }

    public int registerBlockHandler(ISimpleBlockRenderingHandler handler)
    {
        return 0;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void registerEntity(Class entityClass, String entityName, Object mod, int entityID, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void registerEntityID(Class entityClass, String entityName, int entityID)
    {
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
    }

    public void registerEventHandler(Object target)
    {
        MinecraftForge.EVENT_BUS.register(target);
    }

    public void registerFuelHandler(IFuelHandler fuelHandler)
    {
        GameRegistry.registerFuelHandler(checkNotNull(fuelHandler));
    }
    
    public void registerItem(Item item, String name) {
    	GameRegistry.registerItem(item, name, Reference.MOD_ID);
    	
    }

    @Deprecated
    public void registerOre(int id, Block ore)
    {
        OreDictionary.registerOre(id, new ItemStack(ore));
    }

    @Deprecated
    public void registerOre(int id, Item ore)
    {
        OreDictionary.registerOre(id, new ItemStack(ore));
    }

    @Deprecated
    public void registerOre(int id, ItemStack ore)
    {
        OreDictionary.registerOre(id, ore);
    }

    public void registerOre(String name, Block ore)
    {
        OreDictionary.registerOre(name, new ItemStack(ore));
    }

    public void registerOre(String name, Item ore)
    {
        OreDictionary.registerOre(name, new ItemStack(ore));
    }

    public void registerOre(String name, ItemStack ore)
    {
        OreDictionary.registerOre(name, ore);
    }

    public void registerOreInAllSubblocks(String name, Block ore)
    {
        OreDictionary.registerOre(name, new ItemStack(ore, 1, Short.MAX_VALUE));
    }

    public void registerRenderInformation()
    {}

    public void registerScarecrowRendering()
    {}

    public void registerWorldGenerator(IWorldGenerator worldGenerator)
    {
    	// TODO: check correct weight
        GameRegistry.registerWorldGenerator(worldGenerator, 50);
    }
    
    public void removeBiome(BiomeGenBase biome)
    {
        if (biome != null)
        {
        	LogHelper.severe("REMOVING BIOMES NOT IMPLEMENTED IN DEV BUILD, TODO!");
        	/*LogHelper.fine("Removing biome %s",biome.toString());
            WorldType.DEFAULT.removeBiome(checkNotNull(biome));
            WorldType.LARGE_BIOMES.removeBiome(biome);*/
        }
        else
        {
        	LogHelper.warning("Request to remove null biome");
        }
    }

    public void setBlockHarvestLevel(Block block, String toolClass, int harvestLevel)
    {
        block.setHarvestLevel(toolClass, harvestLevel);
    }

    public void setFireInfo(Block block, int encouragement, int flammability)
    {
        Blocks.fire.setFireInfo(block, encouragement, flammability);
    }

}
