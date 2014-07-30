/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
		if (e.getResult() == Result.DEFAULT && e.block.equals(crop))
        {
            if (!e.world.isRemote)
            {
                if (e.world.rand.nextFloat() < 0.45D)
                {
					crop.markOrGrowMarked(e.world, e.x, e.y, e.z, e.world.rand);
                }
                
                e.setResult(Result.ALLOW);
            }
        }
    }
    
}
