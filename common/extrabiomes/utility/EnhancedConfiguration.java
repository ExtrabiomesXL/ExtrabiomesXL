/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.utility;

import java.io.File;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

/**
 * Addes functionality to the Forge
 * {@link net.minecraftforge.common.Configuration Configuration} class
 * 
 */
public class EnhancedConfiguration extends Configuration {

    public static final String  CATEGORY_BIOME  = "biome";
    public static final String  CATEGORY_DECORATION  = "decoration";
    private final boolean[]     configBiomes    = new boolean[BiomeGenBase.biomeList.length];

    public EnhancedConfiguration(File file) {
        super(file);
    }

    public Property getBiome(String key, int defaultID) {
        return getBiome(CATEGORY_BIOME, key, defaultID);
    }

    /**
     * Gets or create a biome id property. If the biome id property key
     * is already in the configuration, then it will be used. Otherwise,
     * defaultId will be used, except if already taken, in which case
     * this will try to determine a free default id.
     */
    public Property getBiome(String category, String key, int defaultID) {
        final Property prop = get(category, key, -1);

        if (prop.getInt() != -1) {
            configBiomes[prop.getInt()] = true;
            return prop;
        }

        if (BiomeGenBase.biomeList[defaultID] == null && !configBiomes[defaultID]) {
            prop.set(Integer.toString(defaultID));
            configBiomes[defaultID] = true;
            return prop;
        }

        for (int j = configBiomes.length - 1; j > 0; j--)
            if (BiomeGenBase.biomeList[j] == null && !configBiomes[j]) {
                prop.set(Integer.toString(j));
                configBiomes[j] = true;
                return prop;
            }

        throw new RuntimeException("No more biome ids available for " + key);
    }

}
