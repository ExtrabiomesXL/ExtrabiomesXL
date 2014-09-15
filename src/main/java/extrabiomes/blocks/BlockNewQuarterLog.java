package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
//import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;

public class BlockNewQuarterLog extends BlockLog
{
    private BlockSettings settings;
    private final IIcon[]     textures = { null, null, null, null, null, null, null, null, null };
    private static int renderId = 32;
    private String     treeType = "quarter";
    private ItemStack droppedItem;
    
    public BlockNewQuarterLog(BlockSettings settings, String treeType)
    {
        super();
        this.settings = settings;
        this.treeType = treeType;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "top1");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "top2");
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "top3");
        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "top4");
        
        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "log1");
        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "log2");
        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "side1");
        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + treeType + "side2");
        
        //textures[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "todo");
        
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        final int orientation = metadata;
        
        switch (side)
        {
            case 0:
                return textures[bottopIIcon(orientation)];
            case 1:
                return textures[topIIcon(orientation)];
            case 2:
                return textures[southIIcon(orientation)];
            case 3:
                return textures[northIIcon(orientation)];
            case 4:
                return textures[eastIIcon(orientation)];
            case 5:
                return textures[westIIcon(orientation)];
        }
        
        return textures[4];
        
    }
    
    private int topIIcon(int orientation)
    {
        switch (orientation)
        {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 7;
            case 7:
                return 6;
            case 8:
                return 5;
            case 9:
                return 4;
            case 10:
                return 6;
            case 11:
                return 7;
            default:
                return 8;
        }
    }
    
    private int bottopIIcon(int orientation)
    {
        switch (orientation)
        {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 6;
            case 5:
                return 7;
            case 6:
                return 5;
            case 7:
                return 4;
            case 8:
                return 6;
            case 9:
                return 7;
            case 10:
                return 5;
            case 11:
                return 4;
                
            default:
                return 8;
        }
    }
    
    private int eastIIcon(int orientation)
    {
        switch (orientation)
        {
            case 0:
                return 5;
            case 1:
                return 7;
            case 2:
                return 6;
            case 3:
                return 4;
            case 4:
                return 7;
            case 5:
                return 5;
            case 6:
                return 4;
            case 7:
                return 6;
            case 8:
                return 0;
            case 9:
                return 1;
            case 10:
                return 2;
            case 11:
                return 3;
            default:
                return 8;
        }
    }
    
    private int westIIcon(int orientation)
    {
        switch (orientation)
        {
            case 0:
                return 6;
            case 1:
                return 4;
            case 2:
                return 5;
            case 3:
                return 7;
            case 4:
                return 4;
            case 5:
                return 6;
            case 6:
                return 7;
            case 7:
                return 5;
            case 8:
                return 1;
            case 9:
                return 0;
            case 10:
                return 3;
            case 11:
                return 2;
            default:
                return 8;
        }
    }
    
    private int southIIcon(int orientation)
    {
        switch (orientation)
        {
            case 0:
                return 4;
            case 1:
                return 5;
            case 2:
                return 7;
            case 3:
                return 6;
                
            case 4:
                return 0;
            case 5:
                return 1;
            case 6:
                return 2;
            case 7:
                return 3;
            case 8:
                return 4;
            case 9:
                return 6;
            case 10:
                return 7;
            case 11:
                return 5;
            default:
                return 8;
        }
    }
    
    private int northIIcon(int orientation)
    {
        switch (orientation)
        {
            case 0:
                return 7;
            case 1:
                return 6;
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 1;
            case 5:
                return 0;
            case 6:
                return 3;
            case 7:
                return 2;
            case 8:
                return 7;
            case 9:
                return 5;
            case 10:
                return 4;
            case 11:
                return 6;
            default:
                return 0;
        }
    }
    
    public static void setRenderId(int renderId)
    {
        BlockNewQuarterLog.renderId = renderId;
    }
    
    @Override
    public int getRenderType()
    {
        return renderId;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list)
    {
      list.add(new ItemStack(item, 1, 0));
    }
    
    public BlockNewQuarterLog setDroppedItemStack(ItemStack stack) {
      this.droppedItem = stack;
      
      return this;
    }
    
    @Override
    public Item getItemDropped(int metadata, Random rand, int unused)
    {
      if(this.droppedItem != null) {
        return this.droppedItem.getItem();
      } else {
        return Item.getItemFromBlock(this);
      }
    }
    
    @Override
    public int damageDropped(int metadata)
    {
      if(this.droppedItem != null) {
        return this.droppedItem.getItemDamage();
      } else {
        return 0;
      }
    }
    
    @SubscribeEvent
    public void onUseLogTurnerEvent(UseLogTurnerEvent event)
    {
        Block block = event.world.getBlock(event.x, event.y, event.z);
        
        if (block == this)
        {
            final Block wood = Blocks.log;
            event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F, wood.stepSound.soundName, (wood.stepSound.getVolume() + 1.0F) / 2.0F, wood.stepSound.getPitch() * 1.55F);
            
            if (!event.world.isRemote)
            {
                int metadata = event.world.getBlockMetadata(event.x, event.y, event.z);
                
                if (event.entityPlayer.isSneaking() == true)
                {
                    switch (BlockPistonBase.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving))
                    {
                        case 0:
                            metadata = (++metadata > 3) ? 0 : metadata;
                            break;
                        case 1:
                            metadata = (--metadata < 0 || metadata > 3) ? 3 : metadata;
                            break;
                        case 2:
                            metadata = (++metadata > 7 || metadata < 4) ? 4 : metadata;
                            break;
                        case 3:
                            metadata = (--metadata < 4 || metadata > 7) ? 7 : metadata;
                            break;
                        case 4:
                            metadata = (++metadata > 11 || metadata < 8) ? 8 : metadata;
                            break;
                        default:
                            metadata = (--metadata < 8) ? 11 : metadata;
                            break;
                    }
                }
                else
                {
                    // Increment the orentation
                    if (BlockPistonBase.determineOrientation(event.world, event.x, event.y, event.z, event.entityLiving) % 2 == 0)
                    {
                        metadata = (++metadata > 11) ? 0 : metadata;
                    }
                    else
                    {
                        metadata = (--metadata < 0) ? 11 : metadata;
                    }
                }
                
                event.world.setBlock(event.x, event.y, event.z, block, metadata, 3);
                
                //LogHelper.info("Orientation: %d", metadata);
                
                //unturned = false;
            }
            event.setHandled();
            
            // Patch for skipping over block ids that are out of order
            event.setCanceled(true);
        }
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        
        final int direction = BlockPistonBase.determineOrientation(world, x, y, z, entity);
        
        Block checkBlock = this;
        int checkMetaId = 0;
        
        switch(direction){
        	case 0:
        	case 1: // Up/down
        		checkBlock = world.getBlock(x, y+1, z);
        		checkMetaId = world.getBlockMetadata(x, y+1, z);
        		
        		if(checkBlock == this && checkMetaId < 4){
        	        world.setBlock(x, y, z, this, checkMetaId, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y-1, z);
        		checkMetaId = world.getBlockMetadata(x, y-1, z);
        		
        		if(checkBlock == this && checkMetaId < 4){
        	        world.setBlock(x, y, z, this, checkMetaId, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y, z+1);
        		checkMetaId = world.getBlockMetadata(x, y, z+1);
        		
        		if(checkBlock == this && checkMetaId == 2){
        	        world.setBlock(x, y, z, this, 1, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 3){
        	        world.setBlock(x, y, z, this, 0, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y, z-1);
        		checkMetaId = world.getBlockMetadata(x, y, z-1);
        		
        		if(checkBlock == this && checkMetaId == 1){
        	        world.setBlock(x, y, z, this, 2, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 0){
        	        world.setBlock(x, y, z, this, 3, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x+1, y, z);
        		checkMetaId = world.getBlockMetadata(x+1, y, z);
        		
        		if(checkBlock == this && checkMetaId == 2){
        	        world.setBlock(x, y, z, this, 3, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 1){
        	        world.setBlock(x, y, z, this, 0, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x-1, y, z);
        		checkMetaId = world.getBlockMetadata(x-1, y, z);
        		
        		if(checkBlock == this && checkMetaId == 3){
        	        world.setBlock(x, y, z, this, 2, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 0){
        	        world.setBlock(x, y, z, this, 1, 3);
        			return;
        		}
        		
        		
        		int rotation = MathHelper.floor_double((double)((entity.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        		
        		// set the default rotation
        		if(direction == 0) {
        			switch(rotation){
	    				case 0:
	    					world.setBlock(x, y, z, this, 2, 3);
	    					break;
	    				case 1:
	    					world.setBlock(x, y, z, this, 3, 3);
	    					break;
	    				case 2:
	    					world.setBlock(x, y, z, this, 0, 3);
	    					break;
	    				default:
	    					world.setBlock(x, y, z, this, 1, 3);
	    					break;
        			}
        		} else {
        			switch(rotation){
	    				case 0:
	    					world.setBlock(x, y, z, this, 1, 3);
	    					break;
	    				case 1:
	    					world.setBlock(x, y, z, this, 2, 3);
	    					break;
	    				case 2:
	    					world.setBlock(x, y, z, this, 3, 3);
	    					break;
	    				default:
	    					world.setBlock(x, y, z, this, 0, 3);
	    					break;
        			}
        		}
        		
        		// Set it to the default  vertical rotation
        		//world.setBlock(x, y, z, blockID, 3, 3);
        		
        		break;
        	case 2:
        	case 3: // North/South
        		checkBlock = world.getBlock(x, y, z+1);
        		checkMetaId = world.getBlockMetadata(x, y, z+1);
        		
        		if(checkBlock == this && checkMetaId > 3 && checkMetaId < 8){
        	        world.setBlock(x, y, z, this, checkMetaId, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y, z-1);
        		checkMetaId = world.getBlockMetadata(x, y, z-1);
        		
        		if(checkBlock == this && checkMetaId > 3 && checkMetaId < 8){
        	        world.setBlock(x, y, z, this, checkMetaId, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x+1, y, z);
        		checkMetaId = world.getBlockMetadata(x+1, y, z);
        		
        		if(checkBlock == this && checkMetaId == 7){
        	        world.setBlock(x, y, z, this, 6, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 4){
        	        world.setBlock(x, y, z, this, 5, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x-1, y, z);
        		checkMetaId = world.getBlockMetadata(x-1, y, z);
        		
        		if(checkBlock == this && checkMetaId == 6){
        	        world.setBlock(x, y, z, this, 7, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 5){
        	        world.setBlock(x, y, z, this, 4, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y+1, z);
        		checkMetaId = world.getBlockMetadata(x, y+1, z);
        		
        		if(checkBlock == this && checkMetaId == 5){
        	        world.setBlock(x, y, z, this, 6, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 4){
        	        world.setBlock(x, y, z, this, 7, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y-1, z);
        		checkMetaId = world.getBlockMetadata(x, y-1, z);
        		
        		if(checkBlock == this && checkMetaId == 6){
        	        world.setBlock(x, y, z, this, 5, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 7){
        	        world.setBlock(x, y, z, this, 4, 3);
        			return;
        		}
        		
        		// set the default rotation
        		if(direction == 2) {
        			world.setBlock(x, y, z, this, 7, 3);
        		} else {
        			world.setBlock(x, y, z, this, 6, 3);
        		}
        		
        		break;
        	default: // East/West
        		checkBlock = world.getBlock(x+1, y, z);
        		checkMetaId = world.getBlockMetadata(x+1, y, z);
        		
        		if(checkBlock == this && checkMetaId > 7){
        	        world.setBlock(x, y, z, this, checkMetaId, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x-1, y, z);
        		checkMetaId = world.getBlockMetadata(x-1, y, z);
        		
        		if(checkBlock == this && checkMetaId > 7){
        	        world.setBlock(x, y, z, this, checkMetaId, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y+1, z);
        		checkMetaId = world.getBlockMetadata(x, y+1, z);
        		
        		if(checkBlock == this && checkMetaId == 9){
        	        world.setBlock(x, y, z, this, 10, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 8){
        	        world.setBlock(x, y, z, this, 11, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y-1, z);
        		checkMetaId = world.getBlockMetadata(x, y-1, z);
        		
        		if(checkBlock == this && checkMetaId == 10){
        	        world.setBlock(x, y, z, this, 9, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 11){
        	        world.setBlock(x, y, z, this, 8, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y, z-1);
        		checkMetaId = world.getBlockMetadata(x, y, z-1);
        		
        		if(checkBlock == this && checkMetaId == 8){
        	        world.setBlock(x, y, z, this, 9, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 11){
        	        world.setBlock(x, y, z, this, 10, 3);
        			return;
        		}
        		
        		checkBlock = world.getBlock(x, y, z+1);
        		checkMetaId = world.getBlockMetadata(x, y, z+1);
        		
        		if(checkBlock == this && checkMetaId == 9){
        	        world.setBlock(x, y, z, this, 8, 3);
        			return;
        		} else if (checkBlock == this && checkMetaId == 10){
        	        world.setBlock(x, y, z, this, 11, 3);
        			return;
        		}
        		
        		//LogHelper.info("Block: %d, Meta: %d", checkBlockId, checkMetaId);
        		

        		// set the default rotation
        		if(direction == 4) {
        			world.setBlock(x, y, z, this, 11, 3);
        		} else {
        			world.setBlock(x, y, z, this, 10, 3);
        		}
        		
        		break;
        }
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    
    @Override
    protected boolean canSilkHarvest()
    {
        return true;
    }
    
}
