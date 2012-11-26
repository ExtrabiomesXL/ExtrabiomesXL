/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.biome;

import java.util.Locale;

import net.minecraft.src.BiomeGenBase;
import extrabiomes.Extrabiomes;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.lib.BiomeSettings;

public enum VanillaBiome {
    DESERT, EXTREMEHILLS, FOREST, JUNGLE, PLAINS, SWAMPLAND, TAIGA;

    private static void createBiomes() throws InstantiationException, IllegalAccessException {
        for (final VanillaBiome biome : VanillaBiome.values()) {
            final BiomeGenBase biomeGen = biome.toBiomeGen();
            if (!biome.enableGeneration) {
                Extrabiomes.proxy.removeBiome(biomeGen);
                LogHelper.fine("Vanilla biome %s disabled.", biome.toString());
            }

            VillageSpawnHelper.setVillageSpawn(biomeGen, biome.enableVillages);
            LogHelper.fine("Village spawning %s for vanilla biome %s.",
                    biome.enableVillages ? "enabled" : "disabled", biome.toString());
        }
    }

    private static BiomeSettings getBiomeSettings(final VanillaBiome biome) {
        switch (biome) {
            case DESERT:
                return BiomeSettings.DESERT;
            case EXTREMEHILLS:
                return BiomeSettings.EXTREMEHILLS;
            case FOREST:
                return BiomeSettings.FOREST;
            case JUNGLE:
                return BiomeSettings.JUNGLE;
            case PLAINS:
                return BiomeSettings.PLAINS;
            case SWAMPLAND:
                return BiomeSettings.SWAMPLAND;
            case TAIGA:
                return BiomeSettings.TAIGA;
        }
        return null;
    }

    private static void loadSettings() {

        for (final VanillaBiome biome : VanillaBiome.values()) {

            final BiomeSettings settings = getBiomeSettings(biome);

            biome.enableGeneration = settings.isEnabled();
            biome.enableVillages = settings.allowVillages();
        }
    }

    public static void preInit() throws InstantiationException, IllegalAccessException {
        loadSettings();
        createBiomes();
    }

    private boolean enableGeneration = false;

    private boolean enableVillages   = false;

    private BiomeGenBase toBiomeGen() {
        switch (this) {
            case DESERT:
                return BiomeGenBase.desert;
            case EXTREMEHILLS:
                return BiomeGenBase.extremeHills;
            case FOREST:
                return BiomeGenBase.forest;
            case JUNGLE:
                return BiomeGenBase.jungle;
            case SWAMPLAND:
                return BiomeGenBase.swampland;
            case TAIGA:
                return BiomeGenBase.taiga;
            default:
                return BiomeGenBase.plains;
        }
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
