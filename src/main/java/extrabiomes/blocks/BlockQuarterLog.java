/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import extrabiomes.lib.IQuarterSerializable;
import extrabiomes.lib.PropertyEnum;

/**
 * NB: This class is completely broken by the changes in 1.8
 */

public class BlockQuarterLog<T extends Enum<T> & IQuarterSerializable> extends BlockEBXLLog<T>
{
    public enum BarkOn
    {
        SW, SE, NW, NE;
    	
    	// private Block block;
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
    
    /*
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(world, pos, state, placer, stack);

        final IBlockState northState = world.getBlockState(pos.north());
        final int northMeta = northState.getBlock().getMetaFromState(northState);
        final IBlockState southState = world.getBlockState(pos.south());
        final int southMeta = southState.getBlock().getMetaFromState(southState);
        final IBlockState westState = world.getBlockState(pos.west());
        final int westMeta = westState.getBlock().getMetaFromState(westState);
        final IBlockState eastState = world.getBlockState(pos.east());
        final int eastMeta = eastState.getBlock().getMetaFromState(eastState);
        final IBlockState upState = world.getBlockState(pos.up());
        final int upMeta = upState.getBlock().getMetaFromState(upState);
        final IBlockState downState = world.getBlockState(pos.down());
        final int downMeta = downState.getBlock().getMetaFromState(downState);

        final int thisMeta = state.getBlock().getMetaFromState(state);

        switch (state.getValue(AXIS))
        {
        	case Y:
        	{
            
            
            if (thisMeta == northMeta)
            {
                if (northState.getBlock() == BarkOn.NW.block)
                {
                    world.setBlockState(pos.north(), BarkOn.SW.block, thisMeta, 3);
                    return;
                }
                if (northState == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == eastMeta)
            {
                if (eastState == BarkOn.NE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
                if (eastState == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.SW.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == southMeta)
            {
                if (southState == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NW.block, thisMeta, 3);
                    return;
                }
                if (southState == BarkOn.SE.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
            }
            if (thisMeta == westMeta)
            {
                if (westState == BarkOn.NW.block)
                {
                    world.setBlock(x, y, z, BarkOn.NE.block, thisMeta, 3);
                    return;
                }
                if (westState == BarkOn.SW.block)
                {
                    world.setBlock(x, y, z, BarkOn.SE.block, thisMeta, 3);
                    return;
                }
            }
        }
        
        	case X:
        	{
            
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
    */
    
    @Override
    protected boolean canSilkHarvest()
    {
        return false;
    }
}
