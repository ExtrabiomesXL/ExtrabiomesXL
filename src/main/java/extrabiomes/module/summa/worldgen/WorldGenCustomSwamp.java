/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomSwamp extends WorldGenerator
{
    
    public WorldGenCustomSwamp()
    {
        super();
    }
    
    public WorldGenCustomSwamp(boolean notify)
    {
        super(notify);
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y,
            int z)
    {
        
        while (world.getBlockMaterial(x, y - 1, z) == Material.water)
            y--;
        
        final int height = rand.nextInt(4) + 10;
        
        if (y < 1 || y + height + 1 > 256)
            return false;
        
        for (int y1 = y; y1 <= y + 1 + height; y1++)
        {
            
            if (y1 < 0 && y1 >= 256)
                return false;
            
            byte clearanceNeededAroundTrunk = 1;
            
            if (y1 == y)
                clearanceNeededAroundTrunk = 0;
            
            if (y1 >= y + 1 + height - 2)
                clearanceNeededAroundTrunk = 3;
            
            for (int x1 = x - clearanceNeededAroundTrunk; x1 <= x
                    + clearanceNeededAroundTrunk; x1++)
                for (int x2 = z - clearanceNeededAroundTrunk; x2 <= z
                        + clearanceNeededAroundTrunk; x2++)
                {
                    final int id = world.getBlock(x1, y1, x2);
                    
                    if (Block.blocksList[id] == null
                            || Block.blocksList[id].isLeaves(world, x1,
                                    y1, x2))
                        continue;
                    
                    if (id == Block.waterStill
                            || id == Block.waterMoving)
                    {
                        if (y1 > y)
                            return false;
                    }
                    else
                        return false;
                }
        }
        
        final int id = world.getBlock(x, y - 1, z);
        
        if (id != Block.grass && id != Block.dirt
                || y >= 256 - height - 1)
            return false;
        
        world.setBlock(x, y - 1, z, Block.dirt);
        
        for (int y1 = y - 3 + height; y1 <= y + height; y1++)
        {
            final int posTrunk = y1 - (y + height);
            final int canopyRadius = 2 - posTrunk / 2;
            
            for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++)
            {
                final int xOnRadius = x1 - x;
                
                for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++)
                {
                    final int zOnRadius = z1 - z;
                    
                    final Block block = Block.blocksList[world
                            .getBlockId(x1, y1, z1)];
                    
                    if ((Math.abs(xOnRadius) != canopyRadius
                            || Math.abs(zOnRadius) != canopyRadius || rand
                            .nextInt(2) != 0 && posTrunk != 0)
                            && (block == null || block
                                    .canBeReplacedByLeaves(world, x1,
                                            y1, z1)))
                        world.setBlock(x1, y1, z1, Block.leaves);
                }
            }
        }
        
        for (int y1 = 0; y1 < height; y1++)
        {
            final int id2 = world.getBlock(x, y + y1, z);
            
            if (id2 == 0
                    || Block.blocksList[id2].isLeaves(world, x, y + y1,
                            z) || id2 == Block.waterMoving
                    || id2 == Block.waterStill)
                world.setBlock(x, y + y1, z, Block.wood);
        }
        
        for (int y1 = y - 3 + height; y1 <= y + height; y1++)
        {
            final int posTrunk = y1 - (y + height);
            final int canopyRadius = 2 - posTrunk / 2;
            
            for (int x1 = x - canopyRadius; x1 <= x + canopyRadius; x1++)
                for (int z1 = z - canopyRadius; z1 <= z + canopyRadius; z1++)
                {
                    final int id2 = world.getBlock(x1, y1, z1);
                    if (id2 == 0
                            || !Block.blocksList[id2].isLeaves(world,
                                    x1, y1, z1))
                        continue;
                    
                    if (rand.nextInt(4) == 0
                            && world.getBlock(x1 - 1, y1, z1) == 0)
                        generateVines(world, x1 - 1, y1, z1, 8);
                    
                    if (rand.nextInt(4) == 0
                            && world.getBlock(x1 + 1, y1, z1) == 0)
                        generateVines(world, x1 + 1, y1, z1, 2);
                    
                    if (rand.nextInt(4) == 0
                            && world.getBlock(x1, y1, z1 - 1) == 0)
                        generateVines(world, x1, y1, z1 - 1, 1);
                    
                    if (rand.nextInt(4) == 0
                            && world.getBlock(x1, y1, z1 + 1) == 0)
                        generateVines(world, x1, y1, z1 + 1, 4);
                }
        }
        
        return true;
    }
    
    private void generateVines(World world, int x, int y, int z,
            int metadata)
    {
        world.setBlock(x, y, z,
                Block.vine, metadata, 3);
        
        for (int i = 4; world.getBlock(x, --y, z) == 0 && i > 0; i--)
            world.setBlock(x, y, z,
                    Block.vine, metadata, 3);
    }
}
