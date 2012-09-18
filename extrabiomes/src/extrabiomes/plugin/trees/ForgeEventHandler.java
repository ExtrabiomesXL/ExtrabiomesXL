/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.trees;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class ForgeEventHandler {

	private final BlockCustomSapling	sapling;

	ForgeEventHandler(BlockCustomSapling sapling) {
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
