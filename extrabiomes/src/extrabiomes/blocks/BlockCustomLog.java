/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.api.UseLogTurnerEvent;

public class BlockCustomLog extends BlockLog {
    public enum BlockType {
        FIR(0), ACACIA(1);

        private final int metadata;

        BlockType(int metadata) {
            this.metadata = metadata;
        }

        public int metadata() {
            return metadata;
        }
    }

    public BlockCustomLog(int id) {
        super(id);
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata) {
        final int orientation = metadata & 12;
        int type = metadata & 3;
        if (type > 1) type = 0;
        if (orientation == 0 && (side == 1 || side == 0) || orientation == 4
                && (side == 5 || side == 4) || orientation == 8 && (side == 2 || side == 3))
            return blockIndexInTexture + 16 + type;
        return blockIndexInTexture + type;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(int blockID, CreativeTabs par2CreativeTabs, List list) {
        for (final BlockType type : BlockType.values())
            list.add(new ItemStack(blockID, 1, type.metadata()));
    }

    @Override
    public int idDropped(int metadata, Random rand, int unused) {
        return blockID;
    }

    @ForgeSubscribe
    public void onUseLogTurnerEvent(UseLogTurnerEvent event) {
        final int id = event.world.getBlockId(event.x, event.y, event.z);

        if (id == blockID) {
            final Block wood = Block.wood;
            event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F,
                    wood.stepSound.getStepSound(), (wood.stepSound.getVolume() + 1.0F) / 2.0F,
                    wood.stepSound.getPitch() * 1.55F);

            if (!event.world.isRemote) {
                final int metadata = event.world.getBlockMetadata(event.x, event.y, event.z);
                int orientation = metadata & 12;
                final int type = metadata & 3;

                orientation = orientation == 0 ? 4 : orientation == 4 ? 8 : 0;
                event.world.setBlockAndMetadataWithNotify(event.x, event.y, event.z, blockID, type
                        | orientation, 3);
            }
            event.setHandled();
        }
    }
}
