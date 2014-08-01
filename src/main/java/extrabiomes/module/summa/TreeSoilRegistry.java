/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public enum TreeSoilRegistry
{
    INSTANCE;
    
    private static final List<Block> validSoil;
    
    static
    {
        validSoil = new ArrayList<Block>();
        addValidSoil(Blocks.grass);
        addValidSoil(Blocks.dirt);
        addValidSoil(Blocks.farmland);
    }
    
    public static void addValidSoil(Block soilBlock)
    {
        validSoil.add(soilBlock);
    }
    
    public static boolean isValidSoil(Block soil)
    {
        return validSoil.contains(soil);
    }
}
