/*
 * Copyright (c) Scott Killen and MisterFiber, 2012 This mod is
 * distributed under the terms of the Minecraft Mod Public License 1.0,
 * or MMPL. Please check the contents of the license located in
 * /MMPL-1.0.txt
 */

package extrabiomes.client;

import static com.google.common.base.Preconditions.checkNotNull;
import net.minecraft.src.Achievement;
import net.minecraft.src.ModLoader;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
import extrabiomes.CommonProxy;
import extrabiomes.EntityScarecrow;
import extrabiomes.ModelScarecrow;
import extrabiomes.RenderScarecrow;
import extrabiomes.api.ExtrabiomesItem;

public class ClientProxy extends CommonProxy {

	@Override
	public void addAchievementDesc(Achievement achievement,
			String name, String description)
	{
		ModLoader.addAchievementDesc(checkNotNull(achievement), name,
				description);
	}

	@Override
	public void registerRenderInformation() {
		MinecraftForgeClient
				.preloadTexture("/extrabiomes/extrabiomes.png");

		if (ExtrabiomesItem.scarecrow.isPresent())
			RenderingRegistry.registerEntityRenderingHandler(
					EntityScarecrow.class, new RenderScarecrow(
							new ModelScarecrow(), 0.4F));
	}
}
