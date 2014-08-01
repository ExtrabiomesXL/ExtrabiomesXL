/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;

import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.world.IBlockAccess;

public class BlockCustomWood extends BlockWood
{
    public enum BlockType
    {
        REDWOOD(0), FIR(1), ACACIA(2), CYPRESS(3), JAPANESE_MAPLE(4), RAINBOW_EUCALYPTUS(5), AUTUMN(6), BALD_CYPRESS(7), SAKURA_BLOSSOM(8);
        
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
    
    private IIcon[] textures = { null, null, null, null, null, null, null, null, null };
    
    public BlockCustomWood()
    {
        super();
        setStepSound(soundTypeWood);
        setHardness(2.0F);
        setResistance(5.0F);
        //Blocks.fire.setFireInfo(this, 5, 20);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksredwood");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksfir");
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksacacia");
        textures[3] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "plankscypress");
        textures[4] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksjapanesemaple");
        textures[5] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksrainboweucalyptus");
        textures[6] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksautumn");
        textures[7] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "planksbaldcypress");
        textures[8] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "plankssakura");
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        // Ensure that the texture id is in range
        if (metadata < 0 || metadata > 8)
            metadata = 0;
        return textures[metadata];
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs par2CreativeTabs, List list)
    {
        for (final BlockType type : BlockType.values())
        {
            list.add(new ItemStack(item, 1, type.metadata()));
        }
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
}
