/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import extrabiomes.blocks.BlockCropBasic;

public class CropBonemealEventHandler
{
    
	private final BlockCropBasic	crop;
    
	public CropBonemealEventHandler(BlockCropBasic crop)
    {
		this.crop = crop;
    }
    
    @SubscribeEvent
    public void onBonemealEvent(BonemealEvent e)
    {
		if (e.getResult() == Result.DEFAULT && e.getBlock().equals(crop))
        {
			final World world = e.getWorld();
            if (!world.isRemote)
            {
                if (world.rand.nextFloat() < 0.45D)
                {
					crop.markOrGrowMarked(world, e.getPos(), world.rand);
                }
                
                e.setResult(Result.ALLOW);
            }
        }
    }
    
}
