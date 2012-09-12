/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.terrainfeatures;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import extrabiomes.CommonProxy;

@Mod(modid = "EXBLTerrain", name = "ExtrabiomesXL Terrain Features Plugin", version = "3.0")
@NetworkMod(clientSideRequired = false, serverSideRequired = false)
public class TerrainFeatures {

	@SidedProxy(clientSide = "extrabiomes.client.ClientProxy", serverSide = "extrabiomes.CommonProxy")
	public static CommonProxy		proxy;
	@Instance("EXBLTerrain")
	public static TerrainFeatures	instance;

	@Init
	public static void init(FMLInitializationEvent event) {
		proxy.registerWorldGenerator(new FeatureGenerator());
	}
}
