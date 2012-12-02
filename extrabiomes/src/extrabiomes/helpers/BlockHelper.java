/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.helpers;

import net.minecraft.src.ItemStack;
import extrabiomes.Extrabiomes;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.blocks.BlockCustomFlower;
import extrabiomes.blocks.BlockCustomSapling;
import extrabiomes.blocks.BlockGreenLeaves;
import extrabiomes.events.BlockActiveEvent.FlowerActiveEvent;
import extrabiomes.handlers.SaplingBonemealEventHandler;
import extrabiomes.handlers.SaplingFuelHandler;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ModuleControlSettings;
import extrabiomes.module.summa.worldgen.FlowerGenerator;
import extrabiomes.proxy.CommonProxy;

public abstract class BlockHelper {

    private static void createAutumnLeaves() {
        final int blockID = BlockSettings.AUTUMNLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockAutumnLeaves block = new BlockAutumnLeaves(blockID);
        block.setBlockName("extrabiomes.autumnleaves");

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.module.summa.block.ItemCustomLeaves.class);
        proxy.registerOreInAllSubblocks("treeLeaves", block);

        Element.LEAVES_AUTUMN_BROWN.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.BROWN
                .metadata()));
        Element.LEAVES_AUTUMN_ORANGE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.ORANGE
                .metadata()));
        Element.LEAVES_AUTUMN_PURPLE.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.PURPLE
                .metadata()));
        Element.LEAVES_AUTUMN_YELLOW.set(new ItemStack(block, 1, BlockAutumnLeaves.BlockType.YELLOW
                .metadata()));

        final ItemStack stack = new ItemStack(block, 1, -1);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForestryBackpack(stack);
    }

    public static void createBlocks() {
        createAutumnLeaves();
        createGreenLeaves();
        createSapling();
        createFlower();
    }

    private static void createFlower() {
        final int blockID = BlockSettings.FLOWER.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCustomFlower block = new BlockCustomFlower(blockID);
        block.setBlockName("extrabiomes.greenleaves");

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);

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

        final ItemStack stack = new ItemStack(block, 1, -1);
        ForestryModHelper.registerFlower(stack);
        ForestryModHelper.addToForestryBackpack(stack);

        ForestryModHelper.registerBasicFlower(Element.HYDRANGEA.get());
        ForestryModHelper.registerBasicFlower(Element.FLOWER_ORANGE.get());
        ForestryModHelper.registerBasicFlower(Element.FLOWER_PURPLE.get());
        ForestryModHelper.registerBasicFlower(Element.FLOWER_WHITE.get());

        Extrabiomes.postInitEvent(new FlowerActiveEvent(block));

        proxy.registerWorldGenerator(new FlowerGenerator(block.blockID));
    }

    private static void createGreenLeaves() {
        final int blockID = BlockSettings.GREENLEAVES.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockGreenLeaves block = new BlockGreenLeaves(blockID);
        block.setBlockName("extrabiomes.greenleaves");

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.module.summa.block.ItemCustomLeaves.class);
        proxy.registerOreInAllSubblocks("treeLeaves", block);

        Element.LEAVES_ACACIA.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.ACACIA
                .metadata()));
        Element.LEAVES_FIR.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.FIR.metadata()));
        Element.LEAVES_REDWOOD.set(new ItemStack(block, 1, BlockGreenLeaves.BlockType.REDWOOD
                .metadata()));

        final ItemStack stack = new ItemStack(block, 1, -1);
        ForestryModHelper.registerLeaves(stack);
        ForestryModHelper.addToForestryBackpack(stack);
    }

    private static void createSapling() {
        final int blockID = BlockSettings.SAPLING.getID();
        if (!ModuleControlSettings.SUMMA.isEnabled() || blockID <= 0) return;

        final BlockCustomSapling block = new BlockCustomSapling(blockID);
        block.setBlockName("extrabiomes.sapling");

        final CommonProxy proxy = Extrabiomes.proxy;
        proxy.registerBlock(block, extrabiomes.utility.MultiItemBlock.class);
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

        final ItemStack stack = new ItemStack(block, 1, -1);
        ForestryModHelper.registerSapling(stack);
        ForestryModHelper.addToForestryBackpack(stack);

        // all but redwood
        final Element[] forestrySaplings = { Element.SAPLING_ACACIA, Element.SAPLING_AUTUMN_BROWN,
                Element.SAPLING_AUTUMN_ORANGE, Element.SAPLING_AUTUMN_PURPLE,
                Element.SAPLING_AUTUMN_YELLOW, Element.SAPLING_FIR };
        for (final Element sapling : forestrySaplings)
            ForestryModHelper.registerGermling(sapling.get());

        proxy.registerEventHandler(new SaplingBonemealEventHandler(block));
        proxy.registerFuelHandler(new SaplingFuelHandler(block.blockID));
    }

}
