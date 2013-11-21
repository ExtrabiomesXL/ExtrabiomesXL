/*
 * Copyright (c) Scott Killen and MisterFiber, 2012 This mod is
 * distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package extrabiomes.proxy;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extrabiomes.module.fabrica.scarecrow.EntityScarecrow;
import extrabiomes.module.fabrica.scarecrow.ModelScarecrow;
import extrabiomes.module.fabrica.scarecrow.RenderScarecrow;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public int registerBlockHandler(ISimpleBlockRenderingHandler handler)
    {
        final int renderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(renderId, handler);
        return renderId;
    }
    
    @Override
    public void registerScarecrowRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityScarecrow.class, new RenderScarecrow(new ModelScarecrow(), 0.4F));
    }
    
}
