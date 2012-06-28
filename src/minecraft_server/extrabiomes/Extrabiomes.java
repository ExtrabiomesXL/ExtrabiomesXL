package extrabiomes;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;
import net.minecraft.src.forge.NetworkMod;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.api.IPlugin;
import extrabiomes.api.PluginManager;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.config.AchievementManager;
import extrabiomes.config.Config;
import extrabiomes.config.ConfigureCustomBiomes;
import extrabiomes.config.ConfigureVanillaBiomes;
import extrabiomes.terrain.TerrainGenerator;

public class Extrabiomes {

	private static final String NAME = "ExtraBiomes XL";
	private static final String PRIORITIES = "";
	private static final String VERSION = "2.2.3";

	public static int addFuel(int id, int damage) {
		if (id == ExtrabiomesBlock.sapling.blockID)
			return 100;
		return 0;
	}

	public static boolean clientSideRequired() {
		// Don't require client unless custom blocks are defined
		return ExtrabiomesBlock.autumnLeaves != null
				|| ExtrabiomesBlock.catTail != null
				|| ExtrabiomesBlock.crackedSand != null
				|| ExtrabiomesBlock.flower != null
				|| ExtrabiomesBlock.grass != null
				|| ExtrabiomesBlock.greenLeaves != null
				|| ExtrabiomesBlock.leafPile != null
				|| ExtrabiomesBlock.quickSand != null
				|| ExtrabiomesBlock.redRock != null
				|| ExtrabiomesBlock.sapling != null;
	}

	public static void generateSurface(World world, Random random, int x, int z) {
		TerrainGenerator.generateSurface(world, random, x, z);
	}

	public static String getName() {
		return NAME;
	}

	public static String getPriorities() {
		return PRIORITIES;
	}

	public static String getVersion() {
		return VERSION;
	}

	private static void injectPlugins() {
		for (IPlugin plugin : PluginManager.plugins)
			if (plugin != null && plugin.isEnabled()) {
				Log.write("Injecting the " + plugin.getName()
						+ " plugin into ExtrabiomesXL.");
				plugin.inject();
			}
	}

	public static void load() {
		Config.load();
	}

	public static void modsLoaded(NetworkMod mod) {
		Config.modsLoaded();

		if (ExtrabiomesItem.scarecrow != null) {
			Proxy.addRecipe(new ItemStack(ExtrabiomesItem.scarecrow, 1),
					new Object[] { " a ", "cbc", " c ", Character.valueOf('a'),
							Block.pumpkin, Character.valueOf('b'), Block.melon,
							Character.valueOf('c'), Item.stick });
		}

		if (ExtrabiomesBlock.redRock != null)
			Proxy.addShapelessRecipe(new ItemStack(Item.clay, 4), new Object[] {
					new ItemStack(ExtrabiomesBlock.redRock),
					new ItemStack(Item.bucketWater),
					new ItemStack(Item.bucketWater),
					new ItemStack(Item.bucketWater) });

		if (ExtrabiomesBlock.crackedSand != null)
			Proxy.addShapelessRecipe(new ItemStack(Block.sand), new Object[] {
					new ItemStack(ExtrabiomesBlock.crackedSand),
					new ItemStack(Item.bucketWater) });

		if (ExtrabiomesBlock.flower != null) {
			ModLoader.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 12),
					new Object[] { new ItemStack(ExtrabiomesBlock.flower, 1,
							BlockCustomFlower.metaHydrangea) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 14),
					new Object[] { new ItemStack(ExtrabiomesBlock.flower, 1,
							BlockCustomFlower.metaOrange) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 13),
					new Object[] { new ItemStack(ExtrabiomesBlock.flower, 1,
							BlockCustomFlower.metaPurple) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.dyePowder, 1, 7),
					new Object[] { new ItemStack(ExtrabiomesBlock.flower, 1,
							BlockCustomFlower.metaWhite) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.bowlSoup),
					new Object[] {
							Block.mushroomBrown,
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							Item.bowlEmpty });
			ModLoader.addShapelessRecipe(new ItemStack(Item.bowlSoup),
					new Object[] {
							Block.mushroomRed,
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							new ItemStack(ExtrabiomesBlock.flower, 1,
									BlockCustomFlower.metaToadstool),
							Item.bowlEmpty });
		}

		Config.addNames();

		if (ExtrabiomesItem.scarecrow != null) {
			ExtrabiomesEntity.scarecrow = 127;
			Proxy.registerEntityID(EntityScarecrow.class, "scarecrow",
					ExtrabiomesEntity.scarecrow);
			Proxy.registerEntity(EntityScarecrow.class, mod,
					ExtrabiomesEntity.scarecrow);
		}

		ConfigureVanillaBiomes.disableVanillaBiomes();

		if (TerrainGenManager.blockWasteland != null)
			CustomBiomeManager.wasteland.topBlock = CustomBiomeManager.wasteland.fillerBlock = (byte) TerrainGenManager.blockWasteland.blockID;
		if (TerrainGenManager.blockWasteland != null)
			CustomBiomeManager.mountainRidge.topBlock = CustomBiomeManager.mountainRidge.fillerBlock = (byte) TerrainGenManager.blockMountainRidge.blockID;

		ConfigureCustomBiomes.enableCustomBiomes();

		injectPlugins();
	}

	public static void takenFromCrafting(EntityPlayer player,
			ItemStack itemstack, IInventory var3) {
		AchievementManager.craftingAchievement(player, itemstack);
	}
}
