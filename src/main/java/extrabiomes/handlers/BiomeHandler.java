/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.handlers;

import java.util.Locale;
import java.util.Set;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

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
import extrabiomes.utility.EnhancedConfiguration;

public enum BiomeHandler
{
    INSTANCE;
    
    public static void enableBiomes()
    {
        final Set<WorldType> worldTypes = BiomeHelper.discoverWorldTypes();
        
        for (final BiomeSettings setting : BiomeSettings.values())
        {
            final Optional<? extends BiomeGenBase> biome = setting.getBiome();
            if (!setting.isVanilla())
            {
                if (setting.isEnabled() && biome.isPresent())
                {
                    BiomeHelper.enableBiome(worldTypes, biome.get());
                }
                else
                {
                    LogHelper.fine("Custom biome %s disabled.", setting.toString());
                }
            }
            else if (!setting.isEnabled())
            {
            	final BiomeGenBase bgb = BiomeHelper.settingToBiomeGenBase(setting);
                Extrabiomes.proxy.removeBiome(bgb);
                LogHelper.fine("Vanilla biome %s disabled.", bgb.toString());
            }
            
            if (setting.isEnabled() && setting.allowVillages() && biome.isPresent())
            {
                BiomeManager.addVillageBiome(biome.get(), true);
                LogHelper.fine("Village spawning enabled for custom biome %s.", setting.toString());
            }
        }
        
    }
    
    public static void init() throws Exception
    {
        for (final BiomeSettings biome : BiomeSettings.values())
        {
            if (biome.getID() > 0 && biome.isEnabled())
            {
                BiomeHelper.createBiome(biome);
                biome.postLoad();
            }
        }
        
        Api.getExtrabiomesXLEventBus().register(INSTANCE);
    }
    
    public static void registerWorldGenerators(EnhancedConfiguration config)
    {
        if (BiomeSettings.MARSH.isEnabled() && BiomeSettings.MARSH.getBiome().isPresent())
        {
            Extrabiomes.proxy.registerWorldGenerator(new MarshGenerator());
        }
        
        if (BiomeSettings.MOUNTAINDESERT.isEnabled() && BiomeSettings.MOUNTAINDESERT.getBiome().isPresent())
        {
            Extrabiomes.proxy.registerWorldGenerator(new MountainDesertGenerator());
        }
        
        if (BiomeSettings.MOUNTAINRIDGE.isEnabled() && BiomeSettings.MOUNTAINRIDGE.getBiome().isPresent())
        {
            Extrabiomes.proxy.registerWorldGenerator(new MountainRidgeGenerator());
        }
        
        Extrabiomes.proxy.registerWorldGenerator(new VanillaFloraGenerator());
        
        // allow legendary oaks to be disabled
        if( config.get(config.CATEGORY_GENERAL, "GenerateLegendOaks", true).getBoolean() ) {
        	Extrabiomes.proxy.registerWorldGenerator(new LegendOakGenerator());
        }
    }
    
    @SubscribeEvent
    public void handleBiomeIDRequestsFromAPI(GetBiomeIDEvent event)
    {
        final Optional<BiomeSettings> settings = Optional.fromNullable(BiomeSettings.valueOf(event.targetBiome.toUpperCase(Locale.ENGLISH)));
        if (settings.isPresent())
        {
            event.biomeID = settings.get().getID();
        }
    }
}
