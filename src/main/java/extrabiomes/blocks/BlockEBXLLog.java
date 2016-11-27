/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.List;

import extrabiomes.Extrabiomes;
import extrabiomes.api.UseLogTurnerEvent;
import extrabiomes.lib.IMetaSerializable;
import extrabiomes.lib.PropertyEnum;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEBXLLog<T extends Enum<T> & IMetaSerializable> extends BlockLog {
	public static <T extends Enum<T> & IMetaSerializable> BlockEBXLLog<T> create(Class<T> types, T defaultType)
	{
		//Block needs the type for creating the block state faster than we can normally set it.
		//But we can cheat using ThreadLocal variables to fetch it during construction.
		//Then set it properly to a field afterwards.
		CURRENT_TYPE.set(new PropertyEnum<T>(types, defaultType, 4));
		BlockEBXLLog<T> out = new BlockEBXLLog<T>();
		CURRENT_TYPE.remove();
		
		return out;
	}
	
	protected static final ThreadLocal<PropertyEnum<?>> CURRENT_TYPE = new ThreadLocal<PropertyEnum<?>>();
	public final PropertyEnum<T> type;
	
	protected BlockEBXLLog()
    {
        super();
        type = getTypeProperty();
        
        setDefaultState(getDefaultState().withProperty(type, type.getDefault()).withProperty(LOG_AXIS, EnumAxis.Y));
        setCreativeTab(Extrabiomes.tabsEBXL);
        setSoundType(SoundType.WOOD);
        setHardness(2.0F);
        setResistance(Blocks.LOG.getExplosionResistance(null) * 5.0F);
    }
    
	private PropertyEnum<T> getTypeProperty()
	{
		@SuppressWarnings("unchecked")
		PropertyEnum<T> out = (PropertyEnum<T>) (type != null ? type : CURRENT_TYPE.get());
		
		if (out == null) //Both CURRENT_TYPE and type are null
			throw new IllegalStateException("Cannot find type!"); //Happens if create isn't used.
		
		return out;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getTypeProperty(), LOG_AXIS);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
    {
        IBlockState state = getDefaultState().withProperty(type, type.getForMeta((meta & 3) % 4));

        switch (meta & 12)
        {
            case 0:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
                break;
            case 4:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
                break;
            case 8:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
                break;
            default:
                state = state.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
                break;
        }

        return state;
    }

	@Override
    public int getMetaFromState(IBlockState state)
    {
        int i = state.getValue(type).getMetadata();

        switch (state.getValue(LOG_AXIS))
        {
            case X:
                i |= 4;
                break;
            case Z:
                i |= 8;
                break;
            case NONE:
                i |= 12;
                break;
			default:
				break;
        }

        return i;
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, state.getValue(type).getMetadata());
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(type).getMetadata();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (final T type : type.getAllowedValues())
            list.add(new ItemStack(item, 1, type.getMetadata()));
    }
    
    @SubscribeEvent
    public void onUseLogTurnerEvent(UseLogTurnerEvent event)
    {
    	final World world = event.getWorld();
        IBlockState block = world.getBlockState(event.getPos());
        
        if (block.getBlock() == this)
        {
            world.playSound(event.getEntityPlayer(), event.getPos(), blockSoundType.getPlaceSound(), SoundCategory.BLOCKS, (blockSoundType.getVolume() + 1F) / 2F, blockSoundType.getPitch() * 1.55F);
            
            if (!world.isRemote)
            {
            	if (event.getEntityPlayer().isSneaking())
            		block = withRotation(block, Rotation.COUNTERCLOCKWISE_90);
            	else
            		block = withRotation(block, Rotation.CLOCKWISE_90);
                
                world.setBlockState(event.getPos(), block);
            }
            event.setHandled();
        }
    }
    
    @Override
	public boolean isWood(IBlockAccess world, BlockPos pos)
    {
		return true;
	}
    
	@Override
	public boolean canSustainLeaves(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return true;
	}
}