/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.proxy;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
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
import net.minecraftforge.event.Event;
import net.minecraftforge.oredict.OreDictionary;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {

    public void addBiome(Collection<WorldType> worldTypes, BiomeGenBase biome) {
        for (final WorldType worldType : worldTypes)
            worldType.addNewBiome(biome);
    }

    public void addGrassPlant(Block block, int metadata, int weight) {
        MinecraftForge.addGrassPlant(block, metadata, weight);
    }

    public void addName(Object object, String name) {
        LanguageRegistry.addName(object, name);
    }

    @SuppressWarnings("unchecked")
	public void addRecipe(IRecipe recipe) {
        CraftingManager.getInstance().getRecipeList().add(recipe);
    }

    public void addSmelting(int itemID, int metadata, ItemStack itemstack, float experience) {
        FurnaceRecipes.smelting().addSmelting(itemID, metadata, itemstack, experience);
    }

    public void addSmelting(ItemStack input, ItemStack output, float experience) {
        addSmelting(input.itemID, input.getItemDamage(), output, experience);
    }

    public void addStringLocalization(String key, String lang, String value) {
        LanguageRegistry.instance().addStringLocalization(key, lang, value);
    }

    public int findGlobalUniqueEntityId() {
        return EntityRegistry.findGlobalUniqueEntityId();
    }

    public Logger getFMLLogger() {
        return FMLLog.getLogger();
    }

    public Optional<ItemStack> getGrassSeed(World world) {
        return Optional.fromNullable(ForgeHooks.getGrassSeed(world));
    }

    public ArrayList<ItemStack> getOres(String name) {
        return OreDictionary.getOres(name);
    }

    public String getStringLocalization(String key) {
        return LanguageRegistry.instance().getStringLocalization(key);
    }

    public boolean isModLoaded(String modID) {
        return Loader.isModLoaded(modID);
    }

    public void loadLocalization(String filename, String locale) {
        LanguageRegistry.instance().loadLocalization(filename, locale, true);
    }

    public boolean postEventToBus(Event event) {
        return MinecraftForge.EVENT_BUS.post(event);
    }

    public void registerBlock(Block block) {
        GameRegistry.registerBlock(block);
    }

    public void registerBlock(Block block, Class<? extends ItemBlock> itemclass) {
        GameRegistry.registerBlock(block, itemclass);
    }

    public int registerBlockHandler(ISimpleBlockRenderingHandler handler) {
        return 0;
    }

    public void registerEntity(Class<? extends Entity> entityClass, String entityName, Object mod, int entityID,
            int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
    {
        EntityRegistry.registerModEntity(entityClass, entityName, entityID, mod, trackingRange,
                updateFrequency, sendsVelocityUpdates);
    }

    public void registerEntityID(Class<? extends Entity> entityClass, String entityName, int entityID) {
        EntityRegistry.registerGlobalEntityID(entityClass, entityName, entityID);
    }

    public void registerEventHandler(Object target) {
        MinecraftForge.EVENT_BUS.register(target);
    }

    public void registerFuelHandler(IFuelHandler fuelHandler) {
        GameRegistry.registerFuelHandler(checkNotNull(fuelHandler));
    }

    public void registerOre(int id, Block ore) {
        OreDictionary.registerOre(id, new ItemStack(ore));
    }

    public void registerOre(int id, Item ore) {
        OreDictionary.registerOre(id, new ItemStack(ore));
    }

    public void registerOre(int id, ItemStack ore) {
        OreDictionary.registerOre(id, ore);
    }

    public void registerOre(String name, Block ore) {
        OreDictionary.registerOre(name, new ItemStack(ore));
    }

    public void registerOre(String name, Item ore) {
        OreDictionary.registerOre(name, new ItemStack(ore));
    }

    public void registerOre(String name, ItemStack ore) {
        OreDictionary.registerOre(name, ore);
    }

    public void registerOreInAllSubblocks(String name, Block ore) {
        OreDictionary.registerOre(name, new ItemStack(ore, 1, -1));
    }

    public void registerRenderInformation() {}

    public void registerScarecrowRendering() {}

    public void registerWorldGenerator(IWorldGenerator worldGenerator) {
        GameRegistry.registerWorldGenerator(worldGenerator);
    }

    public void removeBiome(BiomeGenBase biome) {
        WorldType.DEFAULT.removeBiome(checkNotNull(biome));
        WorldType.LARGE_BIOMES.removeBiome(biome);
    }

    public void setBlockHarvestLevel(Block block, String toolClass, int harvestLevel) {
        MinecraftForge.setBlockHarvestLevel(block, toolClass, harvestLevel);
    }

    public void setBurnProperties(int id, int encouragement, int flammability) {
        Block.setBurnProperties(id, encouragement, flammability);
    }

}
