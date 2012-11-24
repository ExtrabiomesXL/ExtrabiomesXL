/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.configuration;

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

public abstract class EnhancedConfiguration extends Configuration {

    public static final String   CATEGORY_BIOME  = "biome";
    private static List<Integer> assignedIdsList = new ArrayList<Integer>();
    private static boolean[]     configBiomes    = new boolean[BiomeGenBase.biomeList.length];

    public static void releaseStaticResources() {
        assignedIdsList = null;
    }

    public EnhancedConfiguration(File file) {
        super(file);
    }

    public Property getBiome(String key, int defaultID) {
        return getBiome(CATEGORY_BIOME, key, defaultID);
    }

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

    public Property getRestrictedBlock(String key, int defaultId) {
        return getBlock(key, defaultId, 256);
    }

}
