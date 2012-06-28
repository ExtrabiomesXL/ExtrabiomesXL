package net.minecraft.src;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.src.forge.*;

public class BlockBrownGrass extends BlockFlower implements ITextureProvider
{
    protected BlockBrownGrass(int par1, int par2)
    {
        super(par1, par2, Material.vine);
        float var3 = 0.4F;
        this.setBlockBounds(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.5F, 0.5F + var3);
	}

    protected boolean canThisPlantGrowOnThisBlockID(int par1)
    {
        return par1 == mod_ExtraBiomesXL.redRock.blockID;
    }

    public int idDropped(int par1, Random par2Random, int par3)
    {
        return -1;
	}
    
    public String getTextureFile()
    {
            return "/ExtraBiomesXL/extrabiomes.png";
    }
}
