/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.core.helper;

import net.minecraft.src.ItemStack;
import extrabiomes.Extrabiomes;
import extrabiomes.blocks.BlockAutumnLeaves;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.Element;
import extrabiomes.lib.ModuleControlSettings;
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

        ForestryModHelper.registerLeaves(new ItemStack(block, 1, -1));
    }

    public static void createBlocks() {
        createAutumnLeaves();
    }

}
