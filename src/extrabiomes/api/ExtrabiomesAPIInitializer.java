package extrabiomes.api;

import java.util.Collection;
import java.util.Map;

import net.minecraft.src.BiomeGenBase;

/**
 * The <code>ExtrabiomesAPIInitializer</code> is provided by the API client mod
 * during initialization. This object is used by the API to alter initialization
 * of the Extrabiomes mod. This interface is implemented by the API client mod.
 * 
 * @author ScottKillen
 */
public interface ExtrabiomesAPIInitializer {

	/**
	 * <code>getCustomBiomes</code> is called <i>after</i> initialization to
	 * retrieve a collection of custom biomes provided by the API client mod.
	 * The biomes are added to the pool of biomes available to the game. This is
	 * analogous to calling ModLoader.addBiome() and provides no additional
	 * error checking. Caution should be exercised in choosing available
	 * <code>biomeId</code>s. This can be done by examining
	 * <code>BiomeGenBase.biomesList</code>, which is indexed by used
	 * <code>biomeId</code>s.
	 * 
	 * @param api
	 *            A proxy to the Extrabiomes API. This object remains in scope
	 *            through the life of Extrabiomes XL.
	 * 
	 * @return A <code>Collection</code> of {@link BiomeGenBase} objects that
	 *         implement the biomes provided by the API client mod. If the API
	 *         client mod provides no custom biomes, this should return
	 *         <code>null</code>.
	 */
	Collection<BiomeGenBase> getCustomBiomes(ExtrabiomesAPI api);

	/**
	 * <code>getDisabledExtrabiomes</code> is called during initialization to
	 * retrieve a collection of Extrabiomes to disable.
	 * 
	 * @return A <code>Collection</code> of {@link Extrabiome} objects that
	 *         contains the Extrabiomes to disable, overriding the players
	 *         config settings. If the API client mod disables no Extrabiomes,
	 *         this should return <code>null</code>.
	 */
	Collection<Extrabiome> getDisabledExtrabiomes();

	/**
	 * <code>getDisabledFlora</code> is called for each {@link Extrabiome}
	 * during initialization to retrieve a collection of flora to disable for
	 * that biome.
	 * 
	 * @param biome
	 *            <code>Extrabiome</code> provided to retrieve a collection of
	 *            disabled <code>Flora</code>.
	 * 
	 * @return A <code>Collection</code> of {@link Flora} objects that contains
	 *         the flora to disable. If the API client mod disables no flora,
	 *         this should return <code>null</code>.
	 */
	Collection<Flora> getDisabledFlora(Extrabiome biome);

	/**
	 * <code>getDisabledVanillaBiomes</code> is called during initialization to
	 * retrieve a collection of vanilla biomes to disable.
	 * 
	 * @return A <code>Collection</code> of {@link RemovableVanillaBiome}
	 *         objects that contains the vanilla biomes to disable, overriding
	 *         the players config settings. If the API client mod disables no
	 *         vanilla biomes, this should return <code>null</code>. This is
	 *         analogous to calling ModLoader.removeBiome() for these biomes.
	 */
	Collection<RemovableVanillaBiome> getDisabledVanillaBiomes();

	/**
	 * <code>getMetaBlockSubstitutions</code> is called <i>after</i>
	 * initialization to retrieve substitutions for Extrabiomes
	 * {@link TerrainGenBlock}s.
	 * 
	 * @param api
	 *            A proxy to the Extrabiomes API. This object remains in scope
	 *            through the life of Extrabiomes XL.
	 * 
	 * @return A <code>Map</code> in which <code>TerrainGenBlock</code> keys
	 *         target the block to be substituted with objects that contains the
	 *         vanilla biomes to disable, overriding the players config
	 *         settings. If the API client mod disables no vanilla biomes, this
	 *         should return <code>null</code>.
	 */
	Map<TerrainGenBlock, MetaBlock> getMetaBlockSubstitutions(ExtrabiomesAPI api);

}
