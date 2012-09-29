/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.scarecrow;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import extrabiomes.proxy.CommonProxy;

public class ScarecrowClientProxy extends ScarecrowProxy {

	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient
				.preloadTexture("/extrabiomes/extrabiomes.png");

		RenderingRegistry.registerEntityRenderingHandler(
				EntityScarecrow.class, new RenderScarecrow(
						new ModelScarecrow(), 0.4F));
	}

}
