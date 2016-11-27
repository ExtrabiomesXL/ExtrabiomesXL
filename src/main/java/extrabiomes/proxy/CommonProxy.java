/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.proxy;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy
{

    public void addBiome(Collection<WorldType> worldTypes, Biome biome)
    {
    	// TODO
        /*for (final WorldType worldType : worldTypes)
            worldType.addNewBiome(biome);*/
    }

    public void addGrassPlant(IBlockState state, int weight, Biome biome)
    {
    	if(biome != null) biome.addFlower(state, weight);
    }
    public void addGrassPlant(IBlockState state, int weight)
    {
        for(Biome biome: Biome.REGISTRY) {
            addGrassPlant(state, weight, biome);
        }
    }

    public void addRecipe(IRecipe recipe)
    {
    	GameRegistry.addRecipe(recipe);
    }

    public void addSmelting(Item item, int metadata, ItemStack itemstack, float experience)
    {
    	addSmelting(new ItemStack(item, 1, metadata), itemstack, experience);
    }

    public void addSmelting(ItemStack input, ItemStack output, float experience)
    {
    	GameRegistry.addSmelting(input, output, experience);
    }

    @Deprecated
    public int findGlobalUniqueEntityId()
    {
    	throw new RuntimeException("Dead");
    }

    public Logger getFMLLogger()
    {
        return FMLLog.getLogger();
    }

    @Deprecated
    public Optional<ItemStack> getGrassSeed(World world)
    {
    	throw new RuntimeException("Dead");
        //return Optional.fromNullable(ForgeHooks.getGrassSeed(world));
    }

    public List<ItemStack> getOres(String name)
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

    public void registerItem(Item item, String name) {
    	GameRegistry.register(item, new ResourceLocation(Reference.MOD_ID, name));
    }
    
    public void registerBlock(Block block, String uniqueName)
    {
        registerBlock(block, ItemBlock.class, uniqueName);
    }

    public void registerBlock(Block block, Class<? extends ItemBlock> itemclass, String uniqueName) //As nicked from GameRegistry
    {
    	try
        {
            assert block != null : "registerBlock: block cannot be null";
            
            if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(uniqueName))
                throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + uniqueName);
            
            ItemBlock itemBlock = null;
            
            if (itemclass != null)
                itemBlock = itemclass.getConstructor(Block.class).newInstance(block);
            
            GameRegistry.register(block.getRegistryName() == null ? block.setRegistryName(uniqueName) : block);
            
            if (itemBlock != null)
            	registerItem(itemBlock, uniqueName);
        }
    	catch (Exception e)
        {
            LogHelper.severe("Fatal error registering block!");
            throw new RuntimeException("Could not register block!", e); //Splat
        }
    }

    public void registerEntity(Class<? extends Entity> entityClass, String entityName, int entityID, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID, Extrabiomes.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public void registerEventHandler(Object target)
    {
        MinecraftForge.EVENT_BUS.register(target);
    }

    public void registerFuelHandler(IFuelHandler fuelHandler)
    {
        GameRegistry.registerFuelHandler(checkNotNull(fuelHandler));
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
    
    public void removeBiome(Biome biome)
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
        Blocks.FIRE.setFireInfo(block, encouragement, flammability);
    }
    
    public void onPostInit() {
    }

    @SuppressWarnings("deprecation")
	public String translate(String key) {
    	return net.minecraft.util.text.translation.I18n.translateToLocal(key);
    }
}
