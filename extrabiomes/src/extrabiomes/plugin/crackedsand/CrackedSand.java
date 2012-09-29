/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
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
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginManager;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.utility.EnhancedConfiguration;

@Mod(modid = "EBXLCrackedSand", name = "ExtrabiomesXL Cracked Sand Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class CrackedSand {

	@SidedProxy(clientSide = "extrabiomes.proxy.ClientProxy", serverSide = "extrabiomes.proxy.CommonProxy")
	public static CommonProxy proxy;

	@Instance("EBXLCrackedSand")
	public static CrackedSand instance;

	private static Optional<Block>	crackedSand = Optional .absent();
	private static int				crackedSandId;

	private static boolean			canGrow;
	private static boolean			restrictGrowthToBiome;

	private static final String		CRACKEDSAND_COMMENT	= "CrackedSand is used in terrain generation. Its id must be less than 256.";

	public static boolean isEnabled() {
		return crackedSand.isPresent();
	}

	@Init
	public void init(FMLInitializationEvent event) {
		if (0 < crackedSandId) {
			proxy.registerRenderInformation();

			crackedSand = Optional.of(new BlockCrackedSand(
					crackedSandId, canGrow, restrictGrowthToBiome)
					.setBlockName("crackedsand"));
			proxy.setBlockHarvestLevel(crackedSand.get(), "pickaxe", 0);

			PluginManager.registerPlugin(new ExtrabiomesPlugin(
					crackedSand.get().blockID));
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
		final EnhancedConfiguration cfg = new EnhancedConfiguration(
				new File(event.getModConfigurationDirectory(),
						"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			Property property = cfg
					.getOrCreateRestrictedBlockIdProperty(
							"crackedsand.id", 152);
			crackedSandId = property.getInt(0);
			property.comment = CRACKEDSAND_COMMENT;

			if (0 == crackedSandId)
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
		checkElementIndex(crackedSandId, 256, CRACKEDSAND_COMMENT);
	}
}
