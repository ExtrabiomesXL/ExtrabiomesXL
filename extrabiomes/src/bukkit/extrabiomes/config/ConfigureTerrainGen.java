
package extrabiomes.config;

import java.util.Collection;

import net.minecraft.server.Block;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.terrain.BiomeDecoration;
import extrabiomes.terrain.TreeFactory;
import extrabiomes.terrain.WorldGenChunkCatTail;
import extrabiomes.terrain.WorldGenChunkCustomFlower;
import extrabiomes.terrain.WorldGenChunkGrass;
import extrabiomes.terrain.WorldGenChunkLeafPile;
import extrabiomes.terrain.WorldGenChunkOasis;

public class ConfigureTerrainGen {
	private static void configureAutumnWoodsDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.autumnWoods);

		if (ExtrabiomesBlock.flower != null) {
			collection.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(6)));
			collection.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(0)));
		}
	}

	private static void configureGreenHillsDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.greenHills);

		if (ExtrabiomesBlock.flower != null) {
			collection.add(new BiomeDecoration(
					new WorldGenChunkCustomFlower(2)));
			collection.add(new BiomeDecoration(
					new WorldGenChunkCustomFlower(7)));
		}
	}

	private static void configureGreenSwampDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.greenSwamp);

		if (ExtrabiomesBlock.flower != null) {
			collection.add(new BiomeDecoration(
					new WorldGenChunkCustomFlower(1)));
			collection.add(new BiomeDecoration(15,
					new WorldGenChunkCustomFlower(5)));
		}

		if (ExtrabiomesBlock.catTail != null)
			collection.add(new BiomeDecoration(999,
					new WorldGenChunkCatTail()));

		if (ExtrabiomesBlock.leafPile != null)
			collection.add(new BiomeDecoration(10,
					new WorldGenChunkLeafPile()));
	}

	private static void configureMountainRidgeDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.mountainRidge);

		if (ExtrabiomesBlock.flower != null)
			collection.add(new BiomeDecoration(10,
					new WorldGenChunkCustomFlower(4)));

		if (ExtrabiomesBlock.grass != null) {
			collection.add(new BiomeDecoration(100,
					new WorldGenChunkGrass(0)));
			collection.add(new BiomeDecoration(100,
					new WorldGenChunkGrass(1)));
		}

		if (ExtrabiomesBlock.leafPile != null)
			collection.add(new BiomeDecoration(10,
					new WorldGenChunkLeafPile()));

		collection.add(new BiomeDecoration(999,
				new WorldGenChunkOasis()));
	}

	private static void configureRedwoodLushDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.redwoodLush);

		if (ExtrabiomesBlock.flower != null)
			collection.add(new BiomeDecoration(15,
					new WorldGenChunkCustomFlower(5)));

		if (ExtrabiomesBlock.leafPile != null)
			collection.add(new BiomeDecoration(15,
					new WorldGenChunkLeafPile()));
	}

	private static void configureSavannaDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.savanna);

		if (ExtrabiomesBlock.flower != null)
			collection.add(new BiomeDecoration(
					new WorldGenChunkCustomFlower(3)));
	}

	private static void configureSnowRainforestDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.snowRainForest);

		if (ExtrabiomesBlock.flower != null)
			collection.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(6)));
	}

	private static void configureTemperateRainforestDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.temperateRainForest);

		if (ExtrabiomesBlock.flower != null)
			collection.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(6)));
	}

	private static void configureTrees() {
		TerrainGenManager.treeFactory = new TreeFactory();
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.GRASS.id));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.DIRT.id));
		TerrainGenManager.treesCanGrowOnIDs.add(Integer
				.valueOf(Block.SOIL.id));
	}

	private static void configureTundraDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.tundra);

		if (ExtrabiomesBlock.flower != null)
			collection.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(6)));
	}

	private static void configureWastelandDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.wasteland);

		if (ExtrabiomesBlock.grass != null) {
			collection.add(new BiomeDecoration(9,
					new WorldGenChunkGrass(2)));
			collection.add(new BiomeDecoration(9,
					new WorldGenChunkGrass(4)));
			collection.add(new BiomeDecoration(7,
					new WorldGenChunkGrass(3)));
		}
	}

	private static void configureWoodlandsDecorations() {
		final Collection collection = (Collection) BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.woodlands);

		if (ExtrabiomesBlock.leafPile != null)
			collection.add(new BiomeDecoration(30,
					new WorldGenChunkLeafPile()));
	}

	public static void initialize() {
		configureTrees();
		configureAutumnWoodsDecorations();
		configureGreenHillsDecorations();
		configureGreenSwampDecorations();
		configureMountainRidgeDecorations();
		configureRedwoodLushDecorations();
		configureSavannaDecorations();
		configureSnowRainforestDecorations();
		configureTemperateRainforestDecorations();
		configureTundraDecorations();
		configureWastelandDecorations();
		configureWoodlandsDecorations();
	}

	public ConfigureTerrainGen() {}
}
