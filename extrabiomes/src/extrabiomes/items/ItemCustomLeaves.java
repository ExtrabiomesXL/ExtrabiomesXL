/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.items;

import extrabiomes.utility.MultiItemBlock;

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
