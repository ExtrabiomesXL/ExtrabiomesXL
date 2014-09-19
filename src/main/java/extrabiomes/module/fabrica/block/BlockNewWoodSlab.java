/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.fabrica.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWoodSlab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.lib.BlockSettings;

public class BlockNewWoodSlab extends BlockWoodSlab
{
    public enum BlockType
    {
        SAKURA_BLOSSOM(0);
        
        private final int metadata;
        
        BlockType(int metadata)
        {
            this.metadata = metadata;
        }
        
        public int metadata()
        {
            return metadata;
        }
        
        @Override
        public String toString()
        {
            return name().toLowerCase();
        }
    }
    
    private BlockSettings settings;
    private static Block singleSlab = null;
    
    private IIcon[]     textures     = { null, null, null, null, null, null, null, null };
    
    public BlockNewWoodSlab(BlockSettings settings, boolean isDouble)
    {
        super(isDouble);
        if (!isDouble)
            singleSlab = this;
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        //Blocks.fire.setFireInfo(this, 5, 20);
        setLightOpacity(0);
        setCreativeTab(Extrabiomes.tabsEBXL);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "plankssakura");
    }
    
    @Override
    protected ItemStack createStackedBlock(int damage)
    {
        return new ItemStack(singleSlab, 2, damage & 7);
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return textures[0];
    }
    
    @Override
    /* Deobf name: getFullSlabName */
    public String func_150002_b(int metadata)
    {
        String woodType;
        switch (metadata & 7)
        {
            default:
                woodType = BlockType.SAKURA_BLOSSOM.toString();
        }
        
        return super.getUnlocalizedName() + "." + woodType;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List itemList)
    {
        if (this == singleSlab)
        {
            for (final BlockType type : BlockType.values())
            {
                itemList.add(new ItemStack(item, 1, type.metadata()));
            }
        }
    }
    
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return ItemBlock.getItemFromBlock(singleSlab);
    }
    

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int x, int y, int z) {
      return Item.getItemFromBlock(this);
    }
}
