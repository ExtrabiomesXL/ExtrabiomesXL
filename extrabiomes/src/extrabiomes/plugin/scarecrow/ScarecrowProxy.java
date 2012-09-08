/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.scarecrow;

import net.minecraft.src.CraftingManager;
import net.minecraft.src.IRecipe;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ScarecrowProxy {

	public void addName(Object object, String name) {
		LanguageRegistry.instance();
		LanguageRegistry.addName(object, name);
	}

	void addRecipe(IRecipe recipe) {
		CraftingManager.getInstance().getRecipeList().add(recipe);
	}

	public int findGlobalUniqueEntityId() {
		return EntityRegistry.findGlobalUniqueEntityId();
	}

	public void registerEntity(Class entityClass, String entityName,
			Object mod, int entityID, int trackingRange,
			int updateFrequency, boolean sendsVelocityUpdates)
	{
		EntityRegistry.registerModEntity(entityClass, entityName,
				entityID, mod, trackingRange, updateFrequency,
				sendsVelocityUpdates);
	}

	public void registerEntityID(Class entityClass, String entityName,
			int entityID)
	{
		EntityRegistry.registerGlobalEntityID(entityClass, entityName,
				entityID);
	}

	void registerRenderInformation() {}
}
