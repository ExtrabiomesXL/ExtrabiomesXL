package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockAutumnShrub extends BlockFlower implements ITextureProvider {
	protected BlockAutumnShrub(int i, int j) {
		super (i, j);
        blockIndexInTexture = j;
        setTickRandomly(true);
        float f = 0.2F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 3F, 0.5F + f);

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