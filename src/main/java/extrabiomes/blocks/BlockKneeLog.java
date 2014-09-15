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
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
import extrabiomes.lib.BlockSettings;

public class BlockKneeLog extends BlockLog
{   
    private BlockSettings settings;
    
    private IIcon[]     textures = { null, null, null, null, null, null, null, null, null };
    private static int renderId = 32;
    private String     treeType = "knee";
    private ItemStack droppedItem;
    
    public BlockKneeLog(BlockSettings settings, String treeType)
    {
        super();
        
        this.settings = settings;
        this.treeType = treeType;
        setStepSound(Block.soundTypeWood);
        setHardness(2.0F);
        setResistance(Blocks.log.getExplosionResistance(null) * 5.0F);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee1");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee2");
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee3");
        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "knee4");
        
        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "side1");
        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "side2");
        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "top");
        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "log" + treeType + "top");
        
        textures[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "todo");
        
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
                return 4;
            case 9:
                return 5;
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
                return 4;
            case 7:
                return 5;
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
                return 4;
            case 6:
                return 5;
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
                return 1;
            case 8:
                return 4;
            case 9:
                return 7;
            case 10:
                return 6;
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
                return 6;
            case 9:
                return 4;
            case 10:
                return 5;
            case 11:
                return 7;
            default:
                return 8;
        }
    }
    
    public static void setRenderId(int renderId)
    {
        BlockKneeLog.renderId = renderId;
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
    
    public BlockKneeLog setDroppedItemStack(ItemStack stack) {
      this.droppedItem = stack;
      
      return this;
    }
    
    @Override
    public Item getItemDropped(int metadata, Random rand, int unused) {
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
                            switch (metadata)
                            {
                                case 9:
                                    metadata = 4;
                                    break;
                                case 4:
                                    metadata = 8;
                                    break;
                                case 8:
                                    metadata = 5;
                                    break;
                                case 5:
                                    metadata = 9;
                                    break;
                                default:
                                    metadata = 9;
                                    break;
                            }
                            break;
                        case 1:
                            switch (metadata)
                            {
                                case 6:
                                    metadata = 11;
                                    break;
                                case 7:
                                    metadata = 10;
                                    break;
                                case 10:
                                    metadata = 6;
                                    break;
                                case 11:
                                    metadata = 7;
                                    break;
                                default:
                                    metadata = 10;
                                    break;
                            }
                            break;
                        case 2:
                            switch (metadata)
                            {
                                case 3:
                                    metadata = 10;
                                    break;
                                case 10:
                                    metadata = 2;
                                    break;
                                case 2:
                                    metadata = 9;
                                    break;
                                case 9:
                                    metadata = 3;
                                    break;
                                default:
                                    metadata = 3;
                                    break;
                            }
                            break;
                        case 3:
                            switch (metadata)
                            {
                                case 1:
                                    metadata = 11;
                                    break;
                                case 11:
                                    metadata = 0;
                                    break;
                                case 0:
                                    metadata = 8;
                                    break;
                                case 8:
                                    metadata = 1;
                                    break;
                                default:
                                    metadata = 1;
                                    break;
                            }
                            break;
                        case 4:
                            switch (metadata)
                            {
                                case 2:
                                    metadata = 7;
                                    break;
                                case 7:
                                    metadata = 1;
                                    break;
                                case 1:
                                    metadata = 4;
                                    break;
                                case 4:
                                    metadata = 2;
                                    break;
                                default:
                                    metadata = 2;
                                    break;
                            }
                            break;
                        default:
                            switch (metadata)
                            {
                                case 0:
                                    metadata = 6;
                                    break;
                                case 6:
                                    metadata = 3;
                                    break;
                                case 3:
                                    metadata = 5;
                                    break;
                                case 5:
                                    metadata = 0;
                                    break;
                                default:
                                    metadata = 0;
                                    break;
                            }
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
        
        world.setBlock(x, y, z, this, 3, 3);
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
}
