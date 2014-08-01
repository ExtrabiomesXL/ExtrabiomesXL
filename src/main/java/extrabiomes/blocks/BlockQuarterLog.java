/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.HashMap;
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
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
//import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;

public class BlockQuarterLog extends BlockLog
{
    public enum BarkOn
    {
        SW, SE, NW, NE;
        
        private Block block;
    }
    
    public enum BlockType
    {
        REDWOOD(0), FIR(1), OAK(2);
        
        private final int metadata;
        
        BlockType(int metadata)
        {
            this.metadata = metadata;
        }
        
        public int metadata()
        {
            return metadata;
        }
    }
    
    private enum Orientation
    {
        UD, NS, EW
    }
    
    private static int renderId = 31;
    
    private static Orientation determineOrientation(World world, int x, int y, int z, EntityLivingBase entity)
    {
        final int direction = BlockPistonBase.determineOrientation(world, x, y, z, entity);
        
        switch (direction)
        {
            case 0:
            case 1:
                return Orientation.UD;
            case 2:
            case 3:
                return Orientation.NS;
            default:
                return Orientation.EW;
        }
    }
    
    public static void setRenderId(int renderId)
    {
        BlockQuarterLog.renderId = renderId;
    }
    
    private final BarkOn barkOnSides;
    
    public BarkOn getBarkOnSides()
    {
        return barkOnSides;
    }
    
    private BlockSettings settings;
    private HashMap<Integer, IIcon> textures;
    private IIcon[]                 textureArray = { null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null };
    private int                    index;
    
