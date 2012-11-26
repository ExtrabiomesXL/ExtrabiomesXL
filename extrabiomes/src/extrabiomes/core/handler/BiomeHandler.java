/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.core.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.minecraft.src.BiomeGenBase;
import net.minecraft.src.WorldType;
import net.minecraftforge.common.BiomeManager;
import extrabiomes.core.helper.BiomeHelper;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.module.summa.biome.Biome;

public abstract class BiomeHandler {

    private static List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

    public static void enableBiomes() {
        final Set<WorldType> worldTypes = BiomeHelper.discoverWorldTypes();

        for (final Biome biome : Biome.values()) {
            if (biome.getSettings().isEnabled() && biome.biome.isPresent())
                BiomeHelper.enableBiome(worldTypes, biome.biome.get());
            else
                LogHelper.fine("Custom biome %s disabled.", biome.toString());

            if (biome.getSettings().allowVillages() && biome.biome.isPresent()) {
                BiomeManager.addVillageBiome(biome.biome.get(), true);
                LogHelper.fine("Village spawning enabled for custom biome %s.", biome.toString());
            }
        }

    }

    public static void init() throws Exception {
        for (final Biome biome : Biome.values())
            if (biome.getSettings().getID() > 0) BiomeHelper.createBiome(biome);
    }
}
