package extrabiomes.config;

import java.util.Collection;

import net.minecraft.src.Block;
import extrabiomes.api.BiomeDecorationsManager;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.IBiomeDecoration;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.terrain.BiomeDecoration;
import extrabiomes.terrain.TreeFactory;
import extrabiomes.terrain.WorldGenChunkCatTail;
import extrabiomes.terrain.WorldGenChunkCustomFlower;
import extrabiomes.terrain.WorldGenChunkGrass;
import extrabiomes.terrain.WorldGenChunkLeafPile;
import extrabiomes.terrain.WorldGenChunkOasis;

public class ConfigureTerrainGen {

	private static void configureAutumnWoodsDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.autumnWoods);
		if (ExtrabiomesBlock.flower != null) {
			decorations.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(
							BlockCustomFlower.metaToadstool)));
			decorations.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(
							BlockCustomFlower.metaAutumnShrub)));
		}
	}

	private static void configureGreenHillsDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.greenHills);
		if (ExtrabiomesBlock.flower != null) {
			decorations.add(new BiomeDecoration(new WorldGenChunkCustomFlower(
					BlockCustomFlower.metaOrange)));
			decorations.add(new BiomeDecoration(new WorldGenChunkCustomFlower(
					BlockCustomFlower.metaWhite)));
		}
	}

	private static void configureGreenSwampDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.greenSwamp);
		if (ExtrabiomesBlock.flower != null) {
			decorations.add(new BiomeDecoration(new WorldGenChunkCustomFlower(
					BlockCustomFlower.metaHydrangea)));
			decorations.add(new BiomeDecoration(15,
					new WorldGenChunkCustomFlower(BlockCustomFlower.metaRoot)));
		}
		if (ExtrabiomesBlock.catTail != null)
			decorations
					.add(new BiomeDecoration(999, new WorldGenChunkCatTail()));
		if (ExtrabiomesBlock.leafPile != null)
			decorations
					.add(new BiomeDecoration(10, new WorldGenChunkLeafPile()));
	}

	private static void configureMountainRidgeDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.mountainRidge);
		if (ExtrabiomesBlock.flower != null)
			decorations.add(new BiomeDecoration(10,
					new WorldGenChunkCustomFlower(
							BlockCustomFlower.metaTinyCactus)));
		if (ExtrabiomesBlock.grass != null) {
			decorations.add(new BiomeDecoration(100, new WorldGenChunkGrass(
					BlockCustomTallGrass.metaBrown)));
			decorations.add(new BiomeDecoration(100, new WorldGenChunkGrass(
					BlockCustomTallGrass.metaShortBrown)));
		}
		if (ExtrabiomesBlock.leafPile != null) {
			decorations
					.add(new BiomeDecoration(10, new WorldGenChunkLeafPile()));
		}
		decorations.add(new BiomeDecoration(999, new WorldGenChunkOasis()));
	}

	private static void configureRedwoodLushDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.redwoodLush);
		if (ExtrabiomesBlock.flower != null)
			decorations.add(new BiomeDecoration(15,
					new WorldGenChunkCustomFlower(BlockCustomFlower.metaRoot)));
		if (ExtrabiomesBlock.leafPile != null)
			decorations
					.add(new BiomeDecoration(15, new WorldGenChunkLeafPile()));
	}

	private static void configureSavannaDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.savanna);
		if (ExtrabiomesBlock.flower != null)
			decorations.add(new BiomeDecoration(new WorldGenChunkCustomFlower(
					BlockCustomFlower.metaPurple)));
	}

	private static void configureSnowRainforestDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.snowRainForest);
		if (ExtrabiomesBlock.flower != null)
			decorations.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(
							BlockCustomFlower.metaToadstool)));
	}

	private static void configureTemperateRainforestDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.temperateRainForest);
		if (ExtrabiomesBlock.flower != null)
			decorations.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(
							BlockCustomFlower.metaToadstool)));
	}

	private static void configureTundraDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.tundra);
		if (ExtrabiomesBlock.flower != null)
			decorations.add(new BiomeDecoration(2,
					new WorldGenChunkCustomFlower(
							BlockCustomFlower.metaToadstool)));
	}

	private static void configureWastelandDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.wasteland);
		if (ExtrabiomesBlock.grass != null) {
			decorations.add(new BiomeDecoration(9, new WorldGenChunkGrass(
					BlockCustomTallGrass.metaDead)));
			decorations.add(new BiomeDecoration(9, new WorldGenChunkGrass(
					BlockCustomTallGrass.metaDeadYellow)));
			decorations.add(new BiomeDecoration(7, new WorldGenChunkGrass(
					BlockCustomTallGrass.metaDeadTall)));
		}
	}

	private static void configureWoodlandsDecorations() {
		Collection<IBiomeDecoration> decorations = BiomeDecorationsManager.biomeDecorations
				.get(CustomBiomeManager.woodlands);
		if (ExtrabiomesBlock.leafPile != null)
			decorations
					.add(new BiomeDecoration(30, new WorldGenChunkLeafPile()));
	}

	public static void initialize() {
		TerrainGenManager.treeFactory = new TreeFactory();
		TerrainGenManager.woodBlockIDs.add(Integer.valueOf(Block.wood.blockID));
		TerrainGenManager.leafBlockIDs.add(Integer
				.valueOf(Block.leaves.blockID));

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

}
