/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.google.common.base.Optional;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import extrabiomes.lib.BiomeSettings;

public class BlockCustomTallGrass extends BlockFlower implements IShearable
{
    public enum BlockType
    {
        BROWN(0), SHORT_BROWN(1), DEAD(2), DEAD_TALL(3), DEAD_YELLOW(4);
        
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
    
    private IIcon[] textures = { null, null, null, null, null };
    
    public BlockCustomTallGrass(int index, Material material)
    {
        super(0);
        final float var3 = 0.4F;
        setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister IIconRegistry)
    {
        textures[0] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "browngrasstall");
        textures[1] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "browngrassshort");
        textures[2] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "deadgrassshort");
        textures[3] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "deadgrasstall");
        textures[4] = IIconRegistry.registerIcon(Extrabiomes.TEXTURE_PATH + "deadgrassyellow");
    }
    
    @Override
    protected boolean canPlaceBlockOn(Block block)
    {
        
        return (BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent() && block.equals(BiomeSettings.MOUNTAINRIDGE.getBiome().get().topBlock))
                || (BiomeSettings.WASTELAND.getBiome().isPresent() && block.equals(BiomeSettings.WASTELAND.getBiome().get().topBlock))
                || super.canPlaceBlockOn(block);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        final double temperature = 0.5D;
        final double hunmidity = 1.0D;
        return ColorizerGrass.getGrassColor(temperature, hunmidity);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata,
            int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        int rarity = 8;
        if (metadata == BlockType.DEAD.metadata() || metadata == BlockType.DEAD_TALL.metadata()
                || metadata == BlockType.DEAD_YELLOW.metadata())
            rarity *= 2;
        if (world.rand.nextInt(rarity) != 0)
            return ret;
        
        final Optional<ItemStack> item = Extrabiomes.proxy.getGrassSeed(world);
        
        if (item.isPresent())
            ret.add(item.get());
        return ret;
    }
    
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        // Ensure that the metadata stays in proper range and we return a valid texture
        if (metadata < 0 || metadata > 4)
            metadata = 0;
        return textures[metadata];
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item id, CreativeTabs tab, List itemList)
    {
        for (final BlockType type : BlockType.values())
            itemList.add(new ItemStack(this, 1, type.metadata()));
    }
    
    @Override
    public Item getItemDropped(int par1, Random par2Random, int par3)
    {
        return null;
    }
    
    @Override
    public int damageDropped(int metadata)
    {
        return metadata;
    }
    
    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
    {
        return true;
    }
    
    @Override
    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z,
            int fortune)
    {
        final ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z)));
        return ret;
    }
    
    @Override
    public int quantityDroppedWithBonus(int i, Random rand)
    {
        return 1 + rand.nextInt(i * 2 + 1);
    }
}
