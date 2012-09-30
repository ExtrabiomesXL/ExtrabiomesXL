/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
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
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.configuration.EnhancedConfiguration;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.summa.biome.BiomeManagerImpl;
import extrabiomes.proxy.CommonProxy;

@Mod(modid = "EBXLFlora", name = "ExtrabiomesXL Flora Plugin", version = "3.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Flora {

	@SidedProxy(clientSide = "extrabiomes.proxy.ClientProxy", serverSide = "extrabiomes.proxy.CommonProxy")
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
		if (catTailId == 0) return;
		catTail = Optional.of(new BlockCatTail(catTailId)
				.setBlockName("cattail"));
		proxy.registerBlock(catTail.get(),
				extrabiomes.plugin.flora.ItemCatTail.class);

		proxy.addName(catTail.get(), "Cat Tail");

		proxy.registerWorldGenerator(new CatTailGenerator(
				catTail.get().blockID));
	}

	private static void initFlower() {
		if (flowerId == 0) return;
		flower = Optional.of(new BlockCustomFlower(flowerId)
				.setBlockName("flower"));
		proxy.registerBlock(flower.get(),
				extrabiomes.utility.MultiItemBlock.class);

		setFlowerRecipes();

		for (final FlowerType blockType : FlowerType.values())
			proxy.addName(
					new ItemStack(flower.get(), 1, blockType.metadata()),
					blockType.itemName());

		proxy.registerWorldGenerator(new FlowerGenerator(
				flower.get().blockID));
	}

	private static void initGrass() {
		if (grassId == 0) return;
		grass = Optional.of(new BlockCustomTallGrass(grassId)
				.setBlockName("grass"));
		proxy.registerBlock(grass.get(),
				extrabiomes.utility.MultiItemBlock.class);

		for (final GrassType blockType : GrassType.values())
			proxy.addName(
					new ItemStack(grass.get(), 1, blockType.metadata()),
					blockType.itemName());

		BiomeManager.addWeightedGrassGenForBiome(
				BiomeManager.mountainridge.get(),
				new WorldGenTallGrass(grass.get().blockID, BROWN
						.metadata()), 100);
		BiomeManager.addWeightedGrassGenForBiome(
				BiomeManager.mountainridge.get(),
				new WorldGenTallGrass(grass.get().blockID, SHORT_BROWN
						.metadata()), 100);

		BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland
				.get(), new WorldGenWastelandGrass(grass.get().blockID,
				DEAD.metadata()), 90);
		BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland
				.get(), new WorldGenWastelandGrass(grass.get().blockID,
				DEAD_YELLOW.metadata()), 90);
		BiomeManager.addWeightedGrassGenForBiome(BiomeManager.wasteland
				.get(), new WorldGenWastelandGrass(grass.get().blockID,
				DEAD_TALL.metadata()), 35);
	}

	private static void initLeafPile() {
		if (leafPileId == 0) return;
		leafPile = Optional.of(new BlockLeafPile(leafPileId)
				.setBlockName("leafpile"));
		proxy.registerBlock(leafPile.get());

		final IRecipe recipe = new ShapedOreRecipe(Block.leaves,
				new String[] { "lll", "lll", "lll" }, 'l',
				leafPile.get());
		proxy.addRecipe(recipe);

		proxy.addName(leafPile.get(), "Leaf Pile");
		proxy.registerWorldGenerator(new LeafPileGenerator(leafPile
				.get().blockID));
	}

	public static boolean isCatTailEnabled() {
		return catTail.isPresent();
	}

	public static boolean isFlowerEnabled() {
		return flower.isPresent();
	}

	public static boolean isGrassEnabled() {
		return grass.isPresent();
	}

	public static boolean isLeafPileEnabled() {
		return leafPile.isPresent();
	}

	@PreInit
	public static void preInit(FMLPreInitializationEvent event) {
		ExtrabiomesLog.configureLogging();
		final ExtrabiomesConfig cfg = new ExtrabiomesConfig(
				new File(event.getModConfigurationDirectory(),
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

		if (0 == catTailId)
			ExtrabiomesLog
					.info("cattail.id = 0, so cat tail has been disabled.");
	}

	private static void preInitFlower(Configuration cfg) {
		flowerId = cfg.getOrCreateBlockIdProperty("flower.id", 153)
				.getInt(0);

		if (0 == flowerId)
			ExtrabiomesLog
					.info("flower.id = 0, so flower has been disabled.");
	}

	private static void preInitGrass(Configuration cfg) {
		grassId = cfg.getOrCreateBlockIdProperty("grass.id", 154)
				.getInt(0);

		if (0 == grassId)
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

		if (0 == leafPileId)
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
