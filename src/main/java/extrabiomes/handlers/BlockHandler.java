/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.util.Collection;

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
import extrabiomes.blocks.BlockMachine;
import extrabiomes.blocks.BlockMiniLog;
import extrabiomes.blocks.BlockMoreLeaves;
import extrabiomes.blocks.BlockNewLeaves;
import extrabiomes.blocks.BlockNewLog;
import extrabiomes.blocks.BlockNewQuarterLog;
import extrabiomes.blocks.BlockNewSapling;
import extrabiomes.blocks.BlockQuarterLog;
import extrabiomes.blocks.BlockRedRock;
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
import extrabiomes.lib.Blocks;
import extrabiomes.lib.Element;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.summa.worldgen.CatTailGenerator;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.module.summa.worldgen.LeafPileGenerator;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.renderers.RenderKneeLog;
import extrabiomes.renderers.RenderMiniLog;
import extrabiomes.renderers.RenderNewQuarterLog;
import extrabiomes.renderers.RenderQuarterLog;

public abstract class BlockHandler
{

    private static void createAutumnLeaves()
    {
        final int blockID = BlockSettings.AUTUMNLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockAutumnLeaves block = new BlockAutumnLeaves(blockID, 3, Material.leaves, false);
        block.setUnlocalizedName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomLeaves.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        proxy.setBurnProperties(block.blockID, 30, 60);

        Element.LEAVES_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.UMBER.metadata()));
        Element.LEAVES_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.GOLDENROD.metadata()));
        Element.LEAVES_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.VERMILLION.metadata()));
        Element.LEAVES_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.CITRINE.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    public static void createBlocks()
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
		createMachines();
    }

    private static void createCattail()
    {
        final int blockID = BlockSettings.CATTAIL.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockCatTail block = new BlockCatTail(blockID, 79, Material.plants);
        block.setUnlocalizedName("extrabiomes.cattail").setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCatTail.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOre("reedTypha", block);

        Element.CATTAIL.set(new ItemStack(block));

        proxy.registerWorldGenerator(new CatTailGenerator(block.blockID));
    }

    private static void createCrackedSand()
    {
        final int blockID = BlockSettings.CRACKEDSAND.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final GenericTerrainBlock block = new GenericTerrainBlock(blockID, 0, Material.rock);
        block.setUnlocalizedName("extrabiomes.crackedsand").setHardness(1.2F).setStepSound(Block.soundStoneFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        block.texturePath = "crackedsand";

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "pickaxe", 0);
        proxy.registerBlock(block, block.getUnlocalizedName() + ":" + block.getClass().getName());

        proxy.registerOre("sandCracked", block);

        final ItemStack stack = new ItemStack(block);
        Element.CRACKEDSAND.set(stack);

        BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.WASTELAND, block.blockID, block.blockID);

        ForestryModHelper.addToDiggerBackpack(stack);
        FacadeHelper.addBuildcraftFacade(block.blockID);
    }

	private static void createFlowers()
    {
		if (!ModuleControlSettings.SUMMA.isEnabled()) return;

		final int blockIDs[] = { BlockSettings.FLOWER.getID(),
				BlockSettings.FLOWER2.getID(), BlockSettings.FLOWER3.getID() };
		
		final CommonProxy proxy = Extrabiomes.proxy;
		final FlowerGenerator generator = new FlowerGenerator();
		
		for (int group = 0; group < blockIDs.length; ++group) {
			final int blockID = blockIDs[group];
			if (blockID <= 0) return;

	        final BlockCustomFlower block = new BlockCustomFlower(blockID, group, Material.plants);
			block.setUnlocalizedName(
					"extrabiomes.flower" + ( group > 0 ? group : "" ))
					.setTickRandomly(true).setHardness(0.0F)
					.setStepSound(Block.soundGrassFootstep)
					.setCreativeTab(Extrabiomes.tabsEBXL);
			proxy.registerBlock(
					block,
					extrabiomes.items.ItemFlower.class,
					block.getUnlocalizedName() + ":"
							+ block.getClass().getName());

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

			}
	
			generator.registerBlock(blockID, types);
	        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
		}
		
		proxy.registerWorldGenerator(generator);

		// TODO: register other flowers with forestry

		ForestryModHelper.registerBasicFlower(Element.HYDRANGEA.get());
		ForestryModHelper.registerBasicFlower(Element.BUTTERCUP.get());
		ForestryModHelper.registerBasicFlower(Element.LAVENDER.get());
		ForestryModHelper.registerBasicFlower(Element.CALLA_WHITE.get());
    }

	private static void createVines() {
		if (!ModuleControlSettings.SUMMA.isEnabled()) return;

		final CommonProxy proxy = Extrabiomes.proxy;

		for (BlockCustomVine.BlockType blockType : BlockCustomVine.BlockType
				.values()) {
			final BlockSettings settings;
			try {
				settings = BlockSettings.valueOf(blockType.name());
			} catch (Exception e) {
				LogHelper.severe("Unable to find settings for " + blockType);
				continue;
			}

			final int blockID = settings.getID();
			if (blockID <= 0) continue;

			/*
			 * final String shortName = blockType.name()
			 * .substring(blockType.name().indexOf('_')).toLowerCase();
			 */

			final BlockCustomVine block = new BlockCustomVine(blockID, blockType);
			block.setUnlocalizedName(
					"extrabiomes.vine." + blockType.name().toLowerCase())
					.setCreativeTab(Extrabiomes.tabsEBXL);
			proxy.registerBlock(block, ItemBlock.class,
					block.getUnlocalizedName() + ":"
							+ block.getClass().getName());

			final Element element;
			try {
				element = Element.valueOf("VINE_" + blockType.name());
			} catch (Exception e) {
				LogHelper.warning("No element found for vine " + blockType);
				continue;
			}
			ItemStack item = new ItemStack(block, 1);
			element.set(item);

			ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1,
					Short.MAX_VALUE));
		}
	}

    private static void createGrass()
    {
        final int blockID = BlockSettings.GRASS.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockCustomTallGrass block = new BlockCustomTallGrass(blockID, 48, Material.vine);
        block.setUnlocalizedName("extrabiomes.tallgrass").setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemGrass.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.setBurnProperties(block.blockID, 60, 100);

        Element.GRASS_BROWN.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.BROWN.metadata()));
        Element.GRASS_DEAD.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD.metadata()));
        Element.GRASS_BROWN_SHORT.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.SHORT_BROWN.metadata()));
        Element.GRASS_DEAD_TALL.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD_TALL.metadata()));
        Element.GRASS_DEAD_YELLOW.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD_YELLOW.metadata()));

        ItemStack grassStack = Element.GRASS_BROWN.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(), new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 100);
        grassStack = Element.GRASS_BROWN_SHORT.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(), new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 100);

        grassStack = Element.GRASS_DEAD.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 90);
        grassStack = Element.GRASS_DEAD_YELLOW.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 90);
        grassStack = Element.GRASS_DEAD_TALL.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 35);
    }

    private static void createNewLeaves()
    {
        final int blockID = BlockSettings.NEWLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockNewLeaves block = new BlockNewLeaves(blockID, Material.leaves, false);
        block.setUnlocalizedName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomNewLeaves.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        proxy.setBurnProperties(block.blockID, 30, 60);

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
        final int blockID = BlockSettings.MORELEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockMoreLeaves block = new BlockMoreLeaves(blockID, Material.leaves, false);
        block.setUnlocalizedName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomMoreLeaves.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        proxy.setBurnProperties(block.blockID, 30, 60);

        Element.LEAVES_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockMoreLeaves.BlockType.SAKURA_BLOSSOM.metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    private static void createGreenLeaves()
    {
        final int blockID = BlockSettings.GREENLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockGreenLeaves block = new BlockGreenLeaves(blockID, Material.leaves, false);
        block.setUnlocalizedName("extrabiomes.leaves").setTickRandomly(true).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomGreenLeaves.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        proxy.setBurnProperties(block.blockID, 30, 60);

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
        final int blockID = BlockSettings.LEAFPILE.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockLeafPile block = new BlockLeafPile(blockID, 64, Material.vine);
        block.setUnlocalizedName("extrabiomes.leafpile").setHardness(0.0F).setTickRandomly(true).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.setBurnProperties(block.blockID, 30, 60);

        Element.LEAFPILE.set(new ItemStack(block));

        proxy.registerWorldGenerator(new LeafPileGenerator(block.blockID));
    }

    private static void createLogs()
    {
        createWood();
        createMiniLogs();
        createQuarterLogs();
        createNewQuarterLogs();
        createKneeLogs();
    }

	private static void createMachines() {
		final int blockID = BlockSettings.MACHINE.getID();
		if (blockID <= 0) return;

		final BlockMachine block = new BlockMachine(blockID);
		block.setUnlocalizedName("extrabiomes.machine")
				.setStepSound(Block.soundMetalFootstep).setHardness(2.0F)
				.setResistance(Block.cobblestone.getExplosionResistance(null))
				.setCreativeTab(Extrabiomes.tabsEBXL);

		final CommonProxy proxy = Extrabiomes.proxy;
		proxy.setBlockHarvestLevel(block, "pick", 0);
		proxy.registerBlock(block, net.minecraft.item.ItemBlock.class,
				block.getUnlocalizedName() + ":" + block.getClass().getName());
		proxy.registerEventHandler(block);
	}

    private static void createMiniLogs()
    {
        final int blockID = BlockSettings.MINILOG.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockMiniLog block = new BlockMiniLog(blockID);
        Blocks.BLOCK_LOG_SAKURA_GROVE.set(block);
        block.setUnlocalizedName("extrabiomes.log").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, extrabiomes.items.ItemCustomMiniLog.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        proxy.setBurnProperties(block.blockID, 5, 5);

        Element.LOG_SAKURA_BLOSSOM.set(new ItemStack(block, 1, BlockMiniLog.BlockType.SAKURA_BLOSSOM.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));

        BlockMiniLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderMiniLog()));
    }

    private static void createKneeLogs()
    {
        final BlockKneeLog block = new BlockKneeLog(BlockSettings.KNEELOG.getID(), "baldcypress");
        if (!ModuleControlSettings.SUMMA.isEnabled() || BlockSettings.KNEELOG.getID() <= 0)
            return;

        block.setUnlocalizedName("extrabiomes.cypresskneelog").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        //proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);
        proxy.registerBlock(block, ItemKneeLog.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        proxy.setBurnProperties(block.blockID, 5, 5);

        final BlockKneeLog block2 = new BlockKneeLog(BlockSettings.RAINBOWKNEELOG.getID(), "rainboweucalyptus");
        if (!ModuleControlSettings.SUMMA.isEnabled() || BlockSettings.RAINBOWKNEELOG.getID() <= 0)
            return;

        block2.setUnlocalizedName("extrabiomes.rainbowkneelog").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block2, "axe", 0);
        proxy.registerBlock(block2, ItemKneeLog.class, block2.getUnlocalizedName() + ":" + block2.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block2);
        proxy.registerEventHandler(block2);
        proxy.setBurnProperties(block2.blockID, 5, 5);

        Element.LOG_KNEE_BALD_CYPRESS.set(new ItemStack(block, 1, Short.MAX_VALUE));
        Element.LOG_KNEE_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, Short.MAX_VALUE));

        BlockKneeLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderKneeLog()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block.blockID, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block2.blockID, 1, Short.MAX_VALUE));

        FacadeHelper.addBuildcraftFacade(block.blockID);
        FacadeHelper.addBuildcraftFacade(block2.blockID);

    }

    private static void createNewQuarterLogs()
    {
        final CommonProxy proxy = Extrabiomes.proxy;
    	BlockNewQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderNewQuarterLog()));

        final BlockNewQuarterLog block = new BlockNewQuarterLog(BlockSettings.NEWQUARTERLOG.getID(), "baldcypress");
        if (!ModuleControlSettings.SUMMA.isEnabled() || BlockSettings.NEWQUARTERLOG.getID() <= 0)
            return;

        block.setUnlocalizedName("extrabiomes.baldcypressquarter").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, ItemNewQuarterLog.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        proxy.setBurnProperties(block.blockID, 5, 5);

        final BlockNewQuarterLog block2 = new BlockNewQuarterLog(BlockSettings.RAINBOWQUARTERLOG.getID(), "rainboweucalyptus");
        if (!ModuleControlSettings.SUMMA.isEnabled() || BlockSettings.RAINBOWQUARTERLOG.getID() <= 0)
            return;

        block2.setUnlocalizedName("extrabiomes.rainboweucalyptusquarter").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block2, "axe", 0);
        proxy.registerBlock(block2, ItemNewQuarterLog.class, block2.getUnlocalizedName() + ":" + block2.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block2);
        proxy.registerEventHandler(block2);
        proxy.setBurnProperties(block2.blockID, 5, 5);

        final BlockNewQuarterLog block3 = new BlockNewQuarterLog(BlockSettings.OAKQUARTERLOG.getID(), "oak");
        if (!ModuleControlSettings.SUMMA.isEnabled() || BlockSettings.OAKQUARTERLOG.getID() <= 0)
            return;

        block3.setUnlocalizedName("extrabiomes.oakquarter").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block3, "axe", 0);
        proxy.registerBlock(block3, ItemNewQuarterLog.class, block3.getUnlocalizedName() + ":" + block3.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block3);
        proxy.registerEventHandler(block3);
        proxy.setBurnProperties(block3.blockID, 5, 5);

        final BlockNewQuarterLog block4 = new BlockNewQuarterLog(BlockSettings.FIRQUARTERLOG.getID(), "fir");
        if (!ModuleControlSettings.SUMMA.isEnabled() && BlockSettings.FIRQUARTERLOG.getID() <= 0)
            return;

        block4.setUnlocalizedName("extrabiomes.firquarter").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block4, "axe", 0);
        proxy.registerBlock(block4, ItemNewQuarterLog.class, block4.getUnlocalizedName() + ":" + block4.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block4);
        proxy.registerEventHandler(block4);
        proxy.setBurnProperties(block4.blockID, 5, 5);

        final BlockNewQuarterLog block5 = new BlockNewQuarterLog(BlockSettings.REDWOODQUARTERLOG.getID(), "redwood");
        if (!ModuleControlSettings.SUMMA.isEnabled() || BlockSettings.REDWOODQUARTERLOG.getID() <= 0)
            return;

        block5.setUnlocalizedName("extrabiomes.redwoodquarter").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);
        //block5.setRenderId(renderId);

        proxy.setBlockHarvestLevel(block5, "axe", 0);
        proxy.registerBlock(block5, ItemNewQuarterLog.class, block5.getUnlocalizedName() + ":" + block5.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block5);
        proxy.registerEventHandler(block5);
        proxy.setBurnProperties(block5.blockID, 5, 5);


        Element.LOG_QUARTER_BALD_CYPRESS.set(new ItemStack(block, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_OAK.set(new ItemStack(block3, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_FIR.set(new ItemStack(block4, 1, Short.MAX_VALUE));
        Element.LOG_QUARTER_REDWOOD.set(new ItemStack(block5, 1, Short.MAX_VALUE));

        //BlockNewQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderNewQuarterLog()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block.blockID, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block2.blockID, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block3.blockID, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block4.blockID, 1, Short.MAX_VALUE));
        ForestryModHelper.addToForesterBackpack(new ItemStack(block5.blockID, 1, Short.MAX_VALUE));
        FacadeHelper.addBuildcraftFacade(block.blockID);
        FacadeHelper.addBuildcraftFacade(block2.blockID);
        FacadeHelper.addBuildcraftFacade(block3.blockID);
        FacadeHelper.addBuildcraftFacade(block4.blockID);
        FacadeHelper.addBuildcraftFacade(block5.blockID);

    }

    private static void createQuarterLogs()
    {
        final int blockIDNW = BlockSettings.QUARTERLOG0.getID();
        final int blockIDNE = BlockSettings.QUARTERLOG1.getID();
        final int blockIDSW = BlockSettings.QUARTERLOG2.getID();
        final int blockIDSE = BlockSettings.QUARTERLOG3.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockIDNE <= 0 || blockIDNW <= 0 || blockIDSE <= 0 || blockIDSW <= 0)
            return;

        final BlockQuarterLog blockNW = new BlockQuarterLog(blockIDNW, 144, BlockQuarterLog.BarkOn.NW);
        final BlockQuarterLog blockNE = new BlockQuarterLog(blockIDNE, 144, BlockQuarterLog.BarkOn.NE);
        final BlockQuarterLog blockSW = new BlockQuarterLog(blockIDSW, 144, BlockQuarterLog.BarkOn.SW);
        final BlockQuarterLog blockSE = new BlockQuarterLog(blockIDSE, 144, BlockQuarterLog.BarkOn.SE);

        for (final BlockQuarterLog block : new BlockQuarterLog[] { blockNW, blockNE, blockSW, blockSE })
        {
            block.setUnlocalizedName("extrabiomes.log.quarter").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F); //*/.setCreativeTab(Extrabiomes.tabsEBXL);

            final CommonProxy proxy = Extrabiomes.proxy;
            proxy.setBlockHarvestLevel(block, "axe", 0);
            proxy.registerBlock(block, ItemOldQuarterLog.class, block.getUnlocalizedName() + ":" + block.getBarkOnSides().toString() + ":" + block.getClass().getName());
            proxy.registerOreInAllSubblocks("logWood", block);
            proxy.registerEventHandler(block);
            proxy.setBurnProperties(block.blockID, 5, 5);
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
            FacadeHelper.addBuildcraftFacade(blockSE.blockID, type.metadata());
        }
    }

    private static void createRedRock()
    {
        final int blockID = BlockSettings.REDROCK.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockRedRock block = new BlockRedRock(blockID, 2, Material.rock);
        block.setUnlocalizedName("extrabiomes.redrock").setHardness(1.5F).setResistance(2.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "pickaxe", 0);
        proxy.registerBlock(block, extrabiomes.items.ItemRedRock.class, block.getUnlocalizedName() + ":" + block.getClass().getName());

        Element.RED_ROCK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK.metadata()));
        Element.RED_COBBLE.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_COBBLE.metadata()));
        Element.RED_ROCK_BRICK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK_BRICK.metadata()));

        Extrabiomes.postInitEvent(new RedRockActiveEvent(block));
        BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.MOUNTAINRIDGE, block.blockID, block.blockID);

        ForestryModHelper.addToDiggerBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        for (final BlockRedRock.BlockType type : BlockRedRock.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(block.blockID, type.metadata());
        }
    }

    private static void createSapling()
    {
        final int blockID = BlockSettings.SAPLING.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockCustomSapling block = new BlockCustomSapling(blockID, 16);
        block.setUnlocalizedName("extrabiomes.sapling").setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemSapling.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
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
        proxy.registerFuelHandler(new SaplingFuelHandler(block.blockID));
    }

    private static void createNewSapling()
    {
        final int blockID = BlockSettings.NEWSAPLING.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockNewSapling block = new BlockNewSapling(blockID);
        block.setUnlocalizedName("extrabiomes.sapling").setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemNewSapling.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
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
        proxy.registerFuelHandler(new SaplingFuelHandler(block.blockID));
    }

    private static void createWood()
    {
        final int blockID = BlockSettings.CUSTOMLOG.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0)
            return;

        final BlockCustomLog block = new BlockCustomLog(blockID);
        block.setUnlocalizedName("extrabiomes.log").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class, block.getUnlocalizedName() + ":" + block.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        proxy.setBurnProperties(block.blockID, 5, 5);

        Element.LOG_ACACIA.set(new ItemStack(block, 1, BlockCustomLog.BlockType.ACACIA.metadata()));
        Element.LOG_FIR.set(new ItemStack(block, 1, BlockCustomLog.BlockType.FIR.metadata()));
        Element.LOG_CYPRESS.set(new ItemStack(block, 1, BlockCustomLog.BlockType.CYPRESS.metadata()));
        Element.LOG_JAPANESE_MAPLE.set(new ItemStack(block, 1, BlockCustomLog.BlockType.JAPANESE_MAPLE.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(block.blockID, type.metadata());
        }

        final int newblockID = BlockSettings.NEWLOG.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || newblockID <= 0)
            return;

        final BlockNewLog block2 = new BlockNewLog(newblockID);
        block2.setUnlocalizedName("extrabiomes.newlog").setStepSound(Block.soundWoodFootstep).setHardness(2.0F).setResistance(Block.wood.getExplosionResistance(null) * 5.0F).setCreativeTab(Extrabiomes.tabsEBXL);

        proxy.setBlockHarvestLevel(block2, "axe", 0);
        proxy.registerBlock(block2, extrabiomes.utility.MultiItemBlock.class, block2.getUnlocalizedName() + ":" + block2.getClass().getName());
        proxy.registerOreInAllSubblocks("logWood", block2);
        proxy.registerEventHandler(block2);
        proxy.setBurnProperties(block2.blockID, 5, 5);

        Element.LOG_RAINBOW_EUCALYPTUS.set(new ItemStack(block2, 1, BlockNewLog.BlockType.RAINBOW_EUCALYPTUS.metadata()));
        Element.LOG_AUTUMN.set(new ItemStack(block2, 1, BlockNewLog.BlockType.AUTUMN.metadata()));
        Element.LOG_BALD_CYPRESS.set(new ItemStack(block2, 1, BlockNewLog.BlockType.BALD_CYPRESS.metadata()));
        Element.LOG_REDWOOD.set(new ItemStack(block2, 1, BlockNewLog.BlockType.REDWOOD.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block2, 1, Short.MAX_VALUE));
        for (final BlockNewLog.BlockType type : BlockNewLog.BlockType.values())
        {
            FacadeHelper.addBuildcraftFacade(block2.blockID, type.metadata());
        }
    }

}
