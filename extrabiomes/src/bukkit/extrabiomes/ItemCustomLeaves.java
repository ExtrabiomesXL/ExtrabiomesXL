
package extrabiomes;

public class ItemCustomLeaves extends MultiItemBlock {
	private static int setUserPlacedOnMetadata(int i) {
		return i | 4;
	}

	public ItemCustomLeaves(int i) {
		super(i);
	}

	/**
	 * Returns the metadata of the block which this Item (ItemBlock) can
	 * place
	 */
	@Override
	public int filterData(int i) {
		return setUserPlacedOnMetadata(i);
	}
}
