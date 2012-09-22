/*
 * Copyright (c) Scott Killen and MisterFiber, 2012 This mod is
 * distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package extrabiomes.client;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import extrabiomes.CommonProxy;
import extrabiomes.Extrabiomes;
import extrabiomes.utility.RenderStairsHalfSlabs;

public class ClientProxy extends CommonProxy {

	private static boolean	rendersInitialized	= false;

	@Override
	public int getNextAvailableRenderId() {
		return RenderingRegistry.getNextAvailableRenderId();
	}

	void initRenders() {
		if (rendersInitialized) return;
		rendersInitialized = true;

		final ISimpleBlockRenderingHandler handler = new RenderStairsHalfSlabs();
		Extrabiomes.setSlabRenderId(Extrabiomes.proxy.getNextAvailableRenderId());
		Extrabiomes.setStairsRenderId(Extrabiomes.proxy.getNextAvailableRenderId());
		
		RenderingRegistry.registerBlockHandler(Extrabiomes.getSlabRenderId(), handler);
		RenderingRegistry.registerBlockHandler(Extrabiomes.getStairsRenderId(), handler);
	}

	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient
				.preloadTexture("/extrabiomes/extrabiomes.png");
	}

}
