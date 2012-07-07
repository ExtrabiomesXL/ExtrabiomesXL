
package extrabiomes.config;

import net.minecraft.server.ItemStack;
import extrabiomes.Proxy;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCatTail;
import extrabiomes.blocks.BlockCrackedSand;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.blocks.BlockLeafPile;
import extrabiomes.blocks.BlockQuickSand;
import extrabiomes.blocks.BlockRedRock;
import forge.MinecraftForge;
import forge.Property;

public class ConfigureBlocks {
	public static boolean	crackedSandCanGrow						= true;
	public static boolean	crackedSandGrowthRestrictedToWasteland	= false;

	public static void addNames() {
		Proxy.addName(ExtrabiomesBlock.catTail, "Cat Tail");
		Proxy.addName(ExtrabiomesBlock.crackedSand, "Cracked Sand");
		Proxy.addName(ExtrabiomesBlock.leafPile, "Leaf Pile");
		Proxy.addName(ExtrabiomesBlock.quickSand, "Quick Sand");
		Proxy.addName(ExtrabiomesBlock.redRock, "Red Rock");

		if (ExtrabiomesBlock.autumnLeaves != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 0), "Brown Autumn Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 1), "Orange Autumn Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 2), "Purple Autumn Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.autumnLeaves,
					1, 3), "Yellow Autumn Leaves");
		}

		if (ExtrabiomesBlock.flower != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 0),
					"Autumn Shrub");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 1),
					"Hydrangea");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 2),
					"Orange Flower");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 3),
					"Purple Flower");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 4),
					"Tiny Cactus");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 5),
					"Root");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 6),
					"Toad Stool");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.flower, 1, 7),
					"White Flower");
		}

		if (ExtrabiomesBlock.grass != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 0),
					"Brown Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 1),
					"Short Brown Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 2),
					"Dead Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 3),
					"Tall Dead Grass");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.grass, 1, 4),
					"Yellow Dead Grass");
		}

		if (ExtrabiomesBlock.greenLeaves != null) {
			Proxy.addName(new ItemStack(ExtrabiomesBlock.greenLeaves,
					1, 0), "Fir Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.greenLeaves,
					1, 1), "Redwood Leaves");
			Proxy.addName(new ItemStack(ExtrabiomesBlock.greenLeaves,
					1, 2), "Acacia Leaves");
		}

		if (ExtrabiomesBlock.sapling != null) {
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 0),
					"Brown Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 1),
					"Orange Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 2),
					"Purple Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 3),
					"Yellow Autumn Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 4),
					"Fir Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 5),
					"Redwood Sapling");
			Proxy.addName(
					new ItemStack(ExtrabiomesBlock.sapling, 1, 6),
					"Acacia Sapling");
		}
	}

	public static void initialize() {
		if (Config.enableClassicMode) return;

		final int i = Config.getOrCreateIntProperty("crackedsand.id",
				"block", 150) & 0xff;

		if (i != 0) {
			crackedSandCanGrow = Config.getOrCreateBooleanProperty(
					"wasteland.enable.growth", "BIOME",
					crackedSandCanGrow);
			crackedSandGrowthRestrictedToWasteland = Config
					.getOrCreateBooleanProperty(
							"wasteland.restrict.growth.to.biome",
							"BIOME",
							crackedSandGrowthRestrictedToWasteland);
			ExtrabiomesBlock.crackedSand = new BlockCrackedSand(i)
					.a("crackedsand");
			MinecraftForge.setBlockHarvestLevel(
					ExtrabiomesBlock.crackedSand, "pickaxe", 0);
		}

		final Property property = Config.getProperty("crackedsand.id",
				"block", String.valueOf(i));
		property.value = String.valueOf(i);
		property.comment = "Due to a hole in the 4096 patch crackedsand.id must be set to a value less than 256.";
		final int j = Config.getOrCreateIntProperty("redrock.id",
				"block", 151) & 0xff;

		if (j != 0) {
			ExtrabiomesBlock.redRock = new BlockRedRock(j).a("redrock");
			MinecraftForge.setBlockHarvestLevel(
					ExtrabiomesBlock.redRock, "pickaxe", 0);
		}

		final Property property1 = Config.getProperty("redrock.id",
				"block", String.valueOf(j));
		property1.value = String.valueOf(j);
		property1.comment = "Due to a hole in the 4096 patch redrock.id must be set to a value less than 256.";
		final int k = Config.getOrCreateBlockIdProperty("quicksand.id",
				152);

		if (k != 0) {
			ExtrabiomesBlock.quickSand = new BlockQuickSand(k)
					.a("quicksand");
			MinecraftForge.setBlockHarvestLevel(
					ExtrabiomesBlock.quickSand, "shovel", 0);
		}

		final int l = Config.getOrCreateBlockIdProperty(
				"autumnleaves.id", 153);

		if (l != 0)
			ExtrabiomesBlock.autumnLeaves = new BlockAutumnLeaves(l)
					.a("autumnleaves");

		final int i1 = Config.getOrCreateBlockIdProperty(
				"greenleaves.id", 154);

		if (i1 != 0)
			ExtrabiomesBlock.greenLeaves = new BlockGreenLeaves(i1)
					.a("greenleaves");

		final int j1 = Config.getOrCreateBlockIdProperty("flower.id",
				155);

		if (j1 != 0)
			ExtrabiomesBlock.flower = new BlockCustomFlower(j1)
					.a("flower");

		final int k1 = Config.getOrCreateBlockIdProperty("grass.id",
				156);

		if (k1 != 0)
			ExtrabiomesBlock.grass = new BlockCustomTallGrass(k1)
					.a("grass");

		final int l1 = Config.getOrCreateBlockIdProperty("sapling.id",
				156);

		if (l1 != 0)
			ExtrabiomesBlock.sapling = new BlockCustomSapling(l1)
					.a("sapling");

		final int i2 = Config.getOrCreateBlockIdProperty("cattail.id",
				156);

		if (i2 != 0)
			ExtrabiomesBlock.catTail = new BlockCatTail(i2)
					.a("cattail");

		final int j2 = Config.getOrCreateBlockIdProperty("leafpile.id",
				156);

		if (j2 != 0)
			ExtrabiomesBlock.leafPile = new BlockLeafPile(j2)
					.a("leafpile");

		Proxy.registerBlock(ExtrabiomesBlock.crackedSand);
		Proxy.registerBlock(ExtrabiomesBlock.leafPile);
		Proxy.registerBlock(ExtrabiomesBlock.quickSand);
		Proxy.registerBlock(ExtrabiomesBlock.redRock);
		Proxy.registerBlock(ExtrabiomesBlock.autumnLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Proxy.registerBlock(ExtrabiomesBlock.catTail,
				extrabiomes.ItemCatTail.class);
		Proxy.registerBlock(ExtrabiomesBlock.flower,
				extrabiomes.MultiItemBlock.class);
		Proxy.registerBlock(ExtrabiomesBlock.grass,
				extrabiomes.MultiItemBlock.class);
		Proxy.registerBlock(ExtrabiomesBlock.greenLeaves,
				extrabiomes.ItemCustomLeaves.class);
		Proxy.registerBlock(ExtrabiomesBlock.sapling,
				extrabiomes.MultiItemBlock.class);
	}

	public ConfigureBlocks() {}
}
