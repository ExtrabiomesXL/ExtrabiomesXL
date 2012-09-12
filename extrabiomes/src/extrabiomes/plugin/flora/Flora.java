/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.flora;

import static extrabiomes.plugin.flora.FlowerType.HYDRANGEA;
import static extrabiomes.plugin.flora.FlowerType.ORANGE;
import static extrabiomes.plugin.flora.FlowerType.PURPLE;
import static extrabiomes.plugin.flora.FlowerType.TOADSTOOL;
import static extrabiomes.plugin.flora.FlowerType.WHITE;
import static extrabiomes.plugin.flora.GrassType.BROWN;
import static extrabiomes.plugin.flora.GrassType.DEAD;
import static extrabiomes.plugin.flora.GrassType.DEAD_TALL;
import static extrabiomes.plugin.flora.GrassType.DEAD_YELLOW;
import static extrabiomes.plugin.flora.GrassType.SHORT_BROWN;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraft.src.IRecipe;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.WorldGenTallGrass;
import net.minecraftforge.common.Configuration;
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
import extrabiomes.api.BiomeManager;
import extrabiomes.biomes.BiomeManagerImpl;

@Mod(modid = "EBXLFlora", name = "ExtrabiomesXL Flora Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Flora {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy		proxy;
	@Instance("EBXLFlora")
	public static Flora				instance;
	private static int				catTailId;
	private static int				flowerId;
	private static int				grassId;
	private static int				leafPileId;
	private static Optional<Block>	flower;
	private static Optional<Block>	grass;
	private static Optional<Block>	catTail;
	private static Optional<Block>	leafPile;

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerRenderInformation();

		initFlower();
		initGrass();
		initCatTail();
		initLeafPile();
	}

	private static void initCatTail() {
		if (!isCatTailEnabled()) return;
		catTail = Optional.of(new BlockCatTail(catTailId)
				.setBlockName("cattail"));
		proxy.registerBlock(catTail,
				extrabiomes.plugin.flora.ItemCatTail.class);

		proxy.addName(catTail.get(), "Cat Tail");

		proxy.registerWorldGenerator(new CatTailGenerator(catTailId));
	}

	private static void initFlower() {
		if (!isFlowerEnabled()) return;
		flower = Optional.of(new BlockCustomFlower(flowerId)
				.setBlockName("flower"));
		proxy.registerBlock(flower,
				extrabiomes.utility.MultiItemBlock.class);

		setFlowerRecipes();

		for (final FlowerType blockType : FlowerType.values())
			proxy.addName(
					new ItemStack(flower.get(), 1, blockType.metadata()),
					blockType.itemName());

		proxy.registerWorldGenerator(new FlowerGenerator(flowerId));
	}

	private static void initGrass() {
		if (!isGrassEnabled()) return;
		grass = Optional.of(new BlockCustomTallGrass(grassId)
				.setBlockName("grass"));
		proxy.registerBlock(grass,
				extrabiomes.utility.MultiItemBlock.class);

		for (final GrassType blockType : GrassType.values())
			proxy.addName(
					new ItemStack(grass.get(), 1, blockType.metadata()),
					blockType.itemName());

		BiomeManager.addWeightedGrassGenForBiome(
				BiomeManager.mountainridge.get(),
				new WorldGenTallGrass(grassId, BROWN.metadata()), 100);
		BiomeManager.addWeightedGrassGenForBiome(
				BiomeManager.mountainridge.get(),
				new WorldGenTallGrass(grassId, SHORT_BROWN.metadata()),
				100);

		BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland
				.get(),
				new WorldGenTallGrass(grassId, DEAD.metadata()), 90);
		BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland
				.get(),
				new WorldGenTallGrass(grassId, DEAD_YELLOW.metadata()),
				90);
		BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland
				.get(),
				new WorldGenTallGrass(grassId, DEAD_TALL.metadata()),
				70);
	}

	private static void initLeafPile() {
		if (!isLeafPileEnabled()) return;
		leafPile = Optional.of(new BlockLeafPile(leafPileId)
				.setBlockName("leafpile"));
		proxy.registerBlock(leafPile);

		final IRecipe recipe = new ShapedOreRecipe(Block.leaves,
				new String[] { "lll", "lll", "lll" }, 'l',
				leafPile.get());
		proxy.addRecipe(recipe);

		proxy.addName(leafPile.get(), "Leaf Pile");
		proxy.registerWorldGenerator(new LeafPileGenerator(leafPileId));
	}

	public static boolean isCatTailEnabled() {
		return 0 < catTailId;
	}

	public static boolean isFlowerEnabled() {
		return 0 < flowerId;
	}

	public static boolean isGrassEnabled() {
		return 0 < grassId;
	}

	public static boolean isLeafPileEnabled() {
		return 0 < leafPileId;
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final Configuration cfg = new Configuration(new File(
				event.getModConfigurationDirectory(),
				"/extrabiomes/extrabiomes.cfg"));
		try {
			cfg.load();

			preInitCatTail(cfg);
			preInitFlower(cfg);
			preInitGrass(cfg);
			preInitLeafPile(cfg);

		} catch (final Exception e) {
			ExtrabiomesLog
					.log(Level.SEVERE, e,
							"ExtrabiomesXL had a problem loading it's configuration.");
		} finally {
			cfg.save();
		}
	}

	private static void preInitCatTail(Configuration cfg) {
		catTailId = cfg.getOrCreateBlockIdProperty("cattail.id", 151)
				.getInt(0);

		if (!isCatTailEnabled())
			ExtrabiomesLog
					.info("cattail.id = 0, so cat tail has been disabled.");
	}

	private static void preInitFlower(Configuration cfg) {
		flowerId = cfg.getOrCreateBlockIdProperty("flower.id", 153)
				.getInt(0);

		if (!isFlowerEnabled())
			ExtrabiomesLog
					.info("flower.id = 0, so flower has been disabled.");
	}

	private static void preInitGrass(Configuration cfg) {
		grassId = cfg.getOrCreateBlockIdProperty("grass.id", 154)
				.getInt(0);

		if (!isGrassEnabled())
			ExtrabiomesLog
					.info("grass.id = 0, so grass has been disabled.");

		final Collection<BiomeGenBase> biomes = new ArrayList();
		biomes.add(BiomeManager.mountainridge.get());
		biomes.add(BiomeManager.wasteland.get());
		BiomeManagerImpl.disableDefaultGrassforBiomes(biomes);
	}

	private static void preInitLeafPile(Configuration cfg) {
		leafPileId = cfg.getOrCreateBlockIdProperty("leafpile.id", 156)
				.getInt(0);

		if (!isLeafPileEnabled())
			ExtrabiomesLog
					.info("leafpile.id = 0, so leaf pile has been disabled.");
	}

	private static void setFlowerRecipes() {
		final ItemStack dyeLightBlue = new ItemStack(Item.dyePowder, 1,
				12);
		final ItemStack dyeLightGray = new ItemStack(Item.dyePowder, 1,
				7);
		final ItemStack dyeOrange = new ItemStack(Item.dyePowder, 1, 14);
		final ItemStack dyePurple = new ItemStack(Item.dyePowder, 1, 5);
		final ItemStack flowerHydrangea = new ItemStack(flower.get(),
				1, HYDRANGEA.metadata());
		final ItemStack flowerOrange = new ItemStack(flower.get(), 1,
				ORANGE.metadata());
		final ItemStack flowerPurple = new ItemStack(flower.get(), 1,
				PURPLE.metadata());
		final ItemStack flowerToadstool = new ItemStack(flower.get(),
				1, TOADSTOOL.metadata());
		final ItemStack flowerWhite = new ItemStack(flower.get(), 1,
				WHITE.metadata());

		IRecipe recipe = new ShapelessOreRecipe(dyeLightBlue,
				flowerHydrangea);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyeOrange, flowerOrange);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyePurple, flowerPurple);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(dyeLightGray, flowerWhite);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(Item.bowlSoup,
				Block.mushroomBrown, flowerToadstool, flowerToadstool,
				Item.bowlEmpty);
		proxy.addRecipe(recipe);

		recipe = new ShapelessOreRecipe(Item.bowlSoup,
				Block.mushroomRed, flowerToadstool, flowerToadstool,
				Item.bowlEmpty);
		proxy.addRecipe(recipe);
	}
}
