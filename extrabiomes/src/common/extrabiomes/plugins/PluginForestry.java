
package extrabiomes.plugins;

import java.util.Collection;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.IPlugin;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.biomes.ExtrabiomeGenBase;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.plugins.forestry.CropProviderSapling;
import extrabiomes.plugins.forestry.WorldGenChunkBogEarth;
import extrabiomes.terrain.BiomeDecoration;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.ForestryBlock;
import forestry.api.core.GlobalManager;
import forestry.api.core.ItemInterface;
import forestry.api.cultivation.CropProviders;
import forestry.api.liquids.LiquidStack;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;

public enum PluginForestry implements IPlugin {
	INSTANCE;

	private static final int	DIGGER		= 1;
	private static final int	FORESTER	= 2;
	private static final int	ADVENTURER	= 4;
	private static ItemStack	liquidJuice;
	private static ItemStack	liquidHoney;
	private static ItemStack	liquidBiomass;

	private static void addBackpackItems() {
		if (ExtrabiomesBlock.crackedSand != null)
			BackpackManager.backpackItems[DIGGER].add(new ItemStack(
					ExtrabiomesBlock.crackedSand));
		if (ExtrabiomesBlock.redRock != null)
			BackpackManager.backpackItems[DIGGER].add(new ItemStack(
					ExtrabiomesBlock.redRock));

		if (ExtrabiomesBlock.quickSand != null)
			BackpackManager.backpackItems[ADVENTURER]
					.add(new ItemStack(ExtrabiomesBlock.quickSand));

		if (ExtrabiomesBlock.autumnLeaves != null) {
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1,
					BlockAutumnLeaves.metaBrown));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1,
					BlockAutumnLeaves.metaOrange));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1,
					BlockAutumnLeaves.metaPurple));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1,
					BlockAutumnLeaves.metaYellow));
		}
		if (ExtrabiomesBlock.greenLeaves != null) {
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.greenLeaves, 1,
					BlockGreenLeaves.metaFir));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.greenLeaves, 1,
					BlockGreenLeaves.metaRedwood));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.greenLeaves, 1,
					BlockGreenLeaves.metaAcacia));
		}

		if (ExtrabiomesBlock.sapling != null) {
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaBrown));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaOrange));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaPurple));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaYellow));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaFir));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaRedWood));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaAcacia));
		}

		if (ExtrabiomesBlock.flower != null) {
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaAutumnShrub));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaHydrangea));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaOrange));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaPurple));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaRoot));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaTinyCactus));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaToadstool));
			BackpackManager.backpackItems[FORESTER].add(new ItemStack(
					ExtrabiomesBlock.flower, 1,
					BlockCustomFlower.metaWhite));
		}
	}

	private static void addBasicFlowers() {
		if (ExtrabiomesBlock.flower == null) return;

		FlowerManager.plainFlowers.add(new ItemStack(
				ExtrabiomesBlock.flower, 1,
				BlockCustomFlower.metaHydrangea));
		FlowerManager.plainFlowers.add(new ItemStack(
				ExtrabiomesBlock.flower, 1,
				BlockCustomFlower.metaOrange));
		FlowerManager.plainFlowers.add(new ItemStack(
				ExtrabiomesBlock.flower, 1,
				BlockCustomFlower.metaPurple));
		FlowerManager.plainFlowers
				.add(new ItemStack(ExtrabiomesBlock.flower, 1,
						BlockCustomFlower.metaWhite));
	}

	private static void addBogEarthToGreenSwampBiome() {
		final BiomeDecoration bogEarth = new BiomeDecoration(1,
				new WorldGenChunkBogEarth());
		final Collection<IBiomeDecoration> greenSwampDecorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.greenSwamp);
		greenSwampDecorations.add(bogEarth);
	}

	private static void addClimates() {
		for (final BiomeGenBase biome : ExtrabiomeGenBase.customBiomeList)
		{
			if (biome == null) continue;
			if (biome.temperature >= 2.0F)
				EnumTemperature.hotBiomeIds.add(Integer
						.valueOf(biome.biomeID));
			else if (biome.temperature >= 1.2F)
				EnumTemperature.warmBiomeIds.add(Integer
						.valueOf(biome.biomeID));
			else if (biome.temperature >= 0.2F)
				EnumTemperature.normalBiomeIds.add(Integer
						.valueOf(biome.biomeID));
			else if (biome.temperature >= 0.15F)
				EnumTemperature.coldBiomeIds.add(Integer
						.valueOf(biome.biomeID));
			else
				EnumTemperature.icyBiomeIds.add(Integer
						.valueOf(biome.biomeID));

			if (biome.rainfall >= 0.9F)
				EnumHumidity.dampBiomeIds.add(Integer
						.valueOf(biome.biomeID));
			else if (biome.rainfall >= 0.3F)
				EnumHumidity.normalBiomeIds.add(Integer
						.valueOf(biome.biomeID));
			else
				EnumHumidity.aridBiomeIds.add(Integer
						.valueOf(biome.biomeID));
		}
	}

	private static void addFermenterRecipeSapling(ItemStack resource) {
		RecipeManagers.fermenterManager.addRecipe(
				resource,
				800,
				1.0f,
				new LiquidStack(liquidBiomass.itemID, 1, liquidBiomass
						.getItemDamage()), new LiquidStack(
						Block.waterStill, 1));
		RecipeManagers.fermenterManager.addRecipe(
				resource,
				800,
				1.5f,
				new LiquidStack(liquidBiomass.itemID, 1, liquidBiomass
						.getItemDamage()),
				new LiquidStack(liquidJuice.itemID, 1, liquidJuice
						.getItemDamage()));
		RecipeManagers.fermenterManager.addRecipe(
				resource,
				800,
				1.5f,
				new LiquidStack(liquidBiomass.itemID, 1, liquidBiomass
						.getItemDamage()),
				new LiquidStack(liquidHoney.itemID, 1, liquidHoney
						.getItemDamage()));

	}

	private static void addGlobals() {
		if (ExtrabiomesBlock.autumnLeaves != null)
			GlobalManager.leafBlockIds
					.add(ExtrabiomesBlock.autumnLeaves.blockID);
		if (ExtrabiomesBlock.greenLeaves != null)
			GlobalManager.leafBlockIds
					.add(ExtrabiomesBlock.greenLeaves.blockID);
	}

	private static void addRecipes() {
		if (RecipeManagers.fermenterManager == null) return;
		liquidJuice = ItemInterface.getItem("liquidJuice");
		liquidHoney = ItemInterface.getItem("liquidHoney");
		liquidBiomass = ItemInterface.getItem("liquidBiomass");
		if (ExtrabiomesBlock.sapling != null) {
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaBrown));
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaOrange));
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaPurple));
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaYellow));
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaFir));
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaRedWood));
			addFermenterRecipeSapling(new ItemStack(
					ExtrabiomesBlock.sapling, 1,
					BlockCustomSapling.metaAcacia));
		}
	}

	private static void addSaplings() {
		if (ExtrabiomesBlock.sapling == null) return;

		if (ForestryBlock.soil != null) {
			BlockCustomSapling.forestryHumusID = ForestryBlock.soil.blockID;
			BlockCustomSapling.forestryHumusMeta = 0;
		}

		CropProviders.arborealCrops.add(new CropProviderSapling());
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(ForestryBlock.soil.blockID));
	}

	@Override
	public String getName() {
		return "Forestry";
	}

	@Override
	public void inject() {
		addBogEarthToGreenSwampBiome();
		addSaplings();
		addBasicFlowers();
		addClimates();
		addGlobals();
		addBackpackItems();
		addRecipes();
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_Forestry");
	}

}
