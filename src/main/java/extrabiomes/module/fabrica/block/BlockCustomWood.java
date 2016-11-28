/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Locale;

import extrabiomes.Extrabiomes;
import extrabiomes.lib.IMetaSerializable;
import extrabiomes.lib.ITextureRegisterer;
import extrabiomes.lib.PropertyEnum;
import extrabiomes.utility.ModelUtil;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCustomWood extends Block implements ITextureRegisterer
{
    public enum BlockType implements IMetaSerializable
    {
        REDWOOD(0), FIR(1), ACACIA(2), CYPRESS(3), JAPANESE_MAPLE(4), RAINBOW_EUCALYPTUS(5), AUTUMN(6), BALD_CYPRESS(7), SAKURA_BLOSSOM(8);
        
    	private final String name;
        private final int metadata;
        
        BlockType(int metadata)
        {
        	this.name = name().toLowerCase(Locale.ENGLISH);
            this.metadata = metadata;
        }
        
        @Override
        public String getName() {
        	return name;
        }
        
        @Override
        public int getMetadata()
        {
            return metadata;
        }
        
        
    }
    
    public static final PropertyEnum<BlockType> TYPE = new PropertyEnum<BlockType>(BlockType.class, BlockType.REDWOOD, 16);
    
    public BlockCustomWood()
    {
        super(Material.WOOD);
        
        setSoundType(SoundType.WOOD);
        setHardness(2.0F);
        setResistance(5.0F);
        setCreativeTab(Extrabiomes.tabsEBXL);
        
        Blocks.FIRE.setFireInfo(this, 5, 20);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerTexture()
    {
    	ModelUtil.registerTextures(this, TYPE.getTypeStates(getDefaultState()));
        /*textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksredwood");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksfir");
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksacacia");
        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "plankscypress");
        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksjapanesemaple");
        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksrainboweucalyptus");
        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksautumn");
        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksbaldcypress");
        textures[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "plankssakura");*/
    }
    
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, TYPE);
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, TYPE.getForMeta(meta));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(TYPE).getMetadata();
    }
    
    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(TYPE).getMetadata();
    }

    @Override
    public MapColor getMapColor(IBlockState state)
    {
        return MapColor.WOOD;//((BlockPlanks.EnumType)state.getValue(TYPE)).getMapColor(); TODO: Maybe do this too?
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List<ItemStack> list)
    {
        for (final BlockType type : BlockType.values())
        {
            list.add(new ItemStack(item, 1, type.getMetadata()));
        }
    }

    @Override
    public boolean isWood(IBlockAccess world, BlockPos pos) {
    	return true;
    }
}
