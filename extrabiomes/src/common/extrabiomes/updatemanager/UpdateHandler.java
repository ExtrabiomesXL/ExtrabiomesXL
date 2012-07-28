/**
 * Copyright (c) Scott Killen and MisterFiber, 2012
 * 
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.updatemanager;

import vazkii.um.UpdateManagerMod;
import cpw.mods.fml.common.modloader.BaseMod;
import extrabiomes.Proxy;

public class UpdateHandler extends UpdateManagerMod {

	private static final String	CHANGELOG_URL	= "https://raw.github.com/ScottKillen/ExtrabiomesXL/master/extrabiomes/Change%20Log.txt";
	private static final String	MOD_URL			= "http://www.minecraftforum.net/topic/1090288-";
	private static final String	UPDATE_URL		= "https://raw.github.com/ScottKillen/ExtrabiomesXL/master/extrabiomes/version.txt";

	public UpdateHandler(BaseMod m) {
		super(m);
	}

	public String getChangelogURL() {
		return CHANGELOG_URL;
	}

	public String getDirectDownloadURL() {
		return Proxy.getModDownloadURL();
	}

	public String getModURL() {
		return MOD_URL;
	}

	@Override
	public String getUpdateURL() {
		return UPDATE_URL;
	}

}
