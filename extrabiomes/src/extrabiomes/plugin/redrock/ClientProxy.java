/*
 * Copyright (c) Scott Killen and MisterFiber, 2012 This mod is
 * distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package extrabiomes.plugin.redrock;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import extrabiomes.CommonProxy;
import extrabiomes.utility.SlabRenderer;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient
				.preloadTexture("/extrabiomes/extrabiomes.png");
		if (RedRock.halfSlabsEnabled()) {
			final SlabRenderer renderer = new SlabRenderer();
			RenderingRegistry.registerBlockHandler(renderer);
			RedRock.setSlabRenderId(renderer.getRenderId());
		}
	}
}
