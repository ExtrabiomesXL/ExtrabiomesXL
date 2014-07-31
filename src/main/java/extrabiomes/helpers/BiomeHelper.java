/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeManager;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

import extrabiomes.Extrabiomes;
import extrabiomes.api.Api;
import extrabiomes.api.DiscoverWorldTypesEvent;
import extrabiomes.lib.BiomeSettings;

public abstract class BiomeHelper
{
    
    private static final Set<WorldType>                        worldTypes   = new HashSet<WorldType>();
    
    private static Optional<? extends ArrayList<BiomeGenBase>> activeBiomes = Optional.absent();
    
    public static void addTerrainBlockstoBiome(BiomeSettings biome, Block topBlock, Block fillerBlock)
    {
        if (!biome.getBiome().isPresent())
            return;
        final BiomeGenBase baseBiome = biome.getBiome().get();
        baseBiome.topBlock = topBlock;
        baseBiome.fillerBlock = fillerBlock;
    }
    
    /**
     * <pre>
     * static void createBiome(BiomeSettings biome);
     * </pre>
     * 
     * create a custom biome.
     * <p>
     * 
     * @param setting - the biome to create
     */
    public static void createBiome(BiomeSettings setting) throws Exception
    {
        if (BiomeGenBase.getBiomeGenArray()[setting.getID()] != null)
        {
            throw new IllegalArgumentException(String.format("Biome id %d is already in use by %s when adding %s. Please review the configuration file.", setting.getID(), BiomeGenBase.getBiomeGenArray()[setting.getID()].biomeName, setting.toString()));
        }
        
        setting.createBiome();
    }
    
    /**
     * <pre>
     * static Set&lt;WorldType&gt; discoverWorldTypes();
     * </pre>
     * 
     * Allow other mods to add ExtrabiomesXL biomes to their custom world types.
     * <p>
     * 
     * @return An immutable set of world types.
     */
    public static Set<WorldType> discoverWorldTypes()
    {
        if (worldTypes.isEmpty())
        {
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
     * @param worldTypes - a collection of worldTypes in which to enable these biomes
     * @param biome - the BiomeGenBase to add
     */
    public static void enableBiome(Set<WorldType> worldTypes, BiomeGenBase biome)
    {
        Extrabiomes.proxy.addBiome(worldTypes, biome);
        BiomeManager.addSpawnBiome(biome);
        BiomeManager.addStrongholdBiome(biome);
    }
    
    public static Collection<BiomeGenBase> getActiveBiomes()
    {
        if (!activeBiomes.isPresent())
        {
            activeBiomes = Optional.of(new ArrayList<BiomeGenBase>(BiomeSettings.values().length));
            for (final BiomeSettings setting : BiomeSettings.values())
            {
                if (setting.getBiome().isPresent() && !setting.isVanilla())
                {
                    activeBiomes.get().add(setting.getBiome().get());
                }
            }
            activeBiomes.get().trimToSize();
        }
        return ImmutableSet.copyOf(activeBiomes.get());
    }
    
    public static BiomeGenBase settingToBiomeGenBase(BiomeSettings setting)
    {
        switch (setting)
        {
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
            case PLAINS:
                return BiomeGenBase.plains;
			case OCEAN:
				return BiomeGenBase.ocean;
            default:
            	if (setting.getBiome().isPresent()) {
            		return setting.getBiome().get();
            	}
        }
        return null;
    }
    
    @SuppressWarnings("deprecation")
    public static void addWeightedGrassGen(Optional<? extends BiomeGenBase> biome,
            WorldGenerator grassGen, int weight)
    {
        if (!biome.isPresent())
            return;
        
        extrabiomes.api.BiomeManager.addWeightedGrassGenForBiome(biome.get(), grassGen, weight);
    }
}
