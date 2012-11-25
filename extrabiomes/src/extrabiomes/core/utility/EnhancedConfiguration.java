/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.core.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.google.common.base.Optional;

/**
 * Addes functionality to the Forge
 * {@link net.minecraftforge.common.Configuration Configuration} class
 * 
 */
public class EnhancedConfiguration extends Configuration {

    public static final String  CATEGORY_BIOME  = "biome";
    private final List<Integer> assignedIdsList = new ArrayList<Integer>();
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
            prop.value = Integer.toString(defaultID);
            configBiomes[defaultID] = true;
            return prop;
        }

        for (int j = configBiomes.length - 1; j > 0; j--)
            if (BiomeGenBase.biomeList[j] == null && !configBiomes[j]) {
                prop.value = Integer.toString(j);
                configBiomes[j] = true;
                return prop;
            }

        throw new RuntimeException("No more biome ids available for " + key);
    }

    @Override
    public Property getBlock(String key, int defaultId) {
        return getBlock(key, defaultId, Block.blocksList.length);
    }

    private Property getBlock(String key, int defaultId, int exclusiveUpperBound) {
        Optional<? extends Map<String, Property>> properties = Optional.fromNullable(categories
                .get(CATEGORY_BLOCK));
        if (properties.isPresent() && properties.get().containsKey(key)) {
            final Property property = get(CATEGORY_BLOCK, key, defaultId);
            assignedIdsList.add(Integer.parseInt(property.value));
            return property;
        } else {
            if (!properties.isPresent()) {
                properties = Optional.of(new TreeMap<String, Property>());
                categories.put(CATEGORY_BLOCK, properties.get());
            }
            final Property property = new Property();
            properties.get().put(key, property);
            property.setName(key);

            if (Block.blocksList[defaultId] == null
                    && !assignedIdsList.contains(Integer.valueOf(defaultId)))
            {
                property.value = Integer.toString(defaultId);
                assignedIdsList.add(Integer.parseInt(property.value));
                return property;
            } else {
                for (int j = exclusiveUpperBound - 1; j >= 0; --j)
                    if (Block.blocksList[j] == null
                            && !assignedIdsList.contains(Integer.valueOf(j)))
                    {
                        property.value = Integer.toString(j);
                        assignedIdsList.add(Integer.parseInt(property.value));
                        return property;
                    }

                throw new RuntimeException("No more block ids available for " + key);
            }
        }
    }

    /**
     * Gets or create a block id property with a value of 255 or less.
     * If the block id property key is already in the configuration,
     * then it will be used. Otherwise, defaultId will be used, except
     * if already taken, in which case this will try to determine a free
     * default id.
     */
    public Property getRestrictedBlock(String key, int defaultId) {
        return getBlock(key, defaultId, 256);
    }

}