    public BlockQuarterLog(BlockSettings settings, int index, BarkOn barkOnSides)
    {
        super();
        this.settings = settings;
        this.barkOnSides = barkOnSides;
        // barkOnSides = blockID; // TODO: huh?
        this.index = index;
        textures = new HashMap<Integer, IIcon>();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        
        textureArray[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodsideleft");
        textureArray[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logredwoodsideright");
        textureArray[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodtopleft");
        textureArray[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodtopright");
        textureArray[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodbottomleft");
        textureArray[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodbottomright");
        textureArray[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodsideleft");
        textureArray[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redwoodsideright");
        
        textureArray[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logfirsideleft");
        textureArray[9] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logfirsideright");
        textureArray[10] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firtopleft");
        textureArray[11] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firtopright");
        textureArray[12] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firbottomleft");
        textureArray[13] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firbottomright");
        textureArray[14] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firsideleft");
        textureArray[15] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "firsideright");
        
        textureArray[16] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logoaksideleft");
        textureArray[17] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "logoaksideright");
        textureArray[18] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaktopleft");
        textureArray[19] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaktopright");
        textureArray[20] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oakbottomleft");
        textureArray[21] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oakbottomright");
        textureArray[22] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaksideleft");
        textureArray[23] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "oaksideright");
        
        setupTextures(index);
    }
    
    public void setupTextures(int index)
    {
        //The intention here is to maintain the old system of using texture sheet indexes to choose a texture. This is because reworking these texture
        //orientation methods to work with the new system will take quite some time.
        
        //row 1
        textures.put(index, textureArray[0]);
        textures.put(index + 1, textureArray[1]);
        textures.put(index + 2, textureArray[8]);
        textures.put(index + 3, textureArray[9]);
        textures.put(index + 4, textureArray[16]);
        textures.put(index + 5, textureArray[17]);
        
        //row2
        textures.put(index + 16, textureArray[2]);
        textures.put(index + 17, textureArray[3]);
        textures.put(index + 18, textureArray[10]);
        textures.put(index + 19, textureArray[11]);
        textures.put(index + 20, textureArray[18]);
        textures.put(index + 21, textureArray[19]);
        
        //row3
        textures.put(index + 32, textureArray[4]);
        textures.put(index + 33, textureArray[5]);
        textures.put(index + 34, textureArray[12]);
        textures.put(index + 35, textureArray[13]);
        textures.put(index + 36, textureArray[20]);
        textures.put(index + 37, textureArray[21]);
        
        //row4
        textures.put(index + 48, textureArray[6]);
        textures.put(index + 49, textureArray[7]);
        textures.put(index + 50, textureArray[14]);
        textures.put(index + 51, textureArray[15]);
        textures.put(index + 52, textureArray[22]);
        textures.put(index + 53, textureArray[23]);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        final int orientation = metadata & 12;
        int type = metadata & 3;
        
        if (type > 2)
            type = 0;
        
        int offset = 0;
        switch (barkOnSides)
        {
            case SE:
                offset = getSETextureOffset(side, orientation);
                break;
            case SW:
                offset = getSWTextureOffset(side, orientation);
                break;
            case NE:
                offset = getNETextureOffset(side, orientation);
                break;
            case NW:
                offset = getNWTextureOffset(side, orientation);
                break;
        }
        return textures.get(index + offset + type * 2);
    }
    
    private int getNETextureOffset(int side, final int orientation)
    {
        int offset = 0;
        if (orientation == 0)
            switch (side)
            {
                case 0:
                case 1:
                    offset = 17;
                    break;
                case 2:
                    offset = 0;
                    break;
                case 3:
                    offset = 49;
                    break;
                case 4:
                    offset = 48;
                    break;
                default:
                    offset = 1;
            }
        else if (orientation == 4)
            switch (side)
            {
                case 0:
                    offset = 49;
                    break;
                case 1:
                    offset = 0;
                    break;
                case 2:
                    offset = 1;
                    break;
                case 3:
                    offset = 48;
                    break;
                case 4:
                    offset = 16;
                    break;
                default:
                    offset = 17;
            }
        else if (orientation == 8)
            switch (side)
            {
                case 0:
                    offset = 49;
                    break;
                case 2:
                    offset = 16;
                    break;
                case 3:
                    offset = 17;
                    break;
                case 4:
                    offset = 49;
                    break;
                default:
                    offset = 1;
            }
        return offset;
    }
    
    private Block getNextBlock()
    {
        //LogHelper.info(Integer.toString(blockID));
        if (this == BarkOn.SW.block)
            return BarkOn.NE.block;
        if (this == BarkOn.NE.block)
            return BarkOn.NW.block;
        if (this == BarkOn.NW.block)
            return BarkOn.SE.block;
        return BarkOn.SW.block;
    }
    
    private int getNWTextureOffset(int side, final int orientation)
    {
        int offset = 0;
        if (orientation == 0)
            switch (side)
            {
                case 0:
                case 1:
                    offset = 16;
                    break;
                case 2:
                    offset = 1;
                    break;
                case 3:
                    offset = 48;
                    break;
                case 4:
                    offset = 0;
                    break;
                default:
                    offset = 49;
            }
        else if (orientation == 4)
            switch (side)
            {
                case 0:
                    offset = 48;
                    break;
                case 1:
                    offset = 1;
                    break;
                case 2:
                    offset = 49;
                    break;
                case 3:
                    offset = 0;
                    break;
                case 4:
                    offset = 17;
                    break;
                default:
                    offset = 16;
            }
        else if (orientation == 8)
            switch (side)
            {
                case 0:
                    offset = 48;
                    break;
                case 1:
                    offset = 0;
                    break;
                case 2:
                    offset = 17;
                    break;
                case 3:
                    offset = 16;
                    break;
                case 4:
                    offset = 1;
                    break;
                default:
                    offset = 49;
            }
        return offset;
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	Item pickItem;
    	int pickMeta = 0;
    	
    	switch (meta){
	    	case 1:
	    		pickItem = BlockSettings.NEWLOG.getItem();
	    		pickMeta = BlockNewLog.BlockType.REDWOOD.metadata();
	    		break;
	    	case 2:
	    		pickItem = ItemBlock.getItemFromBlock(Blocks.log);
	    		pickMeta = 0;
	    		break;
    		default:
    			pickItem = BlockSettings.CUSTOMLOG.getItem();
    			pickMeta = BlockCustomLog.BlockType.FIR.metadata();
    			break;
    	}
    	
    	
        final ItemStack itemstack = new ItemStack(pickItem, 1, pickMeta);//super.getPickBlock(target, world, x, y, z);
        //itemstack.itemID = BarkOn.SE;
        return itemstack;
    }
    
    @Override
    public int getRenderType()
    {
        return renderId;
    }
    
    private int getSETextureOffset(int side, final int orientation)
    {
        int offset = 0;
        if (orientation == 0)
            switch (side)
            {
                case 0:
                case 1:
                    offset = 33;
                    break;
                case 2:
                    offset = 48;
                    break;
                case 3:
                    offset = 1;
                    break;
                case 4:
                    offset = 49;
                    break;
                default:
                    offset = 0;
            }
        else if (orientation == 4)
            switch (side)
            {
                case 0:
                    offset = 0;
                    break;
                case 1:
                    offset = 49;
                    break;
                case 2:
                    offset = 48;
                    break;
                case 3:
                    offset = 1;
                    break;
                case 4:
                    offset = 33;
                    break;
                default:
                    offset = 32;
            }
        else if (orientation == 8)
            switch (side)
            {
                case 0:
                    offset = 1;
                    break;
                case 1:
                    offset = 49;
                    break;
                case 2:
                    offset = 32;
                    break;
                case 3:
                    offset = 33;
                    break;
                case 4:
                    offset = 48;
                    break;
                default:
                    offset = 0;
            }
        return offset;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list)
    {
        //for (final BlockType type : BlockType.values())
        //    list.add(new ItemStack(item, 1, type.metadata()));
    }
    
    private int getSWTextureOffset(int side, final int orientation)
    {
        int offset = 0;
        if (orientation == 0)
            switch (side)
            {
                case 0:
                case 1:
                    offset = 32;
                    break;
                case 2:
                    offset = 49;
                    break;
                case 3:
                    offset = 0;
                    break;
                case 4:
                    offset = 1;
                    break;
                default:
                    offset = 48;
            }
        else if (orientation == 4)
            switch (side)
            {
                case 0:
                    offset = 1;
                    break;
                case 1:
                    offset = 48;
                    break;
                case 2:
                    offset = 0;
                    break;
                case 3:
                    offset = 49;
                    break;
                case 4:
                    offset = 32;
                    break;
                default:
                    offset = 33;
            }
        else if (orientation == 8)
            switch (side)
            {
                case 0:
                    offset = 0;
                    break;
                case 1:
                    offset = 48;
                    break;
                case 2:
                    offset = 33;
                    break;
                case 3:
                    offset = 32;
                    break;
                case 4:
                    offset = 0;
                    break;
                default:
                    offset = 48;
            }
        return offset;
    }
    
    @Override
    public Item getItemDropped(int metadata, Random rand, int unused)
    {
    	//LogHelper.info("Unused: %d", unused);
    	
    	metadata &= 3;
    	switch (metadata){
	    	case 1:
    			return BlockSettings.CUSTOMLOG.getItem();
	    	case 2:
	    		return ItemBlock.getItemFromBlock(Blocks.log);
    		default:
	    		return BlockSettings.NEWLOG.getItem();
    	}
    	
        //return BarkOn.SE;
    }
        
    @Override
    public int damageDropped(int metadata)
    {
    	metadata &= 3;
       	switch (metadata){
	    	case 0:
	    		return BlockNewLog.BlockType.REDWOOD.metadata();
	    	case 2:
	    		return 0;
			default:
				return BlockCustomLog.BlockType.FIR.metadata();
		}
    }
    
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
        
        final Orientation orientation = determineOrientation(world, x, y, z, entity);
        
        if (orientation == Orientation.UD)
        {
            final Block northBlock = world.getBlock(x, y, z - 1);
            final int northMeta = world.getBlockMetadata(x, y, z - 1);
            final Block southBlock = world.getBlock(x, y, z + 1);
            final int southMeta = world.getBlockMetadata(x, y, z + 1);
            final Block westBlock = world.getBlock(x - 1, y, z);
            final int westMeta = world.getBlockMetadata(x - 1, y, z);
            final Block eastBlock = world.getBlock(x + 1, y, z);
            final int eastMeta = world.getBlockMetadata(x + 1, y, z);
            
            final int thisMeta = world.getBlockMetadata(x, y, z);
            
            if (thisMeta == northMeta)
            {
                if (northBlock == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
                if (northBlock == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == eastMeta)
            {
                if (eastBlock == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
                if (eastBlock == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == southMeta)
            {
                if (southBlock == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
                if (southBlock == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == westMeta)
            {
                if (westBlock == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
                if (westBlock == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
            }
        }
        
        if (orientation == Orientation.NS)
        {
            final Block upBlock = world.getBlock(x, y + 1, z);
            final int upMeta = world.getBlockMetadata(x, y + 1, z);
            final Block downBlock = world.getBlock(x, y - 1, z);
            final int downMeta = world.getBlockMetadata(x, y - 1, z);
            final Block westBlock = world.getBlock(x - 1, y, z);
            final int westMeta = world.getBlockMetadata(x - 1, y, z);
            final Block eastBlock = world.getBlock(x + 1, y, z);
            final int eastMeta = world.getBlockMetadata(x + 1, y, z);
            
            final int thisMeta = world.getBlockMetadata(x, y, z);
            
            if (thisMeta == upMeta)
            {
                if (upBlock == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
                if (upBlock == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == eastMeta)
            {
                if (eastBlock == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
                if (eastBlock == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == downMeta)
            {
                if (downBlock == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
                if (downBlock == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == westMeta)
            {
                if (westBlock == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
                if (westBlock == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
            }
        }
        
        if (orientation == Orientation.EW)
        {
            final Block northBlock = world.getBlock(x, y, z - 1);
            final int northMeta = world.getBlockMetadata(x, y, z - 1);
            final Block southBlock = world.getBlock(x, y, z + 1);
            final int southMeta = world.getBlockMetadata(x, y, z + 1);
            final Block upBlock = world.getBlock(x, y + 1, z);
            final int upMeta = world.getBlockMetadata(x, y + 1, z);
            final Block downBlock = world.getBlock(x, y - 1, z);
            final int downMeta = world.getBlockMetadata(x, y - 1, z);
            
            final int thisMeta = world.getBlockMetadata(x, y, z);
            
            if (thisMeta == northMeta)
            {
                if (northBlock == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
                if (northBlock == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == upMeta)
            {
                if (upBlock == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
                if (upBlock == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == southMeta)
            {
                if (southBlock == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
                if (southBlock == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == downMeta)
            {
                if (downBlock == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
                if (downBlock == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onUseLogTurnerEvent(UseLogTurnerEvent event)
    {
        Block block = event.world.getBlock(event.x, event.y, event.z);
        
        if (block == this)
        {
            final Block wood = Blocks.log;
            event.world.playSoundEffect(event.x + 0.5F, event.y + 0.5F, event.z + 0.5F,
                    wood.stepSound.soundName, (wood.stepSound.getVolume() + 1.0F) / 2.0F,
                    wood.stepSound.getPitch() * 1.55F);
            
            if (!event.world.isRemote)
            {
                final int metadata = event.world.getBlockMetadata(event.x, event.y, event.z);
                int orientation = metadata & 12;
                final int type = metadata & 3;
                
                orientation = orientation == 0 ? 4 : orientation == 4 ? 8 : 0;
                
                if (orientation == 0)
                    block = getNextBlock();
                event.world.setBlock(event.x, event.y, event.z, block, type | orientation, 3);
            }
            event.setHandled();
            
            // Patch for skipping over block ids that are out of order
            event.setCanceled(true);
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
        return false;
    }
}
