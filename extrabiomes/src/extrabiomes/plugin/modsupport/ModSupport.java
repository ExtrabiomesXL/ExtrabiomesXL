/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.plugin.modsupport;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.ExtrabiomesLog;
import extrabiomes.api.PluginManager;
import extrabiomes.plugin.modsupport.buildcraft.BuildcraftPlugin;
import extrabiomes.proxy.CommonProxy;

@Mod(modid = "EBXLModSupport", name = "ExtrabiomesXL Mod Support Plugin", version = "3.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class ModSupport {

	@SidedProxy(clientSide = "extrabiomes.proxy.ClientProxy", serverSide = "extrabiomes.proxy.CommonProxy")
	public static CommonProxy	proxy;
	@Instance("EBXLModSupport")
	public static ModSupport	instance;

	@Init
	public static void init(FMLInitializationEvent event) {
		ExtrabiomesLog.configureLogging();

		PluginManager.registerPlugin(new BuildcraftPlugin());
	}
}
