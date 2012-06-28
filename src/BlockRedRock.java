package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

import java.util.*;

public class BlockRedRock extends Block implements ITextureProvider
{
	public BlockRedRock(int par1, int par2)
    {
        super(par1, par2, Material.rock);
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