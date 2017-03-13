/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
import extrabiomes.handlers.BlockHandler.LogHandler.Log_A_Type;
import extrabiomes.handlers.BlockHandler.LogHandler.Log_B_Type;
import extrabiomes.handlers.BlockHandler.LogHandler.QuarterLogs_A_Type;
//import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BlockSettings;
import extrabiomes.lib.IMetaSerializable;
import extrabiomes.lib.IQuarterSerializable;
import extrabiomes.lib.PropertyEnum;

public class BlockQuarterLog<T extends Enum<T> & IQuarterSerializable> extends BlockEBXLLog<T>
{
    public enum BarkOn
    {
        SW, SE, NW, NE;
    }
    
    
    public static <T extends Enum<T> & IQuarterSerializable> BlockQuarterLog<T> create(Class<T> types, T defaultType, BarkOn barkOnSides)
	{
		CURRENT_TYPE.set(new PropertyEnum<T>(types, defaultType, 2));
		BlockQuarterLog<T> out = new BlockQuarterLog<T>(barkOnSides);
		CURRENT_TYPE.remove();
		
		return out;
	}
    
    protected BlockQuarterLog(BarkOn barkOnSides)
    {
        super();
        
        this.barkOnSides = barkOnSides;
    }

    private final BarkOn barkOnSides;
    
    public BarkOn getBarkOnSides()
    {
        return barkOnSides;
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        return new ItemStack(getItemDropped(state, RANDOM, 0), 1, damageDropped(state));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
    	//Let's not add them all to creative
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
    	return state.getValue(type).getItem();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
       	return state.getValue(type).getMeta();
    }
    
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        
        switch (state.getValue(AXIS))
        {
        	case Y:
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
        
        	case X:
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
        
        	case Z:
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
    }
    
    @Override
    protected boolean canSilkHarvest()
    {
        return false;
    }
}
