/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.modsupport;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.CommonProxy;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginManager;
import extrabiomes.plugin.modsupport.buildcraft.BuildcraftPlugin;

@Mod(modid = "EBXLModSupport", name = "ExtrabiomesXL Mod Support Plugin", version = "3.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ModSupport {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy	proxy;
	@Instance("EBXLModSupport")
	public static ModSupport	instance;

	@Init
	public static void init(FMLInitializationEvent event) {
		ExtrabiomesLog.configureLogging();

		PluginManager.registerPlugin(new BuildcraftPlugin());
	}
}
