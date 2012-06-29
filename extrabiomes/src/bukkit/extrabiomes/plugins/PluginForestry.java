
package extrabiomes.plugins;

import java.util.Collection;
import java.util.Iterator;

import net.minecraft.server.BiomeBase;
import net.minecraft.server.ModLoader;
import net.minecraft.server.ItemStack;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.IPlugin;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.biomes.ExtrabiomeGenBase;
import extrabiomes.plugins.forestry.CropProviderSapling;
import extrabiomes.plugins.forestry.WorldGenChunkBogEarth;
import extrabiomes.terrain.BiomeDecoration;
import forestry.api.apiculture.FlowerManager;
import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.core.ForestryBlock;
import forestry.api.core.GlobalManager;
import forestry.api.cultivation.CropProviders;
import forestry.api.storage.BackpackManager;

public enum PluginForestry implements IPlugin {
	INSTANCE;

	private static void addBackpackItems() {
		if (ExtrabiomesBlock.crackedSand != null)
			BackpackManager.backpackItems[1].add(new ItemStack(
					ExtrabiomesBlock.crackedSand));

		if (ExtrabiomesBlock.redRock != null)
			BackpackManager.backpackItems[1].add(new ItemStack(
					ExtrabiomesBlock.redRock));

		if (ExtrabiomesBlock.quickSand != null)
			BackpackManager.backpackItems[4].add(new ItemStack(
					ExtrabiomesBlock.quickSand));

		if (ExtrabiomesBlock.autumnLeaves != null) {
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1, 0));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1, 1));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1, 2));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.autumnLeaves, 1, 3));
		}

		if (ExtrabiomesBlock.greenLeaves != null) {
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.greenLeaves, 1, 0));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.greenLeaves, 1, 1));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.greenLeaves, 1, 2));
		}

		if (ExtrabiomesBlock.sapling != null) {
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 0));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 1));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 2));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 3));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 4));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 5));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.sapling, 1, 6));
		}

		if (ExtrabiomesBlock.flower != null) {
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 0));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 1));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 2));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 3));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 5));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 4));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 6));
			BackpackManager.backpackItems[2].add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 7));
		}
	}

	private static void addBasicFlowers() {
		if (ExtrabiomesBlock.flower == null)
			return;
		else {
			FlowerManager.plainFlowers.add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 1));
			FlowerManager.plainFlowers.add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 2));
			FlowerManager.plainFlowers.add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 3));
			FlowerManager.plainFlowers.add(new ItemStack(
					ExtrabiomesBlock.flower, 1, 7));
			return;
		}
	}

	private static void addBogEarthToGreenSwampBiome() {
		final BiomeDecoration biomedecoration = new BiomeDecoration(1,
				new WorldGenChunkBogEarth());
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.greenSwamp);
		collection.add(biomedecoration);
	}

	private static void addClimates() {
		final Iterator iterator = ExtrabiomeGenBase.customBiomeList
				.iterator();

		do {
			if (!iterator.hasNext()) break;

			final BiomeBase biomebase = (BiomeBase) iterator.next();

			if (biomebase != null) {
				if (biomebase.F >= 2.0F)
					EnumTemperature.hotBiomeIds.add(Integer
							.valueOf(biomebase.id));
				else if (biomebase.F >= 1.2F)
					EnumTemperature.warmBiomeIds.add(Integer
							.valueOf(biomebase.id));
				else if (biomebase.F >= 0.2F)
					EnumTemperature.normalBiomeIds.add(Integer
							.valueOf(biomebase.id));
				else if (biomebase.F >= 0.15F)
					EnumTemperature.coldBiomeIds.add(Integer
							.valueOf(biomebase.id));
				else
					EnumTemperature.icyBiomeIds.add(Integer
							.valueOf(biomebase.id));

				if (biomebase.G >= 0.9F)
					EnumHumidity.dampBiomeIds.add(Integer
							.valueOf(biomebase.id));
				else if (biomebase.G >= 0.3F)
					EnumHumidity.normalBiomeIds.add(Integer
							.valueOf(biomebase.id));
				else
					EnumHumidity.aridBiomeIds.add(Integer
							.valueOf(biomebase.id));
			}
		} while (true);
	}

	private static void addGlobals() {
		if (ExtrabiomesBlock.autumnLeaves != null)
			GlobalManager.leafBlockIds.add(Integer
					.valueOf(ExtrabiomesBlock.autumnLeaves.id));

		if (ExtrabiomesBlock.greenLeaves != null)
			GlobalManager.leafBlockIds.add(Integer
					.valueOf(ExtrabiomesBlock.greenLeaves.id));
	}

	private static void addSaplings() {
		if (ExtrabiomesBlock.sapling == null)
			return;
		else {
			CropProviders.arborealCrops.add(new CropProviderSapling());
			TerrainGenManager.treesCanGrowOnIDs.add(Integer
					.valueOf(ForestryBlock.soil.id));
			return;
		}
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
	}

	@Override
	public boolean isEnabled() {
		return ModLoader.isModLoaded("mod_Forestry");
	}
}
