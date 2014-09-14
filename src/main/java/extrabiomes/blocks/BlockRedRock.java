/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.blocks;

import static extrabiomes.blocks.BlockRedRock.BlockType.RED_COBBLE;
import static extrabiomes.blocks.BlockRedRock.BlockType.RED_ROCK_BRICK;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.IDRestrictionAnnotation;

@IDRestrictionAnnotation(maxIDRValue = 255)
public class BlockRedRock extends Block
{
    
    public enum BlockType
    {
        RED_ROCK(0), RED_COBBLE(1), RED_ROCK_BRICK(2);
        
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
    
    private final IIcon[] textures = { null, null, null };
    
    public BlockRedRock()
    {
        super(Material.rock);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        textures[0] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrock");
        textures[1] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockcobble");
        textures[2] = iconRegister.registerIcon(Extrabiomes.TEXTURE_PATH + "redrockbrick");
    }
    
    @Override
    public int damageDropped(int metadata)
    {
        return metadata == RED_ROCK_BRICK.metadata() ? RED_ROCK_BRICK.metadata() : RED_COBBLE.metadata();
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z)
    {
        final int meta = world.getBlockMetadata(x, y, z);
        return meta == RED_COBBLE.metadata() ? 2.0F : blockHardness;
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        if (metadata > 2)
            metadata = 2;
        return metadata == RED_ROCK_BRICK.metadata() ? textures[2] : metadata == RED_COBBLE.metadata() ? textures[1]
                : textures[0];
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List itemList)
    {
        for (final BlockType blockType : BlockType.values())
            itemList.add(new ItemStack(this, 1, blockType.metadata()));
    }
}
