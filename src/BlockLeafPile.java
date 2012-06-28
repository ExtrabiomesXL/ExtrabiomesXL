package net.minecraft.src;

import java.util.ArrayList;
import net.minecraft.src.forge.ITextureProvider;

public class BlockLeafPile extends Block implements ITextureProvider
{
    protected BlockLeafPile(int par1, int par2)
    {
        super(par1, par2, Material.vine);
        float f = 0.5F;
        float f1 = 0.015625F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }
	
    public int getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return blockIndexInTexture;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
    {
        return null;
    }

    public int getBlockColor()
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    
    public int getRenderColor(int par1)
    {
        return ColorizerFoliage.getFoliageColorBasic();
    }
    
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return par1IBlockAccess.getBiomeGenForCoords(par2, par4).getBiomeFoliageColor();
    }

	
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == Block.grass.blockID;
    }
	
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        if (par3 < 0 || par3 >= 256)
        {
            return false;
        }
        else
        {
            return par1World.getBlockMaterial(par2, par3 - 1, par4) == Material.grass && par1World.getBlockMetadata(par2, par3 - 1, par4) == 0;
        }
    }
    
    public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
	
	public String getTextureFile()
    {
            return "/ExtraBiomesXL/extrabiomes.png";
    }
}