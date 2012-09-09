/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.crackedsand;

import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.BiomeManager;
import extrabiomes.api.IPlugin;

public class ExtrabiomesPlugin implements IPlugin {

	int	crackedSandID;

	ExtrabiomesPlugin(int crackedSandID) {
		this.crackedSandID = crackedSandID;
	}

	@Override
	public String getName() {
		return "Cracked Sand";
	}

	@Override
	public String getUniqueID() {
		return "EBXLCrackedSand";
	}

	@Override
	public void init() {
		BiomeManager.wasteland.get().topBlock = (byte) crackedSandID;
		BiomeManager.wasteland.get().fillerBlock = (byte) crackedSandID;
		ExtrabiomesLog.info("Added cracked sand to wasteland biome.");
	}

	@Override
	public boolean isEnabled() {
		return CrackedSand.isEnabled();
	}

	@Override
	public void postInit() {}

	@Override
	public void preInit() {}

}
