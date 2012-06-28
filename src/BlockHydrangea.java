package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockHydrangea extends BlockFlower implements ITextureProvider
{
    protected BlockHydrangea(int par1, int par2)
    {
        super(par1, par2, Material.plants);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
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