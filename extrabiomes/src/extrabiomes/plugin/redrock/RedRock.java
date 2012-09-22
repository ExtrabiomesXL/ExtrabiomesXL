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
import net.minecraft.src.BlockHalfSlab;
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

	@SidedProxy(clientSide = "extrabiomes.plugin.redrock.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy							proxy;
	@Instance("EBXLRedRock")
	public static RedRock								instance;

	private static int									redRockId;
	private static int									redRockHalfId;
	private static int									redRockDoubleId;
	private static int                                  renderId;

	public static int getRenderId() {
		return renderId;
	}

	public static void setRenderId(int renderId) {
		RedRock.renderId = renderId;
	}

	private static Optional<Block>						redRock;
	private static Optional<? extends BlockHalfSlab>	halfSlab;
	private static Optional<? extends BlockHalfSlab>	doubleSlab;
	private static final String							REDROCK_COMMENT	= "RedRock is used in terrain generation. Its id must be less than 256.";

	public static BlockHalfSlab getDoubleSlab() {
		return doubleSlab.get();
	}

	public static BlockHalfSlab getHalfSlab() {
		return halfSlab.get();
	}

	@Init
	public static void init(FMLInitializationEvent event) {
		if (0 < redRockId) {
			proxy.registerRenderInformation();

			redRock = Optional.of(new BlockRedRock(redRockId)
					.setBlockName("redrock"));

			halfSlab = Optional.of(new BlockSlab(redRockHalfId, false));
			halfSlab.get().setBlockName("redrockSlab");

			doubleSlab = Optional.of(new BlockSlab(redRockDoubleId,
					true));
			doubleSlab.get().setBlockName("redrockSlab");

			proxy.setBlockHarvestLevel(redRock.get(), "pickaxe", 0);
			proxy.setBlockHarvestLevel(halfSlab.get(), "pickaxe", 0);
			proxy.setBlockHarvestLevel(doubleSlab.get(), "pickaxe", 0);

			proxy.registerBlock(redRock,
					extrabiomes.utility.MultiItemBlock.class);
			proxy.registerBlock(halfSlab,
					extrabiomes.plugin.redrock.ItemSlab.class);
			proxy.registerBlock(doubleSlab,
					extrabiomes.plugin.redrock.ItemSlab.class);

			PluginManager.registerPlugin(new ExtrabiomesPlugin(redRock
					.get().blockID));

			registerRecipes();

			for (final BlockType blockType : BlockType.values()) {
				proxy.addName(
						new ItemStack(redRock.get(), 1, blockType
								.metadata()), blockType.itemName());
				proxy.addName(new ItemStack(halfSlab.get(), 1,
						blockType.metadata()), blockType.itemName()
						+ " Slab");
				proxy.addName(new ItemStack(doubleSlab.get(), 1,
						blockType.metadata()), blockType.itemName()
						+ " Double Slab");
			}
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

			Property property = cfg
					.getOrCreateRestrictedBlockIdProperty("redrock.id",
							158);
			redRockId = property.getInt(0);
			property.comment = REDROCK_COMMENT;

			if (0 == redRockId)
				ExtrabiomesLog
						.info("redrock.id = 0, so red rock has been disabled.");

			property = cfg.getOrCreateBlockIdProperty(
					"redrock.halfslab.id", 160);
			redRockHalfId = property.getInt(0);

			if (0 == redRockHalfId)
				ExtrabiomesLog
						.info("redrock.halfslab.id = 0, so red rock slabs have been disabled.");

			property = cfg.getOrCreateBlockIdProperty(
					"redrock.doubleslab.id", 161);
			redRockDoubleId = property.getInt(0);

			if (0 == redRockDoubleId)
				ExtrabiomesLog
						.info("redrock.doubleslab.id = 0, so red rock slabs have been disabled.");

			if (0 == redRockHalfId || 0 == redRockDoubleId) {
				redRockHalfId = 0;
				redRockDoubleId = 0;
			}

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
		final ItemStack redCobbleItem = new ItemStack(redRock.get(), 1,
				RED_COBBLE.metadata());
		final ItemStack redRockBrickItem = new ItemStack(redRock.get(),
				1, RED_ROCK_BRICK.metadata());

		OreDictionary.registerOre("rockRed", redRockItem);
		OreDictionary.registerOre("cobbleRed", redCobbleItem);
		OreDictionary.registerOre("brickRedRock", redRockBrickItem);

		IRecipe recipe = new ShapelessOreRecipe(new ItemStack(
				Item.clay, 4), "rockRed", Item.bucketWater,
				Item.bucketWater, Item.bucketWater);
		proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(redRock.get(), 4,
				RED_ROCK_BRICK.metadata()),
				new String[] { "rr", "rr" }, 'r', "rockRed");
		proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(halfSlab.get(), 6,
				RED_ROCK.metadata()), new String[] { "rrr" }, 'r',
				"rockRed");
		proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(halfSlab.get(), 6,
				RED_COBBLE.metadata()), new String[] { "rrr" }, 'r',
				"cobbleRed");
		proxy.addRecipe(recipe);

		recipe = new ShapedOreRecipe(new ItemStack(halfSlab.get(), 6,
				RED_ROCK_BRICK.metadata()), new String[] { "rrr" },
				'r', "brickRedRock");
		proxy.addRecipe(recipe);

		FurnaceRecipes.smelting().addSmelting(redRock.get().blockID,
				RED_COBBLE.metadata(), redRockItem);
	}
}
