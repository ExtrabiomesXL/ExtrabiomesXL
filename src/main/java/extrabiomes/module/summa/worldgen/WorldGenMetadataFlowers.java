/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.worldgen;

import java.util.Random;

import extrabiomes.helpers.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

class WorldGenMetadataFlowers extends WorldGenerator
{
    private final int blockId;
    private final int metadata;
    
    WorldGenMetadataFlowers(int blockId, int metadata)
    {
        this.blockId = blockId;
        this.metadata = metadata;
    }
    
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        for (int var6 = 0; var6 < 64; ++var6)
        {
            final int x1 = x + rand.nextInt(8) - rand.nextInt(8);
            final int y1 = y + rand.nextInt(4) - rand.nextInt(4);
            final int z1 = z + rand.nextInt(8) - rand.nextInt(8);
            
            final boolean isAir = world.isAirBlock(x1, y1, z1);
            final boolean isSnow = (world.getBlockId(x1, y1, z) == Block.snow.blockID);
            
            try {
	            if ((isAir || isSnow) && Block.blocksList[blockId].canBlockStay(world, x1, y1, z1)) {
	                world.setBlock(x1, y1, z1, blockId, metadata, 2);
	            }
            } catch (Exception e) {
            	LogHelper.severe("We stopped a crash in the flower generator.");
            	LogHelper.severe("BlockID: %d, World: %s, BlockList[BlockId]: %s, Line: %d",
            		blockId,
            		(world == null) ? "null" : world.toString(), 
            		(Block.blocksList[blockId] == null) ? "null": Block.blocksList[blockId].toString(),
            		e.getStackTrace()[0].getLineNumber()
            	);
            }
        }
        
        return true;
    }
}
