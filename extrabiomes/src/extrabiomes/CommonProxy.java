/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import static com.google.common.base.Preconditions.checkNotNull;
import net.minecraft.src.Achievement;
import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.CraftingManager;
import net.minecraft.src.IRecipe;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.WorldType;
import net.minecraftforge.common.MinecraftForge;

import com.google.common.base.Optional;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CommonProxy {

	public static void addGrassPlant(Block block, int metadata,
			int weight)
	{
		MinecraftForge.addGrassPlant(block, metadata, weight);
	}

	public void addAchievementDesc(Achievement achievement,
			String name, String description)
	{}

	public void addBiome(BiomeGenBase biome) {
		WorldType.DEFAULT.addNewBiome(checkNotNull(biome));
		WorldType.LARGE_BIOMES.addNewBiome(biome);
	}

	public void addName(Object object, String name) {
		LanguageRegistry.instance();
		LanguageRegistry.addName(object, name);
	}

	public void addRecipe(IRecipe recipe) {
		CraftingManager.getInstance().getRecipeList().add(recipe);
	}

	/**
	 * @see GameRegistry#registerBlock(Block) for details.
	 */
	public void registerBlock(Optional<Block> block) {
		if (block.isPresent()) GameRegistry.registerBlock(block.get());
	}

	/**
	 * @see GameRegistry#registerBlock(Block, Class) for details.
	 */
	public void registerBlock(Optional<Block> block,
			Class<? extends ItemBlock> itemclass)
	{
		if (block.isPresent())
			GameRegistry.registerBlock(block.get(),
					checkNotNull(itemclass));
	}

	public void registerFuelHandler(FuelHandler fuelHandler) {
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
