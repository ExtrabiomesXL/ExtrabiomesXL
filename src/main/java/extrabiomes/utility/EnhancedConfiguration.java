/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.utility;

import java.io.File;
import java.util.Set;

import com.google.common.collect.Sets;

import extrabiomes.helpers.LogHelper;
import net.minecraft.world.biome.Biome;
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
	private static final int   MIN_BIOME_ID        = 40;
	private static final int   MAX_BIOME_ID        = 254;
    private final Set<Integer> configBiomes        = Sets.newHashSet();	// list of biome ID's claimed
    
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
        int biomeID = prop.getInt();
        
        if (biomeID == -1)
        {
            return prop;
        }
        
        if (Biome.getBiome(biomeID) == null && !configBiomes.contains(biomeID))
        {
            prop.set(Integer.toString(biomeID));
            configBiomes.add(biomeID);
            return prop;
        }
        
        if(configBiomes.contains(biomeID)) {
          LogHelper.warning("Warning two of Extrabiomes were set to Biome ID #%d in your config file.", biomeID);
        } else if(prop.getInt() != defaultID) {
          String msg = "Warning biome ID conflict.\n";
          msg += "According to ExtrabiomesXL's config file, biome id #%d was used by %s, but it has been overwritten by %s.\n";
          msg += "Any existing worlds may have incorrect biome information.";
          final Biome biome = Biome.getBiome(biomeID);
          LogHelper.warning(msg, prop.getInt(), key, biome.getBiomeClass().getName() + ":" + biome.getBiomeName());
        }
        
        for (int j = MIN_BIOME_ID; j < MAX_BIOME_ID; j++) {
            if (Biome.getBiome(j) == null && !configBiomes.contains(j)) {
                prop.set(Integer.toString(j));
                configBiomes.add(j);
                return prop;
            }
        }
        
        throw new RuntimeException("No more biome ids available for " + key);
    }
    
}
