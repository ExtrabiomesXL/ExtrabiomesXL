/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import net.minecraft.util.WeightedRandom.Item;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WeightedWorldGenerator extends Item
{
    
    private final WorldGenerator worldGen;
    
    public WeightedWorldGenerator(WorldGenerator worldGen, int weight)
    {
        super(weight);
        this.worldGen = worldGen;
    }
    
    public WorldGenerator getWorldGen()
    {
        return worldGen;
    }
    
}
