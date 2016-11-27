/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.lib;

import net.minecraft.block.Block;

public interface ILeafSerializable extends IMetaSerializable {
	Block getSaplingBlock();
    
    int getSaplingMetadata();
}