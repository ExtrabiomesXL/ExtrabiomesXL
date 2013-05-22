/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import extrabiomes.Extrabiomes;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCatTail;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomLog;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockCustomTallGrass;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.blocks.BlockLeafPile;
import extrabiomes.blocks.BlockQuarterLog;
import extrabiomes.blocks.BlockRedRock;
import extrabiomes.blocks.GenericTerrainBlock;
import extrabiomes.events.BlockActiveEvent.RedRockActiveEvent;
import extrabiomes.helpers.BiomeHelper;
import extrabiomes.helpers.ForestryModHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.module.amica.buildcraft.FacadeHelper;
import extrabiomes.module.summa.worldgen.CatTailGenerator;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.module.summa.worldgen.LeafPileGenerator;
import extrabiomes.proxy.CommonProxy;
import extrabiomes.renderers.RenderQuarterLog;

public abstract class BlockHandler {

    private static void createAutumnLeaves() {
        final int blockID = BlockSettings.AUTUMNLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockAutumnLeaves block = new BlockAutumnLeaves(blockID, 3, Material.leaves, false);
        block.setUnlocalizedName("extrabiomes.autumnleaves").setTickRandomly(true).setHardness(0.2F)
                .setLightOpacity(1).setStepSound(Block.soundGrassFootstep)/*.setRequiresSelfNotify()*/
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomLeaves.class);
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        proxy.setBurnProperties(block.blockID, 30, 60);

