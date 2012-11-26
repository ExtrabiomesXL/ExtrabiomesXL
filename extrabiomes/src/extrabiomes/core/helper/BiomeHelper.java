/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.core.helper;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldType;
import net.minecraftforge.common.BiomeManager;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Api;
import extrabiomes.api.DiscoverWorldTypesEvent;
import extrabiomes.module.summa.biome.Biome;

public abstract class BiomeHelper {

    private static final Set<WorldType> worldTypes = new HashSet();

    /**
     * <pre>
     * static void createBiome(Biome biome);
     * </pre>
     * 
     * create a custom biome.
     * <p>
     * 
     * @param biome
     *            - the biome to create
     */
    public static void createBiome(Biome biome) throws Exception {
        if (BiomeGenBase.biomeList[biome.getSettings().getID()] != null)
            throw new IllegalArgumentException(
                    String.format(
                            "Biome id %d is already in use by %s when adding %s. Please review the configuration file.",
                            biome.getSettings().getID(), BiomeGenBase.biomeList[biome.getSettings()
                                    .getID()].biomeName, biome.toString()));

        biome.biome = Optional.of(biome.biomeClass.newInstance());
    }

    /**
     * <pre>
     * static Set&lt;WorldType&gt; discoverWorldTypes();
     * </pre>
     * 
     * Allow other mods to add ExtrabiomesXL biomes to their custom
     * world types.
     * <p>
     * 
     * @return An immutable set of world types.
     */
    public static Set<WorldType> discoverWorldTypes() {
        if (worldTypes.isEmpty()) {
            worldTypes.add(WorldType.DEFAULT);
            worldTypes.add(WorldType.LARGE_BIOMES);
            final DiscoverWorldTypesEvent event = new DiscoverWorldTypesEvent(worldTypes);
            Api.getExtrabiomesXLEventBus().post(event);
        }
        return ImmutableSet.copyOf(worldTypes);
    }

    /**
     * <pre>
     * static void enableBiome(BiomeGenBase biome);
     * </pre>
     * 
     * enable a custom biome.
     * <p>
     * 
     * @param worldTypes
     *            - a collection of worldTypes in which to enable these
     *            biomes
     * @param biome
     *            - the BiomeGenBase to add
     */
    public static void enableBiome(Set<WorldType> worldTypes, BiomeGenBase biome) {
        Extrabiomes.proxy.addBiome(worldTypes, biome);
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
    }
}
