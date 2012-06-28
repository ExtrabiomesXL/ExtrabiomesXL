package extrabiomes.api;

/**
 * A convenience class that aids manipulation of blockId/metadata pairs
 * 
 * @author ScottKillen
 */
public interface MetaBlock {

	/**
	 * Returns to stored blockId.
	 * 
	 * @return the stored blockId.
	 */
	public int blockId();

	/**
	 * Returns the stored metadata.
	 * 
	 * @return the stored metadata.
	 */
	public int metadata();

}