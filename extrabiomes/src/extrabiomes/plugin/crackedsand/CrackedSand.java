/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.crackedsand;

import static com.google.common.base.Preconditions.checkElementIndex;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.google.common.base.Optional;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.CommonProxy;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginManager;

@Mod(modid = "EBXLCrackedSand", name = "ExtrabiomesXL CrackedSand Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class CrackedSand {

	// @formatter:off
	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy proxy;

	@Instance("EBXLCrackedSand")
	public static CrackedSand instance;

	private static Optional<Block>	crackedSand = Optional .absent();
	private static int				crackedSandID;

	private static boolean			canGrow;
	private static boolean			restrictGrowthToBiome;

	private static final String		CRACKEDSAND_COMMENT	= "CrackedSand is used in terrain generation. Its id must be less than 256.";
	// @formatter:on

	public static boolean isEnabled() {
		return 0 < crackedSandID;
	}

	@Init
	public void init(FMLInitializationEvent event) {
		if (isEnabled()) {
			proxy.registerRenderInformation();

			crackedSand = Optional.of(new BlockCrackedSand(
					crackedSandID, canGrow, restrictGrowthToBiome)
					.setBlockName("crackedsand"));
			proxy.setBlockHarvestLevel(crackedSand.get(), "pickaxe", 0);

			PluginManager.registerPlugin(new ExtrabiomesPlugin(
					crackedSandID));
			proxy.registerBlock(crackedSand);

			OreDictionary.registerOre("sandCracked", crackedSand.get());

			final IRecipe recipe = new ShapelessOreRecipe(Block.sand,
					"sandCracked", Item.bucketWater);
			proxy.addRecipe(recipe);

			proxy.addName(crackedSand.get(), "Cracked Sand");
		}
	}

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final Configuration cfg = new Configuration(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			Property property = cfg
					.getOrCreateIntProperty("crackedsand.id",
							Configuration.CATEGORY_BLOCK, 152);
			crackedSandID = property.getInt(0);
			property.comment = CRACKEDSAND_COMMENT;

			if (!isEnabled())
				ExtrabiomesLog
						.info("crackedsand.id = 0, so cracked sand has been disabled.");

			property = cfg.getOrCreateBooleanProperty(
					"crackedsand.growth.enable",
					Configuration.CATEGORY_GENERAL, true);
			canGrow = property.getBoolean(true);
			property.comment = "Set to false to disable Cracked Sand growth.";

			ExtrabiomesLog
					.info("CrackedSand growth %sabled by config file directive.",
							canGrow ? "en" : "dis");

			property = cfg.getOrCreateBooleanProperty(
					"crackedsand.growthoutsidewasteland.allow",
					Configuration.CATEGORY_GENERAL, false);
			restrictGrowthToBiome = !property.getBoolean(false);
			property.comment = "Set to true to restrict Cracked Sand growth to Wasteland biome.";

			if (canGrow)
				ExtrabiomesLog
						.info("CrackedSand growth outside of the wasteland biome %sabled by config file directive.",
								restrictGrowthToBiome ? "dis" : "en");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"EBXL Scarecrow had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
		checkElementIndex(crackedSandID, 256, CRACKEDSAND_COMMENT);
	}
}
