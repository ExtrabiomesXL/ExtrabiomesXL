/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.util.Set;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Api;
import extrabiomes.api.events.GetBiomeIDEvent;
import extrabiomes.helpers.BiomeHelper;
import extrabiomes.helpers.LogHelper;
import extrabiomes.lib.BiomeSettings;
import extrabiomes.module.summa.worldgen.LegendOakGenerator;
import extrabiomes.module.summa.worldgen.MarshGenerator;
import extrabiomes.module.summa.worldgen.MountainDesertGenerator;
import extrabiomes.module.summa.worldgen.MountainRidgeGenerator;
import extrabiomes.module.summa.worldgen.VanillaFloraGenerator;

public enum BiomeHandler {
    INSTANCE;

    public static void enableBiomes() {
        final Set<WorldType> worldTypes = BiomeHelper.discoverWorldTypes();

        for (final BiomeSettings setting : BiomeSettings.values()) {
            final Optional<? extends BiomeGenBase> biome = setting.getBiome();
            if (!setting.isVanilla()) {
                if (setting.isEnabled() && biome.isPresent())
                    BiomeHelper.enableBiome(worldTypes, biome.get());
                else
                    LogHelper.fine("Custom biome %s disabled.", setting.toString());
            } else if (!setting.isEnabled()) {
                Extrabiomes.proxy.removeBiome(BiomeHelper.settingToBiomeGenBase(setting));
                LogHelper.fine("Vanilla biome %s disabled.", biome.toString());
            }

            if (setting.allowVillages() && biome.isPresent()) {
                BiomeManager.addVillageBiome(biome.get(), true);
                LogHelper.fine("Village spawning enabled for custom biome %s.", setting.toString());
            }
        }

    }

    public static void init() throws Exception {
        for (final BiomeSettings biome : BiomeSettings.values())
            if (biome.getID() > 0) BiomeHelper.createBiome(biome);

        Api.getExtrabiomesXLEventBus().register(INSTANCE);
    }

    public static void registerWorldGenerators() {
        if (BiomeSettings.MARSH.getBiome().isPresent())
            Extrabiomes.proxy.registerWorldGenerator(new MarshGenerator());

        if (BiomeSettings.MOUNTAINDESERT.getBiome().isPresent())
            Extrabiomes.proxy.registerWorldGenerator(new MountainDesertGenerator());

        if (BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent())
            Extrabiomes.proxy.registerWorldGenerator(new MountainRidgeGenerator());

        Extrabiomes.proxy.registerWorldGenerator(new VanillaFloraGenerator());
        Extrabiomes.proxy.registerWorldGenerator(new LegendOakGenerator());
    }

    @ForgeSubscribe
    public void handleBiomeIDRequestsFromAPI(GetBiomeIDEvent event) {
        final Optional<BiomeSettings> settings = Optional.fromNullable(BiomeSettings
                .valueOf(event.targetBiome.toUpperCase()));
        if (settings.isPresent()) event.biomeID = settings.get().getID();
    }
}
