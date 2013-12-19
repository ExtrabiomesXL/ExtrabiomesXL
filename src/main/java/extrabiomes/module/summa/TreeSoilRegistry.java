/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

public enum TreeSoilRegistry
{
    INSTANCE;
    
    private static final List<Block> validSoil;
    
    static
    {
        validSoil = new ArrayList<Block>();
        addValidSoil(Block.grass);
        addValidSoil(Block.dirt);
        addValidSoil(Block.tilledField);
    }
    
    public static void addValidSoil(Block soilBlock)
    {
        validSoil.add(soilBlock);
    }
    
    public static boolean isValidSoil(Block soil)
    {
        return validSoil.contains(soil);
    }
    
    public static boolean isValidSoil(int blockID)
    {
        return isValidSoil(Block.blocksList[blockID]);
    }
}
