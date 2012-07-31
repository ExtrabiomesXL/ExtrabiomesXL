/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;

import java.util.Map;
import java.util.Random;

import net.minecraft.src.BaseMod;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraft.src.forge.MinecraftForgeClient;
import net.minecraft.src.forge.NetworkMod;
import extrabiomes.api.ExtrabiomesBlock;
import extrabiomes.api.ExtrabiomesItem;
import extrabiomes.api.IPlugin;
import extrabiomes.api.PluginManager;
import extrabiomes.api.TerrainGenManager;
import extrabiomes.biomes.CustomBiomeManager;
import extrabiomes.config.AchievementManager;
import extrabiomes.config.Config;
import extrabiomes.config.ConfigureCustomBiomes;
import extrabiomes.config.ConfigureRecipes;
import extrabiomes.config.ConfigureVanillaBiomes;
import extrabiomes.terrain.TerrainGenerator;
import extrabiomes.updatemanager.UpdateHandler;

public class Extrabiomes {

	private static final String	PRIORITIES	= "";
	private static final String	VERSION		= "2.2.7";

	public static int addFuel(int id, int damage) {
		if (ExtrabiomesBlock.sapling != null
				&& id == ExtrabiomesBlock.sapling.blockID) return 100;
		return 0;
	}

	public static void addRenderer(Map map) {
		if (ExtrabiomesItem.scarecrow != null)
			map.put(EntityScarecrow.class, new RenderScarecrow(
					new ModelScarecrow(), 0.4F));
	}

	public static void generateSurface(World world, Random random,
			int x, int z)
	{
		TerrainGenerator.generateSurface(world, random, x, z);
	}

	public static String getPriorities() {
		return PRIORITIES;
	}

	public static String getVersion() {
		return VERSION;
	}

	private static void injectPlugins() {
		for (final IPlugin plugin : PluginManager.plugins)
			if (plugin != null && plugin.isEnabled()) {
				Log.write("Injecting the " + plugin.getName()
						+ " plugin into ExtrabiomesXL.");
				plugin.inject();
			}
	}

	public static void onLoad(BaseMod mod) {
		preloadTexture("/extrabiomes/extrabiomes.png");
		new UpdateHandler(mod);

		Config.load();
	}

	public static void onModsLoaded(NetworkMod mod) {
		Config.modsLoaded();

		ConfigureRecipes.initialize();
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
			CustomBiomeManager.wasteland.topBlock = (byte) TerrainGenManager.blockWasteland.blockID;
		if (TerrainGenManager.blockWasteland != null)
			CustomBiomeManager.mountainRidge.topBlock = CustomBiomeManager.mountainRidge.fillerBlock = (byte) TerrainGenManager.blockMountainRidge.blockID;

		ConfigureCustomBiomes.enableCustomBiomes();

		injectPlugins();
	}

	public static void preloadTexture(String filename) {
		MinecraftForgeClient.preloadTexture(filename);
	}

	public static void takenFromCrafting(EntityPlayer player,
			ItemStack itemstack, IInventory var3)
	{
		AchievementManager.craftingAchievement(player, itemstack);
	}
}
