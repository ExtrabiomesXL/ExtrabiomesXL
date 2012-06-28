package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockQuicksand extends Block implements ITextureProvider
{
    public BlockQuicksand(int par1, int par2)
    {
        super(par1, par2, Material.sand);
    }
    
    public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        par5Entity.setInWeb();
    }
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int i)
    {
        return null;
    }
    
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.quicksand.blockID;
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