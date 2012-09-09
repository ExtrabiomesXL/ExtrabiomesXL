/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IPlugin;

public class ExtrabiomesPlugin implements IPlugin {

	int	redRockId;

	ExtrabiomesPlugin(int redRockId) {
		this.redRockId = redRockId;
	}

	@Override
	public String getName() {
		return "Red Rock";
	}

	@Override
	public String getUniqueID() {
		return "EBXLRedRock";
	}

	@Override
	public void init() {
		BiomeManager.mountainridge.get().topBlock = (byte) redRockId;
		BiomeManager.mountainridge.get().fillerBlock = (byte) redRockId;
		ExtrabiomesLog.info("Added red rock to mountain ridge biome.");
	}

	@Override
	public boolean isEnabled() {
		return RedRock.isEnabled();
	}

	@Override
	public void postInit() {}

	@Override
	public void preInit() {}

}
