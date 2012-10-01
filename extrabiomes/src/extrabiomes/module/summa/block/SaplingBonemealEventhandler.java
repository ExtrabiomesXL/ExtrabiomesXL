/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.summa.block;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

class SaplingBonemealEventhandler {

	private final BlockCustomSapling sapling;

	SaplingBonemealEventhandler(BlockCustomSapling sapling) {
		this.sapling = sapling;
	}

	@ForgeSubscribe
	public void onBonemealEvent(BonemealEvent e) {
		if (!e.isHandeled() && e.ID == sapling.blockID) {
			if (!e.world.isRemote)
				sapling.growTree(e.world, e.X, e.Y, e.Z, e.world.rand);
			e.setHandeled();
		}
	}

}
