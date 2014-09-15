package extrabiomes.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.Extrabiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.Direction;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCustomVine extends BlockVine {

	public enum BlockType {
		GLORIOSA, SPANISH_MOSS;
	}

	public final BlockType	type;

	public BlockCustomVine(BlockType type) {
		super();
		this.type = type;
		setHardness(0.2F);
		setStepSound(soundTypeGrass);
	}
	
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
    	final String IIconPath = Extrabiomes.TEXTURE_PATH + "vine_" + type.toString().toLowerCase();
		this.blockIcon = iconRegister.registerIcon(IIconPath);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    @Override
    public void updateTick(World world, int x, int y, int z, Random rnd)
    {
        if (!world.isRemote && world.rand.nextInt(8) == 0)
        {
            byte b0 = 4;
            int l = 5;
            boolean flag = false;
            int i1;
            int j1;
            int k1;
            label134:

            for (i1 = x - b0; i1 <= x + b0; ++i1)
            {
                for (j1 = z - b0; j1 <= z + b0; ++j1)
                {
                    for (k1 = y - 1; k1 <= y + 1; ++k1)
                    {
                        if (world.getBlock(i1, k1, j1) == this)
                        {
                            --l;

                            if (l <= 0)
                            {
                                flag = true;
                                break label134;
                            }
                        }
                    }
                }
            }

            i1 = world.getBlockMetadata(x, y, z);
            j1 = world.rand.nextInt(6);
            k1 = Direction.facingToDirection[j1];
            int l1;

            if (j1 == 1 && y < 255 && world.isAirBlock(x, y + 1, z))
            {
                if (flag)
                {
                    return;
                }

                int j2 = world.rand.nextInt(16) & i1;

                if (j2 > 0)
                {
                    for (l1 = 0; l1 <= 3; ++l1)
                    {
                        if (!this.func_150093_a(world.getBlock(x + Direction.offsetX[l1], y + 1, z + Direction.offsetZ[l1])))
                        {
                            j2 &= ~(1 << l1);
                        }
                    }

                    if (j2 > 0)
                    {
                        world.setBlock(x, y + 1, z, this, j2, 2);
                    }
                }
            }
            else
            {
                Block block;
                int i2;

                if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0)
                {
                    if (flag)
                    {
                        return;
                    }

                    block = world.getBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1]);

                    if (block.getMaterial() == Material.air)
                    {
                        l1 = k1 + 1 & 3;
                        i2 = k1 + 3 & 3;

                        if ((i1 & 1 << l1) != 0 && this.func_150093_a(world.getBlock(x + Direction.offsetX[k1] + Direction.offsetX[l1], y, z + Direction.offsetZ[k1] + Direction.offsetZ[l1])))
                        {
                            world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], this, 1 << l1, 2);
                        }
                        else if ((i1 & 1 << i2) != 0 && this.func_150093_a(world.getBlock(x + Direction.offsetX[k1] + Direction.offsetX[i2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[i2])))
                        {
                            world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], this, 1 << i2, 2);
                        }
                        else if ((i1 & 1 << l1) != 0 && world.isAirBlock(x + Direction.offsetX[k1] + Direction.offsetX[l1], y, z + Direction.offsetZ[k1] + Direction.offsetZ[l1]) && this.func_150093_a(world.getBlock(x + Direction.offsetX[l1], y, z + Direction.offsetZ[l1])))
                        {
                            world.setBlock(x + Direction.offsetX[k1] + Direction.offsetX[l1], y, z + Direction.offsetZ[k1] + Direction.offsetZ[l1], this, 1 << (k1 + 2 & 3), 2);
                        }
                        else if ((i1 & 1 << i2) != 0 && world.isAirBlock(x + Direction.offsetX[k1] + Direction.offsetX[i2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[i2]) && this.func_150093_a(world.getBlock(x + Direction.offsetX[i2], y, z + Direction.offsetZ[i2])))
                        {
                            world.setBlock(x + Direction.offsetX[k1] + Direction.offsetX[i2], y, z + Direction.offsetZ[k1] + Direction.offsetZ[i2], this, 1 << (k1 + 2 & 3), 2);
                        }
                        else if (this.func_150093_a(world.getBlock(x + Direction.offsetX[k1], y + 1, z + Direction.offsetZ[k1])))
                        {
                            world.setBlock(x + Direction.offsetX[k1], y, z + Direction.offsetZ[k1], this, 0, 2);
                        }
                    }
                    else if (block.getMaterial().isOpaque() && block.renderAsNormalBlock())
                    {
                        world.setBlockMetadataWithNotify(x, y, z, i1 | 1 << k1, 2);
                    }
                }
                else if (y > 1)
                {
                    block = world.getBlock(x, y - 1, z);

                    if (block.getMaterial() == Material.air)
                    {
                        l1 = world.rand.nextInt(16) & i1;

                        if (l1 > 0)
                        {
                            world.setBlock(x, y - 1, z, this, l1, 2);
                        }
                    }
                    else if (block == this)
                    {
                        l1 = world.rand.nextInt(16) & i1;
                        i2 = world.getBlockMetadata(x, y - 1, z);

                        if (i2 != (i2 | l1))
                        {
                            world.setBlockMetadataWithNotify(x, y - 1, z, i2 | l1, 2);
                        }
                    }
                }
            }
        }
    }

    private boolean func_150093_a(Block p_150093_1_)
    {
        return p_150093_1_.renderAsNormalBlock() && p_150093_1_.getMaterial().blocksMovement();
    }

    /*
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return 0xffffff;
    }//*/
    
    @SideOnly(Side.CLIENT)

    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        return 0xffffff;
    }

}
