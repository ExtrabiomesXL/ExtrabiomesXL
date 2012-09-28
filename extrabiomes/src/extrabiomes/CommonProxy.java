/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import static com.google.common.base.Preconditions.checkNotNull;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.IRecipe;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.WorldType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.base.Optional;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {

	public void addBiome(BiomeGenBase biome) {
		WorldType.DEFAULT.addNewBiome(checkNotNull(biome));
		WorldType.LARGE_BIOMES.addNewBiome(biome);
	}

	public void addGrassPlant(Block block, int metadata, int weight) {
		MinecraftForge.addGrassPlant(block, metadata, weight);
	}

	public void addName(Object object, String name) {
		LanguageRegistry.instance();
		LanguageRegistry.addName(object, name);
	}

	public void addRecipe(IRecipe recipe) {
		CraftingManager.getInstance().getRecipeList().add(recipe);
	}

	public Optional<ItemStack> getGrassSeed(World world) {
		return Optional.fromNullable(ForgeHooks.getGrassSeed(world));
	}

	public boolean isModLoaded(String modID) {
		return Loader.isModLoaded(modID);
	}

	public void registerBlock(Optional<? extends Block> block,
			Class<? extends ItemBlock> itemclass)
	{
		if (block.isPresent())
			GameRegistry.registerBlock(block.get(), itemclass);
	}

	public void registerBlock(Optional<Block> block) {
		if (block.isPresent()) GameRegistry.registerBlock(block.get());
	}

	public int registerBlockHandler(ISimpleBlockRenderingHandler handler)
	{
		return 0;
	}

	public void registerEventHandler(Object target) {
		MinecraftForge.EVENT_BUS.register(target);
	}

	public void registerFuelHandler(IFuelHandler fuelHandler) {
		GameRegistry.registerFuelHandler(checkNotNull(fuelHandler));
	}

	public void registerRenderInformation() {}

	public void registerWorldGenerator(IWorldGenerator worldGenerator) {
		GameRegistry.registerWorldGenerator(worldGenerator);
	}

	public void removeBiome(BiomeGenBase biome) {
		WorldType.DEFAULT.removeBiome(checkNotNull(biome));
		WorldType.LARGE_BIOMES.removeBiome(biome);
	}

	/**
	 * @see MinecraftForge#setBlockHarvestLevel(Block, String, int) for
	 *      details.
	 */
	public void setBlockHarvestLevel(Block block, String toolClass,
			int harvestLevel)
	{
		MinecraftForge.setBlockHarvestLevel(block, toolClass,
				harvestLevel);
	}

}
