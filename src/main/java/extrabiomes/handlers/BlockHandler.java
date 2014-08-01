/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.util.Collection;

import net.minecraft.init.Blocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import extrabiomes.Extrabiomes;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCatTail;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomFlower.BlockType;
import extrabiomes.blocks.BlockCustomLog;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.blocks.BlockCustomVine;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.blocks.BlockKneeLog;
import extrabiomes.blocks.BlockLeafPile;
import extrabiomes.blocks.BlockMiniLog;
import extrabiomes.blocks.BlockMoreLeaves;
import extrabiomes.blocks.BlockNewLeaves;
import extrabiomes.blocks.BlockNewLog;
import extrabiomes.blocks.BlockNewQuarterLog;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.blocks.BlockQuarterLog;
import extrabiomes.blocks.BlockRedRock;
import extrabiomes.blocks.BlockWaterPlant;
import extrabiomes.blocks.GenericTerrainBlock;
import extrabiomes.events.BlockActiveEvent.RedRockActiveEvent;
import extrabiomes.helpers.BiomeHelper;
import extrabiomes.helpers.ForestryModHelper;
import extrabiomes.helpers.LogHelper;
import extrabiomes.items.ItemKneeLog;
import extrabiomes.items.ItemNewQuarterLog;
import extrabiomes.items.ItemOldQuarterLog;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.summa.worldgen.CatTailGenerator;
import extrabiomes.module.summa.worldgen.EelGrassGenerator;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.module.summa.worldgen.LeafPileGenerator;
import extrabiomes.module.summa.worldgen.VineGenerator;
import extrabiomes.module.summa.worldgen.WorldGenCustomVine;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.renderers.RenderKneeLog;
import extrabiomes.renderers.RenderMiniLog;
import extrabiomes.renderers.RenderNewQuarterLog;
import extrabiomes.renderers.RenderQuarterLog;
import extrabiomes.subblocks.SubBlockWaterPlant;

public abstract class BlockHandler
{

