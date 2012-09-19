/**
 * This mod is distributed under the terms of the Minecraft Mod Public
 * License 1.0, or MMPL. Please check the contents of the license
 * located in /MMPL-1.0.txt
 */

package extrabiomes.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

public class EnhancedConfiguration extends Configuration {

	private static List<Integer>	assignedIdsList	= new ArrayList<Integer>();

	public static void releaseStaticResources() {
		assignedIdsList = null;
	}

	public EnhancedConfiguration(File file) {
		super(file);
	}

	private Property do_getOrCreateBlockIdProperty(String key,
			int defaultId, int exclusiveUpperBound)
	{
		final Map<String, Property> properties = categories
				.get(CATEGORY_BLOCK);
		if (properties.containsKey(key)) {
			final Property property = getOrCreateIntProperty(key,
					Configuration.CATEGORY_BLOCK, defaultId);
			assignedIdsList.add(Integer.parseInt(property.value));
			return property;
		} else {
			final Property property = new Property();
			properties.put(key, property);
			property.setName(key);

			if (Block.blocksList[defaultId] == null
					&& !assignedIdsList.contains(Integer
							.valueOf(defaultId)))
			{
				property.value = Integer.toString(defaultId);
				assignedIdsList.add(Integer.parseInt(property.value));
				return property;
			} else {
				for (int j = exclusiveUpperBound - 1; j >= 0; --j)
					if (Block.blocksList[j] == null
							&& !assignedIdsList.contains(Integer
									.valueOf(j)))
					{
						property.value = Integer.toString(j);
						assignedIdsList.add(Integer
								.parseInt(property.value));
						return property;
					}

				throw new RuntimeException(
						"No more block ids available for " + key);
			}
		}
	}

	@Override
	public Property getOrCreateBlockIdProperty(String key, int defaultId)
	{
		return do_getOrCreateBlockIdProperty(key, defaultId,
				Block.blocksList.length);
	}

	public Property getOrCreateRestrictedBlockIdProperty(String key,
			int defaultId)
	{
		return do_getOrCreateBlockIdProperty(key, defaultId, 256);
	}

}
