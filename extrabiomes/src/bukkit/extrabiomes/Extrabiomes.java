
package extrabiomes;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.server.Block;
import net.minecraft.server.EntityHuman;
import net.minecraft.server.IInventory;
import net.minecraft.server.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ModLoader;
import net.minecraft.server.World;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.api.IPlugin;
import extrabiomes.api.PluginManager;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.config.AchievementManager;
import extrabiomes.config.Config;
import extrabiomes.config.ConfigureCustomBiomes;
import extrabiomes.config.ConfigureVanillaBiomes;
import extrabiomes.terrain.TerrainGenerator;
import forge.NetworkMod;

public class Extrabiomes {
	public static int addFuel(int i, int j) {
		if (ExtrabiomesBlock.sapling != null
				&& id == ExtrabiomesBlock.sapling.blockID) return 100;
		return 0;
	}

	public static boolean clientSideRequired() {
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

	public static void generateSurface(World world, Random random,
			int i, int j)
	{
		TerrainGenerator.generateSurface(world, random, i, j);
	}

	public static String getName() {
		return "ExtraBiomes XL";
	}

	public static String getPriorities() {
		return "";
	}

	public static String getVersion() {
		return new StringBuilder().append(getVersionNumber())
				.append(" Server").toString();
	}

	public static String getVersionNumber() {
		return "2.2.5";
	}

	private static void injectPlugins() {
		final Iterator iterator = PluginManager.plugins.iterator();

		do {
			if (!iterator.hasNext()) break;

			final IPlugin iplugin = (IPlugin) iterator.next();

			if (iplugin != null && iplugin.isEnabled()) {
				Log.write(new StringBuilder().append("Injecting the ")
						.append(iplugin.getName())
						.append(" plugin into ExtrabiomesXL.")
						.toString());
				iplugin.inject();
			}
		} while (true);
	}

	public static void load() {
		Config.load();
	}

	public static void modsLoaded(NetworkMod networkmod) {
		Config.modsLoaded();

		if (ExtrabiomesItem.scarecrow != null)
			Proxy.addRecipe(
					new ItemStack(ExtrabiomesItem.scarecrow, 1),
					new Object[] { " a ", "cbc", " c ", 'a',
							Block.PUMPKIN, 'b', Block.MELON, 'c',
							Item.STICK });

		if (ExtrabiomesBlock.redRock != null)
			Proxy.addShapelessRecipe(new ItemStack(Item.CLAY_BALL, 4),
					new Object[] {
							new ItemStack(ExtrabiomesBlock.redRock),
							new ItemStack(Item.WATER_BUCKET),
							new ItemStack(Item.WATER_BUCKET),
							new ItemStack(Item.WATER_BUCKET) });

		if (ExtrabiomesBlock.crackedSand != null)
			Proxy.addShapelessRecipe(
					new ItemStack(Block.SAND),
					new Object[] {
							new ItemStack(ExtrabiomesBlock.crackedSand),
							new ItemStack(Item.WATER_BUCKET) });

		if (ExtrabiomesBlock.flower != null) {
			ModLoader.addShapelessRecipe(new ItemStack(Item.INK_SACK,
					1, 12), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1, 1) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.INK_SACK,
					1, 14), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1, 2) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.INK_SACK,
					1, 13), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1, 3) });
			ModLoader.addShapelessRecipe(new ItemStack(Item.INK_SACK,
					1, 7), new Object[] { new ItemStack(
					ExtrabiomesBlock.flower, 1, 7) });
			ModLoader.addShapelessRecipe(new ItemStack(
					Item.MUSHROOM_SOUP), new Object[] {
					Block.BROWN_MUSHROOM,
					new ItemStack(ExtrabiomesBlock.flower, 1, 6),
					new ItemStack(ExtrabiomesBlock.flower, 1, 6),
					Item.BOWL });
			ModLoader.addShapelessRecipe(new ItemStack(
					Item.MUSHROOM_SOUP), new Object[] {
					Block.RED_MUSHROOM,
					new ItemStack(ExtrabiomesBlock.flower, 1, 6),
					new ItemStack(ExtrabiomesBlock.flower, 1, 6),
					Item.BOWL });
		}

		Config.addNames();

		if (ExtrabiomesItem.scarecrow != null) {
			ExtrabiomesEntity.scarecrow = 127;
			Proxy.registerEntityID(extrabiomes.EntityScarecrow.class,
					"scarecrow", ExtrabiomesEntity.scarecrow);
			Proxy.registerEntity(extrabiomes.EntityScarecrow.class,
					networkmod, ExtrabiomesEntity.scarecrow);
		}

		ConfigureVanillaBiomes.disableVanillaBiomes();

		if (TerrainGenManager.blockWasteland != null)
			CustomBiomeManager.wasteland.A = CustomBiomeManager.wasteland.B = (byte) TerrainGenManager.blockWasteland.id;

		if (TerrainGenManager.blockWasteland != null)
			CustomBiomeManager.mountainRidge.A = CustomBiomeManager.mountainRidge.B = (byte) TerrainGenManager.blockMountainRidge.id;

		ConfigureCustomBiomes.enableCustomBiomes();
		injectPlugins();
	}

	public static void takenFromCrafting(EntityHuman entityhuman,
			ItemStack itemstack, IInventory iinventory)
	{
		AchievementManager.craftingAchievement(entityhuman, itemstack);
	}

	public Extrabiomes() {}
}
