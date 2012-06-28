package net.minecraft.src;
import net.minecraft.src.forge.*;
import java.util.*;

public class BlockCrackedSand extends Block implements ITextureProvider
{
	public BlockCrackedSand(int par1, int par2)
    {
        super(par1, par2, Material.rock);
    }
	
	public String getTextureFile()
    {
            return "/ExtraBiomesXL/extrabiomes.png";
    }
	
	public void addCreativeItems(ArrayList itemList)
    {
            itemList.add(new ItemStack(this));
    }
}