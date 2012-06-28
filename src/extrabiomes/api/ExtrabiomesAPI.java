package extrabiomes.api;

import java.util.Collection;
import net.minecraft.src.BiomeGenBase;

/**
 * The interface used by API client mods to alter the behavior of the
 * Extrabiomes XL mod and retrieve information from it.
 * <p>
 * Passed to API client mods upon initialization in
 * {@link UsesExtrabiomesAPI#onExtrabiomesAPIInitialized}. This interface is
 * implemented by the API.
 * 
 * @author ScottKillen
 */
public interface ExtrabiomesAPI {

	/**
	 * Retrieve the <code>BiomeGenBase</code> object that formulates the given
	 * Extrabiome.
	 * 
	 * @param biome
	 *            {@link Extrabiome} for which to request
	 *            <code>BiomeGenBase</code>.
	 * @return The <code>BiomeGenBase</code> that formulates the specified
	 *         Extrabiome. If the Extrabiome has been disabled,
	 *         <code>null</code> is returned.
	 */
	BiomeGenBase getBiome(Extrabiome biome);

	/**
	 * Retrieve the biomeId in use by a specific Extrabiome.
	 * 
	 * @param biome
	 *            {@link Extrabiome} for which to request biomeId.
	 * @return The biomeId in use by the specified Extrabiome. If the Extrabiome
	 *         has been disabled, 0 is returned.
	 */
	int getBiomeId(Extrabiome biome);

	/**
	 * Retrieve {@link MetaBlock} in use by Extrabiomes for terrain generation.
	 * 
	 * @param block
	 *            {@link TerrainGenBlock} for which to request
	 *            <code>MetaBlock</code>
	 * @return <code>MetaBlock</code> in use by extrabiomes for terrain
	 *         generation for the specified <code>TerrainGenBlock</code>. If a
	 *         substitution has occurred, this will be the substituted block.
	 */
	MetaBlock getMetaBlock(TerrainGenBlock block);

	/**
	 * Checks to see if the blockID has been registered as leaves. The vanilla
	 * leaves block is registered by Extrabiomes.
	 * 
	 * @param blockId
	 *            The id of the block to check.
	 * @return Returns <code>true</code> if the blockId has been registered as
	 *         leaves, otherwise <code>false</code>.
	 */
	boolean isLeaves(int blockId);

	/**
	 * Checks to see if the blockID has been registered as wood. The vanilla
	 * wood block is registered by Extrabiomes.
	 * 
	 * @param blockId
	 *            The id of the block to check.
	 * @return Returns <code>true</code> if the blockId has been registered as
	 *         wood, otherwise <code>false</code>.
	 */
	boolean isWood(int blockId);

	/**
	 * Registers blocks to be treated as leaves. Blocks thus registered are
	 * notified of decay and wood cutting like vanilla leaves. For this reason,
	 * bits 2 and 3 (0x4 and 0x8) of metadata should be reserved and behave
	 * exactly as they do for vanilla leaves.
	 * 
	 * @param blockId
	 *            the id of the block to register.
	 */
	void registerLeaves(int blockId);

	/**
	 * Registers blocks to be treated as wood. Blocks thus registered are Able
	 * to support Extrabiomes leaves and their removal causes leaf decay.
	 * 
	 * @param blockId
	 *            the id of the block to register.
	 */
	void registerWood(int blockId);

	/**
	 * Subscribe a {@link ExtrabiomeDecorateSubscriber} for participation in
	 * decoration of Extrabiomes
	 * 
	 * @param subscriber
	 *            The subscribing object, an instance of
	 *            <code>ExtrabiomeDecorateSubscriber</code>
	 * @param filter
	 *            <code>Collection</code> of {@link Extrabiome} object for which
	 *            to receive notification of decoration. The subscriber will
	 *            only participate in the decoration of the Extrabiomes within
	 *            this collection.
	 */
	void subscribeExtrabiomeDecorate(ExtrabiomeDecorateSubscriber subscriber,
			Collection<Extrabiome> filter);

}
