/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes;


public class ItemCustomLeaves extends MultiItemBlock {

	private static final int	METADATA_USERPLACEDBIT	= 0x4;

	private static int setUserPlacedOnMetadata(final int metadata) {
		return metadata | METADATA_USERPLACEDBIT;
	}

	public ItemCustomLeaves(final int id) {
		super(id);
	}

	@Override
	public int getMetadata(final int metadata) {
		return setUserPlacedOnMetadata(metadata);
	}
}
