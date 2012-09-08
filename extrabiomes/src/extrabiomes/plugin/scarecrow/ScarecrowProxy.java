/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.plugin.scarecrow;

import cpw.mods.fml.common.registry.EntityRegistry;
import extrabiomes.CommonProxy;

public class ScarecrowProxy extends CommonProxy {

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
}
