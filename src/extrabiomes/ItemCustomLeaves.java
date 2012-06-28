package extrabiomes;

import net.minecraft.src.Block;

public class ItemCustomLeaves extends MultiItemBlock {

	private static final int METADATA_USERPLACEDBIT = 0x4;

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