        Element.LEAVES_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.BROWN
                .metadata()));
        Element.LEAVES_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.ORANGE
                .metadata()));
        Element.LEAVES_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.PURPLE
                .metadata()));
        Element.LEAVES_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.YELLOW
                .metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    public static void createBlocks() {
        createAutumnLeaves();
        createCattail();
        createCrackedSand();
        createFlower();
        createGrass();
        createGreenLeaves();
        createLeafPile();
        createRedRock();
        createSapling();
        createLogs();
    }

    private static void createCattail() {
        final int blockID = BlockSettings.CATTAIL.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCatTail block = new BlockCatTail(blockID, 79, Material.plants);
        block.setUnlocalizedName("extrabiomes.cattail").setHardness(0.0F)
                .setStepSound(Block.soundGrassFootstep)
               /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCatTail.class);
        proxy.registerOre("reedTypha", block);

        Element.CATTAIL.set(new ItemStack(block));

        proxy.registerWorldGenerator(new CatTailGenerator(block.blockID));
    }

    private static void createCrackedSand() {
        final int blockID = BlockSettings.CRACKEDSAND.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final GenericTerrainBlock block = new GenericTerrainBlock(blockID, 0, Material.rock);
        block.setUnlocalizedName("extrabiomes.crackedsand").setHardness(1.2F)
                .setStepSound(Block.soundStoneFootstep)
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);
        
        block.texturePath = "crackedsand";

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "pickaxe", 0);
        proxy.registerBlock(block);

        proxy.registerOre("sandCracked", block);

        final ItemStack stack = new ItemStack(block);
        Element.CRACKEDSAND.set(stack);

        BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.WASTELAND, block.blockID, block.blockID);

        ForestryModHelper.addToDiggerBackpack(stack);
        FacadeHelper.addBuildcraftFacade(block.blockID);
    }

    private static void createFlower() {
        final int blockID = BlockSettings.FLOWER.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCustomFlower block = new BlockCustomFlower(blockID, 32, Material.plants);
        block.setUnlocalizedName("extrabiomes.flower").setTickRandomly(true).setHardness(0.0F)
                .setStepSound(Block.soundGrassFootstep)
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemFlower.class);

        Element.AUTUMN_SHRUB.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.AUTUMN_SHRUB
                .metadata()));
        Element.HYDRANGEA.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.HYDRANGEA
                .metadata()));
        Element.FLOWER_ORANGE.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.ORANGE
                .metadata()));
        Element.FLOWER_PURPLE.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.PURPLE
                .metadata()));
        Element.FLOWER_WHITE.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.WHITE
                .metadata()));
        Element.ROOT.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.ROOT.metadata()));
        Element.TINY_CACTUS.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.TINY_CACTUS
                .metadata()));
        Element.TOADSTOOL.set(new ItemStack(block, 1, BlockCustomFlower.BlockType.TOADSTOOL
                .metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));

        ForestryModHelper.registerBasicFlower(Element.HYDRANGEA.get());
        ForestryModHelper.registerBasicFlower(Element.FLOWER_ORANGE.get());
        ForestryModHelper.registerBasicFlower(Element.FLOWER_PURPLE.get());
        ForestryModHelper.registerBasicFlower(Element.FLOWER_WHITE.get());

        proxy.registerWorldGenerator(new FlowerGenerator(block.blockID));
    }

    private static void createGrass() {
        final int blockID = BlockSettings.GRASS.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCustomTallGrass block = new BlockCustomTallGrass(blockID, 48, Material.vine);
        block.setUnlocalizedName("extrabiomes.tallgrass").setHardness(0.0F)
                .setStepSound(Block.soundGrassFootstep)
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemGrass.class);
        proxy.setBurnProperties(block.blockID, 60, 100);

        Element.GRASS_BROWN.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.BROWN
                .metadata()));
        Element.GRASS_DEAD.set(new ItemStack(block, 1, BlockCustomTallGrass.BlockType.DEAD
                .metadata()));
        Element.GRASS_BROWN_SHORT.set(new ItemStack(block, 1,
                BlockCustomTallGrass.BlockType.SHORT_BROWN.metadata()));
        Element.GRASS_DEAD_TALL.set(new ItemStack(block, 1,
                BlockCustomTallGrass.BlockType.DEAD_TALL.metadata()));
        Element.GRASS_DEAD_YELLOW.set(new ItemStack(block, 1,
                BlockCustomTallGrass.BlockType.DEAD_YELLOW.metadata()));

        ItemStack grassStack = Element.GRASS_BROWN.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(),
                new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 100);
        grassStack = Element.GRASS_BROWN_SHORT.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.MOUNTAINRIDGE.getBiome(),
                new WorldGenTallGrass(grassStack.itemID, grassStack.getItemDamage()), 100);

        grassStack = Element.GRASS_DEAD.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(
                grassStack.itemID, grassStack.getItemDamage()), 90);
        grassStack = Element.GRASS_DEAD_YELLOW.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(
                grassStack.itemID, grassStack.getItemDamage()), 90);
        grassStack = Element.GRASS_DEAD_TALL.get();
        BiomeHelper.addWeightedGrassGen(BiomeSettings.WASTELAND.getBiome(), new WorldGenTallGrass(
                grassStack.itemID, grassStack.getItemDamage()), 35);
    }

    private static void createGreenLeaves() {
        final int blockID = BlockSettings.GREENLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockGreenLeaves block = new BlockGreenLeaves(blockID, Material.leaves, false);
        block.setUnlocalizedName("extrabiomes.greenleaves").setTickRandomly(true).setHardness(0.2F)
                .setLightOpacity(1).setStepSound(Block.soundGrassFootstep)/*.setRequiresSelfNotify()*/
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemCustomGreenLeaves.class);
        proxy.registerOreInAllSubblocks("treeLeaves", block);
        proxy.setBurnProperties(block.blockID, 30, 60);

        Element.LEAVES_ACACIA.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.ACACIA
                .metadata()));
        Element.LEAVES_FIR.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.FIR.metadata()));
        Element.LEAVES_REDWOOD.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.REDWOOD
                .metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForesterBackpack(stack);
    }

    private static void createLeafPile() {
        final int blockID = BlockSettings.LEAFPILE.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockLeafPile block = new BlockLeafPile(blockID, 64, Material.vine);
        block.setUnlocalizedName("extrabiomes.leafpile").setHardness(0.0F).setTickRandomly(true)
                .setStepSound(Block.soundGrassFootstep)
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block);
        proxy.setBurnProperties(block.blockID, 30, 60);

        Element.LEAFPILE.set(new ItemStack(block));

        proxy.registerWorldGenerator(new LeafPileGenerator(block.blockID));
    }

    private static void createLogs() {
        createWood();
        createQuarterLogs();
    }

    private static void createQuarterLogs() {
        final int blockIDNW = BlockSettings.QUARTERLOG0.getID();
        final int blockIDNE = BlockSettings.QUARTERLOG1.getID();
        final int blockIDSW = BlockSettings.QUARTERLOG2.getID();
        final int blockIDSE = BlockSettings.QUARTERLOG3.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockIDNE <= 0 || blockIDNW <= 0
                || blockIDSE <= 0 || blockIDSW <= 0) return;

        final BlockQuarterLog blockNW = new BlockQuarterLog(blockIDNW, 144,
                BlockQuarterLog.BarkOn.NW);
        final BlockQuarterLog blockNE = new BlockQuarterLog(blockIDNE, 144,
                BlockQuarterLog.BarkOn.NE);
        final BlockQuarterLog blockSW = new BlockQuarterLog(blockIDSW, 144,
                BlockQuarterLog.BarkOn.SW);
        final BlockQuarterLog blockSE = new BlockQuarterLog(blockIDSE, 144,
                BlockQuarterLog.BarkOn.SE);

        for (final Block block : new Block[] { blockNW, blockNE, blockSW, blockSE }) {
            block.setUnlocalizedName("extrabiomes.log.quarter").setStepSound(Block.soundWoodFootstep)
                    /*.setRequiresSelfNotify()*/.setHardness(2.0F)
                    .setResistance(Block.wood.getExplosionResistance(null) * 5.0F)
                    /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                    .setCreativeTab(Extrabiomes.tabsEBXL);

            final CommonProxy proxy = Extrabiomes.proxy;
            proxy.setBlockHarvestLevel(block, "axe", 0);
            proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);
            proxy.registerOreInAllSubblocks("logWood", block);
            proxy.registerEventHandler(block);
            proxy.setBurnProperties(block.blockID, 5, 5);
        }

        Element.LOG_HUGE_FIR_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.FIR
                .metadata()));
        Element.LOG_HUGE_FIR_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.FIR
                .metadata()));
        Element.LOG_HUGE_FIR_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.FIR
                .metadata()));
        Element.LOG_HUGE_FIR_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.FIR
                .metadata()));
        Element.LOG_HUGE_OAK_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.OAK
                .metadata()));
        Element.LOG_HUGE_OAK_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.OAK
                .metadata()));
        Element.LOG_HUGE_OAK_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.OAK
                .metadata()));
        Element.LOG_HUGE_OAK_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.OAK
                .metadata()));
        Element.LOG_HUGE_REDWOOD_NW.set(new ItemStack(blockNW, 1, BlockQuarterLog.BlockType.REDWOOD
                .metadata()));
        Element.LOG_HUGE_REDWOOD_NE.set(new ItemStack(blockNE, 1, BlockQuarterLog.BlockType.REDWOOD
                .metadata()));
        Element.LOG_HUGE_REDWOOD_SW.set(new ItemStack(blockSW, 1, BlockQuarterLog.BlockType.REDWOOD
                .metadata()));
        Element.LOG_HUGE_REDWOOD_SE.set(new ItemStack(blockSE, 1, BlockQuarterLog.BlockType.REDWOOD
                .metadata()));

        BlockQuarterLog.setRenderId(Extrabiomes.proxy.registerBlockHandler(new RenderQuarterLog()));

        for (final BlockQuarterLog.BlockType type : BlockQuarterLog.BlockType.values())
            FacadeHelper.addBuildcraftFacade(blockSE.blockID, type.metadata());
    }

    private static void createRedRock() {
        final int blockID = BlockSettings.REDROCK.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockRedRock block = new BlockRedRock(blockID, 2, Material.rock);
        block.setUnlocalizedName("extrabiomes.redrock").setHardness(1.5F).setResistance(2.0F)
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "pickaxe", 0);
        proxy.registerBlock(block, extrabiomes.items.ItemRedRock.class);

        Element.RED_ROCK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK.metadata()));
        Element.RED_COBBLE
                .set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_COBBLE.metadata()));
        Element.RED_ROCK_BRICK.set(new ItemStack(block, 1, BlockRedRock.BlockType.RED_ROCK_BRICK
                .metadata()));

        Extrabiomes.postInitEvent(new RedRockActiveEvent(block));
        BiomeHelper.addTerrainBlockstoBiome(BiomeSettings.MOUNTAINRIDGE, block.blockID,
                block.blockID);

        ForestryModHelper.addToDiggerBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        for (final BlockRedRock.BlockType type : BlockRedRock.BlockType.values())
            FacadeHelper.addBuildcraftFacade(block.blockID, type.metadata());
    }

    private static void createSapling() {
        final int blockID = BlockSettings.SAPLING.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCustomSapling block = new BlockCustomSapling(blockID, 16);
        block.setUnlocalizedName("extrabiomes.sapling").setHardness(0.0F)
                .setStepSound(Block.soundGrassFootstep)/*.setRequiresSelfNotify()*/
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.items.ItemSapling.class);
        proxy.registerOreInAllSubblocks("treeSapling", block);

        Element.SAPLING_ACACIA.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.ACACIA
                .metadata()));
        Element.SAPLING_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.BROWN
                .metadata()));
        Element.SAPLING_AUTUMN_ORANGE.set(new ItemStack(block, 1,
                BlockCustomSapling.BlockType.ORANGE.metadata()));
        Element.SAPLING_AUTUMN_PURPLE.set(new ItemStack(block, 1,
                BlockCustomSapling.BlockType.PURPLE.metadata()));
        Element.SAPLING_AUTUMN_YELLOW.set(new ItemStack(block, 1,
                BlockCustomSapling.BlockType.YELLOW.metadata()));
        Element.SAPLING_FIR
                .set(new ItemStack(block, 1, BlockCustomSapling.BlockType.FIR.metadata()));
        Element.SAPLING_REDWOOD.set(new ItemStack(block, 1, BlockCustomSapling.BlockType.REDWOOD
                .metadata()));

        final ItemStack stack = new ItemStack(block, 1, Short.MAX_VALUE);
        ForestryModHelper.registerSapling(stack);
        ForestryModHelper.addToForesterBackpack(stack);

        // all but redwood
        final Element[] forestrySaplings = { Element.SAPLING_ACACIA, Element.SAPLING_AUTUMN_BROWN,
                Element.SAPLING_AUTUMN_ORANGE, Element.SAPLING_AUTUMN_PURPLE,
                Element.SAPLING_AUTUMN_YELLOW, Element.SAPLING_FIR };
        for (final Element sapling : forestrySaplings)
            ForestryModHelper.registerGermling(sapling.get());

        proxy.registerEventHandler(new SaplingBonemealEventHandler(block));
        proxy.registerFuelHandler(new SaplingFuelHandler(block.blockID));
    }

    private static void createWood() {
        final int blockID = BlockSettings.CUSTOMLOG.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCustomLog block = new BlockCustomLog(blockID);
        block.setUnlocalizedName("extrabiomes.log").setStepSound(Block.soundWoodFootstep)
                /*.setRequiresSelfNotify()*/.setHardness(2.0F)
                .setResistance(Block.wood.getExplosionResistance(null) * 5.0F)
                /*.setTextureFile("/extrabiomes/extrabiomes.png")*/
                .setCreativeTab(Extrabiomes.tabsEBXL);

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.setBlockHarvestLevel(block, "axe", 0);
        proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);
        proxy.registerOreInAllSubblocks("logWood", block);
        proxy.registerEventHandler(block);
        proxy.setBurnProperties(block.blockID, 5, 5);

        Element.LOG_ACACIA.set(new ItemStack(block, 1, BlockCustomLog.BlockType.ACACIA.metadata()));
        Element.LOG_FIR.set(new ItemStack(block, 1, BlockCustomLog.BlockType.FIR.metadata()));

        ForestryModHelper.addToForesterBackpack(new ItemStack(block, 1, Short.MAX_VALUE));
        for (final BlockCustomLog.BlockType type : BlockCustomLog.BlockType.values())
            FacadeHelper.addBuildcraftFacade(block.blockID, type.metadata());
    }

}
