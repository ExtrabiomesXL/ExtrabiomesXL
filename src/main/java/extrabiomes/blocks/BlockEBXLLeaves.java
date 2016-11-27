package extrabiomes.blocks;

import java.util.Collections;
import java.util.List;

import extrabiomes.Extrabiomes;
import extrabiomes.lib.ILeafSerializable;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.lib.PropertyEnum;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockEBXLLeaves<T extends Enum<T> & ILeafSerializable> extends BlockLeaves implements ITextureRegisterer {
	public static <T extends Enum<T> & ILeafSerializable> BlockEBXLLeaves<T> create(Class<T> types, T defaultType) {
		//Block needs the type for creating the block state faster than we can normally set it.
		//But we can cheat using ThreadLocal variables to fetch it during construction.
		//Then set it properly to a field afterwards.
		CURRENT_TYPE.set(new PropertyEnum<T>(types, defaultType, 4));
		BlockEBXLLeaves<T> out = new BlockEBXLLeaves<T>();
		CURRENT_TYPE.remove();
		
		return out;
	}
	
	protected static final ThreadLocal<PropertyEnum<?>> CURRENT_TYPE = new ThreadLocal<PropertyEnum<?>>();
	public final PropertyEnum<T> type;
	
	protected BlockEBXLLeaves() {
        super();
        type = getTypeProperty();
        
        setDefaultState(getDefaultState().withProperty(type, type.getDefault()).withProperty(CHECK_DECAY, Boolean.TRUE).withProperty(DECAYABLE, Boolean.TRUE));
        setCreativeTab(Extrabiomes.tabsEBXL);
    }

	private PropertyEnum<T> getTypeProperty() {
		@SuppressWarnings("unchecked")
		PropertyEnum<T> out = (PropertyEnum<T>) (type != null ? type : CURRENT_TYPE.get());
		
		if (out == null) //Both CURRENT_TYPE and type are null
			throw new IllegalStateException("Cannot find type!"); //Happens if create isn't used.
		
		return out;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, getTypeProperty(), CHECK_DECAY, DECAYABLE);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(type, type.getForMeta(meta))
				.withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
				.withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(type).getMetadata() - 4;

		if (!state.getValue(DECAYABLE)) {
			meta |= 4;
		}

		if (state.getValue(CHECK_DECAY)) {
			meta |= 8;
		}

		return meta;
    }
	
	@Override
	public void registerTexture() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		T leaf = world.getBlockState(pos).getValue(type);
		return Collections.singletonList(new ItemStack(leaf.getSaplingBlock(), 1, leaf.getSaplingMetadata()));
	}

	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}

}