    private static void createAutumnLeaves()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.AUTUMNLEAVES.getEnabled())
            return;

        final BlockAutumnLeaves block = new BlockAutumnLeaves(3, Material.leaves, false);
        block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomLeaves.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        Blocks.fire.setFireInfo(block, 30, 60);

        Element.LEAVES_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.UMBER.metadata()));
        Element.LEAVES_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.GOLDENROD.metadata()));
        Element.LEAVES_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.VERMILLION.metadata()));
        Element.LEAVES_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.CITRINE.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    public static void createBlocks() throws Exception
    {
        createAutumnLeaves();
        createCattail();
        createCrackedSand();
		createFlowers();
        createGrass();
        createGreenLeaves();
        createNewLeaves();
        createMoreLeaves();
        createLeafPile();
        createRedRock();
        createSapling();
        createNewSapling();
        createLogs();
		createVines();
		createWaterPlants();
		
    }
    
    private static void createWaterPlants() throws Exception {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.WATERPLANT.getEnabled()) return;
        
    	final BlockWaterPlant waterPlantBlock = new BlockWaterPlant(BlockSettings.WATERPLANT, "waterPlant");
    	
    	waterPlantBlock.setBlockName("extrabiomes.waterplant").setHardness(0.01F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);
    	
    	// Add the subblocks
    	waterPlantBlock.registerSubBlock(new SubBlockWaterPlant("eelgrass").addPlaceableBlock(Blocks.grass).addPlaceableBlock(Blocks.sand).addPlaceableBlock(Blocks.gravel).addPlaceableBlock(Blocks.clay), 0);
    	
    	final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(waterPlantBlock, extrabiomes.items.ItemBlockWaterPlant.class, "waterplant1");
        
        Element.WATERPLANT.set(new ItemStack(waterPlantBlock, 1, 0));
        
        proxy.registerWorldGenerator(new EelGrassGenerator(waterPlantBlock, 0));
    }

    private static void createCattail()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.CATTAIL.getEnabled())
            return;

        final BlockCatTail block = new BlockCatTail(79, Material.plants);
        block.setBlockName("extrabiomes.cattail").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCatTail.class, "plants4");
        proxy.registerOre("reedTypha", block);

        Element.CATTAIL.set(new ItemStack(block));

        proxy.registerWorldGenerator(new CatTailGenerator(block));
    }

    private static void createCrackedSand()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.CRACKEDSAND.getEnabled())
            return;

        final GenericTerrainBlock block = new GenericTerrainBlock(0, Material.rock);
        block.setBlockName("extrabiomes.crackedsand").setHardness(1.2F).setStepSound(Block.soundTypeStone).setCreativeTab(Extrabiomes.tabsEBXL);

        block.texturePath = "crackedsand";

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "pickaxe", 0);
        proxy.registerBlock(block, "terrain_blocks2");

        proxy.registerOre("sandCracked", block);

        final ItemStack stack = new ItemStack(block);
        Element.CRACKEDSAND.set(stack);

        BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.WASTELAND, block, block);

        ForestryModHelper.addToDiggerBackpack(stack);
        FacadeHelper.addBuildcraftFacade(block);
    }

	private static void createFlowers()
    {
		if (!ModuleControlSettings.SUMMA.isEnabled()) return;

		final boolean enableds[] = { BlockSettings.FLOWER.getEnabled(),
				BlockSettings.FLOWER2.getEnabled(), BlockSettings.FLOWER3.getEnabled() };
		
		final CommonProxy proxy = Extrabiomes.proxy;
		final FlowerGenerator generator = FlowerGenerator.getInstance();
		
		for (int group = 0; group < enableds.length; ++group) {
			if (!enableds[group]) continue;

	        final BlockCustomFlower block = new BlockCustomFlower(group, Material.plants);
			block.setBlockName(
					"extrabiomes.flower")
					.setTickRandomly(true).setHardness(0.0F)
					.setStepSound(Block.soundTypeGrass)
					.setCreativeTab(Extrabiomes.tabsEBXL);
			proxy.registerBlock(
					block,
					extrabiomes.items.ItemFlower.class, "flower" + (group+1));

			Collection<BlockType> types = block.getGroupTypes();
			for (BlockType type : types) {
				final Element element;
				try {
					element = Element.valueOf(type.name());
				} catch (Exception e) {
					LogHelper.warning("No element found for flower " + type);
					continue;
				}
				ItemStack item = new ItemStack(block, 1, type.metadata());
				element.set(item);
				ForestryModHelper.registerBasicFlower(item);

			}
	
			generator.registerBlock(block, types);
	        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
		}
		
		proxy.registerWorldGenerator(generator);

		// TODO: register other flowers with forestry

		//ForestryModHelper.registerBasicFlower(Element.HYDRANGEA.get());
		//ForestryModHelper.registerBasicFlower(Element.BUTTERCUP.get());
		//ForestryModHelper.registerBasicFlower(Element.LAVENDER.get());
		//ForestryModHelper.registerBasicFlower(Element.CALLA_WHITE.get());
    }

	private static void createVines() {
		if (!ModuleControlSettings.SUMMA.isEnabled()) return;

		final CommonProxy proxy = Extrabiomes.proxy;

		//BlockCustomVine.BlockType[] vines = BlockCustomVine.BlockType.values();
		BlockCustomVine.BlockType[] vines = { BlockCustomVine.BlockType.GLORIOSA };
		
		for (BlockCustomVine.BlockType blockType : vines) {
			final BlockSettings settings;
			try {
				settings = BlockSettings.valueOf(blockType.name());
			} catch (Exception e) {
				LogHelper.severe("Unable to find settings for " + blockType);
				continue;
			}

			if (!settings.getEnabled()) continue;

			/*
			 * final String shortName = blockType.name()
			 * .substring(blockType.name().indexOf('_')).toLowerCase();
			 */

			final BlockCustomVine block = new BlockCustomVine(blockType);
			block.setBlockName(
					"extrabiomes.vine." + blockType.name().toLowerCase())
					.setCreativeTab(Extrabiomes.tabsEBXL);
			proxy.registerBlock(block, ItemBlock.class,
					block.getUnlocalizedName());

			final Element element;
			try {
				element = Element.valueOf("VINE_" + blockType.name());
			} catch (Exception e) {
				LogHelper.warning("No element found for vine " + blockType);
				continue;
			}
			final ItemStack item = new ItemStack(block, 1);
			element.set(item);

			ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1,
					Short.MAX_VALUE));
			
			final VineGenerator generator;
			// gloriosa gets a biome list override
			if( blockType == BlockCustomVine.BlockType.GLORIOSA ) {
				final BiomeSettings[] biomeList = {
					BiomeSettings.EXTREMEJUNGLE,
					BiomeSettings.MINIJUNGLE,
					BiomeSettings.RAINFOREST
				};
				generator = new VineGenerator(block, biomeList);
			} else {
				generator = new VineGenerator(block);	
			}
			proxy.registerWorldGenerator(generator);
		}
	}

    private static void createGrass()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.GRASS.getEnabled())
            return;

        final BlockCustomTallGrass block = new BlockCustomTallGrass(48, Material.vine);
        block.setBlockName("extrabiomes.tallgrass").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemGrass.class,  block.getUnlocalizedName());
        Blocks.fire.setFireInfo(block, 60, 100);

        Element.GRASS_BROWN.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.BROWN.metadata()));
        Element.GRASS_DEAD.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD.metadata()));
        Element.GRASS_BROWN_SHORT.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.SHORT_BROWN.metadata()));
        Element.GRASS_DEAD_TALL.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD_TALL.metadata()));
        Element.GRASS_DEAD_YELLOW.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD_YELLOW.metadata()));

        ItemStack grassStack = Element.GRASS_BROWN.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 100);
        grassStack = Element.GRASS_BROWN_SHORT.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 100);

        grassStack = Element.GRASS_DEAD.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 90);
        grassStack = Element.GRASS_DEAD_YELLOW.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 90);
        grassStack = Element.GRASS_DEAD_TALL.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(Block.getBlockFromItem(grassStack.getItem()), grassStack.getItemDamage()), 35);
    }

    private static void createNewLeaves()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWLEAVES.getEnabled())
            return;

        final BlockNewLeaves block = new BlockNewLeaves(Material.leaves, false);
        block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomNewLeaves.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        Blocks.fire.setFireInfo(block, 30, 60);

        Element.LEAVES_BALD_CYPRESS.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.BALD_CYPRESS.metadata()));
        Element.LEAVES_JAPANESE_MAPLE.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.JAPANESE_MAPLE.metadata()));
        Element.LEAVES_JAPANESE_MAPLE_SHRUB.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.JAPANESE_MAPLE_SHRUB.metadata()));
        Element.LEAVES_RAINBOW_EUCALYPTUS.set(new ItemStack(block, 1, BlockNewLeaves.BlockType.RAINBOW_EUCALYPTUS.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    private static void createMoreLeaves()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.MORELEAVES.getEnabled())
            return;

        final BlockMoreLeaves block = new BlockMoreLeaves(Material.leaves, false);
        block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomMoreLeaves.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        Blocks.fire.setFireInfo(block, 30, 60);

        Element.LEAVES_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockMoreLeaves.BlockType.SAKURA_BLOSSOM.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    private static void createGreenLeaves()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.GREENLEAVES.getEnabled())
            return;

        final BlockGreenLeaves block = new BlockGreenLeaves(Material.leaves, false);
        block.setBlockName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomGreenLeaves.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        Blocks.fire.setFireInfo(block, 30, 60);

        Element.LEAVES_ACACIA.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.ACACIA.metadata()));
        Element.LEAVES_FIR.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.FIR.metadata()));
        Element.LEAVES_REDWOOD.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.REDWOOD.metadata()));
        Element.LEAVES_CYPRESS.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.CYPRESS.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    private static void createLeafPile()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.LEAFPILE.getEnabled())
            return;

        final BlockLeafPile block = new BlockLeafPile(64, Material.vine);
        block.setBlockName("extrabiomes.leafpile").setHardness(0.0F).setTickRandomly(true).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block,  block.getUnlocalizedName());
        Blocks.fire.setFireInfo(block, 30, 60);

        Element.LEAFPILE.set(new ItemStack(block));

        proxy.registerWorldGenerator(new LeafPileGenerator(block));
    }

    private static void createLogs()
    {
        createWood();
        createMiniLogs();
        createQuarterLogs();
        createNewQuarterLogs();
        createKneeLogs();
    }

    private static void createMiniLogs()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.MINILOG.getEnabled())
            return;

        final BlockMiniLog block = new BlockMiniLog(BlockSettings.MINILOG);
        extrabiomes.lib.Blocks.BLOCK_LOG_SAKURA_GROVE.set(block);
        block.setBlockName("extrabiomes.log").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, extrabiomes.items.ItemCustomMiniLog.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        Blocks.fire.setFireInfo(block, 5, 5);

        Element.LOG_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockMiniLog.BlockType.SAKURA_BLOSSOM.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));

        BlockMiniLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderMiniLog()));
    }

    private static void createKneeLogs()
    {
        final BlockKneeLog block = new BlockKneeLog(BlockSettings.KNEELOG, "baldcypress");
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.KNEELOG.getEnabled())
            return;

        block.setBlockName("extrabiomes.cypresskneelog").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        //proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);
        proxy.registerBlock(block, ItemKneeLog.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        Blocks.fire.setFireInfo(block, 5, 5);

        final BlockKneeLog block2 = new BlockKneeLog(BlockSettings.RAINBOWKNEELOG, "rainboweucalyptus");
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.RAINBOWKNEELOG.getEnabled())
            return;

        block2.setBlockName("extrabiomes.rainbowkneelog").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block2, "axe", 0);
        proxy.registerBlock(block2, ItemKneeLog.class, block2.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("logWood", block2);
        proxy.registerEventHandler(block2);
        Blocks.fire.setFireInfo(block2, 5, 5);

        Element.LOG_KNEE_BALD_CYPRESS.set(new ItemStack(block, 1, Short.MAX_VALUE));
        Element.LOG_KNEE_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, Short.MAX_VALUE));

        BlockKneeLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderKneeLog()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block2, 1, Short.MAX_VALUE));

        FacadeHelper.addBuildcraftFacade(block);
        FacadeHelper.addBuildcraftFacade(block2);

    }

    private static void createNewQuarterLogs()
    {
        final CommonProxy proxy = Extrabiomes.proxy;
    	BlockNewQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderNewQuarterLog()));

        final BlockNewQuarterLog block = new BlockNewQuarterLog(BlockSettings.NEWQUARTERLOG, "baldcypress");
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWQUARTERLOG.getEnabled())
            return;

        block.setBlockName("extrabiomes.baldcypressquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, ItemNewQuarterLog.class, "cornerlog_baldcypress");
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        Blocks.fire.setFireInfo(block, 5, 5);

        final BlockNewQuarterLog block2 = new BlockNewQuarterLog(BlockSettings.RAINBOWQUARTERLOG, "rainboweucalyptus");
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.RAINBOWQUARTERLOG.getEnabled())
            return;

        block2.setBlockName("extrabiomes.rainboweucalyptusquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block2, "axe", 0);
        proxy.registerBlock(block2, ItemNewQuarterLog.class, "cornerlog_rainboweucalyptus");
        proxy.registerOreInAllSubblocks("logWood", block2);
        proxy.registerEventHandler(block2);
        Blocks.fire.setFireInfo(block2, 5, 5);

        final BlockNewQuarterLog block3 = new BlockNewQuarterLog(BlockSettings.OAKQUARTERLOG, "oak");
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.OAKQUARTERLOG.getEnabled())
            return;

        block3.setBlockName("extrabiomes.oakquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block3, "axe", 0);
        proxy.registerBlock(block3, ItemNewQuarterLog.class, "cornerlog_oak");
        proxy.registerOreInAllSubblocks("logWood", block3);
        proxy.registerEventHandler(block3);
        Blocks.fire.setFireInfo(block3, 5, 5);

        final BlockNewQuarterLog block4 = new BlockNewQuarterLog(BlockSettings.FIRQUARTERLOG, "fir");
        if (!ModuleControlSettings.SUMMA.isEnabled() && !BlockSettings.FIRQUARTERLOG.getEnabled())
            return;

        block4.setBlockName("extrabiomes.firquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block4, "axe", 0);
        proxy.registerBlock(block4, ItemNewQuarterLog.class, "cornerlog_fir");
        proxy.registerOreInAllSubblocks("logWood", block4);
        proxy.registerEventHandler(block4);
        Blocks.fire.setFireInfo(block4, 5, 5);

        final BlockNewQuarterLog block5 = new BlockNewQuarterLog(BlockSettings.REDWOODQUARTERLOG, "redwood");
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.REDWOODQUARTERLOG.getEnabled())
            return;

        block5.setBlockName("extrabiomes.redwoodquarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
        //block5.setRenderId(renderId);

        proxy.setBlockHarvestLevel(block5, "axe", 0);
        proxy.registerBlock(block5, ItemNewQuarterLog.class, "cornerlog_redwood");
        proxy.registerOreInAllSubblocks("logWood", block5);
        proxy.registerEventHandler(block5);
        Blocks.fire.setFireInfo(block5, 5, 5);


        Element.LOG_QUARTER_BALD_CYPRESS.set(new ItemStack(block, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_OAK.set(new ItemStack(block3, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_FIR.set(new ItemStack(block4, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_REDWOOD.set(new ItemStack(block5, 1, Short.MAX_VALUE));

        //BlockNewQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderNewQuarterLog()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block2, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block3, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block4, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block5, 1, Short.MAX_VALUE));
        FacadeHelper.addBuildcraftFacade(block);
        FacadeHelper.addBuildcraftFacade(block2);
        FacadeHelper.addBuildcraftFacade(block3);
        FacadeHelper.addBuildcraftFacade(block4);
        FacadeHelper.addBuildcraftFacade(block5);

    }

    private static void createQuarterLogs()
    {
        final boolean blockIDNW = BlockSettings.QUARTERLOG0.getEnabled();
        final boolean blockIDNE = BlockSettings.QUARTERLOG1.getEnabled();
        final boolean blockIDSW = BlockSettings.QUARTERLOG2.getEnabled();
        final boolean blockIDSE = BlockSettings.QUARTERLOG3.getEnabled();
        if (!ModuleControlSettings.SUMMA.isEnabled() || !blockIDNE || !blockIDNW || !blockIDSE || !blockIDSW)
            return;

        final BlockQuarterLog blockNW = new BlockQuarterLog(BlockSettings.QUARTERLOG0, 144, BlockQuarterLog.BarkOn.NW);
        final BlockQuarterLog blockNE = new BlockQuarterLog(BlockSettings.QUARTERLOG1, 144, BlockQuarterLog.BarkOn.NE);
        final BlockQuarterLog blockSW = new BlockQuarterLog(BlockSettings.QUARTERLOG2, 144, BlockQuarterLog.BarkOn.SW);
        final BlockQuarterLog blockSE = new BlockQuarterLog(BlockSettings.QUARTERLOG3, 144, BlockQuarterLog.BarkOn.SE);

        for (final BlockQuarterLog block : new BlockQuarterLog[] { blockNW, blockNE, blockSW, blockSE })
        {
            block.setBlockName("extrabiomes.log.quarter").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F); //*/.setCreativeTab(Extrabiomes.tabsEBXL);

            final CommonProxy proxy = Extrabiomes.proxy;
            proxy.setBlockHarvestLevel(block, "axe", 0);
            proxy.registerBlock(block, ItemOldQuarterLog.class,  block.getBarkOnSides().toString() + ":" + block.getUnlocalizedName());
            proxy.registerOreInAllSubblocks("logWood", block);
            proxy.registerEventHandler(block);
            Blocks.fire.setFireInfo(block, 5, 5);
        }

        Element.LOG_HUGE_FIR_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.FIR.metadata()));
        Element.LOG_HUGE_FIR_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.FIR.metadata()));
        Element.LOG_HUGE_FIR_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.FIR.metadata()));
        Element.LOG_HUGE_FIR_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.FIR.metadata()));
        Element.LOG_HUGE_OAK_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.OAK.metadata()));
        Element.LOG_HUGE_OAK_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.OAK.metadata()));
        Element.LOG_HUGE_OAK_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.OAK.metadata()));
        Element.LOG_HUGE_OAK_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.OAK.metadata()));
        Element.LOG_HUGE_REDWOOD_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.REDWOOD.metadata()));
        Element.LOG_HUGE_REDWOOD_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.REDWOOD.metadata()));
        Element.LOG_HUGE_REDWOOD_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.REDWOOD.metadata()));
        Element.LOG_HUGE_REDWOOD_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.REDWOOD.metadata()));
        
        // Create the recipies to update logs
        
        BlockQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderQuarterLog()));

        for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(blockSE, type.metadata());
        }
    }

    private static void createRedRock()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.REDROCK.getEnabled())
            return;

        final BlockRedRock block = new BlockRedRock(2, Material.rock);
        block.setBlockName("extrabiomes.redrock").setHardness(1.5F).setResistance(2.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "pickaxe", 0);
        proxy.registerBlock(block, extrabiomes.items.ItemRedRock.class, "terrain_blocks1");

        Element.RED_ROCK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK.metadata()));
        Element.RED_COBBLE.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_COBBLE.metadata()));
        Element.RED_ROCK_BRICK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()));

        Extrabiomes.postInitEvent(new RedRockActiveEvent(block));
        BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.MOUNTAINRIDGE, block, block);

        ForestryModHelper.addToDiggerBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        for (final BlockRedRock.BlockType type : BlockRedRock.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(block, type.metadata());
        }
    }

    private static void createSapling()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.SAPLING.getEnabled())
            return;

        final BlockCustomSapling block = new BlockCustomSapling(16);
        block.setBlockName("extrabiomes.sapling").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemSapling.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("treeSapling", block);

        Element.SAPLING_ACACIA.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.ACACIA.metadata()));
        Element.SAPLING_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.UMBER.metadata()));
        Element.SAPLING_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.GOLDENROD.metadata()));
        Element.SAPLING_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.VERMILLION.metadata()));
        Element.SAPLING_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.CITRINE.metadata()));
        Element.SAPLING_FIR.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.FIR.metadata()));
        Element.SAPLING_REDWOOD.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.REDWOOD.metadata()));
        Element.SAPLING_CYPRESS.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.CYPRESS.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);

        // Temp fix so that NEI shows the fermenter recipies when you try to view uses of saplings.
        //ForestryModHelper.registerSapling(stack);
        ForestryModHelper.registerSapling(Element.SAPLING_ACACIA.get());
        ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_BROWN.get());
        ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_ORANGE.get());
        ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_PURPLE.get());
        ForestryModHelper.registerSapling(Element.SAPLING_AUTUMN_YELLOW.get());
        ForestryModHelper.registerSapling(Element.SAPLING_FIR.get());
        ForestryModHelper.registerSapling(Element.SAPLING_REDWOOD.get());
        ForestryModHelper.registerSapling(Element.SAPLING_CYPRESS.get());
        ForestryModHelper.addToForesterBackpack(stack);

        // all but redwood
        final Element[] forestrySaplings = { Element.SAPLING_ACACIA, Element.SAPLING_AUTUMN_BROWN, Element.SAPLING_AUTUMN_ORANGE, Element.SAPLING_AUTUMN_PURPLE, Element.SAPLING_AUTUMN_YELLOW, Element.SAPLING_FIR, Element.SAPLING_CYPRESS };
        for (final Element sapling : forestrySaplings)
        {
            ForestryModHelper.registerGermling(sapling.get());
        }

        proxy.registerEventHandler(new SaplingBonemealEventHandler(block));
        proxy.registerFuelHandler(new SaplingFuelHandler(block));
    }

    private static void createNewSapling()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWSAPLING.getEnabled())
            return;

        final BlockNewSapling block = new BlockNewSapling();
        block.setBlockName("extrabiomes.sapling").setHardness(0.0F).setStepSound(Block.soundTypeGrass).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemNewSapling.class,  block.getUnlocalizedName());
        proxy.registerOreInAllSubblocks("treeSapling", block);

        Element.SAPLING_BALD_CYPRESS.set(new ItemStack(block, 1, BlockNewSapling.BlockType.BALD_CYPRESS.metadata()));
        Element.SAPLING_JAPANESE_MAPLE.set(new ItemStack(block, 1, BlockNewSapling.BlockType.JAPANESE_MAPLE.metadata()));
        Element.SAPLING_JAPANESE_MAPLE_SHRUB.set(new ItemStack(block, 1, BlockNewSapling.BlockType.JAPANESE_MAPLE_SHRUB.metadata()));
        Element.SAPLING_RAINBOW_EUCALYPTUS.set(new ItemStack(block, 1, BlockNewSapling.BlockType.RAINBOW_EUCALYPTUS.metadata()));
        Element.SAPLING_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockNewSapling.BlockType.SAKURA_BLOSSOM.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);

        // Temp fix so that NEI shows the fermenter recipies when you try to view uses of saplings.
        //ForestryModHelper.registerSapling(stack);
        ForestryModHelper.registerSapling(Element.SAPLING_BALD_CYPRESS.get());
        ForestryModHelper.registerSapling(Element.SAPLING_JAPANESE_MAPLE.get());
        ForestryModHelper.registerSapling(Element.SAPLING_JAPANESE_MAPLE_SHRUB.get());
        ForestryModHelper.registerSapling(Element.SAPLING_RAINBOW_EUCALYPTUS.get());
        ForestryModHelper.registerSapling(Element.SAPLING_SAKURA_BLOSSOM.get());
        ForestryModHelper.addToForesterBackpack(stack);

        // all but redwood
        final Element[] forestrySaplings = { Element.SAPLING_JAPANESE_MAPLE, Element.SAPLING_JAPANESE_MAPLE_SHRUB, Element.SAPLING_SAKURA_BLOSSOM };
        for (final Element sapling : forestrySaplings)
        {
            ForestryModHelper.registerGermling(sapling.get());
        }

        proxy.registerEventHandler(new SaplingBonemealNewEventHandler(block));
        proxy.registerFuelHandler(new SaplingFuelHandler(block));
    }

    private static void createWood()
    {
        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.CUSTOMLOG.getEnabled())
            return;

        final BlockCustomLog block = new BlockCustomLog();
        block.setBlockName("extrabiomes.log").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class, "log1");
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        Blocks.fire.setFireInfo(block, 5, 5);

        Element.LOG_ACACIA.set(new ItemStack(block, 1, BlockCustomLog.BlockType.ACACIA.metadata()));
        Element.LOG_FIR.set(new ItemStack(block, 1, BlockCustomLog.BlockType.FIR.metadata()));
        Element.LOG_CYPRESS.set(new ItemStack(block, 1, BlockCustomLog.BlockType.CYPRESS.metadata()));
        Element.LOG_JAPANESE_MAPLE.set(new ItemStack(block, 1, BlockCustomLog.BlockType.JAPANESE_MAPLE.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(block, type.metadata());
        }

        if (!ModuleControlSettings.SUMMA.isEnabled() || !BlockSettings.NEWLOG.getEnabled())
            return;

        final BlockNewLog block2 = new BlockNewLog(BlockSettings.NEWLOG);
        block2.setBlockName("extrabiomes.newlog").setStepSound(Block.soundTypeWood).setHardness(2.0F).setResistance(Blocks.log.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block2, "axe", 0);
        proxy.registerBlock(block2, extrabiomes.utility.MultiItemBlock.class, "log2");
        proxy.registerOreInAllSubblocks("logWood", block2);
        proxy.registerEventHandler(block2);
        Blocks.fire.setFireInfo(block2, 5, 5);

        Element.LOG_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, BlockNewLog.BlockType.RAINBOW_EUCALYPTUS.metadata()));
        Element.LOG_AUTUMN.set(new ItemStack(block2, 1, BlockNewLog.BlockType.AUTUMN.metadata()));
        Element.LOG_BALD_CYPRESS.set(new ItemStack(block2, 1, BlockNewLog.BlockType.BALD_CYPRESS.metadata()));
        Element.LOG_REDWOOD.set(new ItemStack(block2, 1, BlockNewLog.BlockType.REDWOOD.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block2, 1, Short.MAX_VALUE));
        for (final BlockNewLog.BlockType type : BlockNewLog.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(block2, type.metadata());
        }
    }

}
