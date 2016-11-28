/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

import extrabiomes.Extrabiomes;
import extrabiomes.lib.ILeafSerializable;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.lib.PropertyEnum;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockEBXLLeaves<T extends Enum<T> & ILeafSerializable> extends BlockLeaves implements ITextureRegisterer {
	@SideOnly(Side.CLIENT)
	private class LeafMapper extends StateMapperBase implements ItemMeshDefinition {
		@Override
		protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
			assert state.getBlock() == BlockEBXLLeaves.this;
			return new ModelResourceLocation(new ResourceLocation(Extrabiomes.RESOURCE_PATH, "leaves"), getPropertyString(state.getProperties()));
		}
		
		protected ResourceLocation[] getVarients() {
			Collection<ModelResourceLocation> varients = mapStateModelLocations.values(); 
			return varients.toArray(new ResourceLocation[varients.size()]);
		}
		
		@Override
		public ModelResourceLocation getModelLocation(ItemStack stack) {
			return getModelResourceLocation(getStateFromMeta(stack.getItemDamage()));
		}
		
		@Override
		public String getPropertyString(Map<IProperty<?>, Comparable<?>> values) {
			if (values.containsKey(type)) {
				return type.getName() + '=' + getPropertyName(type, values.get(type))/* + "_leaves"*/;
			} else {
				return "normal";
			}
		}

		@SuppressWarnings("unchecked")
		private <N extends Comparable<N>> String getPropertyName(IProperty<N> property, Comparable<?> value) {
			return property.getName((N) value);
		}
	};
	
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
        setTickRandomly(true);
        setHardness(0.2F);
        setLightOpacity(1);
        setSoundType(SoundType.PLANT);
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
		return getDefaultState().withProperty(type, type.getForMeta((meta & 3) % 4))
				.withProperty(DECAYABLE, Boolean.valueOf((meta & 4) == 0))
				.withProperty(CHECK_DECAY, Boolean.valueOf((meta & 8) > 0));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		int meta = state.getValue(type).getMetadata();

		if (!state.getValue(DECAYABLE)) {
			meta |= 4;
		}

		if (state.getValue(CHECK_DECAY)) {
			meta |= 8;
		}

		return meta;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerTexture() {
		final LeafMapper mapper = new LeafMapper();
		Item item = Item.getItemFromBlock(this);
		
		ModelLoader.setCustomStateMapper(this, mapper);
		ModelLoader.setCustomMeshDefinition(item, mapper);
		ModelBakery.registerItemVariants(item, mapper.getVarients());
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return Blocks.LEAVES.isOpaqueCube(state);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return Blocks.LEAVES.getBlockLayer();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return Blocks.LEAVES.shouldSideBeRendered(state, world, pos, side);
	}
	
	@Override
	public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
		return true;
	}

	@Override
	public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
		return Collections.singletonList(createStackedBlock(world.getBlockState(pos)));
	}

	@Override
	protected int getSaplingDropChance(IBlockState state) {
		return state.getValue(type).getSaplingDropChance();
	}
	
	@Override
	protected void dropApple(World world, BlockPos pos, IBlockState state, int chance) {
		if (!world.isRemote && state.getValue(type).canDropApples() && world.rand.nextInt(chance) == 0) {
			spawnAsEntity(world, pos, new ItemStack(Items.APPLE));
		}
	}
	
	@Override
	public EnumType getWoodType(int meta) {
		return null;
	}
	
	@Override
	protected ItemStack createStackedBlock(IBlockState state) {
		T type = state.getValue(this.type);
		return new ItemStack(type.getSaplingBlock(), 1, type.getSaplingMetadata());
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return createStackedBlock(state);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(state.getValue(type).getSaplingBlock());
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return state.getValue(type).getSaplingMetadata();
	}

	@Override
    @SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list) {
		for (final T type : type.getAllowedValues())
			list.add(new ItemStack(type.getSaplingBlock(), 1, type.getSaplingMetadata()));
	}
}