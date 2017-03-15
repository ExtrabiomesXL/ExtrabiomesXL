/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenQuicksand extends WorldGenerator
{
    
    private final Block quicksand;
    
    WorldGenQuicksand(Block quicksand)
    {
        this.quicksand = quicksand;
    }
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        while ((world.isAirBlock(pos) || !world.getBlockState(pos).isNormalCube()) && pos.getY() > 2)
            pos.down();
        
        final IBlockState state = world.getBlockState(pos);
        if (!state.getBlock().equals(Blocks.GRASS) && !state.getBlock().equals(Blocks.SAND))
            return false;
        
        for (int x1 = -2; x1 <= 2; x1++)
            for (int z1 = -2; z1 <= 2; z1++)
                if (world.isAirBlock(new BlockPos(pos).add(x1, -1, z1))
                        && world.isAirBlock(new BlockPos(pos).add(x1, -2, z1)))
                    return false;
        
        for (int x1 = -1; x1 <= 1; x1++)
            for (int z1 = -1; z1 <= 1; z1++)
                for (int y1 = -2; y1 <= 0; y1++)
                    world.setBlockState(new BlockPos(pos).add(x1, y1, z1), quicksand.getDefaultState());
        
        return true;
    }
    
}
