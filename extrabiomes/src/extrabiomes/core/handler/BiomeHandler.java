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
import net.minecraftforge.event.ForgeSubscribe;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Api;
import extrabiomes.api.events.GetBiomeIDEvent;
import extrabiomes.core.helper.BiomeHelper;
import extrabiomes.core.helper.LogHelper;
import extrabiomes.lib.BiomeSettings;

public enum BiomeHandler {
    INSTANCE;

    private static List<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();

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

    @ForgeSubscribe
    public void handleBiomeIDRequestsFromAPI(GetBiomeIDEvent event) {
        final Optional<BiomeSettings> settings = Optional.fromNullable(BiomeSettings
                .valueOf(event.targetBiome.toUpperCase()));
        if (settings.isPresent()) event.biomeID = settings.get().getID();
    }
}
