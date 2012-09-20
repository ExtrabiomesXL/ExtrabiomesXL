/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

import static com.google.common.base.Preconditions.checkElementIndex;
import static extrabiomes.plugin.redrock.BlockType.RED_COBBLE;
import static extrabiomes.plugin.redrock.BlockType.RED_ROCK;
import static extrabiomes.plugin.redrock.BlockType.RED_ROCK_BRICK;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.src.Block;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
import extrabiomes.utility.EnhancedConfiguration;

@Mod(modid = "EBXLRedRock", name = "ExtrabiomesXL Red Rock Plugin", version = "3.0 PR1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class RedRock {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy		proxy;
	@Instance("EBXLRedRock")
	public static RedRock			instance;

	private static int				redRockId;
	private static Optional<Block>	redRock;

	private static final String		REDROCK_COMMENT	= "RedRock is used in terrain generation. Its id must be less than 256.";

	@Init
	public static void init(FMLInitializationEvent event) {
		if (0 < redRockId) {
			proxy.registerRenderInformation();

			redRock = Optional.of(new BlockRedRock(redRockId)
					.setBlockName("redrock"));

			final Block block = redRock.get();
			proxy.setBlockHarvestLevel(block, "pickaxe", 0);

			proxy.registerBlock(redRock,
					extrabiomes.utility.MultiItemBlock.class);
			PluginManager.registerPlugin(new ExtrabiomesPlugin(redRock
					.get().blockID));

			registerRecipes();

			for (final BlockType blockType : BlockType.values())
				proxy.addName(
						new ItemStack(block, 1, blockType.metadata()),
						blockType.itemName());
		}
	}

	public static boolean isEnabled() {
		return redRock.isPresent();
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final EnhancedConfiguration cfg = new EnhancedConfiguration(
				new File(event.getModConfigurationDirectory(),
						"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			final Property property = cfg
					.getOrCreateRestrictedBlockIdProperty("redrock.id",
							158);
			redRockId = property.getInt(0);
			property.comment = REDROCK_COMMENT;

			if (0 == redRockId)
				ExtrabiomesLog
						.info("redrock.id = 0, so red rock has been disabled.");

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
		checkElementIndex(redRockId, 256, REDROCK_COMMENT);
	}

	private static void registerRecipes() {
		final ItemStack redRockItem = new ItemStack(redRock.get(), 1,
				RED_ROCK.metadata());

		OreDictionary.registerOre("rockRed", redRockItem);

		IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
				Item.clay, 4), "rockRed", Item.bucketWater,
				Item.bucketWater, Item.bucketWater);
		proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(redRock.get(), 4,
				RED_ROCK_BRICK.metadata()),
				new String[] { "rr", "rr" }, 'r', "rockRed");
		proxy.addRecipe(recipe);

		FurnaceRecipes.smelting().addSmelting(redRock.get().blockID,
				RED_COBBLE.metadata(), redRockItem);
	}
}
