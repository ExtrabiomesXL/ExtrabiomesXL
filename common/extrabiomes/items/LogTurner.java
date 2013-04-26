/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;

public class LogTurner extends ItemTool {

    public LogTurner(int id) {
        super(id, 1, EnumToolMaterial.WOOD, new Block[] {});
    }
       
    @Override
    public void registerIcons(IconRegister iconRegister)
    {
    	itemIcon = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logturner");
    }

    
    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean isItemTool(ItemStack par1ItemStack) {
        return true;
    }

    @Override
    public boolean onItemUse(ItemStack itemUsed, EntityPlayer player, World world, int x, int y,
            int z, int side, float xOffset, float yOffset, float zOffset)
    {
        if (!player.canPlayerEdit(x, y, z, side, itemUsed)) return false;

        final UseLogTurnerEvent event = new UseLogTurnerEvent(player, itemUsed, world, x, y, z);
        if (Extrabiomes.proxy.postEventToBus(event)) return false;
        if (event.isHandled()) return true;

        final int blockID = world.getBlockId(x, y, z);

        if (blockID != Block.wood.blockID) return false;
        final Block wood = Block.wood;
        world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, wood.stepSound.getStepSound(),
                (wood.stepSound.getVolume() + 1.0F) / 2.0F, wood.stepSound.getPitch() * 1.55F);

        if (!world.isRemote) {
            final int metadata = world.getBlockMetadata(x, y, z);
            int orientation = metadata & 12;
            final int type = metadata & 3;

            orientation = orientation == 0 ? 4 : orientation == 4 ? 8 : 0;
            world.setBlock(x, y, z, blockID, type | orientation, 3);
        }
        return true;
    }
}
