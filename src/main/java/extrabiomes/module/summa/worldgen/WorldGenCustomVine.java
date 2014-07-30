package extrabiomes.module.summa.worldgen; 

import java.util.Random;

import extrabiomes.blocks.BlockCustomVine;
import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomVine extends WorldGenerator
{
	private final BlockCustomVine block;
    
    WorldGenCustomVine(BlockCustomVine block)
    {
        this.block = block;
    }
	
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int _x = x;

        for (int _z = z; y < 128; ++y)
        {
        	final Block targetBlock = world.getBlock(x, y, z);
            if (world.isAirBlock(x, y, z) || (targetBlock.equals(Blocks.vine)) )
            {
                for (int j = 2; j <= 5; ++j)
                {
                    if (block.canPlaceBlockOnSide(world, x, y, z, j))
                    {
                    	//LogHelper.info("Gloriosa placing at "+x+","+y+","+z);
                        world.setBlock(x, y, z, block, 1 << Direction.facingToDirection[Facing.oppositeSide[j]], 2);
                        break;
                    }
                }
            }
            else
            {
                x = _x + rand.nextInt(4) - rand.nextInt(4);
                z = _z + rand.nextInt(4) - rand.nextInt(4);
            }
        }

        return true;
    }
}
