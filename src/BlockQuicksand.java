package net.minecraft.src;

import java.util.Random;

public class BlockQuicksand extends Block
{
    public BlockQuicksand(int par1, int par2)
    {
        super(par1, par2, Material.sand);
    }

    /**
     * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
     */
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

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return mod_ExtraBiomesXL.quicksand.blockID;
    }
}
