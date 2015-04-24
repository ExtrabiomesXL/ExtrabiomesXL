/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.utility;

import java.io.File;

import extrabiomes.helpers.LogHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

/**
 * Adds functionality to the Forge {@link net.minecraftforge.common.config.Configuration Configuration} class
 * 
 */
public class EnhancedConfiguration extends Configuration
{
    
    public static final String CATEGORY_BIOME      = "biome";
    public static final String CATEGORY_DECORATION = "decoration";
    public static final String CATEGORY_NEWDAWN    = "newdawn";
    public static final String CATEGORY_VERSION    = "version";
    public static final String CATEGORY_BLOCK      = "block";
    private final boolean[]    configBiomes        = new boolean[BiomeGenBase.getBiomeGenArray().length];
    
    public EnhancedConfiguration(File file)
    {
        super(file);
    }
    
    public Property getBiome(String key, int defaultID)
    {
        return getBiome(CATEGORY_BIOME, key, defaultID);
    }
    
    /**
     * Gets or create a biome id property. If the biome id property key is already in the configuration, then it will be used. Otherwise,
     * defaultId will be used, except if already taken, in which case this will try to determine a free default id.
     */
    public Property getBiome(String category, String key, int defaultID)
    {
        final Property prop = get(category, key, defaultID);
        
        if (prop.getInt() == -1)
        {
            //configBiomes[prop.getInt()] = true;
            return prop;
        }
        
        if (BiomeGenBase.getBiomeGenArray()[prop.getInt()] == null && !configBiomes[prop.getInt()])
        {
            prop.set(Integer.toString(prop.getInt()));
            configBiomes[prop.getInt()] = true;
            return prop;
        }
        
        if(configBiomes[prop.getInt()]) {
          LogHelper.warning("Warning two of Extrabiomes were set to Biome ID #%d in your config file.", prop.getInt());
        } else if(prop.getInt() != defaultID) {
          String msg = "Warning biome ID conflict.\n";
          msg += "According to ExtrabiomesXL's config file, biome id #%d was used by %s, but it has been overwritten by %s.\n";
          msg += "Any existing worlds may have incorrect biome information.";
          LogHelper.warning(msg, prop.getInt(), key, BiomeGenBase.getBiomeGenArray()[prop.getInt()].getBiomeClass().getName() + ":" + BiomeGenBase.getBiomeGenArray()[prop.getInt()].biomeName);
        }
        
        for (int j = 40; j < configBiomes.length - 1; j++) {
            if (BiomeGenBase.getBiomeGenArray()[j] == null && !configBiomes[j]) {
                prop.set(Integer.toString(j));
                configBiomes[j] = true;
                return prop;
            }
        }
        
        throw new RuntimeException("No more biome ids available for " + key);
    }
    
}
