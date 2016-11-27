/*
 * Copyright (c) Scott Killen and MisterFiber, 2012 This mod is
 * distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package extrabiomes.proxy;

import extrabiomes.module.fabrica.scarecrow.EntityScarecrow;
import extrabiomes.module.fabrica.scarecrow.ModelScarecrow;
import extrabiomes.module.fabrica.scarecrow.RenderScarecrow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerScarecrowRendering()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityScarecrow.class, new IRenderFactory<EntityScarecrow>() {
			@Override
			public Render<? super EntityScarecrow> createRenderFor(RenderManager manager) {
				return new RenderScarecrow(manager, new ModelScarecrow(), 0.4F);
			}
		});
    }
    
}
