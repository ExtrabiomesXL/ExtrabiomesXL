/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package scottkillen.mods.extrabiomes;

import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

import net.minecraft.src.BaseMod;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;
import extrabiomes.EntityScarecrow;
import extrabiomes.ExtrabiomesEntity;
import extrabiomes.Log;
import extrabiomes.ModelScarecrow;
import extrabiomes.Proxy;
import extrabiomes.RenderScarecrow;
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

@Mod(modid = "ExtrabiomesXL", name = "ExtrabiomesXL", version = "2.2.8")
@NetworkMod(channels = { "ExtrabiomesXL" }, clientSideRequired = true, serverSideRequired = false)
public class Extrabiomes {

	@SidedProxy(clientSide = "scottkillen.mods.extrabiomes.client.ClientProxy", serverSide="scottkillen.mods.extrabiomes.CommonProxy")
	public static CommonProxy proxy;
	@Instance
	public static Extrabiomes instance;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		setModMetadata(event.getModMetadata());
		Config.load(event.getSuggestedConfigurationFile());
	}

	private void setModMetadata(ModMetadata modMetadata) {
		modMetadata.url = "http://www.minecraftforum.net/topic/1090288-";
		modMetadata.updateUrl = "http://www.minecraftforum.net/topic/1090288-";
		modMetadata.credits = "Concept and original mod created by MisterFiber. Some content courtesy TDWP_FTW. Some artwork courtesy of bonemouse. Some artwork courtesy Delocuro. Code contributors: Eloraam, mistaqur, SirSengir";
		modMetadata.authorList.add("ScottKillen");
		modMetadata.description = "ExtraBiomesXL adds new aesthetic content that greatly enhances Minecraft's landscapes and exploration.";
		modMetadata.logoFile = "/extrabiomes/eb-logo.png";
	}
	
	@Override
	public static int addFuel(int id, int damage) {
		if (ExtrabiomesBlock.sapling != null
				&& id == ExtrabiomesBlock.sapling.blockID) return 100;
		return 0;
	}

	@Override
	public static void addRenderer(Map map) {
		if (ExtrabiomesItem.scarecrow != null)
			map.put(EntityScarecrow.class, new RenderScarecrow(
					new ModelScarecrow(), 0.4F));
	}

	@Override
	public static void generateSurface(World world, Random random,
			int x, int z)
	{
		TerrainGenerator.generateSurface(world, random, x, z);
	}

	@Override
	public static String getPriorities() {
		return PRIORITIES;
	}

	@Override
	public static String getVersion() {
		return VERSION;
	}

	@Override
	private static void injectPlugins() {
		for (final IPlugin plugin : PluginManager.plugins)
			if (plugin != null && plugin.isEnabled()) {
				Log.write("Injecting the " + plugin.getName()
						+ " plugin into ExtrabiomesXL.");
				plugin.inject();
			}
	}

	@Override
	public static void onLoad(BaseMod mod) {
		preloadTexture("/extrabiomes/extrabiomes.png");

		Config.load();
	}

	@Override
	public static void onModsLoaded(BaseMod mod) {
		Config.modsLoaded();

		ConfigureRecipes.initialize();
		Config.addNames();

		if (ExtrabiomesItem.scarecrow != null) {
			ExtrabiomesEntity.scarecrow = 127;
			Proxy.registerEntityID(EntityScarecrow.class, "scarecrow",
					ExtrabiomesEntity.scarecrow);
			Proxy.registerEntity(EntityScarecrow.class, "scarecrow", mod,
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

	@Override
	public static void preloadTexture(String filename) {
		MinecraftForgeClient.preloadTexture(filename);
	}

	@Override
	public static void takenFromCrafting(EntityPlayer player,
			ItemStack itemstack, IInventory var3)
	{
		AchievementManager.craftingAchievement(player, itemstack);
	}
}
