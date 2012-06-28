package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockWhiteFlower extends BlockFlower implements ITextureProvider
{
    protected BlockWhiteFlower(int par1, int par2)
    {
        super(par1, par2, Material.plants);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
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