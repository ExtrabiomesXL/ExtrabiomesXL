/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.event.entity.player.PlayerEvent;

@Cancelable
public class UseLogTurnerEvent extends PlayerEvent
{
    
    private final ItemStack current;
    private final World     world;
    private final BlockPos  pos;
    
    private boolean        handled = false;
    
    public UseLogTurnerEvent(EntityPlayer player, ItemStack current,
            World world, BlockPos pos)
    {
        super(player);
        this.current = current;
        this.world = world;
        this.pos = pos;
    }
    
    public World getWorld() {
    	return world;
    }
    
    public ItemStack getCurrent() {
    	return current;
    }
    
    public BlockPos getPos() {
    	return pos;
    }
    
    public boolean isHandled()
    {
        return handled;
    }
    
    public void setHandled()
    {
        handled = true;
    }
}
