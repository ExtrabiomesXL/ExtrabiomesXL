/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.events;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.eventhandler.Event;

public abstract class BlockActiveEvent extends Event
{
    
    public static class BaldCypressStairsActiveEvent extends BlockActiveEvent
    {
        public BaldCypressStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class AutumnStairsActiveEvent extends BlockActiveEvent
    {
        public AutumnStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class SakuraBlossomStairsActiveEvent extends BlockActiveEvent
    {
        public SakuraBlossomStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class RainbowEucalyptusStairsActiveEvent extends BlockActiveEvent
    {
        public RainbowEucalyptusStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class CypressStairsActiveEvent extends BlockActiveEvent
    {
        public CypressStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class JapaneseMapleStairsActiveEvent extends BlockActiveEvent
    {
        public JapaneseMapleStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class AcaciaStairsActiveEvent extends BlockActiveEvent
    {
        public AcaciaStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class FirStairsActiveEvent extends BlockActiveEvent
    {
        public FirStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class PlankActiveEvent extends BlockActiveEvent
    {
        public PlankActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class RedCobbleStairsActiveEvent extends BlockActiveEvent
    {
        public RedCobbleStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class RedRockActiveEvent extends BlockActiveEvent
    {
        public RedRockActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class RedRockBrickStairsActiveEvent extends BlockActiveEvent
    {
        public RedRockBrickStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class RedRockSlabActiveEvent extends BlockActiveEvent
    {
        public RedRockSlabActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class RedwoodStairsActiveEvent extends BlockActiveEvent
    {
        public RedwoodStairsActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class WoodSlabActiveEvent extends BlockActiveEvent
    {
        public WoodSlabActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class NewWoodSlabActiveEvent extends BlockActiveEvent
    {
        public NewWoodSlabActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class WallActiveEvent extends BlockActiveEvent
    {
        public WallActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class FenceActiveEvent extends BlockActiveEvent
    {
        public FenceActiveEvent(Block block)
        {
            super(block);
        }
    }
    
    public static class FenceGateActiveEvent extends Event
    {
      public final ItemStack gate;
      public final ItemStack wood;

      public FenceGateActiveEvent(ItemStack gate, ItemStack wood) {
        this.gate = gate;
        this.wood = wood;
      }
    }
    
    public static class WoodDoorActiveEvent extends Event {
    	public final ItemStack wood;
    	public final ItemStack door;

  		public WoodDoorActiveEvent(ItemStack door, ItemStack wood) {
  			//super(door);
  			this.door = door;
  			this.wood = wood;
  		}
    	
    }
    
    public final Block block;
    
    protected BlockActiveEvent(Block block)
    {
        this.block = block;
    }
    
}
